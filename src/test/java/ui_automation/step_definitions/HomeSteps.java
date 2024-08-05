package ui_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.HomePage;
import ui_automation.utilities.*;

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
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "fetchURL"));
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
    public void scanned_Bar_Codes_field_is_visible() {
        Helper.verifyElementDisplayed(home.barCodeField);
    }

    @Then("user verifies side navigation tabs on Homepage")
    public void user_verifies_side_navigation_tabs_on_Homepage(DataTable dataTable) {
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
    public void user_clicks_Accession_on_side_navigation_menu() {
        helper.jSClick(home.accessionLink);
    }

    @Then("verify that Accession navigation link on side menu is highlighted")
    public void verify_that_Accession_navigation_link_on_side_menu_is_highlighted() {
        assertEquals("Link not highlighted", "Accession", home.highlightedLink.getText());
    }

    @When("user clicks Verification on side navigation menu")
    public void user_clicks_Verification_on_side_navigation_menu() throws InterruptedException {
        home.verificationLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Verification navigation link on side menu is highlighted")
    public void verify_that_Verification_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Verification"));
    }

    @When("user clicks Shelving on side navigation menu")
    public void user_clicks_Shelving_on_side_navigation_menu() throws InterruptedException {
        home.shelvingLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Shelving navigation link on side menu is highlighted")
    public void verify_that_Shelving_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Shelving"));
    }

    @When("user clicks Request on side navigation menu")
    public void user_clicks_Request_on_side_navigation_menu() throws InterruptedException {
        home.requestLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Request navigation link on side menu is highlighted")
    public void verify_that_Request_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Request"));
    }

    @When("user clicks Pick List on side navigation menu")
    public void user_clicks_Pick_List_on_side_navigation_menu() throws InterruptedException {
        home.picklistLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Pick List navigation link on side menu is highlighted")
    public void verify_that_Pick_List_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Pick List"));
    }

    @When("user clicks Withdrawal on side navigation menu")
    public void user_clicks_Withdrawal_on_side_navigation_menu() throws InterruptedException {
        home.withdrawalLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Withdrawal navigation link on side menu is highlighted")
    public void verify_that_Withdrawal_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Withdrawal"));
    }

    @When("user clicks Reports on side navigation menu")
    public void user_clicks_Reports_on_side_navigation_menu() throws InterruptedException {
        home.reportsLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Reports navigation link on side menu is highlighted")
    public void verify_that_Reports_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Reports"));
    }

    @When("user clicks Refile on side navigation menu")
    public void user_clicks_Refile_on_side_navigation_menu() throws InterruptedException {
        home.refileLink.click();
        wait.hardWait(1000);
    }

    @Then("verify that Refile navigation link on side menu is highlighted")
    public void verify_that_Refile_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Refile"));
    }

    @When("user clicks Admin on side navigation menu")
    public void user_clicks_Admin_on_side_navigation_menu() throws InterruptedException {
        Helper.clickWithJS(home.adminLink);
        Thread.sleep(1000);
    }

    @When("user logs in as a tester")
    public void user_logs_in_as_a_tester() throws InterruptedException {
        home.loginButton.click();
        wait.hardWait(1000);
        home.usernameField.sendKeys("tester1@loctest.gov");
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
        wait.waitForClickability(home.user,1000);
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
    public void user_switches_on_Toggle_Barcode_Scan() throws InterruptedException {
        WebElement userIcon = driver.findElement(By.cssSelector("[aria-label='UserMenu']"));
        userIcon.click();
        wait.hardWait(2000);
        home.toggleScan.click();
        wait.hardWait(1000);
        userIcon.click();
    }

    @When("user disables Toggle Barcode Scan")
    public void user_disables_Toggle_Barcode_Scan() throws InterruptedException {
        helper.jSClick(home.disableScan);
        wait.hardWait(2000);
    }

    @Then("verify barcode scanning is enabled")
    public void verify_barcode_scanning_is_Enabled() {
        home.scanningEnabledAlert.click();
        Assert.assertEquals("Barcode scanning is enabled.", home.scanningEnabledAlert.getText());
    }


}
