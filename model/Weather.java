package model;

import java.time.LocalDateTime;

public class Weather {
    private int id;
    private String city;
    private double temperature;
    private String condition;
    private int humidity;
    private double windSpeed;
    private LocalDateTime timestamp;
    private String icon;
    private double feelsLike;
    private int pressure;
    private double visibility;
    private String description;

    // Default constructor
    public Weather() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with essential fields
    public Weather(String city, double temperature, String condition, int humidity, double windSpeed) {
        this();
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    // Constructor with all fields
    public Weather(int id, String city, double temperature, String condition, int humidity, 
                  double windSpeed, LocalDateTime timestamp, String icon, double feelsLike, 
                  int pressure, double visibility, String description) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.timestamp = timestamp;
        this.icon = icon;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.visibility = visibility;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Weather{" +
               "city='" + city + '\'' +
               ", temperature=" + temperature +
               ", condition='" + condition + '\'' +
               ", humidity=" + humidity +
               ", windSpeed=" + windSpeed +
               ", timestamp=" + timestamp +
               ", description='" + description + '\'' +
               '}';
    }

    // Create a database table for weather data
    public static String getCreateTableSQL() {
        return """
               CREATE TABLE IF NOT EXISTS weather (
                   id INT PRIMARY KEY AUTO_INCREMENT,
                   city VARCHAR(100) NOT NULL,
                   temperature DOUBLE NOT NULL,
                   condition VARCHAR(50) NOT NULL,
                   humidity INT NOT NULL,
                   wind_speed DOUBLE NOT NULL,
                   timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                   icon VARCHAR(50),
                   feels_like DOUBLE,
                   pressure INT,
                   visibility DOUBLE,
                   description TEXT,
                   INDEX idx_city (city),
                   INDEX idx_timestamp (timestamp)
               )
               """;
    }
}
