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

@WebServlet("/workerSection/controlBook")
public class ControlBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        BookDAO bookDAO = new BookDAO();

        switch (type) {
            case "addNewBook" -> {
                String code = req.getParameter("code");
                boolean result = bookDAO.addBook(new Book(
                        req.getParameter("title"),
                        req.getParameter("author"),
                        req.getParameter("publisher"),
                        Integer.parseInt(req.getParameter("yearPublication")),
                        code
                ));
                req.getSession(false).setAttribute("result", "addNewBook");
                req.getSession(false).setAttribute("resultMsg", result ? "Successfully added new book" : "Something went wrong");

                /*if (result) {
                    Book book = bookDAO.getBookByCode(code);
                    RoomDAO roomDAO = new RoomDAO();
                    List<Room> rooms = roomDAO.getALlRooms();
                }*/

                if (result) {
                    int id = bookDAO.getIdByCode(code);
                    resp.sendRedirect("/workerSection/newBook?id=" + id);
                    return;
                }

            }
            case "deleteBook" -> {
                String code = req.getParameter("code");
                Book book = bookDAO.getBookByCode(code);
                req.getSession(false).setAttribute("result", "deleteBook");
                if (book != null) {
                    boolean result = bookDAO.deleteBook(book.getId(), req.getParameter("reason"));
                    req.getSession(false).setAttribute("resultMsg", result ? "Successfully deleted book" : "Something went wrong");
                } else {
                    req.getSession(false).setAttribute("resultMsg", "I think you have a wrong code");
                }
            }
            case "listBooks" -> {
                List<Book> books = bookDAO.getAllBooks();
                req.getSession(false).setAttribute("result", "listBooks");
                req.getSession(false).setAttribute("listBooks", books);
            }
            case "editBook" -> {
                req.getSession(false).setAttribute("result", "editBook");
                Book book = bookDAO.getBookByCode(req.getParameter("code"));
                req.setAttribute("book", book);
                req.getRequestDispatcher("/workerSection/editBook.jsp").forward(req, resp);
                return;
            }
            case "listBookByRoom" -> {
                req.getSession(false).setAttribute("result", "listBookByRoom");
                RoomDAO roomDAO = new RoomDAO();
                int numberRoom = Integer.parseInt(req.getParameter("number"));
                Room room = roomDAO.getRoomByNumber(numberRoom);
                List<Book> books = roomDAO.listBooksByRoom(numberRoom);
                req.getSession(false).setAttribute("idRoom", room.getId());
                req.getSession(false).setAttribute("listBooksInRoom", books);
            }
            case "addCopiesBookToRoom" -> {
                RoomDAO roomDAO = new RoomDAO();
                boolean res = roomDAO.editCountBooksByAdding(
                        roomDAO.getRoomByNumber(Integer.parseInt(req.getParameter("number"))).getId(),
                        bookDAO.getBookByCode(req.getParameter("code")).getId(),
                        Integer.parseInt(req.getParameter("count"))
                );
                req.getSession(false).setAttribute("result", "addCopiesBookToRoom");
                req.getSession(false).setAttribute("resultMsg", res ? "Successfully added copies book" : "Something went wrong");
            }
            case "removeCopiesFromRoom" -> {
                RoomDAO roomDAO = new RoomDAO();
                boolean res = bookDAO.deleteWithArchevBook(
                        bookDAO.getBookByCode(req.getParameter("code")).getId(),
                        roomDAO.getRoomByNumber(Integer.parseInt(req.getParameter("number"))).getId(),
                        Integer.parseInt(req.getParameter("count")),
                        req.getParameter("reason")
                );
                req.getSession(false).setAttribute("result", "removeCopiesFromRoom");
                req.getSession(false).setAttribute("resultMsg", res ? "Successfully deleted book" : "Something went wrong");
            }
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
