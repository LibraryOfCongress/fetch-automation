package ui_automation.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.VerificationPage;
import ui_automation.utilities.*;

import static org.junit.Assert.assertEquals;

public class VerificationSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    VerificationPage verification = new VerificationPage();
    Helper helper = new Helper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    AlertHelper alert = new AlertHelper();

    public static final Logger oLog = LogManager.getLogger(AccessionSteps.class);


    @When("user clicks on Verification Job for a Trayed Item")
    public void user_clicks_on_Verification_Job_for_a_Trayed_Item()  {
        verification.trayedVerificationJob.click();
    }

    @Then("Tray container view is displayed")
    public void tray_container_view_is_displayed() {
        assertEquals("Please Scan Tray", verification.scanTrayBox.getText());
    }

    @Then("user scans a Tray")
    public void user_scans_a_Tray()  {
        driver.findElement(By.tagName("body")).sendKeys("CH220987!");
        driver.findElement(By.tagName("body")).sendKeys("CH220987!");
        oLog.info("I scanned Barcode ");
    }

    @Then("user verifies the entered barcode is displayed")
    public void user_verifies_the_entered_barcode_is_displayed()  {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Scanned Barcode is not displayed!", verification.scannedVerificationItems.get(index-1).getText(), "12345");
        oLog.info("Entered barcode is displayed under Scanned Items");
    }

    @When("user selects one of the barcodes")
    public void user_selects_one_of_the_barcodes() throws InterruptedException {
        int index = verification.scannedItemsCheckbox.size();
        verification.scannedItemsCheckbox.get(index-1).click();
    }

    @Then("verify the updated barcode is displayed")
    public void verify_the_updated_barcode_is_displayed() {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Updated Barcode is not displayed!", verification.scannedVerificationItems.get(index-1).getText(), "54321");
        oLog.info("Edited barcode is displayed");
    }

    @Then("user deselects the edited barcode")
    public void user_deselects_the_edited_barcode() {
        int index = verification.scannedItemsCheckbox.size();
        verification.scannedItemsCheckbox.get(index-1).click();
    }

    @Then("verify Next Tray button is activated")
    public void verify_Next_Tray_button_is_activated() {
        assertEquals(true, verification.nextTrayBtn.isEnabled());
        oLog.info("Next Tray button is activated");
    }

    @Then("user clicks Next Tray button")
    public void user_clicks_Next_Tray_button() {
        verification.nextTrayBtn.click();
    }

    @Then("user clicks on new tray on the modal")
    public void user_clicks_on_new_tray_on_the_modal() {
        verification.newTrays.get(0).click();
    }

    @When("user clicks on Verification Job for a Non-Trayed Item")
    public void user_clicks_on_Verification_Job_for_a_Non_Trayed_Item() {
        verification.trayedVerificationJob.click();
    }

    @Then("user verifies non-trayed items container view is displayed")
    public void user_verifies_non_trayed_items_container_view_is_displayed() {
        assertEquals("Please Scan Non-Tray", verification.scanTrayBox.getText());
    }

    @When("user verifies all the barcodes")
    public void user_verifies_all_the_barcodes()  {
        int i = 0;
        for(WebElement item: verification.scannedVerificationItems) {
            verification.enterBarcodeBtn.click();
            verification.enterBarcodeField.sendKeys(item.getText());
            verification.submitBtn.click();
            i++;
        }
    }

    @Then("user verifies Complete Job button is activated")
    public void user_verifies_Complete_Job_button_is_activated() {
        assertEquals(true, verification.completeJob.isEnabled());
        oLog.info("Complete Job button is activated");
    }








}
