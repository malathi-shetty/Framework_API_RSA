package oAuth_Serialization_GoogleMapsApi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializeTest {

	public static void main(String[] args) {
		//Serialization: Convert Java Object to Json

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		Add_Place p = new Add_Place();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362); 
		p.setLocation(l);
		
		
		Response res = given().log().all().queryParam("key", "qaclick123")
				.body(p)
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.extract().response();
				
		
		String responseString = res.asPrettyString();
		System.out.println(responseString);
		
		
	}

}
