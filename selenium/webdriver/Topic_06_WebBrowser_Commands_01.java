package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Page_Url(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Page_Title(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_Page_Navigation(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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
}
