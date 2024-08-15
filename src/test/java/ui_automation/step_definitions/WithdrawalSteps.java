package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.WithdrawalPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;
import ui_automation.utilities.WaitHelper;

import java.util.List;
import java.util.Map;


import static org.junit.Assert.assertEquals;

public class WithdrawalSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    WithdrawalPage withdrawal = new WithdrawalPage();
    VerificationSteps verificationSteps = new VerificationSteps();
    Helper helper = new Helper();
    WaitHelper wait = new WaitHelper();


    @Given("user navigates to the Withdrawal Page")
    public void user_navigates_to_the_Withdrawal_Page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui_config.properties", "withdrawalURL"));
    }

    @When("user clicks Create Withdraw Job")
    public void user_clicks_Create_Withdraw_Job() {
        Helper.clickWithJS(withdrawal.createWithdrawJob);
    }

    @When("user clicks on Withdraw Job")
    public void user_clicks_on_withdraw_job() {
        withdrawal.createdJobs.get(0).click();
    }

    @When("user clicks on the created Withdraw Job")
    public void user_clicks_on_the_created_withdraw_job() {
        withdrawal.createdJobs.get(withdrawal.createdJobs.size() - 1).click();
    }

    @Then("user verifies a Withdraw Job is created")
    public void user_verifies_a_Withdraw_Job_is_created() {
        assertEquals("Withdraw Job is not created!", "Created", withdrawal.jobStatus.getText());
    }

    @Then("user verifies the Withdraw Job detail page is displayed")
    public void user_verifies_the_withdraw_job_detail_page_is_displayed() {
        Helper.verifyElementDisplayed(withdrawal.withdrawJobNumber);
        Helper.verifyElementEnabled(withdrawal.withdrawItemsBtn);
        assertEquals("Status of the Job is not created!", "Created", withdrawal.jobStatus.getText());
    }

    @Then("user verifies Withdrawal dashboard column names")
    public void user_verifies_Withdrawal_dashboard_column_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("column");
            wait.hardWait(1000);
            String actualName = withdrawal.withdrawalColumns.get(i).getText();
            assertEquals("Tab names verification failed",
                    expectedName, actualName);
            i++;
        }
    }

    @Then("user verifies Withdraw Job table column names")
    public void user_verifies_table_tab_names(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            String expectedName = map.get("column");
            wait.hardWait(1000);
            String actualName = withdrawal.withdrawalColumns.get(i).getText();
            assertEquals("Tab names verification failed",
                    expectedName, actualName);
            i++;
        }
    }

    @When("user verifies {string} and {string} options are displayed")
    public void user_verifies_and_options_are_displayed(String edit, String deleteJob) throws InterruptedException {
        assertEquals(edit, withdrawal.threeDotMenuOptions.get(0).getText());
        assertEquals(deleteJob, withdrawal.threeDotMenuOptions.get(1).getText());
        verificationSteps.user_clicks_three_dot_menu_next_to_Job_Number();
    }

    @When("user clicks three dots menu next to the Item Barcode in the table")
    public void user_clicks_three_dots_menu_next_to_the_item_barcode_in_the_table() throws InterruptedException {
        helper.jSClick(withdrawal.threeDotNextToItemBarcode);
        wait.hardWait(1000);
    }

    @Then("user verifies {string} option is displayed")
    public void user_verifies_option_is_displayed(String removeItem) {
        WaitHelper.waitForVisibility(withdrawal.threeDotMenuOptions.get(0), 1000);
        assertEquals(removeItem, withdrawal.threeDotMenuOptions.get(0).getText());
    }

    @When("user clicks Delete Job")
    public void user_clicks_Delete_Job() throws InterruptedException {
        withdrawal.threeDotMenuOptions.get(1).click();
        wait.hardWait(100);
    }

    @When("user confirms delete job action")
    public void user_confirms_delete_job_action() throws InterruptedException {
        Helper.clickWithJS(withdrawal.confirmDeleteWithdrawJob);
        wait.hardWait(100);
    }

    @Then("user verifies the assigned user has been updated")
    public void user_verifies_the_assigned_user_has_been_updated() {
        assertEquals("Tester", withdrawal.assignedUser.getText());
    }
}
