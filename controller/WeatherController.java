package controller;

import model.Weather;
import model.User;
import dao.UserDAO;
import view.WeatherView;
import java.util.Random;

public class WeatherController {
    private WeatherView view;
    private UserDAO userDAO;
    private User currentUser;
    
    public WeatherController() {
        this.userDAO = new UserDAO();
    }
    
    public void setView(WeatherView view) {
        this.view = view;
    }
    
    public boolean login(String username, String password) {
        User user = userDAO.getUser(username, password);
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }
    
    public boolean register(String username, String password) {
        User newUser = new User(username, password);
        return userDAO.addUser(newUser);
    }
    
    public Weather getWeatherData(String city) {
        // In a real application, this would call a weather API
        Random random = new Random();
        int temperature = random.nextInt(35) + 5;
        String[] conditions = {"☀ Sunny", "☁ Cloudy", "🌧 Rainy", "⛅ Partly Cloudy"};
        String condition = conditions[random.nextInt(conditions.length)];
        int humidity = random.nextInt(60) + 30;
        int windSpeed = random.nextInt(30) + 5;
        
        return new Weather(city, temperature, condition, humidity, windSpeed);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void logout() {
        currentUser = null;
    }
}
