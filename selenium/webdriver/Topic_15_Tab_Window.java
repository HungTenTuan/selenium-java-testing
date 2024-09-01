package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Tab_Window {
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
    public void TC_01_Tab_Window(){
     driver.get("https://automationfc.github.io/basic-form/index.html");
     String parentID = driver.getWindowHandle();

     //click GG => hien thi tab moi
     driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
     sleepInSeconds(2);

     //Switch sang tab GG
     Set<String> listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (driver.getTitle().equals("Google")){
             break;
         }
     }
     // kiem tra dang o dang tab google
     Assert.assertEquals(driver.getTitle(),"Google");

     //Switch ve tab parent
     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (driver.getTitle().equals("Selenium WebDriver")){
             break;
         }
     }
     // kiem tra dang o dang tab parent
     Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");

     //click FB => hien thi tab moi
     driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
     sleepInSeconds(2);

     //Switch sang tab FB
     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if(driver.getTitle().equals("Facebook – log in or sign up")){
             break;
         }
     }
     // kiem tra dang o dang tab FB
     Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
     sleepInSeconds(2);

     //Switch ve tab parent
     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (driver.getTitle().equals("Selenium WebDriver")){
             break;
         }
     }
     // kiem tra dang o dang tab parent
     Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");

     //click TIKI => hien thi tab moi
     driver.findElement(By.xpath("//a[text()='TIKI']")).click();
     sleepInSeconds(2);

     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if(driver.getTitle().equals("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh")){
             break;
         }
     }
     //kiem tra dang o dang tab TIKI
     Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

     //Switch ve tab parentd
     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (driver.getTitle().equals("Selenium WebDriver")){
             break;
         }
     }
     //Kiem tra dang o tab parent
     Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");

     // click LAZADA => hien thi tab moi
     driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
     sleepInSeconds(2);

     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (driver.getTitle().equals("Lazada - Mua Sắm Hàng Chất Giá Tốt Online")){
             break;
         }
     }
     // kiem tra dang o tab LAZADA
     Assert.assertEquals(driver.getTitle(),"Lazada - Mua Sắm Hàng Chất Giá Tốt Online");

     // dong tat ca cac tab tru tab parent
     listId = driver.getWindowHandles();
     for (String id:listId){
         driver.switchTo().window(id);
         if (!driver.getTitle().equals("Selenium WebDriver")){
            driver.close();
         }
     }
     driver.switchTo().window(parentID);
    }

    @Test
    public void TC_02_Tab_Window(){
        driver.get("http://live.techpanda.org/");

        String parentTab = driver.getWindowHandle();

        // click mobie tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);

        // clicl "add to compare" product 1
        driver.findElement(By.xpath("//img[@id='product-collection-image-1']//parent::a//following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Sony Xperia has been added to comparison list.");

        // clicl "add to compare" product 2
        driver.findElement(By.xpath("//img[@id='product-collection-image-2']//parent::a//following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product IPhone has been added to comparison list.");

        // clicl "add to compare" product 3
        driver.findElement(By.xpath("//img[@id='product-collection-image-3']//parent::a//following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        // click btn compare
        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        sleepInSeconds(2);

        // switch to new window
        switchByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        // close new window and switch to parent tab
        driver.close();
        driver.switchTo().window(parentTab);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSeconds(2);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).getText(),"The comparison list was cleared.");

    }

    @Test
    public void TC_03_(){

    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }

    public void sleepInSeconds(long timeSeconds){
        try {
            Thread.sleep(timeSeconds * 1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public void closeAllTabByTitle(String expectedTitle){
        Set<String> listId = driver.getWindowHandles();
        for (String id:listId){
            driver.switchTo().window(id);
            if (!driver.getTitle().equals(expectedTitle)){
                driver.close();
                driver.switchTo().window(expectedTitle);
            }
        }
    }

    public void switchByTitle(String expectedTitle){
        Set<String> listId = driver.getWindowHandles();

        for (String id:listId){
            driver.switchTo().window(id);

            if(driver.getTitle().equals(expectedTitle)){
                break;
            }
        }
    }

    public String randomEmail(){
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

}
