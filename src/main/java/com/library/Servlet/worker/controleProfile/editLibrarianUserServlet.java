package com.library.Servlet.worker.controleProfile;

import com.library.Data.LibrarianDAO;
import com.library.Objects.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/workerSection/editUserLibrarian")
public class editLibrarianUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibrarianDAO librarianDAO = new LibrarianDAO();
        int id = Integer.parseInt(req.getParameter("workerId"));
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        Librarian librarian = librarianDAO.getLibrarianById(id);
        librarian.setUserName(username);
        librarian.setHashPassword(password);
        if (librarianDAO.updateLibrarian(librarian)) {
            req.getSession(false).setAttribute("userName",username);
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
