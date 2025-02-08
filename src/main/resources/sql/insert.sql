INSERT INTO ReadingRooms (number, name, capacity, address) VALUES
                                                               (1, 'Orion Room', 50, '123 Lunar Lane'),
                                                               (2, 'Galaxy Hall', 100, '456 Cosmic Avenue'),
                                                               (3, 'Starlight Nook', 20, '789 Nebula Street'),
                                                               (4, 'Celestial Chamber', 30, '101 Starshine Blvd'),
                                                               (5, 'Asteroid Alcove', 25, '202 Meteor Drive');
INSERT INTO AvailableBooks (title, author, publisher, yearPublication, code) VALUES
                                                                                 ('Cosmos', 'Carl Sagan', 'Random House', 1980, 'B001'),
                                                                                 ('Astrophysics for People in a Hurry', 'Neil deGrasse Tyson', 'W. W. Norton & Company', 2017, 'B002'),
                                                                                 ('A Brief History of Time', 'Stephen Hawking', 'Bantam Books', 1988, 'B003'),
                                                                                 ('The Universe in a Nutshell', 'Stephen Hawking', 'Bantam Books', 2001, 'B004'),
                                                                                 ('Black Holes and Baby Universes', 'Stephen Hawking', 'Bantam Books', 1993, 'B005'),
                                                                                 ('The Elegant Universe', 'Brian Greene', 'Vintage', 1999, 'B006'),
                                                                                 ('Welcome to the Universe', 'Neil deGrasse Tyson', 'Princeton University Press', 2016, 'B007'),
                                                                                 ('Pale Blue Dot', 'Carl Sagan', 'Random House', 1994, 'B008'),
                                                                                 ('Interstellar Exploration', 'Jill Tarter', 'Astronomy Publishing', 2020, 'B009'),
                                                                                 ('Stargazing 101', 'Alice Johnson', 'Starry Night Press', 2023, 'B010');
INSERT INTO Readers (libraryCardNumber, firstName, lastName, passportNumber, dateBirth, address, phoneNumber, educationLevel, academicDegree, idRoom) VALUES
                                                                                                                                                          ('LC001', 'Alice', 'Smith', 'P12345', '1990-05-10', '123 Moon St.', '555-1234', 'Higher', 'PhD', 1),
                                                                                                                                                          ('LC002', 'Bob', 'Jones', 'P67890', '1985-09-22', '456 Star Ave.', '555-5678', 'Secondary', NULL, 2),
                                                                                                                                                          ('LC003', 'Charlie', 'Brown', 'P54321', '2000-01-15', '789 Comet Rd.', '555-9876', 'Higher', 'Bachelor', 3),
                                                                                                                                                          ('LC004', 'Diana', 'Prince', 'P98765', '1995-11-30', '101 Starshine Blvd.', '555-6543', 'Primary', NULL, 4),
                                                                                                                                                          ('LC005', 'Evan', 'Taylor', 'P87654', '1992-04-20', '202 Meteor Dr.', '555-3210', 'Higher', 'Master', 5);
INSERT INTO CopiesBookByRoom (idRoom, idBook, count) VALUES
                                                         (1, 1, 5),
                                                         (1, 2, 3),
                                                         (2, 3, 4),
                                                         (2, 4, 2),
                                                         (3, 5, 6),
                                                         (3, 6, 7),
                                                         (4, 7, 5),
                                                         (4, 8, 4),
                                                         (5, 9, 3),
                                                         (5, 10, 2);
INSERT INTO AssignedBookToReader (idBook, idReader, dateAssigned) VALUES
                                                                      (1, 1, '2024-01-05'),
                                                                      (2, 1, '2024-10-01'),
                                                                      (2, 2, '2024-02-10'),
                                                                      (3, 3, '2024-03-15'),
                                                                      (4, 4, '2024-04-20'),
                                                                      (5, 5, '2024-05-25'),
                                                                      (6, 1, '2024-06-05'),
                                                                      (7, 2, '2024-07-10'),
                                                                      (8, 3, '2024-08-15'),
                                                                      (9, 4, '2024-09-20'),
                                                                      (10, 5, '2024-10-25');
