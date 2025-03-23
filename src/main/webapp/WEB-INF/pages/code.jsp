<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Code Verification</title>
</head>
<body>
<div align="center">
    <form action="/code" method="POST">
        Check your email and enter the code:<br/>
        Code:<br/><input type="text" name="inputCode"><br/>
        <input type="submit" name="submitCode" value="Verify Code"/>
    </form>

    <c:if test="${errorCode eq true}">
        <p style="color:red;">Wrong code!</p>
    </c:if>
</div>
</body>
</html>
