package HomeWork;

import cucumber.api.java.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Driver;

import java.util.Set;

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
        // model s prise
        String pModelSPrise = driver.findElement(By.xpath("//div[text()='From $ 71,900*']")).getText().replace("From $ ", "");
        pModelSPrise = pModelSPrise.replace("*", "");
        pModelSPrise = pModelSPrise.replace(",", "");

        driver.findElement(By.xpath("//img[@alt='Porsche 718 Cayman S']")).click();
        String currentWindow = driver.getWindowHandle();

        //I also need to get the ids of all open windows - currently 2
        Set<String> allWindowHandles = driver.getWindowHandles();//2 uniques ids

        for (String windowHandle : allWindowHandles) {
            //check if the window handle is not a current window - then switch to it
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }

        WebElement dropDownButton = driver.findElement(By.xpath("//div[text()='Price for Equipment:']/.//span"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", dropDownButton);
        // base prise
        String basePrise = driver.findElement(By.xpath("//div[@class='cca-price' and text()='$71,900'][1]")).getText().replace("$", "");
        basePrise = basePrise.replace(",", "");


        Double pModelPrise1 = Double.parseDouble(pModelSPrise);
        Double basePrise1 = Double.parseDouble(basePrise);

        Assert.assertTrue(pModelPrise1==basePrise1);
        System.out.println("base Prise ---> " + basePrise);
        System.out.println("pModelSPrise---> " + pModelSPrise);

        String equipmentPrise = driver.findElement(By.xpath("//div[@class='cca-price' and text()='$0']")).getText().replace("$", "");
        int equipmentPrise1 = Integer.parseInt(equipmentPrise);
        Assert.assertTrue(equipmentPrise1 == 0);
        System.out.println("equipmentPrise----> " + equipmentPrise);

        String totalPrise = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal']//div[text()='$73,250']")).getText().replace("$", "");
        totalPrise = totalPrise.replace(",", "");
        Double totalPrise1 = Double.parseDouble(totalPrise);

        String deliveryPrise = driver.findElement(By.xpath("//div[@class='cca-price' and text()='$1,350']")).getText().replace("$", "");
        deliveryPrise = deliveryPrise.replace(",", "");

        Double deliveryPrise1 = Double.parseDouble(deliveryPrise);

        Assert.assertTrue(basePrise1 + deliveryPrise1 == totalPrise1);

        WebDriverWait wait = new WebDriverWait(driver,10);
       WebElement MiamiBluColor = driver.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']"));
       wait.until(ExpectedConditions.elementToBeClickable(MiamiBluColor));
        js.executeScript("arguments[0].click();", MiamiBluColor);
       Assert.assertTrue(MiamiBluColor.isEnabled());


       String MiamiBluPrise = driver.findElement(By.xpath("//li[@id='s_exterieur_x_FJ5']")).getText();

        System.out.println("MiamiBluPrise----> " +MiamiBluPrise);



    }
}
