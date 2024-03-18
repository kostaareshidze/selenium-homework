import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CookiesTest {
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
    public void filterCookies(){
        driver.get(Constants.techlisticURL);
        Set<Cookie> cookies = driver.manage().getCookies();
        List<Cookie> filteredCookies =
                cookies.stream().filter(x -> x.getName().contains(Constants.active)).toList();
        for (Cookie cookie: filteredCookies) {
            Assert.assertTrue(cookie.getValue().contains(Constants.pub));
        }
    }
    @Test
    public void injectCookie(){
        driver.get(Constants.techlisticURL);
        int lengthOfCookiesPrevious = driver.manage().getCookies().size();
        driver.manage().addCookie(new Cookie("Cookie1", "Value1"));
        driver.manage().addCookie(new Cookie("Cookie2", "Value2"));
        driver.manage().addCookie(new Cookie("Cookie3", "Value3"));
        driver.manage().addCookie(new Cookie("Cookie4", "Value4"));
        driver.manage().addCookie(new Cookie("Cookie5", "Value5"));
        driver.manage().addCookie(new Cookie("Cookie6", "Value6"));
        driver.manage().addCookie(new Cookie("Cookie7", "Value7"));
        driver.manage().addCookie(new Cookie("Cookie8", "Value8"));
        driver.manage().addCookie(new Cookie("Cookie9", "Value9"));
        driver.manage().addCookie(new Cookie("Cookie10", "Value10"));
        List<Cookie> filteredCookies = driver.manage().getCookies().stream()
                .filter(cookie -> cookie.getName().startsWith("Cookie")).toList();
        for (Cookie cookie: filteredCookies) {
            System.out.println(cookie);
            driver.manage().deleteCookie(cookie);
        }
        int lengthOfCookiesNow = driver.manage().getCookies().size();
        Assert.assertEquals(lengthOfCookiesPrevious, lengthOfCookiesNow);
        //first length is before creation cookies, second is after deleting created cookies
    }
    @Test
    public void autoCompleteTest() throws InterruptedException {
        driver.get(Constants.demoURL);
        WebElement input = driver.findElement
                (By.id("searchbox"));
        input.click();
        input.sendKeys(Constants.start);
        Thread.sleep(3000);
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(3000);
        input.sendKeys(Keys.ENTER);
    }
}
