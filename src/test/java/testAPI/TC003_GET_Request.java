package testAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {

	@Test
	public void googlemapTest() {

		// Specific base URI
		RestAssured.baseURI = "https://maps.googleapis.com/";
		// This is the request object
		RequestSpecification httprequest = RestAssured.given();

		// Response object

		Response response = httprequest.request(Method.GET,
				"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

		// print response in console window

		String responseBody = response.getBody().asString();
		System.out.println("Response body is :" + responseBody);

		// Validating headers in response

		String contentTypeString = response.getHeader("Content-Type");
		Assert.assertEquals(contentTypeString, "application/xml; charset=UTF-8");
		System.out.println("The Content Type is:" + contentTypeString);

		String contentEncoding = response.getHeader("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
		System.out.println("The Content Encoding is:" + contentEncoding);

		Headers allheadersHeaders = response.headers();
		for (Header header : allheadersHeaders) {

			System.out.println(header.getName() + "      " + header.getValue());

		}

	}

}

