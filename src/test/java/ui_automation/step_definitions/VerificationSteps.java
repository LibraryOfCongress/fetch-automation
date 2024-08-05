package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.AccessionPage;
import ui_automation.pages.VerificationPage;
import ui_automation.utilities.*;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class VerificationSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    VerificationPage verification = new VerificationPage();
    AccessionPage accession = new AccessionPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    ThreadLocalRandom random = ThreadLocalRandom.current();
    long random01 = random.nextLong(10000000000L, 100000000000L);
    long random02 = random.nextLong(10000000000L, 100000000000L);
    long scanned01 = random.nextLong(10000000000L, 100000000000L);
    long scanned02 = random.nextLong(10000000000L, 100000000000L);
    static String verifificationJobNumber = "";


    @Given("user navigates to the Verification Page")
    public void user_navigates_to_the_Verification_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "verificationURL"));
        driver.manage().window().maximize();
    }

    @When("user clicks on Verification Job for a Trayed Item")
    public void user_clicks_on_Verification_Job_for_a_Trayed_Item() throws InterruptedException {
        helper.scrollIntoView(verification.trayedVerificationJobs.get(verification.trayedVerificationJobs.size() - 1));
        wait.hardWait(1000);
        verification.trayedVerificationJobs.get(verification.trayedVerificationJobs.size() - 1).click();
    }

    @Then("Tray container view is displayed")
    public void tray_container_view_is_displayed() throws InterruptedException {
        assertEquals("Please Scan Tray", verification.scanTrayBox.getText());
    }

    @Then("user scans a Tray")
    public void user_scans_a_Tray() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys("EL222222");
        wait.hardWait(1000);
    }

    @Then("user verifies the entered barcode is displayed")
    public void user_verifies_the_entered_barcode_is_displayed() {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Entered Barcode is not displayed!", verification.scannedVerificationItems.get(index - 1), AccessionSteps.entered1);
    }

    @Then("verify the updated barcode is displayed")
    public void verify_the_updated_barcode_is_displayed() {
        int index = verification.scannedVerificationItems.size();
        assertEquals("Updated Barcode is not displayed!", verification.scannedVerificationItems.get(index - 1).getText(), "54321");
    }

    @Then("user deselects the edited barcode")
    public void user_deselects_the_edited_barcode() {
        int index = verification.scannedItemsCheckbox.size();
        verification.scannedItemsCheckbox.get(index - 1).click();
    }

    @Then("verify Next Tray button is activated")
    public void verify_Next_Tray_button_is_activated() throws InterruptedException {
        helper.scrollIntoView(verification.nextTrayBtn);
        assertEquals(true, verification.nextTrayBtn.isEnabled());
    }

    @Then("user clicks Next Tray button")
    public void user_clicks_Next_Tray_button() throws InterruptedException {
        helper.scrollIntoView(verification.nextTrayBtn);
        helper.jSClick(verification.nextTrayBtn);
        wait.hardWait(1000);
    }

    @Then("user clicks on new tray on the modal")
    public void user_clicks_on_new_tray_on_the_modal() throws InterruptedException {
        verification.newTrays.get(0).click();
        wait.hardWait(1000);
    }

    @When("user clicks on Verification Job for a Non-Trayed Item")
    public void user_clicks_on_Verification_Job_for_a_Non_Trayed_Item() {
        verification.nonTrayedVerificationJob.click();
    }

    @Then("user verifies non-trayed items container view is displayed")
    public void user_verifies_non_trayed_items_container_view_is_displayed() {
        assertEquals("Please Scan Non Tray", verification.scanTrayBox.getText());
    }

    @When("user verifies second barcode")
    public void user_verifies_second_barcode() throws InterruptedException {
        wait.hardWait(3000);
        verification.enterBarcodeBtn.click();
        wait.waitForClickability(verification.enterBarcodeField, 1000);
        verification.enterBarcodeField.sendKeys(verification.scannedVerificationItems.get(1).getText());
        verification.submitBtn.click();
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

    @When("user verifies the barcode by scanning")
    public void user_verifies_the_barcode_by_scanning() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys(verification.scannedVerificationItems.get(0).getText());
        wait.hardWait(1000);
    }

    @Then("user verifies Complete Job button is activated")
    public void user_verifies_Complete_Job_button_is_activated() {
        assertEquals(true, verification.completeJob.isEnabled());
    }

    @When("user navigates to the verification job link")
    public void user_navigates_to_the_verification_job_link() {
        driver.get("https://test.fetch.loctest.gov/verification/1234567892/scan-items/CH220987");
    }

    @And("user verifies barcode")
    public void user_verifies_barcode() throws InterruptedException {
        wait.hardWait(2000);
        driver.findElement(By.tagName("body")).sendKeys("!000000988989!");
    }

    @When("user clicks most recent Verification Job for a Trayed Item")
    public void user_clicks_most_recent_Verification_Job_for_a_Trayed_Item() {
        helper.jSClick(verification.trayedJobList.get(verification.trayedJobList.size() - 1));
    }

    @When("user clicks most recent Verification Job for a Non-Trayed Item")
    public void user_clicks_most_recent_Verification_Job_for_a_NonTrayed_Item() {
        helper.jSClick(verification.nonTrayedJobList.get(verification.nonTrayedJobList.size() - 1));
    }

    @When("user clicks three dot menu next to Job Number")
    public void user_clicks_three_dot_menu_next_to_Job_Number() throws InterruptedException {
        helper.jSClick(verification.threeDot);
        wait.hardWait(2000);
    }

    @When("user edits Owner field")
    public void user_edits_Owner_field() throws InterruptedException {
        helper.scrollIntoViewAndClick(verification.editOwnerField);
        verification.editFieldOptions.get(4).click();
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
        accession.enterBarcodeField.sendKeys(Long.toString(random01));
        helper.jSClick(accession.submitBtn);
        wait.hardWait(1000);
    }

    @Then("user confirms they want to add a new item to the job")
    public void user_confirms_they_want_to_add_a_new_item_to_the_job() throws InterruptedException {
        Helper.clickWithJS(verification.addNewItem);
        wait.hardWait(1000);
    }


    @Then("user edits the barcode and clicks Submit button")
    public void user_edits_the_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.sendKeys(Keys.CONTROL + "a");
        accession.enterBarcodeField.sendKeys(Keys.DELETE);
        accession.enterBarcodeField.sendKeys(Long.toString(random02));
        accession.submitBtn.click();
        wait.hardWait(2000);
    }

    @Then("verify the edited barcode is displayed")
    public void verify_the_edited_barcode_is_displayed() {
        wait.waitForVisibility(verification.scannedItemList.get(verification.scannedItemList.size() - 1), 2000);
        assertEquals("Edited Barcode is not displayed!", Long.toString(random02), verification.scannedItemList.get(verification.scannedItemList.size() - 1).getText());
    }

    @And("user scans item barcode")
    public void user_scans_item_barcode() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("" + scanned01 + "");
        wait.hardWait(2000);
    }

    @And("user scans items barcode")
    public void user_scans_items_barcode() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("" + scanned02 + "");
        wait.hardWait(2000);
    }

    @When("user scans Tray Barcode")
    public void user_scans_Tray_Barcode() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys(AccessionSteps.generatedTray);
    }

    @When("user scans second Tray Barcode")
    public void user_scans_secondTray_Barcode() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys(AccessionSteps.generatedTray2);
    }

    @And("user saves Verification Job number")
    public void user_saves_Verification_job_number() {
        WebElement vJobNumber = driver.findElement(By.cssSelector("[class='text-h4 text-bold']"));
        verifificationJobNumber = vJobNumber.getText().substring(5).trim();
        System.out.println("Verification Job Number: " + verifificationJobNumber);
    }

    @And("user completes a Non-Tray Verification Job")
    public void user_creates_a_NonTray_Verification_Job() throws InterruptedException {
        user_navigates_to_the_Verification_Page();
        user_clicks_most_recent_Verification_Job_for_a_NonTrayed_Item();
        user_saves_Verification_job_number();
        user_verifies_the_barcode();
        user_verifies_the_barcode();
    }

    @And("user completes a Tray Verification Job")
    public void user_creates_a_Tray_Verification_Job() throws InterruptedException {
        user_clicks_most_recent_Verification_Job_for_a_Trayed_Item();
        user_saves_Verification_job_number();
        user_scans_Tray_Barcode();
        user_verifies_the_barcode_by_scanning();
    }

}
