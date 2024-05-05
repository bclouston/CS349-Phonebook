package models;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.security.*;

public class DBManager {
    private final String URL = "jdbc:mysql:///phonebookdb";
    private final String USER = "root";
    private final String PASS = "phonebook";
    private int userId;
    private Connection conn;

    public DBManager() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    public DBManager(int id) {
        userId = id;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    // returns SHA-256 hash of input string
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }
    }

    // prints users associated contact list in csv format to file
    public boolean exportCSV() {
        List<Contact> contacts = getContacts();
        String OUTPUTCSV = "contacts.csv";
        File csvOutputFile = new File(OUTPUTCSV);
        try {
            FileWriter fw = new FileWriter(csvOutputFile);
            fw.append("Last name,First name,Phone,Email\n");
            for (Contact c : contacts) {
                fw.append(c.getLastName());
                fw.append(",");
                fw.append(c.getFirstName());
                fw.append(",");
                fw.append(c.getNumber());
                fw.append(",");
                fw.append(c.getEmail());
                fw.append("\n");
            }
            fw.flush();
            fw.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    // DATABASE QUERIES

    // add contact to contacts table
    public boolean addContact(Contact contact) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO contacts (userid, firstName, lastName, phone, email)" +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            pstmt.setInt(1, contact.getOwnerId());
            pstmt.setString(2, contact.getFirstName());
            pstmt.setString(3, contact.getLastName());
            pstmt.setString(4, contact.getNumber());
            pstmt.setString(5, contact.getEmail());
            int row = pstmt.executeUpdate();
            return (row > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    // updates a contacts information in contacts table
    public boolean updateContact(Contact contact) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE contacts " +
                            "SET firstName = ?, lastName = ?, phone = ?, email = ? " +
                            "WHERE id = ?"
            );
            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getNumber());
            pstmt.setString(4, contact.getEmail());
            pstmt.setInt(5, contact.getId());
            System.out.println(pstmt);
            int row = pstmt.executeUpdate();
            return (row > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    // deletes contact from contacts table
    public boolean removeContact(Contact contact) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                        "DELETE FROM contacts " +
                            "WHERE id = ?"
            );
            pstmt.setInt(1, contact.getId());
            int row = pstmt.executeUpdate();
            return (row > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    // generates and returns list of contacts associated with user from contacts table
    public List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM contacts WHERE userid = ?"
            );
            pstmt.setInt(1, userId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Contact contact = new Contact(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phone"),
                        result.getString("email"),
                        result.getInt("userid"),
                        result.getInt("id")
                        );
                contactList.add(contact);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contactList;
    }

    // adds user account details to users table
    public boolean addUser(User user) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO users (username, password)" +
                            "VALUES (?, ?)"
            );
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, hashPassword(user.getPassword()));
            int row = pstmt.executeUpdate();

            return (row > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    // authenticate login attempt, check if username/password combination exists in users table
    public boolean loginUser(String username, String password) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
            );
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                this.userId = results.getInt("id");
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    // return authenticated users id, called on successful login
    public int getCurrentUser() {
        return userId;
    }

}
