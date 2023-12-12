package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitUntilUrlChanges {
    public static void waitUntilUrlChanges(WebDriver driver, final String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        int result = 0;
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if( !driver.getCurrentUrl().equals(expectedUrl) ){
                    System.out.println("condition false");
                }else {
                    System.out.println("condition true");

                }

                System.out.println("expectedUrl: " + expectedUrl );
                System.out.println("driver.getCurrentUrl(): " + driver.getCurrentUrl() );
                return null;
            }
        });
    }
}
