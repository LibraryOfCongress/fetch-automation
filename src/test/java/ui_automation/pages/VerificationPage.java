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

    @FindBy(css = ".verification-container div.q-card")
    public List<WebElement> verificationJobs;

    @FindBy(css = "[class='barcode text-h4 q-mb-md-xl q-mb-lg-none']")
    public WebElement scanTrayBox;

    @FindBy(css = "[class='q-table'] tbody tr")
    public List<WebElement> scannedVerificationItems;

    @FindBy(css = "[class='q-table'] td [role='checkbox']")
    public List<WebElement> scannedItemsCheckbox;

    @FindBy(xpath = "//span[.='Next Tray']/..")
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

}
