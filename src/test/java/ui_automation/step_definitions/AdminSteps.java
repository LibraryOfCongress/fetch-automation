package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import ui_automation.pages.AdminPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;
import ui_automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AdminPage admin = new AdminPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    AlertSteps alertSteps = new AlertSteps();

    public static final Logger oLog = LogManager.getLogger(AdminSteps.class);


    @Given("user navigates to the Admin Page")
    public void user_navigates_to_the_Admin_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "adminURL"));
    }

    @When("user is on the Admin Page")
    public void user_is_on_the_Admin_Page() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://test.fetch.loctest.gov/admin";
        assertEquals("Admin Page URL failed", expectedURL, actualURL);
        oLog.info("I am on Admin page");
    }

    @Then("user verifies the Admin Dashboard contains links")
    public void user_verifies_the_Admin_Dashboard_contains_links() {
        int count = 0;
        for (WebElement link : admin.adminPageLinks) {
            count++;
            assertTrue(link.isDisplayed());
            System.out.println("Displayed Links: " + link.getText());
        }
        oLog.info("I verified Admin Dashboard contains " + count + " Links");
    }

    @When("user clicks Buildings")
    public void user_clicks_Buildings() {
        helper.jSClick(admin.buildingsLink);
        oLog.info("I clicked Buildings Link");
    }

    @Then("user verifies the Admin Dashboard contains Buildings")
    public void user_verifies_the_Admin_Dashboard_contains_Buildings() {
        int count = 0;
        for (WebElement building : admin.buildings) {
            count++;
            assertTrue(building.isDisplayed());
            System.out.println("Displayed Building " + count + " : " + building.getText());
        }
        oLog.info("I verified Admin Dashboard contains " + count + " Buildings");
    }

    @Then("Add New dropdown is displayed and clickable")
    public void add_New_dropdown_is_displayed_and_clickable() {
        helper.verifyElementDisplayed(admin.addNew);
        helper.isClickable(admin.addNew);
    }

    @Then("user verifies Add New dropdown options")
    public void user_verifies_Add_New_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        helper.jSClick(admin.addNew);
        wait.hardWait(1000);

        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("option");
            String actualField = admin.addNewOptions.get(i).getText();
            Assert.assertEquals("Options name verification failed", expectedField, actualField);
            i++;
        }
        helper.jSClick(admin.addNew);
        oLog.info("I verified Add New dropdown options");
    }

    @Then("Location Hierarchy dropdown is displayed and clickable")
    public void location_Hierarchy_dropdown_is_displayed_and_clickable() {
        helper.verifyElementDisplayed(admin.locationHierarchy);
        helper.isClickable(admin.locationHierarchy);
    }

    @Then("user verifies Location Hierarchy dropdown options")
    public void user_verifies_Location_Hierarchy_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        helper.jSClick(admin.locationHierarchy);
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("option");
            String actualField = admin.locHierOptions.get(i).getText();
            Assert.assertEquals("Options name verification failed", expectedField, actualField);
            i++;
        }
        oLog.info("I verified Location Hierarchy dropdown options");
    }

    @When("user selects Building")
    public void user_selects_Building() {
        helper.jSClick(admin.buildings.get(0));
        oLog.info("I selected Building");
    }

    @Then("user should see building's shelving items")
    public void user_should_see_building_s_shelving_items() {
        int count = 0;
        for (WebElement item : admin.shelvingItems) {
            count++;
            assertTrue(item.isDisplayed());
            System.out.println("Displayed Shelving Item " + count + " : " + item.getText());
        }
        oLog.info("Shelf table contains " + count + " items");
    }

    @When("user clicks three-dots on the left side of the table")
    public void user_clicks_three_dots_on_the_left_side_of_the_table() {
        admin.threeDots.get(0).click();
    }

    @When("user clicks Edit Shelf button")
    public void user_clicks_Edit_Shelf_button() {
        helper.jSClick(admin.editBtn);
    }

    @Then("Edit Shelf modal is displayed")
    public void edit_Shelf_modal_is_displayed() {
        wait.waitForVisibility(admin.editShelfModal, 1000);
        helper.verifyElementDisplayed(admin.editShelfModal);
        oLog.info("Edit Shelf modal is displayed");
    }

    @Then("user verifies fields on Edit Shelf modal")
    public void user_verifies_fields_on_Edit_Shelf_modal(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("fieldname");
            wait.hardWait(1000);
            String actualField = admin.editShelfFields.get(i).getText();
            Assert.assertEquals("Fields name verification failed", expectedField, actualField);
            i++;
        }
        oLog.info("I verified Edit Shelf modal fields");
    }

    @Then("user is able to edit all the fields")
    public void user_is_able_to_edit_all_the_fields() throws InterruptedException {

        admin.editShelfNumber.sendKeys(Keys.CONTROL + "a");
        admin.editShelfNumber.sendKeys(Keys.DELETE);
        admin.editShelfNumber.sendKeys("2");

//        helper.jSClick(admin.editOwner);
//        admin.modalFieldOptions.get(0).click();
//        actions.sendKeys(Keys.TAB).build().perform();

//        helper.jSClick(admin.editContainerField);
//        wait.hardWait(1000);
//        admin.modalFieldOptions.get(0).click();

        admin.editMaxCapacity.sendKeys(Keys.CONTROL + "a");
        admin.editMaxCapacity.sendKeys(Keys.DELETE);
        admin.editMaxCapacity.sendKeys("17");

        admin.editWidth.sendKeys(Keys.CONTROL + "a");
        admin.editWidth.sendKeys(Keys.DELETE);
        admin.editWidth.sendKeys("10");

        admin.editHeight.sendKeys(Keys.CONTROL + "a");
        admin.editHeight.sendKeys(Keys.DELETE);
        admin.editHeight.sendKeys("2");

        admin.editDepth.sendKeys(Keys.CONTROL + "a");
        admin.editDepth.sendKeys(Keys.DELETE);
        admin.editDepth.sendKeys("20");

        helper.jSClick(admin.editBarcode);
        admin.editBarcode.clear();
    }

    @Then("Update button is clickable")
    public void update_button_is_clickable() {
        helper.isClickable(admin.update);
    }

    @Then("Cancel button is clickable")
    public void cancel_button_is_clickable() {
        helper.isClickable(admin.cancelUpdate);
    }

    @When("user clicks Add New dropdown button")
    public void user_clicks_Add_New_dropdown_button() {
        helper.jSClick(admin.addNew);
    }

    @When("user selects add Building option")
    public void user_selects_add_Building_option() throws InterruptedException {
        admin.addNewOptions.get(0).click();
    }

    @Then("user verifies popup modal is displayed")
    public void user_verifies_popup_modal_is_displayed() {
        wait.waitForVisibility(admin.popUpModal, 1000);
        helper.verifyElementDisplayed(admin.popUpModal);
    }

    @Then("Building field is displayed")
    public void building_field_is_displayed() {
        helper.verifyElementDisplayed(admin.buildingField);
    }

    @Then("Create button is disabled")
    public void create_button_is_disabled() {
        Assert.assertEquals(false, admin.createBtn.isEnabled());
    }

    @When("user enters Building information")
    public void user_enters_Building_information() {
        admin.buildingField.sendKeys("Cabin Brunch");
    }

    @Then("Create button is enabled")
    public void create_button_is_enabled() {
        Assert.assertEquals(true, admin.createBtn.isEnabled());
    }

    @Then("user exits modal")
    public void user_exits_modal() {
        helper.jSClick(admin.cancelBtn);
    }

    @When("user selects add Module option")
    public void user_selects_add_Module_option() {
        admin.addNewOptions.get(1).click();
    }

    @Then("Building dropdown is clickable")
    public void building_dropdown_is_clickable() {
        helper.isClickable(admin.selectBuilding);
    }

    @Then("Module field is disabled")
    public void module_field_is_disabled() {
        Assert.assertEquals("true", admin.moduleField.getAttribute("disabled"));
    }

    @When("user selects Building from dropdown")
    public void user_selects_Building_from_dropdown() throws InterruptedException {
        admin.selectBuilding.click();
        wait.hardWait(1000);
        helper.jSClick(admin.fieldDropdwnList.get(0));
    }

    @Then("Module field is enabled")
    public void module_field_is_enabled() {
        helper.isClickable(admin.moduleField);
    }

    @When("user enters Module information")
    public void user_enters_Module_information() {
        helper.jSClick(admin.moduleField);
        admin.moduleField.sendKeys("Module 1");
    }

    @When("user selects add Aisle option")
    public void user_selects_add_Aisle_option() throws InterruptedException {
        admin.addNewOptions.get(2).click();
        wait.hardWait(1000);
    }

    @Then("Aisle field is disabled")
    public void aisle_field_is_disabled() {
        Assert.assertEquals(false, admin.aisleField.isEnabled());
    }

    @Then("Aisle field is enabled")
    public void aisle_field_is_enabled() {
        Assert.assertEquals(true, admin.aisleField.isEnabled());
    }

    @When("user selects Module from dropdown")
    public void user_selects_Module_from_dropdown() throws InterruptedException {
        wait.hardWait(1000);
        wait.handleStaleElement(By.cssSelector(".q-field__native [placeholder='Select Module']"), 4, 1000);

        admin.selectModule.click();
        admin.fieldDropdwnList.get(1).click();
    }

    @When("user enters Aisle information")
    public void user_enters_Aisle_information() {
        helper.jSClick(admin.aisleField);
        admin.aisleField.sendKeys("3");
    }

    @When("user selects add Ladder option")
    public void user_selects_add_Ladder_option() {
        admin.addNewOptions.get(3).click();
    }

    @Then("Ladder field is disabled")
    public void ladder_field_is_disabled() {
        Assert.assertEquals(false, admin.ladderField.isEnabled());
    }

    @Then("Side button is enabled")
    public void side_button_is_enabled() {
        Assert.assertEquals(true, admin.sideBtnLeft.isEnabled());
    }

    @Then("user selects Aisle from dropdown")
    public void user_selects_Aisle_from_dropdown() throws InterruptedException {
        wait.hardWait(1000);
        wait.handleStaleElement(By.cssSelector(".q-field__native [placeholder='Select Aisle']"), 4, 1000);

        admin.selectAisle.click();
        admin.fieldDropdwnList.get(0).click();
    }

    @Then("Ladder field is enabled")
    public void ladder_field_is_enabled() {
        Assert.assertEquals(true, admin.ladderField.isEnabled());
    }

    @Then("user enters Ladder information")
    public void user_enters_Ladder_information() {
        admin.ladderField.click();
        admin.ladderField.sendKeys("7");
    }

    @Then("user selects Side")
    public void user_selects_Side() throws InterruptedException {
        helper.jSClick(admin.sideBtnRight);
    }

    @When("user clicks Location Hierarchy dropdown button")
    public void user_clicks_Location_Hierarchy_dropdown_button() {
        helper.jSClick(admin.locationHierarchy);
    }

    @When("user selects Manual option")
    public void user_selects_Manual_option() {
        admin.locHierOptions.get(1).click();
    }

    @When("user selects Ladder")
    public void user_selects_Ladder() throws InterruptedException {
        helper.scrollToElement(admin.selectLadder);
        wait.waitForClickability(admin.selectLadder, 1000);
        admin.selectLadder.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(9).click();
    }

    @When("user selects Bulk Upload option")
    public void user_selects_Bulk_Upload_option() {
        admin.locHierOptions.get(0).click();
    }

    @When("user uploads file")
    public void user_uploads_file() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/fileUpload/document.xlsx";
        admin.upload.sendKeys(filePath);
        Thread.sleep(3000);

        String expectedFileName = "document.xlsx";
        String actualFileName = admin.uploadedFile.getText();
        Assert.assertEquals("Uploaded file verification failed!", expectedFileName, actualFileName);
        Thread.sleep(5000);
    }

    @When("user clicks Groups and Permissions")
    public void user_clicks_Groups_and_Permissions() {
        helper.jSClick(admin.groupsAndPermissionsLink);
        oLog.info("I clicked Groups&Permissions Link");
    }

    @Then("user verifies the dashboard contains Groups")
    public void user_verifies_the_dashboard_contains_Groups() throws InterruptedException {
        wait.hardWait(100);
        int count = 0;
        for (WebElement group : admin.groups) {
            count++;
            assertTrue(group.isDisplayed());
            System.out.println("Displayed Groups " + count + " : " + group.getText());
        }
        oLog.info("I verified dashboard contains " + count + " Groups");
    }

    @When("user clicks Add New Group")
    public void user_clicks_Add_New_Group() {
        helper.jSClick(admin.groups.getLast());
        oLog.info("I clicked Add New Group");
    }

    @When("user enters Group Name")
    public void user_enters_Group_Name() {
        helper.jSClick(admin.enterGroupNameField);
        admin.enterGroupNameField.sendKeys("Test");
        oLog.info("I clicked Add New Group");
    }

    @Then("user verifies a new Group is created")
    public void user_verifies_a_new_Group_is_created() {
        for (WebElement group : admin.groups) {
            if (group.getText().equals("Test")) {
                assertTrue(group.isDisplayed());
            }
        }
        oLog.info("New Group is created");
    }

    @Then("user closes alert message")
    public void user_closes_alert_message() {
        alertSteps.alert.closeToastMsg.click();
    }

    @When("user clicks three dot menu next to group name")
    public void user_clicks_three_dot_menu_next_to_group_ame() {
        helper.jSClick(admin.threeDotMenu.getLast());
        oLog.info("I clicked three dot menu");
    }

    @Then("user verifies all the options")
    public void user_verifies_all_the_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("option");
            String actualField = admin.menuOptions.get(i).getText();
            Assert.assertEquals("Options name verification failed", expectedField, actualField);
            i++;
        }
        oLog.info("I verified Group menu options");
    }

    @When("user clicks Edit Permissions")
    public void user_clicks_Edit_Permissions() {
        helper.jSClick(admin.menuOptions.getFirst());
        oLog.info("I clicked Edit Permissions");
    }

    @Then("user verifies permissions tab names")
    public void user_verifies_permissions_tab_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("name");
            String actualField = admin.tabNames.get(i).getText();
            Assert.assertEquals("Tab Name verification failed", expectedField, actualField);
            i++;
        }
        oLog.info("I verified permissions tab names");
    }

    @When("user adds some permissions")
    public void user_adds_some_permissions() throws InterruptedException {
        helper.jSClick(admin.yes.getFirst());
        wait.hardWait(1000);
        helper.jSClick(admin.yes.get(3));
        wait.hardWait(1000);
        WebElement verifTab = driver.findElement(By.cssSelector("[role='tab']:nth-child(2)"));
        helper.jSClick(verifTab);
        wait.hardWait(1000);
        helper.jSClick(admin.yes.getFirst());
        oLog.info("I added some permissions to Group");
    }

    @When("user clicks on Groups and Permissions breadcrumb link")
    public void user_clicks_on_Groups_and_Permissions_breadcrumb_link() {
        WebElement groupsAndPermissionsBreadcrumbLink = driver.findElement(By.xpath("//a[.='Groups & Permissions']"));
        groupsAndPermissionsBreadcrumbLink.click();
        oLog.info("I clicked Groups and Permissions Breadcrumb link");
    }

    @When("user clicks Add Edit Users in Group")
    public void user_clicks_Add_Edit_Users_in_Group() {
        helper.jSClick(admin.menuOptions.get(1));
        oLog.info("I clicked Add/Edit User(s) in Group");
    }

    @When("user selects User names")
    public void user_selects_User_names() {
        admin.selectUserToAddField.click();
        helper.jSClick(admin.usersList.get(4));
        helper.jSClick(admin.usersList.get(5));
        helper.jSClick(admin.selectUserToAddField);
        oLog.info("I selected Users");
    }

    @When("user clicks Add Users")
    public void user_clicks_Add_Users() throws InterruptedException {
        helper.jSClick(admin.addUsersBtn);
        wait.hardWait(1000);
        oLog.info("I clicked Add User(s) button");
    }

    @When("user clicks on User name")
    public void user_clicks_on_User_name() throws InterruptedException {
        wait.waitForClickability(admin.groupUserTabs.getFirst(), 100);
        admin.groupUserTabs.get(0).click();
        wait.hardWait(1000);
        oLog.info("I clicked on User");
    }

    @When("user is able to delete User from Group")
    public void user_is_able_to_delete_User_from_Group() {
        helper.jSClick(admin.deleteUserFromGroupBtn);
        oLog.info("I deleted User from Group");
    }

    @When("user closes modal")
    public void user_closes_modal() throws InterruptedException {
        helper.jSClick(admin.closeModal);
        wait.hardWait(2000);
    }

    @When("user clicks Rename Group Name")
    public void user_clicks_Rename_Group_Name() {
        helper.jSClick(admin.menuOptions.get(2));
        oLog.info("I clicked Rename Group Name");
    }

    @When("user renames Group")
    public void user_renames_Group() throws InterruptedException {
        helper.jSClick(admin.enterGroupNameField);
        admin.enterGroupNameField.sendKeys(Keys.CONTROL + "a");
        admin.enterGroupNameField.sendKeys(Keys.DELETE);
        admin.enterGroupNameField.sendKeys("Renamed Test");
        wait.hardWait(1000);
        oLog.info("I renamed Group");
    }

    @When("user saves changes")
    public void user_saves_changes() throws InterruptedException {
        helper.jSClick(admin.renameGroupBtn);
        wait.hardWait(1000);
        oLog.info("I saved changes to Group Name");
    }

    @When("user clicks Delete Group")
    public void user_clicks_Delete_Group() throws InterruptedException {
        helper.jSClick(admin.menuOptions.getLast());
        wait.hardWait(1000);
        oLog.info("I clicked Delete Group");
    }

    @Then("user is able to delete Group")
    public void user_is_able_to_delete_Group() {
        helper.jSClick(admin.confirmDeleteGroup);
        oLog.info("I deleted Group");
    }


}
