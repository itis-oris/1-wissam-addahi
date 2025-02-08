package com.library.Servlet.admin;

import com.library.Data.DataBaseConnection;
import com.library.Data.LibrarianDAO;
import com.library.Objects.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminSection/listWorker")
public class listWorker extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DataBaseConnection dbc = new DataBaseConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibrarianDAO librarianDAO = new LibrarianDAO();

        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("/adminSection/listWorker.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("delete") && req.getParameter("id") != null) {
            librarianDAO.deleteLibrarian(Integer.parseInt(req.getParameter("id")));
            req.getRequestDispatcher("/adminSection/listWorker.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("edit") && req.getParameter("id") != null) {
             Librarian librarian = librarianDAO.getLibrarianById(Integer.parseInt(req.getParameter("id")));
             req.setAttribute("librarian", librarian);
             req.getRequestDispatcher("/adminSection/editWorker.jsp").forward(req, resp);
        }
    }
}
