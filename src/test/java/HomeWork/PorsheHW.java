package HomeWork;

import cucumber.api.java.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Driver;

import java.util.Set;

public class PorsheHW {
    WebDriver driver = Driver.getDriver();

    @After
    public void TearDown() {
        Driver.closeDriver();
        Driver.quitDriver();

    }


    @Test
    public void PorsheTest() throws InterruptedException {
        driver.navigate().to("https://www.porsche.com/usa/modelstart/");
        WebElement caymanModelS = driver.findElement(By.xpath("//span[text()='718']"));
        caymanModelS.click();
        // model s prise
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //div[@id='m982130']/div[@class='m-14-model-tile-link-overview']/div[@class='m-14-model-tile-title']/div[@class='m-14-model-price']
        WebElement pModelSPrise = driver.findElement(By.xpath("//div[@id='m982130']/div[@class='m-14-model-tile-link-overview']/div[@class='m-14-model-tile-title']/div[@class='m-14-model-price']"));
        String pModelPrice = pModelSPrise.getText().substring(6 + 1);
        wait.until(ExpectedConditions.visibilityOf(pModelSPrise));
        pModelPrice = pModelPrice.replace("$", "");
        pModelPrice = pModelPrice.replace(",", "");
        pModelPrice = pModelPrice.replace("*", "");
        // pModelPrice=pModelPrice.replace("\"\"","");

        Thread.sleep(3000);
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
        Thread.sleep(5000);
        // base prise                                       //div[text()='Base Price 718 Cayman S']/following-sibling::div[@class='pBox']/div
        String basePrise = driver.findElement(By.xpath("//section[@id='s_iccCca']/div[1]/div[3]/div[@class='cca-price']")).getText()
                .substring(1).replace(",", "");

//
        Integer pModelPrise1 = Integer.parseInt(pModelPrice);
        Integer basePrise1 = Integer.parseInt(basePrise);
////
        Assert.assertEquals(pModelPrise1, basePrise1);
        System.out.println("base Prise ---> " + basePrise);
        System.out.println("pModelSPrise---> " + pModelPrice);

        // equipmentPrise
        String equipmentPrise = driver.findElement(By.xpath("//div[@class='cca-label' and contains(text(),'Price for Equipment:')]/following-sibling::div[@class='cca-price']"))
                .getText().substring(1).replace(",", "");
        int equipmentPrise1 = Integer.parseInt(equipmentPrise);
        Assert.assertTrue(equipmentPrise1 == 0);
        System.out.println("equipmentPrise----> " + equipmentPrise);

        // totalPrise
        String totalPrise = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal']/div[text()='Total Price:*']/following-sibling::div[@class='cca-price']")).getText()
                .substring(1).replace(",", "");

        Integer totalPrise1 = Integer.parseInt(totalPrise);

        //deliveryPrise
        String deliveryPrise = driver.findElement(By.xpath("//div[contains(text(),'Delivery, Processing and Handling Fee')]/following-sibling::div[@class='cca-price']"))
                .getText().substring(1).replace(",", "");
        Integer deliveryPrise1 = Integer.parseInt(deliveryPrise);
        System.out.println("delivery prise----> " + deliveryPrise1);

        // assertion of prices
        Assert.assertTrue(basePrise1 + deliveryPrise1 == totalPrise1);

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        WebElement MiamiBluColor = driver.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']"));
        wait1.until(ExpectedConditions.elementToBeClickable(MiamiBluColor));
        js.executeScript("arguments[0].click();", MiamiBluColor);
        Assert.assertTrue(MiamiBluColor.isEnabled());
        Thread.sleep(4000);
        //price for miami blue color
        Integer miamiBluePriceInt = Integer.parseInt(driver.findElement(By.xpath("//li[@data-link-id='FJ5']")).getAttribute("data-price").substring(1).replace(",", ""));
        System.out.println("Miami Blur price---->" + miamiBluePriceInt);
        Thread.sleep(3000);

        // new equipment price and assertion of color and equipment
        Actions actions = new Actions(Driver.getDriver());
        WebElement EquipmentPrise = driver.findElement(By.xpath("//div[@class='cca-label' and contains(text(),'Price for Equipment:')]/following-sibling::div[@class='cca-price']"));
        wait.until(ExpectedConditions.visibilityOf(EquipmentPrise));
        actions.moveToElement(EquipmentPrise);
        String newEquipmentPrice = EquipmentPrise.getText().substring(1).replace(",", "");
        int newEquipmentPrice1 = Integer.parseInt(newEquipmentPrice);
        System.out.println("newEquipmentPrise----> " + newEquipmentPrice1);

        Assert.assertTrue(miamiBluePriceInt == newEquipmentPrice1);


        Thread.sleep(4000);
        // delivery price
        String deliveryPrice = driver.findElement(By.xpath("//div[contains(text(),'Delivery, Processing and Handling Fee')]/following-sibling::div[@class='cca-price']"))
                .getText().substring(1).replace(",", "");
        int intDeliveryPrice = Integer.parseInt(deliveryPrice);

        // equipment price
        String newEquPrice = driver.findElement(By.xpath("//div[@class='cca-label' and contains(text(),'Price for Equipment:')]/following-sibling::div[@class='cca-price']")).getText()
                .substring(1).replace(",", "");
        int intEquPrice = Integer.parseInt(newEquPrice);

        //  total price
        String newTotalPrice = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal']/div[text()='Total Price:*']/following-sibling::div[@class='cca-price']")).getText()
                .substring(1).replace(",", "");
        int intTotalPrice = Integer.parseInt(newTotalPrice);


        Assert.assertTrue(basePrise1 + intDeliveryPrice + intEquPrice == intTotalPrice);

       WebElement wheelSport = driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']//span[@class='wheel-option img-wrapper']"));
       wait.until(ExpectedConditions.elementToBeClickable(wheelSport));
       js.executeScript("arguments[0].click();",wheelSport);


        //price for Carrera Sport Wheels
        Integer carreraSportWheelsPrice = Integer.parseInt(driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price").substring(1).replace(",", ""));
        System.out.println("Carrera Sport Wheels price---->" + miamiBluePriceInt);
        Thread.sleep(3000);

        // assertion of wheels and color
        Assert.assertFalse(miamiBluePriceInt+ carreraSportWheelsPrice==intEquPrice);
        int result = miamiBluePriceInt+carreraSportWheelsPrice;
        int equip = intEquPrice;
        System.out.println("result--->" +result +"equipment--->" +intEquPrice);

        Thread.sleep(4000);

        //assertion of total price with wheels
        Assert.assertTrue("total price failure with wheels----> ",basePrise1+intEquPrice+intDeliveryPrice==intTotalPrice);







    }
}
