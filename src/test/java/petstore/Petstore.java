package petstore;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Petstore {
	//HashMap map = new HashMap();
	
	@Test
	public void test_findByStatus() {
		
		//map.put(id, 15435006001706);
		
		given()
			.contentType("application/json")
		 .when()
		     .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
		 
		 .then()
		 	 .statusCode(200)
		 	 .log().body();
			 
		
	}

}
