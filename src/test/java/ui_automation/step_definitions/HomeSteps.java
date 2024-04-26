package ui_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
    AlertHelper alert = new AlertHelper();
    WaitHelper wait = new WaitHelper();

    public static final Logger oLog = LogManager.getLogger(HomeSteps.class);


    @Given("user navigates to FETCH Homepage")
    public void user_navigates_to_FETCH_Homepage()  {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "fetchURL"));
        oLog.info("I navigated to FETCH webapp");
    }

    @When("user looks at the header")
    public void user_looks_at_the_header() {
        home.header.getText();
    }

    @Then("the logo is displayed")
    public void the_logo_is_displayed() {
        String expectedLogo = "FETCH LOGO";
        String actualLogo = home.logo.getText();
        home.logo.isDisplayed();
        Assert.assertEquals("Logo verification failed",
                expectedLogo, actualLogo);
    }

    @Then("the hamburger menu is clickable")
    public void hamburger_menu_is_clickable() {
        helper.isClickable(home.hamburgerMenu);
    }

    @Then("the search bar is visible")
    public void the_search_bar_is_visible() {
        helper.verifyElementDisplayed(home.searchBar);
    }

    @Then("the login button is visible")
    public void login_button_is_visible() {
        helper.verifyElementDisplayed(home.loginButton);
    }

    @Then("Scanned Bar Codes field is visible")
    public void scanned_Bar_Codes_field_is_visible() {
        helper.verifyElementDisplayed(home.barCodeField);
    }

    @Then("the side navigation menu is visible")
    public void the_side_navigation_menu_is_visible() {
        helper.verifyElementDisplayed(home.sideNavigationMenu);
    }

    @Then("user verifies side navigation tabs on Homepage")
    public void user_verifies_side_navigation_tabs_on_Homepage(DataTable dataTable) {

        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 1;

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
        oLog.info("I navigated to Accession Jobs Page");
    }


    @Then("verify that Accession navigation link on side menu is highlighted")
    public void verify_that_Accession_navigation_link_on_side_menu_is_highlighted() {
        assertEquals("Link not highlighted", "Accession", home.highlightedLink.getText());
    }

    @When("user clicks Verification on side navigation menu")
    public void user_clicks_Verification_on_side_navigation_menu() throws InterruptedException {
        home.verificationLink.click();
        Thread.sleep(1000);
        oLog.info("I navigated to Verification Page");
    }

    @Then("verify that Verification navigation link on side menu is highlighted")
    public void verify_that_Verification_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Verification"));
    }

    @When("user clicks Shelving on side navigation menu")
    public void user_clicks_Shelving_on_side_navigation_menu() throws InterruptedException {
        home.shelvingLink.click();
        Thread.sleep(1000);
        oLog.info("I navigated to Shelving Page");
    }

    @Then("verify that Shelving navigation link on side menu is highlighted")
    public void verify_that_Shelving_navigation_link_on_side_menu_is_highlighted() {
        assertTrue(home.highlightedLink.getText().contains("Shelving"));
    }

    @When("user clicks Admin on side navigation menu")
    public void user_clicks_Admin_on_side_navigation_menu() throws InterruptedException {
        helper.clickWithJS(home.adminLink);
        Thread.sleep(1000);
        oLog.info("I navigated to Admin Page");
    }

    @Then("verify that Admin navigation link on side menu is highlighted")
    public void verify_that_Admin_navigation_link_on_side_menu_is_highlighted() {
        assertEquals("Link not highlighted", "Admin", home.highlightedLink.getText());
    }


    @When("user clicks Login icon on dashboard")
    public void user_clicks_Login_icon_on_dashboard() {
        home.loginIcon.click();
        oLog.info("I clicked Login icon on dashboard");
    }

    @When("user enters {string} username")
    public void user_enters_username(String username) {
        home.usernameField.sendKeys(username);
        oLog.info("I entered username");
    }

    @When("user enters {string} password")
    public void user_enters_password(String password) {
        home.passwordField.sendKeys(password);
        oLog.info("I entered password");
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        wait.waitForClickability(home.login, 20);
        home.login.click();
        oLog.info("I clicked login button");
    }

    @Then("user should be able to verify account name on user dashboard")
    public void user_should_be_able_to_verify_account_name_on_user_dashboard() {
        home.loginIcon.click();
        String actualAccountName = home.userName.getText();
        Assert.assertEquals("Account name is not verified!", "Admin User", actualAccountName);
        oLog.info("I verified account name");
    }

    @Then("user validates {string} error message")
    public void user_validates_error_message(String expectedErrorMessage) {
        String actualErrorMessage = home.errorMessage.getText();
        Assert.assertEquals("Error message validation failed!", expectedErrorMessage, actualErrorMessage);
        oLog.info("I validated error message");
    }

    @When("user clicks logout button")
    public void user_clicks_logout_button() {
        wait.waitForClickability(home.logout, 20);
        home.logout.click();
        oLog.info("I clicked logout button");
    }


}
