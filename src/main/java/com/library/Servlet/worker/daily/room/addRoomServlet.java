package com.library.Servlet.worker.daily.room;

import com.library.Data.RoomDAO;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/workerSection/addNewRoom")
public class addRoomServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberRoom = Integer.parseInt(req.getParameter("number"));
        String roomName = req.getParameter("name");
        int roomCapacity = Integer.parseInt(req.getParameter("capacity"));
        String roomAddress = req.getParameter("address");
        RoomDAO roomDAO = new RoomDAO();
        Room room = new Room(numberRoom, roomName, roomCapacity, roomAddress);
        req.getSession(false).setAttribute("result","addNewRoom");
        if (roomDAO.addRoom(room)) {
            req.getSession(false).setAttribute("resultMsg","Successfully");
        } else {
            req.getSession(false).setAttribute("resultMsg", "Something went wrong");
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
