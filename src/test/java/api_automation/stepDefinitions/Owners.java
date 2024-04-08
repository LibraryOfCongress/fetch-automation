package api_automation.stepDefinitions;


import api_automation.requestBuilder.GorestRequestBuilder;
import api_automation.utils.TestBase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Owners extends TestBase {


    String requestData;
    static Response response;
    int owner_tier_id;
    int owner_id;
    Scenario scenario;

    @Before
    public void keepScenario(Scenario scenario) {
        this.scenario=scenario;
    }

    // OWNER TIER

    @Given("user submits GET request to retrieve all Owner Tier records")
    public void user_submits_GET_request_to_retrieve_all_Owner_Tier_records() {
        response = given().when().get(property.getProperty("baseURI") + "/owners/tiers");
        response.prettyPrint();
    }

    @Then("user validates if status code is {int}")
    public void user_validates_if_status_code_is(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @When("user creates request data with {int}, {string}")
    public void user_creates_request_data_with(int level, String name) throws JsonProcessingException {
        //Create request in Java object
        GorestRequestBuilder reqBuilder=new GorestRequestBuilder();
        reqBuilder.setName(name);
        reqBuilder.setLevel(level);

        //Convert object to string
        ObjectMapper obMap = new ObjectMapper();
        requestData=obMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
        //write response to report
        scenario.write(requestData);
        scenario.embed(requestData.getBytes(), "application/json");
    }


    @When("user submits POST request to owners_tiers endpoint")
    public void user_submits_POST_request_to_owner_tiers_endpoint() {
        response=given()
                .contentType(ContentType.JSON)
                .body(requestData).
                when()
                .post(property.getProperty("baseURI") + "/owners/tiers");

        String strResponse=response.prettyPrint();

        //write response to file
        scenario.write(strResponse);
    }


    @Then("user validates if the value of name in response is {string}")
    public void user_validates_if_the_value_of_name_in_response_is(String name) {
        String actualName = JsonPath.read(response.asString(), "name");
        System.out.println("Expected name: " + name + " Actual name: " + actualName);
        assertEquals(name, actualName);
    }


    @Then("user retrieves owner_tier_id from response")
    public void user_retrieves_owner_tier_id_from_response() {
//        owner_tier_id =JsonPath.read(response.asString(), "id").toString();
        owner_tier_id =JsonPath.read(response.asString(), "id");
        scenario.write("Owner_Tier ID::: "+ owner_tier_id);
    }


    @Then("user submits PATCH request to owners_tiers endpoint")
    public void user_submits_PATCH_request_to_owner_tiers_endpoint() {
        String patchData = "{\n" +
                "  \"level\": 3,\n" +
                "  \"name\": \"ABC\"\n" +
                "}";
        response=given()
                .contentType(ContentType.JSON)
                .body(patchData).
                when()
                .patch(property.getProperty("baseURI") + "/owners/tiers" + "/" + owner_tier_id);

        String strResponse=response.prettyPrint();

        //write response to file
        scenario.write(strResponse);
    }

    @Then("user verifies updated name in response is {string}")
    public void user_verifies_updated_name_in_response_is(String name) {
        String updatedName = JsonPath.read(response.asString(), "name");
        System.out.println("Expected name: " + name + " Actual name: " + updatedName);
        assertEquals(name, updatedName);
    }


    @Then("user deletes Owner Tier record")
    public void user_deletes_owner_tier_record () {
        Response resp=given()
                .when()
                .delete(property.getProperty("owner_tiers")+"/"+ owner_tier_id);
        String strResponse=resp.prettyPrint();
        scenario.write(strResponse);
    }


    // Owner

    @When("user submits GET request to retrieve all Owner records")
    public void user_submits_GET_request_to_retrieve_all_Owner_records() {
        response = given().when().get(property.getProperty("baseURI") + "/owners");
        response.prettyPrint();
    }


    @And("user submits POST request to owners endpoint")
    public void user_submits_PATCH_request_to_owners_enpoint() {
        String postData = "{"
                + "\"name\": \"LOC\","
                + "\"owner_tier_id\": " + owner_tier_id
                + "}";

        response=given()
                .contentType(ContentType.JSON)
                .body(postData).
                 when()
                .post(property.getProperty("baseURI") + "/owners");

        String strResponse=response.prettyPrint();

        //write response to file
        scenario.write(strResponse);
    }

    @Then("user retrieves owner_id from response")
    public void user_retrieves_owner_id_from_response() {
        owner_id =JsonPath.read(response.asString(), "id");
        scenario.write("Owner ID::: "+ owner_id);
    }

    @And("user submits PATCH request to owners endpoint")
    public void user_submits_PATCH_request_to_owners_endpoint() {
        String patchData = "{\n" +
                "      \"name\": \"Pest Control\"\n" +
                "}";
        response=given()
                .contentType(ContentType.JSON)
                .body(patchData).
                when()
                .patch(property.getProperty("baseURI") + "/owners" + "/" + owner_id);

        String strResponse=response.prettyPrint();

        //write response to file
        scenario.write(strResponse);
    }


    @And("user deletes Owner record")
    public void user_deletes_owner_record () {
        Response resp=given()
                .when()
                .delete(property.getProperty("owners")+"/"+ owner_id);
        String strResponse=resp.prettyPrint();
        scenario.write(strResponse);
    }
}
