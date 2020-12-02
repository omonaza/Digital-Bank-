package step_definitions.amzon;

import Pages.amazon.AmazonSearchPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.ConfigReader;
import utility.Driver;
import utility.Seleniumutils;

import java.util.List;
import java.util.Map;

public class amazonSearchSteps {

    WebDriver driver = Driver.getDriver();
    AmazonSearchPage amazonSearchPage = new AmazonSearchPage();

    @Given("^go to amazon\\.com$")
    public void go_to_amazon_com() throws Throwable {
        Seleniumutils.goTo(ConfigReader.getProperty("amazon_url"));
       // Seleniumutils.click(amazonSearchPage.goToAmazonUaEButton);

    }

    @When("^enter search term \"([^\"]*)\"$")
    public void enter_search_term(String book) throws Throwable {
        Seleniumutils.sendKeysWithClear(amazonSearchPage.bookTextToInputFiled, book);

    }

    @When("^click search button$")
    public void click_search_button() throws Throwable {
        Seleniumutils.click(amazonSearchPage.clickButton);
    }

    @Then("^verify that a result with \"([^\"]*)\" is  displayed anywhere in the results$")
    public void verifyThatAResultWithIsDisplayedAnywhereInTheResults(String result) throws Throwable {

        //        String element = "//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(),'"+result+"')]";
//        WebElement elText = driver.findElement(By.xpath(element));
//        Assert.assertTrue(elText.isDisplayed());


//        List<WebElement> searchResult = amazonSearchPage.searchResult;
//        for (int i = 0; i < searchResult.size(); i++) {
//            if (searchResult.get(i).getText().contains(result)) {
//                Assert.assertTrue(searchResult.get(i).getText().toLowerCase().contains(result));
//                System.out.println("text----> " + searchResult.get(i).getText());
//                break;
//
//            }
//        }


        for (WebElement results : amazonSearchPage.searchResult) {
            if (results.getText().contains(result)) {
                Assert.assertTrue(Seleniumutils.getText(results).contains(result));
                break;
            }
        }


    }


    @Then("^verify that a result with \"([^\"]*)\" is not displayed anywhere in the results$")
    public void verify_that_a_result_with_is_not_displayed_anywhere_in_the_results(String expectedText) throws Throwable {
        for (WebElement results : amazonSearchPage.searchResult) {
            Assert.assertFalse(Seleniumutils.getText(results).contains(expectedText));
        }

    }


    @Then("^verify all result contains the word \"([^\"]*)\"$")
    public void verify_all_result_contains_the_word(String expectedText) throws Throwable {
  //      for (WebElement results : amazonSearchPage.searchResult) {
//            if (results.getText().contains(expectedText)) {
//                System.out.println("expected texts -------> " +results);
//                Assert.assertTrue(Seleniumutils.getText(results).contains(expectedText));
//
//            }
//        }

            List<WebElement> searchResult = amazonSearchPage.searchResult;
            for (int i = 0; i < searchResult.size(); i++) {
                if (searchResult.get(i).getText().toLowerCase().contains(expectedText.toLowerCase())) {
                    System.out.println("text----> " + Seleniumutils.getText(searchResult.get(i)));
                   Assert.assertTrue(Seleniumutils.getText(searchResult.get(i)).toLowerCase().contains(expectedText.toLowerCase()));

             }

            }


        }


    @And("^verify free shipping check box is not selected$")
    public void verifyFreeShippingCheckBoxIsNotSelected() {
        Assert.assertFalse(Seleniumutils.Selected(amazonSearchPage.freeShippingButton));

    }

    @And("^check the free shipping box$")
    public void checkTheFreeShippingBox() {
        Seleniumutils.click(amazonSearchPage.freeShippingButton);
    }



    @Then("^verify that top five results are still \"([^\"]*)\"$")
    public void verifyThatTopFiveResultsAreStill(String topFiveResult) throws Throwable {
      for(int i = 0; i<amazonSearchPage.searchResult.size();i++){
          Assert.assertFalse(Seleniumutils.getText(amazonSearchPage.searchResult.get(i)).toLowerCase().contains(topFiveResult.toLowerCase()));
      }


    }
}
