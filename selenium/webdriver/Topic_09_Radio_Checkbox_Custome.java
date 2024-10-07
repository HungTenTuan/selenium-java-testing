package webdriver;

import com.beust.ah.A;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Radio_Checkbox_Custome {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        // handle checkbox
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        checkCheckbox( "//label[text()='Rear side airbags']//preceding-sibling::span/input");
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']//preceding-sibling::span/input")).isSelected());
        sleepInSeconds(2);

        uncheckCheckbox( "//label[text()='Rear side airbags']//preceding-sibling::span/input");
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']//preceding-sibling::span/input")).isSelected());

    }

    @Test
    public void TC_02_(){
        // handle radio
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        checkCheckbox("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span/input");
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']//preceding-sibling::span/input")).isSelected());

        checkCheckbox("//label[text()='1.8 Petrol, 118kW']//preceding-sibling::span/input");
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']//preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span/input")).isSelected());
    }

    @Test
    public void TC_03_(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        // chon het checkbox
        List<WebElement> listCheckbox = driver.findElements(By.xpath("//div[@id='cid_52']//span//input"));

        for (WebElement checkbox : listCheckbox){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify tat ca cac checkbox
       for (WebElement checkbox : listCheckbox){
            Assert.assertTrue(checkbox.isSelected());
        }

        // chi chon 1 checkbox trong danh sach checkbox
        // refresh and delete cookies
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // gan lai danh sach checkbox sau khi DOM duoc cap nhat
        listCheckbox = driver.findElements(By.xpath("//div[@id='cid_52']//span//input"));

        for (WebElement checkbox : listCheckbox ){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(2);
            }else if (checkbox.isSelected()){
                checkbox.click();
            }
        }
        for (WebElement checkbox : listCheckbox){
            if (checkbox.getAttribute("value").equals("Heart Attack") ){
                Assert.assertTrue(checkbox.isSelected());
            }else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_(){
        driver.get("https://login.ubuntu.com/");
        checkCustomRadio("//input[@id='id_new_user']");
    }

    @Test
    public void TC_05_(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        WebElement radioHCM = driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh']"));
        WebElement radioDaNang = driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng']"));

        sleepInSeconds(1);
        radioDaNang.click();
        sleepInSeconds(2);

        Assert.assertEquals(radioHCM.getAttribute("aria-checked"),"false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='false']")).isDisplayed());
        Assert.assertEquals(radioDaNang.getAttribute("aria-checked"),"true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='true']")).isDisplayed());

        radioHCM.click();
        sleepInSeconds(2);

        Assert.assertEquals(radioHCM.getAttribute("aria-checked"),"true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='true']")).isDisplayed());
        Assert.assertEquals(radioDaNang.getAttribute("aria-checked"),"false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='false']")).isDisplayed());
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

    public void checkCheckbox(String Xpathcheckbox){
        WebElement checkbox = driver.findElement(By.xpath(Xpathcheckbox));

        // default chua duoc chon
        if (!checkbox.isSelected()){
            checkbox.click();
            sleepInSeconds(2);
        }
    }

    public void  uncheckCheckbox(String Xpathcheckbox){
        WebElement checkbox = driver.findElement(By.xpath(Xpathcheckbox));

        if(checkbox.isSelected()){
            checkbox.click();
            sleepInSeconds(2);
        }
    }

    public void checkCustomRadio(String Xpathelement){
        WebElement element = driver.findElement(By.xpath(Xpathelement));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click()",element);
        Assert.assertTrue(element.isSelected());
    }

}
