package model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private boolean active;
    private String preferredCity;

    // Default constructor
    public User() {
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    // Constructor with essential fields
    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    // Constructor with all fields
    public User(int id, String username, String password, String email, 
                String firstName, String lastName, LocalDateTime createdAt, 
                LocalDateTime lastLogin, boolean active, String preferredCity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.active = active;
        this.preferredCity = preferredCity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPreferredCity() {
        return preferredCity;
    }

    public void setPreferredCity(String preferredCity) {
        this.preferredCity = preferredCity;
    }

    // Get full name
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        }
        return username;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", createdAt=" + createdAt +
               ", lastLogin=" + lastLogin +
               ", active=" + active +
               ", preferredCity='" + preferredCity + '\'' +
               '}';
    }

    // SQL for creating the users table
    public static String getCreateTableSQL() {
        return """
               CREATE TABLE IF NOT EXISTS users (
                   id INT PRIMARY KEY AUTO_INCREMENT,
                   username VARCHAR(50) UNIQUE NOT NULL,
                   password VARCHAR(255) NOT NULL,
                   email VARCHAR(100),
                   first_name VARCHAR(50),
                   last_name VARCHAR(50),
                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                   last_login TIMESTAMP,
                   active BOOLEAN DEFAULT TRUE,
                   preferred_city VARCHAR(100),
                   INDEX idx_username (username),
                   INDEX idx_email (email)
               )
               """;
    }

    // SQL for creating user preferences table (for future expansion)
    public static String getCreatePreferencesTableSQL() {
        return """
               CREATE TABLE IF NOT EXISTS user_preferences (
                   user_id INT PRIMARY KEY,
                   temperature_unit VARCHAR(1) DEFAULT 'C',
                   wind_speed_unit VARCHAR(3) DEFAULT 'KMH',
                   theme VARCHAR(10) DEFAULT 'LIGHT',
                   notifications_enabled BOOLEAN DEFAULT TRUE,
                   FOREIGN KEY (user_id) REFERENCES users(id)
               )
               """;
    }
}
