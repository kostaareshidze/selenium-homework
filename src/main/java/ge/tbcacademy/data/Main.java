package ge.tbcacademy.data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void universalSelector(Object element, String visibleText) {
        if (element instanceof Select) {
            Select select = (Select) element;
            select.selectByVisibleText(visibleText);
        } else if (element instanceof WebElement) {
            WebElement webElement = (WebElement) element;
            webElement.click();
        }
        System.out.println("I AM A CHANGE MEANWHILE");
    }
}
