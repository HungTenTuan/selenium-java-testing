package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Ureru {
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
        driver.get("https://v2d2c.staging-ureruad.com/login");

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        WebElement loginBtn = driver.findElement(By.xpath("//button[@class='login_btn']"));
        WebElement inputAccount = driver.findElement(By.xpath("//input[@name='account_id']"));
        WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));

        inputAccount.sendKeys("thienyqc");
        userName.sendKeys("thienyqc");
        inputPassword.sendKeys("BZYzKw");
        loginBtn.click();

        WebElement imgLinkListCP = driver.findElement(By.xpath("//a[@href='/admin/campaigns']"));
        imgLinkListCP.click();

        WebElement btnTaoCP = driver.findElement(By.xpath("//p[contains(string(),'ランディングページ自動生成')]"));
        btnTaoCP.click();


        WebElement btnTiepTuc1 = driver.findElement(By.xpath("//a[contains(string(),'はじめる')]"));
        btnTiepTuc1.click();

        WebElement inputTenCP = driver.findElement(By.xpath("//input[@id='quick-campaign-landings-0-product-name']"));
        inputTenCP.sendKeys("demo");

        WebElement btnTiepTuc2 = driver.findElement(By.xpath("//a[contains(string(),'進む')]"));
        btnTiepTuc2.click();

    }

    @Test
    public void TC_02_(){

    }

    @Test
    public void TC_03_(){

    }


    @AfterClass
    public void afterClass(){
       // driver.quit();
    }
}
