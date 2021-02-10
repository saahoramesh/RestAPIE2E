package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.APIpojo;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	static String placeId = null;

	static RequestSpecification reqSpec;
	static ResponseSpecification res;
	static Response response;
	static APIresources resourceAPI;
	APIpojo pojo;
	static Response finalResp;

	@Given("^Add place api is available with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_api_is_available_with(String name, String language, String address)
			throws FileNotFoundException {
		// pojo=new APIpojo();
		// pojo.setName(name);
		// pojo.setLanguage(language);
		// pojo.setAddress(address);
		reqSpec = given().spec(requestSpecification()).body(TestDataBuild.jsonBody(name, language, address));

	}

	@Given("^Get place api is available$")
	public void get_place_api_is_available() throws FileNotFoundException {
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", "" + placeId + "");
	}

	@Given("^update place api is available$")
	public void update_place_api_is_available() throws Throwable {
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", "" + placeId + "")
				.body(TestDataBuild.updatePlaceApi(placeId));
	}

	@Given("^delete place api is available$")
	public void delete_place_api_is_available() throws Throwable {
		reqSpec = given().spec(requestSpecification()).body(TestDataBuild.deletePlaceApi(placeId));
	}

	@When("^User performs \"([^\"]*)\" with \"([^\"]*)\" Request$")
	public void user_performs_with_request(String resource, String httpMethod) throws Throwable {

		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("POST")) {
			resourceAPI = APIresources.valueOf(resource);
			System.out.println(resourceAPI.getResourse());
			response = reqSpec.when().post(resourceAPI.getResourse());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			resourceAPI = APIresources.valueOf(resource);
			response = reqSpec.when().get(resourceAPI.getResourse());

		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			resourceAPI = APIresources.valueOf(resource);
			response = reqSpec.when().put(resourceAPI.getResourse());

		}

	}

	@Then("Check the status code is {string}")
	public void check_the_status_code_is(String code) throws Throwable {
		finalResp = response.then().spec(res).log().all().extract().response();

		int actualIntCode = response.getStatusCode();
		String actualIntToString = String.valueOf(actualIntCode);
		System.out.println(code);
		System.out.println(actualIntToString);

		assertEquals(code, actualIntToString);

	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String key, String expectedValue) throws Throwable {

		assertEquals(getJsonPath(finalResp, key).toString(), expectedValue);
	}

	@And("^verify place_id created is mapped to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_placeid_created_is_mapped_to_using(String expectedNname, String resourse) throws Throwable {
		placeId = getJsonPath(response, "place_id");
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", "" + placeId + "");
		resourceAPI = APIresources.valueOf(resourse);
		response = reqSpec.when().get(resourceAPI.getResourse());
		finalResp = response.then().spec(res).log().all().extract().response();
		assertEquals(getJsonPath(finalResp, "name"), expectedNname);
	}

}
