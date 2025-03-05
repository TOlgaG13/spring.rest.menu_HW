<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Dishes</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Menu</h1>
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
            <td>${dish.weight} г</td>
            <td>${dish.discount ? "Yes" : "No"}</td>
        </tr>
    </c:forEach>
</table>

<h2>Add Dishes</h2>
<form action="/dishes/save" method="post">
    <label>Name:</label> <input type="text" name="name" required><br>
    <label>Price:</label> <input type="number" name="price" step="0.01" required><br>
    <label>Weight (g):</label> <input type="number" name="weight" required><br>
    <label>Discount:</label> <input type="checkbox" name="discount"><br>
    <button type="submit">Add</button>
</form>

</body>
</html><h2>Filter by price</h2>
<form action="/dishes/filter" method="get">
    <label>Min.price:</label> <input type="number" name="minPrice" required>
    <label>Max.price:</label> <input type="number" name="maxPrice" required>
    <button type="submit">Filter</button>
</form>