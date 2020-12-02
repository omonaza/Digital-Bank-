package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seleniumutils {
    private static final Logger LOG = LogManager.getLogger(Seleniumutils.class.getName());


    /**
     * Method open url in browser.
     *
     * @param url
     */
    public static void goTo(String url) {
        Driver.getDriver().get(url);
        LOG.info("Opened Website url: {}", url);
    }

    public static void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        LOG.info("Sent keys to element and text is {}", text);
    }

    /**
     * Method send text to element.
     *
     * @param element
     * @param text
     */
    public static void sendKeysWithClear(WebElement element, String text) {
        clear(element);
        element.sendKeys(text);
        LOG.info("Sent keys to element and text is {}", text);
    }

    public static void click(WebElement element) {
        element.click();
        LOG.info("Successfully clicked on element.");
    }

    public static String getText(WebElement element) {
        String result = element.getText();
        LOG.info("WebElement text is {}", result);
        return result;
    }

    public static void clear(WebElement element) {
        element.clear();
        LOG.info("WebElement cleared.");
    }
  public static boolean Selected(WebElement element){
        boolean result = element.isSelected();
        LOG.info("WebElement is selected: {} ",result);
        return result;
  }
  public static void moveToElement(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
        LOG.info("WebElement successfully moved:" );

  }

}
