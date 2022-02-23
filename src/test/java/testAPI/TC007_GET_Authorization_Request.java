package testAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC007_GET_Authorization_Request {
	@Test
	public void getAuthorizationRequest() {
			
				// Specific base URI
				RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
				
				//Basic Authentication
				PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
				authScheme.setUserName("ToolsQA");
				authScheme.setPassword("TestPassword");
				RestAssured.authentication = authScheme;
				// This is the request object
							
				RequestSpecification httprequest = RestAssured.given();

					
				// Response object

				Response response = httprequest.request(Method.GET, "/");
		
				
				//Status code validation
				int statusCode=response.getStatusCode();		
				System.out.println(statusCode);
				Assert.assertEquals(statusCode, 200);
				
				// print response in console window

				String responseBody = response.getBody().asString();
				System.out.println("Response body is :" + responseBody);

				
			}
		
	}
	


