import ge.tbcacademy.data.Constants;
import ge.tbcacademy.data.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TableTest {
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
    public void firstTest(){
        driver.get(Constants.firstTableURL);
        Main.dynamicallyIndex(driver, Constants.length);
        Main.dynamicallyIndex(driver, Constants.country);
        Main.dynamicallyIndex(driver, Constants.fakeCountry);
        driver.get(Constants.secTableURL);
        Main.dynamicallyIndex(driver, Constants.creta);
        Main.dynamicallyIndex(driver, Constants.cretato);
        driver.get(Constants.thirdTableURL);
        Main.dynamicallyIndex(driver, Constants.element);
        Main.dynamicallyIndex(driver, Constants.fakeElement);
        driver.get(Constants.forthTableURL);
        Main.dynamicallyIndex(driver, Constants.centro);
        Main.dynamicallyIndex(driver, Constants.fakeCentro);
        driver.get(Constants.fifthTableURL);
        Main.dynamicallyIndex(driver, Constants.nyakundi);
        Main.dynamicallyIndex(driver, Constants.iagundi);

    }
}
