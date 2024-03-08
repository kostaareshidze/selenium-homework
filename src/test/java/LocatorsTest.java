import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;


public class LocatorsTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
    @Test
    public void unorderedListTest(){
        driver.get(Constants.jqueryuiURl);
        WebElement path = driver.findElement(By.xpath(Constants.path));

        List<WebElement> listOfWebElements = path.findElements(By.tagName("li"));

        listOfWebElements.stream()
                .filter(element -> element.getText().contains("o"))
                .parallel()
                .forEach(element -> {
                    WebElement newElement = element.findElement(By.tagName("a"));
                    String href = newElement.getAttribute("href");
                    if (!href.contains("animate")) {
                        System.out.println(href);
                    }
                });
    }
    @Test
    public void buttonsTest(){
        driver.get(Constants.buttonDriverURL);
        WebElement addElement = driver.findElement(By.xpath("//button"));
        addElement.click();
        addElement.click();
        addElement.click();
        WebElement delete = driver.findElement(By.xpath(Constants.deletePath));
        Assert.assertEquals(delete.getAttribute("class"), Constants.manual);

        WebElement parentDiv  = driver.findElement(By.cssSelector(Constants.css));
        List<WebElement> list = parentDiv.findElements(By.tagName("button"));
        WebElement newElem = list.get(list.size() - 1);
        Assert.assertEquals(newElem.getAttribute("onclick"), "deleteElement()");
    }
    @Test
    public void challengingDomTest(){
        driver.get(Constants.challengingURL);
        WebElement element = driver.findElement(By.xpath(Constants.relativeXpath));
        System.out.println(element.getText());
    }
}
