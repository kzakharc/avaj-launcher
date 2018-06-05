package avaj.hangar;

import avaj.main.FileCreator;
import avaj.weatherSettings.Coordinates;
import avaj.main.WeatherTower;

import java.util.Random;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(getCoordinates());
        Random random = new Random();
        switch (weather.toLowerCase()) {
            case ("sun"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude() + 10, getCoordinates().getLatitude(), getCoordinates().getHeight() + 2));
            case ("rain"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude() + 5, getCoordinates().getLatitude(), getCoordinates().getHeight()));
            case ("snow"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getHeight() - 12));
            case ("fog"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude() + 1, getCoordinates().getLatitude(), getCoordinates().getHeight()));
            default:
                FileCreator.writeToFile("Helicopter#" + getName() + "(" + getId() + "): " + funnyMessages().get(random.nextInt(42) % funnyMessages().size()));
        }
        if (getCoordinates().getHeight() <= 0) {
            weatherTower.unregister(this);
            FileCreator.writeToFile("Helicopter#" + getName() + "(" + getId() + ") landing.");
            FileCreator.writeToFile("Tower says: Helicopter#" + getName() + "(" + getId() + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        FileCreator.writeToFile("Tower says: Helicopter#" + getName() + "(" + getId() + ") registered to weather tower.");
    }
}

