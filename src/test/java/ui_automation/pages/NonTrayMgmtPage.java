package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class NonTrayMgmtPage {


    WebDriver driver;

    public NonTrayMgmtPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".col > .text-h4")
    public WebElement shelfHeader;

    @FindBy(css = "[class='non-tray-details-label text-h6']")
    public List<WebElement> nonTrayLabels;

    @FindBy(css = ".q-table th")
    public List<WebElement> itemsLabels;

    @FindBy(css = ".item-details label")
    public List<WebElement> overlayItemsLabels;





}
