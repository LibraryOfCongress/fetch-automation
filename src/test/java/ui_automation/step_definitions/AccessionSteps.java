package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui_automation.utilities.ConfigurationReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_automation.pages.AccessionPage;
import ui_automation.utilities.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccessionSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AccessionPage accession = new AccessionPage();
    Helper helper = new Helper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    Random random = new Random();
    int random1 = random.nextInt(100000);
    int random2 = random.nextInt(100000);
    static String generatedTray="";

    public static final Logger oLog = LogManager.getLogger(AccessionSteps.class);


    @Given("user navigates to the Accession Page")
    public void user_navigates_to_the_Accession_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "accessionURL"));
    }
    @When("user clicks Start Accession button")
    public void user_clicks_Start_Accession_button() {
        helper.jSClick(accession.startAccessionBtn);
        oLog.info("I started Accession Job");
    }

    @When("user selects Trayed Accession")
    public void user_selects_Trayed_Accession() {
        helper.jSClick(accession.trayedAccession);
        oLog.info("I clicked Trayed Accession Option");
    }

    @And("user selects Non-Tray Accession")
    public void userSelectsNonTrayAccession()  {
        helper.jSClick(accession.nonTrayAccession);
        oLog.info("I clicked Non-Tray Accession Option");
    }

    @Then("user verifies required and optional fields on Start New Accession modal")
    public void user_verifies_required_and_optional_fields_on_Start_New_Accession_modal(io.cucumber.datatable.DataTable dataTable) {
        wait.waitForVisibility(accession.newAccessionFields.get(0), 10);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("fieldname");
            String actualLabel = accession.newAccessionFields.get(i).getText();
            assertEquals("Fieldname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified New Accession Job's fields");
    }

    @When("user selects all required fields")
    public void user_selects_all_required_fields() throws InterruptedException {
        wait.waitForClickability(accession.ownerField, 10);
        accession.ownerField.click();
        wait.hardWait(1000);
        accession.ownerFieldOptions.get(0).click();
        oLog.info("I selected all required fields ( Owner )");
    }

    @When("user selects all fields")
    public void user_selects_all_fields() throws InterruptedException {
        wait.waitForClickability(accession.ownerField, 10);
        accession.ownerField.click();
        wait.hardWait(1000);
        accession.ownerFieldOptions.get(3).click();
        accession.containerSizeField.click();
        helper.jSClick(accession.containerOptions.get(7));
        accession.mediaTypeField.click();
        wait.hardWait(1000);
        wait.waitForClickability(accession.mediaTypeField, 10);
        accession.mediaOptions.get(3).click();
        oLog.info("I selected all fields ");
    }

    @Then("Owner dropdown is clickable")
    public void owner_dropdown_is_clickable() {
        helper.isClickable(accession.ownerField);
    }

    @Then("Container Size dropdown is clickable")
    public void container_Size_dropdown_is_clickable() {
        helper.isClickable(accession.containerSizeField);
    }

    @Then("Media Type field is clickable")
    public void media_Type_field_is_clickable() {
        helper.isClickable(accession.mediaTypeField);
    }


    @Then("submit button is disabled")
    public void submit_button_is_disabled() {
        helper.verifyElementDisabled(accession.submit);
    }

    @Then("submit button is enabled")
    public void submit_button_is_enabled() {
        helper.verifyElementEnabled(accession.submit);
    }

    @When("back button is clickable")
    public void back_button_is_clickable() {
        helper.isClickable(accession.backBtn);
    }

    @When("cancel button is enabled")
    public void cancel_button_is_enabled() {
        helper.verifyElementEnabled(accession.cancelBtn);
    }

    @And("user clicks cancel button")
    public void user_clicks_cancel_button() {
        helper.jSClick(accession.cancelBtn);
    }

    @Then("user is able to return to the Start Accession single action square screen")
    public void user_is_able_to_return_to_the_Start_Accession_single_action_square_screen() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://test.fetch.loctest.gov/accession";
        assertEquals("Returning to Accession Jobs Page failed",
                expectedURL, actualURL);
        oLog.info("I returned to the Start Accession Job page");
    }

    @When("user clicks Select Owner")
    public void userClicksSelectOwner() {
        wait.waitForClickability(accession.ownerField, 10);
        accession.ownerField.click();
    }

    @Then("user is able to choose any option from Owner dropdown field")
    public void userIsAbleToChooseAnyOptionFromOwnerDropdownField() throws InterruptedException {

        //TODO create dynamic method
        select.selectCheckBox(accession.johnDoe, true);
        Thread.sleep(1000);
        accession.ownerField.click();
        select.selectCheckBox(accession.sanders, true);
        Thread.sleep(1000);
    }


    @When("user clicks Select Media Type")
    public void user_clicks_Select_Media_Type() {
        wait.waitForClickability(accession.mediaTypeField, 10);
        accession.mediaTypeField.click();
    }

    @Then("user is able to choose any option from Media Type dropdown field")
    public void user_is_able_to_choose_any_option_from_Media_Type_dropdown_field() throws InterruptedException {
        //TODO create dynamic method
        select.selectCheckBox(accession.mediaOptions.get(1), true);
        Thread.sleep(1000);
        accession.ownerField.click();
        select.selectCheckBox(accession.mediaOptions.get(2), true);
        Thread.sleep(1000);
    }


    @When("user clicks submit button")
    public void user_clicks_submit_button() throws InterruptedException {
        helper.jSClick(accession.submit);
        wait.hardWait(1000);
        oLog.info("I clicked Submit button ");
    }

    @When("user scans Barcode")
    public void user_scans_Barcode() throws InterruptedException {
        wait.hardWait(3000);
        generatedTray = "!AH"+helper.generateBarcodeNumber()+"!";
        driver.findElement(By.tagName("body")).sendKeys(generatedTray);
        oLog.info("I scanned Barcode ");
    }

    @When("user is able to edit Container Size and Media Type fields of the panel")
    public void user_is_able_to_edit_Container_Size_and_Media_Type_fields_of_the_panel() throws InterruptedException {
        helper.jSClick(accession.editContainerSize);
        helper.jSClick(accession.csField);
        accession.containerOptions.get(3).click();
        oLog.info("I edited Container Size field ");
        helper.jSClick(accession.editMediaType);
        accession.mtField.click();
        accession.mediaOptions.get(1).click();
        oLog.info("I edited Media Type field ");
    }

    @When("user is able to cancel edits")
    public void user_is_able_to_cancel_edits() {
        helper.jSClick(accession.cancelEdit);
        oLog.info("I cancelled edits ");
    }

    @When("user is able to save edits")
    public void user_is_able_to_save_edits() {
        helper.jSClick(accession.saveEdits);
        oLog.info("I saved edited fields ");
    }

    @When("Add Item button is enabled and clickable")
    public void add_Item_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accession.addItem);
        helper.isClickable(accession.addItem);
        oLog.info("Add Item button is enabled ");
    }

    @When("Pause Job button is enabled and clickable")
    public void pause_Job_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accession.pauseJob);
        helper.isClickable(accession.pauseJob);
        oLog.info("Pause Job button is enabled ");
    }

    @When("Complete Job button is enabled and clickable")
    public void complete_Job_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accession.completeJob);
        helper.isClickable(accession.completeJob);
        oLog.info("Complete Job button is enabled ");
    }

    @When("user checks an Item")
    public void user_checks_an_Item() {
        accession.scanItemCheckbox.get(1).click();
    }

    @Then("Delete button is enabled")
    public void delete_button_is_enabled() {
        helper.verifyElementEnabled(accession.delete);
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
    public void user_types_in_the_Owner_dropdown_search_field(String string) throws InterruptedException {
        accession.ownerField.click();
        accession.ownerField.sendKeys("coll");
        wait.hardWait(1000);
    }

    @Then("Owner dropdown should display options related to {string}")
    public void Owner_dropdown_should_display_options_related_to(String string) {
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
    public void user_types_in_the_Media_Type_dropdown_search_field(String string) throws InterruptedException {
        accession.mediaTypeField.click();
        accession.mediaTypeField.sendKeys("boo");
        wait.hardWait(1000);

    }

    @Then("Media Type dropdown should display options related to {string}")
    public void media_Type_dropdown_should_display_options_related_to(String string) {
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
        oLog.info("I clicked Delete button ");
    }

    @Then("verify a modal confirming delete action appears")
    public void verify_a_modal_confirming_delete_action_appears() throws InterruptedException {
        wait.waitForVisibility(accession.modal, 1000);
        assertEquals("Are you sure you want to delete selected items?", accession.modal.getText());
        accession.cancelModal.click();
        wait.hardWait(1000);
    }

    @When("user clicks Complete Job button")
    public void user_clicks_Complete_Job_button() {
        helper.jSClick(accession.completeJob);
        oLog.info("I clicked Complete Job button ");
    }

    @Then("verify a modal confirming complete job action appears")
    public void verify_a_modal_confirming_complete_job_action_appears() {
        wait.waitForVisibility(accession.modal, 1000);
        assertEquals("Are you sure you want to complete the job?", accession.modal.getText());
    }

    @Then("verify that Enter Barcode button is enabled")
    public void verify_that_Enter_Barcode_button_is_enabled() {
        helper.verifyElementEnabled(accession.enterBarcodeBtn);
        oLog.info("Enter Barcode button is enabled ");
    }

    @Then("user clicks Enter Barcode button")
    public void user_clicks_Enter_Barcode_button() throws InterruptedException {
        helper.jSClick(accession.enterBarcodeBtn);
        wait.hardWait(1000);
        oLog.info("I clicked Enter Barcode button");
    }

    @Then("verify a modal with manual barcode entry is displayed")
    public void verify_a_modal_with_manual_barcode_entry_is_displayed() {
        wait.waitForVisibility(accession.popupModal, 1000);
        accession.popupModal.isDisplayed();
        oLog.info("Manual barcode entry modal is displayed");
    }

    @Then("user enters barcode and clicks Submit button")
    public void user_enters_barcode_and_clicks_Submit_button() throws InterruptedException {
        accession.enterBarcodeField.click();
        accession.enterBarcodeField.sendKeys(Integer.toString(random1));
        helper.jSClick(accession.submitBtn);
        Thread.sleep(1000);
        oLog.info("I entered barcode and clicked Submit");
    }

    @Then("verify the entered barcode is displayed under Scanned Items")
    public void verify_the_entered_barcode_is_displayed_under_Scanned_Items() {
        assertEquals("Scanned Barcode is not displayed!", accession.scannedItemList.get(accession.scannedItemList.size()-1).getText(), Integer.toString(random1));
        oLog.info("Entered barcode is displayed under Scanned Items");
    }

    @When("user selects one of the barcodes in the table")
    public void user_selects_one_of_the_barcodes_in_the_table() {
        accession.scannedItemCheckbox.click();
    }

    @Then("verify Enter Barcode button is changed to Edit Barcode")
    public void verify_Enter_Barcode_button_is_changed_to_Edit_Barcode() {
        assertEquals(true, accession.enterBarcodeBtn.getText().contains("Edit Barcode"));
        oLog.info("Enter Barcode button is changed to Edit button ");
    }

    @Then("user clicks Edit Barcode button")
    public void user_clicks_Edit_Barcode_button() {
       helper.scrollIntoView(accession.enterBarcodeBtn);
       helper.jSClick(accession.enterBarcodeBtn);
    }

    @Then("verify new modal allowing to edit the barcode is displayed")
    public void verify_new_modal_allowing_to_edit_the_barcode_is_displayed() {
        wait.waitForVisibility(accession.popupModal, 1000);
        accession.popupModal.isDisplayed();
        oLog.info("Edit barcode modal is displayed");
    }

    @Then("user edits the barcode and clicks submit button")
    public void user_edits_the_barcode_and_clicks_submit_button() throws InterruptedException {
        accession.enterBarcodeField.sendKeys(Keys.CONTROL + "a");
        accession.enterBarcodeField.sendKeys(Keys.DELETE);
        accession.enterBarcodeField.sendKeys(Integer.toString(random2));
        accession.submitBtn.click();
        wait.hardWait(2000);
    }


    @Then("verify the edited barcode is displayed under Scanned Items")
    public void verify_the_edited_barcode_is_displayed_under_Scanned_Items() {
        wait.waitForVisibility(accession.scannedItemList.get(accession.scannedItemList.size()-1), 2000);
        assertEquals("Edited Barcode is not displayed!", Integer.toString(random2), accession.scannedItemList.get(accession.scannedItemList.size()-1).getText());
        oLog.info("Edited barcode is displayed under Scanned Items");
    }


    @Then("verify Add Tray button is activated")
    public void verify_Add_Tray_button_is_activated() {
        assertEquals(true, accession.addTrayBtn.isEnabled());
        oLog.info("Add Tray button is activated");
    }

    @Then("user clicks Add Tray button")
    public void user_clicks_Add_Tray_button() throws InterruptedException {
        //TODO find the locator of alert msg so no need to use hardwait
        wait.hardWait(5000);
        wait.waitForVisibility(accession.addTrayBtn, 1000);
        accession.addTrayBtn.click();;
        oLog.info("I clicked Add Tray button");
    }

    @Then("verify new modal Select Tray is displayed")
    public void verify_new_modal_Select_Tray_is_displayed() {
        wait.waitForVisibility(accession.popupModal,100);
        assertEquals(true, accession.popupModal.isDisplayed());
    }

    @Then("user clicks add tray on the modal")
    public void user_clicks_add_tray_on_the_modal() {
        wait.waitForClickability(accession.addTrayModalBtn, 100);
        accession.addTrayModalBtn.click();
    }

    @Then("verify {string} alert message is displayed")
    public void verify_alert_message_is_displayed(String alertMessage) {
        assertEquals(true, accession.alertMsg.getText().contains(alertMessage));
    }

    @Then("the container is cleared out so a new tray can be scanned")
    public void the_container_is_cleared_out_so_a_new_tray_can_be_scanned() {
        assertEquals("Please Scan Tray", accession.scanTrayField.getText());
        oLog.info("New Tray can be scanned");
    }

    @When("user clicks Complete&Print button")
    public void user_clicks_Complete_Print_button() {
        helper.jSClick(accession.completeAndprint);
        oLog.info("I clicked Complete&Print button");
    }

    @Then("user is able to see a print window with a batch report")
    public void user_is_able_to_see_a_print_window_with_a_batch_report() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
        if(driver.getWindowHandles().size() > 1) {
            System.out.println("Print window is displayed");
        } else {
            System.out.println("Print window is not displayed");
        }

}

    @When("user navigates to the accession job link")
    public void userNavigatesToTheAccessionJobLink() {
        driver.get("https://test.fetch.loctest.gov/accession/2/scan-items/NT555923");
        oLog.info("I navigated to the accession job link");
    }

    @And("user selects Media Type")
    public void user_selects_media_type() throws InterruptedException {
        accession.mediaTypeField.click();
        wait.hardWait(1000);
        accession.mediaOptions.get(3).click();
    }

    @And("user enters barcode by scanning")
    public void user_enters_barcode_by_scanning() throws InterruptedException {
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("!"+random1+"!");
        wait.hardWait(1000);
        oLog.info("I entered barcode by scanning it ");
    }

    @And("user selects Container Size")
    public void user_selects_container_size() {
        accession.containerSizeField.click();
        accession.containerOptions.get(2).click();
    }

    @When("user clicks three dot menu next to Accession Job Number")
    public void user_clicks_three_dot_menu_next_to_Accession_Job_Number()  {
        helper.jSClick(accession.threeDot);
    }

    @When("user clicks Edit")
    public void user_clicks_Edit() {
        helper.jSClick(accession.editAccessionJob);
    }

    @When("user edits Container Size")
    public void user_edits_Container_Size() throws InterruptedException {
        helper.scrollIntoViewAndClick(accession.csField);
        accession.editFieldOptions.get(6).click();
        wait.hardWait(1000);
    }

    @When("user edits Media Type")
    public void user_edits_Media_Type() throws InterruptedException {
        wait.waitForClickability(accession.mtField, 1000);
        helper.jSClick(accession.mtField);
        accession.editFieldOptions.get(2).click();
        wait.hardWait(2000);

    }

    @When("user clicks Save Edits")
    public void user_clicks_Save_Edits()  {
        wait.waitForClickability(accession.saveEdits,1000);
        helper.jSClick(accession.saveEdits);
    }

    @When("user clicks Resume Job button")
    public void user_clicks_Resume_Job_button() {
        helper.jSClick(accession.resumeJob);
    }

    @When("user clicks Complete")
    public void user_clicks_Complete() throws InterruptedException {
        wait.hardWait(1000);
        helper.jSClick(accession.complete);
    }


}