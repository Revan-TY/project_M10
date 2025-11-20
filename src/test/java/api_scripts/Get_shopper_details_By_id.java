package api_scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import generic.Spec_Builder_class;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utils.GenericProperties;

public class Get_shopper_details_By_id {

	
	@Test
	public void get() throws IOException
   {
		GenericProperties gp = new GenericProperties();
		String baseurl = gp.getProperties("baseurl");
		String get_status_line = gp.getProperties("get_status_line");
		String userId = gp.getOutPropertie("userId");
		String Find_Shopper_data_by_shopperId = gp.getProperties("Find_Shopper_data_by_shopperId");
		String bearer_token = gp.getOutPropertie("bearer_token");
		
		Spec_Builder_class sp = new Spec_Builder_class();
		ResponseSpecification res = sp.response(200, get_status_line,3000l, "data.role", "SHOPPER");		
		RestAssured.given().relaxedHTTPSValidation()
		.pathParam("shopperId", userId).auth().oauth2(bearer_token)
		.when().get(baseurl+Find_Shopper_data_by_shopperId).then().spec(res).log().all();
		
		
	}
	
	
}
