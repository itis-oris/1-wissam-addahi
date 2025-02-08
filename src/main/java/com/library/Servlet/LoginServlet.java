package com.library.Servlet;

import com.library.Data.DataBaseConnection;
import com.library.Data.LibrarianDAO;
import com.library.Data.LoginDAO;
import com.library.Objects.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        HttpSession session = req.getSession();

        try (Connection conn = DataBaseConnection.getConnection()){
            LoginDAO loginDAO = new LoginDAO();

            if ("worker".equals(role)) {
                String username = req.getParameter("userName");
                String password = req.getParameter("password");
                if (loginDAO.validateWorker(username,password)) {
                    session.setAttribute("role", "worker");
                    LibrarianDAO librarianDAO = new LibrarianDAO();
                    Librarian librarian = librarianDAO.getLibrarianByUserName(username);
                    session.setAttribute("workerId",librarian.getId());
//                    req.getRequestDispatcher("workerSection.jsp").forward(req, resp);
                    resp.sendRedirect("workerSection.jsp");
                } else {
                    resp.sendRedirect("login.jsp");
                }
            } else if ("admin".equals(role)) {
                String password = req.getParameter("adminPassword");
                if (loginDAO.validateAdmin(password)) {
                    session.setAttribute("role", "admin");
                    session.setAttribute("username","admin");
                    resp.sendRedirect("adminSection.jsp");
                } else {
                    resp.sendRedirect("login.jsp");
                }
            } else if ("reader".equals(role)) {
                String phoneNumber = req.getParameter("telNum");
                String cardNum = req.getParameter("cardNum");
                if (loginDAO.validateReader(phoneNumber,cardNum)) {
                    session.setAttribute("role", "reader");
                    session.setAttribute("cardNumber",cardNum);
                    resp.sendRedirect("readerSection.jsp");
                } else {
                    resp.sendRedirect("login.jsp");
                }
            } else {
                resp.setStatus(400);
                /*Код состояния 400 означает «Плохой запрос».
                Этот код означает,
                что сервер не может вернуть ответ
                из-за ошибки на стороне клиента.*/
                resp.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }



    }
}
