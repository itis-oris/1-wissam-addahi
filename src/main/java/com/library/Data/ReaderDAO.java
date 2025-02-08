package com.library.Data;

import com.library.Objects.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {

    /*CREATE TABLE Readers (
    id SERIAL PRIMARY KEY,
    libraryCardNumber VARCHAR(50) NOT NULL UNIQUE,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    passportNumber VARCHAR(50) NOT NULL UNIQUE,
    dateBirth DATE NOT NULL,
    address VARCHAR(255),
    phoneNumber VARCHAR(20),
    educationLevel VARCHAR(50),
    academicDegree VARCHAR(50),
    idRoom INT REFERENCES ReadingRooms(id),
    dateRegisteration DATE NOT NULL DEFAULT CURRENT_DATE
);*/
    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        String query = "SELECT * FROM Readers";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reader reader = new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("libraryCardNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("passportNumber"),
                        resultSet.getDate("dateBirth"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationLevel"),
                        resultSet.getString("academicDegree"),
                        resultSet.getInt("idRoom"),
                        resultSet.getDate("dateRegisteration")
                );
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    public List<Reader> getAllReadersInRoom (int idRoom) {
        List<Reader> readers = new ArrayList<>();
        String query = "SELECT * FROM Readers WHERE idRoom = " + idRoom;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reader reader = new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("libraryCardNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("passportNumber"),
                        resultSet.getDate("dateBirth"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationLevel"),
                        resultSet.getString("academicDegree"),
                        resultSet.getInt("idRoom"),
                        resultSet.getDate("dateRegisteration")
                );
                readers.add(reader);
            }
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers   ;
    }

    public Reader getReaderById(int id) {
        Reader reader = null;
        String query = "SELECT * FROM Readers WHERE id = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                reader = new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("libraryCardNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("passportNumber"),
                        resultSet.getDate("dateBirth"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationLevel"),
                        resultSet.getString("academicDegree"),
                        resultSet.getInt("idRoom"),
                        resultSet.getDate("dateRegisteration")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public boolean addReader(Reader reader) {
        String query = "INSERT INTO Readers (libraryCardNumber, firstName, lastName, passportNumber, dateBirth, address, phoneNumber, educationLevel, academicDegree, idRoom) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";




        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1,reader.getId());
            statement.setString(1,reader.getLibraryCardNumber());
            statement.setString(2,reader.getFirstName());
            statement.setString(3,reader.getLastName());
            statement.setString(4,reader.getPassportNumber());
            statement.setDate(5, new java.sql.Date(reader.getDateBirth().getTime()));
            statement.setString(6,reader.getAddress());
            statement.setString(7,reader.getPhoneNumber());
            statement.setString(8,reader.getEducationLevel());
//            statement.setString(9,reader.getAcademicDegree());
            if (reader.getAcademicDegree() == null) {
                statement.setString(9, null);
            } else {
                statement.setString(9, reader.getAcademicDegree());
            }

            statement.setInt(10,reader.getIdRoom());
//            statement.setDate(12,(Date) reader.getDateRegisteration());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateReader(Reader reader) {
        String query = "UPDATE Readers SET libraryCardNumber = ?, firstName = ?, lastName = ?, passportNumber = ?, dateBirth = ?, address = ?, phoneNumber = ?, educationLevel = ?, academicDegree = ?, idRoom = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,reader.getLibraryCardNumber());
            statement.setString(2,reader.getFirstName());
            statement.setString(3,reader.getLastName());
            statement.setString(4,reader.getPassportNumber());
            statement.setDate(5, new java.sql.Date(reader.getDateBirth().getTime()));
            statement.setString(6,reader.getAddress());
            statement.setString(7,reader.getPhoneNumber());
            statement.setString(8,reader.getEducationLevel());
            statement.setString(9,reader.getAcademicDegree());
            statement.setInt(10,reader.getIdRoom());
//            statement.setDate(11,(Date) reader.getDateRegisteration());
            statement.setInt(11,reader.getId());

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean archevReader (String libraryCard) {
        String sql = "INSERT INTO ArchevReaders (libraryCard,firstName,lastName, dateOfBirth, idRoom) VALUES (?, ?, ?, ?, ?)";
        Reader reader = getReaderByCard(libraryCard);
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,reader.getLibraryCardNumber());
            statement.setString(2,reader.getFirstName());
            statement.setString(3,reader.getLastName());
            statement.setDate(4, (Date) reader.getDateBirth());
            statement.setInt(5, reader.getIdRoom());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteReader(int id) {
        String query = "DELETE FROM Readers WHERE id = " + id;

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            if (isAssignedBook(id)) {
                return false;
            } else {
                if (archevReader(getReaderById(id).getLibraryCardNumber())) {
                    int rows = statement.executeUpdate();
                    return rows > 0;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Reader getReaderByCard(String cardNumber) {
        String query = "SELECT * FROM Readers WHERE libraryCardNumber = '" + cardNumber + "'";

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()) {
                return new Reader(
                        resultSet.getInt("id"),
                        resultSet.getString("libraryCardNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("passportNumber"),
                        resultSet.getDate("dateBirth"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationLevel"),
                        resultSet.getString("academicDegree"),
                        resultSet.getInt("idRoom"),
                        resultSet.getDate("dateRegisteration")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean reRegisterReader(String oldCardNumber, String newCardNumber) {
        Reader reader = getReaderByCard(oldCardNumber);
        reader.setLibraryCardNumber(newCardNumber);
        reader.setDateRegisteration(new java.util.Date());
        return updateReader(reader);
    }

    public boolean isAssignedBook (int idReader) {
        String query = "SELECT 1 FROM AssignedBookToReader WHERE idReader = " + idReader;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getIdByCard(String cardNumber) {
        Reader reader = getReaderByCard(cardNumber);
        return reader.getId();
    }

    public List<AssignedBookDetails> whichBooksAssignedToReader (String libraryCardNumber) {
        List<AssignedBookDetails> assignedBooks = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        int idReader = getIdByCard(libraryCardNumber);
        String query = "SELECT * FROM AssignedBookToReader WHERE idReader = " + idReader;

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idBook = resultSet.getInt("idBook");
                java.util.Date dateAssigned = resultSet.getDate("dateAssigned");

                Book book = bookDAO.getBookById(idBook);
                if (book != null) {
                    assignedBooks.add(new AssignedBookDetails(book.getId(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getYearPublication(),book.getCode(),dateAssigned));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignedBooks;
    }

    public java.util.Date whenWasBookAssignedToReader (String libraryCardNumber, String codeNumberBook) {
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookByCode(codeNumberBook);
        int idBook = book.getId();
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.getReaderByCard(libraryCardNumber);
        int idReader = reader.getId();

        String query = "SELECT * FROM AssignedBookToReader WHERE idReader = " + idReader + " AND idBook = " + idBook;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDate("dateAssigned");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reader> whichReadersBorrowedBooksMoreThanMounthAgo () {
        List<Reader> readers = new ArrayList<>();
        List<AssignedBookToReader> assignedBooksToReaders = new ArrayList<>();
        String query = "SELECT * FROM AssignedBookToReader";
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                int idReader = resultSet.getInt("idReader");
                readers.add(getReaderById(idReader));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    public int countBookInLibrary (String codeBook) {
        BookDAO bookDAO = new BookDAO();
        int count = 0;
        int id = bookDAO.getIdByCode(codeBook);
        String query = "SELECT count FROM CopiesBookByRoom WHERE idBook = " + id;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
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

    public List<Reader> whichReadersHaveBeenAssignedBookWhereTheNumberOfCopiesInTheLibraryDoesNotExceed(int two) {
        List<Reader> readers = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        String query = "SELECT * FROM AssignedBookToReader";

        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idReader = resultSet.getInt("idReader");
                int idBook = resultSet.getInt("idBook");
                String codeBook = bookDAO.getBookById(idBook).getCode();
                if (countBookInLibrary(codeBook) <= two) {
                    readers.add(getReaderById(idReader));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readers;
    }

    public int countReadersCurrentlyInLibrary () {
        String query = "SELECT COUNT(*) FROM Readers";
        int count = 0;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

//    // Get the current date
//    LocalDate currentDate = LocalDate.now();
//
//    // Calculate the period between the date of birth and current date
//        if (dateOfBirth != null && !dateOfBirth.isAfter(currentDate)) {
//        return Period.between(dateOfBirth, currentDate).getYears();

    /*public boolean chekOld (java.util.Date birth, int old) {
         LocalDate birthLocal = birth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthLocal,LocalDate.now()).getYears() < old ;
    }*/
    public boolean chekOld(java.util.Date birth, int old) {
        LocalDate birthLocal;

        if (birth instanceof java.sql.Date) {
            // Convert java.sql.Date to LocalDate
            birthLocal = ((java.sql.Date) birth).toLocalDate();
        } else {
            // Fallback for java.util.Date
            birthLocal = birth.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        return Period.between(birthLocal, LocalDate.now()).getYears() <= old;
    }


    public int howManyReadersUnder20YearOld (int year) {
        List<Reader> readers = getAllReaders();
        int count = 0;
        for (Reader reader : readers) {
            if (chekOld(reader.getDateBirth(), year)) {
                count = count + 1;
            }
        }
        return count;
    }
//    String totalQuery = "SELECT COUNT(*) AS totalReaders FROM Readers";
//        String educationLevelQuery = "SELECT educationLevel, COUNT(*) AS count FROM Readers GROUP BY educationLevel";
//        String academicDegreeQuery = "SELECT academicDegree, COUNT(*) AS count FROM Readers WHERE academicDegree IS NOT NULL GROUP BY academicDegree";

    public double getPercentagesByReaders(String eduLevOrDegree) {
        String educationLevelQuery = "SELECT COUNT(*) FROM Readers WHERE educationLevel = ?";
        String academicDegreeQuery = "SELECT COUNT(*) FROM Readers WHERE academicDegree = ?";
        double countReaders = countReadersCurrentlyInLibrary(); // Total number of readers

        if (countReaders == 0) {
            return 0; // Avoid division by zero
        }

        if (eduLevOrDegree.equals("Primary") || eduLevOrDegree.equals("Secondary") || eduLevOrDegree.equals("Higher")) {
            try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(educationLevelQuery)) {

                statement.setString(1, eduLevOrDegree);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        double countLevel = resultSet.getInt(1);
                        return (countLevel / countReaders) * 100;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else { // Handle academic degrees
            try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statementHigher = connection.prepareStatement(educationLevelQuery);
                 PreparedStatement statementDegree = connection.prepareStatement(academicDegreeQuery)) {

                // Count readers with higher education
                statementHigher.setString(1, "Higher");
                double countHigher = 0;
                try (ResultSet resultSetHigher = statementHigher.executeQuery()) {
                    if (resultSetHigher.next()) {
                        countHigher = resultSetHigher.getInt(1);
                    }
                }

                // Count readers with specific academic degree
                statementDegree.setString(1, eduLevOrDegree);
                try (ResultSet resultSetDegree = statementDegree.executeQuery()) {
                    if (resultSetDegree.next() && countHigher > 0) {
                        double countDegree = resultSetDegree.getInt(1);
                        return (countDegree / countHigher) * 100;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }


    // Remove readers from the list who registered in the library more than a year ago and have not re-registered.
    public boolean unReRegisteredReaders () {
        List<Reader> readers = getAllReaders();
        LocalDate now = LocalDate.now();
        for (Reader reader : readers) {
            LocalDate readerRegistered = reader.getDateRegisteration().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (Period.between(readerRegistered,now).getYears() > 1) {
                if (!deleteReader(reader.getId())) {
                    return false;
                }
            }
        }
        return true;
    }


    /*
    * -- Table for Assigned Books to Readers
        CREATE TABLE AssignedBookToReader (
                id SERIAL PRIMARY KEY,
                idBook INT REFERENCES AvailableBooks(id) ON DELETE CASCADE,
                idReader INT REFERENCES Readers(id) ON DELETE CASCADE,
                dateAssigned DATE NOT NULL DEFAULT CURRENT_DATE
            );
    * */
    public List<Book> getBooksAssignedToReader (int idReader) {
        BookDAO bookDAO = new BookDAO();
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM AssignedBookToReader WHERE idReader = " + idReader;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = bookDAO.getBookById(resultSet.getInt("idBook"));
                book.setDateAssigned(resultSet.getDate("dateAssigned"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public int getMonthlyRegistrations() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Readers " +
                "WHERE EXTRACT(MONTH FROM dateRegisteration) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                "AND EXTRACT(YEAR FROM dateRegisteration) = EXTRACT(YEAR FROM CURRENT_DATE)";

        try (Connection connection = DataBaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1); // Get the count from the first column of the result set
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return count;
    }


}
