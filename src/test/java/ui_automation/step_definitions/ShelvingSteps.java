package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    static String itemBarcode = "";


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
    }

    @Then("Filter dropdown is clickable")
    public void filter_dropdown_is_clickable() {
        Helper.isClickable(shelving.filter);
        shelving.filter.click();
        shelving.filter.click();
    }

    @Then("Rearrange dropdown is clickable")
    public void rearrange_dropdown_is_clickable() {
        Helper.isClickable(shelving.rearrangeDropdown);
        shelving.rearrangeDropdown.click();
        shelving.rearrangeDropdown.click();
    }

    @Then("Create Shelving Job button is clickable")
    public void create_shelving_job_button_is_clickable() {
        Helper.isClickable(shelving.createShelvingJob);
        shelving.createShelvingJob.click();
        shelving.closeMsg.click();
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
            String actualOption = shelving.allDropdownOptions.get(i).getText();
            Assert.assertEquals("Options names verification failed",
                    expectedOption, actualOption);
            i++;
        }
    }

    @Then("user verifies dropdown checkboxes are clickable")
    public void user_verifies_dropdown_checkboxes_are_clickable() {
        for (WebElement option : shelving.allDropdownOptions) {
            assertTrue("Checkbox is not clickable: " + option.isEnabled(), option.isDisplayed());
        }
    }

    @Then("user verifies dropdown options match shelf table column options")
    public void user_verifies_dropdown_options_match_shelf_table_column_options() {
        String shelfTableValue = "";
        String filterDropdownValue = "";
        for (WebElement option : shelving.shelfTableColumns) {
            WaitHelper.waitForVisibility(option, 2000);
            shelfTableValue = option.getText();
        }
        for (WebElement value : shelving.allDropdownOptions) {
            WaitHelper.waitForVisibility(value, 2000);
            filterDropdownValue = value.getText();
        }
        Assert.assertEquals("Options did not match!", filterDropdownValue, shelfTableValue);
    }

    @Then("user verifies all options are selected")
    public void user_verifies_all_options_are_selected() {
        for (WebElement checkbox : shelving.allDropdownOptions) {
            if (checkbox.getAttribute("aria-selected").equals("false")) {
                System.out.println("Not all the checkboxes are selected");
            }
        }
    }

    @Then("user is able to deselect all the options")
    public void user_is_able_to_deselect_all_the_options() {
        for (WebElement checkbox : shelving.allDropdownOptions) {
            select.selectCheckBox(checkbox, true);
        }
    }

    @Then("user selects options A, B and C from the dropdown")
    public void user_selects_options_A_B_and_C_from_the_dropdown() {
        for (WebElement checkbox : shelving.allDropdownOptions) {
            String checkboxValue = checkbox.getText();
            if (checkboxValue.equals("Job Number") || checkboxValue.equals("Status") ||
                    checkboxValue.equals("Date Added")) {
                checkbox.click();
            }
        }
    }

    @Then("selected options are displayed on the page")
    public void selected_options_are_displayed_on_the_page() {
        String checkboxValue = "";
        String shelfTableValue = "";
        for (WebElement checkbox : shelving.allDropdownOptions) {
            if (checkbox.getAttribute("aria-selected").equals("true")) {
                WaitHelper.waitForVisibility(checkbox, 2000);
                checkboxValue = checkbox.getText();
            }
        }
        for (int i = 0; i < shelving.shelfTableColumns.size(); i++) {
            shelfTableValue = shelving.shelfTableColumns.get(i).getText();
        }
        Assert.assertEquals("Selected options are not displayed!", checkboxValue, shelfTableValue);
    }

    @When("user clicks on Create Shelving Job button")
    public void user_clicks_on_create_shelving_job_button() {
        WaitHelper.waitForClickability(shelving.createShelvingJob, 1000);
        shelving.createShelvingJob.click();
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
    }

    @Then("cancel button is clickable")
    public void cancel_button_is_clickable() {
        Helper.isClickable(shelving.cancelBtn);
    }

    @Then("submit button is enabled and clickable")
    public void submit_button_is_enabled_and_clickable() {
        Helper.verifyElementEnabled(shelving.submit);
        Helper.isClickable(shelving.submit);
    }

    @When("user unchecks menu items to their preferred order")
    public void user_unchecks_menu_items_to_their_preferred_order() throws InterruptedException {
        shelving.rearrangeDropdownOptions.get(1).click();
        wait.hardWait(100);
        shelving.rearrangeDropdownOptions.get(4).click();
        shelving.rearrangeDropdownOptions.get(5).click();
        wait.hardWait(100);
        shelving.rearrangeDropdown.click();
    }

    @Then("user verifies the Shelf Table column names")
    public void user_verifies_the_Shelf_Table_column_names() {
        String column = "";
        for (WebElement element : shelving.shelfTableColumns) {
            column = element.getText();
        }
        List<String> shelfColumns = new ArrayList<>(Arrays.asList("Job Number", "Status",
                "Assigned User"));
        if (shelfColumns.contains(column)) {
            System.out.println("Table has correct column names");
        } else {
            System.out.println("Table has wrong column names");
        }
    }

    @When("user enters a Shelf Number")
    public void user_enters_a_Shelf_Number() {
        shelving.enterShelfNumber.sendKeys("2");
        actions.sendKeys(Keys.TAB).build().perform();
    }

    @When("user enters a Shelf Width")
    public void user_enters_a_Shelf_Width() {
        shelving.enterShelfWidth.sendKeys("5");
        actions.sendKeys(Keys.TAB).build().perform();
    }

    @When("user enters a Shelf Height")
    public void user_enters_a_Shelf_Height() {
        shelving.enterShelfHeight.sendKeys("3");
        actions.sendKeys(Keys.TAB).build().perform();
    }

    @When("user enters a Shelf Depth")
    public void user_enters_a_Shelf_Depth() {
        shelving.enterShelfDepth.sendKeys("1");
        actions.sendKeys(Keys.TAB).build().perform();
    }

    @When("user selects a Container Type")
    public void user_selects_a_Container_Type() {
        shelving.selectType.click();
        genHelp.getElement(By.cssSelector("div [role='option']:nth-child(1)")).click();
    }

    @When("user clicks Create Shelf button")
    public void user_clicks_Create_Shelf_button() {
        shelving.submit.click();
    }

    @When("user selects From Verification Job option")
    public void user_selects_From_Verification_Job_option() {
        helper.jSClick(shelving.fromVerificationJob);
    }

    @When("user selects No")
    public void user_selects_No() {
        helper.jSClick(shelving.no);
    }

    @Then("user selects a Verification Job from the Verification Job\\(s) List")
    public void user_selects_a_Verification_Job_from_the_Verification_Job_s_List() throws InterruptedException {
        shelving.selectByNumber.click();
        for (WebElement job : shelving.verificationJobsList) {
            if (job.getText().equals(VerificationSteps.verifificationJobNumber)) {
                job.click();
            }
        }
        shelving.selectByNumber.click();
        wait.hardWait(1000);
    }

    @Then("user selects a Building from Shelving Locations")
    public void user_selects_a_Building_from_Shelving_Locations() {
        WaitHelper.waitForClickability(shelving.building, 1000);
        shelving.building.click();
        helper.jSClick(shelving.buildings.get(0));
    }

    @Then("user clicks Submit")
    public void user_clicks_Submit() throws InterruptedException {
        wait.hardWait(100);
        helper.jSClick(shelving.submit);
    }

    @When("user selects Yes")
    public void user_selects_Yes() {
        WaitHelper.waitForClickability(shelving.yes, 3000);
        shelving.yes.click();
    }

    @Then("a new modal with shelving location options along with the verification job selection is displayed")
    public void a_new_modal_with_shelving_location_options_along_with_the_verification_job_selection_is_displayed() {
        Helper.verifyElementDisplayed(shelving.createShelvingJobModal);
    }

    @When("user navigates to Shelving Job")
    public void user_navigates_to_Shelving_Job() {
//        driver.get("https://test.fetch.loctest.gov/shelving/28");
        WebElement job = driver.findElement(By.xpath("//td[.='29']"));
        job.click();
    }

    @When("user clicks three dot menu next to a container")
    public void user_clicks_three_dot_menu_next_to_a_container() {
        helper.jSClick(shelving.threeDotNextToContainer);
    }

    @Then("user should see Edit Location option")
    public void user_should_see_Edit_Location_option() {
        WaitHelper.waitForVisibility(shelving.editOrAssign, 10);
        assertEquals("Edit Location", shelving.editOrAssign.getText());
    }

    @Then("user clicks Edit Location button")
    public void user_clicks_Edit_Location_button() {
        shelving.editOrAssign.click();
    }

    @When("user navigates to Shelving Job with Running Status")
    public void user_navigates_to_Shelving_Job_with_Running_Status() throws InterruptedException {
        helper.jSClick(shelving.runningJob);
        wait.hardWait(1000);
    }

    @Then("Assign User dropdown is clickable")
    public void assign_user_dropdown_is_clickable() {
        Helper.isClickable(shelving.assignedUserField);
    }

    @Then("user selects User from dropdown")
    public void user_selects_User_from_dropdown() {
        Helper.clickWithJS(shelving.assignedUserField);
        shelving.allDropdownOptions.get(1).click();
    }

    @Then("Save Edits button is clickable")
    public void save_edits_button_is_clickable() {
        Helper.isClickable(shelving.saveEdits);
    }

    @Then("Cancel edits button is clickable")
    public void cancel_edits_button_is_clickable() {
        Helper.isClickable(shelving.cancelEdits);
    }

    @Then("user clicks Save Edits button")
    public void user_clicks_Save_Edits_button() {
        Helper.clickWithJS(shelving.saveEdits);
    }

    @When("user selects Direct To Shelve option")
    public void user_selects_direct_to_shelve_option() {
        helper.jSClick(shelving.directToShelve);
    }

    @Then("user selects Right side")
    public void user_selects_Right_side() {
        WaitHelper.waitForClickability(shelving.rightSide, 1000);
        helper.jSClick(shelving.rightSide);
    }

    @Then("user verifies the Status is {string}")
    public void user_verifies_the_status_is(String string) throws InterruptedException {
        Assert.assertEquals("Shelving Job status does not match! ", string, shelving.shelvingJobStatus.getText());
        wait.hardWait(1000);
    }

    @When("user clicks Execute Job")
    public void user_clicks_execute_job() throws InterruptedException {
        helper.jSClick(shelving.executeJob);
        wait.hardWait(2000);
        helper.jSClick(shelving.beAwareMsg);
        wait.hardWait(100);
    }

    @Then("user selects a created Verification Job")
    public void user_selects_a_created_Verification_Job() throws InterruptedException {
        WaitHelper.waitForClickability(shelving.selectByNumber, 1000);
        shelving.selectByNumber.click();
        for (WebElement dropdownValue : shelving.verificationJobsList) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownValue);
            if (dropdownValue.getText().contains(VerificationSteps.verifificationJobNumber)) {
                dropdownValue.click();
            }
        }
        shelving.selectByNumber.click();
        wait.hardWait(1000);
    }

    @Then("user selects verification job")
    public void user_selects_verification_job() throws InterruptedException {
        WaitHelper.waitForClickability(shelving.selectByNumber, 1000);
        shelving.selectByNumber.click();
        helper.jSClick(shelving.verificationJobsList.get(0));
        shelving.selectByNumber.click();
        wait.hardWait(1000);
    }

    @And("user scans Container")
    public void user_scans_Container() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys(shelving.containerBarcode.getText());
        wait.hardWait(2000);
    }

    @And("user scans wrong Container")
    public void user_scans_wrong_Container() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys("123");
        wait.hardWait(2000);
    }

    @And("user scans shelf to verify Container")
    public void user_scans_shelf_to_verify_Container() throws InterruptedException {
        driver.findElement(By.tagName("body")).sendKeys("1");
        WaitHelper.waitForVisibility(shelving.assignedShelf, 1000);
        String shelf = shelving.assignedShelf.getText().substring(79, 85);
        shelving.closeMsg.click();
        wait.hardWait(1000);
        driver.findElement(By.tagName("body")).sendKeys("" + shelf + "");
        Assert.assertEquals("Shelved", shelving.shelvedCheckMark.getText());
    }

    @And("user verifies second Container if exists")
    public void user_verifies_second_Container_if_exists() throws InterruptedException {
        List<WebElement> containerList = driver.findElements(By.cssSelector("[class='q-table'] tbody tr"));
        if (containerList.size() > 1) {
            driver.findElement(By.tagName("body")).sendKeys(shelving.containerBarcode.getText());
            wait.hardWait(2000);
            driver.findElement(By.tagName("body")).sendKeys("1");
            WaitHelper.waitForVisibility(shelving.assignedShelf, 1000);
            String shelf = shelving.assignedShelf.getText().substring(79, 85);
            System.out.println(shelf);
            shelving.closeMsg.click();
            wait.hardWait(1000);
            driver.findElement(By.tagName("body")).sendKeys("" + shelf + "");
            Assert.assertEquals("Shelved", shelving.shelvedCheckMark.getText());
        } else {
            System.out.println("Job has only one container");
        }
    }

    @And("user selects Shelf")
    public void user_selects_Shelf() throws InterruptedException {
        helper.scrollToElement(shelving.selectShelf);
        WaitHelper.waitForClickability(shelving.selectShelf, 1000);
        shelving.selectShelf.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(0).click();
    }

    @And("user selects Shelf Position")
    public void userSelectsShelfPosition() throws InterruptedException {
        helper.scrollToElement(shelving.selectShelfPosition);
        WaitHelper.waitForClickability(shelving.selectShelfPosition, 1000);
        shelving.selectShelfPosition.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(0).click();
        wait.hardWait(1000);
    }

    @When("user clicks Complete Job")
    public void user_clicks_Complete_Job() throws InterruptedException {
        wait.hardWait(1000);
        helper.jSClick(shelving.completeJob);
        wait.hardWait(2000);
    }

    @When("user clicks three dot menu next to Container")
    public void user_clicks_three_dot_menu_next_to_Container() {
        helper.jSClick(shelving.threeDotNextToContainer);
    }

    @Then("user clicks Edit Location")
    public void user_clicks_Edit_Location() {
        helper.jSClick(shelving.editLocation);
    }

    @Then("user verifies Edit Location button is clickable")
    public void user_verifies_Edit_Location_button_is_clickable() {
        Helper.isClickable(shelving.editLocation);
        helper.jSClick(shelving.threeDotNextToContainer);
    }

    @When("user sets input delay and switches Toggle on")
    public void user_sets_input_delay_and_switches_Toggle_on() throws InterruptedException {
        WebElement userIcon = driver.findElement(By.cssSelector("[aria-label='UserMenu']"));
        userIcon.click();
        wait.hardWait(2000);
        shelving.inputDelay.click();
        shelving.inputDelay.sendKeys(Keys.CONTROL + "a");
        shelving.inputDelay.sendKeys(Keys.DELETE);
        shelving.inputDelay.sendKeys("1.25");
        shelving.toggleScan.click();
        wait.hardWait(1000);
    }

    @And("user saves item barcode")
    public void user_saves_item_barcode() {
        itemBarcode = shelving.containerBarcode.getText();
        System.out.println("Item barcode is : " + itemBarcode);
    }

    @When("user completes a Shelving Job")
    public void user_completes_a_Shelving_Job() throws InterruptedException {
        user_clicks_on_create_shelving_job_button();
        user_selects_From_Verification_Job_option();
        user_selects_a_created_Verification_Job();
        user_selects_a_Building_from_Shelving_Locations();
        user_clicks_Submit();
        user_clicks_execute_job();
        user_scans_Container();
        user_saves_item_barcode();
        user_scans_shelf_to_verify_Container();
        user_clicks_Complete_Job();
    }


}




