package Com.MBS.MBSAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Com.MBS.MBSBody.LoginBody;
import Com.MBS.MBSConstants.PortalAPIConstants;
import Com.MBSWrapper.BaseTest;
import Com.MBSWrapper.DatabaseConnection;
import Com.MBSWrapper.MBSWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class MBSPortalAPITests extends BaseTest
{
//	@BeforeClass
//	public void setup() throws IOException
//	{ 
//		String URI=RateMatchWrapper.getURI();
//		RestAssured.baseURI=URI;
//		
//	}
	DatabaseConnection dc=new DatabaseConnection();
	@Test(enabled=false,priority=4)
	public void getbookingenginbodyverify()
	{
		/*List<String>available_booking_enginesCodes=MBSWrapper.getBookingEngineCodes();
		List<String>available_booking_enginesNames=MBSWrapper.getBookingEngineNames();*/
		try {
		//Response res=given().when().get("/portal/availablebookingengines");
		given()
		.when().get("/propertyInfo/mbsqa1?buster=1542091129102")
		.then().statusCode(200);
	/*	.and()
		.body(available_booking_enginesCodes.get(0), equalTo(PortalAPIConstants.booking_enginesCodes[0]))
		.and()
		.body(available_booking_enginesCodes.get(1), equalTo(PortalAPIConstants.booking_enginesCodes[1]))
		.and()
		.body(available_booking_enginesCodes.get(2), equalTo(PortalAPIConstants.booking_enginesCodes[2]))
		.and()
		.body(available_booking_enginesCodes.get(3), equalTo(PortalAPIConstants.booking_enginesCodes[3]))
		.and()
		.body(available_booking_enginesCodes.get(4), equalTo(PortalAPIConstants.booking_enginesCodes[4]))
		.and()
		.body(available_booking_enginesCodes.get(5), equalTo(PortalAPIConstants.booking_enginesCodes[5]))
		.and()
		.body(available_booking_enginesCodes.get(6), equalTo(PortalAPIConstants.booking_enginesCodes[6]))
		.and()
		.body(available_booking_enginesCodes.get(7), equalTo(PortalAPIConstants.booking_enginesCodes[7]))
		.and()
		.body(available_booking_enginesNames.get(0), equalTo(PortalAPIConstants.booking_enginesNames[0]))
		.and()
		.body(available_booking_enginesNames.get(1), equalTo(PortalAPIConstants.booking_enginesNames[1]))
		.and()
		.body(available_booking_enginesNames.get(2), equalTo(PortalAPIConstants.booking_enginesNames[2]))
		.and()
		.body(available_booking_enginesNames.get(3), equalTo(PortalAPIConstants.booking_enginesNames[3]))
		.and()
		.body(available_booking_enginesNames.get(4), equalTo(PortalAPIConstants.booking_enginesNames[4]))
		.and()
		.body(available_booking_enginesNames.get(5), equalTo(PortalAPIConstants.booking_enginesNames[5]))
		.and()
		.body(available_booking_enginesNames.get(6), equalTo(PortalAPIConstants.booking_enginesNames[6]))
		.and()
		.body(available_booking_enginesNames.get(7), equalTo(PortalAPIConstants.booking_enginesNames[7]))
		.contentType(ContentType.JSON);*/
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		//System.out.println(res.body().prettyPrint());
			
	}
	
//	@Test(enabled=false)
//	public void login()
//	{
//		RestAssured.registerParser("text/plain", Parser.JSON);
//		given().body("{\r\n" + 
//				"        \"username\" : \"snandigam@traveltripper.com\",\r\n" + 
//				"        \"password\" : \"travel123\",\r\n" + 
//				"        \"force_new_session\" : true\r\n" + 
//				"}")
//		.when().post("/portal/login")
//		.then().log().all().body("success", equalTo(true));
//			
//	}
	
//	@Test(enabled=false)
//	public void login1() throws IOException
//	{
//		String body="{\r\n" + 
//				"        \"username\" : \""+RateMatchWrapper.getObject("Username")+"\",\r\n" + 
//				"        \"password\" : \""+RateMatchWrapper.getObject("Password")+"\",\r\n" + 
//				"        \"force_new_session\" : true\r\n" + 
//				"}";
//		RestAssured.registerParser("text/plain", Parser.JSON);
//		given().body(body)
//		.when().post("/portal/login")
//		.then().log().all().body("success", equalTo(true));
//			
//	}
	
//	@Test(enabled=false)
//	public void login2() throws IOException
//	{
//		LoginBody login=new LoginBody();
//		login.setUsername(RateMatchWrapper.getObject("Username"));
//		login.setPassword(RateMatchWrapper.getObject("Password"));
//		login.setForce_new_session(true);
////		String body="{\r\n" + 
////				"        \"username\" : \""+RateMatchWrapper.getObject("Username")+"\",\r\n" + 
////				"        \"password\" : \""+RateMatchWrapper.getObject("Password")+"\",\r\n" + 
////				"        \"force_new_session\" : true\r\n" + 
////				"}";
//		RestAssured.registerParser("text/plain", Parser.JSON);
//		given().body(login)
//		.when().post("/portal/login")
//		.then().log().all().body("success", equalTo(true));
//			
//	}

	@Test(enabled=false,dependsOnMethods= {"updateStyleConfig"},priority=2)
	public void styleconfig() throws IOException
	{
		try {
		String body="{\r\n" + 
				"\"session_id\" : \""+logindetails.get(0)+"\",\r\n" + 
				"\"hotel_code\" : \""+logindetails.get(1)+"\"\r\n"+"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/view/stylingconfig")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	//Note:In Styling SECONDARY BACKGROUND COLOR is not updating properly check with developers
	@Test(enabled=true,priority=1)
	public void updateStyleConfig() throws IOException
	{
		List<String>primary_background_color=MBSWrapper.getRandomNumber(2);
		List<String>background_color=MBSWrapper.getRandomNumber(2);
		String Selectfont=MBSWrapper.randamString(PortalAPIConstants.standedAndGooglefonts);
		String Widgetdirection=MBSWrapper.randamString(PortalAPIConstants.widgetDirection);
		String body="{\r\n" + 
				"\"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"\"hotel_code\": \""+logindetails.get(1)+"\",\r\n" + 
				"\"primary_background_color\" : \"rgb("+primary_background_color.get(0)+","+primary_background_color.get(1)+","+primary_background_color.get(2)+")\",\r\n" + 
				"\"secondary_background_color\" : \"rgb("+background_color.get(0)+","+background_color.get(1)+","+background_color.get(2)+")\",\r\n" + 
				"\"book_button_color\" : \"rgb("+primary_background_color.get(0)+","+primary_background_color.get(1)+","+background_color.get(2)+")\",\r\n" + 
				"\"selected_font\" : \""+Selectfont+"\",\r\n" + 
				"\"widget_direction\":\""+Widgetdirection+"\",\r\n" + 
				"\"available_fonts\": [\r\n" + 
				"{\"google\": [\"Lato\", \"Playfair Display\", \"Sources Sans Pro\", \"Ubuntu\"\r\n],\r\n" + 
				"\"standard\": [\"Helvetica/Arial\", \"Trebuchet MS\", \"Georgia\", \"Courier New\", \"Times New Roman\"]\r\n" + 
				"}\r\n" + 
				"]\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		try {
	    
		given().body(body)
		.when().post("/portal/update/stylingconfig")
		.then().log().all().body("success", equalTo(true));
	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	
	@Test(enabled=false,priority=3)
	public void reservationReport() throws IOException, ParseException
	{
		long bookingdate=MBSWrapper.getTimeStamp("09-06-2018 07:30:48", "MM-dd-yyyy hh:mm:ss");
		try {
		String body="{\r\n" + 
				" \"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				" \"hotel_code\":\""+MBSWrapper.getObject("DefaultHotelcode")+"\",\r\n" + 
				" \"booking_start_date\": \""+bookingdate+"\",\r\n" + 
				" \"booking_end_date\": \"\",\r\n" + 
				" \"arrival_start_date\": \"\",\r\n" + 
				" \"arrival_end_date\": \"\",\r\n" + 
				" \"departure_start_date\": \"\",\r\n" + 
				" \"departure_end_date\": \"\",\r\n" + 
				" \"search_name\" : \"\", \r\n" + 
				" \"start_index\": 0,\r\n" + 
				" \"size\": 50\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/reports/reservation")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	
	@Test(enabled=false,priority=3)
	public void signup() throws IOException, ParseException
	{
		int No=MBSWrapper.getRandomNumber(0,1000);
		String no=Integer.toString(No);
		try {
		String body="{\r\n" + 
				"  \"first_name\": \"test"+no+"\",\r\n" + 
				"  \"last_name\": \"test"+no+"\",\r\n" + 
				"  \"email_address\": \"test"+no+"@gmail.com\",\r\n" + 
				"  \"phone_number\": \"9999999999\",\r\n" + 
				"  \"multiple_hotels\": true,\r\n" + 
				"  \"product_type\": \"PRICE COMPARE\",\r\n" + 
				"  \"city\": \"Culiacán Rosales"+no+"\",\r\n" + 
				"  \"country\": \"Mexico"+no+"\",\r\n" + 
				"  \"hotel_name\": \"knickerbocker"+no+"\",\r\n" + 
				"  \"hotel_address\" : \"Address1\",\r\n" + 
				"  \"hotel_website\" : \"www.google.com\",\r\n" + 
				"  \"booking_engine\" : \"reztrip\",\r\n" + 
				"  \"longitude\": \"-73.98627\",\r\n" + 
				"  \"latitude\": \"40.755580\",\r\n" + 
				"  \"ta_id\":\"12321\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/signup")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
		
}
