package automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.pages.AlertPage;
import automation.pages.PickListPage;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import automation.utilities.Helper;
import automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;

public class PickListSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    PickListPage picklist = new PickListPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    static String itemBarcode = "";
    AlertPage alert = new AlertPage();




    @Given("user navigates to the Pick List Page")
    public void user_navigates_to_the_pick_list_page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("config.properties", "picklistURL"));
    }

    @Then("user verifies the Pick List table column names")
    public void user_verifies_the_pick_list_table_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("name");
            String actualName = picklist.picklistTableColumnNames.get(i).getText();
            Assert.assertTrue(expectedName.contains(actualName));
            i++;
        }
    }

    @When("user clicks on Pick List Job")
    public void user_clicks_on_pick_list_job() throws InterruptedException {
           picklist.picklistJobs.get(0).click();
           wait.hardWait(1000);
    }

    @Then("user verifies job number is displayed")
    public void user_verifies_job_number_is_displayed() {
        WaitHelper.waitForVisibility(picklist.picklistJobNumber,1000);
        Assert.assertTrue(picklist.picklistJobNumber.isDisplayed());
    }

    @Then("Edit option is displayed")
    public void edit_option_is_displayed() {
        WaitHelper.waitForVisibility(picklist.editJob, 100);
        Helper.verifyElementDisplayed(picklist.editJob);
    }

    @Then("Delete Job option is displayed")
    public void delete_job_option_is_displayed() {
        WaitHelper.waitForVisibility(picklist.deleteJob, 100);
        Helper.verifyElementDisplayed(picklist.deleteJob);
    }

    @Then("user verifies the Items in Job table column names")
    public void user_verifies_the_items_in_job_table_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 1;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("name");
            String actualName = picklist.itemsInJobTableColumnNames.get(i).getText();
            Assert.assertEquals("Section names verification failed",
                    expectedName, actualName);
            i++;
        }
    }

    @When("user clicks Retrieve Pick List")
    public void user_clicks_retrieve_pick_list() throws InterruptedException {
        if (picklist.retrievePickList.isDisplayed()) {
            Helper.clickWithJS(picklist.retrievePickList);
            alert.closeToastMsg.click();
        }else{
            alert.closeToastMsg.click();
        }
    }

    @Then("user verifies the item is retrieved")
    public void user_verifies_the_item_is_retrieved() {
        WaitHelper.waitForVisibility(picklist.retrievedItem,700);
        Assert.assertEquals("Retrieved", picklist.retrievedItem.getText());

    }

    @And("user scans a Pick List Container")
    public void user_scans_a_pick_list_container() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys(picklist.containerBarcode.getText());
        wait.hardWait(2000);
    }

    @And("user saves an item barcode")
    public void user_saves_item_barcode() {
        itemBarcode = picklist.containerBarcode.getText();
        System.out.println("Item barcode is : " + itemBarcode);
    }


}
