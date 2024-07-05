package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.PickListPage;
import ui_automation.pages.RequestPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;
import ui_automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;

public class PickListSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    PickListPage picklist = new PickListPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();


    public static final Logger oLog = LogManager.getLogger(PickListSteps.class);


    @Given("user navigates to the Pick List Page")
    public void user_navigates_to_the_Pick_List_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "picklistURL"));
    }

    @Then("user verifies the Pick List table column names")
    public void user_verifies_the_pick_list_table_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("name");
            String actualName = picklist.picklistTableColumnNames.get(i).getText();
            Assert.assertEquals("Section names verification failed",
                    expectedName, actualName);
            i++;
        }
        oLog.info("I verified Pick List table column names");
    }

    @When("user clicks on Pick List Job")
    public void user_clicks_on_pick_list_job() throws InterruptedException {
        for(WebElement tab: picklist.picklistTableRows) {
            if(tab.getText().contains("11")) {
               tab.click();
               wait.hardWait(2000);
            }
        }
//        picklist.picklistTableRows.get(0).click();
        oLog.info("I clicked on Pick List Job");
    }

    @Then("user verifies Pick List number is displayed")
    public void user_verifies_pick_list_number_is_displayed() {
        wait.waitForVisibility(picklist.picklistJobNumber,1000);
        String jobNumber = picklist.picklistJobNumber.getText();
        System.out.println("Job NUMBER: " +jobNumber);
        Assert.assertEquals("11", jobNumber);
        oLog.info("I verified Pick List Job number");
    }

    @When("user clicks three dot menu next to Pick List Number")
    public void user_clicks_three_dot_menu_next_to_pick_list_number() {
        helper.jSClick(picklist.threeDotNextToPickListJob);
        oLog.info("I clicked three dot menu");
    }

    @Then("Edit button is displayed")
    public void edit_button_is_displayed() {
        wait.waitForVisibility(picklist.editButton, 100);
        helper.verifyElementDisplayed(picklist.editButton);
        oLog.info("Edit button is displayed");
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
        oLog.info("I verified Items in Job table column names");
    }

}
