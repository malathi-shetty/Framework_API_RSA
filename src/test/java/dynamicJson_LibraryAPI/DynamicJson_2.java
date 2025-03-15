package dynamicJson_LibraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson_2 {
	// 2. Parameterized the Pojo_API Tests with multiple data sets
	// DataProvider: TestNG annotation to provide multiple data sets to a test method
	// DataProvider is a method that returns a 2D array of objects
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.AddBook(isbn, aisle)). // change data here
				when().post("/Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();
		System.out.println("Response: " + response); // Print the response to see what you are getting.

		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println("ID from the Response-isbn & aisle: " + id);

	}

	

	@DataProvider(name="BooksData")
	public Object[][] getData() {
		// array: collection of elements
		// multidimensional array: collection of arrays

		// new object[][] {{array1}, {"isbn","aisle"}, {array3},...};
		return new Object[][] { { "ojwty", "9363" }, { "cwetee", "4253" }, { "okmfy", "533" } }; // 3 data sets
	}

}
