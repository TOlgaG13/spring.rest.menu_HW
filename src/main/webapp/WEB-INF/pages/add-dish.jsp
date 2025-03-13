<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Dish</title>
</head>
<body>
<h1>Add Dish</h1>
<form action="/dish/add" method="post">
    <label>Name: <input type="text" name="name"></label><br>
    <label>Price: <input type="number" step="0.01" name="price"></label><br>
    <label>Weight (g): <input type="number" name="weight"></label><br>
    <label>Discount: <input type="checkbox" name="discount"></label><br>
    <button type="submit">Add</button>
</form>
<a href="/">Back</a>
</body>
</html>
