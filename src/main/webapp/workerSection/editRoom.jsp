<%@ page import="com.library.Objects.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Room room = (Room) request.getAttribute("room");
%>
<html>
<head>
    <title>Edit Room #<%=room.getNumber()%></title>
  <style>
    /* General Styles */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    form {
      background-color: #ffffff;
      padding: 20px 30px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 400px;
      box-sizing: border-box;
    }

    /* Form Title */
    form h1 {
      margin: 0 0 20px 0;
      font-size: 1.5rem;
      text-align: center;
      color: #333;
    }

    /* Form Labels */
    form label {
      font-size: 0.9rem;
      color: #555;
      margin-bottom: 5px;
      display: block;
    }

    /* Form Inputs */
    form input[type="text"],
    form input[type="number"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
      box-sizing: border-box;
      font-size: 0.9rem;
    }

    /* Input Focus */
    form input[type="text"]:focus,
    form input[type="number"]:focus {
      border-color: #007bff;
      outline: none;
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
    }

    /* Submit Button */
    form input[type="submit"] {
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      border: none;
      border-radius: 5px;
      color: #fff;
      font-size: 1rem;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    /* Button Hover Effect */
    form input[type="submit"]:hover {
      background-color: #0056b3;
    }

    /* Form Container for Centering */
    form-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }

    /* Responsive Design */
    @media (max-width: 480px) {
      form {
        width: 100%;
        margin: 0 20px;
      }
    }

  </style>
</head>
<body>
<form action="/workerSection/editRoom" method="post">
  <input name="id" type="hidden" value="<%=room.getId()%>">
  <label>Room Number:</label>
  <input name="number" type="number" min="0" value="<%=room.getNumber()%>">
  <br>
  <label>Room Name:</label>
  <input name="name" type="text" value="<%=room.getName()%>">
  <br>
  <label>Capacity:</label>
  <input name="capacity" type="number" value="<%=room.getCapacity()%>">
  <br>
  <label>Address:</label>
  <input name="address" type="text" value="<%=room.getAddress()%>">
  <br>
  <input type="submit" value="Edit">
</form>
</body>
</html>
