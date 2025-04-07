package oAuth2_0;

import static io.restassured.RestAssured.*;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuth_2_0_pending_due_to_gmail_restriction {

	public static void main(String[] args) throws InterruptedException {
/*due_to_gmail_restriction Selenium part is ruled out
 * 
 * First login in 
 * 
 * https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
 * 
 * then u wud be redircted to url same as mentioned below in String url 
 * 
 * 
 * 
 */
		/*
		 * // Set Chrome options for headless mode ChromeOptions options = new
		 * ChromeOptions(); options.addArguments("--headless");
		 * options.addArguments("--disable-gpu");
		 * options.addArguments("--window-size=1920x1080");
		 * options.addArguments("--disable-logging"); // Disable logging to avoid
		 * warnings options.setCapability("goog:loggingPrefs", new HashMap<String,
		 * String>() {{ put("browser", "ALL"); }});
		 * 
		 * 
		 * WebDriver driver = new ChromeDriver(options);
		
	//	WebDriver driver = new ChromeDriver();
	//	WebDriver driver = new FirefoxDriver();
		WebDriver driver = new EdgeDriver();

	
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");

		Thread.sleep(1000);
		System.out.println("sending text on email");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("automationtestingpractice8443");
		//driver.findElement(By.cssSelector("input[type='email']")).sendKeys("working email"); // need actual email
		Thread.sleep(1000);
		// driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		// driver.findElement(By.xpath("//span[text()='Next']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
		nextButton.click();

		System.out.println("next button clicked on email");

		// driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); //
		// Increase page load timeout

		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement passwordField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
		System.out.println("found password field");
		// passwordField.sendKeys("password"); // --> need actual password

		

		System.out.println("password entered");
		// Add a wait to ensure password input field is visible and interactable
		// wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		// WebElement passwordField =
		// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));

		// passwordField.sendKeys("SecurePassword1234");
		// passwordField.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		System.out.println("next button clicked on password");
		// driver.findElement(By.cssSelector("input[type='password']")).sendKeys("SecurePassword1234");
		// driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

	//	String url = driver.getCurrentUrl();
	*/	
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AQSTgQEfeNzOmXL_VzwJs477l6unZd1P2nx4tEZoP1Dap4yo0PVr7E9tASNPSXZVuX_q3Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		
		String partialCode = url.split("code=")[1]; // returns an array
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		

		Response accessTokenResponse = given().urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token");

		  // Print the access token response
        String accessTokenResponseBody = accessTokenResponse.asString();
		System.out.println("accessTokenResponse: " + accessTokenResponse);
		
		int statusCode = given().urlEncodingEnabled(false)
		        .queryParam("code", code)
		        .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		        .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		        .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		        .queryParam("grant_type", "authorization_code")
		        .when().log().all()
		        .post("https://www.googleapis.com/oauth2/v4/token")
		        .statusCode();

		String statusLine = given().urlEncodingEnabled(false)
		        .queryParam("code", code)
		        .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		        .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		        .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		        .queryParam("grant_type", "authorization_code")
		        .when().log().all()
		        .post("https://www.googleapis.com/oauth2/v4/token")
		        .statusLine();

		System.out.println("Access Token Response Status Code: " + statusCode);
		System.out.println("Access Token Response Status Line: " + statusLine);
		
		JsonPath js = new JsonPath(accessTokenResponseBody);
		String accessToken = js.getString("access_token");

		Response response = given().queryParam("access_token", "accessToken").when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php");

		// Print the response from the second request
        String responseBody = response.asString();
		System.out.println("Response: " + responseBody);
		
		int responseStatusCode = response.statusCode();
		String responseStatusLine = response.statusLine();

		System.out.println("Response Status Code: " + responseStatusCode);
		System.out.println("Response Status Line: " + responseStatusLine);
	}

}
