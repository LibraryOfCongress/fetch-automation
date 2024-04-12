package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class AccessionPage {

    WebDriver driver;

    public AccessionPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "button[class$='q-focusable q-hoverable accession-btn text-h4']")
    public WebElement startAccessionBtn;

    @FindBy(css = ".column > :nth-child(2) > .q-btn__content")
    public WebElement trayedAccession;

    @FindBy(css = ".q-mb-md > .q-btn__content")
    public WebElement nonTrayAccession;

    @FindBy(css = "[placeholder='Select Owner']")
    public WebElement ownerField;

    @FindBy(css = "[role='option']:nth-child(1)")
    public WebElement johnDoe;

    @FindBy(css = ".q-virtual-scroll__content>div:nth-child(3)")
    public WebElement sanders;

    @FindBy(css = "[placeholder='Select Size Class']")
    public WebElement containerSizeField;

    @FindBy(css = ".q-virtual-scroll__content .q-item__label")
    public List<WebElement> ownerFieldOptions;

    @FindBy(css = "[placeholder='Select Media Type']")
    public WebElement mediaTypeField;

    @FindBy(className = "form-group-label")
    public List<WebElement> newAccessionFields;

    @FindBy(css = ".q-pb-none > .q-btn--rectangle > .q-btn__content")
    public WebElement backBtn;

    @FindBy(css = ".q-btn--outline > .q-btn__content")
    public WebElement cancelBtn;

    @FindBy(xpath = "//button[.='Submit']")
    public WebElement submit;

    @FindBy(css = ":nth-child(3) > .text-h6 > .q-btn")
    public WebElement editContainerSize;

    @FindBy(css = ":nth-child(4) > .text-h6 > .q-btn")
    public WebElement editMediaType;

    @FindBy(css = ".q-pl-xs-xs > .q-btn > .q-btn__content")
    public WebElement cancelEdit;

    @FindBy(css = "[role='combobox']")
    public WebElement csField;

    @FindBy(css = "[role='option']")
    public List<WebElement> containerOptions;

    @FindBy(xpath = "(//input[@class='q-field__input q-placeholder col'])[2]")
    public WebElement mtField;

    @FindBy(css = ".q-virtual-scroll__content span")
    public List<WebElement> mediaOptions;

    @FindBy(css = ".q-pr-xs-xs > .q-btn")
    public WebElement saveEdits;

    @FindBy(css = ".no-wrap > .bg-accent")
    public WebElement addItem;

    @FindBy(css = ":nth-child(2) > .q-btn--outline")
    public WebElement pauseJob;

    @FindBy(css = ".q-mb-xs-lg > :nth-child(2) > .q-btn--unelevated")
    public WebElement completeJob;

    @FindBy(css = "[class='q-table--col-auto-width']")
    public List<WebElement> scanItemCheckbox;

    @FindBy(className = "q-td text-left")
    public List<WebElement> scanItemsValues;

    @FindBy(css = ".no-wrap > .bg-negative")
    public WebElement delete;

    @FindBy(css = "[class='q-card__section q-card__section--vert']>p")
    public WebElement modal;

    @FindBy(css = "[class='q-card popup-modal']")
    public WebElement popupModal;

    @FindBy(xpath = "(//span[.='Cancel'])[1]")
    public WebElement cancelModal;

    @FindBy(css = "button[class$='q-btn--no-uppercase btn-no-wrap text-body1 q-mr-sm-md']")
    public WebElement enterBarcodeBtn;

    @FindBy(css = "[placeholder='Please Enter Barcode']")
    public WebElement enterBarcodeField;

    @FindBy(xpath = "//span[.='Add Tray']/../..")
    public WebElement addTrayBtn;

    @FindBy(css = "button[class$='btn-dashed btn-no-wrap text-body1 full-width']")
    public WebElement addTrayModalBtn;

    @FindBy(xpath = "(//span[.='submit']/..)[1]")
    public WebElement submitBtn;

    @FindBy(css = "[class='q-td text-left'] span")
    public WebElement scannedItem;

    @FindBy(css = "td.q-table--col-auto-width [role='checkbox']")
    public WebElement scannedItemCheckbox;

    @FindBy(css = "div[class='q-banner row items-center q-banner--dense rounded-borders alert-banner text-positive bg-color-green-light']")
    public WebElement alertMsg;

    @FindBy(css = "[class='barcode text-h4 q-mb-md-xl q-mb-lg-none']")
    public WebElement scanTrayField;

    @FindBy(xpath = "//button[.='Complete & Print']")
    public WebElement completeAndprint;

    @FindBy(xpath = "//*[.='Delete']/../..")
    public WebElement deleteBtn;

    @FindBy(xpath = "//button[.='Confirm']")
    public WebElement confirmDelete;













}
