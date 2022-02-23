package testAPI;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_Request_Validating_JSONResponse {

	@Test
	public void getJSONDetails() {

		// Specific base URI
		RestAssured.baseURI = "https://reqres.in/";
		// This is the request object
		RequestSpecification httprequest = RestAssured.given();

		// Request payload sending along with post request

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Sounak");
		requestParams.put("job", "Leader");
		// requestParams.put("age","45");

		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestParams.toJSONString());// Attac data to the request

		// Response object

		Response response = httprequest.request(Method.POST, "/api/users");

		// print response in console window

		String responseString = response.getBody().asString();
		System.out.println("The response body is:" + responseString);
	
		// validating that the response body contains Sounak
		
			Assert.assertEquals(responseString.contains("Sounak"),true);
		
	}
}