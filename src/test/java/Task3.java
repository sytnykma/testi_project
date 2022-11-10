import com.codeborne.selenide.WebDriverRunner;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class Task3 {

    private void tabSwitcher() {
        if (checkIfOnlyTwoTabs())
            switchTo().window(1);
        else {
            throw new RuntimeException("New Tab was not found");
        }
    }

    private Boolean checkIfOnlyTwoTabs(){
        return getWebDriver().getWindowHandles().size() == 2;
    }
}
