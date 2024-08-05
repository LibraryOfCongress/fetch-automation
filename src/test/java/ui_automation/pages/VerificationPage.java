package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class VerificationPage {

    WebDriver driver;
    public VerificationPage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Trayed']")
    public List<WebElement> trayedVerificationJobs;

    @FindBy(css = "[class='verification-card-barcode text-h4']")
    public List<WebElement> verificationJobsList;

    @FindBy(xpath = "//*[text()='Non-Trayed']")
    public WebElement nonTrayedVerificationJob;

    @FindBy(xpath = "//div[.='Trayed']")
    public List<WebElement> trayedJobList;

    @FindBy(xpath = "//div[.='Non-Trayed']")
    public List<WebElement> nonTrayedJobList;

    @FindBy(css = "[class$='q-mb-md-xl q-mb-lg-none']")
    public WebElement scanTrayBox;

    @FindBy(xpath = "//*[@class='q-table']/tbody/tr")
    public List<WebElement> scannedVerificationItems;

    @FindBy(css = "[class='q-table'] td [role='checkbox']")
    public List<WebElement> scannedItemsCheckbox;

    @FindBy(xpath = "(//span[.='Next Tray']/..)[1]")
    public WebElement nextTrayBtn;

    @FindBy(css = "button[class$='verification-next-tray-action full-width']")
    public List<WebElement> newTrays;

    @FindBy(css = "button[class$='q-btn--no-uppercase btn-no-wrap text-body1 q-mr-sm-md']")
    public WebElement enterBarcodeBtn;

    @FindBy(css = "[placeholder='Please Enter Barcode']")
    public WebElement enterBarcodeField;

    @FindBy(xpath = "(//span[.='submit']/..)[1]")
    public WebElement submitBtn;

    @FindBy(xpath = "//span[.='Complete Job'] /../..")
    public WebElement completeJob;

    @FindBy(xpath = "(//i[.='more_vert'])[1]")
    public WebElement threeDot;

    @FindBy(xpath = "(//input[@class='q-field__input q-placeholder col'])[1]")
    public WebElement editOwnerField;

    @FindBy(xpath = "(//input[@class='q-field__input q-placeholder col'])[2]")
    public WebElement editContainerSizeField;

    @FindBy(xpath = "(//input[@class='q-field__input q-placeholder col'])[3]")
    public WebElement editMediaTypeField;

    @FindBy(css = "div[role='option']")
    public List<WebElement> editFieldOptions;

    @FindBy(css = "[class='q-td text-left'] span")
    public List<WebElement> scannedItemList;

    @FindBy(css = "[class='q-td text-right']")
    public List<WebElement> verifiedCheckMark;

    @FindBy(xpath = "//button[.='Add New Item']")
    public WebElement addNewItem;

}
