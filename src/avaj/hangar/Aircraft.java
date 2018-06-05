package avaj.hangar;

import avaj.weatherSettings.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;

public class Aircraft {
    protected final long id;
    protected final String name;
    protected Coordinates coordinates;
    private static long idCounter;
    protected final ArrayList<String> message = new ArrayList<>(Arrays.asList(
            "I'm sick of this hot weather.",
            "I can't stand this hot weather.",
            "The weather is becoming cooler.",
            "I am accustomed to cold weather.",
            "According to the weather forecast, it will clear up tomorrow.",
            "You seem to be a little under the weather.",
            "Bad weather kept us from going out.",
            "There was a sudden change in the weather."
    ));

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCounter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<String> funnyMessages() {
        return message;
    }
}
