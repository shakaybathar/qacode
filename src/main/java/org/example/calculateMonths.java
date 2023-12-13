package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class calculateMonths {

    public static void monthsBetween(WebDriver driver, String todayDate, String jobType) throws ParseException, InterruptedException {


        String  bookingDate = "2023-12-30";
        String dayName = nextWorkingDay.getNextWorkingDay(bookingDate);
        String jobDate = dayName.substring(8, 10);
        bookingDate = dayName;
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(todayDate)),
                YearMonth.from(LocalDate.parse(bookingDate))
        );
        String nextMonth = "";
        switch (jobType){
            case "collection":
                nextMonth = "/html/body/section[4]/div[1]/div/div[2]/div/div/a[2]";
                break;

            case "return":
                driver.findElement(By.id("returnDate")).click(); // open return calendar
                Thread.sleep(2000);
                nextMonth = "/html/body/div[15]/div/a[2]/span"; // click on next
                break;

        }

        for(int i = 1; i <= monthsBetween; i++){
            driver.findElement(By.xpath(nextMonth)).click(); // change calendar month
        }

        WebElement e = driver.findElement(By.xpath("//*[text()=" + jobDate +"]"));
        String date = e.getText();
        driver.findElement(By.linkText(date)).click();
        Thread.sleep(10000);
    }

    public static String getToday(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);
        return todayDate;
    }
    public static String getNow(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = now.format(dtf);
        return dateTime;
    }
}
