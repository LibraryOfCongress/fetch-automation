package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class AlertPage {

    WebDriver driver;

    public AlertPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[.='Show Generic Alert'])[1]")
    public WebElement genericAlert;

    @FindBy(css = ".alert-notification")
    public WebElement alertMsg;

    @FindBy(css = ".q-banner__actions")
    public WebElement cancelGenAlert;

    @FindBy(xpath = "(//span[.='Show Persistent Alert'])[1]")
    public WebElement persistentAlert;

    @FindBy(css = "[class='text-body1']")
    public WebElement audioAlertMsg;

    @FindBy(xpath = "(//span[.='Cancel'])[1]")
    public WebElement cancelPersistentAlert;

    @FindBy(css = "p.text-body1")
    public WebElement toastMsg;




}
