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
    public void TC_03_Tab_Window(){
        driver.get("https://dictionary.cambridge.org/vi/");

        // click btn sign up => open new window
        driver.findElement(By.xpath("//span[text()='Đăng nhập']//parent::span//following-sibling::span//span[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        // switch to new window
        Set<String> listId = driver.getWindowHandles();
        for (String id:listId){
            driver.switchTo().window(id);
            if (driver.getTitle().equals("Sign Up")){
                break;
            }
        }

        // click btn sign up at new window
        driver.findElement(By.xpath("//input[@value='Sign up']")).click();

        // verify msg error
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']//following-sibling::span[text()='This field is required']")).getText(),"This field is required");
        //close new window
        driver.close();

        // switch driver back parent tab
        listId = driver.getWindowHandles();
        for (String id:listId){
            driver.switchTo().window(id);
            if (driver.getTitle().equals("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa")){break;}
        }

        // type to input search
        driver.findElement(By.id("searchword")).sendKeys("Automation");
        // click btn search
        driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();
        sleepInSeconds(2);
        // verify text searched
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='cald4-1']//following-sibling::div[1]//span[text()='automation']")).getText(),"automation");
    }

    @Test
    public void TC_04_Tab_Window(){
        driver.get("https://courses.dce.harvard.edu/");
        // click btn 'student login' => open new tab
        driver.findElement(By.xpath("//a[@class='anon-only']")).click();
        sleepInSeconds(4);

        // switch to new tab
        switchByTitle("Harvard Division of Continuing Education Login Portal");
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(),"Harvard Division of Continuing Education Login Portal");
        driver.close();

        // switch back to parent tab
        switchByTitle("DCE Course Search");
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Authentication was not successful.  Please try again.')]")).getText(),"Authentication was not successful. Please try again.");
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
