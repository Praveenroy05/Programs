package Com.MBS.MBSAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import Com.MBSWrapper.BaseTest;
import Com.MBSWrapper.DatabaseConnection;
import Com.MBSWrapper.MBSWrapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class PasswordReset extends BaseTest {
	DatabaseConnection dc=new DatabaseConnection();
	@Test(enabled=true)
	public void forgotPassword() throws IOException
	{
		String body="{\r\n" + 
				    "\"username\" : \""+MBSWrapper.getObject("Username")+"\"\r\n" + 
				    "}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		try {
		given().body(body)
		.when().post("/portal/forgotpassword")
		.then().statusCode(200).log().all().body("success", equalTo(true))
		.and()
		.body("error_message",equalTo("Email sent with instructions on how to reset your password."));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	
	@Test(enabled=true,dependsOnMethods= {"forgotPassword"})
	public void changePassword() throws IOException
	{
		String reset_password_token=dc.getColumnData("select * from users where username='"+MBSWrapper.getObject("Username")+"'", "resetPasswordToken");
		String body="{\r\n" + 
				"  \"new_password\": \"travel123\",\r\n" + 
				"  \"confirm_password\": \"travel123\",\r\n" + 
				"  \"reset_password_token\": \""+reset_password_token+"\"\r\n" + 
				"}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		try {
		given().body(body)
		.when().post("/portal/changepassword")
		.then().statusCode(200).log().all().body("success", equalTo(true))
		.and()
		.body("error_message",equalTo("Your password has been changed successfully."));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	

}
