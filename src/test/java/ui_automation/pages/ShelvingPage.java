package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class ShelvingPage {

    WebDriver driver;
    public ShelvingPage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//*[@class='q-item__label']/span")
    public List<WebElement> allDropdownOptions;

    @FindBy(css = "button[class$='table-component-filter']")
    public WebElement filter;

    @FindBy(css = ".q-field__native > span")
    public WebElement rearrangeDropdown;

    @FindBy(css = "[role='option']")
    public List<WebElement> allFilterDropdownOptions;

    @FindBy(css = ".col-xs-grow > .q-btn")
    public WebElement createShelvingJob;

    @FindBy(css = ".q-table th")
    public List<WebElement> shelfTableColumns;

    @FindBy(css = "[class='form-group-label']")
    public List<WebElement> newShelfFields;

    @FindBy(xpath = "(//span[.='Create Shelf'])[2]")
    public WebElement createShelfBtn;

    @FindBy(xpath = "(//span[.='Cancel'])[2]")
    public WebElement cancelBtn;

    @FindBy(css = "[class='q-toggle__track']")
    public WebElement toggleSwitch;

    @FindBy(css = "[placeholder='Select Owner']")
    public WebElement selectOwner;

    @FindBy(css = "[placeholder='Enter Shelf Number']")
    public WebElement enterShelfNumber;

    @FindBy(css = "[placeholder='Enter Shelf Width']")
    public WebElement enterShelfWidth;

    @FindBy(css = "[placeholder='Enter Shelf Height']")
    public WebElement enterShelfHeight;

    @FindBy(css = "[placeholder='Enter Shelf Depth']")
    public WebElement enterShelfDepth;

    @FindBy(css = "[placeholder='Select Type']")
    public WebElement selectType;

    @FindBy(css = "[role='listbox'] div.q-item:nth-child(3)")
    public WebElement sanders;






}
