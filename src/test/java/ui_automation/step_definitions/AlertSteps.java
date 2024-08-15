package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.AccessionPage;
import ui_automation.pages.AlertPage;
import ui_automation.utilities.*;

import static org.junit.Assert.assertEquals;

public class AlertSteps {


    WebDriver driver = Driver.getInstance().getDriver();
    AlertPage alert = new AlertPage();
    Helper helper = new Helper();
    AccessionPage accession = new AccessionPage();
    WaitHelper wait = new WaitHelper();


    @Given("user navigates to the testing link")
    public void user_navigates_to_the_testing_link() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "alertTestURL"));
    }

    @When("user clicks on the Show Generic Alert button")
    public void user_clicks_on_the_Show_Generic_Alert_button() throws InterruptedException {
        helper.jSClick(alert.genericAlert);
        wait.hardWait(1000);
    }

    @Then("user verifies UI alert on top of the screen is visible")
    public void user_verifies_UI_alert_on_top_of_the_screen_is_visible() {
        String msg = alert.alertMsg.getText();
        String expectedMsg = "This is a user generated error message\n" +
                "close";
        assertEquals(expectedMsg, msg);
    }

    @Then("user is able to click X to cancel alert")
    public void user_is_able_to_click_X_to_cancel_alert() {
        helper.jSClick(alert.cancelGenAlert);
    }

    @When("user clicks on the Show Persistent Alert button")
    public void user_clicks_on_the_Show_Persistent_Alert_button() {
        helper.jSClick(alert.persistentAlert);
    }

    @Then("user verifies alert popup is visible")
    public void user_verifies_alert_popup_is_visible() {
        WaitHelper.waitForVisibility(alert.audioAlertMsg, 100);
        String msg = alert.audioAlertMsg.getText();
        String expectedMsg = "This is a user generated error message with audio";
        assertEquals(expectedMsg, msg);
    }

    @Then("user is able to click cancel button")
    public void user_is_able_to_click_cancel_button() {
        alert.cancelPersistentAlert.click();
    }

    @When("user navigates to Accession Job for a Non-Trayed Item")
    public void user_navigates_to_Accession_Job_for_a_Non_Trayed_Item() {
        alert.nonTrayedAccessionJob.click();
    }

    @When("user enters {string} barcode and clicks Submit button")
    public void user_enters_barcode_and_clicks_Submit_button(String string) {
        accession.enterBarcodeField.sendKeys(string);
        accession.submitBtn.click();
    }

    @Then("user verifies {string} alert msg")
    public void user_verifies_alert_msg(String string) throws InterruptedException {
        WaitHelper.waitForVisibility(alert.toastMsg, 1000);
        assertEquals(string, alert.toastMsg.getText());
        alert.closeToastMsg.click();
        wait.hardWait(3000);
    }

    @When("user clicks Delete")
    public void user_clicks_Delete() {
        WaitHelper.waitForClickability(accession.deleteBtn, 3000);
        helper.jSClick(accession.deleteBtn);
    }

    @When("user clicks Confirm")
    public void user_clicks_Confirm() throws InterruptedException {
        WaitHelper.waitForClickability(accession.confirmDelete, 2000);
        helper.jSClick(accession.confirmDelete);
        wait.hardWait(2000);
    }

    @Then("user verifies {string}")
    public void user_verifies(String string) {
        WaitHelper.waitForVisibility(alert.completedAndMovedForVerificationMsg, 5000);
        assertEquals(string, alert.completedAndMovedForVerificationMsg.getText());
        alert.closeToastMsg.click();
    }

    @Then("user verifies {string} msg")
    public void user_verifies_msg(String string) {
        WaitHelper.waitForVisibility(alert.theJobHasBeenCompleted, 1000);
        assertEquals(string, alert.theJobHasBeenCompleted.getText());
        alert.closeToastMsg.click();
    }

    @Then("user verifies {string} notification")
    public void user_verifies_notification(String string) {
        WaitHelper.waitForVisibility(alert.shelvingJobCreated, 1000);
        assertEquals(string, alert.shelvingJobCreated.getText());
        alert.closeToastMsg.click();
    }

    @Then("user verifies {string} message")
    public void user_verifies_message(String string) throws InterruptedException {
        WaitHelper.waitForVisibility(alert.theContainerHasBeenUpdated, 1000);
        assertEquals(string, alert.theContainerHasBeenUpdated.getText());
        alert.closeToastMsg.click();
        wait.hardWait(1000);
    }


}


