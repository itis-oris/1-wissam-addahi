-- Delete all existing data
DELETE FROM ArchevBooks;
DELETE FROM ArchevReaders;
DELETE FROM AssignedBookToReader;
DELETE FROM CopiesBookByRoom;
DELETE FROM Readers;
DELETE FROM AvailableBooks;
DELETE FROM ReadingRooms;

-- Reset sequences for auto-increment IDs
ALTER SEQUENCE readingrooms_id_seq RESTART WITH 1;
ALTER SEQUENCE availablebooks_id_seq RESTART WITH 1;
ALTER SEQUENCE readers_id_seq RESTART WITH 1;
ALTER SEQUENCE copiesbookbyroom_id_seq RESTART WITH 1;
ALTER SEQUENCE assignedbooktoreader_id_seq RESTART WITH 1;
ALTER SEQUENCE archevreaders_id_seq RESTART WITH 1;
ALTER SEQUENCE archevbooks_id_seq RESTART WITH 1;
