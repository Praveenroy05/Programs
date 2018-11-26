package Com.MBS.MBSAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import Com.MBS.MBSConstants.AdminAPIConstants;
import Com.MBS.MBSConstants.PortalAPIConstants;
import Com.MBSWrapper.BaseTest;
import Com.MBSWrapper.DatabaseConnection;
import Com.MBSWrapper.MBSWrapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class MBSAdminAPITests extends BaseTest
{
	DatabaseConnection dc =new DatabaseConnection();
	@Test(enabled=true)
	public void allhotellist() throws IOException
	{
		try {
		String body="{\r\n" + 
				"	\"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"	\"start_index\": \"0\",\r\n" + 
				"	\"size\": \"15\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/admin/view/allhotellist")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
			
	}
	
	@Test(enabled=true)
	public void updateHotel() throws IOException, ParseException
	{
		
		long StartDate=new Date().getTime();
		long EndDate=MBSWrapper.getNextOneYearTimeStamp();
		try {
		String body="{\r\n" + 
				"\"session_id\":\""+logindetails.get(0)+"\",\r\n" + 
				"\"hotel_code\": \""+MBSWrapper.getObject("DefaultHotelcode")+"\",\r\n" + 
				"\"product_type\": \"BOOK\",\r\n" + 
				"\"ta_hotel_id\": \""+MBSWrapper.getObject("Default_ta_hotel_id")+"\",\r\n" + 
				"  \"booking_engine\":\""+MBSWrapper.randamString(PortalAPIConstants.booking_enginesCodes)+"\",\r\n" + 
				"  \"start_date_time\" :\""+StartDate+"\",\r\n" + 
				" \"expiry_date_time\": \""+EndDate+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/admin/update/hotel")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(enabled=true,dependsOnMethods= {"updateHotel"})
	public void deactivatehotel() throws IOException
	{
		
		try {
		String body="{\r\n" + 
				"	\"session_id\":\""+logindetails.get(0)+"\",\r\n" + 
				"	\"hotel_codes\":[\""+MBSWrapper.getObject("DefaultHotelcode")+"\"]\r\n" + 
				"	\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		Thread.sleep(7000);
		given().body(body)
		.when().post("/admin/update/deactivatehotel")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
			
	}
		
	
	@Test(enabled=true,dependsOnMethods= {"deactivatehotel"})
	public void activatehotel() throws IOException
	{
		try {
		String body="{\r\n" + 
				"	\"session_id\":\""+logindetails.get(0)+"\",\r\n" + 
				"	\"hotel_codes\":[\""+MBSWrapper.getObject("DefaultHotelcode")+"\"]\r\n" + 
				"	\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		Thread.sleep(7000);
		given().body(body)
		.when().post("/admin/update/activatehotel")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
			
	}
	
	@Test(enabled=true)
	public void allUsersList() throws IOException
	{
		try {
		String body="{\r\n" + 
				"  \"session_id\":\""+logindetails.get(0)+"\",\r\n" + 
				"  \"start_index\": \"0\",\r\n" + 
				" \"size\": \"100\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/admin/view/alluserslist")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	@Test(enabled=true)
	public void widgetScriptEmail() throws IOException
	{
		String email="anil.sikhakolli@traveltrippercom,snandigam@traveltripper.com,cbacham@traveltripper.com ";
		try {
		String body="{\r\n" + 
				"  \"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"  \"hotel_code\": \""+MBSWrapper.getObject("DefaultHotelcode")+"\",\r\n" + 
				"  \"email\":\""+email+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/view/widgetscript")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(enabled=true)
	public void widgetScriptInvalidEmail() throws IOException
	{
		String email=MBSWrapper.randamString(AdminAPIConstants.Emails);
		String emailid=email.replace("@", " ");
		try {
		String body="{\r\n" + 
				"  \"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"  \"hotel_code\": \""+MBSWrapper.getObject("DefaultHotelcode")+"\",\r\n" + 
				"  \"email\":\""+emailid+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/view/widgetscript")
		.then().log().all().body("success", equalTo(false))
		.and()
		.body("error_message", equalTo("Please Enter a valid email address."));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(enabled=true)
	public void allHotelGeoCondition() throws IOException
	{
		try {
		String body="{\r\n" + 
				"  \"session_id\":\""+logindetails.get(0)+" \"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/admin/view/allhotelgeocondition")
		.then().log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(enabled=true,dependsOnMethods= {"allhotellist","updateHotel","deactivatehotel","activatehotel","allUsersList","widgetScriptEmail","widgetScriptInvalidEmail","allHotelGeoCondition"})
	public void updateUserDetails() throws IOException, InterruptedException
	{
		String user="anil.sikhakolli@traveltripper.com";
		String firstName=dc.getColumnData("select * from users where username='"+user+"'","firstName");
		String lastName=dc.getColumnData("select * from users where username='"+user+"'", "lastName");
		String customerId=dc.getColumnData("select * from users where username='"+user+"'", "customerId");
		String email=dc.getColumnData("select * from users where username='"+user+"'", "email");
		String phone=dc.getColumnData("select * from users where username='"+user+"'", "phone");
		System.out.println(firstName+","+lastName+","+customerId+","+email+","+phone);
		//dc.printColumns("select * from users where username='"+RateMatchWrapper.getObject("Username")+"'");
		String body="{\r\n" + 
				"  \"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"  \"customer_id\": "+Integer.parseInt(customerId)+",\r\n" + 
				"  \"email_address\": \""+email+"\",\r\n" + 
				"  \"first_name\": \"sandeep\",\r\n" + 
				"  \"last_name\": \"nandigam\",\r\n" + 
				"  \"phone_number\": \""+phone+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		try {
		given().body(body)
		.when().post("/admin/update/updateuserdetails")
		.then().statusCode(200).log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	
	@Test(enabled=true,dependsOnMethods= {"updateUserDetails"})
	public void RevertingupdatedUserDetails() throws IOException, InterruptedException
	{
		String user="anil.sikhakolli@traveltripper.com";
		String firstName=dc.getColumnData("select * from users where username='"+user+"'","firstName");
		String lastName=dc.getColumnData("select * from users where username='"+user+"'", "lastName");
		String customerId=dc.getColumnData("select * from users where username='"+user+"'", "customerId");
		String email=dc.getColumnData("select * from users where username='"+user+"'", "email");
		String phone=dc.getColumnData("select * from users where username='"+user+"'", "phone");
		System.out.println(firstName+","+lastName+","+customerId+","+email+","+phone);
		String body="{\r\n" + 
				"  \"session_id\": \""+logindetails.get(0)+"\",\r\n" + 
				"  \"customer_id\": "+Integer.parseInt(customerId)+",\r\n" + 
				"  \"email_address\": \""+email+"\",\r\n" + 
				"  \"first_name\": \"Anil\",\r\n" + 
				"  \"last_name\": \"Kumar\",\r\n" + 
				"  \"phone_number\": \""+phone+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		try {
		given().body(body)
		.when().post("/admin/update/updateuserdetails")
		.then().statusCode(200).log().all().body("success", equalTo(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
