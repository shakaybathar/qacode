package org.example;

import org.openqa.selenium.WebDriver;

public class getUrlWithEnvAndJobType {
    public static void getUrlWithEnvAndJobType(WebDriver driver, String env, String bookingType) throws InterruptedException {

        env = "stage";
        bookingType = "collection"; // collection | return | login
        driver.get("https://bystored:bystored2020@" + env + ".bystored.com/");
        Thread.sleep(2000);

    }
}
