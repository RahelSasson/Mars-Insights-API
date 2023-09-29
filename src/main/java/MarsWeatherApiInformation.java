import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MarsWeatherApiInformation {

    private solKey weather;
    private String[] sol_Keys = new String[7];
    private int[][][] average_Temperatures = new int[7][1][3];
    private int[] wind_Speeds = new int[7];
    private int[] atmospheric_Pressures = new int[7];
    private String[] sol_Dates = new String[7];

    public MarsWeatherApiInformation () throws IOException, ParseException {
        URL url = new URL("https://mars.nasa.gov/rss/api/?feed=weather&category=insight_temperature&feedtype=json&ver=1.0");
        //Establish connection to the API
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        //Validating that the request has been sent successfully
        if(responseCode == HttpsURLConnection.HTTP_OK){
            StringBuilder sb = new StringBuilder();
            Scanner s = new Scanner(connection.getInputStream());
            while(s.hasNext()) {
                sb.append(s.nextLine());
            }
            System.out.println(sb);

            //using ObjectMapper to convert response string to a java object
            ObjectMapper objectMapper = new ObjectMapper();

            //read the root JSON object
            JsonNode rootNode = objectMapper.readTree(String.valueOf(sb));

            //iterate through all subnodes of the JSON object
            Iterator<Map.Entry<String,JsonNode>> solIterator = rootNode.fields();
            int i = 0;
            while(solIterator.hasNext()) {

                if(i > 6) //we only want the info for the last 7 sols (Martian Days)
                    break;

                Map.Entry<String,JsonNode> solEntry = solIterator.next();
                String Key = solEntry.getKey(); //this is the Sol key

                JsonNode solData = solEntry.getValue();
                this.weather = objectMapper.readValue(solData.toString(),solKey.class);

                String sol = Key;
                sol_Keys[i] = sol;

                //3D array to represent the average temp, highest temp and lowest temp
                //of each individual sol
                average_Temperatures[i][0][0] = (int) weather.getAtmosphericTemperature().getAv();
                average_Temperatures[i][0][1] = (int) weather.getAtmosphericTemperature().getMn();
                average_Temperatures[i][0][2] = (int) weather.getAtmosphericTemperature().getMx();

                wind_Speeds[i] = (int) weather.getWindSpeed().getAv();
                atmospheric_Pressures[i] = (int) weather.getPressure().getAv();

                //converting sol date format into desired string format
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMMM-dd");
                Date d = simpleDateFormat.parse(weather.getLastUtc());
                String dateString = simpleDateFormat1.format(d);
                sol_Dates[i] = dateString;

                i++;
            }
        }
        else {
            System.out.println("Error in sending GET request");
        }
    }

    //returns sol keys array
    public String[] getSol_Keys() {
        return sol_Keys;
    }

    //returns 3D array of temperatures for each sol
    //average temp, highest temp and lowest temp
    public int[][][] getAverage_Temperatures() {
        return average_Temperatures;
    }

    //returns array of wind speeds
    public int[] getWind_Speeds() {
        return wind_Speeds;
    }

    //returns array of atmospheric temperatures
    public int[] getAtmospheric_Pressures() {
        return atmospheric_Pressures;
    }

    //returns array of earth dates
    public String[] getSol_Dates() {
        return sol_Dates;
    }

}
