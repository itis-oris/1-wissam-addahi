package com.library.Data;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {


    public LoginDAO() {

    }

    public boolean validateWorker(String username, String password) throws SQLException {
        String query = "SELECT hashPassword FROM Librarian WHERE username = ?";
        try (Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String hashPassword = resultSet.getString("hashPassword");
                return BCrypt.checkpw(password, hashPassword);
            }
        }
        return false;
    }

     public boolean validateAdmin(String password) throws SQLException {
        return validateWorker("admin", password);
     }

    public boolean validateReader (String phoneNumber, String password) throws SQLException {
        String query = "SELECT phoneNumber FROM Readers WHERE libraryCardNumber = '" + password +"'";
        try (Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("phoneNumber").equals(phoneNumber);
            }
        }
        return false;
    }

}
