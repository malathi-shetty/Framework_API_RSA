package jiraAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {

	    //Creating a new issue in JIRA
		RestAssured.baseURI = "https://shettymalathi113.atlassian.net/";

	String createIssueResponse =	given().log().all()
		.header("Content-Type","application/json")
		.header("Authorization","Basic c2hldHR5bWFsYXRoaTExM0BnbWFpbC5jb206QVRBVFQzeEZmR0YwV202SFljZklZNk5LZ09lNVNNRmI5Rl9RbTZxbHViZjZMSmxtQlVvRnBYTW5FQkx3d1NhcEdMNE81VFZQWXNNYXI0bEstSjltaTJBNzF4Y1Q1OEpmWmkwRUFDb09nLUE0M0RvY05GS2o0V2RLanhVejhFU2ZHYkkyanUxTFBMclpyaFhsMWVHM0lBcVphLTJYMDVHZV9VQVJYVng4VklNaEJCa3lUaUREbDNvPUEyRjE3QjA0")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Websites are not working - automation Rest Assured\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		System.out.println("Response is : "+ createIssueResponse);
		
		//Parsing the response to get the issue id
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println("Issue id is : " + issueId);
		
		// Add Attachment to the issue
		
			given().log().all()
				.header("X-Atlassian-Token","no-check")
				.header("Authorization","Basic c2hldHR5bWFsYXRoaTExM0BnbWFpbC5jb206QVRBVFQzeEZmR0YwV202SFljZklZNk5LZ09lNVNNRmI5Rl9RbTZxbHViZjZMSmxtQlVvRnBYTW5FQkx3d1NhcEdMNE81VFZQWXNNYXI0bEstSjltaTJBNzF4Y1Q1OEpmWmkwRUFDb09nLUE0M0RvY05GS2o0V2RLanhVejhFU2ZHYkkyanUxTFBMclpyaFhsMWVHM0lBcVphLTJYMDVHZV9VQVJYVng4VklNaEJCa3lUaUREbDNvPUEyRjE3QjA0")
				.pathParam("key", issueId).log().all()
				//.multiPart("file","bug.txt")
				.multiPart("file",new File("D:\\Git Projects\\Framework_API_RSA\\src\\test\\java\\jiraAPI\\Bug.png"))
				.post("rest/api/3/issue/{key}/attachments")
				.then().log().all().assertThat().statusCode(200);
				
				
				
		
		
	}

}
