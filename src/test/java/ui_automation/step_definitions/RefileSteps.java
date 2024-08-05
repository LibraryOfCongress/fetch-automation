package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.RefilePage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;
import ui_automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;


public class RefileSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    RefilePage refile = new RefilePage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();



    @Given("user navigates to the Refile Page")
    public void user_navigates_to_the_Refile_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "refileURL"));
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
    public void user_selects_add_item_to_queue_option() {
        helper.jSClick(refile.refileDropdownOptions.get(0));
    }


}
