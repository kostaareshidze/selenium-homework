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

public class WebElementTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.secondLink);
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
    @Test
    public void dimensionsTest(){
        WebElement firstRect = driver.findElement(By.xpath("//div[@id='columns']/div[@id='column-a']"));
        WebElement secondRect = driver.findElement(By.xpath("//div[@id='columns']/div[@id='column-b']"));
        Assert.assertEquals(firstRect.getSize().height, secondRect.getSize().height);
        Assert.assertEquals(firstRect.getAttribute("draggable"), "true");
        Assert.assertEquals(secondRect.getAttribute("draggable"), "true");

    }
    @Test
    public void linkTest(){
        WebElement link = driver.findElement(By.xpath("//a[@target='_blank']"));
        Assert.assertEquals(link.getAttribute("href"), Constants.selenium);
    }
}
