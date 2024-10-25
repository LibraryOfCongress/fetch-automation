package automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.pages.RefilePage;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import automation.utilities.Helper;
import automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;


public class RefileSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    RefilePage refile = new RefilePage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    static String trayBarcodeValue = "";


    @Given("user navigates to the Refile Page")
    public void user_navigates_to_the_refile_page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("config.properties", "refileURL"));
    }

    @Then("user verifies Refile Job table is displayed")
    public void user_verifies_refile_job_table_is_displayed() {
        Helper.verifyElementDisplayed(refile.table);
    }

    @Then("user verifies Refile Job table column names")
    public void user_verifies_refile_job_table_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("name");
            String actualName = refile.refileTableColumnNames.get(i).getText();
            Assert.assertEquals("Column names verification failed",
                    expectedName, actualName);
            i++;
        }
    }

    @When("user clicks Refile Queue")
    public void user_clicks_refile_queue() {
        helper.jSClick(refile.refileQueueBtn);
    }

    @Then("user verifies Refile Queue table is displayed")
    public void user_verifies_refile_queue_table_is_displayed() {
        Helper.verifyElementDisplayed(refile.table);
    }

    @Then("user verifies Refile Queue table column names")
    public void user_verifies_refile_queue_table_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("name");
            String actualName = refile.refileTableColumnNames.get(i).getText();
            Assert.assertEquals("Column names verification failed",
                    expectedName, actualName);
            i++;
        }
    }

    @When("user selects Add Item to Queue option")
    public void user_selects_add_item_to_queue_option() throws InterruptedException {
        helper.jSClick(refile.refileDropdownOptions.get(0));
        wait.hardWait(1000);
    }

    @When("user scans an Item Barcode from a completed Pick List job")
    public void user_enters_an_item_barcode_from_a_completed_pick_list_job() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys(PickListSteps.itemBarcode);
        wait.hardWait(2000);
        refile.doneBtn.click();
    }

    @When("user selects Create Refile Job option")
    public void user_selects_create_refile_job_option() throws InterruptedException {
        helper.jSClick(refile.refileDropdownOptions.get(2));
        wait.hardWait(1000);
    }

    @When("user clicks Create Refile Job")
    public void user_clicks_create_refile_job() throws InterruptedException {
        helper.jSClick(refile.createRefileJobBtn);
        wait.hardWait(1000);
    }

    @Then("user verifies the Refile job is created")
    public void user_verifies_the_refile_job_is_created() {
        WaitHelper.waitForVisibility(refile.alertText, 1000);
        Assert.assertTrue(refile.alertText.getText().contains("Successfully created Refile Job #: "));
    }

    @Then("user verifies Tray Barcode is displayed")
    public void user_verifies_tray_barcode_is_displayed() {
        refile.trayBarcodeValue.isDisplayed();
        trayBarcodeValue = refile.trayBarcodeValue.getText();
    }

    @Then("user verifies scan modal is displayed")
    public void user_verifies_scan_modal_is_displayed() throws InterruptedException {
        Helper.verifyElementDisplayed(refile.scanModal);
        wait.hardWait(1000);
    }

    @Then("user verifies the information on the scan modal")
    public void user_verifies_the_information_on_the_scan_modal() {
        if (refile.scanModalLabels.get(1).getText().contains("Tray Barcode:")) {
            String value = driver.findElement(By.xpath("(//p[@class='text-body1'] )[5]")).getText();
            Assert.assertEquals(value, trayBarcodeValue);
        } else {
            System.out.println("This is a Non-Tray Item");
        }
    }

    @When("user clicks on Refile Job")
    public void user_clicks_on_refile_job() throws InterruptedException {
        refile.refileJobsList.get(0).click();
        wait.hardWait(1000);
    }
}



