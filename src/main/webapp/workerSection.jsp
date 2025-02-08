
<%@ page import="java.util.List" %>
<%@ page import="com.library.Objects.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.library.Objects.Book" %>
<%@ page import="com.library.Data.ReaderDAO" %>
<%@ page import="com.library.Data.RoomDAO" %>
<%@ page import="com.library.Data.LibrarianDAO" %>
<%@ page import="com.library.Objects.Librarian" %>
<%@ page import="com.library.Objects.Reader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workerSection</title>
    <link rel="stylesheet" href="style/workerSection.css">

</head>
<body>
<%
    int workerId = (Integer) request.getSession(false).getAttribute("workerId");
    LibrarianDAO librarianDAO = new LibrarianDAO();
    Librarian librarian = librarianDAO.getLibrarianById(workerId);
    String firstName = librarian.getFirstName();
    String lastName = librarian.getLastName();
    String userName = librarian.getUserName();
%>


<div class="header">
    <div class="library-name">Space Library</div>
    <div class="nav-links">
        <%--<a href="/">Home</a>
        <a href="/">About Us</a>
        <a href="/">Services</a>
        <a href="/">Contact</a>--%>
        <a href="/workerSection/monthlyReport" target="_blank">Report</a>
        <!-- Profile Section -->
        <div class="profile">
            <img src="media/profile.jpg" alt="Profile Photo">
            <div class="control">
                <h2>Hi, <%= firstName %> <%= lastName %></h2>
                <p>@<%= userName %></p>
                <input id="editBtn" type="button" value="Edit">
                <div id="editPOrUN" class="editPasswordOrUserName hidden">
                    <form action="/workerSection/editUserLibrarian" method="post">
                        <input type="hidden" value="<%=workerId%>" name="workerId">
                        <label for="userName">Username:</label>
                        <input id="userName" name="userName" type="text" value="<%= userName %>" required>
                        <label for="password">New Password:</label>
                        <input id="password" name="password" type="password" required>
                        <input type="submit" value="Save">
                        <input id="cancelEdit" type="button" value="Cancel">
                    </form>
                </div>
                <input id="logout" type="button" value="Logout">
            </div>
        </div>
    </div>
</div>


<main class="main">

  <section>
      <h2>Daily Work Section</h2>
      <div class="request">
          <select id="request" name="request">
              <option>Chose Operation</option>
              <optgroup label="Work With Rooms">
                  <option value="addNewRoom">Add new Room</option>
                  <option value="listRooms">List Rooms</option>
                  <option value="editRoom">Edit Room</option>
                  <option value="deleteRoom">Delete Room</option>
              </optgroup>
              <optgroup label="Work With Books">
                  <option value="addNewBook">Add new Book</option>
                  <option value="listBooks">List Books in Library</option>
                  <option value="reclassificationBooks">Reclassification Books</option>
                  <option value="editBook">Edit Book</option>
                  <option value="deleteBook">Delete Book from Library</option>
              </optgroup>
              <optgroup label="Work With Copies Books in Rooms">
                  <option value="listBookByRoom">List Books By Room</option>
                  <option value="addCopiesBookToRoom">Add new copies book to room</option>
                  <option value="removeCopiesFromRoom">Delete copies from Room</option>
              </optgroup>
              <optgroup label="Work With Reader">
                  <option value="registerReader">Register new Reader</option>
                  <option value="listReaders">List Readers in Library</option>
                  <option value="readersInRoom">List Readers by Room</option>
                  <option value="editReader">Edit information Reader</option>
                  <option value="findBook">Find book by title</option>
                  <option value="assignedBook">Assigned book to Reader</option>
                  <option value="listAssignedBooksToReader">list books borrowed by reader</option>
                  <option value="returnBook">Return a borrowed book from reader</option>
                  <option value="reRegisterReader">re-Register a reader</option>
                  <option value="deleteReader">Delete Reader</option>
              </optgroup>

          </select>
      </div>


      <div id="requestForm" class="response">

      </div>

      <div id="result" class="result">
          <%
              String result = null;
              try {
                  result = (String) request.getSession(false).getAttribute("result");
              } finally {
                  if (result != null) {
                      if (result.equals("addNewRoom")) {
                          String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                        request.getSession(false).removeAttribute("resultMsg");
                    } else if (result.equals("listRooms")) {
              List<Room> rooms = (ArrayList<Room>) request.getSession(false).getAttribute("rooms");
          %>
          <table>

              <tr>
                  <th>Room Number</th>
                  <th>Room Name</th>
                  <th>Capacity</th>
                  <th>Address</th>
              </tr>
          <%
              for (Room room : rooms) {

          %>
                  <tr>
                      <td><%=room.getNumber()%></td>
                      <td><%=room.getName()%></td>
                      <td><%=room.getCapacity()%></td>
                      <td><%=room.getAddress()%></td>
                  </tr>
          <%
                          }
              %>
          </table>
          <%

                        request.getSession(false).removeAttribute("rooms");
                    } else if (result.equals("editRoom")) {
                          String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%

                } else if (result.equals("deleteRoom")) {
                  String msg = (String) request.getSession(false).getAttribute("resultMsg");

          %>
          <p><%=msg%></p>
          <%
                    request.getSession(false).removeAttribute("resultMsg");
                          } else if (result.equals("addNewBook")) {
String msg= (String) request.getSession(false).getAttribute("resultMsg");
          %>
<p><%=msg%></p>
          <%
                    request.getSession(false).removeAttribute("resultMsg");
                } else if (result.equals("deleteBook")) {
                        String msg= (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                      } else if (result.equals("listBooks")) {
                          List<Book> books = (ArrayList<Book>) request.getSession(false).getAttribute("listBooks");
                          %>
          <table>
              <tr>
                  <th>Title</th>
                  <th>Author</th>
                  <th>Publisher</th>
                  <th>Year publication</th>
                  <th>Code</th>
                  <th>Count</th>
              </tr>
          <%
              ReaderDAO readerDAO = new ReaderDAO();
                          for (Book book : books) {
          %>
<tr>
    <td><%=book.getTitle()%></td>
    <td><%=book.getAuthor()%></td>
    <td><%=book.getPublisher()%></td>
    <td><%=book.getYearPublication()%></td>
    <td><%=book.getCode()%></td>
    <td><%=readerDAO.countBookInLibrary(book.getCode())%></td>
</tr>
          <%
                          }
                          %>
      </table>
          <%
                      request.getSession(false).removeAttribute("listBooks");
                  } else if (result.equals("editBook")) {
                          String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                              request.getSession(false).removeAttribute("resultMsg");
                  } else if (result.equals("reClassBooks")) {
                          String msg = (String) request.getSession(false).getAttribute("resultMsg");
                          %>
          <p><%=msg%></p>
          <%
                    request.getSession(false).removeAttribute("resultMsg");
                  } else if (result.equals("listBookByRoom")) {
                          List<Book> books = (List<Book>) request.getSession(false).getAttribute("listBooksInRoom");
                          %>
          <table>
              <tr>
                  <th>Title</th>
                  <th>Author</th>
                  <th>Publisher</th>
                  <th>Year publication</th>
                  <th>Code</th>
                  <th>Count</th>
              </tr>
          <%
              RoomDAO roomDAO = new RoomDAO();
              int idRoom = (Integer) request.getSession(false).getAttribute("idRoom");
                          for (Book book : books) {

                          %>
              <tr>
              <td><%=book.getTitle()%></td>
              <td><%=book.getAuthor()%></td>
              <td><%=book.getPublisher()%></td>
              <td><%=book.getYearPublication()%></td>
              <td><%=book.getCode()%></td>
              <td><%=roomDAO.getCountBookByRoom(idRoom, book.getId())%></td>
              </tr>
          <%
                          }
                          %>

          </table>
          <%
                          request.getSession(false).removeAttribute("idRoom");
                          request.getSession(false).removeAttribute("listBooksInRoom");
                  } else if (result.equals("addCopiesBookToRoom")) {
                               String msg = (String) request.getSession(false).getAttribute("resultMsg");
                          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                              } else if (result.equals("removeCopiesFromRoom")) {
                                  String msg = (String) request.getSession(false).getAttribute("resultMsg");
                          %>
          <p><%=msg%></p>

          <%
                          request.getSession(false).removeAttribute("resultMsg");
                          } else if (result.equals("registerReader")) {
                              String msg = (String) request.getSession(false).getAttribute("resultMsg");

                          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");

                          } else if (result.equals("listReaders")) {
                             List<Reader> readers = (List<Reader>) request.getSession(false).getAttribute("readers");

          %>
          <table>
              <tr>
                  <th>Library Card</th>
                  <th>Name</th>
                  <th>Family</th>
                  <th>Passport №</th>
                  <th>Date birth</th>
                  <th>Address</th>
                  <th>Tel number</th>
                  <th>Education level</th>
                  <th>Academic degree</th>
                  <th>Room number</th>
                  <th>Date Registeration</th>
                  <th>Is borrowed book?</th>
              </tr>
              <%
                  ReaderDAO readerDAO = new ReaderDAO();
                  RoomDAO roomDAO = new RoomDAO();
                  for (Reader reader : readers) {
                      Room numRoom = roomDAO.getRoomById(reader.getIdRoom());
              %>
              <tr>
                  <td><%=reader.getLibraryCardNumber()%></td>
                  <td><%=reader.getFirstName()%></td>
                  <td><%=reader.getLastName()%></td>
                  <td><%=reader.getPassportNumber()%></td>
                  <td><%=reader.getDateBirth()%></td>
                  <td><%=reader.getAddress()%></td>
                  <td><%=reader.getPhoneNumber()%></td>
                  <td><%=reader.getEducationLevel()%></td>
                  <td><%=reader.getAcademicDegree()%></td>
                  <td><%= (numRoom != null ? numRoom.getNumber() : "No Room Assigned") %></td>
                  <td><%=reader.getDateRegisteration()%></td>
                  <td><%=readerDAO.isAssignedBook(reader.getId()) ? "Yes" : "No"%></td>
              </tr>
              <%
                  }
              %>
          </table>
          <%
                          request.getSession(false).removeAttribute("readers");

                      } else if (result.equals("readersInRoom")) {
                             List<Reader> readers = (List<Reader>) request.getSession(false).getAttribute("readers");
                             Room room = (Room) request.getSession(false).getAttribute("room");
                          %>
          <p><%="In ROOM #" + room.getNumber() + ", " + room.getName() + ", " + room.getAddress() + ", with capacity: " + room.getCapacity() + "We have next readers :"%></p>
          <table>
              <tr>
                  <th>Library Card</th>
                  <th>Name</th>
                  <th>Family</th>
                  <th>Passport №</th>
                  <th>Date birth</th>
                  <th>Address</th>
                  <th>Tel number</th>
                  <th>Education level</th>
                  <th>Academic degree</th>
                  <%--<th>Room number</th>--%>
                  <th>Date Registeration</th>
                  <th>Is borrowed book?</th>
              </tr>
              <%
                  ReaderDAO readerDAO = new ReaderDAO();
                  for (Reader reader : readers) {
                      %>
              <tr>
                  <td><%=reader.getLibraryCardNumber()%></td>
                  <td><%=reader.getFirstName()%></td>
                  <td><%=reader.getLastName()%></td>
                  <td><%=reader.getPassportNumber()%></td>
                  <td><%=reader.getDateBirth()%></td>
                  <td><%=reader.getAddress()%></td>
                  <td><%=reader.getPhoneNumber()%></td>
                  <td><%=reader.getEducationLevel()%></td>
                  <td><%=reader.getAcademicDegree()%></td>
                  <td><%=reader.getDateRegisteration()%></td>
                  <td><%=readerDAO.isAssignedBook(reader.getId()) ? "Yes" : "No"%></td>
              </tr>
              <%
                  }
              %>
          </table>
          <%
                          request.getSession(false).removeAttribute("readers");
                          request.getSession(false).removeAttribute("room");
                          } else if (result.equals("editReader")) {
                             String msg = (String) request.getSession(false).getAttribute("resultMsg");
                              %>
          <p><%=msg%></p>
          <%
                              request.getSession(false).removeAttribute("resultMsg");
                          } else if (result.equals("assignedBook")) {
              String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                      } else if (result.equals("returnBook")) {
                              String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                      } else if (result.equals("reRegisterReader")) {
              String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                      } else if (result.equals("deleteReader")) {
                              String msg = (String) request.getSession(false).getAttribute("resultMsg");
          %>
          <p><%=msg%></p>
          <%
                          request.getSession(false).removeAttribute("resultMsg");
                   } else if (result.equals("findBook")) {
                              List<Book> books = (List<Book>) request.getSession(false).getAttribute("books");
          %>
          <table>
              <tr>
                  <th>Title</th>
                  <th>Author</th>
                  <th>Publisher</th>
                  <th>Year publication</th>
                  <th>Code</th>
                  <th>Count</th>
              </tr>
              <%
                  ReaderDAO readerDAO = new ReaderDAO();
                  for (Book book : books) {
              %>
              <tr>
                  <td><%=book.getTitle()%></td>
                  <td><%=book.getAuthor()%></td>
                  <td><%=book.getPublisher()%></td>
                  <td><%=book.getYearPublication()%></td>
                  <td><%=book.getCode()%></td>
                  <td><%=readerDAO.countBookInLibrary(book.getCode())%></td>
              </tr>
              <%
                  }
              %>
          </table>
          <%
                          request.getSession(false).removeAttribute("books");

                          } else if (result.equals("listAssignedBooksToReader")) {
                List<Book> books = (List<Book>) request.getSession(false).getAttribute("books");
                          %>
          <table>
              <tr>
                  <th>Title</th>
                  <th>Author</th>
                  <th>Publisher</th>
                  <th>Year publication</th>
                  <th>Code</th>
                  <th>Date Assigned</th>
              </tr>
              <%
                  for (Book book : books) {
              %>
              <tr>
                  <td><%=book.getTitle()%></td>
                  <td><%=book.getAuthor()%></td>
                  <td><%=book.getPublisher()%></td>
                  <td><%=book.getYearPublication()%></td>
                  <td><%=book.getCode()%></td>
                  <td><%=book.getDateAssigned()%></td>
              </tr>
              <%
                  }
              %>
          </table>
          <%
                              request.getSession(false).removeAttribute("books");
                          }


                      request.getSession(false).removeAttribute("result");
                  }

              }
          %>
      </div>


  </section>


<%--<ul>
    <li><a href="/workerSection/controlRoom?action=newRoom">ADD NEW ROOM</a></li>
    <li><a href="/workerSection/controlRoom?action=listRooms">List Rooms</a></li>
</ul>--%>

</main>


<script src="script/workerSection.js"></script>
</body>
</html>

