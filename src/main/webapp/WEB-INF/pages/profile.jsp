<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div align="center">
    <h1>Your email is: ${email}, your roles are:</h1>
    <c:forEach var="s" items="${roles}">
        <h3><c:out value="${s}" /></h3>
    </c:forEach>

    <c:if test="${admin}">
        <p><a href="/admin">Click</a> for admin page</p>
    </c:if>

    <form action="/update" method="POST">
        Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
        Address:<br/><input type="text" name="address" value="${address}" /><br/>
        <input type="submit" value="Update" />
    </form>

    <p>Click to logout: <a href="/logout">LOGOUT</a></p>
</div>
</body>
</html>