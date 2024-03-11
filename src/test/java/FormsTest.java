import ge.tbcacademy.data.Constants;
import ge.tbcacademy.data.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormsTest {
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
    public void customDropDownTest(){
        driver.get(Constants.tympanusURL);
        WebElement demo2 = driver.findElement(By.xpath("//a[@href='index2.html']"));
        demo2.click();
        WebElement dropDown = driver.findElement(By.id("dd"));
        Assert.assertEquals(dropDown.getAttribute("class"), Constants.dropDown);

        //class name is active when it is clicked
        dropDown.click();
        Assert.assertEquals(dropDown.getAttribute("class"), Constants.dropDownActive);
        WebElement github = driver.findElement(By.xpath("//i[@class='icon-github icon-large']/parent::a"));
        Main.universalSelector(github, "Github");
    }
    @Test
    public void nativeDropDownTest() {
        driver.get(Constants.canvaURL);
        WebElement male = driver.findElement(By.xpath("//input[@value='male']"));
        male.click();
        Select dropDown = new Select(driver.findElement(By.xpath("//select")));
        Main.universalSelector(dropDown, Constants.mega);
        WebElement firstName = driver.findElement(By.xpath("//input[@value='First Name']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@value='Last Name']"));
        WebElement address1 = driver.findElement(By.xpath("//input[@value='Address1']"));
        WebElement address2 = driver.findElement(By.xpath("//input[@value='Address2']"));
        WebElement city = driver.findElement(By.xpath("//input[@value='City']"));
        WebElement contact1 = driver.findElement(By.xpath("//input[@value='Contact1']"));
        WebElement contact2 = driver.findElement(By.xpath("//input[@value='Contact2']"));
        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));

        firstName.sendKeys(Constants.name);
        lastName.sendKeys(Constants.lastName);
        address1.sendKeys(Constants.address1);
        address2.sendKeys(Constants.address2);
        city.sendKeys(Constants.location);
        contact1.sendKeys(Constants.number1);
        contact2.sendKeys(Constants.number2);
        checkBox.click();

    }

}
