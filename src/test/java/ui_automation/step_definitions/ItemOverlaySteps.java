package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.ItemOverlayPage;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;
import ui_automation.utilities.SelectHelper;
import ui_automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;

public class ItemOverlaySteps {

    WebDriver driver = Driver.getInstance().getDriver();
    ItemOverlayPage itemOverlay = new ItemOverlayPage();
    Helper helper = new Helper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    public static final Logger oLog = LogManager.getLogger(ItemOverlaySteps.class);


    @When("user clicks on any item in the table of items")
    public void user_clicks_on_any_item_in_the_table_of_items() {
        itemOverlay.item1.click();
    }

    @Then("the overlay slide is visible")
    public void the_overlay_slide_is_visible()  {
        wait.waitForVisibility(itemOverlay.sideOverlay, 3000);
    }

    @And("user verifies item in tray details on Overlay Slide")
    public void userVerifiesItemInTrayDetailsOnOverlaySlide(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for(Map<String, String> map: maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = itemOverlay.overlayItemsLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified Item in Tray Details on Overlay Slide");
    }


    @Then("user verifies item details on Overlay Slide")
    public void user_verifies_item_details_on_Overlay_Slide(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for(Map<String, String> map: maps) {
            String expectedLabel = map.get("labelname");
            String actualLabel = itemOverlay.overlayItemsLabels.get(i).getText();
            Assert.assertEquals("Labelname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
    }


    @And("the x button is clickable")
    public void theXButtonIsClickable() {
        helper.isClickable(itemOverlay.closeBtn);
        helper.jSClick(itemOverlay.closeBtn);
    }

    @And("user clicks outside of overlay")
    public void userClicksOutsideOfOverlay() throws InterruptedException {
        wait.hardWait(2000);
        helper.jSClick(itemOverlay.mainPage);
    }

    @Then("the overlay slide is not visible")
    public void theOverlaySlideIsNotVisible() {
        helper.verifyElementNotDisplayed(itemOverlay.sideOverlay);

    }
}


