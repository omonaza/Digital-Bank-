package Pages.amazon;

import Pages.DigitalBankLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Driver;

import java.util.List;

public class AmazonSearchPage {
    private static final Logger LOG = LogManager.getLogger(AmazonSearchPage.class.getName());

    private WebDriver driver;

    public AmazonSearchPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement bookTextToInputFiled;

    @FindBy(xpath = "//input[@value='Go']")
    public WebElement clickButton;

    //a[@class='a-link-normal a-text-normal']

    @FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
    public List<WebElement> searchResult;

    @FindBy(xpath = "//li[@id='p_n_free_shipping_eligible/15397661031']/span/a/div")
    public WebElement freeShippingButton;



}
