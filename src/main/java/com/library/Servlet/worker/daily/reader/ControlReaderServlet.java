package com.library.Servlet.worker.daily.reader;

import com.library.Data.BookDAO;
import com.library.Data.ReaderDAO;
import com.library.Data.RoomDAO;
import com.library.Objects.Book;
import com.library.Objects.Reader;
import com.library.Objects.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet("/workerSection/controlReader")
public class ControlReaderServlet extends HttpServlet {

    ReaderDAO readerDAO = new ReaderDAO();
    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = readerDAO.getReaderByCard(req.getParameter("cardLibrary"));
        req.setAttribute("reader", reader);
        req.getRequestDispatcher("/workerSection/editReader.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "registerReader" -> {
                // Convert the string input to a LocalDate, then to a Date object
                String birthDateString = req.getParameter("birthDate");
                LocalDate birthDate = LocalDate.parse(birthDateString); // Parses a string like "2002-06-15"
                Date dateOfBirth = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                String educationLevel = req.getParameter("eduLevel");
                String academicDegree = req.getParameter("academic");
                if (academicDegree == null || academicDegree.trim().isEmpty() || !educationLevel.equals("Higher")) {
                    academicDegree = null;
                }

                int roomId = roomDAO.getRoomByNumber(Integer.parseInt(req.getParameter("room"))).getId();

                Reader reader = new Reader(
                        req.getParameter("libraryCard"),
                        req.getParameter("firstName"),
                        req.getParameter("lastName"),
                        req.getParameter("passport"),
                        dateOfBirth,
                        req.getParameter("address"),
                        req.getParameter("tel"),
                        educationLevel,
                        academicDegree,
                        roomId
                );
                boolean res = readerDAO.addReader(reader);
                req.getSession(false).setAttribute("result", "registerReader");
                req.getSession(false).setAttribute("resultMsg", res ? "Success" : "Failed");
            }
            case "listReaders" -> {
                List<Reader> readers = readerDAO.getAllReaders();
                req.getSession(false).setAttribute("result", "listReaders");
                req.getSession(false).setAttribute("readers", readers );
            }
            case "readersInRoom" -> {
                Room room = roomDAO.getRoomByNumber(Integer.parseInt(req.getParameter("numRoom")));
                List<Reader> readers = readerDAO.getAllReadersInRoom(room.getId());
                req.getSession(false).setAttribute("result", "readersInRoom");
                req.getSession(false).setAttribute("readers", readers );
                req.getSession(false).setAttribute("room", room);
            }
            case "editReader" -> {
                // Convert the string input to a LocalDate, then to a Date object
                String birthDateString = req.getParameter("birthDate");
                LocalDate birthDate = LocalDate.parse(birthDateString); // Parses a string like "2002-06-15"
                Date dateOfBirth = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                int roomId = roomDAO.getRoomByNumber(Integer.parseInt(req.getParameter("room"))).getId();

                String educationLevel = req.getParameter("eduLevel");
                String academicDegree = req.getParameter("academic");
                if (academicDegree == null || academicDegree.trim().isEmpty() || !educationLevel.equals("Higher")) {
                    academicDegree = null;
                }

                Reader reader = new Reader(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("libraryCard"),
                        req.getParameter("firstName"),
                        req.getParameter("lastName"),
                        req.getParameter("passport"),
                        dateOfBirth,
                        req.getParameter("address"),
                        req.getParameter("tel"),
                        educationLevel,
                        academicDegree,
                        roomId
                );
                boolean rs = readerDAO.updateReader(reader);
                req.getSession(false).setAttribute("result", "editReader");
                req.getSession(false).setAttribute("resultMsg", rs ? "Success" : "Failed");
            }
            case "assignedBook" -> {
                boolean rs = roomDAO.assignedBookToReader(req.getParameter("libCard"),req.getParameter("codeBook"));
                req.getSession(false).setAttribute("result", "assignedBook");
                req.getSession(false).setAttribute("resultMsg", rs ? "Success" : "Failed");
            }
            case "returnBook" -> {
                boolean rs = roomDAO.returnBookFromReader(req.getParameter("libCard"),req.getParameter("codeBook"));
                req.getSession(false).setAttribute("result", "returnBook");
                req.getSession(false).setAttribute("resultMsg", rs ? "Success" : "Failed");
            }
            case "reRegisterReader" -> {
                boolean rs = readerDAO.reRegisterReader(req.getParameter("oldLibCard"),req.getParameter("newLibCard"));
                req.getSession(false).setAttribute("result", "reRegisterReader");
                req.getSession(false).setAttribute("resultMsg", rs ? "Success" : "Failed");
            }
            case "deleteReader" -> {
                Reader reader = readerDAO.getReaderByCard(req.getParameter("libCard"));
                boolean rs = readerDAO.deleteReader(reader.getId());
                req.getSession(false).setAttribute("result", "deleteReader");
                req.getSession(false).setAttribute("resultMsg", rs ? "Success" : "Failed");
            }
            case "findBook" -> {
                // this my logic but I will use gpt
                String title = req.getParameter("title");

                BookDAO bookDAO = new BookDAO();
                List<Book> books = bookDAO.findBooksByTitle(title);

                req.getSession(false).setAttribute("result","findBook");
                req.getSession(false).setAttribute("books", books);

            }
            case "listAssignedBooksToReader" -> {
                Reader reader = readerDAO.getReaderByCard(req.getParameter("card"));
                List<Book> books = readerDAO.getBooksAssignedToReader(reader.getId());
                req.getSession(false).setAttribute("result","listAssignedBooksToReader");
                req.getSession(false).setAttribute("books", books);
            }
        }
        resp.sendRedirect("/workerSection.jsp");
    }
}
