package complexJson_mockJson;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// Check File Mock_json_Used & Payload

		JsonPath js = new JsonPath(Payload.CoursePrice());// successfully mocked the response when your Pojo_API is not
															// ready

		// 1. Print No of courses returned by Pojo_API
		int count = js.getInt("courses.size()");// can be applied only on arrays
		System.out.println("Pojo_Courses Size: " + count);

		// 2. Print Purchase Amount
		System.out.println("");
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase Amount: " + totalAmount); // 252 + 450 + 160 + 300 = 1162 --> price * copies
																		// of courses

		// 3. Print Title of the first course
		System.out.println("");
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println("Title of the first course: " + titleFirstCourse);

		String titlethirdCourse = js.get("courses[2].title");
		System.out.println("Title of the third course: " + titlethirdCourse);

		// 4. Print All course titles and their respective Prices
		System.out.println("");
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");// +i+ is used to dynamically insert the value of
																		// the variable i into the string.
			System.out.println(courseTitles);

			// System.out.println(js.get("courses["+i+"].price")); //this statemet wud throw
			// error so we dnt know return type so to avoid we will just add .toString() so
			// that the return type would be in string format

			System.out.println(js.get("courses[" + i + "].price").toString());

			// System.out.println(courseTitles + ": " + js.get("courses[" + i +
			// "].price").toString());
		}

		// 5. Print no of copies sold by RPA Course

		System.out.println("");
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			if (courseTitles.equalsIgnoreCase("RPA")) {
				// return copies sold
				int copies = js.get("courses[" + i + "].copies");
				System.out.println("Print no of copies sold by RPA Course: " + copies);
				break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount

		// Check in SumOfAllCourses

	}

}
