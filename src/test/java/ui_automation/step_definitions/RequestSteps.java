package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.AccessionPage;
import ui_automation.pages.RequestPage;
import ui_automation.utilities.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class RequestSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    RequestPage request = new RequestPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();


    public static final Logger oLog = LogManager.getLogger(RequestSteps.class);


    @Given("user navigates to the Request Page")
    public void user_navigates_to_the_Request_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "requestURL"));
    }


    @When("user clicks Create button")
    public void user_clicks_create_button() {
        helper.jSClick(request.create);
        oLog.info("I clicked Create button");
    }

    @Then("user verifies the dropdown options")
    public void user_verifies_the_dropdown_options(io.cucumber.datatable.DataTable dataTable) {
        wait.waitForVisibility(request.dropdownOptions.get(0), 10);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("option");
            String actualLabel = request.dropdownOptions.get(i).getText();
            assertEquals("Options name verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified dropdown options");
    }


    @Then("user selects Create Manual Requests option")
    public void user_selects_create_manual_requests_option() {
        request.dropdownOptions.get(2).click();
        oLog.info("I selected Create Manual Requests option");
    }

    @Then("request job creation modal is displayed")
    public void request_job_creation_modal_is_displayed() {
        wait.waitForVisibility(request.modal, 10);
        helper.verifyElementDisplayed(request.modal);
    }

    @When("user enters an Item Barcode from an existing Shelving Job")
    public void user_enters_an_item_barcode_from_an_existing_shelving_job()  {
        request.itemBarcodeField.click();
        request.itemBarcodeField.sendKeys("12345678944");
        oLog.info("I entered an Item Barcode");
    }

    @When("user enters Requestor Name")
    public void user_enters_Request_Name() {
        request.requestorNameField.click();
        request.requestorNameField.sendKeys("Victoria Smith");
        oLog.info("I entered Requestor Name");
    }


    @When("user selects Request Type")
    public void user_selects_Request_Type() {
        request.requestTypeField.click();
        request.options.get(2).click();
        oLog.info("I selected Request Type");
    }


    @When("user selects Delivery Location")
    public void user_selects_Delivery_Location() throws InterruptedException {
        request.deliveryLocationField.click();
        wait.hardWait(2000);
        request.options.get(1).click();
        oLog.info("I selected Delivery Location");
    }


    @Then("user clicks on created Request")
    public void user_clicks_on_created_request() throws InterruptedException {
        List<WebElement> requests = driver.findElements(By.cssSelector(".q-table tr "));
        for(WebElement request: requests) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",request);
            if(request.getText().contains("Victoria Smith") && request.getText().contains("12345678944") == true) {
                request.click();
            }
        }
        wait.hardWait(2000);
    }


    @Then("user verifies Request details on Overlay Slide")
    public void user_verifies_request_details_on_overlay_slide(){
        wait.waitForVisibility(request.requestItemDetails.get(0), 10);
            String actualBarcode= request.actualBarcode.getText();
        Assert.assertEquals("12345678944", actualBarcode);

        oLog.info("I verified Request details on Overlay Slide");
    }

}
