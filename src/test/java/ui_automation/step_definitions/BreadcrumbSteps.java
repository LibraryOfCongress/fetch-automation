package ui_automation.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui_automation.pages.HomePage;
import ui_automation.pages.VerificationPage;
import ui_automation.utilities.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreadcrumbSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    VerificationPage verification = new VerificationPage();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();
    HomePage home = new HomePage();
    Actions actions = new Actions(driver);
    AlertHelper alert = new AlertHelper();
    GenericHelper genHelp = new GenericHelper();

    public static final Logger oLog = LogManager.getLogger(AdminSteps.class);


    @Then("user should see the corresponding breadcrumbs")
    public void user_should_see_the_corresponding_breadcrumbs(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<WebElement> breadcrumbs = driver.findElements(By.className("breadcrumb-items"));
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedField = map.get("breadcrumb");
            String actualField = breadcrumbs.get(i).getText();
            Assert.assertEquals(true, actualField.contains(expectedField));
            i++;
        }
        oLog.info("I verified Breadcrumb link names");
    }


    @Then("user selects a Verification Job")
    public void user_selects_a_Verification_Job() {
        helper.jSClick(verification.verificationJobs.get(0));
    }

    @When("user clicks on Verification breadcrumb link")
    public void user_clicks_on_Verification_breadcrumb_link() {
        WebElement verificationBreadcrumbLink = driver.findElement(By.xpath("//a[.='Verification']"));
        verificationBreadcrumbLink.click();
        oLog.info("I clicked Verification Breadcrumb link");
    }

    @Then("user should navigate to Verification page")
    public void user_should_navigate_to_Verification_page() {
        assertTrue(driver.getCurrentUrl().equals("https://test.fetch.loctest.gov/verification"));
        oLog.info("I navigated to Verification page");
    }


    @When("user clicks on Home breadcrumb link")
    public void user_clicks_on_Home_breadcrumb_link() throws InterruptedException {
        WebElement homeBreadcrumbLink = driver.findElement(By.xpath("//*[contains(text(),'Home')]"));
        homeBreadcrumbLink.click();
    }


    @When("user clicks Later on the banner")
    public void user_clicks_Later_on_the_banner() {
        home.banner.click();
    }

    @Then("user should navigate to the Home page")
    public void user_should_navigate_to_the_Home_page() {
        assertTrue(driver.getCurrentUrl().equals("https://test.fetch.loctest.gov/"));
        oLog.info("I navigated to Home page");
    }


    @When("user clicks on Admin breadcrumb link")
    public void user_clicks_on_Admin_breadcrumb_link() {
        WebElement adminBreadcrumbLink = driver.findElement(By.xpath("//a[.='Admin']"));
        adminBreadcrumbLink.click();
        oLog.info("I clicked Admin Breadcrumb link");
    }

    @Then("user should navigate to the Admin page")
    public void user_should_navigate_to_the_Admin_page() {
        assertTrue(driver.getCurrentUrl().equals("https://test.fetch.loctest.gov/admin"));
        oLog.info("I navigated to Admin page");
    }

}
