package ui_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.HomePage;
import ui_automation.utilities.*;

import java.util.List;
import java.util.Map;

public class HomePageSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    HomePage homePage = new HomePage();
    Helper helper = new Helper();

    public static final Logger oLog = LogManager.getLogger(HomePageSteps.class);


    @Given("user navigates to FETCH Homepage")
    public void user_navigates_to_FETCH_Homepage() throws InterruptedException {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties","fetchURL"));
        oLog.info("I navigated to FETCH webapp");
    }

    @When("user looks at the header")
    public void user_looks_at_the_header() {
    homePage.header.getText();
    }

    @Then("the logo is displayed")
    public void the_logo_is_displayed() {
    String expectedLogo = "FETCH LOGO";
    String actualLogo = homePage.logo.getText();
    homePage.logo.isDisplayed();
        Assert.assertEquals("Logo verification failed",
                expectedLogo, actualLogo);
    }

    @Then("the hamburger menu is clickable")
    public void hamburger_menu_is_clickable() {helper.isClickable(homePage.hamburgerMenu); }

    @Then("the search bar is visible")
    public void the_search_bar_is_visible() {helper.verifyElementDisplayed(homePage.searchBar);}

    @Then("the login button is visible")
    public void login_button_is_visible() {
        helper.verifyElementDisplayed(homePage.loginButton);
    }

    @Then("Scanned Bar Codes field is visible")
    public void scanned_Bar_Codes_field_is_visible() {
        helper.verifyElementDisplayed(homePage.barCodeField);
    }

    @Then("the side navigation menu is visible")
    public void the_side_navigation_menu_is_visible() {
        helper.verifyElementDisplayed(homePage.sideNavigationMenu);
    }

    @Then("user verifies side navigation tabs on Homepage")
    public void user_verifies_side_navigation_tabs_on_Homepage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 1;

        for(Map<String, String> map: maps) {
            String expectedTabname = map.get("tabname");
            String actualTabname = homePage.allNavigationTabs.get(i).getText();
            Assert.assertEquals("Tabname verification failed",
                    expectedTabname, actualTabname);
            i++;

            //System.out.println(map);
        }






    }

}
