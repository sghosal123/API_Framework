package dataDriveTestingPackage;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class dataDrivenTest_002 {
	@Test(dataProvider = "EmpDataProvider")
	void postNewEmployees(String eName,String eSalary, String eAge) {
		
			
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
			RequestSpecification httpRequest = RestAssured.given();
			
			// Here we created data we can send along with the post request
			JSONObject requestParams = new JSONObject();
			requestParams.put("name",eName);
			requestParams.put("salary",eSalary);
			requestParams.put("age",eAge);
		
			//Add a header stating the request body is Json
			httpRequest.header("Content-Type","application/json");
			httpRequest.body(requestParams.toJSONString());
			
			//Post request and get the reqponse in the response object
			Response response = httpRequest.request(Method.POST,"/create");
			
			//Capture response body to perform validation
			String responseBodyString = response.getBody().asString();
			Assert.assertEquals(responseBodyString.contains(eName),true);
			Assert.assertEquals(responseBodyString.contains(eSalary),true);
			Assert.assertEquals(responseBodyString.contains(eAge),true);
			
			//validation of status code
			int responseString= response.getStatusCode();
			Assert.assertEquals(responseString,200);
				
		}
		
	
	@DataProvider(name="EmpDataProvider")
	public String[][] getEmpdata(){
		String empdata[][]= {{"abc123","30000","40"},{"xyz123","40000","20"},{"pqr123","40000","20"}};		
		return empdata;
	}
	

}
