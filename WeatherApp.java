import controller.WeatherController;
import view.WeatherView;

public class WeatherApp {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController();
        WeatherView view = new WeatherView(controller);
        controller.setView(view);
        view.showLoginFrame();
    }
}
