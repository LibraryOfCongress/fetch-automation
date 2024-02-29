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
import ui_automation.utilities.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccessionSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AccessionPage accessionJob = new AccessionPage();
    Helper helper = new Helper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    GenericHelper genhelp = new GenericHelper();
    public static final Logger oLog = LogManager.getLogger(AccessionSteps.class);


    @When("user clicks Start Accession button")
    public void user_clicks_Start_Accession_button() {
        helper.jSClick(accessionJob.startAccessionBtn);
        oLog.info("I started Accession Job");
    }

    @When("user selects Trayed Accession")
    public void user_selects_Trayed_Accession() {
        helper.jSClick(accessionJob.trayedAccession);
        oLog.info("I clicked Trayed Accession Option");
    }

    @And("user selects Non-Tray Accession")
    public void userSelectsNonTrayAccession() {
        helper.jSClick(accessionJob.nonTrayAccession);
        oLog.info("I clicked Non-Tray Accession Option");
    }

    @Then("user verifies required and optional fields on Start New Accession modal")
    public void user_verifies_required_and_optional_fields_on_Start_New_Accession_modal(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("fieldname");
            String actualLabel = accessionJob.newAccessionFields.get(i).getText();
            assertEquals("Fieldname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified New Accession Job's fields");
    }

    @When("user selects all required fields")
    public void user_selects_all_required_fields() {
        helper.jSClick(accessionJob.ownerField);
        accessionJob.johnDoe.click();
        oLog.info("I clicked John Doe in Owner field");
        accessionJob.containerSizeField.click();
        accessionJob.aHigh.click();
        oLog.info("I clicked A High option in Container Size field");
    }

    @When("user is able to click Back button")
    public void user_is_able_to_click_Back_button() {
        helper.isClickable(accessionJob.backBtn);
    }

    @When("user is able to click Cancel button")
    public void user_is_able_to_click_Cancel_button() {
        helper.isClickable(accessionJob.cancelBtn);
    }

    @Then("user is able to return to the Start Accession single action square screen")
    public void user_is_able_to_return_to_the_Start_Accession_single_action_square_screen() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://c2vldimsweb01.loctest.gov/accession";
        assertEquals("Returning to Accession Jobs Page failed",
                expectedURL, actualURL);
        oLog.info("I returned to the Start Accession Job page");
    }

    //TODO find better locator
    @And("user verifies Owner field options")
    public void userVerifiesOwnerFieldOptions() {
        helper.jSClick(accessionJob.ownerField);
        List<String> elemTexts = helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for (String elemText : elemTexts) {
            System.out.println(elemText);
        }
        oLog.info("I printed Owner field options");

    }

    @And("user verifies Container Size field options")
    public void userVerifiesContainerSizeFieldOptions() {
        helper.jSClick(accessionJob.containerSizeField);

        //TODO find better locator
        List<String> elemTexts = helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for (String elemText : elemTexts) {
            System.out.println(elemText);
        }
        oLog.info("I printed Container Size field options ");
    }

    @And("user verifies Media Type field options")
    public void userVerifiesMediaTypeFieldOptions() {
        helper.jSClick(accessionJob.mediaTypeField);

        //TODO find better locator
        List<String> elemTexts = helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for (String elemText : elemTexts) {
            System.out.println(elemText);
        }
        oLog.info("I printed Media Type field options ");
    }

    @When("user clicks on select Owner button")
    public void userClicksOnSelectOwnerButton() throws InterruptedException {
        helper.jSClick(accessionJob.ownerField);
    }

    @Then("user is able to choose any option from dropdown field")
    public void userIsAbleToChooseAnyOptionFromDropdownField() throws InterruptedException {

        //TODO create dynamic method
        select.selectCheckBox(accessionJob.johnDoe, true);
        Thread.sleep(3000);
        accessionJob.ownerField.click();
        select.selectCheckBox(accessionJob.georgeW, true);
        Thread.sleep(3000);
    }

    @When("user clicks Submit button")
    public void user_clicks_Submit_button() {
        helper.jSClick(accessionJob.submit);
        oLog.info("I clicked Submit button ");
    }

    @When("user clicks Scan Barcode")
    public void user_clicks_Scan_Barcode() {
        helper.jSClick(accessionJob.scanBarcode);
        oLog.info("I clicked(scanned) Barcode ");
    }

    @When("user is able to edit Container Size and Media Type fields of the panel")
    public void user_is_able_to_edit_Container_Size_and_Media_Type_fields_of_the_panel() throws InterruptedException {
        helper.jSClick(accessionJob.editContainerSize);
        helper.jSClick(accessionJob.csField);
        accessionJob.containerOptions.get(3).click();
        oLog.info("I edited Container Size field ");
        helper.jSClick(accessionJob.editMediaType);
        accessionJob.mtField.click();
        accessionJob.mediaOptions.get(1).click();
        oLog.info("I edited Media Type field ");
    }

    @When("user is able to cancel edits")
    public void user_is_able_to_cancel_edits() {
        helper.jSClick(accessionJob.cancelEdit);
        oLog.info("I cancelled edits ");
    }

    @When("user is able to save edits")
    public void user_is_able_to_save_edits() {
        helper.jSClick(accessionJob.saveEdits);
        oLog.info("I saved edited fields ");
    }

    @When("Add Item button is enabled and clickable")
    public void add_Item_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accessionJob.addItem);
        helper.isClickable(accessionJob.addItem);
        oLog.info("Add Item button is enabled ");
    }

    @When("Pause Job button is enabled and clickable")
    public void pause_Job_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accessionJob.pauseJob);
        helper.isClickable(accessionJob.pauseJob);
        oLog.info("Pause Job button is enabled ");
    }

    @When("Complete Job button is enabled and clickable")
    public void complete_Job_button_is_enabled_and_clickable() {
        helper.verifyElementEnabled(accessionJob.completeJob);
        helper.isClickable(accessionJob.completeJob);
        oLog.info("Complete Job button is enabled ");
    }

    @When("user checks an Item")
    public void user_checks_an_Item() {
        accessionJob.scanItemList.get(1).click();
    }

    @Then("Delete button is enabled")
    public void delete_button_is_enabled() {
        helper.verifyElementEnabled(accessionJob.delete);
    }

    @When("user clicks Pause Job button")
    public void user_clicks_Pause_Job_button() {
        helper.jSClick(accessionJob.pauseJob);
    }

    @Then("Add Item, Delete and Complete Job buttons are disabled")
    public void add_Item_Delete_and_Complete_Job_buttons_are_disabled() {
        System.out.println("Add Item button is disabled: " + accessionJob.addItem.getAttribute("disabled"));
        System.out.println("Delete button is disabled: " + accessionJob.delete.getAttribute("disabled"));
        WebElement completeBtn = driver.findElement(By.cssSelector(":nth-child(2) > .text-positive"));
        System.out.println("Complete Job button is disabled: " + completeBtn.getAttribute("disabled"));
    }


    @When("user types {string} in the Owner dropdown search field")
    public void user_types_in_the_Owner_dropdown_search_field(String string) {
        accessionJob.ownerField.click();
        accessionJob.ownerField.sendKeys("wash");
    }

    @Then("Owner dropdown should display options related to {string}")
    public void Owner_dropdown_should_display_options_related_to(String string) {
        for (WebElement option : accessionJob.ownerFieldOptions) {
            assertTrue(option.getText().toLowerCase().contains("wash"));
        }
    }

    @Then("user selects an option from the Owner dropdown")
    public void user_selects_an_option_from_the_Owner_dropdown() {
        accessionJob.ownerField.sendKeys(Keys.ARROW_DOWN);
        accessionJob.ownerField.sendKeys(Keys.ENTER);
    }

    @Then("the selected Owner option should be displayed on the page")
    public void the_selected_Owner_option_should_be_displayed_on_the_page() {

        //TODO actual value should be displayed
        String actual = accessionJob.ownerField.getAttribute("placeholder");
        System.out.println(actual);
        // Assert.assertEquals("Selected option is not displayed ", "George Washington", actual);
    }


    @Then("user types {string} in the Container Size dropdown search field")
    public void user_types_in_the_Container_Size_dropdown_search_field(String string) throws InterruptedException {
        accessionJob.containerSizeField.click();
        accessionJob.containerSizeField.sendKeys("b l");
        wait.hardWait(1000);
    }

    @Then("Container Size dropdown should display options related to {string}")
    public void container_Size_dropdown_should_display_options_related_to(String string) {
        for (WebElement option : accessionJob.containerOptions) {
            assertTrue(option.getText().toLowerCase().contains("b l"));
        }
    }

    @Then("user selects an option from the Container Size dropdown")
    public void user_selects_an_option_from_the_Container_Size_dropdown() throws InterruptedException {
        accessionJob.containerSizeField.sendKeys(Keys.ARROW_DOWN);
        accessionJob.containerSizeField.sendKeys(Keys.ENTER);
        wait.hardWait(1000);
    }

    //TODO actual value should be displayed
    @Then("the selected Container Size option should be displayed on the page")
    public void the_selected_Container_Size_option_should_be_displayed_on_the_page() {
        String actual = accessionJob.containerSizeField.getText();
        System.out.println(actual);
        // Assert.assertEquals("Selected option is not displayed ", "B Low", actual);
    }


    @Then("user types {string} in the Media Type dropdown search field")
    public void user_types_in_the_Media_Type_dropdown_search_field(String string) throws InterruptedException {
        accessionJob.mediaTypeField.click();
        accessionJob.mediaTypeField.sendKeys("mu");
        wait.hardWait(1000);

    }

    @Then("Media Type dropdown should display options related to {string}")
    public void media_Type_dropdown_should_display_options_related_to(String string) {
        for (WebElement option : accessionJob.mediaOptions) {
            assertTrue(option.getText().toLowerCase().contains("mu"));
        }
    }

    @Then("user selects an option from the Media Type dropdown")
    public void user_selects_an_option_from_the_Media_Type_dropdown() {
        accessionJob.mediaTypeField.sendKeys(Keys.ARROW_DOWN);
        accessionJob.mediaTypeField.sendKeys(Keys.ENTER);
    }

    //TODO actual value should be displayed
    @Then("the selected Media Type option should be displayed on the page")
    public void the_selected_Media_Type_option_should_be_displayed_on_the_page() {
        String actual = accessionJob.mediaTypeField.getText();
        System.out.println(actual);
        // Assert.assertEquals("Selected option is not displayed ", "Music", actual);
    }


    @Then("when user clicks Delete button")
    public void when_user_clicks_Delete_button() {
        helper.jSClick(accessionJob.delete);
        oLog.info("I clicked Delete button ");
    }

    @Then("verify a modal confirming delete action appears")
    public void verify_a_modal_confirming_delete_action_appears() throws InterruptedException {
        wait.waitForVisibility(accessionJob.modal, 1000);
        assertEquals("Are you sure you want to delete selected items?", accessionJob.modal.getText());
        accessionJob.cancelModal.click();
        wait.hardWait(1000);
    }

    @When("user clicks Complete Job button")
    public void user_clicks_Complete_Job_button() {
        helper.jSClick(accessionJob.completeJob);
        oLog.info("I clicked Complete Job button ");
    }


    @Then("verify a modal confirming complete job action appears")
    public void verify_a_modal_confirming_complete_job_action_appears() {
        wait.waitForVisibility(accessionJob.modal, 1000);
        assertEquals("Are you sure you want to complete the job?", accessionJob.modal.getText());
    }


}


