package ge.tbcacademy.data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Main {
    public static void universalSelector(Object element, String visibleText) {
        if (element instanceof Select) {
            Select select = (Select) element;
            select.selectByVisibleText(visibleText);
        } else if (element instanceof WebElement) {
            WebElement webElement = (WebElement) element;
            webElement.click();
        }


        System.out.println("I AM A CHANGE MEANWHILE!");


    }
    public static void dynamicallyIndex(WebDriver driver, String string) {
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        boolean isSearched = false;
        for (WebElement table : tables) {
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> singles = row.findElements(By.tagName("td"));

                for (WebElement single : singles) {
                    if (single.getText().equals(string)) {
                        System.out.println(string);
                        isSearched = true;
                        break;
                    }
                }
                if (isSearched) {
                    break;
                }
            }
            if (isSearched) {
                break;
            }
        }
        if (!isSearched) {
            System.out.println("Warning: No such element found.");
        }

    }
}
