package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui_automation.pages.ShelfMgmtPage;
import ui_automation.utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ShelfMgmtSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    ShelfMgmtPage shelf = new ShelfMgmtPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    SelectHelper select = new SelectHelper();

    public static final Logger oLog = LogManager.getLogger(ShelfMgmtSteps.class);

    String selectedAisle;
    String selectedSide;
    String selectedLadder;

    @Given("user navigates to Shelving Page")
    public void user_navigates_to_Shelving_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "shelvingMgmtURL"));
    }

    @When("user is on Shelving page")
    public void user_is_on_Shelving_Page() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://c2vldimsweb01.loctest.gov/shelving";
        Assert.assertEquals("Shelving page URL failed",
                expectedURL, actualURL);
    }

    @Then("Aisle field is clickable")
    public void aisle_field_is_clickable() {
        helper.isClickable(shelf.aisle);
    }

    @Then("Side field is clickable")
    public void side_field_is_clickable() {
        helper.isClickable(shelf.side);
    }

    @Then("Ladder field is clickable")
    public void ladder_field_is_clickable() {
        helper.isClickable(shelf.ladder);
    }

    @Then("Filter dropdown is clickable")
    public void filter_dropdown_is_clickable() {
        helper.isClickable(shelf.filterDropdown);
    }

    @Then("Add shelf button is clickable")
    public void add_shelf_button_is_clickable() {
        helper.isClickable(shelf.addShelfBtn);
    }

    @When("user selects an option from Aisle dropdown")
    public void user_selects_an_option_from_Aisle_dropdown() throws InterruptedException {
        helper.jSClick(shelf.aisle);
        shelf.allDropdownOptions.get(1).click();
        selectedAisle = shelf.aisle.getText();
        System.out.println("Selected aisle is " + selectedAisle);
        wait.hardWait(1000);
    }

    @When("user selects an option from Side dropdown")
    public void user_selects_an_option_from_Side_dropdown() throws InterruptedException {
        helper.jSClick(shelf.side);
        shelf.allDropdownOptions.get(1).click();
        selectedSide = shelf.side.getText();
        wait.hardWait(1000);
    }

    @When("user selects an option from Ladder dropdown")
    public void user_selects_an_option_from_Ladder_dropdown() {
        helper.jSClick(shelf.ladder);
        shelf.allDropdownOptions.get(2).click();
        selectedLadder = shelf.ladder.getText();
    }

    @Then("the selected options should be displayed")
    public void the_selected_options_should_be_displayed() {
       // driver.findElement(By.cssSelector("[id='aisleSelection']")).click();
        String displayedAisle = driver.findElement(By.xpath("(//*[@role='combobox'])[1]")).getText();
        System.out.println("Displayed aisle is " + displayedAisle);
        Assert.assertEquals("Selected Aisle is not displayed", selectedAisle, displayedAisle);
        String displayedSide = driver.findElement(By.xpath("(//*[@role='combobox'])[2]")).getText();
        Assert.assertEquals("Selected Side is not displayed", selectedSide, displayedSide);
        String displayedLadder = driver.findElement(By.xpath("(//*[@role='combobox'])[3]")).getText();
        Assert.assertEquals("Selected Ladder is not displayed", selectedLadder, displayedLadder);
        oLog.info("All selected options from Aisle, Side and Ladder dropdown are displayed");
    }


    @When("user clicks on filter dropdown")
    public void user_clicks_on_filter_dropdown() {
        helper.jSClick(shelf.filterDropdown);
    }

    @Then("user verifies filter dropdown options")
    public void user_verifies_filter_dropdown_options(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedOption = map.get("optionname");
            String actualOption = shelf.allFilterDropdownOptions.get(i).getText();
            Assert.assertEquals("Options names verification failed",
                    expectedOption, actualOption);
            i++;
        }
        oLog.info("I verified Filter Dropdown Options");
    }

    @Then("user verifies filter dropdown checkboxes are clickable")
    public void user_verifies_filter_dropdown_checkboxes_are_clickable() {
        for (WebElement option : shelf.allFilterDropdownOptions) {
            assertTrue("Checkbox is not clickable: " + option.isEnabled(), option.isDisplayed());
        }
    }

    @Then("user verifies filter options match shelf table column options")
    public void user_verifies_filter_options_match_shelf_table_column_options() {
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
        if(checkboxValue.equals("Vacancy") || checkboxValue.equals("Available Capacity") ||
                checkboxValue.equals("Shelf Barcode") ){
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

    @When("user clicks on add shelf button")
    public void user_clicks_on_add_shelf_button() {
     helper.jSClick(shelf.addShelfBtn);
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
    public void user_clicks_Rearrange_Columns_toggle_switch() throws InterruptedException {
        helper.jSClick(shelf.toggleSwitch);
    }


    @When("user drags each menu item to their preferred order")
    public void user_drags_each_menu_item_to_their_preferred_order() throws InterruptedException {

       //TODO find out why fields are moving when rearranging
      //  Shelf Barcode field is not movable(moves other element instead)
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.cssSelector("div.q-virtual-scroll__content .q-item:nth-child(4)"));
        WebElement target = driver.findElement(By.cssSelector("div.q-virtual-scroll__content .q-item:nth-child(1)"));

        WebElement source2 = driver.findElement(By.cssSelector("div.q-virtual-scroll__content .q-item:nth-child(8)"));
        WebElement target2 = driver.findElement(By.xpath("(//div[.='Shelf Height'])[2]"));
        actions.dragAndDrop(source, target).perform();
        actions.dragAndDrop(source2, target2).perform();

    }

    @When("user switches off the Rearrange Columns toggle")
    public void user_switches_off_the_Rearrange_Columns_toggle() throws InterruptedException {
        helper.jSClick(shelf.toggleSwitch);
        Thread.sleep(3000);
    }

    @Then("user verifies the Shelf Table column names")
    public void user_verifies_the_Shelf_Table_column_names() {
        String column = "";
        for(WebElement element: shelf.shelfTableColumns) {
             column = element.getText();
        }
        List<String> shelfColumns =new ArrayList<>(Arrays.asList("Shelf Width", "Shelf Height", "Shelf Depth",
                "Vacancy", "Max Capacity", "Current Capacity", "Available Capacity", "Size Class", "Shelf Barcode"));
               if(shelfColumns.contains(column)) {
              System.out.println("Table has correct column names");
               } else{
              System.out.println("Table has wrong column names");
     }

    }


}




