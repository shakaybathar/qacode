package org.example;

import org.openqa.selenium.WebDriver;

public class tearDown {

//    @AfterMethod
    public void tearDown(WebDriver driver) {

        if (driver != null) {
            driver.quit();
        }
    }

}
