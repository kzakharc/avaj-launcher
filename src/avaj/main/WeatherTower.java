package avaj.main;

import avaj.weatherSettings.Coordinates;
import avaj.weatherSettings.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }
    void changeWeather() {
        super.conditionsChanged();
    }
}
