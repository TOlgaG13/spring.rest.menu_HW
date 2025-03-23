package com.restmenu.spring.rest.menu.config;

import com.restmenu.spring.rest.menu.models.CustomUser;
import com.restmenu.spring.rest.menu.models.UserRegisterType;
import com.restmenu.spring.rest.menu.models.UserRole;
import com.restmenu.spring.rest.menu.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthHandler  implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public AuthHandler(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.getOrDefault("email", "");
        CustomUser user = userService.findByEmail(email).orElse(null);

        if (user == null) {
            String randomPassword = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(randomPassword);

            CustomUser customUser = new CustomUser(
                    encodedPassword,
                    UserRole.USER,
                    UserRegisterType.GOOGLE,
                    email,
                    "",
                    ""
            );
            userService.addUser(customUser);
        } else {
            if (!user.getType().equals(UserRegisterType.GOOGLE)) {
                request.getSession().setAttribute("oauth_email", email);
                response.sendRedirect("/link-google-account");
                return;
            }
        }


        String redirectUrl = (String) request.getSession().getAttribute("redirect_url");
        if (redirectUrl == null || redirectUrl.contains("/login")) {
            redirectUrl = "/profile";
        }

        response.sendRedirect(redirectUrl);
    }
}