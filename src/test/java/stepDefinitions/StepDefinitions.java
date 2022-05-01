package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import testData.ApiResources;
import testData.TestData;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    private Response response;
    private RequestSpecification request;
    public static String place_id;

    @Given("add place payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
         request = given().log().all().spec(getRequestSpecification())
                .body(TestData.getAddPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http call")
    public void user_calls_with_post_http_call(String resource, String httpMethod) {

        if(httpMethod.equalsIgnoreCase("POST"))
            response = request.when().post(ApiResources.valueOf(resource).getResource());
        else if (httpMethod.equalsIgnoreCase("GET"))
            response = request.when().get(ApiResources.valueOf(resource).getResource());
        else if (httpMethod.equalsIgnoreCase("DELETE"))
            response = request.when().delete(ApiResources.valueOf(resource).getResource());

        response = response.then().log().all()
                .assertThat().spec(getResponseSpecification())
                .extract().response();
    }

    @Then("api call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer code) {
        Assert.assertEquals(response.getStatusCode(), code);
        if(place_id==null)
            place_id = getJsonValue(response, "place_id");
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String status, String value) {
        Assert.assertEquals(getJsonValue(response, status), value);
    }

    @Then("verify placeID created with {string} from {string}")
    public void verify_place_id_created_with_from(String name, String api) throws IOException {
        request = given().spec(getRequestSpecification()).queryParam("place_id", place_id);
        user_calls_with_post_http_call("getPlaceAPI", "GET");
        String actualName = getJsonValue(response, "name");
        Assert.assertEquals(actualName, name);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        request = given().log().all().spec(getRequestSpecification())
                .body(TestData.getDeletePlacePayload(place_id));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        user_calls_with_post_http_call(resource, httpMethod);
    }
}
