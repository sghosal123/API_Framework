package dataDriveTestingPackage;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class dataDrivenTest_003 {
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
	public String[][] getEmpdata() throws IOException{
		
		//Read data from excel
		
		String pathString ="C:/Users/sghosal/eclipse-workspace/RestAssuredAPITesting/src/test/java/dataDriveTestingPackage/empData.xlsx";
		int rownum = XLutils.getRowCount(pathString,"Sheet1");
		int colnum = XLutils.getCellCount(pathString, "Sheet1",1);
		String[][] empdata = new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colnum;j++) {
				empdata[i-1][j]=XLutils.getCellData(pathString, "Sheet1", i, j);
									
			}
				
		}
		
		//String empdata[][]= {{"abc123","30000","40"},{"xyz123","40000","20"},{"pqr123","40000","20"}};		
		return empdata;
	}
	

}
