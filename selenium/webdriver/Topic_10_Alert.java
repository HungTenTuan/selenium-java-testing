package webdriver;

import org.bouncycastle.util.encoders.Base64;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Alert {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("116");
        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Promt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String keyInAlert = "Hung";
        alert.sendKeys(keyInAlert);
        sleepInSeconds(2);
        alert.accept();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered:" + " " + keyInAlert);
    }

    @Test
    public void TC_04_Authencation_Alert() {
        String userName = "admin";
        String password = "admin";
        driver.get("http://" + userName + ":" + password + "@the-internet.herokuapp.com/basic_auth");
        driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed();
    }

    @Test
    public void TC_05_Authentication_Pass_To_URL() {
        String username = "admin";
        String password = "admin";

        // Method 1: Pass the username and password directly in the URL (Bypass Trick)
        // driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        // Assert.assertTrue(driver.findElement(
        //      By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Method 2: From page A, interact with an element to go to page B (Auth Alert should be handled first)
        driver.get("http://the-internet.herokuapp.com/");

        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        // http://the-internet.herokuapp.com/basic_auth

        String[] authenArray = authenLinkUrl.split("//");
        driver.get(authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1]);

        Assert.assertTrue(driver.findElement(By.xpath(
                "//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_06_Authencation_Selenium4x() {
        //chrome devtool protocol(CDP) - chi ho tro cho chrome va edge (chromium)

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");

    }


    @AfterClass
    public void afterClass() {

        //driver.quit();
    }

    public void sleepInSeconds(long timeSeconds) {
        try {
            Thread.sleep(timeSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String randomEmail() {
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

}
