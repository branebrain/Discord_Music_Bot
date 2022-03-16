package mbot;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static java.net.URLEncoder.encode;

//Primary class for taking the inputted link and making a request to the Songlink API
//https://www.notion.so/API-d0ebe08a5e304a55928405eb682f6741

public class LinkConverter {

    static boolean badURL = false;
    //method to request from Songlink/Odesli API
    private static String makeRequest(String inputURL){
        String stringResponse = "";
        try {
            URL u = new URL(inputURL);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            stringResponse = response.toString();
            //System.out.println(stringResponse);

        } catch (IOException e) {
            badURL = true;
        }
        return stringResponse;
    }

    //method to encode url and append to API
    private static String buildQueryString(String link) {
        String base = "https://api.song.link/v1-alpha.1/links?url=";
        String EncodedURL = encode(link, StandardCharsets.UTF_8);
        return base + EncodedURL;
    }

    //method to parse the query response and return the correct link
    private static String parseQueryResponse(String queryResponse, String platform){
        String pathExpression = "$.linksByPlatform." +  platform + ".url";
        //System.out.println(pathExpression);
        String returnURL;
        try {
             returnURL = JsonPath.read(queryResponse, pathExpression);
        } catch (PathNotFoundException e) {
            returnURL = "I cannot find that song on " + platform + " :(" ;
        }
        return returnURL;
    }

    //getter method to be called from the Responder class
    public static String getNewLink(String platform, String link){

        String queryString = buildQueryString(link);
        String queryResponse = makeRequest(queryString);
        if (badURL){
            return "That was not a valid URL";
        }else {
            return parseQueryResponse(queryResponse, platform);
        }
    }


    /*public static void main(String[] args){

        String testString = "https://open.spotify.com/track/765PjNKQQuXJO4CWAKWewQ?si=0eb9a65910c34fd7";
        String returnLink = getNewLink("youtubeMusic", testString);
        System.out.println(returnLink);

    }*/



}
