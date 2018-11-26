package Com.MBSWrapper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;


import Com.MBS.MBSAPITests.MBSPortalAPITests;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class MBSWrapper {
	static Properties properties;
	public static ArrayList<String> available_booking_enginesNames = new ArrayList<String>();
	public static ArrayList<String> available_booking_rooms = new ArrayList<String>();
	static Random random=new Random();
	public static void loaddata() throws IOException {
		properties = new Properties();
		File f = new File(System.getProperty("user.dir") + "\\Admin.properties");
		FileReader fr = new FileReader(f);
		properties.load(fr);
	}

	public static String getURI() throws IOException {
		String URI = "";
		String[] uri = { "BaseURI", "Environment", "Product" };
		loaddata();
		for (String objectdata : uri) {
			String objectvalue = properties.getProperty(objectdata);
			URI = URI + objectvalue;

		}
		return URI;

	}

	public static String getObject(String object) throws IOException {
		loaddata();
		String objectvalue = properties.getProperty(object);
		return objectvalue;

	}

	/*public static List getBookingEngineCodes() {
		ArrayList<String> available_booking_engines = new ArrayList<String>();
		for (int i = 0; i <= 7; i++) {
			String booking_engines_paths = PortalAPIpathExpressions.available_booking_engines;
			String booking_engines_path = booking_engines_paths.replace("[0]", "[" + i + "]");
			available_booking_engines.add(booking_engines_path);
		}
        return available_booking_engines;
	}
	
	public static List getBookingEngineNames() {
		ArrayList<String> available_booking_enginesNames = new ArrayList<String>();
		for (int i = 0; i <= 7; i++) {
			String booking_engines_paths = PortalAPIpathExpressions.available_booking_Names;
			String booking_engines_path = booking_engines_paths.replace("[0]", "[" + i + "]");
			available_booking_enginesNames.add(booking_engines_path);
		}
        return available_booking_enginesNames;
	}
	*/
	public static List loginsuccessdetails() throws IOException, InterruptedException {
		
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
		String user_role=response.path("user_role");
		//boolean multiple_hotels=response.path("multiple_hotels");
		available_booking_enginesNames.add(sessionid);
		available_booking_enginesNames.add(hotel_code);
		available_booking_enginesNames.add(user_role);
		//available_booking_enginesNames.add(multiple_hotels);
		return available_booking_enginesNames;
	}
	
	public static void logout() throws IOException
	{
		String body="{\r\n" + 
				"\"session_id\" : \""+available_booking_enginesNames.get(0)+"\"\r\n" + "}";
		RestAssured.registerParser("text/plain", Parser.JSON);
		given().body(body)
		.when().post("/portal/logout")
		.then().log().all().body("success", equalTo(true));
			
	}
	
	public static List getRandomNumber(int NoOfRendomNumbers)
	{
		List<String> numbers =  new ArrayList<String>();;
		Random r=new Random();
		for(int i=0;i<=NoOfRendomNumbers;i++) 
		{
		   int no=r.nextInt(200);
		   String number=Integer.toString(no);
		   numbers.add(number);
		}
		return numbers;
		
	}
	
	public static String randamString(String [] arryvalues)
	{
		List<String> randamstring=Arrays.asList(arryvalues);
		Random randomizer = new Random();
		String randomString = randamstring.get(randomizer.nextInt(randamstring.size()));
		return randomString;
		
	}
	
	public static long getNextOneYearTimeStamp() throws ParseException
	{
		long CurrentTimeStamp;
		String Year = new SimpleDateFormat("yyyy").format(new Date().getTime());
		int year=Integer.parseInt(Year);
		int increment=year+1;
		
		String month = new SimpleDateFormat("MM").format(new Date().getTime());
		String day = new SimpleDateFormat("dd").format(new Date().getTime());
		String hhmmss = new SimpleDateFormat("hh:mm:ss").format(new Date().getTime());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm:ss");
	 
	    Date parsedTimeStamp = dateFormat.parse(""+month+"-"+day+"-"+Integer.toString(increment)+" "+hhmmss+"");
	 
	    Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
	    CurrentTimeStamp=timestamp.getTime();
        return CurrentTimeStamp;
	}
	
	public static long getTimeStamp(String date,String dateformat) throws ParseException
	{
		long CurrentTimeStamp;		
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
	    Date parsedTimeStamp = dateFormat.parse(date);
	    Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
	    CurrentTimeStamp=timestamp.getTime();
        return CurrentTimeStamp;
	}
	
	public static int getRandomNumber(int MinNum, int MaxNum){
		int rndNumber=0;
		if(MinNum < 1 && MaxNum <1){
                    
                }
                else if(MinNum > 0 && MaxNum >0 && MinNum == MaxNum){
				rndNumber=MinNum;
			}
			else{
			rndNumber=random.nextInt((MaxNum-MinNum+1))+MinNum;
			}
			return rndNumber;

    }
	
	

//	public static void main(String args[]) throws ParseException
//	{
//		getTimeStamp();
//	}
}
