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

public class NavigationTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.thirdLink);
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
    @Test
    public void goToSuccessStoriesAndBack() throws InterruptedException {
        WebElement successStories = driver.findElement(By.id("menu-item-217938"));
        successStories.click();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.testimonialURL);
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.thirdLink);

    }
}
