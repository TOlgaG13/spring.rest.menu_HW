<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add dishes</title>
</head>
<body>
<h2>Add dishes</h2>
<form action="/dishes/save" method="post">
    <label>Name:</label> <input type="text" name="name" required><br>
    <label>Price:</label> <input type="number" name="price" required><br>
    <label>Weight (g):</label> <input type="number" name="weight" required><br>
    <label>Discount:</label> <input type="checkbox" name="discount"><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
