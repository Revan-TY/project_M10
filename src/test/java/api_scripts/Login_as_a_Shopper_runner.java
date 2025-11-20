package api_scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import generic.Spec_Builder_class;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Login_as_a_Shopper_pojo;
import tools.jackson.databind.ObjectMapper;
import utils.GenericProperties;


public class Login_as_a_Shopper_runner {

	@Test
	public void login() throws IOException
	{
	  GenericProperties gp = new GenericProperties();
	   String baseurl = gp.getProperties("baseurl");
	   String login_as_a_shopper_endpoint = gp.getProperties("login_as_a_shopper_endpoint");

	  String email = gp.getOutPropertie("email");
	  String role = gp.getOutPropertie("role");
	  String  contentTypeJson= gp.getProperties("contentTypeJson");
	  String get_status_line=gp.getProperties("get_status_line");
	  
	Login_as_a_Shopper_pojo lp = new Login_as_a_Shopper_pojo();
	lp.setEmail(email);
	lp.setPassword("vir@18");
	lp.setRole(role);
	
	ObjectMapper obj = new ObjectMapper();
	String jsonBody = obj.writeValueAsString(lp);
	Spec_Builder_class sp = new Spec_Builder_class();
	RequestSpecification req = sp.request(jsonBody, contentTypeJson);
	ResponseSpecification res = sp.response(200,get_status_line,3000l, "message","OK");
	
	 Response response = RestAssured.given().spec(req).when().post(baseurl+login_as_a_shopper_endpoint)
	.then().spec(res).extract().response();
	 
	JsonPath jp = new JsonPath(response.asPrettyString());
	String bearer_token = jp.getString("data.jwtToken");
	gp.setProperties("bearer_token", bearer_token);
	
	}
	
	
}
