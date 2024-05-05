package models;

public class User {

    private final String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
