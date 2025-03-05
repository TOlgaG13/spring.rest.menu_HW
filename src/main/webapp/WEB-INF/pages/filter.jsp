<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Filter by price</title>
</head>
<body>
<h2>Filter by price</h2>
<form action="filteredDishes.jsp" method="get">
    <label>Minimum price:</label> <input type="number" name="minPrice" step="0.01" required><br>
    <label>Maximum price:</label> <input type="number" name="maxPrice" step="0.01" required><br>
    <input type="submit" value="Filter">
</form>
</body>
</html>