package com.library.Servlet.admin;

import com.library.Data.DataBaseConnection;
import com.library.Objects.ArchevBooks;
import com.library.Objects.ArchevReaders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminSection/archiveBooks")
public class archiveBooks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ArchevBooks> archiveBooksList = new ArrayList<>();
        String sql = "SELECT * FROM ArchevBooks";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ArchevBooks archevBooks = new ArchevBooks(
                        resultSet.getString("bookTitle"),
                        resultSet.getInt("idRoom"),
                        resultSet.getInt("countDeleted"),
                        resultSet.getString("reason"),
                        resultSet.getDate("dateDeleted")
                );
                archiveBooksList.add(archevBooks);
            }

        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        }
        req.setAttribute("archiveBooksList", archiveBooksList);

        req.getRequestDispatcher("/adminSection/archiveBooks.jsp").forward(req, resp);
    }
}
