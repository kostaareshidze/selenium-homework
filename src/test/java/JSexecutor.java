import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JSexecutor {


    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
    @Test
    public void deleteTest() {
        driver.get(Constants.webDriverUni);
        WebElement lastItem = driver.findElement(By.cssSelector("ul li:last-child"));
        Actions actions = new Actions(driver);
        actions.moveToElement(lastItem).perform();
        int count = driver.findElements(By.cssSelector("ul li")).size();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement deleteButton = (WebElement) js.executeScript(
                "return arguments[0].querySelector('.fa.fa-trash');", lastItem);
        js.executeScript("arguments[0].click();", deleteButton);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int lastCount = driver.findElements(By.cssSelector("ul li")).size();
        System.out.println(count);
        System.out.println(lastCount);
        if (count > lastCount)
            System.out.println("List item is deleted");
        else
            System.out.println("There was no deletion");

    }
    @Test
    public void scrollTest(){
        driver.get(Constants.scroll);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//div[@class='p-4 overflow-y-auto']")));
        Map<String, String> codeExamples = new HashMap<>();
        List<WebElement> codeSections = driver.findElements(By.
                xpath("//div[@class='p-4 overflow-y-auto']"));
        for (int i = 0; i < codeSections.size(); i++) {
            String code = codeSections.get(i).getText();
            codeExamples.put("Section " + (i + 1), code);
        }
        System.out.println("Number of code examples: " + codeExamples.size());
        Map<String, String> tutorialLinks = new HashMap<>();
        List<WebElement> links = driver.findElements(By.xpath("//div[@id='PopularPosts1']//a"));
        for (WebElement link : links) {
            String label = link.getText().trim();
            String href = link.getAttribute("href");
            tutorialLinks.put(label, href);
        }
        System.out.println("\nTutorial Links:");
        for (Map.Entry<String, String> entry : tutorialLinks.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    @Test
    public void anotherScrollTest(){
        driver.get(Constants.webScroll);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#zone2').scrollIntoView(true);");

        WebElement leftBox = driver.findElement(By.cssSelector("#zone2"));
        String actualText = (String) js.executeScript("return arguments[0].innerText;", leftBox);
        if (actualText.contains(Constants.expectedText))
            System.out.println("Passed!");
        else
            System.out.println("Failed!");
    }

}