package ExcelDriven_2_learn;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ExcelDriven_1 {
	
	@Test
	public void addBook() 
	{
		
		 RestAssured.baseURI = "http://216.10.245.166";
		 Response resp = given()
				 .header("Content-Type","application/json")
				 .body("{\r\n\"name\":\"Learn Mobile Automation with Java\",\r\n\"isbn\":\"ach\",\r\n\"aisle\":\"1155\",\r\n\"author\":\"John foe\"\r\n}")
				 .when()
				 .post("/Library/Addbook.php")
				 .then().assertThat().statusCode(200)
				 .extract().response();
		 
		 
		 JsonPath js = ReUsableMethods.rawToJson(resp);
		 String id = js.get("ID");
		 System.out.println("ID: " + id);
				
	}

	

}
