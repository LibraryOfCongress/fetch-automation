package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.utilities.Driver;

import java.util.List;

public class AdminPage {

    WebDriver driver;

    public AdminPage() {
        driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[class='q-list'] a")
    public List<WebElement> adminPageLinks;

    @FindBy(css = "[href='/admin/buildings/']")
    public WebElement buildingsLink;

    @FindBy(xpath = "//div[contains(text(),'Location Manager')]/../..")
    public WebElement locationManagerLink;

    @FindBy(css = "[href='/admin/groups/']")
    public WebElement groupsAndPermissionsLink;

    @FindBy(css = "[class='q-expansion-item q-item-type q-expansion-item--expanded q-expansion-item--standard admin-dashboard-expansion'] a")
    public List<WebElement> lmDropdownLinks;

    @FindBy(xpath = "//*[.='Add Building']/../..")
    public WebElement addBuildingBtn;

    @FindBy(css = "[class='row'] [class$='building-card']")
    public List<WebElement> buildings;

    @FindBy(css = "[class='text-h5 text-bold']")
    public List<WebElement> groups;

    @FindBy(css = "[class='q-card q-card--bordered q-card--flat no-shadow admin-groups-card admin-groups-card-dashed']")
    public WebElement addNewGroup;

    @FindBy(css = "[class$='q-mr-sm']")
    public WebElement addNew;

    @FindBy(css = "[class$='q-btn--no-uppercase text-body1']")
    public WebElement locationHierarchy;

    @FindBy(css = ".q-table tbody tr")
    public List<WebElement> shelvingItems;

    @FindBy(css = "[class$='q-btn--actionable q-focusable q-hoverable more-menu']")
    public List<WebElement> threeDots;

    @FindBy(css = ".q-menu > .q-list > .q-item")
    public WebElement editBtn;

    @FindBy(css = "[class='q-card popup-modal']")
    public WebElement editShelfModal;

    @FindBy(css = "[class='form-group q-mb-md']  .form-group-label")
    public List<WebElement> editShelfFields;

    @FindBy(css = "[placeholder='Enter Shelf Number']")
    public WebElement editShelfNumber;

    @FindBy(css = "[placeholder='Select Owner']")
    public WebElement editOwner;

//    @FindBy(css = "div.q-virtual-scroll__content div.q-item__label")
    @FindBy(css = "div.q-virtual-scroll__content div.q-item")
    public List<WebElement> modalFieldOptions;

    @FindBy(css = "[placeholder='Select Container Size']")
    public WebElement editContainerField;

    @FindBy(css = "[placeholder='Enter Max Capacity']")
    public WebElement editMaxCapacity;

    @FindBy(css = "[placeholder='Enter Shelf Width']")
    public WebElement editWidth;

    @FindBy(css = "[placeholder='Enter Shelf Height']")
    public WebElement editHeight;

    @FindBy(css = "[placeholder='Enter Shelf Depth']")
    public WebElement editDepth;

    @FindBy(css = "[placeholder='Select Shelf Barcode']")
    public WebElement editBarcode;

    @FindBy(css = "[class$='q-hoverable q-btn--no-uppercase text-body1 full-width']")
    public WebElement update;

    @FindBy(css = "[class$='q-btn--no-uppercase building-modal-btn text-body1 full-width']")
    public WebElement cancelUpdate;

    @FindBy(css = "[class='q-list'] [class='q-ml-xs']")
    public List<WebElement> addNewOptions;

    @FindBy(css = "[class='q-list'] span")
    public List<WebElement> locHierOptions;

    @FindBy(css = "[class='q-card popup-modal']")
    public WebElement popUpModal;

    @FindBy(css = "[placeholder='Please Type Building Name']")
    public WebElement buildingField;

    @FindBy(css = "[placeholder='Select Building']")
    public WebElement selectBuilding;

    @FindBy(css = "[class$='bg-accent text-white q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase text-body1 full-width']")
    public WebElement createBtn;

    @FindBy(xpath = "//button[.='Cancel']")
    public WebElement cancelBtn;

    @FindBy(css = ".items-end > :nth-child(2) > .q-field > .q-field__inner input")
    public WebElement moduleField;

    @FindBy(css = ".q-field__native [placeholder='Select Module']")
    public WebElement selectModule;

    @FindBy(css = ".q-field__native [placeholder='Select Aisle']")
    public WebElement selectAisle;

    @FindBy(css = ".q-field__native [placeholder='Select Ladder']")
    public WebElement selectLadder;

    @FindBy(css = "div[class='q-virtual-scroll__content'] [role='option']")
    public List<WebElement> fieldDropdwnList;

    @FindBy(css = ".q-pr-xs > .form-group > .q-field > .q-field__inner input")
    public WebElement aisleField;

    @FindBy(css = ".q-mt-md > .q-field > .q-field__inner input")
    public WebElement ladderField;

    @FindBy(css = ".q-btn-group > .bg-accent")
    public WebElement sideBtnLeft;

    @FindBy(css = ".q-btn-group > .bg-white")
    public WebElement sideBtnRight;

    @FindBy(css = "[id='file-input']")
    public WebElement upload;

    @FindBy(xpath = "//li[@class='text-body1']/span")
    public WebElement uploadedFile;

    @FindBy(css = "[placeholder='Enter Group Name']")
    public WebElement enterGroupNameField;

    @FindBy(css = "[aria-label='optionsMenu']")
    public List<WebElement> threeDotMenu;

    @FindBy(css = "[role='menuitem']")
    public List<WebElement> menuOptions;

    @FindBy(css = "[role='tab']")
    public List<WebElement> tabNames;

    @FindBy(css = "[class='q-table'] tr th")
    public List<WebElement> tableColumns;

    @FindBy(xpath = "//button[.='Delete Group']")
    public WebElement confirmDeleteGroup;

    @FindBy(xpath = "//button[.='Yes']")
    public List<WebElement> yes;

    @FindBy(xpath = "//button[.='No']")
    public List<WebElement> no;

    @FindBy(css = "[placeholder='Select User To Add']")
    public WebElement selectUserToAddField;

    @FindBy(css = "[role='option']")
    public List<WebElement> usersList;

    @FindBy(xpath = "//button[.='Add User(s)']")
    public WebElement addUsersBtn;

    @FindBy(css = "[class='admin-groups-users-chip q-pa-xs']")
    public List<WebElement> groupUserTabs;

    @FindBy(xpath = "//button[.='Delete User']")
    public WebElement deleteUserFromGroupBtn;

    @FindBy(css = "[aria-label='closeModal']")
    public WebElement closeModal;

    @FindBy(xpath = "//button[.='Save Changes']")
    public WebElement renameGroupBtn;


}
