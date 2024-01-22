package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class ShelfMgmtPage {

    WebDriver driver;
    public ShelfMgmtPage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "(//div[.='Shelving'])[2]")
    public WebElement shelving;

    @FindBy(css = ":nth-child(1) > .shelving-details > .q-field > .q-field__inner > .q-field__control > .q-field__control-container > .q-field__native")
    public WebElement aisle;

    @FindBy(xpath = "//*[@class='q-item__label']/span")
    public List<WebElement> allDropdownOptions;

    @FindBy(css = ":nth-child(2) > .shelving-details > .q-field > .q-field__inner > .q-field__control > .q-field__control-container > .q-field__native")
    public WebElement side;

    @FindBy(css = ":nth-child(3) > .shelving-details > .q-field > .q-field__inner > .q-field__control")
    public WebElement ladder;

    @FindBy(xpath = "(//*[@class='q-field__native row items-center'])[4]")
    public WebElement filterDropdown;

    @FindBy(css = "[role='option']")
    public List<WebElement> allFilterDropdownOptions;

    @FindBy(xpath = "//span[.='Add Shelf']")
    public WebElement addShelfBtn;

    @FindBy(css = ".q-table th")
    public List<WebElement> shelfTableColumns;

    @FindBy(css = "[class='form-group-label']")
    public List<WebElement> newShelfFields;

    @FindBy(xpath = "(//span[.='Create Shelf'])[2]")
    public WebElement createShelfBtn;

    @FindBy(xpath = "(//span[.='Cancel'])[2]")
    public WebElement cancelBtn;

    @FindBy(css = ".q-btn--flat > .q-btn__content > .block")
    public WebElement exportReport;

    @FindBy(css = "[class='q-toggle__track']")
    public WebElement toggleSwitch;





}
