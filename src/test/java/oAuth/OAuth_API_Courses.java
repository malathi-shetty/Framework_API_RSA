package oAuth;

import io.restassured.path.json.JsonPath;
import oAuth_Courses.Pojo_API;
import oAuth_Courses.Pojo_GetCourse;
import oAuth_Courses.Pojo_WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class OAuth_API_Courses {
	//Injecting Pojo_Courses into OAuth Pojo_API

	public static void main(String[] args) {
		String response =

				given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
						.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
						.formParams("grant_type","client_credentials")
						.formParams("scope","trust")
						.when().log().all()
						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asPrettyString();

		System.out.println("Response:" + response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println("accessToken:" + accessToken);
		
		// Pojo_GetCourse from another package
		
		Pojo_GetCourse gc = given()
				.queryParams("access_token", accessToken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
				.as(Pojo_GetCourse.class);
		
		String[] WebAutomationcourseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		String[] ApiCourseTitles = {"Rest Assured Automation using Java", "SoapUI Webservices testing"};
		
		String[] MobileCourseTitles = {"Appium-Pojo_Mobile Automation using Java"};
		
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor", "Rest Assured Automation using Java", "SoapUI Webservices testing", "Appium-Pojo_Mobile Automation using Java"};
		
	System.out.println("LinkedIn: " + gc.getLinkedIn());
	System.out.println("Instructor: " + gc.getInstructor());
	System.out.println("Url: " + gc.getUrl());
	System.out.println("Services: " + gc.getServices());
	System.out.println("Expertise: " + gc.getExpertise());
	
	System.out.println("Api Course Title 0: " + gc.getCourses().getApi().get(0).getCourseTitle());
	System.out.println("Api Course Title 1: " + gc.getCourses().getApi().get(1).getCourseTitle());
	
	List<Pojo_API> apiCourses = gc.getCourses().getApi();
	for(int i = 0; i<apiCourses.size(); i++)
	{
		if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
		{
			System.out.println("Price: " + apiCourses.get(i).getPrice());		
		}
		
	}
	
	System.out.println("Pojo_Mobile Course Title 0: " + gc.getCourses().getMobile().get(0).getCourseTitle() + " Price: " + gc.getCourses().getMobile().get(0).getPrice());
	
	//Get Pojo_Courses of Web Automation Pojo_Courses: 
	ArrayList<String> actualList = new ArrayList<String>();
	
	List<Pojo_WebAutomation> w = gc.getCourses().getWebAutomation();
	for(int j =0; j<w.size(); j++)
	{
		//System.out.print("Web Automation Pojo_Courses: " );
		//System.out.println( w.get(j).getCourseTitle());
		actualList.add(w.get(j).getCourseTitle());
	}
	
	List<String> expectedList = Arrays.asList(WebAutomationcourseTitles);
	
	Assert.assertTrue(actualList.equals(expectedList));// True
	
	List<String> expectedList2 = Arrays.asList(courseTitles);
	Assert.assertTrue(actualList.equals(expectedList2)); // False

	}

}
