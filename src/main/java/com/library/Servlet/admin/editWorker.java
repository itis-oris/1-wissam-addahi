package com.library.Servlet.admin;

import com.library.Data.LibrarianDAO;
import com.library.Objects.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminSection/editWorker")
public class editWorker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibrarianDAO librarianDAO = new LibrarianDAO();
        Librarian librarian ;
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        librarian = new Librarian(id, firstName, lastName, userName, password);
        librarianDAO.updateLibrarian(librarian);
        resp.sendRedirect(req.getContextPath() + "/adminSection/listWorker");
    }
}
