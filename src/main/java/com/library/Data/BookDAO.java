package com.library.Data;

import com.library.Objects.Book;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM AvailableBooks";
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("yearPublication"),
                        resultSet.getString("code")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(int id) {
        Book book = null;
        String query = "SELECT * FROM AvailableBooks WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                book = new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("yearPublication"),
                        resultSet.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public boolean addBook(Book book) {
        String query = "INSERT INTO AvailableBooks (title, author, publisher, yearPublication, code) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setString(3,book.getPublisher());
            statement.setInt(4,book.getYearPublication());
            statement.setString(5,book.getCode());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBook(Book book) {
        String query = "UPDATE AvailableBooks SET title = ? , author = ?, publisher = ?, yearPublication = ?, code = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setString(3,book.getPublisher());
            statement.setInt(4,book.getYearPublication());
            statement.setString(5,book.getCode());
            statement.setInt(6,book.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean killArchevBook (Book book , String reason) {
        ReaderDAO readerDAO = new ReaderDAO();
        int count = readerDAO.countBookInLibrary(book.getCode());
        String query = "INSERT INTO ArchevBooks (bookTitle, idRoom, countDeleted, reason) VALUES (?, NULL, ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setInt(2,count);
            statement.setString(3,reason);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBook(int id , String reason) {
        String query = "DELETE FROM AvailableBooks WHERE id = " + id;

        try (Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            if (killArchevBook(getBookById(id), reason)) {
                int rows = statement.executeUpdate();
                return rows > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    --------------------------------------------------------------------------------------------------
    public boolean deleteWithArchevBook (int bookId , int roomId , int countDel , String reason) {
//        String query = "DELETE FROM AvailableBooks WHERE id = " + bookId;
        String queryArchev = "INSERT INTO ArchevBooks (bookTitle, idRoom, countDeleted, reason) VALUES (?, ?, ?, ?)";
        Book book = getBookById(bookId);
        RoomDAO roomDAO = new RoomDAO();
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(queryArchev)) {
            statement.setString(1,book.getTitle());
            statement.setInt(2,roomId);
            statement.setInt(3,countDel);
            statement.setString(4,reason);
            if (roomDAO.editCountByDeleteBookInRoom(roomId,bookId,countDel)) {
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
    }
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Book getBookByCode(String code) {
        Book book = null;
        String query = "SELECT * FROM AvailableBooks WHERE code = '" + code + "'";

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                book = new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("yearPublication"),
                        resultSet.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<String> getAllCodesBooks () {
        List<Book> books = getAllBooks();
        List<String> cards = new ArrayList<>();
        for (Book book : books) {
            cards.add(book.getCode());
        }
        return cards;
    }

    public boolean reclassificationBook(int id,String newCode) {
        String sql = "UPDATE AvailableBooks SET code = '" + newCode + "' WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
//        Book book = getBookById(id);
//        book.setCode(newCode);
//        return updateBook(book);
    }

    public String getTitleByCode(String code) {
//        String query = "SELECT * FROM AvailableBooks WHERE code = '" + code + "'";
        Book book = getBookByCode(code);
        return book.getTitle();
    }

    public String getCodeByTitle(String title) {
        String query = "SELECT * FROM AvailableBooks WHERE title = '" + title + "'";
         try (Connection connection = DataBaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
             if (resultSet.next()) {
                 return resultSet.getString("title");
             } else {
                 return  "unfounded!";
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
    }

    public int getIdByCode(String code) {
        Book book = getBookByCode(code);
        return book.getId();
    }

    // this is my aidea but here i ask gpt
    public List<Book> findBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM AvailableBooks WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Use '%' for wildcard search
            statement.setString(1, "%" + title + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("yearPublication"),
                            resultSet.getString("code")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}
