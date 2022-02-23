package testAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_Request_ExtractValuesOfEachNodes {

		@Test
		public void getJSONDetails() {

			// Specific base URI
			RestAssured.baseURI = "https://reqres.in/";
			// This is the request object
			RequestSpecification httprequest = RestAssured.given();

			Response response = httprequest.request(Method.GET, "/users/2");
			System.out.println(response);
			
			// Validating the values of the nodes
			JsonPath jsonPath = response.jsonPath();
			System.out.println(jsonPath.get("id"));
					
		}

}
