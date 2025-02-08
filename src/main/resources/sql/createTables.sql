-- Table for Reading Rooms
CREATE TABLE ReadingRooms (
                              id SERIAL PRIMARY KEY,
                              number INT NOT NULL UNIQUE,
                              name VARCHAR(255) NOT NULL,
                              capacity INT NOT NULL,
                              address VARCHAR(255) NOT NULL
);

-- Table for Available Books
CREATE TABLE AvailableBooks (
                                id SERIAL PRIMARY KEY,
                                title VARCHAR(255) NOT NULL,
                                author VARCHAR(255) NOT NULL,
                                publisher VARCHAR(255),
                                yearPublication INT,
                                code VARCHAR(50) NOT NULL UNIQUE
);

-- Table for Readers
CREATE TABLE Readers (
                         id SERIAL PRIMARY KEY,
                         libraryCardNumber VARCHAR(50) NOT NULL UNIQUE,
                         firstName VARCHAR(255) NOT NULL,
                         lastName VARCHAR(255) NOT NULL,
                         passportNumber VARCHAR(50) NOT NULL UNIQUE,
                         dateBirth DATE NOT NULL,
                         address VARCHAR(255),
                         phoneNumber VARCHAR(20),
                         educationLevel VARCHAR(50) NOT NULL CHECK (educationLevel IN ('Primary', 'Secondary', 'Higher')),
                         academicDegree VARCHAR(50) CHECK (
                             academicDegree IS NULL OR educationLevel = 'Higher'
                             ), -- Optional, but only valid if educationLevel is 'Higher'
                         idRoom INT REFERENCES ReadingRooms(id),
                         dateRegisteration DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Table for Copies of Books by Room
CREATE TABLE CopiesBookByRoom (
                                  id SERIAL PRIMARY KEY,
                                  idRoom INT REFERENCES ReadingRooms(id) ON DELETE CASCADE,
                                  idBook INT REFERENCES AvailableBooks(id) ON DELETE CASCADE,
                                  count INT NOT NULL DEFAULT 0
);

-- Table for Assigned Books to Readers
CREATE TABLE AssignedBookToReader (
                                      id SERIAL PRIMARY KEY,
                                      idBook INT REFERENCES AvailableBooks(id) ON DELETE CASCADE,
                                      idReader INT REFERENCES Readers(id) ON DELETE CASCADE,
                                      dateAssigned DATE NOT NULL DEFAULT CURRENT_DATE
);
-- Archive of Readers
CREATE TABLE ArchevReaders (
                               id SERIAL PRIMARY KEY,
                               libraryCard VARCHAR(50),
                               firstName VARCHAR(255),
                               lastName VARCHAR(255),
                               dateOfBirth DATE,
                               idRoom INT,
                               dateDelete DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Archive of Books
CREATE TABLE ArchevBooks (
                             id SERIAL PRIMARY KEY,
                             bookTitle VARCHAR(255),
                             idRoom INT,
                             countDeleted INT NOT NULL,
                             reason TEXT NOT NULL,
                             dateDeleted DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Table for Librarians
CREATE TABLE Librarian (
                           id SERIAL PRIMARY KEY,
                           firstName VARCHAR(255) NOT NULL,
                           lastName VARCHAR(255) NOT NULL,
                           userName VARCHAR(50) NOT NULL UNIQUE,
                           hashPassword VARCHAR(255) NOT NULL
);

INSERT INTO Librarian (firstName, lastName, userName, hashPassword)
VALUES ('Wissam', 'Addahi', 'admin', '$2a$10$qvkX90xUdtfcOmT5UwEiM.4LO39KWUl2KyXw1/ck201Ruqa1UUpPa');

