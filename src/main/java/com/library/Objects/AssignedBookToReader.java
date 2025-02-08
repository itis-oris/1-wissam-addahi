package com.library.Objects;

import java.util.Date;

public class AssignedBookToReader {

    private int id;
    private int idBook;
    private int idReader;
    private Date dateAssigned;

    public AssignedBookToReader() {
    }

    public AssignedBookToReader(int id, int idBook, int idReader, Date dateAssigned) {
        this.id = id;
        this.idBook = idBook;
        this.idReader = idReader;
        this.dateAssigned = dateAssigned;
    }

    public AssignedBookToReader(int idBook, int idReader, Date dateAssigned) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.dateAssigned = dateAssigned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
}
