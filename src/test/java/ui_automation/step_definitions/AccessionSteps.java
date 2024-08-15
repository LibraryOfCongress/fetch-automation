package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui_automation.pages.AlertPage;
import ui_automation.pages.VerificationPage;
import ui_automation.utilities.ConfigurationReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_automation.pages.AccessionPage;
import ui_automation.utilities.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccessionSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AccessionPage accession = new AccessionPage();
    VerificationPage verification = new VerificationPage();
    AlertPage alert = new AlertPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    static ThreadLocalRandom random = ThreadLocalRandom.current();
    static long scanned1 = random.nextLong(10000000000L, 100000000000L);
    static long scanned2 = random.nextLong(10000000000L, 100000000000L);
    static long entered1 = random.nextLong(10000000000L, 100000000000L);
    static long entered2 = random.nextLong(10000000000L, 100000000000L);
    static long entered3 = random.nextLong(10000000000L, 100000000000L);
    static long edited1 = random.nextLong(10000000000L, 100000000000L);
    static long itemBarcode = random.nextLong(10000000000L, 100000000000L);
    static String editedTrayBarcode = "";
    static String generatedTray = "";
    static String generatedTray2 = "";
    static String accessionJobNumber = "";


    @Given("user navigates to the Accession Page")
    public void user_navigates_to_the_Accession_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "accessionURL"));

    }

    @When("user clicks Start Accession button")
    public void user_clicks_Start_Accession_button() {
        WaitHelper.waitForClickability(accession.startAccessionBtn, 1000);
        accession.startAccessionBtn.click();
    }

    @When("user selects Trayed Accession")
    public void user_selects_Trayed_Accession() {
        helper.jSClick(accession.trayedAccession);
    }

    @And("user selects Non-Tray Accession")
    public void user_selects_NonTray_Accession() {
        helper.jSClick(accession.nonTrayAccession);
    }

    @And("user saves Accession Job number")
    public void user_saves_Accession_job_number() {
        WebElement vJobNumber = driver.findElement(By.cssSelector("[class='text-h4 text-bold']"));
        accessionJobNumber = vJobNumber.getText().substring(5).trim();
        System.out.println("Accession Job Number: " + accessionJobNumber);
    }

    @Then("user verifies required and optional fields on Start New Accession modal")
    public void user_verifies_required_and_optional_fields_on_Start_New_Accession_modal(io.cucumber.datatable.DataTable dataTable) {
        WaitHelper.waitForVisibility(accession.newAccessionFields.get(0), 10);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("fieldname");
            String actualLabel = accession.newAccessionFields.get(i).getText();
            assertEquals("Fieldname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
    }

    @When("user selects all required fields")
    public void user_selects_all_required_fields() throws InterruptedException {
        WaitHelper.waitForClickability(accession.ownerField, 10);
        accession.ownerField.click();
        wait.hardWait(100);
        accession.ownerFieldOptions.get(3).click();
        wait.hardWait(100);
    }

    @When("user selects all fields")
    public void user_selects_all_fields() throws InterruptedException {
        WaitHelper.waitForClickability(accession.ownerField, 10);
        accession.ownerField.click();
        wait.hardWait(1000);
        accession.ownerFieldOptions.get(3).click();
        accession.containerSizeField.click();
        helper.jSClick(accession.containerOptions.get(2));
        accession.mediaTypeField.click();
        wait.hardWait(1000);
        WaitHelper.waitForClickability(accession.mediaTypeField, 10);
        accession.mediaOptions.get(3).click();
    }

    @Then("Owner dropdown is clickable")
    public void owner_dropdown_is_clickable() {
        Helper.isClickable(accession.ownerField);
    }

    @Then("Container Size dropdown is clickable")
    public void container_Size_dropdown_is_clickable() {
        Helper.isClickable(accession.containerSizeField);
    }

    @Then("Media Type field is clickable")
    public void media_Type_field_is_clickable() {
        Helper.isClickable(accession.mediaTypeField);
    }

    @Then("submit button is disabled")
    public void submit_button_is_disabled() {
        Helper.verifyElementDisabled(accession.submit);
    }

    @Then("submit button is enabled")
    public void submit_button_is_enabled() {
        Helper.verifyElementEnabled(accession.submit);
    }

    @When("back button is clickable")
    public void back_button_is_clickable() {
        Helper.isClickable(accession.backBtn);
    }

    @When("cancel button is enabled")
    public void cancel_button_is_enabled() {
        Helper.verifyElementEnabled(accession.cancelBtn);
    }

    @And("user clicks cancel button")
    public void user_clicks_cancel_button() {
        helper.jSClick(accession.cancelBtn);
    }

    @When("user clicks submit button")
    public void user_clicks_submit_button() throws InterruptedException {
        helper.jSClick(accession.submit);
        wait.hardWait(1000);
    }

    @When("user submits the change")
    public void user_submits_the_change() throws InterruptedException {
        helper.jSClick(accession.submitBtn);
        wait.hardWait(2000);
    }

    @When("user scans Barcode")
    public void user_scans_Barcode() throws InterruptedException {
        wait.hardWait(3000);
        generatedTray = "AH" + Helper.generateBarcodeNumber();
        driver.findElement(By.tagName("body")).sendKeys(generatedTray);
    }

    @When("user scans another Barcode")
    public void user_scans_another_Barcode() throws InterruptedException {
        wait.hardWait(3000);
        generatedTray2 = "AL" + Helper.generateBarcodeNumber();
        driver.findElement(By.tagName("body")).sendKeys(generatedTray2);
    }

    @When("user is able to edit Container Size and Media Type fields of the panel")
    public void user_is_able_to_edit_Container_Size_and_Media_Type_fields_of_the_panel() {
        helper.jSClick(accession.editContainerSize);
        helper.jSClick(accession.csField);
        accession.containerOptions.get(3).click();
        helper.jSClick(accession.editMediaType);
        accession.mtField.click();
        accession.mediaOptions.get(1).click();
    }

    @When("user is able to cancel edits")
    public void user_is_able_to_cancel_edits() {
        helper.jSClick(accession.cancelEdit);
    }

    @When("user is able to save edits")
    public void user_is_able_to_save_edits() {
        helper.jSClick(accession.saveEdits);
    }

    @When("Add Item button is enabled and clickable")
    public void add_Item_button_is_enabled_and_clickable() {
        Helper.verifyElementEnabled(accession.addItem);
        Helper.isClickable(accession.addItem);
    }

    @When("Pause Job button is enabled and clickable")
    public void pause_Job_button_is_enabled_and_clickable() {
        Helper.verifyElementEnabled(accession.pauseJob);
        Helper.isClickable(accession.pauseJob);
    }

    @When("Complete Job button is enabled and clickable")
    public void complete_Job_button_is_enabled_and_clickable() {
        Helper.verifyElementEnabled(accession.completeJob);
        Helper.isClickable(accession.completeJob);
    }

    @When("user checks an Item")
    public void user_checks_an_Item() {
        accession.scanItemCheckbox.get(1).click();
    }

    @Then("Delete button is enabled")
    public void delete_button_is_enabled() {
        Helper.verifyElementEnabled(accession.delete);
    }

    @When("user clicks Pause Job button")
    public void user_clicks_Pause_Job_button() {
        helper.jSClick(accession.pauseJob);
    }

    @Then("Add Item, Delete and Complete Job buttons are disabled")
    public void add_Item_Delete_and_Complete_Job_buttons_are_disabled() {
        System.out.println("Add Item button is disabled: " + accession.addItem.getAttribute("disabled"));
        System.out.println("Delete button is disabled: " + accession.delete.getAttribute("disabled"));
        WebElement completeBtn = driver.findElement(By.cssSelector(":nth-child(2) > .text-positive"));
        System.out.println("Complete Job button is disabled: " + completeBtn.getAttribute("disabled"));
    }

    @When("user types {string} in the Owner dropdown search field")
    public void user_types_in_the_Owner_dropdown_search_field(String owner) throws InterruptedException {
        owner = "coll";
        accession.ownerField.click();
        accession.ownerField.sendKeys(owner);
        wait.hardWait(1000);
    }

    @Then("Owner dropdown should display options related to search query")
    public void Owner_dropdown_should_display_options_related_to_search_query() {
        for (WebElement option : accession.ownerFieldOptions) {
            assertTrue(option.getText().toLowerCase().contains("coll"));
        }
    }

    @Then("user selects an option from the Owner dropdown")
    public void user_selects_an_option_from_the_Owner_dropdown() {
        accession.ownerField.sendKeys(Keys.ARROW_DOWN);
        accession.ownerField.sendKeys(Keys.ENTER);
    }

    @Then("user types {string} in the Media Type dropdown search field")
    public void user_types_in_the_Media_Type_dropdown_search_field(String mediaType) throws InterruptedException {
        mediaType = "boo";
        accession.mediaTypeField.click();
        accession.mediaTypeField.sendKeys(mediaType);
        wait.hardWait(2000);
    }

    @Then("Media Type dropdown should display options related to search query")
    public void media_Type_dropdown_should_display_options_related_to_search_query() {
        for (WebElement option : accession.mediaOptions) {
            assertTrue(option.getText().toLowerCase().contains("boo"));
        }
    }

    @Then("user selects an option from the Media Type dropdown")
    public void user_selects_an_option_from_the_Media_Type_dropdown() {
        accession.mediaTypeField.sendKeys(Keys.ARROW_DOWN);
        accession.mediaTypeField.sendKeys(Keys.ENTER);
    }

    @Then("user selects an option from the Container Size dropdown")
    public void user_selects_an_option_from_the_Container_Size_dropdown() throws InterruptedException {
        accession.containerSizeField.sendKeys(Keys.ARROW_DOWN);
        accession.containerSizeField.sendKeys(Keys.ENTER);
        wait.hardWait(2000);
    }

    @Then("when user clicks Delete button")
    public void when_user_clicks_Delete_button() {
        helper.jSClick(accession.delete);
    }

    @Then("verify a modal confirming delete action appears")
    public void verify_a_modal_confirming_delete_action_appears() throws InterruptedException {
        WaitHelper.waitForVisibility(accession.modal, 1000);
        assertEquals("Are you sure you want to delete selected items?", accession.modal.getText());
        accession.cancelModal.click();
        wait.hardWait(1000);
    }

    @When("user clicks Complete Job button")
    public void user_clicks_Complete_Job_button() throws InterruptedException {
        wait.hardWait(1000);
        helper.jSClick(accession.completeJob);
        wait.hardWait(1000);
    }

    @Then("verify a modal confirming complete job action appears")
    public void verify_a_modal_confirming_complete_job_action_appears() {
        WaitHelper.waitForVisibility(accession.modal, 1000);
        assertEquals("Are you sure you want to complete the job?", accession.modal.getText());
    }

    @Then("verify that Enter Barcode button is enabled")
    public void verify_that_Enter_Barcode_button_is_enabled() {
        Helper.verifyElementEnabled(accession.enterBarcodeBtn);
    }

    @Then("user clicks Enter Barcode button")
    public void user_clicks_Enter_Barcode_button() throws InterruptedException {
        WaitHelper.waitForClickability(accession.enterBarcodeBtn, 1000);
        helper.jSClick(accession.enterBarcodeBtn);
        wait.hardWait(2000);
    }

    @Then("verify a modal with manual barcode entry is displayed")
    public void verify_a_modal_with_manual_barcode_entry_is_displayed() {
        WaitHelper.waitForVisibility(accession.popupModal, 1000);
        accession.popupModal.isDisplayed();
    }

    @Then("user enters barcode and clicks Submit button")
    public void user_enters_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Long.toString(entered1));
        helper.jSClick(accession.submitBtn);
        wait.hardWait(1000);
    }

    @Then("user enters second barcode and clicks Submit button")
    public void user_enters_second_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Long.toString(entered2));
        helper.jSClick(accession.submitBtn);
        wait.hardWait(1000);
    }

    @Then("user enters another barcode and clicks Submit button")
    public void user_enters_another_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Long.toString(entered3));
        helper.jSClick(accession.submitBtn);
        wait.hardWait(1000);
    }

    @Then("user enters item barcode")
    public void user_enters_item_barcode() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Long.toString(itemBarcode));
        helper.jSClick(accession.submitBtn);
        wait.hardWait(1000);
    }

    @When("user selects one of the barcodes in the table")
    public void user_selects_one_of_the_barcodes_in_the_table() {
        helper.jSClick(accession.scannedItemCheckbox);
    }

    @When("user selects another barcode in the table")
    public void user_selects_another_barcode_in_the_table() {
        driver.getCurrentUrl();
        driver.navigate().refresh();
        helper.jSClick(accession.scannedItemCheckbox);
    }

    @Then("user verifies Enter Barcode button is changed to Edit Barcode")
    public void user_verifies_Enter_Barcode_button_is_changed_to_Edit_Barcode() {
        assertTrue(accession.enterBarcodeBtn.getText().contains("Edit Barcode"));
    }

    @Then("user clicks Edit Barcode button")
    public void user_clicks_Edit_Barcode_button() {
        helper.scrollIntoView(accession.enterBarcodeBtn);
        helper.jSClick(accession.enterBarcodeBtn);
    }

    @Then("verify new modal allowing to edit the barcode is displayed")
    public void verify_new_modal_allowing_to_edit_the_barcode_is_displayed() {
        WaitHelper.waitForVisibility(accession.popupModal, 1000);
        accession.popupModal.isDisplayed();
    }

    @Then("user edits the barcode and clicks submit button")
    public void user_edits_the_barcode_and_clicks_submit_button() throws InterruptedException {
        accession.enterBarcodeField.sendKeys(Keys.CONTROL + "a");
        accession.enterBarcodeField.sendKeys(Keys.DELETE);
        accession.enterBarcodeField.sendKeys(Long.toString(edited1));
        accession.submitBtn.click();
        wait.hardWait(3000);
    }

    @Then("user verifies that edited barcode is displayed")
    public void user_verifies_that_edited_barcode_is_displayed() throws InterruptedException {
        wait.hardWait(1000);
        WaitHelper.waitForVisibility(accession.scannedItemList.get(0), 5000);
        assertEquals("Edited Barcode is not displayed!", Long.toString(edited1), accession.scannedItemList.get(0).getText());
    }

    @Then("verify Add Tray button is activated")
    public void verify_Add_Tray_button_is_activated() {
        helper.scrollIntoView(accession.addTrayBtn);
        assertTrue(accession.addTrayBtn.isEnabled());
    }

    @Then("user clicks Add Tray button")
    public void user_clicks_Add_Tray_button() {
        driver.manage().window().fullscreen();
        WaitHelper.waitForVisibility(accession.addTrayBtn, 4000);
        helper.jSClick(accession.addTrayBtn);
    }

    @Then("verify new modal Select Tray is displayed")
    public void verify_new_modal_Select_Tray_is_displayed() {
        WaitHelper.waitForVisibility(accession.popupModal, 100);
        assertTrue(accession.popupModal.isDisplayed());
    }

    @Then("user clicks add tray on the modal")
    public void user_clicks_add_tray_on_the_modal() {
        WaitHelper.waitForClickability(accession.addTrayModalBtn, 100);
        accession.addTrayModalBtn.click();
    }

    @Then("verify {string} alert message is displayed")
    public void verify_alert_message_is_displayed(String alertMessage) {
        assertTrue(accession.alertMsg.getText().contains(alertMessage));
    }

    @Then("the container is cleared out so a new tray can be scanned")
    public void the_container_is_cleared_out_so_a_new_tray_can_be_scanned() {
        assertEquals("Please Scan Tray", accession.scanTrayField.getText());
    }

    @When("user clicks Complete&Print button")
    public void user_clicks_Complete_Print_button() {
        helper.jSClick(accession.completeAndprint);
    }

    @Then("user is able to see a print window with a batch report")
    public void user_is_able_to_see_a_print_window_with_a_batch_report() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
        if (driver.getWindowHandles().size() > 1) {
            System.out.println("Print window is displayed");
        } else {
            System.out.println("Print window is not displayed");
        }
    }

    @And("user selects Media Type")
    public void user_selects_Media_Type() throws InterruptedException {
        accession.mediaTypeField.click();
        wait.hardWait(1000);
        accession.mediaOptions.get(3).click();
    }

    @And("user enters barcode by scanning")
    public void user_enters_barcode_by_scanning() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("" + scanned1 + "");
        wait.hardWait(2000);
    }

    @And("user enters a second barcode by scanning")
    public void user_enters_a_second_barcode_by_scanning() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("" + scanned2 + "");
        wait.hardWait(2000);
    }

    @Then("user verifies that new added barcode is displayed first in the table")
    public void user_verifies_that_new_added_barcode_is_displayed_fiest_in_the_table() {
        assertEquals("Barcode is not displayed first in the table!", Long.toString(scanned2), verification.scannedItemList.get(0).getText());
    }

    @Then("user verifies that scanned barcode is displayed")
    public void user_verifies_that_scanned_barcode_is_displayed() {
        assertEquals("Scanned Barcode is not displayed!", Long.toString(scanned1), verification.scannedItemList.get(verification.scannedItemList.size() - 1).getText());
    }

    @And("user selects Container Size")
    public void user_selects_container_size() {
        accession.containerSizeField.click();
        accession.containerOptions.get(2).click();
    }

    @When("user clicks three dot menu next to Accession Job Number")
    public void user_clicks_three_dot_menu_next_to_Accession_Job_Number() throws InterruptedException {
        helper.jSClick(accession.threeDot);
        wait.hardWait(1000);
    }

    @When("user clicks Edit")
    public void user_clicks_Edit() throws InterruptedException {
        wait.hardWait(1000);
        helper.jSClick(accession.editAccessionJob);
    }

    @When("user edits Container Size")
    public void user_edits_Container_Size() throws InterruptedException {
        helper.scrollIntoViewAndClick(accession.csField);
        accession.editFieldOptions.get(2).click();
        wait.hardWait(1000);
    }

    @When("user edits Media Type")
    public void user_edits_Media_Type() throws InterruptedException {
        WaitHelper.waitForClickability(accession.mtField, 1000);
        helper.jSClick(accession.mtField);
        accession.editFieldOptions.get(2).click();
        wait.hardWait(2000);

    }

    @When("user clicks Save Edits")
    public void user_clicks_Save_Edits() {
        WaitHelper.waitForClickability(accession.saveEdits, 1000);
        helper.jSClick(accession.saveEdits);
    }

    @When("user clicks Resume Job button")
    public void user_clicks_Resume_Job_button() {
        helper.jSClick(accession.resumeJob);
    }

    @When("user clicks Complete")
    public void user_clicks_Complete() throws InterruptedException {
        WaitHelper.waitForClickability(accession.complete, 3000);
        helper.jSClick(accession.complete);
        wait.hardWait(2000);
    }

    @Then("Enter Barcode button is enabled")
    public void enter_Barcode_button_is_enabled() {
        Helper.verifyElementEnabled(accession.enterBarcodeBtn);
    }

    @When("user selects an Accession Job")
    public void user_selects_an_Accession_Job() {
        accession.accessionJobsList.get(accession.accessionJobsList.size() - 1).click();
//        for(WebElement job: accession.accessionJobNumbers) {
//            if(job.getText().equals("39")) {
//                job.click();
//            }
//        }
    }

    @And("user clicks Cancel Job")
    public void user_clicks_Cancel_Job() {
        helper.jSClick(accession.cancelAccessionJob);
    }

    @Then("user verifies warning message")
    public void user_verifies_warning_message() {
        WaitHelper.waitForVisibility(accession.cancelModal, 1000);
        assertTrue(accession.warningMsg.getText().contains("Are you sure you want to cancel the accession job? Warning:"));
    }

    @And("user confirms cancellation")
    public void user_confirms_cancellation() throws InterruptedException {
        helper.jSClick(accession.confirmCancellation);
        wait.hardWait(2000);
        alert.closeToastMsg.click();
    }

    @And("user clicks Delete Tray")
    public void user_clicks_Delete_Tray() {
        helper.jSClick(accession.deleteTray);
    }

    @And("user clicks Edit Tray Barcode")
    public void user_clicks_Edit_Tray_Barcode() {
        helper.jSClick(accession.editTrayBarcode);
    }

    @And("user edits Tray Barcode")
    public void user_edits_Tray_Barcode() throws InterruptedException {
        helper.jSClick(accession.editTrayBarcodeField);
        accession.editTrayBarcodeField.sendKeys(Keys.CONTROL + "a");
        accession.editTrayBarcodeField.sendKeys(Keys.DELETE);
        editedTrayBarcode = "BL" + Helper.generateBarcodeNumber();
        accession.editTrayBarcodeField.sendKeys(editedTrayBarcode);
        wait.hardWait(1000);
    }

    @Then("user verifies delete tray warning message")
    public void user_verifies_delete_tray_warning_message() {
        WaitHelper.waitForVisibility(accession.modal, 1000);
        assertTrue(accession.warningMsg.getText().contains("Are you sure you want to delete the tray? Warning: All associated tray items will be deleted."));
    }

    @And("user confirms delete action")
    public void user_confirms_delete_actios() throws InterruptedException {
        helper.jSClick(accession.confirmDeleteTray);
        wait.hardWait(2000);
        alert.closeToastMsg.click();
    }

    @When("user completes a Non-Tray Accession Job")
    public void user_creates_a_NonTray_Accession_Job() throws InterruptedException {
        user_clicks_Start_Accession_button();
        user_selects_NonTray_Accession();
        user_selects_all_fields();
        user_clicks_submit_button();
        user_clicks_Enter_Barcode_button();
        user_enters_barcode_and_clicks_Submit_button();
        user_clicks_Complete_Job_button();
        user_clicks_Complete();
    }

    @When("user completes a Tray Accession Job")
    public void user_creates_a_Tray_Accession_Job() throws InterruptedException {
        user_clicks_Start_Accession_button();
        user_selects_Trayed_Accession();
        user_selects_all_required_fields();
        user_selects_Media_Type();
        user_clicks_submit_button();
        user_scans_Barcode();
        user_enters_barcode_by_scanning();
        user_clicks_Complete_Job_button();
        user_clicks_Complete();
    }


}