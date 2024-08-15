package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class WithdrawalPage {

    WebDriver driver;

    public WithdrawalPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".q-table tbody tr")
    public WebElement withdrawJob;

    @FindBy(xpath = "//button[.='Create Withdraw Job']")
    public WebElement createWithdrawJob;

    @FindBy(css = "[class='info-display-number-box text-h4']")
    public WebElement withdrawJobNumber;

    @FindBy(css = "[class='text-body1 text-center outline text-highlight']")
    public WebElement jobStatus;

    @FindBy(xpath = "//button[.='Withdraw Items']")
    public WebElement withdrawItemsBtn;

    @FindBy(css = ".q-table th.text-left")
    public List<WebElement> withdrawalColumns;

    @FindBy(css = "[role='menuitem']")
    public List<WebElement> threeDotMenuOptions;

    @FindBy(xpath = "(//button[.='more_vert'])[2]")
    public WebElement threeDotNextToItemBarcode;

    @FindBy(xpath = "//td[.='Created']")
    public List<WebElement> createdJobs;

    @FindBy(xpath = "//button[.='Delete Job']")
    public WebElement confirmDeleteWithdrawJob;

    @FindBy(xpath = "(//p[@class='text-body1'])[1]")
    public WebElement assignedUser;

}
