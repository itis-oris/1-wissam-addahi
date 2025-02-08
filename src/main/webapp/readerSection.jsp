<%@ page import="com.library.Data.ReaderDAO" %>
<%@ page import="com.library.Objects.Reader" %>
<%@ page import="com.library.Data.RoomDAO" %>
<%@ page import="com.library.Objects.Room" %>
<%@ page import="com.library.Objects.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reader Profile</title>
    <link rel="stylesheet" href="style/reader.css">
</head>
<body>
<%
    String libraryCard = (String) session.getAttribute("cardNumber");
    ReaderDAO readerDAO = new ReaderDAO();
    Reader reader = readerDAO.getReaderByCard(libraryCard);
    String degree = reader.getAcademicDegree() != null && !reader.getAcademicDegree().isEmpty() ? reader.getAcademicDegree() : "";
    List<Book> assignedBooks = readerDAO.getBooksAssignedToReader(reader.getId());
%>

<div class="container">
    <!-- Header -->
    <header>
        <h1>Welcome, <%= reader.getFirstName() %>!</h1>
        <div class="profile-actions">
            <a href="logout" class="btn">Logout</a>
        </div>
    </header>

    <!-- Profile Info Section -->
    <section class="profile-info">
        <h2>My Information</h2>
        <div class="info-card">
            <p><strong>First Name:</strong> <%= reader.getFirstName() %></p>
            <p><strong>Last Name:</strong> <%= reader.getLastName() %></p>
            <p><strong>Library Card:</strong> <%= reader.getLibraryCardNumber() %></p>
            <p><strong>Passport:</strong> <%= reader.getPassportNumber() %></p>
            <p><strong>Date of Birth:</strong> <%= reader.getDateBirth() %></p>
            <p><strong>Address:</strong> <%= reader.getAddress() %></p>
            <p><strong>Phone Number:</strong> <%= reader.getPhoneNumber() %></p>
            <p><strong>Education Level:</strong> <%= reader.getEducationLevel() %></p>
            <p><%= degree.isEmpty() ? "" : "<strong>Academic Degree:</strong> " + degree %></p>
        </div>
    </section>
    <!-- Notification if Books are Borrowed for Over a Month -->
    <%
        if (!assignedBooks.isEmpty()) {
            for (Book book : assignedBooks) {
                Date dateAssigned = book.getDateAssigned();
                Calendar currentDate = Calendar.getInstance();
                currentDate.add(Calendar.MONTH, -1);
                Date oneMonthAgo = currentDate.getTime();

                if (dateAssigned.before(oneMonthAgo)) {
    %>
    <div class="notification">
        <h2>You borrowed this book more than 1 month ago!</h2>
        <p><%= book.getTitle() + " (" + book.getCode() + ")" %><br>
            Author: <%=book.getAuthor() %> , Date Assigned: <%=book.getDateAssigned()%> <br>
            Please return it to the library.</p>
    </div>
    <%
                }
            }
        }
    %>
    <!-- Room Info Section -->
    <section class="room-info">
        <h2>My Reading Room</h2>
        <div class="room-card">
            <%
                RoomDAO roomDAO = new RoomDAO();
                Room room = roomDAO.getRoomById(reader.getIdRoom());
            %>
            <p><strong>Room:</strong> <%= room.getName() %></p>
            <p><strong>Room Number:</strong> <%= room.getNumber() %></p>
            <p><strong>Capacity:</strong> <%= room.getCapacity() %></p>
            <p><strong>Address:</strong> <%= room.getAddress() %></p>
        </div>
    </section>

    <!-- Borrowed Books Section -->
    <section class="book-list">
        <h2>Books I Have Borrowed</h2>
        <%

            if (!assignedBooks.isEmpty()) {
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Year Published</th>
                    <th>Code</th>
                    <th>Copies in Library</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Book book : assignedBooks) {
                %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td><%= book.getYearPublication() %></td>
                    <td><%= book.getCode() %></td>
                    <td><%= readerDAO.countBookInLibrary(book.getCode()) %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <%
            }
        %>
    </section>

    <!-- Room Books Section -->
    <section class="room-books">
        <h2>Books in My Reading Room</h2>
        <%
            List<Book> booksRoom = roomDAO.listBooksByRoom(room.getNumber());
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Year Published</th>
                    <th>Code</th>
                    <th>Count</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Book book : booksRoom) {
                %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td><%= book.getYearPublication() %></td>
                    <td><%= book.getCode() %></td>
                    <td><%= roomDAO.getCountBookByRoom(room.getId(), book.getId()) %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </section>



</div>

<script src="script/reader.js"></script>
</body>
</html>

