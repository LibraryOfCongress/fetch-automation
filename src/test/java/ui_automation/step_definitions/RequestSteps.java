package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.PickListPage;
import ui_automation.pages.RequestPage;
import ui_automation.utilities.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    RequestPage request = new RequestPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    PickListPage picklist = new PickListPage();



    @Given("user navigates to the Request Page")
    public void user_navigates_to_the_Request_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "requestURL"));
    }

    @When("user clicks Create button")
    public void user_clicks_create_button() throws InterruptedException {
        helper.jSClick(request.create);
        wait.hardWait(100);
    }

    @Then("user verifies the dropdown options")
    public void user_verifies_the_dropdown_options(io.cucumber.datatable.DataTable dataTable) {
        WaitHelper.waitForVisibility(request.dropdownOptions.get(0), 10);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("option");
            String actualLabel = request.dropdownOptions.get(i).getText();
            assertEquals("Options name verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
    }

    @Then("user selects Create Manual Requests option")
    public void user_selects_create_manual_requests_option() {
        request.dropdownOptions.get(2).click();
    }

    @Then("request job creation modal is displayed")
    public void request_job_creation_modal_is_displayed() {
        WaitHelper.waitForVisibility(request.modal, 10);
        Helper.verifyElementDisplayed(request.modal);
    }

    @Then("request submit button is disabled")
    public void request_submit_button_is_disabled() {
        helper.scrollIntoView(request.submitRequest);
        Assert.assertFalse(request.submitRequest.isEnabled());
    }

    @When("user enters an incorrect Item Barcode")
    public void user_enters_an_incorrect_item_barcode()  {
        request.itemBarcodeField.click();
        request.itemBarcodeField.sendKeys("1234567890");
    }

    @When("user enters an item barcode")
    public void user_enters_an_item_barcode()  {
        request.itemBarcodeField.click();
        request.itemBarcodeField.sendKeys("11223344550");
    }

    @When("user enters an Item Barcode from an existing Shelving Job")
    public void user_enters_an_item_barcode_from_an_existing_shelving_job()  {
        request.itemBarcodeField.click();
        request.itemBarcodeField.sendKeys(ShelvingSteps.itemBarcode);
    }

    @When("user enters Request ID")
    public void user_enters_Request_ID() {
        request.requestIDField.click();
        request.requestIDField.sendKeys("111");
    }

    @When("user enters Requester Name")
    public void user_enters_Request_Name() {
        request.requestorNameField.click();
        request.requestorNameField.sendKeys("Totu");
    }

    @When("user selects Request Type")
    public void user_selects_Request_Type() {
        request.requestTypeField.click();
        request.options.get(2).click();
    }

    @When("user selects Priority")
    public void user_selects_Priority() {
        request.priorityField.click();
        request.options.get(1).click();
    }

    @When("user selects Delivery Location")
    public void user_selects_Delivery_Location() throws InterruptedException {
        request.deliveryLocationField.click();
        wait.hardWait(2000);
        request.options.get(1).click();
    }

    @Then("user clicks on created Request")
    public void user_clicks_on_created_request() throws InterruptedException {
        List<WebElement> requests = driver.findElements(By.cssSelector("[class='q-table'] tr"));
        for(WebElement request: requests) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",request);
            if(request.getText().contains("Totu") && request.getText().contains(ShelvingSteps.itemBarcode)) {
                request.click();
            }
        }
        wait.hardWait(2000);
    }

    @Then("user verifies Request details on Overlay Slide")
    public void user_verifies_request_details_on_overlay_slide(){
        WaitHelper.waitForVisibility(request.requestItemDetails.get(0), 10);
            String actualBarcode= request.actualBarcode.getText();
        Assert.assertEquals(ShelvingSteps.itemBarcode, actualBarcode);
    }

    @When("user selects Create a Pick List option")
    public void user_selects_create_a_pick_list_option() {
        helper.jSClick(request.dropdownOptions.get(1));
    }

    @Then("user verifies Requests with checkboxes are displayed")
    public void user_verifies_requests_with_checkboxes_are_displayed() {
        Helper.verifyElementDisplayed(request.checkboxesRequests.get(0));
    }

    @When("user selects Requests")
    public void user_selects_requests() {
        helper.jSClick(request.checkboxesRequests.get(1));
        helper.jSClick(request.checkboxesRequests.get(2));
    }

    @When("user clicks Create Pick List")
    public void user_clicks_create_pick_list() throws InterruptedException {
        helper.jSClick(request.createPickListBtn);
        wait.hardWait(1000);
    }

    @Then("user verifies the Pick List is created")
    public void user_verifies_the_pick_list_is_created() {
        wait.waitForVisibility(request.alertText, 1000);
        Assert.assertTrue(request.alertText.getText().contains("Successfully created Pick List #: "));
    }

    @When("user clicks the alert link")
    public void user_clicks_the_alert_link() {
        helper.jSClick(request.createdPickListLink);
    }

    @Then("user is able to see the Pick List dashboard")
    public void user_is_able_to_see_the_pick_list_dashboard() {
        String picklistJobNumber = picklist.picklistJobNumber.getText();
        String createdPickList = request.createdPickListLink.getText();
        Assert.assertEquals(picklistJobNumber, createdPickList);
    }

    @When("user selects Add to Pick List option")
    public void user_selects_add_to_pick_list_option() {
        helper.jSClick(request.dropdownOptions.get(0));
    }

    @When("user selects Pick List from dropdown")
    public void user_selects_pick_list_from_dropdown() throws InterruptedException {
        request.selectPickListJobDropdown.click();
        request.dropdownList.get(0).click();
        wait.hardWait(1000);
//        for(WebElement job: request.dropdownList) {
//            if(job.getText().equals("2")) {
//                job.click();
//            }
//        }
    }

    @When("user clicks Add to Pick List")
    public void user_clicks_add_to_pick_list() throws InterruptedException {
        helper.jSClick(request.addToPickListBtn);
        wait.hardWait(1000);
    }

    @Then("user verifies items are added to the Pick List")
    public void user_verifies_items_are_added_to_the_Pck_List() {
        Assert.assertTrue(request.alertText.getText().contains("Successfully added items to Pick List #: "));
    }


}
