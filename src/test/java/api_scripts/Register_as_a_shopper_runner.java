package api_scripts;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import generic.Spec_Builder_class;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Register_as_a_shopper_pojo;
import tools.jackson.databind.ObjectMapper;
import utils.GenericProperties;

public class Register_as_a_shopper_runner {

	@Test
	public void create() throws IOException
	{
		String r_email = "vir"+RandomStringUtils.randomAlphabetic(5)+"@gmail.com";
		
		
		Register_as_a_shopper_pojo rp= new Register_as_a_shopper_pojo();
		rp.setCity("banglore");
		rp.setCountry("india");
		rp.setEmail(r_email);
		rp.setFirstName("virat");
		rp.setGender("MALE");
		rp.setLastName("kholi");
		rp.setPassword("vir@18");
		rp.setPhone(9099989990l);
		rp.setState("karnataka");
		rp.setZoneId("ALPHA");
		
		ObjectMapper obj = new ObjectMapper();
		String body = obj.writeValueAsString(rp);
	GenericProperties gp = new GenericProperties();
	String baseurl = gp.getProperties("baseurl");
	String contentTypeJson = gp.getProperties("contentTypeJson");
	String Register_as_a_shopper_endpoint = gp.getProperties("Register_as_a_shopper_endpoint");
	String create_status_line = gp.getProperties("create_status_line");

	
		Spec_Builder_class sp = new Spec_Builder_class();		
		RequestSpecification req = sp.request(body,contentTypeJson);
    ResponseSpecification res = sp.response(201,create_status_line, 3000l,"data.role" , "SHOPPER");
		
   Response response = RestAssured.given().spec(req).when().
    post(baseurl+Register_as_a_shopper_endpoint).then().spec(res).extract().response();
    
    JsonPath jp = new JsonPath(response.asPrettyString());
    String email = jp.getString("data.email");
    String userId = jp.getString("data.userId");
    String role = jp.getString("data.role");

    gp.setProperties("email", email);
    gp.setProperties("userId", userId);
    gp.setProperties("role", role);

    
    
    
	}
	
	
}
