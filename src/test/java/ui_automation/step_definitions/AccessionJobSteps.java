package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.AccessionJobPage;
import ui_automation.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessionJobSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    AccessionJobPage accessionJob = new AccessionJobPage();
    Helper helper = new Helper();
    SelectHelper select = new SelectHelper();
    WaitHelper wait = new WaitHelper();
    public static final Logger oLog = LogManager.getLogger(AccessionJobSteps.class);


    @When("user clicks Accession button on side navigation menu")
    public void user_clicks_Accession_button_on_side_navigation_menu() {
       helper.jSClick(accessionJob.accessionTab);
        oLog.info("I navigated to Accession Jobs Page");
    }

    @When("user hovers over Start Accession button")
    public void user_hovers_over_the_Start_Accession_button() {
      helper.hover(accessionJob.startAccessionBtn);
    }

    @When("user clicks Start Accession button")
    public void user_clicks_Start_Accession_button()  {
       helper.jSClick(accessionJob.startAccessionBtn);
        oLog.info("I started Accession Job");
    }

    @When("user selects Trayed Accession")
    public void user_selects_Trayed_Accession()  {
    helper.jSClick(accessionJob.trayedAccession);
        oLog.info("I clicked Trayed Accession Option");
    }

    @And("user selects Non-Tray Accession")
    public void userSelectsNonTrayAccession() {
        helper.jSClick(accessionJob.nonTrayAccession);
        oLog.info("I clicked Non-Tray Accession Option");
    }

    @Then("user verifies required and optional fields on Start New Accession modal")
    public void user_verifies_required_and_optional_fields_on_Start_New_Accession_modal(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedLabel = map.get("fieldname");
            String actualLabel = accessionJob.newAccessionFields.get(i).getText();
            Assert.assertEquals("Fieldname verification failed",
                    expectedLabel, actualLabel);
            i++;
        }
        oLog.info("I verified New Accession Job's fields");
    }

    @When("user selects all required fields")
    public void user_selects_all_required_fields() {
      helper.jSClick(accessionJob.ownerField);
      helper.jSClick(accessionJob.johnDoe);
        oLog.info("I chose John Doe in Owner field");
      helper.jSClick(accessionJob.containerSizeField);
      helper.jSClick(accessionJob.aHigh);
        oLog.info("I clicked A High option in Container Size field");
    }

    @When("user is able to click Back button")
    public void user_is_able_to_click_Back_button() {
    helper.isClickable(accessionJob.backBtn);
    }

    @When("user is able to click Cancel button")
    public void user_is_able_to_click_Cancel_button() {
    helper.isClickable(accessionJob.cancelBtn);
    }

    @Then("user is able to return to the Start Accession single action square screen")
    public void user_is_able_to_return_to_the_Start_Accession_single_action_square_screen() {
       String actualURL = driver.getCurrentUrl();
       String expectedURL = "https://c2vldimsweb01.loctest.gov/accession";
       Assert.assertEquals("Returning to Accession Jobs Page failed",
               expectedURL, actualURL);
        oLog.info("I returned to the Start Accession Job page");
    }

    //TODO find better locator
    @And("user verifies Owner field options")
    public void userVerifiesOwnerFieldOptions() {
        helper.jSClick(accessionJob.ownerField);
        List<String> elemTexts =  helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for(String elemText: elemTexts){
            System.out.println(elemText);
        }
        oLog.info("I printed Owner field options");

    }

    @And("user verifies Container Size field options")
    public void userVerifiesContainerSizeFieldOptions() {
        helper.jSClick(accessionJob.containerSizeField);

        //TODO find better locator
        List<String> elemTexts =  helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for(String elemText: elemTexts){
            System.out.println(elemText);
        }
        oLog.info("I printed Container Size field options ");
    }

    @And("user verifies Media Type field options")
    public void userVerifiesMediaTypeFieldOptions() {
        helper.jSClick(accessionJob.mediaTypeField);

        //TODO find better locator
        List<String> elemTexts =  helper.getElementsText(By.cssSelector(".q-virtual-scroll__content span"));
        for(String elemText: elemTexts){
            System.out.println(elemText);
        }
        oLog.info("I printed Media Type field options ");
    }

    @When("user clicks on select Owner button")
    public void userClicksOnSelectOwnerButton() {
       helper.jSClick(accessionJob.ownerField);
    }

    @Then("user is able to choose any option from dropdown field")
    public void userIsAbleToChooseAnyOptionFromDropdownField() throws InterruptedException {

        //TODO create dynamic method
         select.selectCheckBox(accessionJob.johnDoe, true);
         Thread.sleep(3000);
         helper.jSClick(accessionJob.ownerField);
         select.selectCheckBox(accessionJob.georgeW, true);
         Thread.sleep(3000);
       }

    }


