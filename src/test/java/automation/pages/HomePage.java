package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.utilities.Driver;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='q-focus-helper'])[1]" )
    public WebElement header;

    @FindBy(css = "[aria-label='Menu Button']")
    public WebElement hamburgerMenu;

    @FindBy(css = "[type='search']")
    public WebElement searchBar;

    @FindBy(css = "[aria-label='UserMenu']")
    public WebElement userIcon;

    @FindBy(xpath = "//*[@class='demo']/li")
    public WebElement barCodeField;

    @FindBy(css = "[class='q-list nav-list'] [class='q-item__label']")
    public List<WebElement> allNavigationTabs;

    @FindBy(css = "a[class$='nav-active']")
    public WebElement highlightedLink;

    @FindBy(css = "[href='/accession']")
    public WebElement accessionLink;

    @FindBy(css = "[href='/verification']")
    public WebElement verificationLink;

    @FindBy(css = "[href='/shelving']")
    public WebElement shelvingLink;

    @FindBy(css = "[href='/request']")
    public WebElement requestLink;

    @FindBy(css = "[href='/picklist']")
    public WebElement picklistLink;

    @FindBy(css = "[href='/refile']")
    public WebElement refileLink;

    @FindBy(css = "[href='/withdrawal']")
    public WebElement withdrawalLink;

    @FindBy(css = "[href='/reports']")
    public WebElement reportsLink;

    @FindBy(css = "[href='/admin']")
    public WebElement adminLink;

    @FindBy(css = "[aria-label='barcodeToggle']")
    public WebElement toggleScan;

    @FindBy(xpath = "//button[.='Disable Scan']")
    public WebElement disableScan;

    @FindBy(css = "[class='q-banner__content col text-body2']")
    public WebElement scanningEnabledAlert;

    @FindBy(css = "button[class$='q-btn q-btn-item non-selectable no-outline q-btn--flat q-btn--rectangle text-white q-btn--actionable q-focusable q-hoverable text-body1']:nth-child(3)")
     public WebElement banner;

    @FindBy(css = "[class='nav-actions']")
    public WebElement loginButton;

    @FindBy(css = "[placeholder='Enter User Email']")
    public WebElement usernameField;

    @FindBy(css = "[aria-label='Internal Login']")
    public WebElement login;

    @FindBy(css = "h1.text-h6")
    public WebElement user;

    @FindBy(xpath = "//i[.='logout']/../../..")
    public WebElement logout;

    @FindBy(xpath = "")
    public WebElement errorMessage;

    @FindBy(css = "ul.demo >li")
    public List<WebElement> scannedBarcodes;

    @FindBy(xpath = "//button[.='SSO Login']")
    public WebElement ssoLogin;

    @FindBy(css = "[type='email']")
    public WebElement email;

    @FindBy(css = "[type='submit']")
    public WebElement next;

    @FindBy(css = "[type='password']")
    public WebElement password;

    @FindBy(css = "[aria-label='dismissAlert']")
    public WebElement cancelAlert;

    @FindBy(css = "[class='q-banner__content col text-body2']")
    public WebElement downloadBanner;

    @FindBy(xpath = "//button[.='Never']")
    public WebElement never;

    @FindBy(css = "thead th")
    public List<WebElement> tableColumns;

    @FindBy(css = "[aria-label='tableFilterOptions']")
    public WebElement filterIcon;

    @FindBy(css = "[role='menuitem'] label")
    public List<WebElement> filterOptions;






}
