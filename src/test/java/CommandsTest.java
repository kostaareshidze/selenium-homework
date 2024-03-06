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

@Test
public class CommandsTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.firstLink);
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
    @Test
    public void buttonTest() throws InterruptedException {
        WebElement enable = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enable.click();
        Thread.sleep(5000);

        WebElement check = driver.findElement(By.xpath("//form[@id='input-example']/p"));
        Assert.assertEquals(check.getText(), "It's enabled!");

        WebElement disable = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        Assert.assertEquals(disable.getText(), "Disable");

        WebElement input = driver.findElement(By.xpath("//form[@id='input-example']/input"));
        input.click();
        input.sendKeys("TBC IT Academy");
        Thread.sleep(5000);
        input.clear();
    }

    @Test
    public void labelsTest(){
        WebElement mainName = driver.findElement(By.xpath("//div[@class='example']/h4[1]"));
        Assert.assertEquals(mainName.getText(), "Dynamic Controls");
        WebElement description = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertEquals(description.getText(), Constants.description);


    }



}
