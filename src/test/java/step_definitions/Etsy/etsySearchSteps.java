package step_definitions.Etsy;

import Pages.Etsy.EtsySearchPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ConfigReader;
import utility.Driver;
import utility.Seleniumutils;

public class etsySearchSteps {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
    EtsySearchPage etsySearchPage = new EtsySearchPage();

    @Given("^got to etsy\\.com$")
    public void got_to_etsy_com() throws Throwable {
        Seleniumutils.goTo(ConfigReader.getProperty("etsy_url"));

    }


    @When("^Enter any search term \"([^\"]*)\"$")
    public void enterAnySearchTerm(String text) throws Throwable {
      Seleniumutils.sendKeys(etsySearchPage.etsySearchInputField,text);

    }

    @And("^click on search button$")
    public void clickOnSearchButton() {
        Seleniumutils.click(etsySearchPage.searchButton);
    }

    @And("^click on allFilters button$")
    public void clickOnAllFiltersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(etsySearchPage.allFiltersButton));
        Seleniumutils.click(etsySearchPage.allFiltersButton);
    }

    @Then("^verify shop location anywhere is checked$")
    public void verifyShopLocationAnywhereIsChecked() throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOf(etsySearchPage.targetElementToMove));
       Seleniumutils.moveToElement(etsySearchPage.targetElementToMove);
        Thread.sleep(4000);
        Assert.assertTrue(Seleniumutils.Selected(etsySearchPage.anywhereRadioButton));
    }

}
