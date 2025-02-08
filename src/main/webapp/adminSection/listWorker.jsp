<%@ page import="com.library.Data.LibrarianDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.Objects.Librarian" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workers</title>
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

        /* Table styles */
        .table-section {
            background-color: #222;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px rgba(0, 255, 0, 0.4);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            font-size: 1.1rem;
            border-bottom: 1px solid #444;
        }

        th {
            background-color: #333;
            color: #0f0;
        }

        td {
            background-color: #222;
            color: #0f0;
        }

        tr:hover {
            background-color: #333;
        }

        a.action-btn {
            padding: 5px 10px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1rem;
            margin: 0 5px;
            transition: background-color 0.3s ease;
        }

        a.delete-btn {
            background-color: #f00;
            color: #fff;
        }

        a.delete-btn:hover {
            background-color: #cc0000;
        }

        a.edit-btn {
            background-color: #0f0;
            color: #111;
        }

        a.edit-btn:hover {
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
        <h1>List of Librarians</h1>
    </header>

    <section class="table-section">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                LibrarianDAO librarianDAO = new LibrarianDAO();
                List<Librarian> librarians = librarianDAO.getAllLibrarian();
                for (Librarian librarian : librarians) {
            %>
            <tr>
                <td><%= librarian.getId() %></td>
                <td><%= librarian.getFirstName() %></td>
                <td><%= librarian.getLastName() %></td>
                <td><%= librarian.getUserName() %></td>
                <td>
                    <a href="?action=delete&id=<%=librarian.getId()%>" class="action-btn delete-btn">Delete</a>
                    <a href="?action=edit&id=<%=librarian.getId()%>" class="action-btn edit-btn">Edit</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </section>

    <footer>
        <p>&copy; 2024 Library Management System | Admin Section</p>
    </footer>
</div>
</body>
</html>




<%--
<%@ page import="com.library.Data.LibrarianDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.Objects.Librarian" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Workers</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>userName</th>
    </tr>
    <%
        LibrarianDAO librarianDAO = new LibrarianDAO();
        List<Librarian> librarians = librarianDAO.getAllLibrarian();
        for (Librarian librarian : librarians) {
    %>
    <tr>
        <td><%= librarian.getId() %></td>
        <td><%= librarian.getFirstName() %></td>
        <td><%= librarian.getLastName() %></td>
        <td><%= librarian.getUserName() %></td>
        <td><a href="?action=delete&id=<%=librarian.getId()%>">Delete</a> <a href="?action=edit&id=<%=librarian.getId()%>">Edit</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
--%>
