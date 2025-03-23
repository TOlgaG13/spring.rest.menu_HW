package com.restmenu.spring.rest.menu.controllers;

import com.restmenu.spring.rest.menu.models.CustomUser;
import com.restmenu.spring.rest.menu.models.UserRegisterType;
import com.restmenu.spring.rest.menu.models.UserRole;
import com.restmenu.spring.rest.menu.services.EmailService;
import com.restmenu.spring.rest.menu.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public MyController(UserService userService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            String email = user.getUsername();
            CustomUser dbUser = userService.findByEmail(email).orElse(null);

            if (dbUser != null) {
                model.addAttribute("roles", user.getAuthorities());
                model.addAttribute("email", dbUser.getEmail());
                model.addAttribute("phone", dbUser.getPhone());
                model.addAttribute("address", dbUser.getAddress());
                model.addAttribute("admin", isAdmin(user));
            } else {
                throw new IllegalStateException();
            }
        } else if (principal instanceof DefaultOidcUser) {
            DefaultOidcUser oAuth2User = (DefaultOidcUser) principal;
            String email = (String) oAuth2User.getAttributes().get("email");
            CustomUser dbUser = userService.findByEmail(email).orElse(null);

            List<GrantedAuthority> roles = oAuth2User.getAuthorities().stream()
                    .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                    .collect(Collectors.toList());

            if (dbUser != null) {
                model.addAttribute("roles", roles);
                model.addAttribute("email", dbUser.getEmail());
                model.addAttribute("phone", dbUser.getPhone());
                model.addAttribute("address", dbUser.getAddress());
                model.addAttribute("admin", isAdmin(oAuth2User));
            } else {
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalStateException();
        }

        return "profile";
    }

    @PostMapping(value = "/update")
    public String update(@RequestParam(required = false) String phone,
                         @RequestParam(required = false) String address) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;

            String userEmail = user.getUsername();
            userService.updateUser(userEmail, phone, address);
        } else if (principal instanceof DefaultOidcUser) {
            DefaultOidcUser oAuth2User = (DefaultOidcUser) principal;
            String oAuthEmail = (String) oAuth2User.getAttributes().get("email");

            userService.updateUser(oAuthEmail, phone, address);
        }


        return "redirect:/";
    }

    @PostMapping("/newuser")
    public String handleNewUser(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String phone,
                                @RequestParam String address,
                                HttpSession session,
                                Model model) {

        if (userService.findByEmail(email).isPresent()) {
            model.addAttribute("exists", true);
            return "register";
        }


        String code = String.valueOf((int) (Math.random() * 9000) + 1000);

        // зберігаємо дані в сесії
        session.setAttribute("email", email);
        session.setAttribute("password", passwordEncoder.encode(password));
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("trueCode", code);

        emailService.sendMessage(email, code);
        // System.out.println("Verification code sent: " + code);

        return "redirect:/code";
    }

    @PostMapping("/code")
    public String verifyCode(@RequestParam String inputCode,
                             HttpSession session,
                             Model model) {

        String trueCode = (String) session.getAttribute("trueCode");

        if (trueCode != null && trueCode.equals(inputCode)) {

            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            String phone = (String) session.getAttribute("phone");
            String address = (String) session.getAttribute("address");

            userService.addUser(password, UserRole.USER, UserRegisterType.FORM, email, phone, address);


            session.invalidate();

            return "redirect:/login";
        } else {
            model.addAttribute("errorCode", true);
            return "code";
        }
    }


    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "toDelete[]", required = false) List<Long> ids,
                         Model model) {
        userService.deleteUsers(ids);
        model.addAttribute("users", userService.getAllUsers());

        return "admin";
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");

        if (referrer != null && !referrer.contains("/login") && !referrer.contains("/logout")) {
            request.getSession().setAttribute("redirect_url", referrer);
        }
        return "login";
    }

    @GetMapping("/code")
    public String showCodeForm() {
        return "code";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private boolean isAdmin(User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    private boolean isAdmin(DefaultOidcUser user) {
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }
}
