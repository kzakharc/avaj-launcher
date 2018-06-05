package avaj.hangar;

import avaj.main.FileCreator;
import avaj.weatherSettings.Coordinates;
import avaj.main.WeatherTower;

import java.util.Random;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(getCoordinates());
        Random random = new Random();
        switch (weather.toLowerCase()) {
            case ("sun"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude() + 2, getCoordinates().getLatitude(), getCoordinates().getHeight() + 4));
            case ("rain"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getHeight() - 5));
            case ("snow"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getHeight() - 15));
            case ("fog"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getHeight() - 3));
            default:
                FileCreator.writeToFile("Baloon#" + getName() + "(" + getId() + "): " + funnyMessages().get(random.nextInt(42) % funnyMessages().size()));
        }
        if (getCoordinates().getHeight() <= 0) {
            weatherTower.unregister(this);
            FileCreator.writeToFile("Baloon#" + getName() + "(" + getId() + ") landing.");
            FileCreator.writeToFile("Tower says: Baloon#" + getName() + "(" + getId() + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        FileCreator.writeToFile("Tower says: Baloon#" + getName() + "(" + getId() + ") registered to weather tower.");
    }
}
