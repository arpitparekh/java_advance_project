<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Product</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    form {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 10px;
      background-color: #f9f9f9;
    }
    label {
      display: block;
      margin-bottom: 8px;
      font-weight: bold;
    }
    input[type="text"], input[type="number"] {
      width: 100%;
      padding: 8px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    button {
      padding: 10px 15px;
      background-color: #2196F3;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #1976D2;
    }
  </style>
</head>
<body>

<h2>Add Product</h2>

<form action="${pageContext.request.contextPath}/product_list" method="post">
  <label for="name">Product Name:</label>
  <input type="text" id="name" name="name" required>

  <label for="price">Price:</label>
  <input type="number" id="price" name="price" min="0" required>

  <label for="quantity">Quantity:</label>
  <input type="number" id="quantity" name="quantity" required>

  <button type="submit">Submit</button>
</form>

</body>
</html>
