package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class ItemOverlayPage {

    WebDriver driver;

    public ItemOverlayPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".item-details label")
    public List<WebElement> overlayItemsLabels;

    @FindBy(css = "tbody > :nth-child(1)")
    public WebElement item1;

    @FindBy(css = ".q-card")
    public WebElement sideOverlay;


    @FindBy(xpath = "//*[contains(text(),'close')]")
    public WebElement closeBtn;

    @FindBy(css = ".q-page")
    public WebElement mainPage;

}
