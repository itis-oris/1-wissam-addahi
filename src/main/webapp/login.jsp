<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to Library</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
<h1>✨ Welcome to the Galaxy Library ✨</h1>
<div>
    <form action="/login" method="post">
        <label for="role">Select Your Role:</label>
        <select id="role" name="role">
            <option selected value="reader">Reader</option>
            <option value="worker">Worker</option>
            <option value="admin">Admin</option>
        </select>

        <div id="roleDiv">
            <label for="numberR">Phone Number:</label>
            <input type="tel" name="telNum" id="numberR" placeholder="Enter your phone number">

            <label for="cardR">Library Card Number:</label>
            <input id="cardR" name="cardNum" type="password" placeholder="Enter your card number">
        </div>

        <input type="submit" value="Log In">
        <button type="reset">Reset</button>
    </form>
</div>
<div class="stars"></div>

<script src="script/login.js"></script>


</body>
</html>
