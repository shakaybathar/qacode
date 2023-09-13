package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class main {

    public static void main(String[] args) throws InterruptedException, ParseException {


//        System.setProperty("webdriver.chrome.driver","C:\\chromeDriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C:\\chromeDriver\\chromedriver_v107.exe");
        WebDriver driver = new ChromeDriver();
        Thread.sleep(2000);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);


        String env = "stage";
        driver.get("https://bystored:bystored2020@" + env + ".bystored.com/");
        Thread.sleep(2000);
        String bookingType = "return"; // collection | return

        switch (bookingType){

            case "collection":
                collectionBooking collection = new collectionBooking(); // agr collectionCls static na ho tw object bnany ki need nhi
                collection.collectionBooking(driver,todayDate,env,bookingType);
                break;

            case "return":
                returnBooking rb = new returnBooking();
                rb.returnAssets(driver,todayDate,bookingType);
                break;

        }
    }
}

