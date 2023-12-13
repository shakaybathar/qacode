package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginTest {

    private final WebDriver driver;

    // Default no-argument constructor
    public loginTest() {
        this.driver = null;
    }

    // Constructor to receive the WebDriver instance
    public loginTest(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeClass
    public static void setUp() {
        System.out.println("Before");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Tear down");
    }

    @Test
    public void loginCases() throws Exception {

        testLogin("", "");
        testLogin("invalidUsername", "12345");
        testLogin("shakayb@nxtdevs.com", "invalidPassword");
        testLogin("invalidEmail", "invalidPassword");
      testLogin("shakayb@nxtdevs.com", "12345");

    }

    public void testLogin(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        if (!driver.getCurrentUrl().equals("https://stage.bystored.com/login")) {
            driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[3]/ul/li[1]/a")).click();
        }

        WebElement locatorEmail = driver.findElement(myLocators.email);
        WebElement locatorPass = driver.findElement(myLocators.password);
        WebElement submitLogin = driver.findElement(myLocators.submitLogin);

        int emailSize = locatorEmail.getAttribute("value").length();
        int passSize = locatorPass.getAttribute("value").length();

        if( emailSize >  0 || passSize > 0){
            locatorEmail.clear();locatorPass.clear();
        }

        locatorEmail.sendKeys(email);
        locatorPass.sendKeys(password);
        submitLogin.click();
        Thread.sleep(2000);

        WebElement loginError = driver.findElement(myLocators.loginError);
        String errorMessage =  loginError.getText();
        System.out.println("loginErrorSize: " + errorMessage.length());

        if ( errorMessage.length() > 0 ) {

            System.out.println("Login Failed: " + errorMessage);
        } else {

            Thread.sleep(4000);
            WebElement locatorWelcome = driver.findElement(myLocators.welcome);

            if( locatorWelcome.isDisplayed() ){
                System.out.println("Login Successful");
            }else{
                System.out.println("Unfortunately error found while landing on my pages");
            }

        }
    }
}

