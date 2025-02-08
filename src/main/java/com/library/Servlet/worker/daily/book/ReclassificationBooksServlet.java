package com.library.Servlet.worker.daily.book;

import com.library.Data.BookDAO;
import com.library.Objects.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/workerSection/reClassBooks")
public class ReclassificationBooksServlet extends HttpServlet {
    BookDAO bookDAO = new BookDAO();

    private ArrayList<Book> books () {
        ArrayList<Book> sortedBooks = (ArrayList<Book>) bookDAO.getAllBooks();
        sortedBooks.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
        return sortedBooks;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("action").equals("reClass")) {
            resp.sendRedirect("/workerSection.jsp");
            return;
        }
        ArrayList<Book> sortedBooks = books();
        req.setAttribute("books", sortedBooks);
        req.getRequestDispatcher("/workerSection/reClassBooks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Book book : books()) {
            ids.add(book.getId());
        }
        boolean result = true;
        for (Integer id : ids) {
            if (!bookDAO.reclassificationBook(id,req.getParameter("newCode" + id))) {
                result = false;
                break;
            }
        }
        req.getSession(false).setAttribute("result", "reClassBooks");
        req.getSession(false).setAttribute("resultMsg", result ? "Successfully reclassification books" : "Something went wrong");
        resp.sendRedirect("/workerSection.jsp");
    }
}
