package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");

    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass(){
        if (osName.contains("Windows")){
            System.getProperty("webdrive.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        }else {
            System.getProperty("webdrive.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Relative(){

        //Define textbox email
        By textboxEmail = By.id("Email");

        ////Define textbox password
        WebElement textboxPassword = driver.findElement(By.id("Password"));

        //Define button
        WebElement buttonLogin = driver.findElement(By.cssSelector("button.login-button"));

        //Define checkbox
        By rememberMecheckbox = By.id("RememberMe");

        //Define forgot link
        WebElement forgotLink = driver.findElement(By.linkText("Forgot password?"));

        //find lable 'Please enter your email'
        WebElement rememberMeLable;
        rememberMeLable = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(buttonLogin)
                .toRightOf(rememberMecheckbox)
                .toLeftOf(forgotLink)
                .below(textboxPassword)
                .near(forgotLink));
        System.out.println(rememberMeLable.getText());
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
