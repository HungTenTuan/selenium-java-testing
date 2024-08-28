package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_14_Frame_Iframe {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Iframe(){
        driver.get("https://toidicodedao.com/");

        // verify iframe hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='fb-root']//following-sibling::div//iframe")).isDisplayed());

        // Switch sang iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='fb-root']//following-sibling::div//iframe")));

        // verify so luong followers
        String followersRealtime = driver.findElement(By.xpath("//div[@class='lfloat']//div[@class='_1drq']")).getText();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']//div[@class='_1drq']")).getText(),followersRealtime);
    }

    @Test
    public void TC_02_Iframe(){
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        // scroll den element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("imageTemplateContainer")));
        sleepInSeconds(2);

        //click vao form
        actions.moveToElement(driver.findElement(By.id("imageTemplateContainer"))).click().perform();

        // Switch sang iframe form
        driver.switchTo().frame(driver.findElement(By.id("frame-one85593366")));
        sleepInSeconds(2);

        // nhap du lieu vao form
        new Select(driver.findElement(By.id("RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.id("RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        actions.click(driver.findElement(By.id("RESULT_RadioButton-4_0"))).perform();

        // Switch ve lai trang web
        driver.switchTo().defaultContent();

        // scroll to btn login
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//nav[@class='header header--desktop']//a[@title='Log in']")));
        sleepInSeconds(2);

        // click btn login
        actions.click(driver.findElement(By.xpath("//nav[@class='header header--desktop']//a[@title='Log in']"))).perform();
        sleepInSeconds(2);

        // click btn login form dang nhap
        actions.click(driver.findElement(By.id("login"))).perform();

        // kiem tra alert
        Assert.assertEquals(driver.findElement(By.id("message-error")).getText(),"Username and password are both required.");
    }

    @Test
    public void TC_03_Frame(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        // Switch to frame
        driver.switchTo().frame(driver.findElement(By.xpath("//frame")));

        // nhap vao truong Customer ID
        driver.findElement(By.xpath("//a[text()='Forgot Customer ID']//parent::span//parent::div//input")).sendKeys("1234656");

        // click continue
        actions.click(driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']"))).perform();
        sleepInSeconds(2);

        // verify textbox Customer ID is disabled
        Assert.assertFalse(driver.findElement(By.id("liabiltyLoginCustId")).isEnabled());

        // verify textbox password is displayed
        Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
    }


    @AfterClass
    public void afterClass(){
        /*driver.quit();*/
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
