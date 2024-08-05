package ui_automation.step_definitions;


import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ui_automation.utilities.BrowserFactory;
import ui_automation.utilities.Driver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public WebDriver driver = null;

    //before scenario
    @Before
    public void setUp() {
        driver = BrowserFactory.createInstance();
        Driver.getInstance().setDriver(driver);
        driver = Driver.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    //after scenario
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        Driver.getInstance().removeDriver();
    }
}