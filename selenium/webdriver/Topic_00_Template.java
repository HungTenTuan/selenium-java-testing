package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_00_Template {
    WebDriver driver;
    Actions actions;
    WebDriverWait webDriverWait;

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
        actions = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        // Selenium version: 1.x/ 2.x/ 3.x/ 4.x
        // Có 8 loo locator
        // Selenium locator = HTML Attribute
        // Id/ Class/ Name = Trùng với 3 attribute của HTML
        // LinkText/

    }

    @Test
    public void TC_02_(){

    }

    @Test
    public void TC_03_(){

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSeconds(long timeSeconds){
        try {
            Thread.sleep(timeSeconds * 1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String randomEmail(){
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

}
