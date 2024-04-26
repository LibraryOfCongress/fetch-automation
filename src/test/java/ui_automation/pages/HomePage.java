package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class HomePage {
    WebDriver driver;
    public HomePage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='q-focus-helper'])[1]" )
    public WebElement header;

    @FindBy(xpath = "(//div[.=' FETCH LOGO '])[2]")
    public WebElement logo;

    @FindBy(xpath = "//i[.='menu']")
    public WebElement hamburgerMenu;

    @FindBy(css = "[placeholder='Search']")
    public WebElement searchBar;

    @FindBy(xpath = "(//i[@role='presentation'])[2]")
    public WebElement loginButton;

    @FindBy(className = "q-list")
    public WebElement sideNavigationMenu;

    @FindBy(xpath = "//*[@class='demo']/li")
    public WebElement barCodeField;

    @FindBy(xpath="//*[@class='q-list nav-list']/a")
    public List<WebElement> allNavigationTabs;

    @FindBy(css = "a[class$='nav-active']")
    public WebElement highlightedLink;

    @FindBy(css = "[href='/accession']")
    public WebElement accessionLink;

    @FindBy(css = "[href='/verification']")
    public WebElement verificationLink;

    @FindBy(css = "[href='/shelving']")
    public WebElement shelvingLink;

    @FindBy(css = "[href='/admin']")
    public WebElement adminLink;

    @FindBy(css = "button[class$='q-btn q-btn-item non-selectable no-outline q-btn--flat q-btn--rectangle text-white q-btn--actionable q-focusable q-hoverable text-body1']:nth-child(3)")
     public WebElement banner;

    @FindBy(css = "[class='nav-actions']")
    public WebElement loginIcon;

    @FindBy(css = "[placeholder='Enter Username']")
    public WebElement usernameField;

    @FindBy(css = "[placeholder='Enter Password']")
    public WebElement passwordField;

    @FindBy(css = "[aria-label='Internal Login']")
    public WebElement login;

    @FindBy(className = "text-h6")
    public WebElement userName;

    @FindBy(xpath = "//i[.='logout']/../../..")
    public WebElement logout;

    @FindBy(css = "")
    public WebElement errorMessage;



}
