import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Task1 {

    //First Method
    public boolean clickWithRetries(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 5) {
            try {
                getWebDriver().findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    //Second Method
    public void clickWithDriverWaiting(WebDriver driver, Duration timeout){
        new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.id("checkoutLink")));
        driver.findElement(By.id("xpath")).click();
    }
}
