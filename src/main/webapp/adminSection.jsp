
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    session = request.getSession(false);
    if (session == null || !"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("/");
    } else {
        String userName = (String) session.getAttribute("username");
    }
%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    session = request.getSession(false);
    if (session == null || !"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("/");
    } else {
        String userName = (String) session.getAttribute("username");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Section</title>
    <link rel="stylesheet" href="style/admin.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Welcome, Admin: Wissam</h1>
    </header>

    <nav>
        <ul>
            <li><a href="adminSection/registerWorker">Register a Librarian</a></li>
            <li><a href="adminSection/listWorker">List Workers</a></li>
            <li><a href="adminSection/archiveBooks">Archive Books</a></li>
            <li><a href="adminSection/archiveReaders">Archive Readers</a></li>
            <%--<li><a href="#">Generate Reports</a></li>
            <li><a href="#">User Settings</a></li>--%>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <section class="info">
        <p>Here you can control your library system. Navigate through the options on the left to manage workers, books, and reports.</p>
    </section>

    <footer>
        <p>&copy; 2024 Library Management System | Admin Section</p>
    </footer>
</div>
</body>
</html>

<%--
<html>
<body>
<h1>Welcome, Admin: Wissam</h1>

<p>Here you can control your library</p>

<ul>
    <li><a href="adminSection/registerWorker">Register a librarien</a></li>
    <li><a href="adminSection/listWorker">List workers</a></li>
    <li><a></a></li>
    <li><a></a></li>
    <li><a></a></li>
    <li><a></a></li>
    <li><a></a></li>
    <li><a></a></li>
    <li><a></a></li>

</ul>
</body>
</html>


--%>
