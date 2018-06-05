package avaj.weatherSettings;

import java.util.Random;

public class WeatherProvider {

    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        Random random = new Random();
        int rand = random.nextInt(coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight());
        return weather[rand % 4];
    }
}
