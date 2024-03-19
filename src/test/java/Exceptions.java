import ge.tbcacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Exceptions {

    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Invalid browser specified in testng.xml");
        }
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void noSuchElement() {
        try {
            //NoSuchElement Exception
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
            WebDriverWait wait = new WebDriverWait(driver, 5);
            WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            // Solution: Increase the timeout or wait for the element to be present
        } catch (NoSuchSessionException | TimeoutException e) {
            e.printStackTrace();
            // Solution: Handle the NoSuchSessionException appropriately
            // For example, you can reinitialize the WebDriver instance here if needed
            // Or you can log a message indicating the session is already closed
        } // Handle the TimeoutException appropriately

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
    @Parameters("browser")
    public void staleElement(String browser) {
        try {
            // StaleElementReferenceException
            driver.get(Constants.ironSpider);
            WebElement element = driver.findElement(By.xpath("//h1[@class='Heading1']"));

            // Simulate a scenario where the element is removed or replaced
            // Perform some action to refresh the page (e.g., driver.navigate().refresh())
            driver.navigate().refresh();

            // Check if the session is still active before reinitializing the WebDriver
            if (!(((RemoteWebDriver) driver).getSessionId() == null)) {
                // Reinitialize the WebDriver instance after calling quit()
                setup(browser); // Reinitialize the WebDriver

                // Find the element again after the DOM refresh
                WebElement refreshedElement = driver.findElement(By.xpath("//h1[@class='Heading1']"));

                // Perform actions on the refreshed element
                // For example, click the refreshed element
                refreshedElement.click();
            }
        } catch (StaleElementReferenceException | NoSuchSessionException e) {
            e.printStackTrace();
            // Solution: Find the element again after the DOM refresh
        } // Handle the NoSuchSessionException appropriately
        // For example, you can log a message indicating the session is already closed

    }

    @Test
    public void frameEx() {
        try {
            driver.get(Constants.iFrameURL);
            driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
            // Solution: Ensure the frame exists before switching to it

        } catch (NoSuchFrameException e) {
            e.printStackTrace();
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
