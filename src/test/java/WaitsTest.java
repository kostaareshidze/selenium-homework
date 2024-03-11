import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitsTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
    @Test
    public void waitForDisappearance() {
        driver.get(Constants.internetURL);
        WebElement enable = driver.findElement(By.xpath("//button[@onclick='swapInput()']"));
        enable.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        Assert.assertEquals(enable.getText(), Constants.disable);
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        input.sendKeys(Constants.access);
    }
    @Test
    public void waitForText() {
        driver.get(Constants.democaURL);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement elementToScroll = driver.findElement(By.xpath("//li[@class='btn btn-light active']"));
        Actions actions = new Actions(driver);
        WebElement start = driver.findElement(By.id("startStopButton"));
        actions.moveToElement(elementToScroll);
        actions.perform();
        start.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@role='progressbar']"), Constants.maxValue));
        System.out.println(Constants.maxValue);
    }

}
