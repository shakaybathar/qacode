package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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
        String bookingType = "collection"; // collection | return
        switch (bookingType){

            case "collection" :
                collectionCls collection = new collectionCls(); // agr collectionCls static na ho tw object bnany ki need nhi
                collection.collectionBooking(driver,todayDate,env);

            case "return":
                returnAssets(driver);
        }


    }



    public static void login(WebDriver driver) throws ParseException, InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[1]/a")).click();
        String email = "shakaybassets@gmail.com";
        String password = "12345";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnLoginSubmit")).click();
    }
    public static void returnAssets (WebDriver driver) throws ParseException, InterruptedException {
        login(driver);
        int itemsToReturn = 0; // -1 for all, 0 for none
        int isAssistantNeeded = 0;
        Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li[3]/a")).click();

        List<WebElement> isAssets = driver.findElements(By.xpath("/html/body/div[1]/p[1]"));

        // Check if the div element exists
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
                default :
                    List<WebElement> assetArray = driver.findElements(By.name("assets[]"));
                    /*
                    for (WebElement assetArr : assetArray) {
                        String id = assetArr.getAttribute("id");
                        System.out.println("Element ID: " + id);
                    }
                    */
                    for (int i = 0; i < itemsToReturn; i++) {
                        driver.findElement(By.id(assetArray.get(i).getAttribute("id"))).click();
                    }
                    break;
            }
            driver.findElement(By.id("step1")).click();

            driver.findElement(By.className("selected-reason")).click();
            driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[2]/div/div/div/ul/li[2]")).click();
            switch (isAssistantNeeded){
                case 0 -> driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[4]/div[2]/div/label")).click();
                case 1 -> driver.findElement(By.xpath("/html/body/form/fieldset[2]/div/div[1]/div/div/div[4]/div[1]/div/label")).click();
            }




        }
    }
}

