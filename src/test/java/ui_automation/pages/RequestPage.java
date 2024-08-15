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

    @FindBy(css = "[placeholder='Enter External Request Id']")
    public WebElement requestIDField;

    @FindBy(css = "[placeholder='Enter Requestor Name']")
    public WebElement requestorNameField;

    @FindBy(css = "[placeholder='Select Request Type']")
    public WebElement requestTypeField;

    @FindBy(css = "[placeholder='Select Priority']")
    public WebElement priorityField;

    @FindBy(css = "[role='option']")
    public List<WebElement> options;

    @FindBy(css = "[placeholder='Select Delivery Location']")
    public WebElement deliveryLocationField;

    @FindBy(css = "[class='request-item-details']")
    public List<WebElement> requestItemDetails;

    @FindBy(css = "[class='barcode text-h4 text-center']")
    public WebElement actualBarcode;

    @FindBy(css = "[role='checkbox']")
    public List<WebElement> checkboxesRequests;

    @FindBy(xpath = "//span[contains(text(),'Create Pick List')]")
    public WebElement createPickListBtn;

    @FindBy(xpath = "//span[contains(text(),'Add To Pick List')]")
    public WebElement addToPickListBtn;

    @FindBy(css = "[id='alertText']")
    public WebElement alertText;

    @FindBy(css = "[id='alertText'] a")
    public WebElement createdPickListLink;

    @FindBy(css = "[placeholder='Select Pick List Job']")
    public WebElement selectPickListJobDropdown;

    @FindBy(css = "[role='option']")
    public List<WebElement> dropdownList;

    @FindBy(xpath = "//button[.='Submit']")
    public WebElement submitRequest;

    @FindBy(css = "[class='q-table'] tbody td:nth-child(3)")
    public WebElement firstItemBarcode;


}
