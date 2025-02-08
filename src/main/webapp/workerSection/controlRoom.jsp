<%@ page import="java.util.List" %>
<%@ page import="com.library.Objects.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control Rooms</title>
</head>
<body>

<%--
-- Table for Reading Rooms
    CREATE TABLE ReadingRooms (
        id SERIAL PRIMARY KEY,
        number INT NOT NULL UNIQUE,
        name VARCHAR(255) NOT NULL,
        capacity INT NOT NULL,
        address VARCHAR(255) NOT NULL
    );
--%>

<%
    String action = request.getParameter("action");
    if (action.equals("newRoom")) {
//        request.setAttribute("action","newRoom");

%>

<form action="/workerSection/controlRoom" method="post">
    <label>Number</label>
    <input type="number" name="number">
    <br>
    <label>Name</label>
    <input type="text" name="name">
    <br>
    <label>capacity</label>
    <input type="number" name="capacity">
    <br>
    <label>address</label>
    <input type="text" name="address">
    <br>
    <input type="submit"> <input type="reset">
</form>
<%
    } else if (action.equals("listRooms")) {

%>
<%--
-- Table for Reading Rooms
CREATE TABLE ReadingRooms (
    id SERIAL PRIMARY KEY,
    number INT NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    address VARCHAR(255) NOT NULL
);
--%>
<table>
    <tr>
        <th>Number</th>
        <th>Name</th>
        <th>Capacity</th>
        <th>Address</th>
    </tr>
    <%
        List<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
        for (Room room : rooms) {
    %>
    <tr>
    <th><%= room.getNumber() %></th>
    <th><%= room.getName() %></th>
    <th><%= room.getCapacity() %></th>
    <th><%= room.getAddress() %></th>
    <th>
        <a href="/workerSection/controlRoom?action=delete&id=<%=room.getId()%>">Delete</a>
        <a href="/workerSection/controlRoom?action=edit&id=<%=room.getId()%>">Edit</a>
    </th>
    </tr>
    <%
        }
    %>
</table>

<%
    }
%>
</body>
</html>
