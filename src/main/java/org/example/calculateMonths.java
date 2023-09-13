package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class calculateMonths {

    public static void monthsBetween(WebDriver driver, String todayDate, String jobType) throws ParseException, InterruptedException {

        Map<String, Object> result = new HashMap<>();
        String  bookingDate = "2023-12-05";
        String dayName = nextWorkingDay.getNextWorkingDay(bookingDate);
        String jobDate = dayName.substring(8, 10);
        bookingDate = dayName;
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(todayDate)),
                YearMonth.from(LocalDate.parse(bookingDate))
        );
        String nextMonth = "";
        System.out.println("jobType: " + jobType);
        switch (jobType){
            case "collection"   -> nextMonth = "/html/body/section[4]/div[1]/div/div[2]/div/div/a[2]";
            case "return"       -> nextMonth = "/html/body/div[17]/div/a[2]";

        }
        System.out.println("nextMonth: " + nextMonth);

        for(int i = 1; i <= monthsBetween; i++){
            driver.findElement(By.xpath(nextMonth)).click(); // change calendar month
        }

        WebElement e = driver.findElement(By.xpath("//*[text()=" + jobDate +"]"));
        String date = e.getText();
        driver.findElement(By.linkText(date)).click();
        Thread.sleep(10000);
    }
}
