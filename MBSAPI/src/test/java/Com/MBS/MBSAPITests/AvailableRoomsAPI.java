package Com.MBS.MBSAPITests;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.MBS.MBSConstants.EndPoint;
import Com.MBS.MBSConstants.InputConstants;
import Com.MBSWrapper.BaseTest;
import Com.MBSWrapper.MBSWrapper;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import scala.Console;

public class AvailableRoomsAPI extends BaseTest

{
	public static ArrayList<String> available_rate_codes = new ArrayList<String>();
	public static ArrayList<String> available_booking_rooms_codes = new ArrayList<String>();
	
	
	
	
	public AvailableRoomsAPI() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{ 
		String URI=MBSWrapper.getURI();
		RestAssured.baseURI=URI;
	//	logindetails=MBSWrapper.loginsuccessdetails();
	}
	
	
	
    @Test(priority=1, groups="room")
    public void validateRatecalender() {
    	
        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification();
        requestSpecification/*param("arrivalDate", InputConstants.GET_ARRIVAL_DATE)
        .param("departureDate", InputConstants.GET_DEPARTURE_DATE)*/
        .param("numberOfAdults", InputConstants.GET_NUMBER_OF_ADULTS)
        .param("numberOfChildren", InputConstants.GET_NUMBER_OF_CHILD)
        .param("locale",InputConstants.GET_LOCALE)
        .param("propertyCode", InputConstants.GET_PROPERTY_CODE)
       .log().all();
        
        RestAssured.registerParser("text/plain", Parser.JSON);
        given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_RATE_CALENDAR).
                then().statusCode(200)
                .body("data.rateCode", equalTo("TDBAR"))
                .body("data.ratePlanName",equalTo("Main BAR (Not for Storefront Use)"))
              //  .body("data.rates[0].available", equalTo("true"))
                .log().all();
        RestAssured.registerParser("text/plain", Parser.JSON);
		
        Response response=   given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_RATE_CALENDAR)
        		.then().extract().response();
      
        response.path("data.rates[0].available", "true");
        
      String rateCode=response.path("data.rateCode").toString();
    //  System.out.println(sessionid);
      available_rate_codes.add(rateCode);
    //  available_rate_codes.add(sessionid);
      String rateCode1= available_rate_codes.get(0);
     System.out.println(rateCode1);
    
    /* String date= response.path("data.rates[*].date").toString();
     available_booking_rooms_codes.add(date);*/
     
  
     }
    
		


		@Test(priority=2, enabled=false)
	    public void validateAvailableRooms() {
	    	
	        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification();
	        requestSpecification.param("arrivalDate", InputConstants.GET_ARRIVAL_DATE)
	        .param("departureDate", InputConstants.GET_DEPARTURE_DATE)
	        .param("numberOfAdults", InputConstants.GET_NUMBER_OF_ADULTS)
	        .param("numberOfChildren", InputConstants.GET_NUMBER_OF_CHILD)
	        .param("locale",InputConstants.GET_LOCALE)
	        .param("propertyCode", InputConstants.GET_PROPERTY_CODE)
	       .log().all();
	        given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_AVAILABLE_ROOMS).
	                then().statusCode(200).log().all();
		}
	/*        assert.equals("data.availableRooms.*.code");
	        
	       Getting Response
	        Response response = given().spec(requestSpecification).get(EndPoint.GET_AVAILABLE_ROOMS);
	        System.out.println(response.getHeaders());
	        //Inline Validation
	        //1.Hard Assertion
	        response.then().body("firstName", equalTo("Fluent")).body("lastName", equalTo("Wait")).
	                body("address", equalTo("New York"));
	        //2.Soft Assertion
	        response.then().body("firstName", equalTo("Fluent"), "lastName", equalTo("Wait"), "address", equalTo("New York"));
	        //Path Validation
	        //1.Hard Assertion
	        Assert.assertEquals(response.path("firstName"), "Fluent");
	        Assert.assertEquals(response.path("lastName"), "Wait");
	        //2.Soft Assertion
	        SoftAssert softAssert = new SoftAssert();
	        softAssert.assertEquals(response.path("firstName"), "Fluent", "First Name Not Equal");
	        softAssert.assertEquals(response.path("lastName"), "Wait");
	        softAssert.assertEquals(response.path("address"), "New York", "City is not equal");
	        softAssert.assertAll();
	        //Java Object
	        EmployeeBin employeeBin = response.as(EmployeeBin.class);
	        //1.Hard Assertion
	        Assert.assertEquals(employeeBin.getFirstName(),"Fluent");
	        //2.Soft Assertion
	        SoftAssert newSoftAssert = new SoftAssert();
	        newSoftAssert.assertEquals(employeeBin.getFirstName(), "Fluent", "First Name Not Equal");
	        newSoftAssert.assertEquals(employeeBin.getLastName(), "Wait");
	        newSoftAssert.assertEquals(employeeBin.getAddress(), "New York", "City is not equal");
	        newSoftAssert.assertAll();

	    }
	    given().pathParam("propertyCode","mbsqa1")//.queryParam("B", "B")
		.when().get("https://mbsapi-qa1.reztrip.io/propertyInfo/{propertyCode}")
*/
	    
		@Test(priority=3)
	    public void validateAllOffers() {
			
	        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification(); 
	       requestSpecification
	       // .param("locale", InputConstants.GET_LOCALE)
	        .param("propertyCode", InputConstants.GET_PROPERTY_CODE)
	        .log().all();
	        given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_ALL_OFFERS).
	                then().statusCode(200)/*.body("data.name", equalTo("Main BAR (Not for Storefront Use)"))*/.log().all();
	        
		}
		
		
		
	    @Test(groups = {"Rooms"}, enabled= false)
	    public void validateAvailableRatePlans() {
	    	
	        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification();
	        requestSpecification.pathParam("roomCode", "FAKE")
	        .param("arrivalDate", "2018-11-29")
	        .param("departureDate", "2018-11-30").param("roomOccupancy", "FAKE:1:1:0")
	        .param("numberOfAdults", 1)
	        .param("numberOfChildren", 0)
	        .param("locale", "en")
	        .param("propertyCode", "mbsqa1")
	        .log().all();
	        
	       
	       String response=   given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_AVAILABLE_RATE_PLAN+"/{roomCode}").asString();
	                //then().extract().response();
	       
	       given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_AVAILABLE_RATE_PLAN+"/{roomCode}")
           .then().statusCode(200).and().body("data.availableRooms..code", equalTo("FAKE"));
	       
	       
	       
	       System.out.println("**********************************************************************");
	       List<String> ls= from(response).getList("data.availableRooms..code");
			System.out.println(ls+"\n");
			available_booking_rooms_codes.addAll(ls);
		//	response_from_available_rooms.add(roomCode);
	        //Getting Response
	      //  Response response = given().spec(requestSpecification).get(EndPoint.GET_AVAILABLE_ROOMS);

	    }
	    
	   

	    
		@Test(priority=4, dependsOnGroups= {"room"})
	    public void validateRatePlans() {
			
	        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification();
	        String rateCode= available_rate_codes.get(0);
	        System.out.println(rateCode);
	        String date= available_booking_rooms_codes.get(0);
	        
	       requestSpecification.pathParam("rateCode",available_rate_codes.get(0))
	        .param("locale", InputConstants.GET_LOCALE)
	        .param("propertyCode", InputConstants.GET_PROPERTY_CODE)
	        .log().all();
	        given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_RATE_PLAN+"/{rateCode}").
	                then().statusCode(200).body("data.name", equalTo("Main BAR (Not for Storefront Use)")).log().all();
	        System.out.println(date);
		}
		
		@Test(priority=5, dependsOnGroups= {"room"})
	    public void validateAvailableRoomUpgrades() {
			
	        RequestSpecification requestSpecification = new BaseTest().getRequestSpecification();
	        
	       requestSpecification.param("rateCode",available_rate_codes.get(0))
	        .param("locale", InputConstants.GET_LOCALE)
	        .param("propertyCode", InputConstants.GET_PROPERTY_CODE)
	        .log().all();
	        given().header("x-api-key",EndPoint.GET_API_KEY).spec(requestSpecification).get(EndPoint.GET_AVAILABLE_ROOMS_UPGRADE).
	                then().statusCode(200).body("data.name", equalTo("Main BAR (Not for Storefront Use)")).log().all();
	        
		}
}
