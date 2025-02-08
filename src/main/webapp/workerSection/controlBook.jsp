
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<%--
-- Table for Available Books
CREATE TABLE AvailableBooks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    yearPublication INT,
    code VARCHAR(50) NOT NULL UNIQUE
);
--%>


<%--
<form action="/workerSection/controlRoom" method="post">
    <label>title</label>
    <input type="text" name="title">
    <br>
    <label>author</label>
    <input type="text" name="author">
    <br>
    <label>publisher</label>
    <input type="text" name="publisher">
    <br>
    <label>year publication</label>
    <input type="number" name="yearPublication">
    <br>
    <label>code</label>
    <input type="text" name="code">
</form>
--%>


<%--<button id="btn-new">Add new book to library</button>
<br>
<button id="btn-add">Add new copies to exist book</button>
<br>


<form method="post" action="/workerSection/controlBook">

</form>--%>
</body>
</html>
