<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Archive Readers</title>
    <link rel="stylesheet" href="../style/archive.css">
</head>
<body>
<div class="container">
    <header>
        <h1>ARCHIVE READERS DATABASE</h1>
        <p class="subtitle">Admin Section - Confidential</p>
    </header>

    <section class="table-section">
        <h2>Reader Archive</h2>
        <c:choose>
            <c:when test="${empty archiveReadersList}">
                <p class="no-data">No data available in the archive.</p>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>Library Card</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Date of Birth</th>
                            <th>Room ID</th>
                            <th>Date Deleted</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reader" items="${archiveReadersList}">
                            <tr>
                                <td>${reader.libraryCard}</td>
                                <td>${reader.firstName}</td>
                                <td>${reader.lastName}</td>
                                <td>${reader.dateOfBirth}</td>
                                <td>${reader.idRoom}</td>
                                <td>${reader.dateDelete}</td>
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
<%@ page import="com.library.Objects.ArchevReaders" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Archive Readers</title>
    <link rel="stylesheet" href="../style/archive.css">
</head>
<body>
<div class="container">
    <header>
        <h1>ARCHIVE READERS DATABASE</h1>
        <p class="subtitle">Admin Section - Confidential</p>
    </header>

    <section class="table-section">
        <h2>Reader Archive</h2>
        <%
            List<ArchevReaders> archiveReadersList = (List<ArchevReaders>) request.getAttribute("archiveReadersList");
            if (archiveReadersList == null || archiveReadersList.isEmpty()) {
        %>
        <p class="no-data">No data available in the archive.</p>
        <%
        } else {
        %>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Library Card</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>Room ID</th>
                    <th>Date Deleted</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (ArchevReaders reader : archiveReadersList) {
                %>
                <tr>
                    <td><%= reader.getLibraryCard() %></td>
                    <td><%= reader.getFirstName() %></td>
                    <td><%= reader.getLastName() %></td>
                    <td><%= reader.getDateOfBirth() %></td>
                    <td><%= reader.getIdRoom() %></td>
                    <td><%= reader.getDateDelete() %></td>
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
