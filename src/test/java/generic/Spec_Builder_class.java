package generic;

import org.hamcrest.Matchers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec_Builder_class {

     public RequestSpecification request(String body,String content)
     {
      RequestSpecBuilder rsb= new RequestSpecBuilder();
    	RequestSpecification req = rsb.setRelaxedHTTPSValidation().setBody(body)
    	 .setContentType(content).build();
    	 return req;	 
     }
     
     
     public ResponseSpecification response(int code,String statusline,long time,String key , String value)
     {	 
    	ResponseSpecBuilder rsb= new ResponseSpecBuilder();
    	ResponseSpecification res = rsb.expectStatusCode(code).expectStatusLine(statusline)
    	.expectResponseTime(Matchers.lessThanOrEqualTo(time))
    	.expectBody(key,Matchers.equalTo(value)).build();
    	 return res;
    	 
     }
	
}
