package api_automation.stepDefinitions;

import com.jayway.jsonpath.JsonPath;
import api_automation.utils.TestBase;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;

public class AccessionJobs extends TestBase {

    String requestData;
    Response response;
    Response resp;
    static String accessionJobID;
//    Scenario scenario;

//    @Before
//    public void setUp(Scenario scenario) {
//        this.scenario=scenario;
//    }

    @Given("user creates the request data")
    public void user_creates_the_request_data() {
     requestData ="{\n" +
             "  \"container_type_id\": 1,\n" +
             "  \"last_transition\": \"2023-11-27T12:34:56.789123Z\",\n" +
             "  \"owner_id\": 42,\n" +
             "  \"run_time\": \"03:25:15\",\n" +
             "  \"status\": \"Paused\",\n" +
             "  \"trayed\": true,\n" +
             "  \"user_id\": 1\n" +
             "}\n";

    }

    @Given("user submits POST request to Accession Jobs API endpoint")
    public void user_submits_POST_request_to_Accession_Jobs_API_endpoint() {
        response = given()
                .contentType(ContentType.JSON)
                .body(requestData)
                .when()
                .post(property.getProperty("accession_jobs"));

        String strResponse = response.prettyPrint();
      //  scenario.write(strResponse);
    }


    @Then("user validates that status code is {int}")
    public void user_validates_that_status_code_is(int status) {
     Assert.assertEquals(status, response.getStatusCode());
    }


    @Given("user submits GET request to retrieve all Accession Job records")
    public void user_submits_GET_request_to_retrieve_all_Accession_Job_records() {
        response = given().when().get(property.getProperty("accession_jobs"));
        response.prettyPrint();
    }


    @Then("user validates the status code is {int}")
    public void user_validates_the_status_code_is(int statusCode) {
       assertEquals(statusCode, response.getStatusCode());
    }


    @Then("user retrieves accessionJobID from response")
    public void user_retrieves_accessionJobID_from_response() {
        accessionJobID = JsonPath.read(response.asString(), "id").toString();
        System.out.println("Accession Job ID is: " +accessionJobID);
    }


    @Given("user submits GET request with accessionJobID to retrieve an Accession Job details")
    public void user_submits_GET_request_with_accessionJobID_to_retrieve_an_Accession_Job_details() {
        response = given().when().get(property.getProperty("accession_jobs")+"/"+accessionJobID);
        response.prettyPrint();
       // scenario.write(response.prettyPrint());
    }


    @Given("user submits DELETE request with accessionJobID to Accession Job API endpoint")
    public void user_submits_DELETE_request_with_accessionJobID_to_Accession_Job_API_endpoint() {
        resp = given()
                .when()
                .delete(property.getProperty("accession_jobs")+"/"+accessionJobID);
    }



}
