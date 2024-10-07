package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_20_FluentWait {
    WebDriver driver;
    FluentWait<WebDriver> webDriverFluentWait;
    FluentWait<WebElement> webElementFluentWait;
    long timeOutInSecond = 15;
    long pollingTimeInSecond = 1;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_FluentWait_TC08(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        // khoi tao fluentWait
        webDriverFluentWait = new FluentWait<WebDriver>(driver);

        // setting fluentWait
        webDriverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(300))
                .ignoring(TimeoutException.class);

        // dieu kien (Wait Time countdown visible)
        webDriverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.id("javascript_countdown_time")));
            }
        });

        // setting fluentWait
        webDriverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, NoAlertPresentException.class);

        // dieu kien (wait for element dem nguoc = 00)
        webDriverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String text = driver.findElement(By.id("javascript_countdown_time")).getText();
                System.out.println("time countdown is" + " " + text);
                return text.endsWith("00");
            }
        });


        // conditon fluent wait for alert
        Alert alert = webDriverFluentWait.until(new Function<WebDriver, Alert>() {
            @Override
            public Alert apply(WebDriver webDriver) {
                return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
            }
        });
    }

    @Test
    public void TC_02_FluentWait_TC09(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // khoi tao
        webDriverFluentWait = new FluentWait<WebDriver>(driver);

        // setting fluentWait for btn
        webDriverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(100))
                .ignoring(TimeoutException.class);

        // conditon for btn click (visible)
        webDriverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='start']/button")));
            }
        });

        // click btn start
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        WebElement element = driver.findElement(By.xpath("//div[@id='finish']/h4"));

        // khoi tao fluentWait
        webElementFluentWait = new FluentWait<WebElement>(element);

        // setting
        webElementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(100))
                .ignoring(NoSuchElementException.class);

        // conditions
        String text = webElementFluentWait.until(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement element) {
                return element.getText();
            }
        });

        // verify text
        Assert.assertEquals(text,"Hello World!");
    }

    public WebElement FindElementByFluentWait(By locator){
        webDriverFluentWait = new FluentWait<WebDriver>(driver);

        webDriverFluentWait.withTimeout(Duration.ofSeconds(timeOutInSecond))
                .pollingEvery(Duration.ofSeconds(pollingTimeInSecond))
                .ignoring(NoSuchElementException.class);

        return webDriverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });
    };





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

    public WebElement getElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
