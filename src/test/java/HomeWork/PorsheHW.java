package HomeWork;

import cucumber.api.java.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Driver;

public class PorsheHW {
    WebDriver driver = Driver.getDriver();

    @After
    public void TearDown() {
        Driver.getDriver().close();
    }


    @Test
    public void PorsheTest() throws InterruptedException {
        driver.navigate().to("https://www.porsche.com/usa/modelstart/");
        WebElement caymanModelS = driver.findElement(By.xpath("//span[text()='718']"));
        caymanModelS.click();
        String pModelSPrice = driver.findElement(By.xpath("//div[text()='From $ 71,900*']")).getText();

        driver.findElement(By.xpath("//div[text()='From $ 71,900*']")).click();


        // WebElement basePrise=driver.findElement(By.xpath("//div[@class='row highlighted priceTotal']"));
//Assert.assertFalse(pModelSPrice.equals(basePrise));
    }
}
