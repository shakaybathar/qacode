package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class loginUser {
    public void login(WebDriver driver) throws ParseException, InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[1]/a")).click();
        String email = "shakaybassets@gmail.com";
        String password = "12345";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnLoginSubmit")).click();
    }
}
