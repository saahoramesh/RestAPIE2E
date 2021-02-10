package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils{
	
	public RequestSpecification req;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log=new PrintStream(new FileOutputStream("Logging.txt"));
		RestAssured.config.logConfig(new LogConfig().defaultStream(log));
		
		 req=new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log)).
				 addFilter(ResponseLoggingFilter.logResponseTo(log)).
				 setBaseUri("http://216.10.245.166").
				 setContentType(ContentType.JSON)
  			  .addQueryParam("key", "qaclick123").build();
		 req.log().all();

		return req;
	}
	
	public String getJsonPath(Response response, String key) {
		 String rpsAsString = response.asString();
		   JsonPath jp = new JsonPath(rpsAsString);
		  
		   return jp.get(key).toString();
			

	}

}
