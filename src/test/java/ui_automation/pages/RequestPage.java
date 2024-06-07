package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class RequestPage {


    WebDriver driver;

    public RequestPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[.='Create']")
    public WebElement create;

    @FindBy(css = "[class='q-list'] div.q-item__label")
    public List<WebElement> dropdownOptions;

    @FindBy(css = "[class='q-card popup-modal']")
    public WebElement modal;

    @FindBy(css = "[placeholder='Enter or Scan Item Barcode']")
    public WebElement itemBarcodeField;

    @FindBy(css = "[placeholder='Enter Requestor Name']")
    public WebElement requestorNameField;

    @FindBy(css = "[placeholder='Select Request Type']")
    public WebElement requestTypeField;

    @FindBy(css = "[role='option']")
    public List<WebElement> options;

    @FindBy(css = "[placeholder='Select Delivery Location']")
    public WebElement deliveryLocationField;

    @FindBy(css = "[class='request-item-details']")
    public List<WebElement> requestItemDetails;

    @FindBy(css = "[class='barcode text-h4 text-center']")
    public WebElement actualBarcode;
}
