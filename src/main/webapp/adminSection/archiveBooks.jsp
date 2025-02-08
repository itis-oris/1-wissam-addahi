<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Archive Books</title>
    <link rel="stylesheet" href="../style/archive.css">
</head>
<body>
<div class="container">
    <header>
        <h1>ARCHIVE BOOKS DATABASE</h1>
        <p class="subtitle">Admin Section - Confidential</p>
    </header>

    <section class="table-section">
        <h2>Books Archive</h2>
        <c:choose>
            <c:when test="${empty archiveBooksList}">
                <p class="no-data">No data available in the archive.</p>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Room ID</th>
                            <th>Count Deleted</th>
                            <th>Reason</th>
                            <th>Date Deleted</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="${archiveBooksList}">
                            <tr>
                                <td>${book.bookTitle}</td>
                                <td>${book.idRoom}</td>
                                <td>${book.countDeleted}</td>
                                <td>${book.reason}</td>
                                <td>${book.dateDeleted}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

    <footer>
        <p>Confidential © Wissam's Space Library - Admin Access Only</p>
    </footer>
</div>
</body>
</html>













<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.Objects.ArchevBooks" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Archive Books</title>
    <link rel="stylesheet" href="../style/archive.css">
</head>
<body>
<div class="container">
    <header>
        <h1>ARCHIVE BOOKS DATABASE</h1>
        <p class="subtitle">Admin Section - Confidential</p>
    </header>

    <section class="table-section">
        <h2>Books Archive</h2>
        <%
            List<ArchevBooks> archiveBooksList = (List<ArchevBooks>) request.getAttribute("archiveBooksList");
            if (archiveBooksList == null || archiveBooksList.isEmpty()) {
        %>
        <p class="no-data">No data available in the archive.</p>
        <%
        } else {
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Room ID</th>
                    <th>Count Deleted</th>
                    <th>Reason</th>
                    <th>Date Deleted</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (ArchevBooks book : archiveBooksList) {
                %>
                <tr>
                    <td><%= book.getBookTitle() %></td>
                    <td><%= book.getIdRoom() %></td>
                    <td><%= book.getCountDeleted() %></td>
                    <td><%= book.getReason() %></td>
                    <td><%= book.getDateDeleted() %></td>
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

    <footer>
        <p>Confidential © Wissam's Space Library - Admin Access Only</p>
    </footer>
</div>
</body>
</html>
--%>
