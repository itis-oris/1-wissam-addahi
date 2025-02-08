<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Librarian</title>
<%--    <link rel="stylesheet" href="styles.css">--%>
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
        .submit-btn, .reset-btn {
            padding: 10px;
            font-size: 1.2rem;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            width: 48%;
        }

        .submit-btn {
            background-color: #0f0;
            color: #111;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #00cc00;
        }

        .reset-btn {
            background-color: #333;
            color: #0f0;
            transition: background-color 0.3s ease;
        }

        .reset-btn:hover {
            background-color: #444;
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
        <h1>Register a New Librarian</h1>
    </header>

    <section class="form-section">
        <form action="/adminSection/registerWorker" method="post">
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" name="firstName" id="firstName" required>
            </div>

            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" name="lastName" id="lastName" required>
            </div>

            <div class="form-group">
                <label for="userName">Username:</label>
                <input type="text" name="userName" id="userName" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
            </div>

            <div class="form-group">
                <button type="submit" class="submit-btn">Register Librarian</button>
                <button type="reset" class="reset-btn">Reset</button>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register worker</title>
</head>
<body>
&lt;%&ndash;
CREATE TABLE Librarian (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    userName VARCHAR(50) NOT NULL UNIQUE,
    hashPassword VARCHAR(255) NOT NULL
);
&ndash;%&gt;
<form action="/adminSection/registerWorker" method="post">
  <label>First Name: </label>
  <input type="text" name="firstName">
  <br>
  <label>Last Name: </label>
  <input type="text" name="lastName">
  <br>
  <label>User Name: </label>
  <input type="text" name="userName">
  <br>
  <label>Password: </label>
  <input type="password" name="password">
  <br>
  <input type="submit"><span>..--...--..</span><input type="reset">
</form>
</body>
</html>
--%>
