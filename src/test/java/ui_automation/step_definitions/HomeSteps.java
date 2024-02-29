package ui_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
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

    public static final Logger oLog = LogManager.getLogger(HomeSteps.class);


    @Given("user navigates to FETCH Homepage")
    public void user_navigates_to_FETCH_Homepage() throws InterruptedException {
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

            //System.out.println(map);
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
        home.adminLink.click();
        Thread.sleep(1000);
        oLog.info("I navigated to Admin Page");
    }

    @Then("verify that Admin navigation link on side menu is highlighted")
    public void verify_that_Admin_navigation_link_on_side_menu_is_highlighted() {
        assertEquals("Link not highlighted", "Admin", home.highlightedLink.getText());
    }


}
