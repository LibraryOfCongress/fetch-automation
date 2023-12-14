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

    @FindBy(css = ".col-xs-6 > .q-btn > .q-btn__content")
    public WebElement startAccessionBtn;

    @FindBy(css = ".column > :nth-child(2) > .q-btn__content")
    public WebElement trayedAccession;

    @FindBy(css = ".q-mb-md > .q-btn__content")
    public WebElement nonTrayAccession;

    @FindBy(xpath = "(//*[@class='q-field__native row items-center'])[1]")
    public WebElement ownerField;

    @FindBy(css = ".q-virtual-scroll__content>div:nth-child(2)")
    public WebElement johnDoe;

    @FindBy(css = ".q-virtual-scroll__content>div:nth-child(3)")
    public WebElement georgeW;

    @FindBy(xpath = "(//*[@class='q-field__native row items-center'])[2]")
    public WebElement containerSizeField;

    @FindBy(css = ".q-virtual-scroll__content span")
    public List<WebElement> ownerFieldOptions;

    @FindBy(xpath = "(//div[@class='q-item__label']/span)[1]")
    public WebElement aHigh;

    @FindBy(xpath = "(//*[@class='q-field__native row items-center'])[3]")
    public WebElement mediaTypeField;

    @FindBy(className = "form-group-label")
    public List<WebElement> newAccessionFields;

    @FindBy(css = ".q-pb-none > .q-btn--rectangle > .q-btn__content")
    public WebElement backBtn;

    @FindBy(css = ".q-btn--outline > .q-btn__content")
    public WebElement cancelBtn;











}
