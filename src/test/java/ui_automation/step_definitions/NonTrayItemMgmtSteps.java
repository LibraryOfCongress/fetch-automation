package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.NonTrayItemMgmtPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;

import java.util.List;
import java.util.Map;

public class NonTrayItemMgmtSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    NonTrayItemMgmtPage nonTrayMgmt = new NonTrayItemMgmtPage();


    @Given("user navigates to Non-Trayed Item Management Page")
    public void user_navigates_to_Non_Trayed_Item_Management_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "nonTrayItemMgmtURL"));
    }

    @Then("the name of shelf is displayed")
    public void the_name_of_shelf_is_displayed() {
        Helper.verifyElementDisplayed(nonTrayMgmt.shelfHeader);
    }

    @Then("user verifies shelf labels on Non-Trayed Items Management Page")
    public void user_verifies_shelf_labels_on_Non_Trayed_Items_Management_Page(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = nonTrayMgmt.nonTrayLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
    }

    @Then("user verifies non-trayed items labels on Non-Trayed Items Management Page")
    public void user_verifies_non_trayed_items_labels_on_Non_Trayed_Items_Management_Page(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = nonTrayMgmt.itemsLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
    }
}

