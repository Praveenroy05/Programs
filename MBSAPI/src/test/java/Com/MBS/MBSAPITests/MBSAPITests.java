package Com.MBS.MBSAPITests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Com.MBSWrapper.MBSWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class MBSAPITests {	
	
//	public List<String> logindetails;
//	@BeforeClass
//	public void setup() throws IOException
//	{ 
//		String URI=RateMatchWrapper.getURI();
//		RestAssured.baseURI=URI;
////		String body="{\r\n" + 
////				"        \"username\" : \""+RateMatchWrapper.getObject("Username")+"\",\r\n" + 
////				"        \"password\" : \""+RateMatchWrapper.getObject("Password")+"\",\r\n" + 
////				"        \"force_new_session\" : true\r\n" + 
////				"}";
////		RestAssured.registerParser("text/plain", Parser.JSON);
////		given().body(body)
////		.when().post("/portal/login")
////		.then().log().all().body("success", equalTo(true));
//		logindetails=RateMatchWrapper.loginsuccessdetails();
//		
//	}
	@BeforeClass
	public void setup() throws IOException
	{ 
		String URI=MBSWrapper.getURI();
		RestAssured.baseURI=URI;
		
	}
	@Test(enabled=false)
	public void getbookingenginsupport()
	{
		
		given().when().get("/portal/availablebookingengines").then().statusCode(200);
		
	}
	
	@Test (enabled=false)
	public void getbookingenginsupportbody()
	{
		
		Response res=given().when().get("/portal/availablebookingengines");
		//System.out.println(res.body().asString());
		System.out.println(res.body().prettyPrint());
		
	}
	
	@Test(enabled=true)
	public void getbookingenginbodyverify()
	{
		ArrayList<String> available_booking_engines=new ArrayList<String>();
		for(int i=0;i<=7;i++)
		{
			//String booking_engines_paths=RateMatchPortalAPITests.available_booking_engines;
			//String booking_engines_path=booking_engines_paths.replace("[0]", "["+i+"]");
			//available_booking_engines=available_booking_engines.add(booking_engines_path);
		}
		Response res=given().when().get("/portal/availablebookingengines");
		given()
		.when().get("/portal/availablebookingengines")
		.then().statusCode(200)
		.and()
		.body("available_booking_engines[0].booking_engine_code", equalTo("classic.tt"))
		.and()
		.contentType(ContentType.JSON);
		System.out.println(res.body().prettyPrint());
		//System.out.println(res.body().asString());
		//System.out.println(res.body().prettyPrint());
		
	}
	
	public static List loginsuccessdetails() throws IOException {
		ArrayList<String> available_booking_enginesNames = new ArrayList<String>();
		String body="{\r\n" + 
				"        \"username\" : \""+MBSWrapper.getObject("Username")+"\",\r\n" + 
				"        \"password\" : \""+MBSWrapper.getObject("Password")+"\",\r\n" + 
				"        \"force_new_session\" : true\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		Response response=given().body(body)
		.when().post("/portal/login")
		.then().statusCode(200).extract().response();
		
		String sessionid=response.path("session_id");
		String hotel_code=response.path("hotel_code");
		String user_role=response.path("SU");
		//boolean multiple_hotels=response.path("multiple_hotels");
		available_booking_enginesNames.add(sessionid);
		available_booking_enginesNames.add(hotel_code);
		available_booking_enginesNames.add(user_role);
		//available_booking_enginesNames.add(multiple_hotels);
		return available_booking_enginesNames;
	}

}
