package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.AccessionPage;
import ui_automation.pages.VerificationPage;
import ui_automation.utilities.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class VerificationSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    VerificationPage verification = new VerificationPage();
    AccessionPage accession = new AccessionPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    ThreadLocalRandom random = ThreadLocalRandom.current();
    long random1 = random.nextLong(10000000000L, 100000000000L);
    long random2 = random.nextLong(10000000000L, 100000000000L);
    static String verifificationJobNumber = "";

    public static final Logger oLog = LogManager.getLogger(AccessionSteps.class);


    @When("user clicks on Verification Job for a Trayed Item")
    public void user_clicks_on_Verification_Job_for_a_Trayed_Item() {
        verification.trayedVerificationJob.click();
    }

    @Then("Tray container view is displayed")
    public void tray_container_view_is_displayed() {
        assertEquals("Please Scan Tray", verification.scanTrayBox.getText());
    }

    @Then("user scans a Tray")
    public void user_scans_a_Tray() {
        driver.findElement(By.tagName("body")).sendKeys("!AH787878!");
        oLog.info("I scanned Barcode ");
    }

    @Then("user verifies the entered barcode is displayed")
    public void user_verifies_the_entered_barcode_is_displayed() {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Scanned Barcode is not displayed!", verification.scannedVerificationItems.get(index - 1).getText(), "12345");
        oLog.info("Entered barcode is displayed under Scanned Items");
    }

    @When("user selects the barcode")
    public void user_selects_the_barcodes() throws InterruptedException {
        int index = verification.scannedItemsCheckbox.size();
        verification.scannedItemsCheckbox.get(index - 1).click();
    }

    @Then("verify the updated barcode is displayed")
    public void verify_the_updated_barcode_is_displayed() {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Updated Barcode is not displayed!", verification.scannedVerificationItems.get(index - 1).getText(), "54321");
        oLog.info("Edited barcode is displayed");
    }

    @Then("user deselects the edited barcode")
    public void user_deselects_the_edited_barcode() {
        int index = verification.scannedItemsCheckbox.size();
        verification.scannedItemsCheckbox.get(index - 1).click();
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
        verification.nonTrayedVerificationJob.click();
    }

    @Then("user verifies non-trayed items container view is displayed")
    public void user_verifies_non_trayed_items_container_view_is_displayed() {
        assertEquals("Please Scan Non Tray", verification.scanTrayBox.getText());
    }

    @When("user verifies all the barcodes")
    public void user_verifies_all_the_barcodes() throws InterruptedException {
        int i = 0;
        for (WebElement item : verification.scannedVerificationItems) {
            verification.enterBarcodeBtn.click();
            wait.waitForClickability(verification.enterBarcodeField,1000);
            verification.enterBarcodeField.sendKeys(item.getText());
            verification.submitBtn.click();
            i++;
        }
        wait.hardWait(2000);
    }


    @When("user verifies the barcode")
    public void user_verifies_the_barcode() throws InterruptedException {
        wait.waitForClickability(verification.enterBarcodeBtn, 1000);
        verification.enterBarcodeBtn.click();
        verification.enterBarcodeField.sendKeys(verification.scannedVerificationItems.get(0).getText());
        verification.submitBtn.click();
        wait.hardWait(2000);
    }

    @Then("user verifies Complete Job button is activated")
    public void user_verifies_Complete_Job_button_is_activated() {
        assertEquals(true, verification.completeJob.isEnabled());
        oLog.info("Complete Job button is activated");
    }


    @When("user navigates to the verification job link")
    public void user_navigates_to_the_verification_job_link() {
        driver.get("https://test.fetch.loctest.gov/verification/1234567892/scan-items/CH220987");
        oLog.info("I navigated to the verification job link");
    }


    @And("user verifies barcode")
    public void user_verifies_barcode() throws InterruptedException {
        wait.hardWait(2000);
        driver.findElement(By.tagName("body")).sendKeys("!000000988989!");
    }

    @When("user clicks most recent Verification Job for a Trayed Item")
    public void userClicksMostRecentVerificationJobForATrayedItem() {
        helper.jSClick(verification.trayedJobList.get(verification.trayedJobList.size()-1));
        oLog.info("I clicked most recent Verification Job for a Trayed Item");
    }


    @When("user clicks most recent Verification Job for a Non-Trayed Item")
    public void userClicksMostRecentVerificationJobForANonTrayedItem() {
        helper.jSClick(verification.nonTrayedJobList.get(verification.nonTrayedJobList.size()-1));
        oLog.info("I clicked most recent Verification Job for a Non-Trayed Item");
    }


    @When("user clicks three dot menu next to Job Number")
    public void user_clicks_three_dot_menu_next_to_Job_Number() throws InterruptedException {
        helper.jSClick(verification.threeDot);
        wait.hardWait(2000);
        oLog.info("I clicked three dot menu");
    }


    @When("user edits Owner field")
    public void user_edits_Owner_field() throws InterruptedException {
        helper.scrollIntoViewAndClick(verification.editOwnerField);
        verification.editFieldOptions.get(3).click();
        wait.hardWait(1000);
    }


    @When("user edits Container Size field")
    public void user_edits_Container_Size_field() throws InterruptedException {
        helper.scrollIntoViewAndClick(verification.editContainerSizeField);
        verification.editFieldOptions.get(2).click();
        wait.hardWait(1000);
    }

    @When("user edits Media Type field")
    public void user_edits_Media_Type_field() throws InterruptedException {
        wait.waitForClickability(verification.editMediaTypeField, 1000);
        helper.jSClick(verification.editMediaTypeField);
        verification.editFieldOptions.get(4).click();
        wait.hardWait(1000);
    }


    @Then("user enters the barcode and clicks Submit button")
    public void user_enters_the_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Long.toString(random1));
        helper.jSClick(accession.submitBtn);
        Thread.sleep(1000);
        oLog.info("I entered barcode and clicked Submit");
    }


    @Then("verify the entered barcode is displayed")
    public void verify_the_entered_barcode_is_displayed()  {
        assertEquals("Scanned Barcode is not displayed!", Long.toString(random1), verification.scannedItemList.get(verification.scannedItemList.size()-1).getText());
        oLog.info("Entered barcode is displayed");
    }


    @Then("user edits the barcode and clicks Submit button")
    public void user_edits_the_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.sendKeys(Keys.CONTROL + "a");
        accession.enterBarcodeField.sendKeys(Keys.DELETE);
        accession.enterBarcodeField.sendKeys(Long.toString(random2));
        accession.submitBtn.click();
        wait.hardWait(2000);
    }


    @Then("verify the edited barcode is displayed")
    public void verify_the_edited_barcode_is_displayed() {
        wait.waitForVisibility(verification.scannedItemList.get(verification.scannedItemList.size()-1), 2000);
        assertEquals("Edited Barcode is not displayed!", Long.toString(random2), verification.scannedItemList.get(verification.scannedItemList.size()-1).getText());
        oLog.info("Edited barcode is displayed");
    }


    @When("user scans Tray Barcode")
    public void user_scans_Tray_Barcode() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys(AccessionSteps.generatedTray);
       oLog.info("I scanned Barcode ");
    }


    @And("user saves Verification Job number")
    public void user_saves_Verification_job_number() {
        WebElement vJobNumber = driver.findElement(By.xpath("(//div[@class='breadcrumb-items'])[3]"));
        verifificationJobNumber = vJobNumber.getText();
        System.out.println("Verification Job Number: " + verifificationJobNumber);

    }

}
