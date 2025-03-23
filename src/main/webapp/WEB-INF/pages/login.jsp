<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <form action="/login" method="POST">
        Email:<br/><input type="text" name="username"><br/>
        Password:<br/><input type="password" name="password"><br/>
        <input type="submit" value="Login" />
    </form>

    <p><a href="/register">Register new user</a></p>

    <c:if test="${param.error ne null}">
        <p style="color:red;">Wrong email or password!</p>
    </c:if>

    <c:if test="${param.logout ne null}">
        <p style="color:green;">You have been logged out.</p>
    </c:if>

    <a href="/oauth2/authorization/google">
        <button>Join via Google</button>
    </a>
</div>
</body>
</html>
