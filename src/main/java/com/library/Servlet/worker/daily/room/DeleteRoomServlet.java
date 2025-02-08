package com.library.Servlet.worker.daily.room;

import com.library.Data.RoomDAO;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/workerSection/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
    private static RoomDAO dao = new RoomDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int number = Integer.parseInt(req.getParameter("number"));
        Room room = dao.getRoomByNumber(number);
        req.getSession(false).setAttribute("result", "deleteRoom");
        if (dao.deleteRoom(room.getId())) {
            req.getSession(false).setAttribute("resultMsg", "success");
        } else {
            req.getSession(false).setAttribute("resultMsg", "error");
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
