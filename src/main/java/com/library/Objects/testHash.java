package com.library.Objects;
import org.mindrot.jbcrypt.BCrypt;


public class testHash {

    public static void main(String[] args) {
        String adminPassword = "admin";
        String hashedPassword = BCrypt.hashpw(adminPassword, BCrypt.gensalt());
        System.out.println("Admin Password Hash: " + hashedPassword);
    }

}
