package automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import automation.pages.AdminPage;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import automation.utilities.Helper;
import automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AdminPage admin = new AdminPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    AlertSteps alertSteps = new AlertSteps();


    @Given("user navigates to the Admin Page")
    public void user_navigates_to_the_admin_page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("config.properties", "adminURL"));
    }

    @When("user is on the Admin Page")
    public void user_is_on_the_admin_page() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://test.fetch.loctest.gov/admin";
        assertEquals("Admin Page URL failed", expectedURL, actualURL);
    }

    @Then("user verifies the Admin Dashboard contains links")
    public void user_verifies_the_admin_dashboard_contains_links() {
        for (WebElement link : admin.adminPageLinks) {
            assertTrue(link.isDisplayed());
        }
    }

    @When("user clicks Buildings")
    public void user_clicks_buildings() throws InterruptedException {
        WaitHelper.waitForClickability(admin.buildingsLink, 2000);
        helper.jSClick(admin.buildingsLink);
        wait.hardWait(100);
    }

    @When("user selects Buildings")
    public void user_selects_buildings() throws InterruptedException {
        WaitHelper.waitForClickability(admin.lmDropdownLinks.get(0), 2000);
        helper.jSClick(admin.lmDropdownLinks.get(0));
        wait.hardWait(100);
    }

    @When("user clicks Location Manager Link")
    public void user_clicks_location_manager_Link() throws InterruptedException {
        WaitHelper.waitForClickability(admin.locationManagerLink, 2000);
        helper.jSClick(admin.locationManagerLink);
        wait.hardWait(100);
    }

    @Then("user verifies the Admin Dashboard contains Buildings")
    public void user_verifies_the_admin_dashboard_contains_buildings() {
        int count = 0;
        for (WebElement building : admin.buildings) {
            count++;
            assertTrue(building.isDisplayed());
            System.out.println("Displayed Building " + count + " : " + building.getText());
        }
    }

    @Then("Add New dropdown is displayed and clickable")
    public void add_new_dropdown_is_displayed_and_clickable() {
        Helper.verifyElementDisplayed(admin.addNew);
        Helper.isClickable(admin.addNew);
    }

    @Then("user verifies Location Manager dropdown options")
    public void user_verifies_location_manager_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedField = map.get("option");
            String actualField = admin.lmDropdownLinks.get(i).getText();
            Assert.assertTrue(expectedField, actualField.contains(expectedField));
            i++;
        }
    }

    @Then("Location Hierarchy dropdown is displayed and clickable")
    public void location_hierarchy_dropdown_is_displayed_and_clickable() {
        Helper.verifyElementDisplayed(admin.locationHierarchy);
        Helper.isClickable(admin.locationHierarchy);
    }

    @Then("user verifies Location Hierarchy dropdown options")
    public void user_verifies_location_hierarchy_dropdown_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
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
    }

    @When("user selects Building")
    public void user_selects_building() {
        helper.jSClick(admin.lmDropdownLinks.get(0));
    }

    @Then("user should see building's shelving items")
    public void user_should_see_building_s_shelving_items() {
        int count = 0;
        for (WebElement item : admin.shelvingItems) {
            count++;
            assertTrue(item.isDisplayed());
            System.out.println("Displayed Shelving Item " + count + " : " + item.getText());
        }
    }

    @When("user clicks three-dots on the left side of the table")
    public void user_clicks_three_dots_on_the_left_side_of_the_table() {
        admin.threeDots.get(0).click();
    }

    @When("user clicks Edit Shelf button")
    public void user_clicks_edit_shelf_button() {
        helper.jSClick(admin.editBtn);
    }

    @Then("Edit Shelf modal is displayed")
    public void edit_shelf_modal_is_displayed() {
        WaitHelper.waitForVisibility(admin.editShelfModal, 1000);
        Helper.verifyElementDisplayed(admin.editShelfModal);
    }

    @Then("user verifies fields on Edit Shelf modal")
    public void user_verifies_fields_on_edit_shelf_modal(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("fieldname");
            wait.hardWait(1000);
            String actualField = admin.editShelfFields.get(i).getText();
            Assert.assertEquals("Fields name verification failed", expectedField, actualField);
            i++;
        }
    }

    @Then("user is able to edit all the fields")
    public void user_is_able_to_edit_all_the_fields() {
        admin.editShelfNumber.sendKeys(Keys.CONTROL + "a");
        admin.editShelfNumber.sendKeys(Keys.DELETE);
        admin.editShelfNumber.sendKeys("2");

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
        Helper.isClickable(admin.update);
    }

    @Then("Cancel button is clickable")
    public void cancel_button_is_clickable() {
        Helper.isClickable(admin.cancelUpdate);
    }

    @When("user clicks Add New dropdown button")
    public void user_clicks_add_new_dropdown_button() {
        helper.jSClick(admin.addNew);
    }

    @When("user selects add Building option")
    public void user_selects_add_building_option() {
        admin.addNewOptions.get(0).click();
    }

    @Then("user verifies popup modal is displayed")
    public void user_verifies_popup_modal_is_displayed() {
        WaitHelper.waitForVisibility(admin.popUpModal, 1000);
        Helper.verifyElementDisplayed(admin.popUpModal);
    }

    @Then("Building field is displayed")
    public void building_field_is_displayed() {
        Helper.verifyElementDisplayed(admin.buildingField);
    }

    @Then("Create button is disabled")
    public void create_button_is_disabled() {
        assertFalse(admin.createBtn.isEnabled());
    }

    @When("user enters Building information")
    public void user_enters_building_information() {
        admin.buildingField.sendKeys("Cabin Brunch");
    }

    @Then("Create button is enabled")
    public void create_button_is_enabled() {
        assertTrue(admin.createBtn.isEnabled());
    }

    @Then("user exits modal")
    public void user_exits_modal() {
        helper.jSClick(admin.cancelBtn);
    }

    @When("user selects add Module option")
    public void user_selects_add_module_option() {
        admin.addNewOptions.get(1).click();
    }

    @Then("Building dropdown is clickable")
    public void building_dropdown_is_clickable() {
        Helper.isClickable(admin.selectBuilding);
    }

    @Then("Module field is disabled")
    public void module_field_is_disabled() {
        Assert.assertEquals("true", admin.moduleField.getAttribute("disabled"));
    }

    @When("user selects Building from dropdown")
    public void user_selects_building_from_dropdown() throws InterruptedException {
        admin.selectBuilding.click();
        wait.hardWait(1000);
        helper.jSClick(admin.fieldDropdwnList.get(0));
    }

    @Then("Module field is enabled")
    public void module_field_is_enabled() {
        Helper.isClickable(admin.moduleField);
    }

    @When("user enters Module information")
    public void user_enters_module_information() {
        helper.jSClick(admin.moduleField);
        admin.moduleField.sendKeys("Module 1");
    }

    @When("user selects add Aisle option")
    public void user_selects_add_aisle_option() throws InterruptedException {
        admin.addNewOptions.get(2).click();
        wait.hardWait(1000);
    }

    @Then("Aisle field is disabled")
    public void aisle_field_is_disabled() {
        assertFalse(admin.aisleField.isEnabled());
    }

    @Then("Aisle field is enabled")
    public void aisle_field_is_enabled() {
        assertTrue(admin.aisleField.isEnabled());
    }

    @When("user selects Module from dropdown")
    public void user_selects_module_from_dropdown() throws InterruptedException {
        wait.hardWait(1000);
        wait.handleStaleElement(By.cssSelector(".q-field__native [placeholder='Select Module']"), 4, 1000);
        admin.selectModule.click();
        admin.fieldDropdwnList.get(0).click();
    }

    @When("user enters Aisle information")
    public void user_enters_aisle_information() {
        helper.jSClick(admin.aisleField);
        admin.aisleField.sendKeys("3");
    }

    @When("user selects add Ladder option")
    public void user_selects_add_ladder_option() {
        admin.addNewOptions.get(3).click();
    }

    @Then("Ladder field is disabled")
    public void ladder_field_is_disabled() {
        assertFalse(admin.ladderField.isEnabled());
    }

    @Then("Side button is enabled")
    public void side_button_is_enabled() {
        assertTrue(admin.sideBtnLeft.isEnabled());
    }

    @Then("user selects Aisle from dropdown")
    public void user_selects_aisle_from_dropdown() throws InterruptedException {
        wait.hardWait(1000);
        wait.handleStaleElement(By.cssSelector(".q-field__native [placeholder='Select Aisle']"), 4, 1000);
        admin.selectAisle.click();
        admin.fieldDropdwnList.get(0).click();
    }

    @Then("Ladder field is enabled")
    public void ladder_field_is_enabled() {
        assertTrue(admin.ladderField.isEnabled());
    }

    @Then("user enters Ladder information")
    public void user_enters_ladder_information() {
        admin.ladderField.click();
        admin.ladderField.sendKeys("7");
    }

    @Then("user selects Side")
    public void user_selects_side() {
        helper.jSClick(admin.sideBtnRight);
    }

    @When("user clicks Location Hierarchy dropdown button")
    public void user_clicks_location_hierarchy_dropdown_button() {
        helper.jSClick(admin.locationHierarchy);
    }

    @When("user selects Manual option")
    public void user_selects_manual_option() {
        admin.locHierOptions.get(1).click();
    }

    @When("user selects Ladder")
    public void user_selects_ladder() throws InterruptedException {
        helper.scrollToElement(admin.selectLadder);
        WaitHelper.waitForClickability(admin.selectLadder, 1000);
        admin.selectLadder.click();
        wait.hardWait(1000);
        admin.modalFieldOptions.get(0).click();
    }

    @When("user selects Bulk Upload option")
    public void user_selects_bulk_upload_option() {
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
    public void user_clicks_groups_and_permissions() {
        helper.jSClick(admin.groupsAndPermissionsLink);
    }

    @Then("user verifies the dashboard contains Groups")
    public void user_verifies_the_dashboard_contains_groups() throws InterruptedException {
        wait.hardWait(100);
        int count = 0;
        for (WebElement group : admin.groups) {
            count++;
            assertTrue(group.isDisplayed());
            System.out.println("Displayed Groups " + count + " : " + group.getText());
        }
    }

    @When("user clicks Add New Group")
    public void user_clicks_add_new_group() {
        helper.jSClick(admin.addNewGroup);
    }

    @When("user enters Group Name")
    public void user_enters_Group_Name() {
        helper.jSClick(admin.enterGroupNameField);
        admin.enterGroupNameField.sendKeys("Test");
    }

    @Then("user verifies a new Group is created")
    public void user_verifies_a_new_group_is_created() {
        for (WebElement group : admin.groups) {
            if (group.getText().equals("Test")) {
                assertTrue(group.isDisplayed());
            }
        }
    }

    @Then("user closes alert message")
    public void user_closes_alert_message() {
        alertSteps.alert.closeToastMsg.click();
    }

    @When("user clicks three dots menu")
    public void user_clicks_three_dots_menu() {
        helper.jSClick(admin.threeDotsMenu.getLast());
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
    }

    @When("user clicks Edit Permissions")
    public void user_clicks_edit_permissions() {
        helper.jSClick(admin.menuOptions.getFirst());
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
    }

    @Then("user verifies table columns")
    public void user_verifies_table_columns(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        wait.hardWait(1000);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 1;
        for (Map<String, String> map : maps) {
            String expectedField = map.get("column");
            String actualField = admin.tableColumns.get(i).getText();
            Assert.assertTrue(actualField.contains(expectedField));
            i++;
        }
    }

    @When("user adds some permissions")
    public void user_adds_some_permissions() throws InterruptedException {
        helper.jSClick(admin.no.getFirst());
        wait.hardWait(1000);
        helper.jSClick(admin.yes.get(1));
        wait.hardWait(1000);
        WebElement verifTab = driver.findElement(By.cssSelector("[role='tab']:nth-child(2)"));
        helper.jSClick(verifTab);
        wait.hardWait(1000);
        helper.jSClick(admin.yes.getFirst());
    }

    @When("user clicks on Groups and Permissions breadcrumb link")
    public void user_clicks_on_groups_and_permissions_breadcrumb_link() {
        WebElement groupsAndPermissionsBreadcrumbLink = driver.findElement(By.xpath("//a[.='Groups & Permissions']"));
        groupsAndPermissionsBreadcrumbLink.click();
    }

    @When("user clicks Add Edit Users in Group")
    public void user_clicks_add_edit_users_in_group() {
        helper.jSClick(admin.menuOptions.get(1));
    }

    @When("user selects User names")
    public void user_selects_user_names() {
        admin.selectUserToAddField.click();
        helper.jSClick(admin.usersList.get(4));
        helper.jSClick(admin.usersList.get(5));
        helper.jSClick(admin.selectUserToAddField);
    }

    @When("user clicks Add Users")
    public void user_clicks_add_users() throws InterruptedException {
        helper.jSClick(admin.addUsersBtn);
        wait.hardWait(1000);
    }

    @When("user clicks on User name")
    public void user_clicks_on_user_name() throws InterruptedException {
        WaitHelper.waitForClickability(admin.groupUserTabs.getFirst(), 100);
        admin.groupUserTabs.get(0).click();
        wait.hardWait(1000);
    }

    @When("user is able to delete User from Group")
    public void user_is_able_to_delete_user_from_group() {
        helper.jSClick(admin.deleteUserFromGroupBtn);
    }

    @When("user closes modal")
    public void user_closes_modal() throws InterruptedException {
        helper.jSClick(admin.closeModal);
        wait.hardWait(2000);
    }

    @When("user clicks Rename Group Name")
    public void user_clicks_rename_group_name() {
        helper.jSClick(admin.menuOptions.get(2));
    }

    @When("user renames Group")
    public void user_renames_group() throws InterruptedException {
        helper.jSClick(admin.enterGroupNameField);
        admin.enterGroupNameField.sendKeys(Keys.CONTROL + "a");
        admin.enterGroupNameField.sendKeys(Keys.DELETE);
        admin.enterGroupNameField.sendKeys("Renamed Test");
        wait.hardWait(1000);
    }

    @When("user saves changes")
    public void user_saves_changes() throws InterruptedException {
        helper.jSClick(admin.renameGroupBtn);
        wait.hardWait(1000);
    }

    @When("user clicks Delete Group")
    public void user_clicks_delete_group() throws InterruptedException {
        helper.jSClick(admin.menuOptions.getLast());
        wait.hardWait(1000);
    }

    @Then("user is able to delete Group")
    public void user_is_able_to_delete_group() {
        helper.jSClick(admin.confirmDeleteGroup);
    }

    @Then("user verifies Add Building button is clickable")
    public void user_verifies_add_building_button_is_clickable() {
        Helper.isClickable(admin.addBuildingBtn);
    }

    @When("user clicks List Configurations")
    public void user_clicks_list_configurations() {
        helper.jSClick(admin.listConfigurationsLink);
    }

    @When("user clicks Size Class Management link")
    public void user_clicks_size_class_management_link() {
        helper.jSClick(admin.sizeClassManagementLink);
    }

    @Then("user verifies Size Class dashboard is displayed")
    public void user_verifies_size_class_dashboard_is_displayed() {
        assertEquals("Size Class", admin.pageHeader.getText());
        Helper.verifyElementDisplayed(admin.addSizeClass);
    }

    @When("user clicks Add Size Class")
    public void user_clicks_add_size_class() throws InterruptedException {
        WaitHelper.waitForClickability(admin.addSizeClass, 1000);
        admin.addSizeClass.click();
        wait.hardWait(1000);
    }

    @Then("user verifies a modal to add new Size Class is displayed")
    public void user_verifies_a_modal_to_add_new_size_class_is_displayed() {
        WaitHelper.waitForVisibility(admin.popUpModal, 1000);
        Helper.verifyElementDisplayed(admin.popUpModal);
        assertEquals("Add New Size Class",admin.modalHeader.getText());
    }

    @Then("user verifies a modal to edit Size Class is displayed")
    public void user_verifies_a_modal_to_edit_size_class_is_displayed() {
        WaitHelper.waitForVisibility(admin.popUpModal, 1000);
        Helper.verifyElementDisplayed(admin.popUpModal);
        assertEquals("Edit Size Class",admin.modalHeader.getText());
    }

    @And("user verifies Add Size Class button is disabled")
    public void user_verifies_add_size_class_button_is_disabled() {
        WaitHelper.waitForVisibility(admin.addSizeClassButton, 1000);
        Helper.verifyElementEnabled(admin.addSizeClassButton);
    }

    @And("user verifies Cancel button is enabled")
    public void user_verifies_cancel_button_is_enabled() {
        WaitHelper.waitForVisibility(admin.cancelButton, 1000);
        Helper.verifyElementEnabled(admin.cancelButton);
    }

    @When("user enters full name")
    public void user_enters_full_name() throws InterruptedException {
        admin.fullname.click();
        admin.fullname.sendKeys("Microfilm Boxes");
        wait.hardWait(100);
    }

    @When("user updates full name")
    public void user_updates_full_name() throws InterruptedException {
        admin.fullname.click();
        admin.fullname.sendKeys(Keys.CONTROL + "a");
        admin.fullname.sendKeys(Keys.DELETE);
        admin.fullname.sendKeys("Microfilm Boxes - 12 - REEL");
        wait.hardWait(100);
    }

    @And("user enters short name")
    public void user_enters_short_name() throws InterruptedException {
        admin.shortname.click();
        admin.shortname.sendKeys("MF");
        wait.hardWait(100);
    }

    @And("user enters witdth")
    public void user_enters_witdth() throws InterruptedException {
        admin.width.click();
        admin.width.sendKeys("40");
        wait.hardWait(100);
    }

    @And("user enters depth")
    public void user_enters_depth() throws InterruptedException {
        admin.depth.click();
        admin.depth.sendKeys("27");
        wait.hardWait(100);
    }

    @And("user enters height")
    public void user_enters_height() throws InterruptedException {
        admin.height.click();
        admin.height.sendKeys("15.70");
        wait.hardWait(100);
    }

    @And("user selects owner")
    public void user_selects_owner() {
        admin.owner.click();
        admin.ownerFieldOptions.get(1).click();
        admin.ownerFieldOptions.get(3).click();
        admin.ownerFieldOptions.get(4).click();
    }

    @Then("user verifies Add Size Class button is enabled")
    public void user_verifies_add_size_class_button_is_enabled() {
        WaitHelper.waitForVisibility(admin.addSizeClassButton, 1000);
        Helper.verifyElementEnabled(admin.addSizeClassButton);
    }

    @And("user verifies that Size Class is created")
    public void user_verifies_that_size_class_is_created() {
        helper.scrollIntoView(admin.sizeClassList.get(admin.sizeClassList.size()-1));
        String addedSizeClass = "Microfilm Boxes";
        assertTrue(admin.sizeClassList.get(admin.sizeClassList.size()-1).getText().contains(addedSizeClass));
    }

    @When("user clicks Edit Size Class")
    public void user_clicks_edit_size_class() throws InterruptedException {
        helper.jSClick(admin.menuOptions.get(0));
        wait.hardWait(1000);
    }

    @When("user clicks Delete Size Class")
    public void user_clicks_delete_size_class() throws InterruptedException {
        helper.jSClick(admin.menuOptions.getLast());
        wait.hardWait(1000);
    }

    @When("user clicks Add Size Class button")
    public void user_clicks_add_size_class_button() {
        admin.addSizeClassButton.click();
    }

    @When("user clicks Update Size Class button")
    public void user_clicks_update_size_class_button() {
        admin.updateSizeClassButton.click();
    }

    @Then("user verifies delete size class warning message")
    public void user_verifies_delete_size_class_warning_message() {
        WaitHelper.waitForVisibility(admin.warningMsg, 1000);
        assertTrue(admin.warningMsg.getText().contains("Are you sure you want to delete"));
    }

    @And("user confirms delete size class action")
    public void user_confirms_delete_size_class_action() {
        helper.jSClick(admin.confirmDeleteSizeClass);
    }

    @When("user enters existing in the system full name")
    public void user_enters_existing_in_the_system_full_name()  {
        Helper.clickWithJS(admin.fullname);
        admin.fullname.sendKeys("Record Storage Box");
    }

    @When("user enters existing in the system short name")
    public void user_enters_existing_in_the_system_short_name()  {
        Helper.clickWithJS(admin.shortname);
        admin.shortname.sendKeys("RS");
    }

    @And("user verifies that Size Class with existing full name is not created")
    public void user_verifies_that_size_class_with_existing_full_name_is_not_created()  {
        WaitHelper.fluentWait(admin.sizeClassList.get(admin.sizeClassList.size()-1),1000);
        assertFalse(admin.sizeClassList.get(admin.sizeClassList.size()-1).getText().contains("Record Storage Box"));
    }

    @And("user verifies that Size Class with existing short name is not created")
    public void user_verifies_that_size_class_with_existing_short_name_is_not_created()  {
        WaitHelper.fluentWait(admin.sizeClassList.get(admin.sizeClassList.size()-1),1000);
        assertFalse(admin.sizeClassList.get(admin.sizeClassList.size()-1).getText().contains("RS"));
    }
}
