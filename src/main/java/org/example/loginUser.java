package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class loginUser {
    public void login(WebDriver driver,String email,String password) throws ParseException, InterruptedException {
        try{
            driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[1]/a")).click();
            driver.findElement(By.id("email")).sendKeys(email);
//            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("btnLoginSubmit")).click();

            Thread.sleep(4000);
            String isLoginError = String.valueOf(driver.findElements(By.id("loginErrorMsg")).size());
            if(driver.findElements(By.id("loginErrorMsg")).size() != 0){
                System.out.println("Login Failed");
            }else{
                System.out.println("Login Successful");
            }
        }catch (Exception e){
            System.out.println("Exception found in Login.");
        }
    }
}
