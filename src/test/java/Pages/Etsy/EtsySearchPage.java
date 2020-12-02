package Pages.Etsy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Driver;

public class EtsySearchPage {

    private WebDriver driver;

    public EtsySearchPage(){
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

  @FindBy(id = "global-enhancements-search-query")
    public WebElement etsySearchInputField;


    @FindBy(xpath = "//span[@class='wt-hide-xs wt-show-md filter-expander']")
    public WebElement allFiltersButton;

    @FindBy(xpath = "//span[@class='wt-radio__label' and contains(text(),'Anywhere')]")
    public WebElement anywhereRadioButton;

   @FindBy(xpath = "//h3[@class='wt-text-caption-title wt-pl-xs-2 wt-pr-xs-2' and contains(text(),' Ship to  ')]")
   public WebElement targetElementToMove;

  @FindBy(xpath = "//span[@class='etsy-icon wt-nudge-b-1']")
    public WebElement searchButton;

}
