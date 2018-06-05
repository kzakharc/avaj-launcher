package avaj.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Validator validator = new Validator();
    private static int numberSimulations;

    private Validator() {
    }

    public static ArrayList<String[]> validator(BufferedReader bufferedReader) {
        ArrayList<String[]> allAircraft = new ArrayList<>();
        Pattern digit = Pattern.compile("^\\d+\\s*$");
        Pattern aircraftInfo = Pattern.compile(
                "^(Baloon [B]{1}\\d+ \\d+ \\d+ \\d+$|" +
                "^JetPlane [J]{1}\\d+ \\d+ \\d+ \\d+$|" +
                "^Helicopter [H]{1}\\d+ \\d+ \\d+ \\d+$)");
        try {
            String  line = bufferedReader.readLine();
            if (line == null) {
                throw new Exceptions("File is empty");
            }
            Matcher matcher = digit.matcher(line);
            if (!matcher.matches()) {
                throw new Exceptions("Wrong simulation number");
            }
            numberSimulations = Integer.parseInt(line.split(" ")[0]);
            for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                matcher = aircraftInfo.matcher(line);
                if (!matcher.matches()) {
                    throw new Exceptions("Invalid data in the string: " + line);
                }
                allAircraft.add(line.split(" "));
            }
        } catch (IOException | Exceptions e) {
            e.printStackTrace();
        }
        return allAircraft;
    }

    public static int getNumberSimulations() {
        return numberSimulations;
    }
}
