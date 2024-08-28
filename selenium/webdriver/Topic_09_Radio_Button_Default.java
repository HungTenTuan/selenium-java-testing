package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_09_Radio_Button_Default {
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        WebElement btnLogin= driver.findElement(By.xpath("//div[@class='fhs-btn-box']//button[@title='Đăng nhập']"));
        Assert.assertFalse(btnLogin.isEnabled());

        String btnLoginRgba  = btnLogin.getCssValue("background-color");
        String btnLoginHex = Color.fromString(btnLoginRgba).asHex();

        Assert.assertEquals(btnLoginHex.toLowerCase(),"#000000");

        driver.findElement(By.id("login_username")).sendKeys("Hung1912200x@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("123456");

        btnLogin= driver.findElement(By.xpath("//div[@class='fhs-btn-box']//button[@title='Đăng nhập']"));
        Assert.assertTrue(btnLogin.isEnabled());

        Assert.assertEquals(Color.fromString(btnLogin.getCssValue("background-color")).asHex().toUpperCase(),"#C92127");
    }

    @Test
    public void TC_02_(){
        driver.get("https://egov.danang.gov.vn/reg");
        Assert.assertFalse(driver.findElement(By.id("button2")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.id("button2")).getCssValue("background-color")).asHex().toUpperCase(),"#A0A0A0");

        driver.findElement(By.id("chinhSach")).click();
        Assert.assertTrue(driver.findElement(By.id("button2")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.id("button2")).getCssValue("background-color")).asHex().toLowerCase(),"#ef5a00");
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
}
