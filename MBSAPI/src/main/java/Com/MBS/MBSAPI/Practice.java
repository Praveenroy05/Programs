package Com.MBS.MBSAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import static io.restassured.path.json.JsonPath.*;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Practice {

	@Test
	public void test()
	{
//		given().
//		header("propertyCode","mbsqa1").
		given().
		get("http://jsonplaceholder.typicode.com/photos/1")
		.then().statusCode(200)
		.and().body("id", equalTo(1)).
	//	.body("available_booking_engines[0].booking_engine_code", equalTo("classic.tt"))
		log().all()
		.contentType(ContentType.JSON).body("thumbnailUrl", equalTo("https://via.placeholder.com/150/92c952"));
		//System.out.println(body().prettyPrint());
	}
	
	@Test
	public void Test1()
	{
		Response response=
				
				given().get("http://jsonplaceholder.typicode.com/photos/1").andReturn().then().extract().response();
		
		System.out.println("ThumbNail URL is :" +response.path("thumbnailUrl"));
		
		String header= response.getHeader("CF-RAY");
		System.out.println(header);
		Map<String, String> cookies= response.getCookies();
		for(Map.Entry<String, String> entry: cookies.entrySet())
		{
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		Cookie a= response.getDetailedCookie("__cfduid");
		System.out.println(a.hasExpiryDate());
		System.out.println(a.getExpiryDate());
		System.out.println(a.hasValue());
		
		System.out.println("***********************************************************");
		Headers header1= response.getHeaders();
		for(Header h:header1)
		{
		System.out.println(h.getName()+":"+ h.getValue());
		}
	}
	
	@Test
	public void Test2()
	{
		String response= given().get("http://services.groupkt.com/country/search?text=lands").asString();
		
		List<String> ls= from(response).getList("RestResponse.result.findAll{it.name.length()>30}.name");
		System.out.println(ls+"\n");
	}
	
	
	@Test
	public void Test3()
	{
		given().param("propertyCode","mbsqa1")//.queryParam("B", "B")
		.when().get("https://mbsapi-qa1.reztrip.io/propertyInfo")
		.then().statusCode(200).log().all();
		
	}
	
	//Path param
	
	@Test
	public void Test4()
	{
		double time=
		given().pathParam("propertyCode","mbsqa1")//.queryParam("B", "B")
		.when().get("https://mbsapi-qa1.reztrip.io/propertyInfo/{propertyCode}").timeIn(TimeUnit.DAYS);
	//	.then()//statusCode(200).log().all();
		
		System.out.println(time);
		
	}
	
	
	@Test
	public void Test5()
	{
		//String href=
		given().pathParam("page","page=4")//.queryParam("B", "B")
		.when().get("https://reqres.in/api/users?{page}").then().rootPath("data.avatar").log().all();
	//	path("data[0].avatar");
		
		//String link= res.path("page.avatar");
		
		//statusCode(200).contentType(ContentType.JSON).log().all();
		
	//	given().get(href).then().statusCode(200).log().all();
		
		
		
	}
	
	@Test
	public void Test6()
	{
		given().pathParam("code","IN")//.queryParam("B", "B")
		//.when().get("http://services.groupkt.com/country/get/iso2code/{code}").then().statusCode(200).log().all();
		
		.when().get("http://services.groupkt.com/country/search?text={code}").then().statusCode(200).log().all();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
