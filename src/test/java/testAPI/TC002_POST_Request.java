package testAPI;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {
	
	@Test
	public void registrationSuccessful() {

		// Specific base URI
		RestAssured.baseURI = "https://reqres.in/";
		// This is the request object
		RequestSpecification httprequest = RestAssured.given();

		
		//Request payload sending along with post request
		
		JSONObject requestParams=new JSONObject();
		requestParams.put("name","Sounak");
		requestParams.put("job","Leader");
		//requestParams.put("age","45");
				
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestParams.toJSONString());// Attac data to the request
		
		
		//Response object
		
		Response response = httprequest.request(Method.POST,"/api/users");
		
		// print response in console window
		
		String responseString = response.getBody().asString();
		System.out.println("The response body is:"+responseString);
		
		// Status code validation
		int statusCode=response.statusCode();
		System.out.println("The status code is:"+statusCode);	
		Assert.assertEquals(statusCode, 201);	
		
		// Validation of the response body
		String resbodymessage=response.jsonPath().getString("createdAt");
		String newValueString =resbodymessage.substring(0, 10);
		System.out.println(newValueString);
		System.out.println(getCurrentDate());	
		
		if(newValueString.contentEquals(getCurrentDate())) {
			Assert.assertTrue(true);
			
		}
		else {
			Assert.assertTrue(false);
		}
				
		}
	
		public String getCurrentDate() {
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			Date date = new Date();
			return(formatter.format(date));
			
	
	}
}