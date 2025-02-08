// profile

const editBtn = document.getElementById("editBtn");
const editPOrUN = document.getElementById("editPOrUN");
editBtn.addEventListener("click", () => {
    editPOrUN.classList.remove("hidden");
})
const cancelEdit = document.getElementById("cancelEdit");
cancelEdit.addEventListener("click", () => editPOrUN.classList.add("hidden"))
const logout = document.getElementById("logout");
logout.addEventListener("click" , () => {
    if (confirm("Are you sure you want to log out?")) {
        window.location.href = "/logout"; // Update with your logout route
    }
});

// daily work section

const requestSelect = document.getElementById("request");
const resultDev = document.getElementById("result");
const requestFormDiv = document.getElementById("requestForm");


const  addNewRoomForm = `
    <form action="/workerSection/addNewRoom" method="post">
        <label for="numberR">Room Number: </label>
        <input id="numberR" type="number" min="0" name="number" required>
        <label for="nameR">Room Name: </label>
        <input id="nameR" type="text" name="name" required>
        <label for="capacityR">Capacity: </label>
        <input id="capacityR" type="number" name="capacity" min="0" required>
        <label for="addressR">Address: </label>
        <input id="addressR" type="text" name="address" required>
        <input type="submit" value="Send">
    </form>
`;

const listRoomsForm = `
    <form action="/workerSection/listRooms" method="post">
        <input type="submit" value="Show list">
    </form>
`;

const editRoomForm = `
    <form action="/workerSection/editRoom" method="get">
        <label for="roomNumber">Enter Room Number:</label>
        <input type="number" id="roomNumber" name="roomNumber" required>
        <input type="submit" value="Edit Room">
    </form>
`;

const deleteRoomForm = `
    <form action="/workerSection/deleteRoom" method="post">
        <label for="deleteRoomNumber">Room Number to Delete: </label>
        <input id="deleteRoomNumber" type="number" name="number" required>
        <input type="submit" value="Delete Room">
    </form>
`;

const addNewBookForm = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="addNewBook">
        <label>Title:</label>
        <input name="title" type="text" required>
        <label>Author:</label>
        <input name="author" type="text" required>
        <label>Publisher:</label>
        <input name="publisher" type="text" required>
        <label>Year publication:</label>
        <input name="yearPublication" type="number" required>
        <label>Code:</label>
        <input name="code" type="text" required>
        <input type="submit" value="Add">
    </form>
`;

const deleteBookForm = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="deleteBook">
        <label>Code Book:</label>
        <input type="text" name="code" required>
        <label>Reason delete book from library?</label>
        <input type="text" name="reason" required>
        <input type="submit" value="Delete">
    </form>
`;

const listBooksForm = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="listBooks">
        <input type="submit" value="Get list books">
    </form>
`;

const editBookForm = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="editBook">
        <label>Enter code book:</label>
        <input type="text" name="code" required>
        <input type="submit" value="Edit book">
    </form>    
`;

const reclassificationBooks = `
    <form action="/workerSection/reClassBooks" method="get">
        <input name="action" value="reClass" type="hidden">
        <input type="submit" value="Re-Classification Books">
    </form>
`;

const listBookByRoom = `
    <form action="/workerSection/controlBook" method="post">
    <input type="hidden" name="type" value="listBookByRoom">
    <label>Enter Number Room:</label>
    <input type="number" name="number">
    <input type="submit" value="Get List books in Room">
    </form>
`;

const addCopiesBookToRoom = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="addCopiesBookToRoom">
        <label>Number of Room</label>
        <input type="number" name="number">
        <label>Code of Book</label>
        <input type="text" name="code">
        <label>Count adding copies</label>
        <input min="0" type="number" name="count">
        <input type="submit" value="Add copies">
    </form>
`;

const removeCopiesFromRoom = `
    <form action="/workerSection/controlBook" method="post">
        <input type="hidden" name="type" value="removeCopiesFromRoom">
        <label>Number of Room</label>
        <input type="number" name="number">
        <label>Code of Book</label>
        <input type="text" name="code">
        <label>Count deleting copies</label>
        <input min="0" type="number" name="count">
        <label>Reason ?</label>
        <input type="text" name="reason" required>
        <input type="submit" value="Add copies">
    </form>    
`;

const registerReader = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="registerReader">
        <label>Library card:</label>
        <input type="text" name="libraryCard" required>
        <label>First Name:</label>
        <input type="text" name="firstName">
        <label>Last Name:</label>
        <input type="text" name="lastName">
        <label>Passport Number:</label>
        <input type="text" name="passport">
        <label>Date Birth:</label>
        <input type="date" name="birthDate" required>
        <label>Address</label>
        <input type="text" name="address" required>
        <label>Phone Number</label>
        <input type="tel" name="tel" required>
        <label>Education level:</label>
        <select name="eduLevel">
        <option value="Primary">Primary</option>
        <option value="Secondary">Secondary</option>
        <option value="Higher">Higher</option>
        </select>
        <label>Academic Degree : enter it like Bachelor,Master or PhD</label>
        <input type="text" name="academic" placeholder="for who have Higher education">
        <label>Number Room:</label>
        <input type="number" name="room">
        <input type="submit" value="Register">
    </form>
`;

const listReaders = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="listReaders">
        <input type="submit" value="Get list readers in library">
    </form>
`;

const readersInRoom = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="readersInRoom">
        <label>Enter number room</label>
        <input type="number" name="numRoom">
        <input type="submit" value="list readers in room">
    </form>
`;

const editReader = `
    <form action="/workerSection/controlReader" method="get">
    <label>Card library:</label>
    <input type="text" name="cardLibrary">
    <input type="submit" value="Edit">
    </form>
`;

const assignedBook = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="assignedBook">
        <label>Library card:</label>
        <input type="text" name="libCard">
        <label>Code book</label>
        <input type="text" name="codeBook">
        <input type="submit" value="Assign">
    </form>
`;

const returnBook = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="returnBook">
        <label>Library card:</label>
        <input type="text" name="libCard">
        <label>Code book</label>
        <input type="text" name="codeBook">
        <input type="submit" value="Return">
    </form>
`;

const reRegisterReader = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="reRegisterReader">
        <label>Old library card:</label>
        <input type="text" name="oldLibCard" required>
        <label>New library card:</label>
        <input type="text" name="newLibCard" required>
        <input type="submit" value="reRegister">
    </form>
`;

const deleteReader = `
    <form action="/workerSection/controlReader" method="post">
        <input type="hidden" name="type" value="deleteReader">
        <label>Library card:</label>
        <input type="text" name="libCard">
        <!--<label>Why!</label>
        <input type="text" name="reason">-->
        <input type="submit" value="Delete">
    </form>
`;

const findBook = `
    <form action="/workerSection/controlReader" method="post">
    <input name="type" type="hidden" value="findBook">
    <label>Write title book</label>
    <input type="text" name="title" required>
    <input type="submit" value="Search">
    </form>
`;

const listAssignedBooksToReader = `
    <form action="/workerSection/controlReader" method="post">
        <input name="type" type="hidden" value="listAssignedBooksToReader">
        <label>Library Card:</label>
        <input type="text" name="card">
        <input type="submit" value="Search">
    </form>
`;


requestSelect.addEventListener("change", () => {
    const value = requestSelect.value;
    switch (value) {
        case "addNewRoom":
            requestFormDiv.innerHTML = addNewRoomForm;
            break;
        case "listRooms":
            requestFormDiv.innerHTML = listRoomsForm;
            break;
        case "editRoom":
            requestFormDiv.innerHTML = editRoomForm;
            break;
        case "deleteRoom":
            requestFormDiv.innerHTML = deleteRoomForm;
            break;
        case "addNewBook":
            requestFormDiv.innerHTML = addNewBookForm;
            break;
        case "deleteBook":
            requestFormDiv.innerHTML = deleteBookForm;
            break;
        case "listBooks":
            requestFormDiv.innerHTML = listBooksForm;
            break;
        case "editBook":
            requestFormDiv.innerHTML = editBookForm;
            break;
        case "reclassificationBooks":
            requestFormDiv.innerHTML = reclassificationBooks;
            break;
        case "listBookByRoom":
            requestFormDiv.innerHTML = listBookByRoom;
            break;
        case "removeCopiesFromRoom":
            requestFormDiv.innerHTML = removeCopiesFromRoom;
            break;
        case "addCopiesBookToRoom":
            requestFormDiv.innerHTML = addCopiesBookToRoom;
            break;
        case "registerReader":
            requestFormDiv.innerHTML = registerReader;
            break;
        case "listReaders":
            requestFormDiv.innerHTML = listReaders;
            break;
        case "readersInRoom":
            requestFormDiv.innerHTML = readersInRoom;
            break;
        case "editReader":
            requestFormDiv.innerHTML = editReader;
            break;
        case "assignedBook":
            requestFormDiv.innerHTML = assignedBook;
            break;
        case "returnBook":
            requestFormDiv.innerHTML = returnBook;
            break;
        case "reRegisterReader":
            requestFormDiv.innerHTML = reRegisterReader;
            break;
        case "deleteReader":
            requestFormDiv.innerHTML = deleteReader;
            break;
        case "findBook":
            requestFormDiv.innerHTML = findBook;
            break;
        case "listAssignedBooksToReader":
            requestFormDiv.innerHTML = listAssignedBooksToReader;
            break;
    }
});
