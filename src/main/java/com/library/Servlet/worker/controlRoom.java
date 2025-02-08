package com.library.Servlet.worker;

import com.library.Data.RoomDAO;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/workerSection/controlRoom")
public class controlRoom extends HttpServlet {

    /*
    -- Table for Reading Rooms
    CREATE TABLE ReadingRooms (
        id SERIAL PRIMARY KEY,
        number INT NOT NULL UNIQUE,
        name VARCHAR(255) NOT NULL,
        capacity INT NOT NULL,
        address VARCHAR(255) NOT NULL
    );
    * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("newRoom")) {
            req.getRequestDispatcher("/workerSection/controlRoom.jsp").forward(req, resp);
        } else if (action.equals("listRooms")) {
            RoomDAO roomDAO = new RoomDAO();
            List<Room> rooms = roomDAO.getALlRooms();
            req.setAttribute("rooms", rooms);
            req.getRequestDispatcher("/workerSection/controlRoom.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = (String) req.getAttribute("action");
//        if (action.equals("newRoom")) {
            RoomDAO roomDAO = new RoomDAO();
            Room room = new Room(
                    Integer.parseInt(req.getParameter("number")),
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("capacity")),
                    req.getParameter("address")
            );
            roomDAO.addRoom(room);
            resp.sendRedirect("/workerSection/controlRoom?action=listRooms");
//        }
    }

}
