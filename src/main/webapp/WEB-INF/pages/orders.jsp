<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Orders</h1>
<table border="1">
    <tr>
        <th>Dish</th>
        <th>Price</th>
        <th>Weight</th>
        <th>Discount</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.dishes.name}</td>
            <td>${order.dishes.price} грн</td>
            <td>${order.dishes.weight} г</td>
            <td>
                <c:choose>
                    <c:when test="${order.dishes.discount}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="/order/remove/${order.dishes.id}" method="post">
                    <button type="submit">Remove from order</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/">Back</a>
</body>
</html>

