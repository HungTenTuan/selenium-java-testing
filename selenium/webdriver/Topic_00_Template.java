package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_00_Template {
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
        driver.get("https://www.facebook.com/?stype=lo&deoia=1&jlou=AfctzyXnD0v_G6nnHDnm6RwlH_wr-6TXPXeBDHgCXIZnLiK26zJnMtL4inm7m-zRkLdu6QilT7Ml0nBM4YpttBRSmMs9rEoq2v6y1JhkpNlw_Q&smuh=14472&lh=Ac9H7KD4PyaB_ois1Nw");

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
}
