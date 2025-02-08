package com.library.Servlet.worker.report;

import com.library.Data.BookDAO;
import com.library.Data.ReaderDAO;
import com.library.Data.RoomDAO;
import com.library.Objects.Reader;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/workerSection/monthlyReport")
public class MonthlyReport extends HttpServlet {
    private final ReaderDAO readerDAO = new ReaderDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Readers who borrowed books more than a month ago
        List<Reader> readersBorrowedBooksMoreThanMonthAgo = readerDAO.whichReadersBorrowedBooksMoreThanMounthAgo();
        req.setAttribute("readersBM", readersBorrowedBooksMoreThanMonthAgo);

        // Readers assigned books with no more than two copies in the library
        List<Reader> readersBL2 = readerDAO.whichReadersHaveBeenAssignedBookWhereTheNumberOfCopiesInTheLibraryDoesNotExceed(2);
        req.setAttribute("readersBL2", readersBL2);

        // Reader counts and demographics
        req.setAttribute("numberReaders", readerDAO.countReadersCurrentlyInLibrary());
        req.setAttribute("readersL20", readerDAO.howManyReadersUnder20YearOld(20));
        req.setAttribute("Primary", String.format("%.2f",readerDAO.getPercentagesByReaders("Primary")));
        req.setAttribute("Secondary", String.format("%.2f",readerDAO.getPercentagesByReaders("Secondary")));
        req.setAttribute("Higher", String.format("%.2f",readerDAO.getPercentagesByReaders("Higher")));
        req.setAttribute("Bachelor", String.format("%.2f",readerDAO.getPercentagesByReaders("Bachelor")));
        req.setAttribute("Master", String.format("%.2f",readerDAO.getPercentagesByReaders("Master")));
        req.setAttribute("PhD", String.format("%.2f",readerDAO.getPercentagesByReaders("PhD")));

        // Monthly activity report
        req.setAttribute("countBooks", bookDAO.getAllBooks().size());
        req.setAttribute("countReadersInLibrary", readerDAO.getAllReaders().size());
        req.setAttribute("countReadersRegisterMonthly", readerDAO.getMonthlyRegistrations());

        // Reader counts per room
        List<Room> rooms = roomDAO.getALlRooms();
        List<Integer> countReadersByRooms = new ArrayList<>();
        for (Room room : rooms) {
            countReadersByRooms.add(readerDAO.getAllReadersInRoom(room.getId()).size());
        }
        req.setAttribute("rooms", rooms);
        req.setAttribute("countReadersByRooms", countReadersByRooms);



        // Forward to JSP
        req.getRequestDispatcher("/workerSection/monthlyReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = readerDAO.unReRegisteredReaders();
        req.setAttribute("result", result);
        doGet(req, resp);
    }
}

























/*// Readers who did not borrow books this month
        List<Reader> readers = readerDAO.getAllReaders();
        List<Reader> readersNotBorrowedThisMonth = new ArrayList<>();
        for (Reader reader : readers) {
            if (!readerDAO.isAssignedBook(reader.getId())) {
                readersNotBorrowedThisMonth.add(reader);
            }
        }
        req.setAttribute("readersNotBorrowed", readersNotBorrowedThisMonth);*/



/*

package com.library.Servlet.worker.report;

import com.library.Data.BookDAO;
import com.library.Data.ReaderDAO;
import com.library.Data.RoomDAO;
import com.library.Objects.Reader;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/workerSection/monthlyReport")
public class MonthlyReport extends HttpServlet {
    ReaderDAO readerDAO = new ReaderDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reader> readersBorrowedBooksMoreThanMonthAgo = readerDAO.whichReadersBorrowedBooksMoreThanMounthAgo();
        req.setAttribute("readersBM", readersBorrowedBooksMoreThanMonthAgo);
        List<Reader> readersBL2 = readerDAO.whichReadersHaveBeenAssignedBookWhereTheNumberOfCopiesInTheLibraryDoesNotExceed(2);
        req.setAttribute("readersBL2", readersBL2);
        int numberReaders = readerDAO.countReadersCurrentlyInLibrary();
        req.setAttribute("numberReaders", numberReaders);
        int readersL20 = readerDAO.howManyReadersUnder20YearOld(20);
        req.setAttribute("readersL20", readersL20);
        double prePrim = readerDAO.getPercentagesByReaders("Primary");
        double preSec = readerDAO.getPercentagesByReaders("Secondary");
        double preHig = readerDAO.getPercentagesByReaders("Higher");
        double preBack = readerDAO.getPercentagesByReaders("Bachelor");
        double preMas = readerDAO.getPercentagesByReaders("Master");
        double prePhD = readerDAO.getPercentagesByReaders("PhD");
        req.setAttribute("Primary", prePrim);
        req.setAttribute("Secondary", preSec);
        req.setAttribute("Higher", preHig);
        req.setAttribute("Bachelor", preBack);
        req.setAttribute("Master", preMas);
        req.setAttribute("PhD", prePhD);

        // report monthly library process

        BookDAO bookDAO = new BookDAO();
        RoomDAO roomDAO = new RoomDAO();
        int countBookInLibrary = bookDAO.getAllBooks().size();
        int countReadersRegisterMonthly = readerDAO.getMonthlyRegistrations();
        List<Reader> readers = readerDAO.getAllReaders();
        int countReadersInLibrary = readers.size();
        List<Integer> countReadersByRooms = new ArrayList<>();
        List<Room> rooms = roomDAO.getALlRooms();
        for (Room count : rooms) {
            int c = readerDAO.getAllReadersInRoom(count.getId()).size();
            countReadersByRooms.add(c);
        }
        List<Reader> readerNotBorrowedBookByThisMonth = new ArrayList<>();
        for (Reader reader : readers) {
            if(!readerDAO.isAssignedBook(reader.getId())) {
                readerNotBorrowedBookByThisMonth.add(reader);
            }
        }

        req.setAttribute("readers", readers);
        req.setAttribute("books", readerNotBorrowedBookByThisMonth);
        req.getRequestDispatcher("/workerSection/monthlyReport.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = readerDAO.unReRegisteredReaders();
        req.setAttribute("result", result);

    }
}
*/
