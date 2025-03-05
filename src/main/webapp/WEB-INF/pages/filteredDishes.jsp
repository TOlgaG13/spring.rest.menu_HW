<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dishes in the price range</title>
</head>
<body>
<h2>Filtered dishes</h2>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Weight</th>
        <th>Discount</th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <tr>
            <td>${dish.name}</td>
            <td>${dish.price}</td>
            <td>${dish.weight}</td>
            <td>${dish.discount ? "Так" : "Ні"}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/dishes/list">⬅ Return to list</a>
</body>
</html>



