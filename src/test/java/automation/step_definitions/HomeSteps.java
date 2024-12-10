package automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import automation.pages.HomePage;
import automation.utilities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    HomePage home = new HomePage();
    Helper helper = new Helper();
    static WaitHelper wait = new WaitHelper();


    @Given("user navigates to FETCH Homepage")
    public void user_navigates_to_FETCH_Homepage() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("config.properties", "fetchURL"));
    }

    @When("user looks at the header")
    public void user_looks_at_the_header() {
        home.header.getText();
    }

    @Then("the hamburger menu is clickable")
    public void hamburger_menu_is_clickable() {
        Helper.isClickable(home.hamburgerMenu);
    }

    @Then("the search bar is visible")
    public void the_search_bar_is_visible() {
        Helper.verifyElementDisplayed(home.searchBar);
    }

    @Then("the login button is clickable")
    public void login_button_is_clickable() {
        Helper.isClickable(home.loginButton);
        home.loginButton.click();
        home.loginButton.click();
    }

    @Then("Scanned Bar Codes field is visible")
    public void scanned_bar_codes_field_is_visible() {
        Helper.verifyElementDisplayed(home.barCodeField);
    }

    @Then("user verifies side navigation tabs on Homepage")
    public void user_verifies_side_navigation_tabs_on_homepage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedTabname = map.get("tabname");
            String actualTabname = home.allNavigationTabs.get(i).getText();
            Assert.assertEquals("Tabname verification failed",
                    expectedTabname, actualTabname);
            i++;
        }
    }

    @When("user clicks Accession on side navigation menu")
    public void user_clicks_accession_on_side_navigation_menu() {
        helper.jSClick(home.accessionLink);
    }

    @Then("verify that Accession navigation link on side menu is highlighted")
    public void verify_that_accession_navigation_link_on_side_menu_is_highlighted() {
        assertEquals("Link not highlighted", "Accession", home.highlightedLink.getText());
    }

    @When("user clicks Verification on side navigation menu")
    public void user_clicks_verification_on_side_navigation_menu() throws InterruptedException {
        home.verificationLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Verification navigation link on side menu is highlighted")
    public void verify_that_verification_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Verification"));
    }

    @When("user clicks Shelving on side navigation menu")
    public void user_clicks_shelving_on_side_navigation_menu() throws InterruptedException {
        Helper.clickWithJS(home.shelvingLink);
        wait.hardWait(1000);
    }

    @Then("verify that Shelving navigation link on side menu is highlighted")
    public void verify_that_shelving_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Shelving"));
    }

    @When("user clicks Request on side navigation menu")
    public void user_clicks_request_on_side_navigation_menu() throws InterruptedException {
        home.requestLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Request navigation link on side menu is highlighted")
    public void verify_that_request_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Request"));
    }

    @When("user clicks Pick List on side navigation menu")
    public void user_clicks_pick_List_on_side_navigation_menu() throws InterruptedException {
        home.picklistLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Pick List navigation link on side menu is highlighted")
    public void verify_that_pick_List_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Pick List"));
    }

    @When("user clicks Withdrawal on side navigation menu")
    public void user_clicks_withdrawal_on_side_navigation_menu() throws InterruptedException {
        home.withdrawalLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Withdrawal navigation link on side menu is highlighted")
    public void verify_that_withdrawal_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Withdrawal"));
    }

    @When("user clicks Reports on side navigation menu")
    public void user_clicks_reports_on_side_navigation_menu() throws InterruptedException {
        home.reportsLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Reports navigation link on side menu is highlighted")
    public void verify_that_reports_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Reports"));
    }

    @When("user clicks Refile on side navigation menu")
    public void user_clicks_refile_on_side_navigation_menu() throws InterruptedException {
        home.refileLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Refile navigation link on side menu is highlighted")
    public void verify_that_refile_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Refile"));
    }

    @When("user clicks Admin on side navigation menu")
    public void user_clicks_admin_on_side_navigation_menu() throws InterruptedException {
        Helper.clickWithJS(home.adminLink);
        Thread.sleep(1000);
    }

    @When("user logs in as a tester1")
    public void user_logs_in_as_a_tester1() throws InterruptedException {
        home.loginButton.click();
        wait.hardWait(1000);
        home.usernameField.sendKeys("tester1@loctest.gov");
        WaitHelper.waitForClickability(home.login, 20);
        home.login.click();
        wait.hardWait(2000);
    }

    @And("user logs in as an admin")
    public void user_logs_in_as_an_admin() throws InterruptedException {
        home.loginButton.click();
        wait.hardWait(1000);
        home.usernameField.sendKeys("admin@loctest.gov");
        WaitHelper.waitForClickability(home.login, 20);
        home.login.click();
        wait.hardWait(2000);
    }


    @When("user logs in with invalid email")
    public void user_logs_in_with_invalid_email() throws InterruptedException {
        home.loginButton.click();
        wait.hardWait(1000);
        home.usernameField.sendKeys("test@loctest.gov");
        WaitHelper.waitForClickability(home.login, 20);
        home.login.click();
        wait.hardWait(2000);
    }

    @Then("user should be able to verify account name on user dashboard")
    public void user_should_be_able_to_verify_account_name_on_user_dashboard() {
        WebElement userIcon = driver.findElement(By.cssSelector("[aria-label='UserMenu']"));
        userIcon.click();
        WaitHelper.waitForClickability(home.user,1000);
        String actualAccountName = home.user.getText();
        Assert.assertEquals("Account name is not verified!", "Tester One", actualAccountName);
    }

    @Then("user validates {string} error message")
    public void user_validates_error_message(String expectedErrorMessage) {
        String actualErrorMessage = home.errorMessage.getText();
        Assert.assertEquals("Error message validation failed!", expectedErrorMessage, actualErrorMessage);
    }

    @When("user clicks logout button")
    public void user_clicks_logout_button() {
        WaitHelper.waitForClickability(home.logout, 20);
        home.logout.click();
    }

    @Then("login button is not enabled")
    public void login_button_is_not_enabled() {
        Assert.assertFalse(home.login.isEnabled());
    }

    @Then("user verifies the scanned barcode is displayed")
    public void user_verifies_the_scanned_barcode_is_displayed() {
        assertEquals("Scanned Barcode is not displayed!", home.scannedBarcodes.get(home.scannedBarcodes.size() - 1).getText(), Long.toString(AccessionSteps.scanned1));
    }

    @When("user switches on Toggle Barcode Scan")
    public void user_switches_on_toggle_barcode_scan()  {
        if(home.userIcon.isDisplayed()) {
            WaitHelper.waitForClickability(home.userIcon, 3000);
            home.userIcon.click();
            WaitHelper.waitForClickability(home.toggleScan, 3000);
            home.toggleScan.click();
            home.userIcon.click();
        }if(!home.userIcon.isDisplayed()) {
           driver.navigate().refresh();
            WaitHelper.waitForClickability(home.userIcon,3000);
            home.userIcon.click();
            WaitHelper.waitForClickability(home.toggleScan,3000);
            home.toggleScan.click();
            home.userIcon.click();
        }
    }

    @When("user swithes off Toggle Barcode Scan")
    public void user_switches_off_barcode_scan() {
        WaitHelper.waitForClickability(home.disableScan,3000);
        helper.jSClick(home.disableScan);
    }

    @Then("verify barcode scanning is enabled")
    public void verify_barcode_scanning_is_enabled() {
        home.scanningEnabledAlert.click();
        Assert.assertEquals("Barcode scanning is enabled.", home.scanningEnabledAlert.getText());
    }

    @Then("user verifies today date")
    public void user_verifies_today_date() {
        Helper.todayDate();
    }

    @Then("user verifies columns displayed")
    public void user_verifies_columns_displayed(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedColumn = map.get("column");
            wait.hardWait(1000);
            String actualColumn = home.tableColumns.get(i).getText();
            Assert.assertEquals("Column name verification failed",
                    expectedColumn, actualColumn);
            i++;
        }
    }

    @When("user clicks filter icon")
    public void userClicksFilterIcon() {
    WaitHelper.waitForVisibility(home.filterIcon,2000);
    home.filterIcon.click();
    }

    @Then("user verifies filter options")
    public void user_verifies_filter_options(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedOption = map.get("option");
            wait.hardWait(1000);
            String actualOption = home.filterOptions.get(i).getText();
            Assert.assertEquals("Filter options verification failed",
                    expectedOption, actualOption);
            i++;
        }
    }

}
