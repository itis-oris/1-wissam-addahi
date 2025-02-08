package com.library.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class Librarian {
    /*
    CREATE TABLE Librarian (
        id SERIAL PRIMARY KEY,
        firstName VARCHAR(255) NOT NULL,
        lastName VARCHAR(255) NOT NULL,
        userName VARCHAR(50) NOT NULL UNIQUE,
        hashPassword VARCHAR(255) NOT NULL
    );
    * */
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String hashPassword;

    public Librarian(int id, String firstName, String lastName, String userName, String hashPassword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.hashPassword = BCrypt.hashpw(hashPassword, BCrypt.gensalt());;
    }

    public Librarian(String firstName, String lastName, String userName, String hashPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.hashPassword = BCrypt.hashpw(hashPassword, BCrypt.gensalt());;
    }

    public Librarian() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = BCrypt.hashpw(hashPassword, BCrypt.gensalt());;
    }
}
