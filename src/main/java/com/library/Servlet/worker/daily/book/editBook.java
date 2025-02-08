package com.library.Servlet.worker.daily.book;

import com.library.Data.BookDAO;
import com.library.Data.RoomDAO;
import com.library.Objects.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/workerSection/editBook")
public class editBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();
        RoomDAO roomDAO = new RoomDAO();
        int idBook = Integer.parseInt(req.getParameter("id"));
        boolean result = bookDAO.updateBook(new Book(
                idBook,
                req.getParameter("title"),
                req.getParameter("author"),
                req.getParameter("publisher"),
                Integer.parseInt(req.getParameter("yearPublication")),
                req.getParameter("code")
        ));
        List<Integer> idRooms = (ArrayList<Integer>) req.getSession(false).getAttribute("idRooms");
        for (Integer id : idRooms) {
            if (!roomDAO.editCountBookByRoom(id,idBook,Integer.parseInt(req.getParameter("count"+id)))) {
                if(roomDAO.addCopiesNewBookToRooms(id,idBook,Integer.parseInt(req.getParameter("count"+id)))) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        req.getSession(false).setAttribute("resultMsg", result ? "Success" : "Fail");
        req.getSession(false).removeAttribute("idRooms");
        resp.sendRedirect("/workerSection.jsp");
    }
}
