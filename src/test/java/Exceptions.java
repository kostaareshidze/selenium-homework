import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exceptions {

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
    public void noSuchElement() {
        try {

            driver.get(Constants.w2schools);
            WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            //correct locator or wait for the element to be visible

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementNotSelectable() {
        try {
            //ElementNotSelectable Exception
            driver.get(Constants.ironSpider);
            WebElement element = driver.findElement(By.xpath("//input[@value='red']"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // Solution: check if the element can be selected
            wait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (ElementNotSelectableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void timeOut() {
        try {
            //TimeoutException
            driver.get(Constants.w2schools);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
            wait.until(ExpectedConditions.visibilityOf(element));
            // Solution: Increase the timeout or wait for the element to be present
            WebDriverWait wait1 = new WebDriverWait(driver, 20);
            wait1.until(ExpectedConditions.visibilityOf(element));

        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void alertCheck() {
        try {
            //NoAlertPresentException
            driver.get(Constants.alertURL);
            driver.switchTo().alert();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            // Solution: Check if alert is present before switching to it
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void staleElement() {

        try {
            //StaleElementReferenceException
            driver.get(Constants.ironSpider);
            WebElement element = driver.findElement(By.xpath("//h1[@class='Heading1']"));
            //imagine element is removed or replaced
            driver.navigate().refresh();
            WebElement element1 = driver.findElement(By.xpath("//h1[@class='Heading1']"));
            element1.click();
            // Solution: Find the element again after the DOM refresh

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
            // Solution: Find the element again after the DOM refresh
        }
    }

    @Test
    public void frameEx() {
        try {
            driver.get(Constants.iFrameURL);
            driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
            // Solution: Ensure the frame exists before switching to it

        } catch (NoSuchFrameException e) {
            e.printStackTrace();
            // Solution: Ensure the frame exists before switching to it

        }
    }
    @Test
    public void noSuchSession(){
        try {
            //NoSuchSessionException
            driver.get(Constants.w2schools);
            driver.quit();
            // Try to perform an action after closing the session
            driver.get(Constants.w2schools);
            driver.findElement(By.tagName("body"));
            //Fix: restart the WebDriver session

        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void noSuchContext(){
        try {
            // Simulate NoSuchContextException
            driver.get(Constants.w2schools);
            for (String windowHandle : driver.getWindowHandles()) {
                if (windowHandle.equals("nonexistent-window")) {
                    // Switch to the context only if it exists
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

        } catch (NoSuchContextException e) {
            e.printStackTrace();

        }
    }
}
