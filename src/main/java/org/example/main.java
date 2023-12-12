package org.example;

import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class main {

    public static void main(String[] args) throws InterruptedException, ParseException {

        setupChromeDriverProperty browser = new setupChromeDriverProperty();
//        WebDriver driver = browser.setupChromeDriverProperty();
        WebDriver driver = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);

        String env = "stage";
        String bookingType = "collection"; // collection | return | login
        /*getUrlWithEnvAndJobType envAndJobType = new getUrlWithEnvAndJobType();
        envAndJobType.getUrlWithEnvAndJobType(driver,env,bookingType);*/

        String email = "test.shk@bystored.com";
        String password = "12345";
        getUrlWithEnvAndJobType envAndJobType = new getUrlWithEnvAndJobType();

        switch (bookingType){

            case "login":
                driver = browser.setupChromeDriverProperty();

                envAndJobType.getUrlWithEnvAndJobType(driver,env,bookingType);

                loginUser lg = new loginUser();
                lg.login(driver,email,password);
                break;


            case "collection":

                collectionBooking collection = new collectionBooking(); // agr collectionCls static na ho tw object bnany ki need nhi

                int promo1 = 1;
                int promo2 = 2;
                int promo3 = 3;

                String fName1 = "";
                String lName1 = "";
                String email1 = "";
//                String email1 = "shakayb@nxtdevs.com";
                envAndJobType = new getUrlWithEnvAndJobType();

                for (int i = 0 ; i < 1 ; i++){
                    fName1 = "shaka70077yb_0s" + i;
                    lName1 = "loop_0s" + i;
                    email1 = fName1+"."+env+"."+lName1+"@bystored.com";
                    driver = browser.setupChromeDriverProperty();
                    envAndJobType.getUrlWithEnvAndJobType(driver,env,bookingType);
                    System.out.println("this is loop # " + i);
                    collection.collectionBooking(driver,fName1,lName1,email1,promo1,todayDate,env,bookingType);
                    driver.quit();
                }
                break;

            case "return":
                email = "assetswo.subsshk@bystored.com"; // shakaybassets@bystored.com | returnOldFeeshkQa@bystored.com stage | smstest1.dev@yopmail.com dev | assetswo.subsshk@bystored.com dev
                password = "12345";
                int itemsToReturn = 1; // -1 for all, 0 for none , 1 for partial
                int isAssistantNeeded = 0;
                String addressType = "selected"; // manual , loqate , selected
                int restriction = 0;
                int parking = 0; // 0 = immediate parking | 1 = 100 meters from enterance
                int stairs = 0; // -1 = lift | 0 = ground floor | 1 = 7th floor

                returnBooking rb = new returnBooking();
                driver = browser.setupChromeDriverProperty();
                rb.returnAssets(driver,email,password,todayDate,bookingType, itemsToReturn,isAssistantNeeded,addressType,restriction,parking,stairs);
                break;
        }
//        Thread.sleep(10000);
//        driver.quit();
    }
    /*private static WebDriver setupChromeDriverProperty() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\chromeDriver\\chromedriver_v119.exe");
        WebDriver driver = new ChromeDriver();
        Thread.sleep(2000);
        return driver;
    }*/
}

