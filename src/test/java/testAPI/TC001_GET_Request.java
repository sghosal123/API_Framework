package testAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	@Test
	public void getweatherDetails() {

		// Specific base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		// This is the request object
		RequestSpecification httprequest = RestAssured.given();

		// Response object

		Response response = httprequest.request(Method.GET, "/employees");

		// print response in console window

		String responseBody = response.getBody().asString();
		System.out.println("Response body is :" + responseBody);
		
		//Status code validation
		int statusCode=response.getStatusCode();		
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status line verification
		String statusLine=response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
	}

}

