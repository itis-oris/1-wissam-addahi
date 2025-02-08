package com.library.Data;

import com.library.Objects.Librarian;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAO {

    public List<Librarian> getAllLibrarian() {
        List<Librarian> librarianList = new ArrayList<Librarian>();
        String sql = "SELECT * FROM Librarian";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Librarian librarian = new Librarian(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("userName"),
                        resultSet.getString("hashPassword")
                );
                librarianList.add(librarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarianList;
    }

    public boolean addLibrarian(Librarian librarian) {
//        librarian.setHashPassword(BCrypt.hashpw(librarian.getHashPassword(), BCrypt.gensalt()));
        String sql = "INSERT INTO Librarian (firstName, lastName, userName, hashPassword) VALUES (?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, librarian.getFirstName());
            statement.setString(2, librarian.getLastName());
            statement.setString(3, librarian.getUserName());
            statement.setString(4, librarian.getHashPassword());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Librarian getLibrarianByUserName(String userName) {
        Librarian librarian = null;
        String sql = "SELECT * FROM Librarian WHERE userName = '" + userName +"'";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                librarian = new Librarian(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("userName"),
                        resultSet.getString("hashPassword")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarian;
    }

    public boolean updateLibrarian(Librarian librarian) {
//        librarian.setHashPassword(BCrypt.hashpw(librarian.getHashPassword(), BCrypt.gensalt()));
        String sql = "UPDATE Librarian SET firstName = '" + librarian.getFirstName() + "', lastName = '" + librarian.getLastName() + "', userName = '" + librarian.getUserName() + "', hashPassword = '" + librarian.getHashPassword() + "' WHERE id = " + librarian.getId();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Librarian getLibrarianById(int id) {
        Librarian librarian = null;
        String sql = "SELECT * FROM Librarian WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                librarian = new Librarian(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("userName"),
                        resultSet.getString("hashPassword")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarian;
    }

    public boolean deleteLibrarian(int id) {
        String sql = "DELETE FROM Librarian WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
