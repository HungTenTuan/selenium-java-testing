package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_17_UploadFile {
    WebDriver driver;
    Actions actions;
    WebDriverWait webDriverWait;
    String projectPath = System.getProperty("user.dir");



    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        // access URL
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // define loction file img always true when access with any system
        String img1 = "img1.jpeg";
        String img2 = "img2.jpeg";
        String img3 = "img3.jpeg";

        // File.separator: adjust "\" in window and "/" in macOS
        String img1FilePath = projectPath + File.separator + "uploadFile" + File.separator + img1;
        String img2FilePath = projectPath + File.separator + "uploadFile" + File.separator + img2;
        String img3FilePath = projectPath + File.separator + "uploadFile" + File.separator + img3;
        System.out.println(projectPath);

        /*// upload single file with method sendkey
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(img1FilePath);
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(img2FilePath);
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(img3FilePath);
        sleepInSeconds(2);*/

        // upload multiple file with method sendkey
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(img1FilePath + "\n" + img2FilePath + "\n" + img3FilePath);
        sleepInSeconds(2);

        //verify file successfully loaded
        Assert.assertTrue(getElementByXpath("//p[text()='" + img1 + "']").isDisplayed());
        Assert.assertTrue(getElementByXpath("//p[text()='" + img2 + "']").isDisplayed());
        Assert.assertTrue(getElementByXpath("//p[text()='" + img3 + "']").isDisplayed());

        // upload file
        List<WebElement> listBtnEdit = driver.findElements(By.xpath("//span[text()='Start']"));

        for (WebElement btn:listBtnEdit){
            btn.click();
            sleepInSeconds(2);
        }

        // verify successfully uploaded
        Assert.assertTrue(getElementByXpath("//a[text()='" +img1+ "']").isDisplayed());
        Assert.assertTrue(getElementByXpath("//a[text()='" +img2+ "']").isDisplayed());
        Assert.assertTrue(getElementByXpath("//a[text()='" +img3+ "']").isDisplayed());
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

    public WebElement getElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
