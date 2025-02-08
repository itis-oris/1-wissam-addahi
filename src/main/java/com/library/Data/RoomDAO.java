package com.library.Data;

import com.library.Objects.Book;
import com.library.Objects.CopiesBookByRoom;
import com.library.Objects.Reader;
import com.library.Objects.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    /*
    * -- Table for Reading Rooms
    CREATE TABLE ReadingRooms (
        id SERIAL PRIMARY KEY,
        number INT NOT NULL UNIQUE,
        name VARCHAR(255) NOT NULL,
        capacity INT NOT NULL,
        address VARCHAR(255) NOT NULL
    );
    -- Table for Copies of Books by Room
    CREATE TABLE CopiesBookByRoom (
        id SERIAL PRIMARY KEY,
        idRoom INT REFERENCES ReadingRooms(id) ON DELETE CASCADE,
        idBook INT REFERENCES AvailableBooks(id) ON DELETE CASCADE,
        count INT NOT NULL DEFAULT 0
    );
    * */
    public List<Room> getALlRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM ReadingRooms";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("name"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("address")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomById(int id) {
        String sql = "SELECT * FROM ReadingRooms WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new Room(
                        resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("name"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRoom(Room room) {
        String sql = "INSERT INTO ReadingRooms (number, name, capacity, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,room.getNumber());
            statement.setString(2,room.getName());
            statement.setInt(3,room.getCapacity());
            statement.setString(4,room.getAddress());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoom(Room room) {
        String sql = "UPDATE ReadingRooms SET name = '" + room.getName() + "', capacity = " + room.getCapacity() + ", address = '" + room.getAddress() + "' WHERE id = " + room.getId();
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoom(int id) {
        String sql = "DELETE FROM ReadingRooms WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Room getRoomByNumber(int number) {
        String sql = "SELECT * FROM ReadingRooms WHERE number = " + number;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new Room(
                        resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("name"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> listBooksByRoom(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        List<Book> books = new ArrayList<>();
        List<CopiesBookByRoom> copiesBookByRooms = new ArrayList<>();
        List<Integer> idBooks = new ArrayList<>();
        String sql = "SELECT * FROM CopiesBookByRoom WHERE idRoom = " + room.getId();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                copiesBookByRooms.add(new CopiesBookByRoom(
                        resultSet.getInt("id"),
                        resultSet.getInt("idRoom"),
                        resultSet.getInt("idBook"),
                        resultSet.getInt("count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BookDAO bookDAO = new BookDAO();
        for (CopiesBookByRoom copiesBookByRoom : copiesBookByRooms) {
            Book book = bookDAO.getBookById(copiesBookByRoom.getBookId());
            book.setRoomNumber(roomNumber);
            book.setCountCopies(copiesBookByRoom.getCopies());
            books.add(book);
        }
        return books;
    }

    public int getCountBookByRoom (int /*roomNumber*/ idRoom , int idBook/*String codeBook*/) {
        /*BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByCode(codeBook);
        Room room = getRoomByNumber(roomNumber);*/
        int count = 0;
        String sql = "SELECT * FROM CopiesBookByRoom WHERE idRoom = " + idRoom + " AND idBook = " + idBook;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                count += resultSet.getInt("count");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean editCountBookByRoom (int idRoom, int idBook , int count) {
        String sql = "UPDATE CopiesBookByRoom SET count = " + count + " WHERE idRoom = " + idRoom  + " AND idBook = " + idBook;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editCountByDeleteBookInRoom(int idRoom /*roomNumber*/, int idBook /*String code*/ , int countDelete) {
        /*Room room = getRoomByNumber(roomNumber);
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByCode(code);*/
        if (getCountBookByRoom(idRoom /*roomNumber*/,idBook /*code*/) < countDelete) {
            return false;
        }
        String sql = "UPDATE CopiesBookByRoom SET count = (count - " + countDelete + ") WHERE idRoom = " + /*room.getId()*/ idRoom + " AND idBook = " + idBook /*book.getId()*/;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editCountBooksByAdding(int idRoom,int idBook , int countAdd) {
        String sql = "UPDATE CopiesBookByRoom SET count = ( count + " + countAdd + " ) WHERE idRoom = " +  idRoom + " AND idBook = " + idBook ;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCopiesNewBookToRooms (int idRoom, int idBook, int count) {
        String sql = "INSERT INTO CopiesBookByRoom (idRoom,idBook,count) VALUES ("+ idRoom +","+ idBook +"," + count + ")";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean assignedBookToReader(String cardNumber,String bookCode) {
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.getReaderByCard(cardNumber);
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByCode(bookCode);
        if (!isAlreadyHavecopy(reader.getId(),book.getId())) {
            if (editCountByDeleteBookInRoom(reader.getIdRoom(), book.getId(), 1)) {
                String sql = "INSERT INTO AssignedBookToReader (idBook,idReader) VALUES (" + book.getId() + "," + reader.getId() + ")";
                try (Connection connection = DataBaseConnection.getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    return statement.executeUpdate() > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public boolean isAlreadyHavecopy(int idReader,int idBook) {
        String sql = "SELECT * FROM AssignedBookToReader WHERE idBook = " + idBook + " AND idReader = " + idReader ;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBookFromReader(String cardNumber, String bookCode) {
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.getReaderByCard(cardNumber);
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByCode(bookCode);
        if (editCountBooksByAdding(reader.getIdRoom(),book.getId(),1)) {
            String sql = "DELETE FROM AssignedBookToReader WHERE idBook = " + book.getId() + " AND idReader = " + reader.getId();
            try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
