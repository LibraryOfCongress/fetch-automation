package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui_automation.pages.AdminPage;
import ui_automation.pages.ShelvingPage;
import ui_automation.utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShelvingSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    ShelvingPage shelving = new ShelvingPage();
    AdminPage admin = new AdminPage();
    Helper helper = new Helper();
    GenericHelper genHelp = new GenericHelper();
    WaitHelper wait = new WaitHelper();
    SelectHelper select = new SelectHelper();
    Actions actions = new Actions(driver);


    public static final Logger oLog = LogManager.getLogger(ShelvingSteps.class);


    @Given("user navigates to Shelving Page")
    public void user_navigates_to_Shelving_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "shelvingURL"));
    }

    @When("user is on Shelving page")
    public void user_is_on_Shelving_Page() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://test.fetch.loctest.gov/shelving";
        Assert.assertEquals("Shelving page URL failed",
                expectedURL, actualURL);
        oLog.info("I am on Shelving page");
    }

    @Then("Filter icon is clickable")
    public void filter_icon_is_clickable() {
        helper.isClickable(shelving.filter);
    }
    @Then("Rearrange dropdown is clickable")
    public void rearrange_dropdown_is_clickable() {
        helper.isClickable(shelving.rearrangeDropdown);
    }

    @Then("Create Shelving Job button is clickable")
    public void create_shelving_job_button_is_clickable() {
        helper.isClickable(shelving.createShelvingJob);
    }

    @When("user clicks on Rearrange dropdown")
    public void user_clicks_on_rearrange_dropdown() {
        helper.jSClick(shelving.rearrangeDropdown);
    }

    @Then("user verifies dropdown options")
    public void user_verifies_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("optionname");
            wait.hardWait(1000);
            String actualOption = shelving.allFilterDropdownOptions.get(i).getText();
            Assert.assertEquals("Options names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Rearrange Dropdown Options");
    }

    @Then("user verifies dropdown checkboxes are clickable")
    public void user_verifies_dropdown_checkboxes_are_clickable() {
        for (WebElement option : shelving.allFilterDropdownOptions) {
            assertTrue("Checkbox is not clickable: " + option.isEnabled(), option.isDisplayed());
        }
    }

    @Then("user verifies dropdown options match shelf table column options")
    public void user_verifies_dropdown_options_match_shelf_table_column_options() {
        String shelfTableValue = "";
        String filterDropdownValue = "";
        for (WebElement option : shelving.shelfTableColumns) {
            wait.waitForVisibility(option,2000);
            shelfTableValue = option.getText();
        }
        for (WebElement value : shelving.allFilterDropdownOptions) {
            wait.waitForVisibility(value, 2000);
            filterDropdownValue = value.getText();
        }
        Assert.assertEquals("Options did not match!", filterDropdownValue, shelfTableValue);
    }

    @Then("user verifies all options are selected")
    public void user_verifies_all_options_are_selected() {
        for (WebElement checkbox : shelving.allFilterDropdownOptions) {
            if(checkbox.getAttribute("aria-selected").equals("false")){
                System.out.println("Not all the checkboxes are selected");
            }
        }
    }

    @Then("user is able to deselect all the options")
    public void user_is_able_to_deselect_all_the_options() {
        for(WebElement checkbox : shelving.allFilterDropdownOptions){
            select.selectCheckBox(checkbox, true);
        }
    }

    @Then("user selects options A, B and C from the dropdown")
    public void user_selects_options_A_B_and_C_from_the_dropdown() {
        for(WebElement checkbox : shelving.allFilterDropdownOptions) {
        String checkboxValue = checkbox.getText();
        if(checkboxValue.equals("Job Number") || checkboxValue.equals("Status") ||
                checkboxValue.equals("Date Added") ){
            checkbox.click();
        }
        }
    }

    @Then("selected options are displayed on the page")
    public void selected_options_are_displayed_on_the_page() {
        String checkboxValue = "";
        String shelfTableValue = "";
        for(WebElement checkbox : shelving.allFilterDropdownOptions){
            if(checkbox.getAttribute("aria-selected").equals("true")) {
                wait.waitForVisibility(checkbox, 2000);
                checkboxValue = checkbox.getText();
            }
        }
        for(int i = 0; i < shelving.shelfTableColumns.size(); i++) {
           shelfTableValue = shelving.shelfTableColumns.get(i).getText();
        }
        Assert.assertEquals("Selected options are not displayed!", checkboxValue, shelfTableValue);
    }

    @When("user clicks on Create Shelving Job button")
    public void user_clicks_on_create_shelving_job_button() {
     helper.jSClick(shelving.createShelvingJob);
    }

    @Then("user verifies Create Shelving Job modal sections")
    public void user_verifies_Create_Shelving_Job_modal_sections(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 1;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("section");
            String actualOption = shelving.modalSections.get(i).getText();
            Assert.assertEquals("Section names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Create Shelving Job sections");
    }


    @Then("user verifies Create Shelving Job modal dropdown fields")
    public void user_verifies_Create_Shelving_Job_modal_dropdown_fields(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("field");
            String actualOption = shelving.modalFields.get(i).getText();
            Assert.assertEquals("Dropdown field verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Create Shelving Job dropdown fields");
    }

    @Then("cancel button is clickable")
    public void cancel_button_is_clickable() {
        helper.isClickable(shelving.cancelBtn);
    }

    @Then("submit button is enabled and clickable")
    public void submit_button_is_enabled_and_clickable() {
      helper.verifyElementEnabled(shelving.submit);
      helper.isClickable(shelving.submit);
    }


    @When("user unchecks menu items to their preferred order")
    public void user_unchecks_menu_items_to_their_preferred_order() throws InterruptedException {
        shelving.rearrangeDropdownOptions.get(1).click();
        wait.hardWait(100);
        shelving.rearrangeDropdownOptions.get(4).click();
        shelving.rearrangeDropdownOptions.get(5).click();
        wait.hardWait(100);
        shelving.rearrangeDropdown.click();
        oLog.info("I unchecked some menu items");
    }

    @Then("user verifies the Shelf Table column names")
    public void user_verifies_the_Shelf_Table_column_names() {
        String column = "";
        for(WebElement element: shelving.shelfTableColumns) {
             column = element.getText();
        }
        List<String> shelfColumns =new ArrayList<>(Arrays.asList("Job Number", "Status",
                "Assigned User"));
               if(shelfColumns.contains(column)) {
              System.out.println("Table has correct column names");
               } else{
              System.out.println("Table has wrong column names");
     }
        oLog.info("I verified column names");
    }

    @When("user selects an Owner")
    public void user_selects_an_Owner() throws InterruptedException {
        //TODO choose an Owner
//    shelving.selectOwner.click();
//    wait.hardWait(1000);
//    shelving.sanders.click();
//    actions.sendKeys(Keys.TAB).build().perform();
//        oLog.info("I selected an Owner");
    }

    @When("user enters a Shelf Number")
    public void user_enters_a_Shelf_Number() {
    shelving.enterShelfNumber.sendKeys("2");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Number");
    }

    @When("user enters a Shelf Width")
    public void user_enters_a_Shelf_Width() {
    shelving.enterShelfWidth.sendKeys("5");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Width");
    }

    @When("user enters a Shelf Height")
    public void user_enters_a_Shelf_Height() {
    shelving.enterShelfHeight.sendKeys("3");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Height");
    }


    @When("user enters a Shelf Depth")
    public void user_enters_a_Shelf_Depth() {
    shelving.enterShelfDepth.sendKeys("1");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Depth");
    }

    @When("user selects a Container Type")
    public void user_selects_a_Container_Type() throws InterruptedException {
    shelving.selectType.click();
    genHelp.getElement(By.cssSelector("div [role='option']:nth-child(1)")).click();
        oLog.info("I selected a Container Type");
    }

    @When("user clicks Create Shelf button")
    public void user_clicks_Create_Shelf_button() {
    shelving.submit.click();
        oLog.info("I clicked submit button");
    }


    @When("user selects From Verification Job option")
    public void user_selects_From_Verification_Job_option()  {
       helper.jSClick(shelving.fromVerificationJob);
        oLog.info("I selected option - From Verification Job");
    }

    @When("user selects No")
    public void user_selects_No() {
        helper.jSClick(shelving.no);
        oLog.info("I selected option - No");
    }

    @Then("user selects a Verification Job from the Verification Job\\(s) List")
    public void user_selects_a_Verification_Job_from_the_Verification_Job_s_List() throws InterruptedException {
        shelving.selectByNumber.click();
        helper.jSClick(shelving.verificationJobsList.get(0));
        shelving.selectByNumber.click();
        wait.hardWait(1000);
        oLog.info("I selected a Verification Job from the list");
    }


    @Then("user selects a Building from Shelving Locations")
    public void user_selects_a_Building_from_Shelving_Locations() throws InterruptedException {
        wait.waitForClickability(shelving.building, 1000);
        helper.jSClick(shelving.building);
        helper.jSClick(shelving.buildings.get(0));
        oLog.info("I selected a Building from Shelving Locations");
    }

    @Then("user clicks Submit")
    public void user_clicks_Submit() throws InterruptedException {
        wait.hardWait(100);
        helper.jSClick(shelving.submit);
        oLog.info("I clicked Submit");
    }

    @Then("user verifies the Shelving Job is created")
    public void user_verifies_the_Shelving_Job_is_created() {
        Assert.assertEquals("Shelving Job is not created! ", "Created", shelving.shelvingJobStatus.getText());
        oLog.info("Shelving Job is created");
    }


    @Then("user is navigated to the shelving detail page")
    public void user_is_navigated_to_the_shelving_detail_page() {
        assertEquals("Job Number:", shelving.jobNumber.getText() );
        oLog.info("I navigated to Shelving Job detail page");
    }

    @When("user selects Yes")
    public void user_selects_Yes() throws InterruptedException {
        wait.waitForClickability(shelving.yes,3000);
        shelving.yes.click();
        oLog.info("I selected option - Yes");
    }

    @Then("a new modal with shelving location options along with the verification job selection is displayed")
    public void a_new_modal_with_shelving_location_options_along_with_the_verification_job_selection_is_displayed() {
       helper.verifyElementDisplayed(shelving.createShelvingJobModal);
        oLog.info("Modal with Shelving Location options and Verification Job selection is displayed");
    }


    @When("user navigates to Shelving Job")
    public void user_navigates_to_Shelving_Job() {
        driver.get("https://test.fetch.loctest.gov/shelving/4");
        oLog.info("I navigated to the Shelving Job");
    }

    @When("user clicks three dot menu next to a container")
    public void user_clicks_three_dot_menu_next_to_a_container() {
        helper.jSClick(shelving.threeDotNextToContainer);
        oLog.info("I clicked three dot menu next to a container");
    }

    @Then("user should see Edit Location option")
    public void user_should_see_Edit_Location_option() {
        wait.waitForVisibility(shelving.editOrAssign,10);
        assertEquals("Edit Location", shelving.editOrAssign.getText());
        oLog.info("I see Edit Location option");
    }

    @Then("user clicks Edit Location button")
    public void user_clicks_Edit_Location_button() {
        helper.jSClick(shelving.editOrAssign);
        oLog.info("I clicked Edit Location button");
    }

    @Then("Edit Shelving Location modal is displayed")
    public void edit_Shelving_Location_modal_is_displayed() {
        oLog.info("Edit Shelving Location modal is displayed");
    }

    @When("user clicks three dot menu next to a container that does not have a shelving location information")
    public void user_clicks_three_dot_menu_next_to_a_container_that_does_not_have_a_shelving_location_information() {
        oLog.info("I clicked three dot menu next to a container that does not have a shelving location information");
    }

    @Then("user should see Assign Location option")
    public void user_should_see_Assign_Location_option() {
        oLog.info("I see Assign Location option");
    }

    @Then("user clicks Assign Location button")
    public void user_clicks_Assign_Location_button() {
        oLog.info("I clicked Assign Location button");
    }

    @Then("Assign Shelving Location modal is displayed")
    public void assign_Shelving_Location_modal_is_displayed() {
        oLog.info("Assign Shelving Location modal is displayed");
    }

    @When("user navigates to Shelving Job with Running Status")
    public void user_navigates_to_Shelving_Job_with_Running_Status() throws InterruptedException {
        helper.jSClick(shelving.runningJob);
        wait.hardWait(1000);
        oLog.info("I navigated to Shelving Job with Running Status");
    }

    @Then("Assign User dropdown is clickable")
    public void assign_user_dropdown_is_clickable() {
        helper.isClickable(shelving.assignedUserField);
        oLog.info("Assign User dropdown is clickable");
    }

    @Then("Save Edits button is clickable")
    public void save_edits_button_is_clickable() {
       helper.isClickable(shelving.saveEdits);
        oLog.info("Save Edits button is clickable");
    }

    @Then("Cancel edits button is clickable")
    public void cancel_edits_button_is_clickable() {
        helper.isClickable(shelving.cancelEdits);
        oLog.info("Cancel button is clickable");
    }


    @When("user selects Direct To Shelve option")
    public void user_selects_direct_to_shelve_option() {
       helper.jSClick(shelving.directToShelve);
    }

    @Then("user selects Right side")
    public void user_selects_Right_side() {
        wait.waitForClickability(shelving.rightSide, 1000);
        helper.jSClick(shelving.rightSide);
    }

    @Then("user verifies the Status is {string}")
    public void user_verifies_the_status_is(String string) {
        Assert.assertEquals("Shelving Job status does not match! ", string, shelving.shelvingJobStatus.getText());
        oLog.info("Shelving Job status is correct");
    }

    @When("user clicks Execute Job")
    public void user_clicks_execute_job() throws InterruptedException {
       helper.jSClick(shelving.executeJob);
       wait.hardWait(2000);
       helper.jSClick(shelving.beAwareMsg);
       wait.hardWait(100);
        oLog.info("I clicked Execute Job button");
    }


    @Then("user selects a created Verification Job")
    public void user_selects_a_created_Verification_Job() throws InterruptedException {
        shelving.selectByNumber.click();

        for(WebElement dropdownValue: shelving.verificationJobsList) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",dropdownValue);
            System.out.println(dropdownValue.getText());
            if(dropdownValue.getText().contains(VerificationSteps.verifificationJobNumber)) {
                dropdownValue.click();
            }
        }
        shelving.selectByNumber.click();
        wait.hardWait(1000);
        oLog.info("I selected a Verification Job from the list");
    }


    @And("user scans Container")
    public void user_scans_Container() {
        System.out.println("Generated Tray: " + AccessionSteps.generatedTray);
        driver.findElement(By.tagName("body")).sendKeys(AccessionSteps.generatedTray);
        oLog.info("I scanned Container ");
    }

    @And("user scans shelf to verify Container")
    public void user_scans_shelf_to_verify_Container() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys(AccessionSteps.generatedTray);
        wait.waitForVisibility(shelving.assignedShelf, 1000);
        String shelf = shelving.assignedShelf.getText().substring(79,84);
        shelving.closeMsg.click();
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys(""+ shelf + "");
        Assert.assertEquals("Shelved", shelving.shelvedCheckMark.getText());
        oLog.info("I scanned Shelf ");
    }

    @And("user selects Shelf")
    public void user_selects_Shelf() throws InterruptedException {
        helper.scrollToElement(shelving.selectShelf);
        wait.waitForClickability(shelving.selectShelf, 1000);
        shelving.selectShelf.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(0).click();
    }

    @And("user selects Shelf Position")
    public void userSelectsShelfPosition() throws InterruptedException {
        helper.scrollToElement(shelving.selectShelfPosition);
        wait.waitForClickability(shelving.selectShelfPosition, 1000);
        shelving.selectShelfPosition.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(0).click();
        wait.hardWait(1000);
    }

    @When("user clicks Complete Job")
    public void user_clicks_Complete_Job() throws InterruptedException {
        wait.hardWait(1000);
        helper.jSClick(shelving.completeJob);
        wait.hardWait(1000);
        oLog.info("I clicked Complete Job ");
    }

    @When("user clicks three dot menu next to Container")
    public void user_clicks_three_dot_menu_next_to_Container() {
        helper.jSClick(shelving.threeDotNextToContainer);
    }


    @Then("user clicks Edit Location")
    public void user_clicks_Edit_Location() {
        helper.jSClick(shelving.editLocation);
        oLog.info("I clicked Edit Location");
    }

    @When("user switches on Toggle Barcode Scan")
    public void user_switches_on_Toggle_Barcode_Scan() {
        helper.jSClick(shelving.toggleScan);
    }

    @Then("verify barcode scanning is enabled")
    public void verify_barcode_scanning_is_Enabled() {
        shelving.scanningEnabledAlert.click();
        Assert.assertEquals("Barcode scanning is enabled.", shelving.scanningEnabledAlert.getText());
    }

    @When("user disables Toggle Barcode Scan")
    public void user_disables_Toggle_Barcode_Scan() {
        helper.jSClick(shelving.disableScan);
    }

    @When("user sets input delay")
    public void user_sets_input_delay() {
        shelving.inputDelay.click();
        shelving.inputDelay.sendKeys(Keys.CONTROL + "a");
        shelving.inputDelay.sendKeys(Keys.DELETE);
        shelving.inputDelay.sendKeys("0.35");
    }
}




