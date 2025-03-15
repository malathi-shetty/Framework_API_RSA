package complexJson_mockJson;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumOfAllCourses {

	// 6. Verify if Sum of all Course prices matches with Purchase Amount

	@Test
	public void sumOfCourses() {
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");

		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int amount = price * copies;
			System.out.println("Total amount of course copies: " + amount);

			sum = sum + amount;

		}
		System.out.println("Total sum of the all copies: " + sum);

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		// Assert.assertEquals(sum, 912); // AssertionError: expected [912] but found
		// [1162]
	}

}
