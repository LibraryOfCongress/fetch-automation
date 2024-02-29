package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.AlertPage;
import ui_automation.utilities.*;

import static org.junit.Assert.assertEquals;

public class AlertSteps {


    WebDriver driver = Driver.getInstance().getDriver();
    AlertPage alert = new AlertPage();
    Helper helper = new Helper();
    AlertHelper alertHelper = new AlertHelper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    GenericHelper genhelp = new GenericHelper();

    public static final Logger oLog = LogManager.getLogger(AccessionSteps.class);


    @Given("user navigates to the testing link")
    public void user_navigates_to_the_testing_link() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "alertTestURL"));
        oLog.info("I navigated to Alert testing page");
    }

    @When("user clicks on the Show Generic Alert button")
    public void user_clicks_on_the_Show_Generic_Alert_button() throws InterruptedException {
        helper.jSClick(alert.genericAlert);
        oLog.info("I clicked on Show Generic Alert");
        Thread.sleep(1000);
    }

    @Then("user verifies UI alert on top of the screen is visible")
    public void user_verifies_UI_alert_on_top_of_the_screen_is_visible() {
        String msg = alert.alertMsg.getText();
        String expectedMsg = "This is a user generated error message ";
        System.out.println("Actual alert message: " + msg);
        assertEquals(expectedMsg, msg);
    }

    @Then("user is able to click X to cancel alert")
    public void user_is_able_to_click_X_to_cancel_alert() throws InterruptedException {
        alert.cancelGenAlert.click();
    }

    @When("user clicks on the Show Persistent Alert button")
    public void user_clicks_on_the_Show_Persistent_Alert_button() {
        helper.jSClick(alert.persistentAlert);
    }

    @Then("user verifies alert popup is visible")
    public void user_verifies_alert_popup_is_visible() {
        String msg = alert.audioAlertMsg.getText();
        String expectedMsg = "This is a user generated error message with audio";
        assertEquals(expectedMsg, msg);
    }

    @Then("user is able to click cancel button")
    public void user_is_able_to_click_cancel_button() {
        alert.cancelPersistentAlert.click();
    }

}
