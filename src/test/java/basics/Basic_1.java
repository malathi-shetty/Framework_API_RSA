package basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; // copy from above & put static & * as written
import static org.hamcrest.Matchers.*; // solves equalTo()

import org.testng.Assert;

import files.Payload;
import files.ReUsableMethods;

public class Basic_1 {

	public static void main(String[] args) {
		// To validate if Add place Pojo_API is working as expected
		// Add place --> Update Place with New Address --> Get Place to validate if New
		// Address is present in response

		// given - all input details,
		// when - submit the Pojo_API, resource , httpmethod
		// then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		/*
		 * given().log().all().queryParam("key", "qaclick123").header("Content-Type",
		 * "application/json") .body("{\r\n" + "\"location\": {\r\n" +
		 * "\"lat\": -38.383494,\r\n" + "\"lng\": 33.427362\r\n" + "},\r\n" +
		 * "\"accuracy\": 50,\r\n" + "\r\n" + "\"name\": \"Frontline house\",\r\n" +
		 * "\"phone_number\": \"(+91) 983 893 3937\",\r\n" +
		 * "\"address\": \"29, side layout, cohen 09\",\r\n" + "\"types\": [\r\n" +
		 * "\"shoe park\",\r\n" + "\"shop\"\r\n" + "],\r\n" +
		 * "\"website\": \"http://google.com\",\r\n" + "\"language\": \"French-IN\"\r\n"
		 * + "}")
		 * .when().post("maps/api/place/add/json").then().log().all().assertThat().
		 * statusCode(200) .body("scope", equalTo("APP")).header("Server",
		 * "Apache/2.4.52 (Ubuntu)");
		 */
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
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

		// Get Place  (Use GET here instead of PUT)
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
		//	JsonPath js1 = new JsonPath(getPlaceResponse);
			String actualAddress = js1.getString("address");
			System.out.println("actualAddress (from Get Place): " + actualAddress);
			Assert.assertEquals(actualAddress, newAddress);// Assert the new address is returned
		//	Assert.assertEquals(actualAddress, "Pacific ocean"); //AssertionError: expected [Pacific ocean] but found [Summer walk, Africa]
		} else {
			System.out.println("Response body is empty. The place_id might be invalid or the place may not exist.");
		}

	}

}
