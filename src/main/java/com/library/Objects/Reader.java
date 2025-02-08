package com.library.Objects;


import java.util.Date;

public class Reader {

    private int id;
    private String libraryCardNumber;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private Date dateBirth;
    private String address;
    private String phoneNumber;
    private String educationLevel;
    private String academicDegree;
    private int idRoom;
    private Date dateRegisteration;

    public Reader(int id, String libraryCardNumber, String firstName, String lastName, String passportNumber, Date dateBirth, String address, String phoneNumber, String educationLevel, String academicDegree, int idRoom, Date dateRegisteration) {
        this.id = id;
        this.libraryCardNumber = libraryCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.dateBirth = dateBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.educationLevel = educationLevel;
        this.academicDegree = academicDegree;
        this.idRoom = idRoom;
        this.dateRegisteration = dateRegisteration;
    }

    public Reader(int id, String libraryCardNumber, String firstName, String lastName, String passportNumber, Date dateBirth, String address, String phoneNumber, String educationLevel, String academicDegree, int idRoom) {
        this.id = id;
        this.libraryCardNumber = libraryCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.dateBirth = dateBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.educationLevel = educationLevel;
        this.academicDegree = academicDegree;
        this.idRoom = idRoom;
    }

    public Reader(String libraryCardNumber, String firstName, String lastName, String passportNumber, Date dateBirth, String address, String phoneNumber, String educationLevel, String academicDegree, int idRoom, Date dateRegisteration) {
        this.libraryCardNumber = libraryCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.dateBirth = dateBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.educationLevel = educationLevel;
        this.academicDegree = academicDegree;
        this.idRoom = idRoom;
        this.dateRegisteration = dateRegisteration;
    }

    public Reader(String libraryCardNumber, String firstName, String lastName, String passportNumber, Date dateBirth, String address, String phoneNumber, String educationLevel, String academicDegree, int idRoom) {
        this.libraryCardNumber = libraryCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.dateBirth = dateBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.educationLevel = educationLevel;
        this.academicDegree = academicDegree;
        this.idRoom = idRoom;
    }

    public Reader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public Date getDateRegisteration() {
        return dateRegisteration;
    }

    public void setDateRegisteration(Date dateRegisteration) {
        this.dateRegisteration = dateRegisteration;
    }
}
