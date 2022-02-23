package dataDriveTestingPackage;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	@Test
	public void postNewEmployee() {
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		
		// Here we created data we can send along with the post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name","Smith12wer");
		requestParams.put("salary","1231234");
		requestParams.put("age","75");
	
		//Add a header stating the request body is Json
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		//Post request and get the reqponse in the response object
		Response response = httpRequest.request(Method.POST,"/create");
		
		//Capture response body to perform validation
		String responseBodyString = response.getBody().asString();
		Assert.assertEquals(responseBodyString.contains("Smith12wer"),true);
		Assert.assertEquals(responseBodyString.contains("1231234"),true);
		Assert.assertEquals(responseBodyString.contains("75"),true);
		
		//validation of status code
		int responseString= response.getStatusCode();
		Assert.assertEquals(responseString,200);
			
	}
}
