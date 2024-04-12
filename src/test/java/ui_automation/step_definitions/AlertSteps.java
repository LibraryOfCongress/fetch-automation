package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.AccessionPage;
import ui_automation.pages.AlertPage;
import ui_automation.utilities.*;

import static org.junit.Assert.assertEquals;

public class AlertSteps {


    WebDriver driver = Driver.getInstance().getDriver();
    AlertPage alert = new AlertPage();
    Helper helper = new Helper();
    AlertHelper alertHelper = new AlertHelper();
    SelectHelper select = new SelectHelper();
    AccessionPage accession = new AccessionPage();
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
        String expectedMsg = "This is a user generated error message\n" +
                "close";
      //  System.out.println("Actual alert message: " + msg);
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
      //  System.out.println("Actual alert msg: " + msg);
        String expectedMsg = "This is a user generated error message with audio";
        assertEquals(expectedMsg, msg);
    }

    @Then("user is able to click cancel button")
    public void user_is_able_to_click_cancel_button() {
        alert.cancelPersistentAlert.click();
    }

    @When("user navigates to Accession Job link")
    public void user_navigates_to_Accession_Job_link() {
        driver.get("https://test.fetch.loctest.gov/accession/16");
        oLog.info("I navigated to Accession Job");
    }

    @When("user enters {string} barcode and clicks Submit button")
    public void user_enters_barcode_and_clicks_Submit_button(String string) throws InterruptedException {
        accession.enterBarcodeField.sendKeys(string);
        accession.submitBtn.click();
        oLog.info("I entered barcode and clicked submit button");
    }

    @Then("user verifies {string} alert msg")
    public void user_verifies_alert_msg(String string)  {
        assertEquals(string, alert.toastMsg.getText());
        oLog.info("I verified alert notification message");
    }

    @When("user clicks Delete")
    public void user_clicks_Delete() throws InterruptedException {
        accession.deleteBtn.click();
        Thread.sleep(7000);
        oLog.info("I clicked delete button");
    }

    @When("user clicks Confirm")
    public void user_clicks_Confirm()  {
        accession.confirmDelete.click();
        oLog.info("I confirmed I want to delete a barcode");
    }



}
