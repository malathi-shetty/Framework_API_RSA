package specBuilder_oAuth_Serialization_GoogleMapsApi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder_SerializeTest {

	public static void main(String[] args) {
		// Serialization: Convert Java Object to Json

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		Pojo_Add_Place p = new Pojo_Add_Place();
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

		Pojo_Location l = new Pojo_Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		RequestSpecification res = given().log().all().spec(req).body(p); // given attached is the request to Pojo_API

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		Response response = res.when().post("maps/api/place/add/json") // when attached is the request send to Pojo_API
				.then().assertThat().spec(resspec).extract().response(); // then attached is the response we get 
																			// from the Pojo_API

		String responseString = response.asPrettyString();
		System.out.println(responseString);

	}

}
