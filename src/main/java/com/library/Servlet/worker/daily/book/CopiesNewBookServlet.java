package com.library.Servlet.worker.daily.book;

import com.library.Data.BookDAO;
import com.library.Data.RoomDAO;
import com.library.Objects.Book;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/workerSection/newBook")
public class CopiesNewBookServlet extends HttpServlet {

    BookDAO bookDAO = new BookDAO();
    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*if (req.getParameter("id") == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }*/
        /*
        * -- Table for Copies of Books by Room
            CREATE TABLE CopiesBookByRoom (
                id SERIAL PRIMARY KEY,
                idRoom INT REFERENCES ReadingRooms(id) ON DELETE CASCADE,
                idBook INT REFERENCES AvailableBooks(id) ON DELETE CASCADE,
                count INT NOT NULL DEFAULT 0
            );
        * */
        int id = Integer.parseInt(req.getParameter("id"));
        List<Room> rooms = roomDAO.getALlRooms();
        Book book = bookDAO.getBookById(id);
        req.setAttribute("book", book);
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/workerSection/newBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> idRooms = (List<Integer>) req.getSession(false).getAttribute("idRooms");
        int id = Integer.parseInt(req.getParameter("bookId"));
        for (Integer roomId : idRooms) {
            if (!roomDAO.addCopiesNewBookToRooms(roomId,id,Integer.parseInt(req.getParameter("count"+roomId)))) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        }
        req.getSession(false).removeAttribute("idRooms");
        resp.sendRedirect("/workerSection.jsp");
    }
}
