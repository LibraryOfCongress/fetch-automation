package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class PickListPage {

    WebDriver driver;

    public PickListPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".q-table th")
    public List<WebElement> picklistTableColumnNames;

    @FindBy(css = ".q-table tr")
    public List<WebElement> picklistTableRows;

    @FindBy(css = "[class='q-td text-left']")
    public List<WebElement> tabs;

    @FindBy(css = "[class='info-display-number-box text-h4']")
    public WebElement picklistJobNumber;

    @FindBy(xpath = "(//button[.='more_vert'])[1]")
    public WebElement threeDotNextToPickListJob;

    @FindBy(css = "[class='q-list more-menu-list']")
    public WebElement editButton;

    @FindBy(css = ".q-table th")
    public List<WebElement> itemsInJobTableColumnNames;

    @FindBy(xpath = "//td[.='Created']")
    public WebElement createdJob;

    @FindBy(xpath = "//td[.='Running']")
    public WebElement runningJob;
}
