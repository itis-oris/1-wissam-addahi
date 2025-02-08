<%@ page import="com.library.Objects.Librarian" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%
  Librarian librarian = (Librarian) request.getAttribute("librarian");
%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Librarian - <%= librarian.getUserName() %></title>
<%--  <link rel="stylesheet" href="styles.css">--%>
  <style>
    /* General reset */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    /* Basic styles */
    body {
      font-family: 'Courier New', Courier, monospace;
      background-color: #111;
      color: #0f0;
      line-height: 1.5;
      font-size: 16px;
    }

    .container {
      width: 100%;
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }

    /* Header Styles */
    header {
      text-align: center;
      margin-bottom: 20px;
    }

    h1 {
      font-size: 2.5rem;
      color: #0f0;
      border-bottom: 2px solid #0f0;
      padding-bottom: 10px;
    }

    /* Form styles */
    .form-section {
      background-color: #222;
      padding: 30px;
      border-radius: 10px;
      margin-top: 20px;
      box-shadow: 0px 0px 20px rgba(0, 255, 0, 0.4);
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      font-size: 1.2rem;
      margin-bottom: 5px;
      color: #0f0;
    }

    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 10px;
      background-color: #333;
      border: 2px solid #0f0;
      color: #0f0;
      font-size: 1rem;
      border-radius: 5px;
    }

    input[type="text"]:focus, input[type="password"]:focus {
      outline: none;
      border-color: #0f0;
      background-color: #444;
    }

    /* Button styles */
    .submit-btn {
      padding: 10px;
      font-size: 1.2rem;
      border-radius: 5px;
      cursor: pointer;
      border: none;
      background-color: #0f0;
      color: #111;
      transition: background-color 0.3s ease;
      width: 100%;
    }

    .submit-btn:hover {
      background-color: #00cc00;
    }

    /* Footer styles */
    footer {
      text-align: center;
      margin-top: 40px;
      font-size: 0.9rem;
      color: #777;
    }

    footer p {
      margin: 10px 0;
    }

  </style>
</head>
<body>
<div class="container">
  <header>
    <h1>Edit Librarian</h1>
  </header>

  <section class="form-section">
    <%

      if (librarian == null) {
    %>
    <h2>Error: Librarian not found</h2>
    <%
        return;
      }
    %>
    <form action="/adminSection/editWorker" method="post">
      <input type="hidden" name="id" value="<%= librarian.getId() %>">

      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= librarian.getFirstName() %>" required>
      </div>

      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= librarian.getLastName() %>" required>
      </div>

      <div class="form-group">
        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" value="<%= librarian.getUserName() %>" required>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
      </div>

      <div class="form-group">
        <button type="submit" class="submit-btn">Save</button>
      </div>
    </form>
  </section>

  <footer>
    <p>&copy; 2024 Library Management System | Admin Section</p>
  </footer>
</div>
</body>
</html>




<%--
<%@ page import="com.library.Objects.Librarian" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%
    Librarian librarian = (Librarian) request.getAttribute("librarian");
    if (librarian == null) {
  %>
  <title>Error</title>
</head>
<body>
<h1>Error: Librarian not found</h1>
</body>
</html>
<%
    return;
  }
%>
<title>Edit Librarian - <%= librarian.getUserName() %></title>
</head>
<body>
<form action="/adminSection/editWorker" method="post">
  <input type="hidden" name="id" value="<%= librarian.getId() %>">
  <label for="firstName">First Name:</label>
  <input type="text" id="firstName" name="firstName" value="<%= librarian.getFirstName() %>" required><br>

  <label for="lastName">Last Name:</label>
  <input type="text" id="lastName" name="lastName" value="<%= librarian.getLastName() %>" required><br>

  <label for="userName">Username:</label>
  <input type="text" id="userName" name="userName" value="<%= librarian.getUserName() %>" required><br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>

  <button type="submit">Save</button>
</form>
</body>
</html>
--%>
