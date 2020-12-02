package step_definitions.digitalBank;


import com.github.javafaker.Faker;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Driver;

import java.util.List;
import java.util.Map;

/**
 * TODO:
 * Add implementations of steps from feature file
 */
public class DigitalBankRegistrationSteps {
    WebDriver driver = Driver.getDriver();
    private final String LOGIN_PAGE_URL = "http://dbankdemo.com/login";
    private final String HOME_PAGE_URL = "http://dbankdemo.com/home";
    private String username;
    private String password;
    private String title;

//    @Given("^User navigates to Digital Bank login page$")
//    public void user_navigates_to_digital_bank_login_page() {
//        driver.get(LOGIN_PAGE_URL);
//        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(LOGIN_PAGE_URL);
//    }

//    @Given("^Verify that web title is \"([^\"]*)\"$")
//    public void verify_that_web_title_is(String title) throws Throwable {
//        Assertions.assertThat(driver.getTitle()).isEqualTo(title);
//    }

//    @When("^User logs in with following credentials$")
//    public void user_logs_in_with_following_credentials(DataTable dataTable) throws Throwable {
//        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
//        driver.findElement(By.id("username")).sendKeys(credentials.get(0).get("username"));
////        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(credentials.get("username"));
//        driver.findElement(By.id("password")).sendKeys(credentials.get(0).get("password"));
//        driver.findElement(By.id("submit")).click();
//    }

    @Then("^User successfully logged in to home page$")
    public void user_successfully_logged_in_to_home_page() throws Throwable {
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(HOME_PAGE_URL);
        WebElement welcomeLink = driver.findElement(By.xpath("//li[contains(text(),'Welcome')]"));
        Assertions.assertThat(welcomeLink.getText()).contains("Welcome");
    }

    @Then("^User should be displayed with the error message \"([^\"]*)\"$")
    public void user_should_be_displayed_with_the_error_message(String expectedErrorMessage) throws Throwable {
        WebElement error = driver.findElement(
                By.xpath("//div[@class='sufee-alert alert " +
                        "with-close alert-danger alert-dismissible fade show']"));
        Assertions.assertThat(error.getText()).contains("Error Invalid credentials or access not granted.");
    }

    @When("^User logs in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userLogsInWithAnd(String username, String password) throws Throwable {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    @Given("^User navigates to Digital Bank signup page$")
    public void userNavigatesToDigitalBankSignupPage() {
        driver.get(LOGIN_PAGE_URL);
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//a[@href='/signup']")).click();
    }

    @When("^User creates account with following fields$")
    public void user_creates_account_with_following_fields(DataTable dataTable) throws Throwable {

//        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
//        WebElement title = driver.findElement(By.xpath("//label[@for='title']/strong"));
//        Assertions.assertThat(driver.getTitle().equals(title));
//        WebElement selectElement = driver.findElement(By.cssSelector("#title"));
//
//        selectElement.click();
//        Thread.sleep(3000);
//        Select selectDropDown = new Select(selectElement);
//        selectDropDown.selectByVisibleText(credentials.get(0).get("title"));
//        Assertions.assertThat(selectDropDown.getFirstSelectedOption().getText().equals(credentials.get(0).get("title")));


        //generate random data
        Faker fake = new Faker();
        String email = fake.internet().emailAddress();
        String ssn = fake.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}");
        String homePhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");
        String mobilePhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");
        String workPhone = fake.regexify("\\([0-8]\\d{2}\\)\\d{3}-\\d{4}");


          //save datatable from feature file
        List<User> userList = dataTable.asList(User.class);

        //save data on class level to use in the next step
        username = email;
        password = userList.get(0).getPassword();
        this.title=driver.getTitle();


         //select title Mr.
        WebElement selectTitle = driver.findElement(By.id("title"));
        Select select = new Select(selectTitle);
        select.selectByValue(userList.get(0).getTitle());

        //enter firstname
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys(userList.get(0).getFirstName());


        //enter Lastname
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys(userList.get(0).getLastName());


        //selevt gender bynamicly
        List<WebElement> gender = driver.findElements(By.id("gender"));
        for (WebElement element : gender) {
            if (element.getAttribute("value").equalsIgnoreCase(userList.get(0).getGender())) {
                element.click();
            }
        }
         //select randomly gender
        //int randomGender = Integer.valueOf(fake.regexify("[1-2]"));
        //gender.get(randomGender).click();


        //dateOfBirth
        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys(userList.get(0).getDob());

        //ssn
        WebElement ssnLocated = driver.findElement(By.id("ssn"));
        ssnLocated.sendKeys(ssn);

        //email
        WebElement emailLocated = driver.findElement(By.id("emailAddress"));
        emailLocated.sendKeys(email);

        //password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(this.password);

        //confirm password
        WebElement confirmPass = driver.findElement(By.id("confirmPassword"));
        confirmPass.sendKeys(this.password);
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();
        Thread.sleep(3000);

        //verify address page
        WebElement addressWord = driver.findElement(By.xpath("//strong[text()='Address']"));
        Assert.assertTrue(addressWord.isDisplayed());

        //next Page address
        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys(userList.get(0).getAddress());

        //locality
        WebElement locality = driver.findElement(By.id("locality"));
        locality.sendKeys(userList.get(0).getLocality());

        //region
        WebElement region = driver.findElement(By.id("region"));
        region.sendKeys(userList.get(0).getLocality());

        //postal code
        WebElement postalCode = driver.findElement(By.id("postalCode"));
        postalCode.sendKeys(userList.get(0).getPostalCode());

        //country
        WebElement country = driver.findElement(By.id("country"));
        country.sendKeys(userList.get(0).getCountry());

        //home phone
        WebElement homePhoneLocated = driver.findElement(By.id("homePhone"));
        homePhoneLocated.sendKeys(homePhone);

        //mobile phone
        WebElement mobilePhoneLocated = driver.findElement(By.id("mobilePhone"));
        mobilePhoneLocated.sendKeys(mobilePhone);

        //work phone
        WebElement workPhoneLocated = driver.findElement(By.id("workPhone"));
        workPhoneLocated.sendKeys(workPhone);

        //agree terms
        driver.findElement(By.id("agree-terms")).click();

        //submit button
        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }

    @Then("^User should be displayed with the message \"([^\"]*)\"$")
    public void userShouldBeDisplayedWithTheMessage(String expectedMessage) throws Throwable {

        WebElement registrationMessage = driver.findElement(By.xpath("//span[text()='Registration Successful. Please Login.']"));
        Assert.assertTrue(registrationMessage.getText().contains(expectedMessage));


    }

    @And("^User can log in to the new account$")
    public void userCanLogInToTheNewAccount() throws  InterruptedException{
        Thread.sleep(3000);

        driver.findElement(By.id("username")).sendKeys(username);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Assertions.assertThat(driver.getTitle()).isEqualTo(this.title);
    }
}
