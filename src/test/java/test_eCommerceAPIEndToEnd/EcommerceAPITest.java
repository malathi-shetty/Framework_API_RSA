package test_eCommerceAPIEndToEnd;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import test_eCommerceAPIEndToEnd_pojo.Login_Request;
import test_eCommerceAPIEndToEnd_pojo.Login_Response;
import test_eCommerceAPIEndToEnd_pojo.Orders;
import test_eCommerceAPIEndToEnd_pojo.Orders_Detail;

public class EcommerceAPITest {

	public static void main(String[] args) {

		// Create Login

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		// Create the request object
		Login_Request reqlogin = new Login_Request();
		reqlogin.setUserEmail("postmanpractice123@gmail.com");
		reqlogin.setUserPassword("Hello123@");

		RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(reqlogin);

		// Send the request and capture the response
		Login_Response reslogin = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(Login_Response.class);

		// Print the response
		System.out.println(reslogin.getToken());
		String token = reslogin.getToken();
		System.out.println(reslogin.getUserId());
		String userId = reslogin.getUserId();
		System.out.println(reslogin.getMessage());

		// Add Product

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();

		RequestSpecification reqAddProduct = 	given().log().all().spec(addProductBaseReq).param("productName", "Wallpaper").param("productAddedBy", userId)
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Addias Originals").param("productFor", "women")
				.multiPart("productImage", new File("D://Git Projects//Framework_API_RSA//src//test//java//test_eCommerceAPIEndToEnd//laptop.jpg"));
		
		
	String addProductResponse	= reqAddProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asPrettyString();
	
	JsonPath js = new JsonPath(addProductResponse);
	
	String productId = js.get("productId");
	
	//Create Order
	
	RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token)
			.setContentType(ContentType.JSON)
			.build();
	
	Orders_Detail ordersDetail = new Orders_Detail();
	ordersDetail.setCountry("India");
	ordersDetail.setProductOrderedId(productId);
	
	List<Orders_Detail> ordersDetailList = new ArrayList();
	ordersDetailList.add(ordersDetail);
	
	Orders orders = new Orders();
	orders.setOrders(ordersDetailList);
	
	RequestSpecification reqCreateOrder = 	given().log().all().spec(createOrderBaseReq)
			.body(orders);

	String ResponseAddOrder = reqCreateOrder.when().post("/api/ecom/order/create-order")
	.then().log().all().extract().response().asPrettyString();
	
	System.out.println(ResponseAddOrder);
	
	
	// Delete Product
	
	RequestSpecification deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token)
			.setContentType(ContentType.JSON)
			.build();
	
	RequestSpecification deleteProductReq =	given().log().all().spec(deleteProductBaseReq).pathParam("productId", productId);
	
String deleteProductResponse =	deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
	.then().log().all().extract().response().asPrettyString();

JsonPath js1 = new JsonPath(deleteProductResponse);

 Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
	
	}
}

/*
 * 
 * Note: .setContentType(ContentType.JSON) is written when body has Json content on it
 * 
 * relaxedHTTPSValidation(): will bypass SSl certificates
 * 
 * RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(reqlogin);
 * 
 */