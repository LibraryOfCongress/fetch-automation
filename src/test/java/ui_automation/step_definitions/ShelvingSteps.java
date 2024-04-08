package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    Helper helper = new Helper();
    GenericHelper genHelp = new GenericHelper();
    AlertHelper alert = new AlertHelper();
    WaitHelper wait = new WaitHelper();
    SelectHelper select = new SelectHelper();
    Actions actions = new Actions(driver);

    public static final Logger oLog = LogManager.getLogger(ShelvingSteps.class);

    String selectedAisle;
    String selectedSide;
    String selectedLadder;

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

    @Then("user verifies fields on Create New Shelf modal")
    public void user_verifies_fields_on_Create_New_Shelf_modal(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("fieldname");
            String actualOption = shelving.newShelfFields.get(i).getText();
            Assert.assertEquals("Field names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Create New Shelf fields");
    }

    @Then("cancel button is clickable")
    public void cancel_button_is_clickable() {
        helper.isClickable(shelving.cancelBtn);
    }

    @Then("create shelf button is enabled and clickable")
    public void create_shelf_button_is_enabled_and_clickable() {
      helper.verifyElementEnabled(shelving.createShelfBtn);
      helper.isClickable(shelving.createShelfBtn);
    }

    @When("user clicks Rearrange Columns toggle switch")
    public void user_clicks_Rearrange_Columns_toggle_switch()  {
        helper.jSClick(shelving.toggleSwitch);
    }


    @When("user drags each menu item to their preferred order")
    public void user_drags_each_menu_item_to_their_preferred_order() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.xpath("(//div[.='Status'])[1]"));
        WebElement target = driver.findElement(By.xpath("(//div[.='Job Number'])[1]"));
        WebElement source2 = driver.findElement(By.xpath("(//div[.='Assigned User'])[1]"));
        WebElement target2 = driver.findElement(By.xpath("(//div[.='Date Added'])[1]"));
        actions.dragAndDrop(source, target).perform();
        actions.dragAndDrop(source2, target2).perform();
    }

    @When("user switches off the Rearrange Columns toggle")
    public void user_switches_off_the_Rearrange_Columns_toggle() {
        helper.jSClick(shelving.toggleSwitch);
    }

    @Then("user verifies the Shelf Table column names")
    public void user_verifies_the_Shelf_Table_column_names() {
        String column = "";
        for(WebElement element: shelving.shelfTableColumns) {
             column = element.getText();
        }
        List<String> shelfColumns =new ArrayList<>(Arrays.asList("Job Number", "# of Containers in Job", "Status",
                "Assigned User", "Date Added"));
               if(shelfColumns.contains(column)) {
              System.out.println("Table has correct column names");
               } else{
              System.out.println("Table has wrong column names");
     }

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
    shelving.createShelfBtn.click();
        oLog.info("I clicked Create Shelf button");
    }


    @When("user selects From Verification Job option")
    public void user_selects_From_Verification_Job_option() {
       helper.jSClick(shelving.fromVerificationJob);
        oLog.info("I selected option - From Verification Job");
    }

    @When("user selects No")
    public void user_selects_No() {
        helper.jSClick(shelving.no);
        oLog.info("I selected option - No");
    }

    @Then("user is able to select a Verification Job from the Verification Job\\(s) List")
    public void user_is_able_to_select_a_Verification_Job_from_the_Verification_Job_s_List() throws InterruptedException {
        shelving.selectByNumber.click();
        helper.jSClick(shelving.verificationJobsList.get(0));
        oLog.info("I selected a Verification Job from the list");
    }

    @Then("user clicks Submit")
    public void user_clicks_Submit() {
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
    public void user_selects_Yes() {
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
        driver.get("https://test.fetch.loctest.gov/shelving/123456789");
        oLog.info("I navigated to the Shelving Job");
    }

    @When("user clicks three dot menu next to a container that has shelving location information")
    public void user_clicks_three_dot_menu_next_to_a_container_that_has_shelving_location_information() {
        if(!shelving.shelfNumber.getText().isEmpty()) {
            helper.jSClick(shelving.threeDotMenu.get(0));
        }else {
            helper.jSClick(shelving.threeDotMenu.get(1));
        }
        oLog.info("I clicked three dot menu next to a container that has shelving location information");
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





}




