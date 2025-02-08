package com.library.Servlet.worker.daily.room;

import com.library.Data.RoomDAO;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/workerSection/listRooms")
public class listRoomsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getALlRooms();
        req.getSession(false).setAttribute("result","listRooms");
        req.getSession(false).setAttribute("rooms",rooms);
        resp.sendRedirect("/workerSection.jsp");
    }
}
