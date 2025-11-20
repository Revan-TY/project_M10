package api_scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import generic.Spec_Builder_class;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import pojo.Update_the_shopper_details_pojo;
import tools.jackson.databind.ObjectMapper;
import utils.GenericProperties;

public class Update_the_shopper_details_runner {

	@Test
	public void update() throws IOException
	{
		GenericProperties gp = new GenericProperties();
		String baseurl = gp.getProperties("baseurl");
		String contentTypeJson = gp.getProperties("contentTypeJson");
		String Update_the_shopper_Details_endpoint = gp.getProperties("Update_the_shopper_Details_endpoint");
		//String get_status_line = gp.getProperties("get_status_line");
		String email = gp.getOutPropertie("email");
		String bearer_token = gp.getOutPropertie("bearer_token");
		String userId = gp.getOutPropertie("userId");
	
		Update_the_shopper_details_pojo up= new Update_the_shopper_details_pojo();
		up.setCity("banglore");
		up.setCountry("india");
		up.setEmail(email);
		up.setFirstName("king");
		up.setGender("MALE");
		up.setLastName("kholi");
		up.setPassword("vir@18");
		up.setPhone(9099989990l);
		up.setState("karnataka");
		up.setZoneId("ALPHA");
		
		ObjectMapper obj = new ObjectMapper();
		String body = obj.writeValueAsString(up);		
		
  Spec_Builder_class sp = new Spec_Builder_class();
  RequestSpecification req = sp.request(body, contentTypeJson);
//ResponseSpecification res = sp.response(200, get_status_line,3000l, "data.firstName", "king");
		RestAssured
		.given().pathParam("shopperId", userId).auth().oauth2(bearer_token).spec(req)
		.when().patch(baseurl+Update_the_shopper_Details_endpoint)
		.then().statusCode(200);
		

		
		
		
	}
	
	
}
