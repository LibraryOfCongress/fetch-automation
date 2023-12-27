package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class AccessionJobPage {

    WebDriver driver;

    public AccessionJobPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "(//div[.='Accession'])[2]")
    public WebElement accessionTab;

    @FindBy(xpath = "(//button[@type='button'])[2]")
    public WebElement startAccessionBtn;

    @FindBy(css = ".column > :nth-child(2) > .q-btn__content")
    public WebElement trayedAccession;

    @FindBy(css = ".q-mb-md > .q-btn__content")
    public WebElement nonTrayAccession;

    @FindBy(css = "[placeholder='Select Owner']")
    public WebElement ownerField;

    @FindBy(css = "div[id='f_76bce7be-bfea-4fac-ad73-0fe5f879a220_0'][role='option']")
    public WebElement johnDoe;

    @FindBy(css = ".q-virtual-scroll__content>div:nth-child(3)")
    public WebElement georgeW;

    @FindBy(css = "[placeholder='Select Size']")
    public WebElement containerSizeField;

    @FindBy(css = ".q-virtual-scroll__content span")
    public List<WebElement> ownerFieldOptions;

    @FindBy(xpath = "(//div[@class='q-item__label']/span)[1]")
    public WebElement aHigh;

    @FindBy(css = "[placeholder='Select Media Type']")
    public WebElement mediaTypeField;

    @FindBy(className = "form-group-label")
    public List<WebElement> newAccessionFields;

    @FindBy(css = ".q-pb-none > .q-btn--rectangle > .q-btn__content")
    public WebElement backBtn;

    @FindBy(css = ".q-btn--outline > .q-btn__content")
    public WebElement cancelBtn;

    @FindBy(css = ".q-btn--unelevated > .q-btn__content")
    public WebElement submit;

    @FindBy(css = ".column > .q-btn")
    public WebElement scanBarcode;

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

    @FindBy(css = ".q-item__label span")
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
    public List<WebElement> scanItemList;

    @FindBy(css = ".no-wrap > .bg-negative")
    public WebElement delete;

    @FindBy(css = "[class='q-card__section q-card__section--vert']>p")
    public WebElement modal;

    @FindBy(xpath = "(//span[.='Cancel'])[1]")
    public WebElement cancelModal;













}
