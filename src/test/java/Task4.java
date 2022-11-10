import com.codeborne.selenide.Condition;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.Wait;

public class Task4 {

    //I better use Selenide methods if its possible in framework, cause it has inbox waiters to check expected element conditions

    private void waitForElement(){
        $x("//someButton").shouldBe(visible, enabled).click();
        //Here I will be wait for element that should be changed (text, visibility etc)
        $x("//someElement").shouldBe(appear).shouldHave(text("new text on element"));
    }

    //Or in selenium use such waiter
    private void waitInSelenium(WebDriver driver, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf($x("element"))));
    }
}
