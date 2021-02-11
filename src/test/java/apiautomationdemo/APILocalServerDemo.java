

package apiautomationdemo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.junit.Assert;

public class APILocalServerDemo {
	
	//@Test
	public void test_GETRequest() {
		
		baseURI = "http://localhost:3000/";
		
		given()
			.header("Content Type","application/json")
			.accept(ContentType.JSON).
		when()
			.get("/users/1").
		then()
			.statusCode(200)
			.body("firstName", equalTo("Rohit"));	
	}
	
	
	@Test
	public void test_GETRequestValidation() {
		
		baseURI = "http://localhost:3000/";
		
		Response res = given().get("/users/1");
		
		ResponseBody body = res.getBody();
		
		String bodyValue = body.asString();
		System.out.println("BodyValue"+bodyValue);
		
		Assert.assertTrue(bodyValue.contains("Virat Kohli"));
		
		JsonPath jsonPathEvaluators = res.jsonPath();
		
		String fname = jsonPathEvaluators.get("firstName");
		String lname = jsonPathEvaluators.get("lastName");
		
		Assert.assertTrue(fname.equals("Rohit"));
		Assert.assertTrue(lname.equals("Shukla"));
	}
	
	//@Test
	public void test_POSTRequest() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject req = new JSONObject();
		
		req.put("firstName", "Rahul");
		req.put("lastName", "Maurya");
		req.put("fcricketer", "Sachin Tendulkar");
		
		
		given()
			.header("Content-Type", "application/json")
		
			
			.accept(ContentType.JSON)
			.body(req.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log().all();
			
	}
	
	//@Test
	public void test_PUTRequest() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject req = new JSONObject();
		
		req.put("firstName", "Akshay");
		req.put("lastName", "Mhatre");
		//req.put("fcricketer", "Sachin Tendulkar");
		
		
		given()
			.header("Content-Type", "application/json")
			.accept(ContentType.JSON)
			.body(req.toJSONString())
		.when()
			.put("/users/6")
		.then()
			.statusCode(200)
			.log().all();
			
	}
	
	//@Test
	public void test_PATCHRequest() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject req = new JSONObject();
		
		//req.put("firstName", "Akshay");
		//req.put("lastName", "Mhatre");
		req.put("fcricketer", "Sachin Tendulkar");
		
		given()
			.header("Content-Type", "application/json")
			.accept(ContentType.JSON)
			.body(req.toJSONString())
		.when()
			.patch("/users/6")
		.then()
			.statusCode(200)
			.log().all();
			
	}
	
	//@Test
	public void test_DELETERequest() {
		baseURI = "http://localhost:3000/";
		
		given()
		.when()
			.delete("/users/6")
		.then()
			.log().all();
	}

}
