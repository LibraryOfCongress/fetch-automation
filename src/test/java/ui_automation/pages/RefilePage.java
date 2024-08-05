package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class RefilePage {

    WebDriver driver;

    public RefilePage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".q-table")
    public WebElement table;

    @FindBy(css = ".q-table th")
    public List<WebElement> refileTableColumnNames;

    @FindBy(xpath = "//button[.='Refile Queue']")
    public WebElement refileQueueBtn;

    @FindBy(xpath = "//button[.='Refile Job']")
    public WebElement refileJobBtn;

    @FindBy(css = "[class='q-list'] div.q-item__label")
    public List<WebElement> refileDropdownOptions;



}
