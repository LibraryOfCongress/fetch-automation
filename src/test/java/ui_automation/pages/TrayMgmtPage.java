package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class TrayMgmtPage {

    WebDriver driver;

    public TrayMgmtPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".col > .text-h4")
    public WebElement trayHeader;

    @FindBy(css = "[class='tray-details-label text-h6']")
    public List<WebElement> trayLabels;

    @FindBy(css = "[class='barcode text-h4 q-py-xs-md']")
    public WebElement trayBarcodeText;

    @FindBy(css = ".q-field__native > span")
    public WebElement filterColumns;

    @FindBy(css = ".q-virtual-scroll__content .q-item__label")
    public List<WebElement> filterOptions;

    @FindBy(css = ".q-table th")
    public List<WebElement> itemsLabels;
}
