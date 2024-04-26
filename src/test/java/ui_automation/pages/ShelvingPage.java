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

    @FindBy(className = "text-h6")
    public List<WebElement> modalSections;

    @FindBy(className = "form-group-label")
    public List<WebElement> modalFields;

    @FindBy(xpath = "//button[.='Cancel']")
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

    @FindBy(xpath = "//button[.='From Verification Job']")
    public WebElement fromVerificationJob;

    @FindBy(xpath = "//button[.='No']")
    public WebElement no;

    @FindBy(css = "[class$='q-btn--rectangle bg-white text-black q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']")
    public WebElement yes;

    @FindBy(css = "[placeholder='Select Verification Job(s) by Number']")
    public WebElement selectByNumber;

    @FindBy(css = "[role='listbox'] [role='option']")
    public List<WebElement> verificationJobsList;

    @FindBy(xpath = "//button[.='Submit']")
    public WebElement submit;

    @FindBy(xpath = "//p[.='Created']")
    public WebElement shelvingJobStatus;

    @FindBy(id = "jobNumber")
    public WebElement jobNumber;

    @FindBy(css = "[class='q-card popup-modal']")
    public WebElement createShelvingJobModal;

    @FindBy(xpath = "//button[.='more_vert']")
    public List<WebElement> threeDotMenu;

    @FindBy(xpath = "(//td[@class='q-td text-left'])[8]")
    public WebElement shelfNumber;

    @FindBy(xpath = "//div[@role='menu']")
    public WebElement editOrAssign;







}
