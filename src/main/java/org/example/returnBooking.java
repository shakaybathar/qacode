package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.List;

public class returnBooking {
    public static void returnAssets (WebDriver driver,String email,String password, String todayDate, String bookingType, int itemsToReturn, int isAssistantNeeded, String addressType, int restriction, int parking, int stairs) throws ParseException, InterruptedException {

        loginUser user = new loginUser();
        user.login(driver,email,password);

        Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li[3]/a")).click();

        List<WebElement> isAssets = driver.findElements(By.xpath("/html/body/div[1]/p[1]"));
        Thread.sleep(1000);

        // Check if assets are uploaded or not
        if (isAssets.size() > 0) {
            String noAssets = driver.findElement(By.xpath("/html/body/div[1]/p[1]")).getText();
            System.out.println(noAssets);
        }else{
            switch (itemsToReturn){

                case -1 :
                    driver.findElement(By.id("select-all")).click();
                    break;
                case 0 :
                    System.out.println("no items selected for return");
                    driver.quit();
                    break;
                case 1:
                    List<WebElement> assetArray = driver.findElements(By.name("assets[]"));
                    for (int i = 0; i < itemsToReturn; i++) {
                        driver.findElement(By.id(assetArray.get(i).getAttribute("id"))).click();
                    }
                    break;
            }
            driver.findElement(By.id("step1")).click();
            Thread.sleep(2000);

//            step2 Information
            driver.findElement(By.id("selected-reason")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[2]/div/div/div/ul/li[2]")).click();
            Thread.sleep(2000);
            switch (isAssistantNeeded){
                case 0 -> driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[4]/div[2]/div/label")).click();
                case 1 -> driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[4]/div[1]/div/label")).click();
            }
            Thread.sleep(3000);
            driver.findElement(By.id("step2")).click();

            addressAndParkingInfo(driver,addressType,restriction,parking,stairs);
            returnDateAndTime(driver,todayDate,bookingType);
        }
    }

    public static void addressAndParkingInfo(WebDriver driver, String addressType, int restriction, int parking, int stairs) throws InterruptedException { // step3

        switch (addressType){
            case "selected":
                driver.findElement(By.xpath("//*[@id=\"select2-existing-addresses-container\"]/span")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]")).click();
                break;

            case "loqate":
                driver.findElement(By.id("search-address")).sendKeys("SW6 1yx");
                Thread.sleep(5000);
                driver.findElement(By.xpath("/html/body/form/fieldset[3]/div/div[1]/div[1]/div[1]/div[2]/div[2]/ul/li")).click();

            case "manual":
                driver.findElement(By.id("show-address-form")).click();
                driver.findElement(By.id("postcode")).sendKeys("SW6 1YX");
                driver.findElement(By.id("address1")).sendKeys("lineOne");
                driver.findElement(By.id("address2")).sendKeys("lineTwo");
        }
        Thread.sleep(2000);

        driver.findElement(By.id("restricted-access")).click();
        switch (restriction) {
            case 0 -> driver.findElement(By.cssSelector("[data-id='Yes']")).click();
            case 1 -> driver.findElement(By.cssSelector("[data-id='No']")).click();
        }

        driver.findElement(By.id("selectedParkingText")).click();
        switch (parking) {
            case 0 -> driver.findElement(By.cssSelector("[data-id='PARKING_0']")).click();
            case 1 -> driver.findElement(By.cssSelector("[data-id='PARKING_100']")).click();
        }

        driver.findElement(By.id("selectedFloorText")).click();
        switch (stairs) {
            case -1 -> driver.findElement(By.cssSelector("[data-id='FLOOR_LIFT']")).click();
            case 0 -> driver.findElement(By.cssSelector("[data-id='FLOOR_0']")).click();
            case 1 -> driver.findElement(By.cssSelector("[data-id='FLOOR_7']")).click();
        }
        driver.findElement(By.id("parking-info")).sendKeys("This is testing parking info by automated script");
        Thread.sleep(3000);
        driver.findElement(By.id("step3")).click();
    }

    public static void returnDateAndTime(WebDriver driver, String todayDate, String bookingType) throws InterruptedException, ParseException { // step4
        driver.findElement(By.id("returnDate")).click();
        calculateMonths.monthsBetween(driver, todayDate, bookingType);
        Thread.sleep(5000);
        driver.findElement(By.id("timeSlotType")).click();
        driver.findElement(By.xpath("/html/body/form/fieldset[4]/div/div[1]/div/div[1]/div[2]/div/div/div/div/ul/li")).click();
        driver.findElement(By.id("agreement")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("paynow-return")).click(); // create return booking

    }
}
