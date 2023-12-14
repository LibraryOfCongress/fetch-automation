package api_automation.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import api_automation.requestBuilder.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.core.api.Scenario;
import api_automation.utils.TestBase;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.Before;

import java.util.Map;

public class Owners extends TestBase {

    static Response response;
    Response resp;
    String requestData;
    static String recordID;
    static String ownerID;
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario=scenario;
    }

// OWNER TIER
    @Given("user submits GET request to retrieve all Owner Tier records")
    public void user_submits_GET_request_to_retrieve_all_Owner_Tier_records() {
        response = given().when().get(property.getProperty("baseURI")+ "/owners/tiers");
        response.prettyPrint();
    }


    @Then("user validates if status code is {int}")
    public void user_validates_if_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }


    @Given("user creates a new record with following data {int}, {string}")
    public void user_creates_a_new_record_with_following_data(int level, String name) throws JsonProcessingException {
    //Create request in Java Object
        RequestBuilder reqBuilder = new RequestBuilder();
        reqBuilder.setLevel(level);
        reqBuilder.setName(name);
    //Convert object to string
        ObjectMapper obMap = new ObjectMapper();
        requestData = obMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
    }


    @Given("user submits POST request to Owner Tier API endpoint")
    public void user_submits_POST_request_to_Owner_Tier_API_endpoint() {
       response = given()
                    .contentType(ContentType.JSON)
                    .body(requestData)
               .when()
               .post(property.getProperty("owner_tiersURL"));

        String strResponse = response.prettyPrint();
    }


    @Then("user validates if the value of name in response is {string}")
    public void user_validates_if_the_value_of_name_in_response_is(String name) {
      String actualName = JsonPath.read(response.asString(),"name");
        System.out.println("Expected name: " + name + "Actual name: " + actualName);
        assertEquals(name, actualName);
    }


    @Then("user retrieves recordID from response")
    public void user_retrieves_recordID_from_response() {
        recordID = JsonPath.read(response.asString(), "id").toString();
        System.out.println("Record ID is: " + recordID);
    }


    @Given("user submits GET request with recordID to get an Owner Tier details")
    public void user_submits_GET_request_with_recordID_to_get_an_Owner_Tier_details() {
        response = given()
                    .when()
                     .get(property.getProperty("owner_tiersURL")+"/"+recordID);
        response.prettyPrint();
    }


    @Given("user submits DELETE request with recordID to Owner Tier API endpoint")
    public void user_submits_DELETE_request_with_recordID_to_Owner_Tier_API_endpoint() {
        resp = given()
                .when()
                .delete(property.getProperty("owner_tiersURL")+"/"+recordID);
    }


//    OWNER
    @Given("user submits GET request to retrieve all Owner records")
    public void user_submits_GET_request_to_retrieve_all_Owner_records() {
        response = given().when().get(property.getProperty("baseURI")+ "/owners");
        response.prettyPrint();
    }


    @Given("Owner Tier recordID is created")
    public void owner_Tier_recordID_is_created() {
        System.out.println("Owner Tier Record ID is: " + recordID);
    }


    @Given("user creates the request data with {string}, {int}")
    public void user_creates_the_request_data_with(String name, int ownerTierID) throws JsonProcessingException {
        RequestBuilder reqBuilder = new RequestBuilder();
        reqBuilder.setOwnerName(name);
        reqBuilder.setOwnerTierID(ownerTierID);

        ObjectMapper obMap = new ObjectMapper();
        requestData = obMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
    }


    @Given("user submits POST request to Owner API endpoint")
    public void user_submits_POST_request_to_Owner_API_endpoint() {
        response = given()
                .contentType(ContentType.JSON)
                .body(requestData)
                .when()
                .post(property.getProperty("owners_URL"));

        String strResponse = response.prettyPrint();
    }


    @Then("user validates status code is {int}")
    public void userValidatesStatusCodeIs(int status) {
        assertEquals(status, resp.getStatusCode());
    }


    @Then("user retrieves ownerID from response")
    public void user_retrieves_ownerID_from_response() {
        ownerID = JsonPath.read(response.asString(), "id").toString();
        System.out.println("Owner ID is: " + ownerID);
    }


    @Given("user submits GET request with ownerID to get an Owner details")
    public void user_submits_GET_request_with_ownerID_to_get_an_Owner_details() {
        response = given()
                .when()
                .get(property.getProperty("owners_URL")+"/"+ownerID);
        response.prettyPrint();
    }


    @Given("user submits DELETE request with ownerID to Owner API endpoint")
    public void user_submits_DELETE_request_with_ownerID_to_Owner_API_endpoint() {
        resp = given()
                .when()
                .delete(property.getProperty("owners_URL")+"/"+ownerID);
    }

}
