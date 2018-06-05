package avaj.main;

import avaj.hangar.AircraftFactory;
import avaj.hangar.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exceptions {
        if (args.length != 1) {
            throw new Exceptions("Wrong number of arguments");
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
                ArrayList<String[]> allAircraft = Validator.validator(bufferedReader);
                WeatherTower weatherTower = new WeatherTower();
                for (String[] aircraft:
                     allAircraft) {
                    Flyable flyable = AircraftFactory.newAircraft(aircraft[0], aircraft[1], Integer.parseInt(aircraft[2]),
                            Integer.parseInt(aircraft[3]), Integer.parseInt(aircraft[4]));
                    assert flyable != null;
                    flyable.registerTower(weatherTower);
                }
                bufferedReader.close();
                for (int i = 0; i < Validator.getNumberSimulations(); i++) {
                    weatherTower.changeWeather();
                }
            } catch (FileNotFoundException e) {
                throw new Exceptions("File Not Found", e);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                FileCreator.closeFile();
            }
        }
    }
}


/*
+ - public
# - protected
- - private
~ - access inside folder
_ - static
 */
