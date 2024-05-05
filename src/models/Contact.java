package models;

public class Contact {
    private String firstName, lastName, phoneNumber, email;
    private int id;
    private final int ownerId;

    // overloaded constructor - no id
    public Contact(String firstName, String lastName, String phoneNumber, String email, int ownerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ownerId = ownerId;
    }

    // overloaded constructor
    public Contact(String firstName, String lastName, String phoneNumber, String email, int ownerId, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ownerId = ownerId;
        this.id = id;
    }

    // setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // getters

    public String getFirstName() {
        return this.firstName;
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
