package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class setupChromeDriverProperty {
    static WebDriver setupChromeDriverProperty() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver_v119.exe");
        WebDriver driver = new ChromeDriver();
        Thread.sleep(2000);
        return driver;
    }
}