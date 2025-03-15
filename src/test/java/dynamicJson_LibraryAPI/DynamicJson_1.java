package dynamicJson_LibraryAPI;

import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson_1 {

	// 1. Dynamically build json payload with external data inputs
	@Test
	public void addBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.AddBook("wwwwv", "6464")). // change data here
				when().post("/Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();
		System.out.println("Response: " + response); // Print the response to see what you are getting.

		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println("ID from the Response-isbn & aisle: " + id);

	}

}
