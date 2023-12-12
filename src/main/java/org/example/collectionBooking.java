package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.text.ParseException;
import java.util.Properties;
/*import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;*/

public class collectionBooking {
    public void collectionBooking (WebDriver driver, String fName,String lName,String email,int promo,String todayDate, String env,String bookingType) throws ParseException, InterruptedException {

        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("inputField.properties")) {
            properties.load(input);
            int sqFt = 9;

            //        String fName = "assetswo";
            //        String lName = "subsshk";
            //        String email = fName+"."+env+"."+lName+"@bystored.com";
            String postCode = "Sw6 1YX";
            String manAddress = "testing manual address";
            String phone = "7894561230";
            int setPassword = 1;

            /*Map<String, Object> res = calculateMonths.monthsBetween(driver,todayDate,"collection");
            long monthsBetween = (Long) res.get("monthsBetween");*/

            driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[2]/a")).click();

            switch (sqFt) {
                case 9 -> sqFt = 1;
                case 12 -> sqFt = 2;
                case 15 -> sqFt = 3;
                case 25 -> sqFt = 4;
                case 50 -> sqFt = 5;
                case 75 -> sqFt = 6;
                case 100 -> sqFt = 7;
            }

            // selecting storage unit / sqft
            driver.findElement(By.xpath("/html/body/section[1]/div/div/div[3]/div[1]/div/div[" + sqFt + "]/div/label")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("step1-cta-next")).click(); // storage length selected
            Thread.sleep(2000);


            // selecting storage length / promotions
            switch (promo) {
                case 1 -> promo = 1;
                case 3 -> promo = 2;
                case 6 -> promo = 3;
            }
            driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/ul/li[" + promo + "]/label")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("step2-cta-next")).click(); // storage length selected
            Thread.sleep(2000);

            // enter pick-up details / customer info
            //        driver.findElement(By.id("postCode")).clear();
            driver.findElement(By.id("postCode")).sendKeys(postCode);
            driver.findElement(By.id("enter-address-manually")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("addressManually")).sendKeys(manAddress);
            driver.findElement(By.id("fname")).sendKeys(fName);
            driver.findElement(By.id("lname")).sendKeys(lName);
            //        driver.findElement(By.id("email")).sendKeys("talib@nxtdevs.com");
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("phone")).sendKeys(phone);
            Thread.sleep(2000);
            driver.findElement(By.id("step3-cta-next")).click(); // user info entered
            Thread.sleep(2000);

            String searchText = "Looks like you already have an account";
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + searchText + "')]"));

            if ( element.isDisplayed() ) {
                System.out.println("Email already exists in our database");
                Thread.sleep(5000);
            } else {
                System.out.println("New Email!");
            }


            calculateMonths.monthsBetween(driver, todayDate, "collection");
            System.out.println("Date selected!");
            //        select pick-up window
            driver.findElement(By.id("arrival-load")).click(); // Load More
            driver.findElement(By.xpath("//*[@id=\"generic-slot\"]/ul/li[1]/label")).click(); // 09:00 - 18:00
            //        driver.findElement(By.xpath("/html/body/section[4]/div[2]/div[2]/div/ul/li[5]/label")).click(); // 16:00 - 17:00
            System.out.println("Time slot  selected!");
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/section[5]/div/div[2]/a/button")).click(); // continue button
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"section6\"]/div/ul/li[2]/label")).click(); // Yes, help me pack
            Thread.sleep(2000);
//            driver.findElement(By.id("emailQuote")).click(); // Email Quote
//            System.out.println("email quote done");
            driver.findElement(By.id("continueReservation")).click(); // Create booking
            System.out.println("order done");
            Thread.sleep(5000);
            Thread.sleep(5000);
            Thread.sleep(5000);
            driver.get("https:/" + env + "bystored.com/myStored/home");
            Thread.sleep(5000);
            waitUntilUrlChanges waiting = new waitUntilUrlChanges();
            waiting.waitUntilUrlChanges(driver, "https://" + env + "bystored.com/myStored/home");




            /*switch (env) {  // Quote's Ready To Book
                case "stage":
                    driver.findElement(By.xpath("/html/body/div[10]/div/div/div[3]/button[1]")).click();
                    break;
                case "dev":
                    driver.findElement(By.xpath("/html/body/div[9]/div/div/div[3]/button[1]")).click();
                    break;
            }
            Thread.sleep(2000);*/
//            Thread.sleep(2000);
//            driver.quit();
            /*driver.get("https://" + env + ".bystored.com/myStored/home");

            switch (setPassword) {  // Quote's Ready To Book
                case 0:
                    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/div[1]/button")).click(); // close password set modal
                    break;

                case 1:
                    driver.findElement(By.id("password")).sendKeys("12345");
                    driver.findElement(By.id("confirmPassword")).sendKeys("12345");
                    driver.findElement(By.xpath("profileSubmit")).click(); // set password
                    break;
            }*/
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println("Exception occurred for user "+ fName + "and exception is " + e);
        }
    }
}
