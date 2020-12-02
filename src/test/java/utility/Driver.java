package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    /**
     * 1. Declare constructor of class as private so that no one instantiate class outside of it.
     * 2. Declare a private static reference variable of Webdriver. Static is needed to make it available globally.
     * 3. Declare a static method getDriver() with returns an instance of a ChromeDriver type which should check if instance is already instantiated once.
     * 4. Make sure getDriver() method returns maximized browser window and add implicit wait
     */
    private static WebDriver driver;
    static String browser = "chrome";
    private Driver() { //private constractor means Singleton Design Pattern
    }
    public static WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}