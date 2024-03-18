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


        System.out.println("ORIGINAL CODE");

    }

    public static String dynamicallyIndex(WebDriver driver, String header, String element) {
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        for (WebElement table : tables) {
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            int columnIndex = -1;
            WebElement headerRow = rows.get(0);
            List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
            for (int j = 0; j < headerCells.size(); j++) {
                if (headerCells.get(j).getText().equals(header)) {
                    columnIndex = j;
                    break;
                }
            }
            if (columnIndex == -1)
                continue;
            for (int i = 1; i < rows.size(); i++) {
                WebElement row = rows.get(i);
                List<WebElement> singles = row.findElements(By.tagName("td"));
                if (singles.get(0).getText().equals(element)) {
                    return singles.get(columnIndex).getText();
                }
            }
        }
        return "Warning: element not found";
    }


}

