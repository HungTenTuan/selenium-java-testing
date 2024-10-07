package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_19_ExplicitWait_Practice {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String img1 = "img1.jpeg";
    String img2 = "img2.jpeg";
    String img3 = "img3.jpeg";

    String projectPathImg1 = projectPath + File.separator + "uploadFile" + File.separator + img1;
    String projectPathImg2 = projectPath + File.separator + "uploadFile" + File.separator + img2;
    String projectPathImg3 = projectPath + File.separator + "uploadFile" + File.separator + img3;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Bai4_Equal_3_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait icon loading invisible in 3s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // verify text before icon loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_01_Bai4_Equal_5_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait icon loading invisible in 5s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // verify text before icon loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_01_Bai4_Equal_15_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait icon loading invisible in 15s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // verify text before icon loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_02_Bai5_Equal_3_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait element visible in 3s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_02_Bai5_Equal_5_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait element visible in 5s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_02_Bai5_Equal_15_Second(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        // wait element visible in 15s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish"))).isDisplayed());
    }

    @Test
    public void TC_03_Bai6(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // wait "date time" visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']/div")));

        // wait defaut date isn't select "No Selected Dates to display."
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No Selected Dates to display.']")));

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(@title,'Tuesday, September 10, 2024')]/a"))).click();

        // wait icon loading invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'RadAjaxLoadingPanel1ctl00')]/div[@class='raDiv']")));

        // wait until date is selected and verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Tuesday, September 10, 2024']"))).isDisplayed());
    }

    @Test
    public void TC_04_Bai7(){
        driver.get("https://gofile.io/?t=uploadFiles");

        // wait element can click and click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn-secondary')]"))).click();

        // verify icon loading invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner-border']")));

       //check icon upload visible and upload 3 file
        driver.findElement(By.xpath("//input[@id='filesUploadInput']")).sendKeys(projectPathImg1 + "\n" + projectPathImg2 + "\n" + projectPathImg3);

        // wait icons loading invisible
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='spinner-border']"))));

        // wait icons loading progress img
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//span[contains(@class,'justify-content-center')]"))));

        // wait icons loading invisible
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='spinner-border']"))));

        // verify success msg
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'border-success')]"))).isDisplayed());
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

    public WebElement getElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
