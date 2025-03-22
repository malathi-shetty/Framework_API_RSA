package ExcelDriven_2_learn;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashMap;


//https://github.com/rest-assured/rest-assured/wiki/Usage#create-json-from-a-hashmap
//hashmap stores 2 parameter: key & value


/* Nested hashmap
 * 
 * Input Payload : Json:
{

"name":"Learn Appium Automation with Java",

"isbn":"bcd",

"aisle":"227",

  "location": {
  
    "lat": -38.383494,
    
    "lng": 33.427362
    
  }
  
"author":"John foe"

}
 */

public class ExcelDriven_Using_HashMap_Nested {
	
	@Test
	public void addBook() 
	{
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name","Learn Mobile Automation with Java");
		jsonAsMap.put("isbn","ghsgdhs");
		jsonAsMap.put("aisle","4545");
		jsonAsMap.put("author","John foe");
		
		HashMap<String, Object>  Map = new HashMap<>();
		Map.put("lat","12");
		Map.put("lng","34");
		jsonAsMap.put("location",Map);
		
		 RestAssured.baseURI = "http://216.10.245.166";
		 Response resp = given()
				 .header("Content-Type","application/json")
				 .body(jsonAsMap)
				 .when()
				 .post("/Library/Addbook.php")
				 .then().assertThat().statusCode(200)
				 .extract().response();
		 
		 
		 JsonPath js = ReUsableMethods.rawToJson(resp);
		 String id = js.get("ID");
		 System.out.println("ID: " + id);
				
	}

	

}
