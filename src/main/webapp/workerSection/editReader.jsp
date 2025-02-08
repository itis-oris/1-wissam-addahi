<%@ page import="com.library.Objects.Reader" %>
<%@ page import="com.library.Data.RoomDAO" %>
<%@ page import="com.library.Objects.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            /*height: 100vh;*/
        }

        form {
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 400px;
            box-sizing: border-box;
        }

        /* Form Title */
        form h1 {
            margin: 0 0 20px 0;
            font-size: 1.5rem;
            text-align: center;
            color: #333;
        }

        /* Form Labels */
        form label {
            font-size: 0.9rem;
            color: #555;
            margin-bottom: 5px;
            display: block;
        }

        /* Form Inputs */
        form input[type="text"],
        form input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 0.9rem;
        }

        /* Input Focus */
        form input[type="text"]:focus,
        form input[type="number"]:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
        }

        /* Submit Button */
        form input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Button Hover Effect */
        form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Form Container for Centering */
        form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        /* Responsive Design */
        @media (max-width: 480px) {
            form {
                width: 100%;
                margin: 0 20px;
            }
        }

    </style>
</head>
<body>
<%
    RoomDAO roomDAO = new RoomDAO();
    Reader reader = (Reader) request.getAttribute("reader");
    if (reader != null) {
%>
<form action="/workerSection/controlReader" method="post">
    <input type="hidden" name="type" value="editReader">
    <input type="hidden" name="id" value="<%=reader.getId()%>">
    <label>Library card:</label>
    <input type="text" name="libraryCard" value="<%=reader.getLibraryCardNumber()%>" required>
    <label>First Name:</label>
    <input type="text" name="firstName" value="<%=reader.getFirstName()%>">
    <label>Last Name:</label>
    <input type="text" name="lastName" value="<%=reader.getLastName()%>">
    <label>Passport Number:</label>
    <input type="text" name="passport" value="<%=reader.getPassportNumber()%>">
    <label>Date Birth:</label>
    <input type="date" name="birthDate" required value="<%=reader.getDateBirth()%>">
    <label>Address</label>
    <input type="text" name="address" required value="<%=reader.getAddress()%>">
    <label>Phone Number</label>
    <input type="tel" name="tel" required value="<%=reader.getPhoneNumber()%>">
    <label>Education level: Primary,Secondary or Higher</label>
    <input type="text" name="eduLevel" value="<%=reader.getEducationLevel()%>">
    <label>Academic Degree enter it like Bachelor,Master or PhD</label>
    <input type="text" name="academic" value="<%=reader.getAcademicDegree() != null ? reader.getAcademicDegree() : ""%>" placeholder="for who have Higher education">
    <label>Number Room:</label>
    <%
        Room room = roomDAO.getRoomById(reader.getIdRoom());
    %>
    <input type="number" name="room" value="<%=room != null ? room.getNumber() : ""%>">
    <input type="submit" value="Edit">
</form>
<%
    } else {
        %>
<form action="/workerSection/controlReader" method="get">
    <h2>Failed to find reader !!</h2>
    <label>Card library:</label>
    <input type="text" name="cardLibrary" required>
    <input type="submit" value="Edit">
</form>
        <%
    }
%>
</body>
</html>
