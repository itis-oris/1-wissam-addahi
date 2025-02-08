<%@ page import="com.library.Objects.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.Data.RoomDAO" %>
<%@ page import="com.library.Data.BookDAO" %>
<%@ page import="com.library.Data.ReaderDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wissam's Space Library</title>
    <link rel="stylesheet" href="/style/home.css">
</head>
<body>
<%
    String role = (String) session.getAttribute("role");

%>

<!-- Header -->
<header>
    <div class="header-content">
        <h1>Wissam's Space Library</h1>
        <div class="menu-toggle" onclick="toggleMenu()">
            <span></span>
            <span></span>
            <span></span>
        </div>
        <nav>
            <ul>
                <li><a href="#about">About</a></li>
                <li><a href="#rooms">Rooms</a></li>
                <li><a href="#stats">Statistics</a></li>
                <li><a href="#program">Program</a></li>
            </ul>
        </nav>
        <div class="profile-actions">
            <% if (role != null) { %>
            <a class="btn profile" href="/<%=role%>Section.jsp">Profile</a>
            <a class="btn logout" href="/logout">Logout</a>
            <% } else { %>
            <a class="btn login" href="login.jsp">Login</a>
            <% } %>
        </div>
    </div>
</header>

<%--<header>
    <div class="header-content">
        <h1>Wissam's Space Library</h1>
        <nav>
            <ul>
                <li><a href="#about"><i class="icon-info"></i> About</a></li>
                <li><a href="#rooms"><i class="icon-room"></i> Rooms</a></li>
                <li><a href="#stats"><i class="icon-stats"></i> Statistics</a></li>
                <li><a href="#program"><i class="icon-schedule"></i> Program</a></li>
            </ul>
        </nav>
        <div class="profile-actions">
            <%
                String role = (String) session.getAttribute("role");
                if (role != null) {
            %>
            <a class="btn profile" href="/<%=role%>Section.jsp">Profile</a>
            <a class="btn logout" href="/logout">Logout</a>
            <%
            } else {
            %>
            <a class="btn login" href="login.jsp">Login</a>
            <%
                }
            %>
        </div>
    </div>
</header>--%>

<!-- Image Slider -->
<section class="slider">
    <div class="slides">
        <img src="/media/images/room1.webp" alt="Galaxy-themed Reading Room">
        <%--<img src="/media/images/room2.webp" alt="Space Capsule Reading Nook">
        <img src="/media/images/room3.webp" alt="Planetarium-Inspired Study Hall">--%>
    </div>
   <%-- <div class="controls">
        <button class="prev">&#10094;</button>
        <button class="next">&#10095;</button>
    </div>--%>
</section>

<!-- About Section -->
<section id="about">
    <h2>About Wissam's Space Library</h2>
    <p>
        Wissam's Library combines the beauty of knowledge with the mystery of the cosmos.
        Established in London, it offers readers a unique space-themed environment for
        exploration and learning.
    </p>
</section>

<!-- Reading Rooms Section -->
<section id="rooms">
    <h2>Reading Rooms</h2>
    <%
        // Example of fetching room details dynamically
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getALlRooms();
        for (Room room : rooms) {
    %>
    <div class="room">
        <h3><%= room.getName() %></h3>
        <p>Address : <%= room.getAddress() + " , Capacity : " + room.getCapacity() %></p>
    </div>
    <% } %>
</section>

<!-- Statistics Section -->
<section id="stats">
    <h2>Library Statistics</h2>
    <div class="stats-grid">
        <div>
            <h3>Books Collection</h3>
            <%
                BookDAO bookDAO = new BookDAO();
                ReaderDAO readerDAO = new ReaderDAO();
            %>
            <p><%= bookDAO.getAllBooks().size() %> unique titles</p>
        </div>
        <div>
            <h3>Monthly Registrations</h3>
            <p><%= readerDAO.getMonthlyRegistrations() %> new readers</p>
        </div>
    </div>
</section>

<!-- Program Section -->
<section id="program">
    <h2>Library Program</h2>
    <p>Discover our events, workshops, and activities designed to inspire.</p>
</section>

<!-- Footer -->
<footer>
    <p>Contact us: <a href="mailto:support@wissamlibrary.com">support@wissamlibrary.com</a></p>
    <p><a href="#privacy-policy">Privacy Policy</a> | <a href="#tech-support">Technical Support</a></p>
    <p>&copy; 2024 Wissam's Space Library. All Rights Reserved.</p>
</footer>

<%--<script src="/script/home.js"></script>--%>
</body>
</html>


<%--
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Library</title>
</head>
<body>
<h1>Hi , this is new Library</h1>
<%
    String role = (String) session.getAttribute("role");
    if (role != null) {
%>
<a href="/<%=role%>Section.jsp">Go to work Section</a>
<a href="/logout">logout</a>
<%
} else {
    %>
<a href="login.jsp">login</a>

<%
}
%>
<a href="description.html">Description</a>
<hr>


</body>
</html>--%>
