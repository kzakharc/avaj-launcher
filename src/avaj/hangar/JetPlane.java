package avaj.hangar;

import avaj.main.FileCreator;
import avaj.weatherSettings.Coordinates;
import avaj.main.WeatherTower;

import java.util.Random;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(getCoordinates());
        Random random = new Random();
        switch (weather.toLowerCase()) {
            case ("sun"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude() + 10, getCoordinates().getHeight() + 2));
            case ("rain"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude() + 5, getCoordinates().getHeight()));
            case ("snow"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getHeight() - 7));
            case ("fog"):
                setCoordinates(new Coordinates(getCoordinates().getLongitude(), getCoordinates().getLatitude() + 1, getCoordinates().getHeight()));
            default:
                FileCreator.writeToFile("JetPlane#" + getName() + "(" + getId() + "): " + funnyMessages().get(random.nextInt(42) % funnyMessages().size()));
        }
        if (getCoordinates().getHeight() <= 0) {
            weatherTower.unregister(this);
            FileCreator.writeToFile("JetPlane#" + getName() + "(" + getId() + ") landing.");
            FileCreator.writeToFile("Tower says: JetPlane#" + getName() + "(" + getId() + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        FileCreator.writeToFile("Tower says: JetPlane#" + getName() + "(" + getId() + ") registered to weather tower.");
    }
}
