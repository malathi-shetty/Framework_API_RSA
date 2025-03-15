package dynamicJson_LibraryAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson_3_1 {

    // 3. How to Send static Json files(Payload) directly into Post Method of Rest
    // Assured
	
	 @Test
	    public void GenerateBook() throws IOException {
	        // Set the base URI for the Pojo_API
	        RestAssured.baseURI = "http://216.10.245.166";
	        
	        String filePath = "Library_API.json";
	        // Send a POST request with the content of the Addbookdetails.json file as the body
	        Response resp = given()
	            .header("Content-Type", "application/json")
	            .body(GenerateStringFromResource(filePath))  // Read JSON from file
	            .when()
	            .post("/Library/Addbook.php")  // Send the POST request
	            .then()
	            .assertThat().statusCode(200)  // Assert status code is 200 (OK)
	            .extract().response();  // Extract the response

	        // Extract the response body as a String and pass it to rawToJson()
	        String responseString = resp.asString();
	        System.out.println("Response: " + responseString);  // Print the response to the console);
	        
	        // Parse the response and extract the ID
	        JsonPath js = ReUsableMethods.rawToJson(resp);  // Now pass the string to rawToJson()
	        String id = js.get("ID");  // Extract the ID from the response body
	        System.out.println("Response-ID: " + id);  // Print the extracted ID to the console
	    }

	    // Method to read JSON file and return its content as a string
	    public static String GenerateStringFromResource(String path) throws IOException {
	        return new String(Files.readAllBytes(Paths.get(path)));  // Read file content and return as String
	    
    }
	}

