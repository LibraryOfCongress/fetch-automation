package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.TrayMgmtPage;
import ui_automation.utilities.*;

import java.util.List;
import java.util.Map;

public class TrayMgmtSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    TrayMgmtPage trayMgmt = new TrayMgmtPage();
    Helper helper = new Helper();
    GenericHelper generic = new GenericHelper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();

    public static final Logger oLog = LogManager.getLogger(TrayMgmtSteps.class);


    @Given("user navigates to Item Management Page")
    public void user_navigates_to_Item_Management_Page() throws InterruptedException {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties","itemManagementURL"));
        oLog.info("I navigated to Item Management Page");
    }

    @When("user looks at the tray header")
    public void user_looks_at_the_tray_header() {
        generic.IsElementPresentQuick(By.cssSelector(".col > .text-h4"));
    }

    @Then("the name of tray is displayed")
    public void the_name_of_tray_is_displayed() {
        helper.verifyElementDisplayed(trayMgmt.trayHeader);
        String expectedTrayHeader = "Tray Mctray Photograph Archive - 332";
        String actualTrayHeader = trayMgmt.trayHeader.getText();
        Assert.assertEquals("Tray Header verification failed",
                expectedTrayHeader, actualTrayHeader);
        oLog.info("I verified Tray Header");
    }

    @Then("tray barcode is visible")
    public void tray_barcode_is_visible() {
        trayMgmt.trayBarcodeText.getText();
    }


    @Then("filter columns dropdown is visible and clickable")
    public void filter_columns_button_is_visible_and_clickable() {
        helper.verifyElementDisplayed(trayMgmt.filterColumns);
        WaitHelper.waitForClickability(trayMgmt.filterColumns, 10);
        trayMgmt.filterColumns.click();
    }

    @And("user verifies filter column options")
    public void user_verifies_filter_column_options(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for(Map<String, String> map: maps) {
            helper.isClickable(trayMgmt.filterOptions.get(i));
            String expectedLabel = map.get("columnname");
            String actualLabel = trayMgmt.filterOptions.get(i).getText();
            Assert.assertEquals("Columnname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified Filter Columns Options");
    }


    @Then("user verifies tray labels on Items Management Page")
    public void user_verifies_tray_details_labels_on_Items_Management_Page(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for(Map<String, String> map: maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = trayMgmt.trayLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified Tray Labels");
    }

    @Then("user verifies items labels on Items Management Page")
    public void user_verifies_items_labels_on_Items_Management_Page(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for(Map<String, String> map: maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = trayMgmt.itemsLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified Items Labels");
    }


}
