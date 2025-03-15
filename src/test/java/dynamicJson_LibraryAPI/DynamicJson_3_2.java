package dynamicJson_LibraryAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;  // Static import added

public class DynamicJson_3_2 {


		 @Test
		    public void GenerateBook() throws IOException {
		        // Set the base URI for the Pojo_API
		        RestAssured.baseURI = "http://216.10.245.166";
		        
		        // Generate dynamic values for isbn, aisle, name, and author
		        String isbn = "isbn_" + System.currentTimeMillis();  // Generate unique ISBN
		        String aisle = "aisle_" + System.currentTimeMillis();  // Generate unique aisle
		        String name = "Learn Automation with Java";  // Example dynamic name
		        String author = "John Doe";  // Example dynamic author
		        
		        // Generate the dynamic JSON payload using the GenerateStringFromResource method
		        String payload = GenerateStringFromResource("GenerateBook.json", name, author, isbn, aisle);
System.out.println("Payload: " + payload);
		        // Send a POST request with the dynamic payload
		        Response resp = given()
		            .header("Content-Type", "application/json")
		            .body(payload)  // Use dynamic payload
		            .when()
		            .post("/Library/Addbook.php")  // Send the POST request
		            .then()
		            .assertThat().statusCode(200)  // Assert status code is 200 (OK)
		            .extract().response();  // Extract the response

		        // Extract the response body as a String and print it
		        String responseString = resp.asString();
		        System.out.println("Response: " + responseString);  // Print the response to the console
		        
		        // Parse the response and extract the ID
		        JsonPath js = ReUsableMethods.rawToJson(responseString);  // Parse the response
		        String id = js.get("ID");  // Extract the ID from the response body
		        System.out.println("Response-ID: " + id);  // Print the extracted ID to the console
		    }

		    // Method to replace placeholders with dynamic values in JSON file
		    public static String GenerateStringFromResource(String path, String name, String author, String isbn, String aisle) throws IOException {
		        // Read the content of the file into a string
		        String content = new String(Files.readAllBytes(Paths.get(path)));
		        
		        // Replace placeholders with actual values
		        content = content.replace("{{name}}", name);
		        content = content.replace("{{author}}", author);
		        content = content.replace("{{isbn}}", isbn);
		        content = content.replace("{{aisle}}", aisle);
		        
		        return content;	// Return the updated content


}}
