package view;

import controller.WeatherController;
import model.Weather;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class WeatherView {
    private WeatherController controller;
    private JFrame currentFrame;

    public WeatherView(WeatherController controller) {
        this.controller = controller;
    }

    public void showLoginFrame() {
        JFrame loginFrame = new JFrame("Weather App - Login");
        currentFrame = loginFrame;
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Logo/Title
        JLabel titleLabel = new JLabel("Weather App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Login fields
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        fieldsPanel.setMaximumSize(new Dimension(300, 60));
        fieldsPanel.setOpaque(false);
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        fieldsPanel.add(new JLabel("Username:"));
        fieldsPanel.add(usernameField);
        fieldsPanel.add(new JLabel("Password:"));
        fieldsPanel.add(passwordField);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);
        
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (controller.login(username, password)) {
                loginFrame.dispose();
                showWeatherFrame();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!");
            }
        });
        
        signupButton.addActionListener(e -> {
            loginFrame.dispose();
            showSignupFrame();
        });
        
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(fieldsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        
        loginFrame.add(mainPanel);
        loginFrame.setVisible(true);
    }

    public void showSignupFrame() {
        JFrame signupFrame = new JFrame("Weather App - Sign Up");
        currentFrame = signupFrame;
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupFrame.setSize(400, 350);
        signupFrame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        fieldsPanel.setMaximumSize(new Dimension(300, 90));
        fieldsPanel.setOpaque(false);
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        
        fieldsPanel.add(new JLabel("Username:"));
        fieldsPanel.add(usernameField);
        fieldsPanel.add(new JLabel("Password:"));
        fieldsPanel.add(passwordField);
        fieldsPanel.add(new JLabel("Confirm Password:"));
        fieldsPanel.add(confirmPasswordField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);
        
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back to Login");
        
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(signupFrame, "Please fill all fields!");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(signupFrame, "Passwords don't match!");
            } else if (controller.register(username, password)) {
                JOptionPane.showMessageDialog(signupFrame, "Account created successfully!");
                signupFrame.dispose();
                showLoginFrame();
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Username already exists!");
            }
        });
        
        backButton.addActionListener(e -> {
            signupFrame.dispose();
            showLoginFrame();
        });
        
        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);
        
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(fieldsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        
        signupFrame.add(mainPanel);
        signupFrame.setVisible(true);
    }

    public void showWeatherFrame() {
        JFrame weatherFrame = new JFrame("Weather App - Welcome " + controller.getCurrentUser().getUsername());
        currentFrame = weatherFrame;
        weatherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weatherFrame.setSize(800, 600);
        weatherFrame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        JTextField cityInput = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton logoutButton = new JButton("Logout");
        
        searchPanel.add(new JLabel("City: "));
        searchPanel.add(cityInput);
        searchPanel.add(searchButton);
        searchPanel.add(logoutButton);
        
        // Current Weather Panel
        JPanel currentWeatherPanel = new JPanel();
        currentWeatherPanel.setLayout(new BoxLayout(currentWeatherPanel, BoxLayout.Y_AXIS));
        currentWeatherPanel.setBorder(BorderFactory.createTitledBorder("Current Weather"));
        currentWeatherPanel.setOpaque(false);
        
        JLabel temperatureLabel = new JLabel("Temperature: --°C");
        JLabel conditionLabel = new JLabel("Condition: --");
        JLabel humidityLabel = new JLabel("Humidity: --%");
        JLabel windSpeedLabel = new JLabel("Wind Speed: -- km/h");
        
        currentWeatherPanel.add(temperatureLabel);
        currentWeatherPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        currentWeatherPanel.add(conditionLabel);
        currentWeatherPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        currentWeatherPanel.add(humidityLabel);
        currentWeatherPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        currentWeatherPanel.add(windSpeedLabel);
        
        // Forecast Panel
        JPanel forecastPanel = new JPanel(new GridLayout(1, 5, 10, 0));
        forecastPanel.setBorder(BorderFactory.createTitledBorder("5-Day Forecast"));
        forecastPanel.setOpaque(false);
        
        // Add action listeners
        searchButton.addActionListener(e -> {
            String city = cityInput.getText().trim();
            if (!city.isEmpty()) {
                updateWeatherDisplay(city, temperatureLabel, conditionLabel, 
                                  humidityLabel, windSpeedLabel, forecastPanel);
            } else {
                JOptionPane.showMessageDialog(weatherFrame, "Please enter a city name");
            }
        });
        
        logoutButton.addActionListener(e -> {
            controller.logout();
            weatherFrame.dispose();
            showLoginFrame();
        });
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(currentWeatherPanel, BorderLayout.CENTER);
        mainPanel.add(forecastPanel, BorderLayout.SOUTH);
        
        weatherFrame.add(mainPanel);
        weatherFrame.setVisible(true);
    }

    private void updateWeatherDisplay(String city, JLabel temperatureLabel, 
                                    JLabel conditionLabel, JLabel humidityLabel, 
                                    JLabel windSpeedLabel, JPanel forecastPanel) {
        Weather weather = controller.getWeatherData(city);
        
        temperatureLabel.setText("Temperature: " + weather.getTemperature() + "°C");
        conditionLabel.setText("Condition: " + weather.getCondition());
        humidityLabel.setText("Humidity: " + weather.getHumidity() + "%");
        windSpeedLabel.setText("Wind Speed: " + weather.getWindSpeed() + " km/h");
        
        // Update forecast
        forecastPanel.removeAll();
        for (int i = 0; i < 5; i++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createEtchedBorder());
            dayPanel.setOpaque(false);
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i + 1);
            String dayName = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}
                [cal.get(Calendar.DAY_OF_WEEK) - 1];
            
            Weather forecast = controller.getWeatherData(city); // Get forecast for each day
            
            JLabel dayLabel = new JLabel(dayName);
            JLabel tempLabel = new JLabel(forecast.getTemperature() + "°C");
            JLabel condLabel = new JLabel(forecast.getCondition());
            
            dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            condLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            dayPanel.add(dayLabel);
            dayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            dayPanel.add(tempLabel);
            dayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            dayPanel.add(condLabel);
            
            forecastPanel.add(dayPanel);
        }
        forecastPanel.revalidate();
        forecastPanel.repaint();
    }
}
