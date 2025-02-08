package com.library.Objects;

import java.util.Date;

public class Book  {

    int id;
    String title;
    String author;
    String publisher;
    int yearPublication;
    String code;
    int roomNumber;
    int countCopies;
    Date dateAssigned;

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Book(String title, String author, String publisher, int yearPublication, String code, Date dateAssigned) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.code = code;
        this.dateAssigned = dateAssigned;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCountCopies() {
        return countCopies;
    }

    public void setCountCopies(int countCopies) {
        this.countCopies = countCopies;
    }

    public Book() {
    }

    public Book(int id, String title, String author, String publisher, int yearPublication, String code) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.code = code;
    }

    public Book(String title, String author, String publisher, int yearPublication, String code) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
