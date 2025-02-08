package com.library.Servlet.worker.daily.room;

import com.library.Data.RoomDAO;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/workerSection/editRoom")
public class EditRoomServlet extends HttpServlet {

    private static RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rooNumber = Integer.valueOf(req.getParameter("roomNumber"));
        Room room = roomDAO.getRoomByNumber(rooNumber);
        if (room != null) {
            req.setAttribute("room", room);
            req.getRequestDispatcher("/workerSection/editRoom.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/workerSection.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = roomDAO.updateRoom(new Room(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("number")),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("capacity")),
                req.getParameter("address")
        ));
        req.getSession(false).setAttribute("result", "editRoom");
        if (result) {
            req.getSession(false).setAttribute("resultMsg","Successfully");
        } else {
            req.getSession(false).setAttribute("resultMsg","Something went wrong");
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
