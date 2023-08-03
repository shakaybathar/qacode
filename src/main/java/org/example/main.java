package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class main {

    public static void main(String[] args) throws InterruptedException, ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);

        String env = "stage";
        int sqFt = 9;
        int promo = 1;
        String fName = "chkDay";
        String lName = "byshk";
        String email = fName + "." + lName +"@bystored.com";
        String postCode = "Sw6 1YX";
        String manAddress = "testing manual address";
        String phone = "7894561230";
        String  bookingDate = "2023-12-05";
        int setPassword = 0;

        String dayName = getNextWorkingDay(bookingDate);
        String jobDate = dayName.substring(8, 10);
        bookingDate = dayName;
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(todayDate)),
                YearMonth.from(LocalDate.parse(bookingDate))
        );

        System.setProperty("webdriver.chrome.driver","C:\\chromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://bystored:bystored2020@" + env + ".bystored.com/booking");
        Thread.sleep(2000);


        /*driver.findElement(By.id("postcode")).sendKeys("SW6 1YX");
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div[2]/button")).click();
        Thread.sleep(2000);*/

        switch (sqFt){
            case 9   -> sqFt = 1;
            case 12  -> sqFt = 2;
            case 15  -> sqFt = 3;
            case 25  -> sqFt = 4;
            case 50  -> sqFt = 5;
            case 75  -> sqFt = 6;
            case 100 -> sqFt = 7;
        }

        // selecting storage unit / sqft
        driver.findElement(By.xpath("/html/body/section[1]/div/div/div[3]/div[1]/div/div[" + sqFt + "]/div/label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("step1-cta-next")).click(); // storage length selected
        Thread.sleep(2000);


        // selecting storage length / promotions
        switch (promo){
            case 1   -> promo = 1;
            case 3  -> promo = 2;
            case 6  -> promo = 3;
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
        driver.findElement(By.id("step3-cta-next")).click(); // storage length selected
        Thread.sleep(2000);

//        select pick up date

        for(int i = 1; i <= monthsBetween; i++){
            driver.findElement(By.xpath("/html/body/section[4]/div[1]/div/div[2]/div/div/a[2]")).click(); // change calendar month

        }
        WebElement e = driver.findElement(By.xpath("//*[text()=" + jobDate +"]"));
        String date = e.getText();
        driver.findElement(By.linkText(date)).click();
        Thread.sleep(10000);
//        driver.quit();


//        select pick-up window
        driver.findElement(By.id("arrival-load")).click(); // Load More
        driver.findElement(By.xpath("//*[@id=\"generic-slot\"]/ul/li[1]/label")).click(); // 09:00 - 18:00
//        driver.findElement(By.xpath("/html/body/section[4]/div[2]/div[2]/div/ul/li[5]/label")).click(); // 16:00 - 17:00
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/section[5]/div/div[2]/a/button")).click(); // continue button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"section6\"]/div/ul/li[2]/label")).click(); // Yes, help me pack
        Thread.sleep(2000);
        driver.findElement(By.id("emailQuote")).click(); // Email Quote
        Thread.sleep(5000);

        switch (env) {  // Quote's Ready To Book
            case "stage":
                driver.findElement(By.xpath("/html/body/div[10]/div/div/div[3]/button[1]")).click();
                break;
            case "dev":
                driver.findElement(By.xpath("/html/body/div[9]/div/div/div[3]/button[1]")).click();
                break;
        }
        Thread.sleep(2000);
        Thread.sleep(2000);

        driver.get("https://" + env + ".bystored.com/myStored/home");

        switch (setPassword) {  // Quote's Ready To Book
            case 0:
                driver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/div[1]/button")).click(); // close password set modal
                break;
            case 1:


                driver.findElement(By.id("password")).sendKeys("12345");
                driver.findElement(By.id("confirmPassword")).sendKeys("12345");
                driver.findElement(By.xpath("profileSubmit")).click(); // set password
                break;
        }
    }



    public static String getNextWorkingDay (String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekString;
        switch (dayOfWeek) {
            case 1:
                calendar.add(Calendar.DATE, 1);
                date1 = calendar.getTime();
                String changedBookingDate = sdf.format(date1);
                date = changedBookingDate;
                dayOfWeekString = "Sunday";
                break;
            case 2:
                dayOfWeekString = "Monday";
                break;
            case 3:
                dayOfWeekString = "Tuesday";
                break;
            case 4:
                dayOfWeekString = "Wednesday";
                break;
            case 5:
                dayOfWeekString = "Thursday";
                break;
            case 6:
                dayOfWeekString = "Friday";
                break;
            case 7:
                dayOfWeekString = "Saturday";
                break;
            default:
                dayOfWeekString = "error";
        }
        System.out.println("dayOfWeekString: " + dayOfWeekString);
        return date;
    }

}

