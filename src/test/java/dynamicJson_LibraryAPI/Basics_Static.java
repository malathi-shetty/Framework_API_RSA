package dynamicJson_LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; // copy from above & put static & * as written
import static org.hamcrest.Matchers.*; // solves equalTo()

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethods;

public class Basics_Static {

	// 3. How to Send static Json files(Payload) directly into Post Method of Rest
	// Assured
	// External File: static.json

	public static void main(String[] args) throws IOException {
		// Content of the file to String --> Content of file can convert into Byte -->
		// Byte Data to String
//body(new String(Files.readAllBytes(Paths.get("Replace File Path where data is stored")))).when()

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("D:\\Git Projects\\Framework_API_RSA\\static.json"))))
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		// Add place --> Update Place with New Address --> Get Place to validate if New
		// address is present in response

		System.out.println(response);

		JsonPath js = new JsonPath(response); // for parsing json
		String placeId = js.getString("place_id");
		System.out.println("place_id (from AddPlace response): " + placeId);

		// Update Place with new address

		String newAddress = "Summer walk, Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place (Use GET here instead of PUT)
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json") // <-- Correct endpoint for retrieving the place details
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		/*
		 * JsonPath js1 = new JsonPath(getPlaceResponse); String actualAddress =
		 * js1.getString("address"); System.out.println("actualAddress: " +
		 * actualAddress);
		 */

		// Print response to debug
		System.out.println("Get Place Response: " + getPlaceResponse);

		// If the response is valid JSON, then parse it
		if (!getPlaceResponse.isEmpty()) {

			JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
			// JsonPath js1 = new JsonPath(getPlaceResponse);
			String actualAddress = js1.getString("address");
			System.out.println("actualAddress (from Get Place): " + actualAddress);
			Assert.assertEquals(actualAddress, newAddress);// Assert the new address is returned
			// Assert.assertEquals(actualAddress, "Pacific ocean"); //AssertionError:
			// expected [Pacific ocean] but found [Summer walk, Africa]
		} else {
			System.out.println("Response body is empty. The place_id might be invalid or the place may not exist.");
		}

	}

}
