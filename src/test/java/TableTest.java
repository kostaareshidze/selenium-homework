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
        System.out.println(Main.dynamicallyIndex(driver, "Built", Constants.length));
        System.out.println(Main.dynamicallyIndex(driver, "Country", Constants.country));
        System.out.println(Main.dynamicallyIndex(driver, "Contact", Constants.fakeCountry));
        driver.get(Constants.secTableURL);
        System.out.println(Main.dynamicallyIndex(driver, "Ex-showroom Price(INR)", "Hyundai"));
        System.out.println(Main.dynamicallyIndex(driver, "Company Name", Constants.creta));
        System.out.println(Main.dynamicallyIndex(driver, "Company Name", Constants.cretato));
        driver.get(Constants.thirdTableURL);
        System.out.println(Main.dynamicallyIndex(driver, "Diceret", Constants.element));
        System.out.println(Main.dynamicallyIndex(driver, "Ipsum", Constants.fakeElement));
        driver.get(Constants.forthTableURL);
        System.out.println(Main.dynamicallyIndex(driver, "Contact", Constants.centro));
        System.out.println(Main.dynamicallyIndex(driver, "Contact", Constants.fakeCentro));
        driver.get(Constants.fifthTableURL);
        System.out.println(Main.dynamicallyIndex(driver, "Last Name", Constants.nyakundi));
        System.out.println(Main.dynamicallyIndex(driver, "FIRST NAME", Constants.iagundi));

    }
}
