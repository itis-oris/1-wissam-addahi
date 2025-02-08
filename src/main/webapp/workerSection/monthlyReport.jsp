
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://jakarta.ee/jstl/core" prefix="c" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Monthly Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f9f9f9;
        }
        .summary {
            margin: 20px 0;
        }
        .summary p {
            font-size: 16px;
            line-height: 1.5;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Monthly Library Report</h1>

    <h2>Summary</h2>
    <div class="summary">
        <p>Total Readers in Library: ${numberReaders}</p>
        <p>Readers under 20: ${readersL20}</p>
        <p>Education Levels: Primary (${Primary}%), Secondary (${Secondary}%), Higher (${Higher}%),
            Bachelor (${Bachelor}%), Master (${Master}%), PhD (${PhD}%)</p>
    </div>

    <h2>Readers Borrowed Books More Than a Month Ago</h2>
    <table>
        <thead>
        <tr>
            <th>Reader CardNumber</th>
            <th>Name</th>
<%--            <th>Borrowed Date</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="reader" items="${readersBM}">
            <tr>
                <td>${reader.libraryCardNumber}</td>
                <td>${reader.firstName} ${reader.lastName}</td>
<%--                <td>${reader.borrowedDate}</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Monthly Activity</h2>
    <p>Books in Library: ${countBooks}</p>
    <p>New Registrations: ${countReadersRegisterMonthly}</p>
    <%--<p>Readers Not Borrowed Books This Month: </p>
    <ul>
        <c:forEach var="reader" items="${readersNotBorrowed}">
            <li>${reader.firstName} ${reader.lastName}</li>
        </c:forEach>
    </ul>--%>
</div>
</body>
</html>

