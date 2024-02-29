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

import static org.junit.Assert.assertTrue;

public class ShelvingSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    ShelvingPage shelf = new ShelvingPage();
    Helper helper = new Helper();
    GenericHelper genHelp = new GenericHelper();
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
        String expectedURL = "https://c2vldimsweb01.loctest.gov/shelving";
        Assert.assertEquals("Shelving page URL failed",
                expectedURL, actualURL);
        oLog.info("I am on Shelving page");
    }

    @Then("Filter icon is clickable")
    public void filter_icon_is_clickable() {
        helper.isClickable(shelf.filter);
    }
    @Then("Rearrange dropdown is clickable")
    public void rearrange_dropdown_is_clickable() {
        helper.isClickable(shelf.rearrangeDropdown);
    }

    @Then("Create Shelving Job button is clickable")
    public void create_shelving_job_button_is_clickable() {
        helper.isClickable(shelf.createShelvingJob);
    }

    @When("user clicks on Rearrange dropdown")
    public void user_clicks_on_rearrange_dropdown() {
        helper.jSClick(shelf.rearrangeDropdown);
    }

    @Then("user verifies dropdown options")
    public void user_verifies_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("optionname");
            wait.hardWait(1000);
            String actualOption = shelf.allFilterDropdownOptions.get(i).getText();
            Assert.assertEquals("Options names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Rearrange Dropdown Options");
    }

    @Then("user verifies dropdown checkboxes are clickable")
    public void user_verifies_dropdown_checkboxes_are_clickable() {
        for (WebElement option : shelf.allFilterDropdownOptions) {
            assertTrue("Checkbox is not clickable: " + option.isEnabled(), option.isDisplayed());
        }
    }

    @Then("user verifies dropdown options match shelf table column options")
    public void user_verifies_dropdown_options_match_shelf_table_column_options() {
        String shelfTableValue = "";
        String filterDropdownValue = "";
        for (WebElement option : shelf.shelfTableColumns) {
            wait.waitForVisibility(option,2000);
            shelfTableValue = option.getText();
        }
        for (WebElement value : shelf.allFilterDropdownOptions) {
            wait.waitForVisibility(value, 2000);
            filterDropdownValue = value.getText();
        }
        Assert.assertEquals("Options did not match!", filterDropdownValue, shelfTableValue);
    }

    @Then("user verifies all options are selected")
    public void user_verifies_all_options_are_selected() {
        for (WebElement checkbox : shelf.allFilterDropdownOptions) {
            if(checkbox.getAttribute("aria-selected").equals("false")){
                System.out.println("Not all the checkboxes are selected");
            }
        }
    }

    @Then("user is able to deselect all the options")
    public void user_is_able_to_deselect_all_the_options() {
        for(WebElement checkbox : shelf.allFilterDropdownOptions){
            select.selectCheckBox(checkbox, true);
        }
    }

    @Then("user selects options A, B and C from the dropdown")
    public void user_selects_options_A_B_and_C_from_the_dropdown() {
        for(WebElement checkbox : shelf.allFilterDropdownOptions) {
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
        for(WebElement checkbox : shelf.allFilterDropdownOptions){
            if(checkbox.getAttribute("aria-selected").equals("true")) {
                wait.waitForVisibility(checkbox, 2000);
                checkboxValue = checkbox.getText();
            }
        }
        for(int i = 0; i <shelf.shelfTableColumns.size(); i++) {
           shelfTableValue = shelf.shelfTableColumns.get(i).getText();
        }
        Assert.assertEquals("Selected options are not displayed!", checkboxValue, shelfTableValue);
    }

    @When("user clicks on Create Shelving Job button")
    public void user_clicks_on_create_shelving_job_button() {
     helper.jSClick(shelf.createShelvingJob);
    }

    @Then("user verifies fields on Create New Shelf modal")
    public void user_verifies_fields_on_Create_New_Shelf_modal(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("fieldname");
            String actualOption = shelf.newShelfFields.get(i).getText();
            Assert.assertEquals("Field names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Create New Shelf fields");
    }

    @Then("cancel button is clickable")
    public void cancel_button_is_clickable() {
        helper.isClickable(shelf.cancelBtn);
    }

    @Then("create shelf button is enabled and clickable")
    public void create_shelf_button_is_enabled_and_clickable() {
      helper.verifyElementEnabled(shelf.createShelfBtn);
      helper.isClickable(shelf.createShelfBtn);
    }

    @When("user clicks Rearrange Columns toggle switch")
    public void user_clicks_Rearrange_Columns_toggle_switch()  {
        helper.jSClick(shelf.toggleSwitch);
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
        helper.jSClick(shelf.toggleSwitch);
    }

    @Then("user verifies the Shelf Table column names")
    public void user_verifies_the_Shelf_Table_column_names() {
        String column = "";
        for(WebElement element: shelf.shelfTableColumns) {
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
    shelf.selectOwner.click();
    wait.hardWait(1000);
    shelf.sanders.click();
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I selected an Owner");
    }

    @When("user enters a Shelf Number")
    public void user_enters_a_Shelf_Number() {
    shelf.enterShelfNumber.sendKeys("1");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Number");
    }

    @When("user enters a Shelf Width")
    public void user_enters_a_Shelf_Width() {
    shelf.enterShelfWidth.sendKeys("5");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Width");
    }

    @When("user enters a Shelf Height")
    public void user_enters_a_Shelf_Height() {
    shelf.enterShelfHeight.sendKeys("3");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Height");
    }


    @When("user enters a Shelf Depth")
    public void user_enters_a_Shelf_Depth() {
    shelf.enterShelfDepth.sendKeys("1");
    actions.sendKeys(Keys.TAB).build().perform();
        oLog.info("I entered a Shelf Depth");
    }

    @When("user selects a Container Type")
    public void user_selects_a_Container_Type() throws InterruptedException {
    shelf.selectType.click();
    genHelp.getElement(By.cssSelector("div [role='option']:nth-child(1)")).click();
        oLog.info("I selected a Container Type");
    }

    @When("user clicks Create Shelf button")
    public void user_clicks_Create_Shelf_button() {
    shelf.createShelfBtn.click();
        oLog.info("I clicked Create Shelf button");
    }



}




