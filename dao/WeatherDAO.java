package dao;

import model.Weather;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO {
    
    public void createWeatherTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(Weather.getCreateTableSQL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeather(Weather weather) {
        String query = """
                      INSERT INTO weather (city, temperature, condition, humidity, 
                      wind_speed, icon, feels_like, pressure, visibility, description)
                      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                      """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, weather.getCity());
            pstmt.setDouble(2, weather.getTemperature());
            pstmt.setString(3, weather.getCondition());
            pstmt.setInt(4, weather.getHumidity());
            pstmt.setDouble(5, weather.getWindSpeed());
            pstmt.setString(6, weather.getIcon());
            pstmt.setDouble(7, weather.getFeelsLike());
            pstmt.setInt(8, weather.getPressure());
            pstmt.setDouble(9, weather.getVisibility());
            pstmt.setString(10, weather.getDescription());

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    weather.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Weather getLatestWeatherByCity(String city) {
        String query = "SELECT * FROM weather WHERE city = ? ORDER BY timestamp DESC LIMIT 1";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, city);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToWeather(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Weather> getWeatherHistory(String city, LocalDateTime from, LocalDateTime to) {
        String query = "SELECT * FROM weather WHERE city = ? AND timestamp BETWEEN ? AND ? ORDER BY timestamp DESC";
        List<Weather> weatherList = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, city);
            pstmt.setTimestamp(2, Timestamp.valueOf(from));
            pstmt.setTimestamp(3, Timestamp.valueOf(to));
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                weatherList.add(mapResultSetToWeather(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherList;
    }

    private Weather mapResultSetToWeather(ResultSet rs) throws SQLException {
        Weather weather = new Weather();
        weather.setId(rs.getInt("id"));
        weather.setCity(rs.getString("city"));
        weather.setTemperature(rs.getDouble("temperature"));
        weather.setCondition(rs.getString("condition"));
        weather.setHumidity(rs.getInt("humidity"));
        weather.setWindSpeed(rs.getDouble("wind_speed"));
        weather.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        weather.setIcon(rs.getString("icon"));
        weather.setFeelsLike(rs.getDouble("feels_like"));
        weather.setPressure(rs.getInt("pressure"));
        weather.setVisibility(rs.getDouble("visibility"));
        weather.setDescription(rs.getString("description"));
        return weather;
    }
}
