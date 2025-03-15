package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableMethods {

	 // Convert response String to JsonPath
    public static JsonPath rawToJson(String response) {
        return new JsonPath(response); // Convert string to JsonPath
    }

    // Convert Response object to JsonPath
    public static JsonPath rawToJson(Response response) {
        String responseString = response.asString();  // Extract the response body as a String
        return new JsonPath(responseString);  // Convert it into a JsonPath object
    }
   
}