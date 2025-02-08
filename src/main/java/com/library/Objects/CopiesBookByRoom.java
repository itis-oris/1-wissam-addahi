package com.library.Objects;

public class CopiesBookByRoom {

    private int id;
    private int roomId;
    private int bookId;
    private int copies = 0;

    public CopiesBookByRoom() {
    }

    public CopiesBookByRoom(int id, int roomId, int bookId, int copies) {
        this.id = id;
        this.roomId = roomId;
        this.bookId = bookId;
        this.copies = copies;
    }

    public CopiesBookByRoom(int roomId, int bookId, int copies) {
        this.roomId = roomId;
        this.bookId = bookId;
        this.copies = copies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
