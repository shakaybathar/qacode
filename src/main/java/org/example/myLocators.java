package org.example;

import org.openqa.selenium.By;

public class myLocators {


    public static By bookOnlineCta = By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[2]/a");
    public static By SqFt = By.xpath("/html/body/section[1]/div/div/div[3]/div[1]/div/div[9]/div/label");

    // Example 1: Using ID locator
    public static By clickSqFt = By.id("step1-cta-next");
    public static By postCode = By.id("postCode");

    public static By clickIsManual = By.id("enter-address-manually");

    public static By addressManually = By.id("addressManually");

    public static By fname = By.id("fname");

    public static By lname = By.id("lname");

    public static By email = By.id("email");
    public static By password = By.id("password");

    public static By phone = By.id("phone");

    public static By submitUserInfo = By.id("step3-cta-next");

    // Example 2: Using Name locator
    public static By ts9_6 = By.xpath("//*[@id=\"generic-slot\"]/ul/li[1]/label");

    // Example 3: Using XPath locator
    public static By searchInputByXPath = By.xpath("//input[@name='q']");

    // Example 4: Using CSS Selector locator
    public static By emailByCssSelector = By.cssSelector("input[type='email']");

    public static By submitLogin = By.id("btnLoginSubmit");
    public static By loginError = By.id("loginErrorMsg");
    public static By welcome = By.className("welco6e");


}
