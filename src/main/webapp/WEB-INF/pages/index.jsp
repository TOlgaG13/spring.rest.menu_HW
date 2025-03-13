<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<h1>Menu dishes</h1>

<a href="/dish/add">Add dish</a>

<form action="/dish/filter-price" method="get">
    <label>Price from: <input type="number" step="0.01" name="minPrice"></label>
    <label>to: <input type="number" step="0.01" name="maxPrice"></label>
    <button type="submit">Filter</button>
</form>

<form action="/dish/discount" method="get">
    <button type="submit">Show discounted dishes</button>
</form>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Weight</th>
        <th>Discount</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <tr>
            <td>${dish.name}</td>
            <td>${dish.price}</td>
            <td>${dish.weight} г</td>
            <td>${dish.discount ? 'Yes' : 'No'}</td>
            <td>
                <form action="/order/add/${dish.id}" method="post">
                    <button type="submit">Add to order</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<h2>Dishes with a total weight of no more than 1 kg</h2>

<a href="/dishes/under-1kg">View light dishes</a>
<a href="/orders">View orders</a>

</body>
</html>
