<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Register</title>
</head>
<body>
<div align="center">
    <form action="newuser" method="POST">
        E-mail:<br/><input type="text" name="email"><br/>
        Password:<br/><input type="password" name="password"><br/>
        Phone:<br/><input type="text" name="phone"><br/>
        Address:<br/><input type="text" name="address"><br/>
        <input type="submit" />
    </form>

    <c:if test="${exists ne null}">
        <p>User already exists!</p>
    </c:if>
</div>
</body>
</html>