import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwitchToTest {
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
    public void iFrameTest() {
        driver.get(Constants.iFrameURL);
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        WebElement inputText = driver.findElement(By.id("tinymce"));
        inputText.clear();
        inputText.sendKeys(Constants.sendKeys);
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement alignCenter =
                wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//button[@title='Align center']")));
        alignCenter.click();
    }
    @Test
    public void alertTest() {
        driver.get(Constants.alertURL);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("confirmButton")));
        actions.perform();
        WebElement clickMe = driver.findElement(By.id("alertButton"));
        clickMe.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = driver.switchTo().alert();
        wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
