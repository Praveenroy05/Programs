package Com.MBSWrapper;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest 
{
	public List<String> logindetails;
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{ 
		String URI=MBSWrapper.getURI();
		RestAssured.baseURI=URI;
		//logindetails=MBSWrapper.loginsuccessdetails();
	}

	    public RequestSpecification getRequestSpecification() {
	        return RestAssured.given().contentType(ContentType.JSON);
	    }

	    public Response getResponse(RequestSpecification requestSpecification,String endpoint, int
	                            status){
	        Response response = requestSpecification.get(endpoint);
	        Assert.assertEquals(response.getStatusCode(),status);
	        response.then().log().all();
	        return response;
	    }

	
	@AfterClass
	public void logout() throws IOException, InterruptedException
	{
		
			
	}

}
