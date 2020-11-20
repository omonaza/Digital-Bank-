package step_definitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Driver;

import java.util.List;
import java.util.Map;

public class DigitalBankAccountSteps {
    WebDriver driver = Driver.getDriver();
    private final String LOGIN_PAGE_URL = "http://dbankdemo.com/login";
    private final String HOME_PAGE_URL = "http://dbankdemo.com/home";


    @Given("^User navigates to Digital Bank login page$")
    public void user_navigates_to_Digital_Bank_login_page() throws Throwable {
        driver.get(LOGIN_PAGE_URL);
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(LOGIN_PAGE_URL);

    }

    //  \"([^\"]*)\" .*
    @Given("^Verify that web title is \"([^\"]*)\".*$")
    public void verify_that_web_title_is(String title) throws Throwable {
        Assertions.assertThat(driver.getTitle()).isEqualTo(title);
    }

    @When("^User logs in with following credentials$")
    public void user_logs_in_with_following_credentials(DataTable dataTable) throws Throwable {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("username")).sendKeys(credentials.get(0).get("username"));
//        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(credentials.get("username"));
        driver.findElement(By.id("password")).sendKeys(credentials.get(0).get("password"));
        driver.findElement(By.id("submit")).click();

    }


    @Then("^Verify that \"([^\"]*)\" text is displayed$")
    public void verify_that_text_is_displayed(String text) throws Throwable {
        String TextJohn = driver.findElement(By.xpath("//li[text()='Welcome John ']")).getText();
        Assertions.assertThat(TextJohn).isEqualTo(text);


    }

    @Then("^Verify that panel with account information is displayed$")
    public void verify_that_panel_with_account_information_is_displayed() throws Throwable {
        // title
        Assert.assertTrue(driver.getTitle().equals("Digital Bank"));

        // home button
        WebElement homeButton = driver.findElement(By.xpath("//a[@href='/home']"));
        Assert.assertTrue(homeButton.isDisplayed());

        // Banking Accounts text
        String BunkingAccountText = driver.findElement(By.xpath("//h3[@class='menu-title' and text()='Banking Accounts']")).getText();
        Assert.assertTrue("Banking Accounts Failure ", BunkingAccountText.equalsIgnoreCase("Banking Accounts"));

        // Checking Button
        WebElement checkingButton = driver.findElement(By.xpath("//li[@class='menu-item-has-children dropdown']//a[@class='dropdown-toggle']//i[@class='menu-icon fa fa-pencil-square-o']"));
        Assert.assertTrue("Checking button  failure ---> ", checkingButton.isDisplayed());

        // Savings Button
        Assert.assertTrue("Savings Button Failure---> ", driver.findElement(By.id("savings-menu")).isDisplayed());

        // Transaction Button
        WebElement TransactionButton = driver.findElement(By.xpath("//li[@class='menu-item-has-children dropdown']//a[@class='dropdown-toggle']//i[@class='menu-icon fa fa-money']"));
        Assert.assertTrue("Transaction Button Failure", TransactionButton.isDisplayed());

        // Credit Accounts text
        String creditAccountsText = driver.findElement(By.xpath("//h3[text()='Credit Accounts']")).getText();
        Assert.assertTrue("Credit Accounts Failure", creditAccountsText.equalsIgnoreCase("Credit Accounts"));

        // Digital Credit Button
        WebElement DigitalCreditButton = driver.findElement(By.xpath("//a[text()='Digital Credit']"));
        Assert.assertTrue("Digital Credit Button Failure", DigitalCreditButton.isDisplayed());

        // TRANSFERS / PAYMENTS text
        String TransferPaymentsText = driver.findElement(By.xpath("//h3[@class='menu-title' and text()='Transfers / Payments']")).getText();
        Assert.assertTrue("Transfer Payment Text Failure", TransferPaymentsText.equals("TRANSFERS / PAYMENTS"));

        // Transfer Button
        Assert.assertTrue("Transfer Button failure---->", driver.findElement(By.xpath("//a[text()='Transfer']")).isDisplayed());

        // 3rd Party Transfer text
        String ThirdPartyTransfersText = driver.findElement(By.xpath("//h3[text()='3rd Party Transfers']")).getText();
        Assert.assertTrue("3rd Party Transfer Failure---> ", ThirdPartyTransfersText.equalsIgnoreCase("3rd Party Transfers"));

        // Visa Direct Button
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='VISA Direct']")).isDisplayed());

        // Account Balance Summary text
        String AccountBalanceSummaryText = driver.findElement(By.xpath("//h4[text()='Account Balance Summary']")).getText();
        Assert.assertTrue("Account Balance Summary text Failure", AccountBalanceSummaryText.equalsIgnoreCase("Account Balance Summary"));


        // Withdraw By Category (Last 3 Months) text
        String WithdrawByCategoryLastMonthsText = driver.findElement(By.xpath("//h4[text()='Withdraw By Category (Last 3 Months)']")).getText();
        Assert.assertTrue("Withdraw By Category Failure --->", WithdrawByCategoryLastMonthsText.startsWith("Withdraw By Category"));


        // Deposit vs. Withdraw (Last 3 Months)
        String DepositVsWithdrawText = driver.findElement(By.xpath("//h4[text()='Deposit vs. Withdraw (Last 3 Months)']")).getText();
        Assert.assertTrue("Deposit vs. Withdraw (Last 3 Months) text Failure--> ", DepositVsWithdrawText.endsWith("Last 3 Months)"));

        // Deposit By Category (Last 3 Months) text
        String DepositByCategoryText = driver.findElement(By.xpath("//h4[text()='Deposit By Category (Last 3 Months)']")).getText();
        Assert.assertTrue("Deposit By Category (Last 3 Months) text Failure---> ", DepositByCategoryText.startsWith("Deposit By Category"));

        // email button
        Assert.assertTrue("email button failure--->", driver.findElement(By.className("ti-email")).isDisplayed());
        Thread.sleep(3000);
        // bell button
        Assert.assertTrue(" bell button  failure--> ", driver.findElement(By.xpath("//i[@class='fa fa-bell']")).isDisplayed());

        // Search button
        Assert.assertTrue("Search button  failure-->", driver.findElement(By.xpath("//i[@class='fa fa-search']")).isDisplayed());

    }

    @Then("^User clicks on \"([^\"]*)\" button$")
    public void user_clicks_on_button(String checking) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement chButton = driver.findElement(By.xpath("//li[@class='menu-item-has-children dropdown']//a[@class='dropdown-toggle']//i[@class='menu-icon fa fa-pencil-square-o']"));
        js.executeScript("arguments[0].click();", chButton);
       // Thread.sleep(3000);


    }

    @Then("^Verify that under \"([^\"]*)\" we have options$")
    public void verify_that_under_we_have_options(String mainButton, List<String> buttonOptions) throws Throwable {

        WebElement headButton = driver.findElement(By.xpath("//a[text()='"+mainButton+"']"));
//        driver.findElement(By.xpath("//a[text()='Savings']"));
        headButton.click();
        List<WebElement> listOptionButtons = driver.findElements
                (By.xpath("//a[text()='"+mainButton+"']/following-sibling::" +
                        "ul[@class='sub-menu children dropdown-menu show']//a"));
        for(int i=0;i<listOptionButtons.size();i++){
            Assert.assertTrue(buttonOptions.contains(listOptionButtons.get(i).getText()));
        }




    }

    @Then("^User clicks on Checking button$")
    public void user_clicks_on_Checking_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^User clicks on New Checking button$")
    public void user_clicks_on_New_Checking_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Verify that \"([^\"]*)\" header is displayed$")
    public void verify_that_header_is_displayed(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Verify that \"([^\"]*)\" label is displayed$")
    public void verify_that_label_is_displayed(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Verify that radio buttons are unchecked with following text$")
    public void verify_that_radio_buttons_are_unchecked_with_following_text(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        ;
    }

    @Then("^Verify that input field accepts alphanumeric and special characters$")
    public void verify_that_input_field_accepts_alphanumeric_and_special_characters() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Verify that input field accepts accepts numeric whole or decimal values$")
    public void verify_that_input_field_accepts_accepts_numeric_whole_or_decimal_values() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Verify that \"([^\"]*)\" button is displayed$")
    public void verify_that_button_is_displayed(String arg1) throws Throwable {

    }

    @Then("^User should get error message \"([^\"]*)\"$")
    public void user_should_get_error_message(String arg1) throws Throwable {

    }

    @Then("^User clicks on \"([^\"]*)\" radio buttons$")
    public void user_clicks_on_radio_buttons(String arg1) throws Throwable {

    }

    @Then("^User enter name \"([^\"]*)\" into Account Name$")
    public void user_enter_name_into_Account_Name(String arg1) throws Throwable {

    }


}
