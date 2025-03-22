package ExcelDriven_2_learn;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.DataDriven_3;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


//https://github.com/rest-assured/rest-assured/wiki/Usage#create-json-from-a-hashmap
//hashmap stores 2 parameter: key & value

public class ExcelDriven_Using_HashMap_Excel_Integrate {
	
	@Test
	public void addBook() throws IOException 
	{
		//DataDriven_3 is kept on /Framework_API_RSA/src/main/java/resources/DataDriven_3
		//	ArrayList data =	d.getData("RestAddBook", "RestAssured"); (RowName, sheetName)
		
		DataDriven_3 d = new DataDriven_3();
	ArrayList data =	d.getData("RestAddBook","RestAssured");
	
	//ArrayList<String> data = d.getData("RestAddBook");
	System.out.println("Data: " + data);

	
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name",data.get(0));
		jsonAsMap.put("isbn",data.get(1));
		jsonAsMap.put("aisle",data.get(2));
		jsonAsMap.put("author",data.get(3));
		
		
		
		
		
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
