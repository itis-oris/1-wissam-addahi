package com.library.Objects;

import java.util.Date;

public class ArchevBooks {

    /*
    CREATE TABLE ArchevBooks (
        id SERIAL PRIMARY KEY,
        bookTitle VARCHAR(255),
        idRoom INT,
        countDeleted INT NOT NULL,
        reason TEXT NOT NULL,
        dateDeleted DATE NOT NULL DEFAULT CURRENT_DATE
    );
    * */
    private int id;
    private String bookTitle;
    private int idRoom;
    private int countDeleted;
    private String reason;
    private Date dateDeleted;

    public ArchevBooks(int id, String bookTitle, int idRoom, int countDeleted, String reason, Date dateDeleted) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.idRoom = idRoom;
        this.countDeleted = countDeleted;
        this.reason = reason;
        this.dateDeleted = dateDeleted;
    }

    public ArchevBooks(String bookTitle, int idRoom, int countDeleted, String reason, Date dateDeleted) {
        this.bookTitle = bookTitle;
        this.idRoom = idRoom;
        this.countDeleted = countDeleted;
        this.reason = reason;
        this.dateDeleted = dateDeleted;
    }

    public ArchevBooks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getCountDeleted() {
        return countDeleted;
    }

    public void setCountDeleted(int countDeleted) {
        this.countDeleted = countDeleted;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }
}
