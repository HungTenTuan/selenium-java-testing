package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_ExplicitWait {
    WebDriver driver;
    Actions actions;
    WebDriverWait explicitWait;

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
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        // cho` cho 1 alert presence trong DOM
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // cho` cho 1 element khong con xuat hien trong DOM (element da xuat hien truoc do roi)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("ii"))));

        // cho` cho 1 element xuat hien trong DOM (khong quan tam co tren UI hay khong)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("13")));

        // cho` cho 1 list element xuat hien trong DOM (khong quan tam co tren UI hay khong)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("12")));

        // cho` cho 1 hoac nhieu element duoc hien thi tren giao dien (rest parameter)
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("133"))));

        // cho` cho 1 element xuat hien tren giao dien
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("1")));

        // cho` cho 1 element cho phep duoc click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("1")));

        // cho` cho page hien tai co title nhu mong doi
        explicitWait.until(ExpectedConditions.titleIs("Expect title"));

        // dung khi ket hop nhieu loai dieu kien loai voi nhau
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.id("13")),
                                                     ExpectedConditions.presenceOfElementLocated(By.id("133"))));

        explicitWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("13")),
                                                     ExpectedConditions.presenceOfElementLocated(By.id("133"))));

        // cho` cac attribute cua element co tren DOM co gia tri dung voi gia tri mong doi (tuong doi)
        explicitWait.until(ExpectedConditions.attributeContains(By.id("123"),"value","expected value of attribute"));

        // cho` cac attribute cua element co tren DOM co gia tri dung voi gia tri mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.attributeToBe(By.id("123"), "value", "expected value of attribute"));

        // cho` cho 1 attribute cua element chuyen tu trang thai khong co du lieu thanh co du lieu (khong rong)
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("1323")),"value"));

        // cho` cho 1 attribute cua element co hoac khong co(gia tri bi an) trong DOM co gia tri dung voi gia tri mong doi
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.id("12")), "value", "gia tri mong doi cua attribute"));

        // cho` cho 1 property cua element co gia tri dung voi gia tri mong doi
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.id("12")),"height", "12px"));

        // cho` cho 1 element chuyen tu trang thai chua duoc select thanh` da duoc select
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.id("123")));

        // cho` cho 1 element duoc select(true) hoac chua duoc select(false)
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.id("12"),true));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.id("123"), false));

        // cho` cho 1 frame/iframe available va switch sang
        // nhan vao nhieu kieu tham so: int/ name or id/ by or element -> chi nen xai tham so la element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.id("frame"))));

        // cho` 1 element bien mat khoi giao dien (khong quan tam co trong DOM hay khong)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("123")));

        // cho` cho 1 doan script tra ve gia tri
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage"));

        // cho` cho 1 doan script thuc thi ma` khong nem' ra ngoai le nao
        // neu co ngoai le thi` tra ve false, khong co thi tra ve true
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("arguments[0].click()"));

        // cho` cho so luong element duoc tim` dung' voi so luong element mong doi
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.id("13"), 10));

        // cho` cho so luong element duoc tim nho hon so luong element mong doi
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("123"), 10));

        // cho` cho so luong element duoc tim luon hon so luong element mong doi
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("123"), 10));

        // cho` cho so luong tab/window bang voi so luong mong doi
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(10));

        // cho` cho element duoc tim co doan text giong voi doan text mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.textToBe(By.id("123"),"expect text"));

        // cho` cho element duoc tim co doan text mong doi xuat hien trong DOM (khong quan tam xuat hien tren UI hay khong)
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("123"), "expect text"));

        // cho` cho url hien tai giong voi url mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.urlToBe("fb.com/login"));

        // cho` cho url hien tai giong voi url mong doi (tuong doi)
        explicitWait.until(ExpectedConditions.urlContains("/login"));

        // kiem tra la 1 dieu kien cho` trong 1 khoang thoi gian nhat dinh
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id("12"))));
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

    public String randomEmail(){
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

    public WebElement getElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
