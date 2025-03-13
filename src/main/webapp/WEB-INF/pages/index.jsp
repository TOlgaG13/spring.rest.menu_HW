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

    <c:choose>
        <c:when test="${not empty dishes}">
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
        </c:when>
        <c:otherwise>
            <c:forEach var="dish" items="${dishesPage.content}">
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
        </c:otherwise>
    </c:choose>

</table>

<h2>Dishes with a total weight of no more than 1 kg</h2>
<a href="/dishes/under-1kg">View light dishes</a>
<br>
<a href="/orders">View orders</a>

<c:if test="${dishesPage.totalPages > 1}">
    <div>
        <c:if test="${dishesPage.number > 0}">
            <a href="/?page=${dishesPage.number - 1}">Previous</a>
        </c:if>
        Page ${dishesPage.number + 1} of ${dishesPage.totalPages}
        <c:if test="${dishesPage.number + 1 < dishesPage.totalPages}">
            <a href="/?page=${dishesPage.number + 1}">Next</a>
        </c:if>
    </div>
</c:if>

</body>
</html>

