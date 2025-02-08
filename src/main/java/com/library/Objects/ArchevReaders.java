package com.library.Objects;

import java.util.Date;

public class ArchevReaders {

    /*
    CREATE TABLE ArchevReaders (
        id SERIAL PRIMARY KEY,
        libraryCard VARCHAR(50),
        firstName VARCHAR(255),
        lastName VARCHAR(255),
        dateOfBirth DATE,
        idRoom INT,
        dateDelete DATE NOT NULL DEFAULT CURRENT_DATE
    );
    * */
    private int id;
    private String libraryCard;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int idRoom;
    private Date dateDelete;

    public ArchevReaders(int id, String libraryCard, String firstName, String lastName, Date dateOfBirth, int idRoom, Date dateDelete) {
        this.id = id;
        this.libraryCard = libraryCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.idRoom = idRoom;
        this.dateDelete = dateDelete;
    }

    public ArchevReaders(String libraryCard, String firstName, String lastName, Date dateOfBirth, int idRoom, Date dateDelete) {
        this.libraryCard = libraryCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.idRoom = idRoom;
        this.dateDelete = dateDelete;
    }

    public ArchevReaders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(String libraryCard) {
        this.libraryCard = libraryCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }
}
