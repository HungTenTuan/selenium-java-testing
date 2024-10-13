package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_21_Wait_PageReady {
    WebDriver driver;
    Actions actions;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

        // send key email
        WebElement email = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        email.clear();
        email.sendKeys("admin@yourstore.com");

        // send key password
        WebElement password = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
        password.clear();
        password.sendKeys("Password");

        // click button login
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='buttons']/button"))).click();

        /*//wait for ajax loading disapear
        Assert.assertTrue(isLoadingSuccsess());*/

        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//i[contains(@class,'fa-user') and contains(@class,'nav-icon')]/ancestor::li")))
                .click();

        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//p[text()=' Customer roles']//ancestor::li[@class='nav-item']//preceding-sibling::li//a")))
                .click();
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

   public boolean isLoadingSuccess(){
        return new WebDriverWait(driver,Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return (boolean) ((JavascriptExecutor) input)
                        .executeScript("window.jQuery != null && jQuery.active == 0;");
            }
        });
   }

    public String randomEmail(){
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

    public WebElement getElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
