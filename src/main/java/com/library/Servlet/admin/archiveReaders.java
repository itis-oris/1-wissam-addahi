package com.library.Servlet.admin;

import com.library.Data.DataBaseConnection;
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

@WebServlet("/adminSection/archiveReaders")
public class archiveReaders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = "SELECT * FROM ArchevReaders";
        List<ArchevReaders> archiveReadersList = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ArchevReaders archevReaders = new ArchevReaders(
                        resultSet.getString("libraryCard"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getInt("idRoom"),
                        resultSet.getDate("dateDelete")
                );
                archiveReadersList.add(archevReaders);
            }

        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        }
        req.setAttribute("archiveReadersList", archiveReadersList);

        req.getRequestDispatcher("/adminSection/archiveReaders.jsp").forward(req, resp);
    }
}
