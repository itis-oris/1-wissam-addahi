package com.library.Servlet.admin;

import com.library.Data.LibrarianDAO;
import com.library.Objects.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/adminSection/registerWorker")
public class RegisterWorker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminSection/registerWorker.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        LibrarianDAO librarianDAO = new LibrarianDAO();
        Librarian librarian = new Librarian(firstName, lastName, userName, password);
        librarianDAO.addLibrarian(librarian);
        resp.sendRedirect(req.getContextPath() + "/adminSection/listWorker");
    }
}
