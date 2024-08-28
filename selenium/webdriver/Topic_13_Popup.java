package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_13_Popup {
    WebDriver driver;
    Actions actions;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_NotIn_DOM(){
        driver.get("https://ngoaingu24h.vn/");
        // click btn dang nhap
        actions.click(driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]"))).perform();
        sleepInSeconds(2);

        // kiem tra popup display
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='custom-dialog']//div[3]/div")).isDisplayed());

        // nhap thong tin khong ton tai trong popup
        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("Hung1234");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("Hung1234");
        driver.findElement(By.xpath("//span[text()='Quên mật khẩu']//parent::div//following-sibling::div//button")).click();
        sleepInSeconds(1);

        // kiem tra alert loi
        Assert.assertTrue(driver.findElement(By.id("notistack-snackbar")).isDisplayed());

        // clickbtn dong popup
        actions.click(driver.findElement(By.xpath("//h2[@id='mui-2']//button"))).perform();
        sleepInSeconds(2);

        // kiem tra popup khong ton tai
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[@id='custom-dialog']//div[3]/div")).size(),0);
    }

    @Test
    public void TC_02_TC_01_Fixed_Popup_Fixed_Display(){
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        sleepInSeconds(1);

        // kiem tra popup ton tai
        Assert.assertTrue(driver.findElement(By.className("right")).isDisplayed());

        // nhap thong tin sai vao popup
        driver.findElement(By.id("user-login")).sendKeys("hung123");
        driver.findElement(By.id("user-password")).sendKeys("hung123");
        actions.click( driver.findElement(By.id("btn-submit-login"))).perform();
        sleepInSeconds(2);

        // kiem tra hien thi alert
        Assert.assertTrue(driver.findElement(By.id("password-form-login-message")).isDisplayed());
        sleepInSeconds(2);
    }

    @Test
    public void TC_03_Fixed_Popup_NotIn_DOM(){
        driver.get("https://tiki.vn/");
        sleepInSeconds(3);

        // click vao diem bat ki cua man hinh de dong popup random hien thi mac dinh
        actions.moveByOffset(300,500).click().perform();
        sleepInSeconds(2);

        // click vao button tai khoan
        actions.click(driver.findElement(By.xpath("//img[@alt='header_header_account_img']//parent::div"))).perform();
        sleepInSeconds(2);

        // kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn-close']//parent::div")).isDisplayed());

        // click dang nhap bang mail
        actions.click(driver.findElement(By.xpath("//p[text()='Đăng nhập bằng email']"))).perform();
        sleepInSeconds(2);

        // click btn dang nhap ma khong nhap du lieu
        actions.click(driver.findElement(By.xpath("//button[text()='Đăng nhập']"))).perform();
        sleepInSeconds(2);

        // Kiem tra hien thi alert
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).isDisplayed());

        // dong popup
        actions.click(driver.findElement(By.xpath("//button[@class='btn-close']"))).perform();
        sleepInSeconds(2);

        // Kiem tra popup khong ton tai (Not in DOM)
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn-close']//parent::div")).size(),0);
        sleepInSeconds(2);
    }

    @Test
    public void TC_04_Fixed_Popup_NotIn_DOM(){
        driver.get("https://www.facebook.com/");
        // click button create new account
        actions.click(driver.findElement(By.xpath("//a[text()='Create new account']"))).perform();
        sleepInSeconds(2);

        // Kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//parent::div//parent::div")).isDisplayed());

        // CLick dong popup
        actions.click(driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//parent::div/img"))).perform();
        sleepInSeconds(2);

        // kiem tra popup khong hien thi
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']//parent::div//parent::div//parent::div")).size(), 0);

    }

    @Test
    public void TC_05_Random_Popup_In_DOM(){
        driver.get("https://www.kmplayer.com/home");
        sleepInSeconds(2);

        // kiem tra neu co popup thi click btn dong popup
        if (driver.findElement(By.cssSelector("div.close")).isDisplayed()){
            actions.click(driver.findElement(By.cssSelector("div.close"))).perform();
        }

        // kiem tra text hien thi khi dong popup thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'KMPlayer')]")).isDisplayed());
    }

    @Test
    public void TC_06_Random_Popup_NotIn_DOM(){
        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(20);

        // kiem tra neu co popup thi click btn dong popup
        By popup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        if (driver.findElements(popup).size()>0 && driver.findElements(popup).get(0).isDisplayed()){
            actions.click(driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a"))).perform();
        }
        sleepInSeconds(2);

        // nhap du lieu vao field search sau khi close popup
        driver.findElement(By.id("search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.id("search-submit")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Agile Testing Explained')]")).isDisplayed());
    }

    @Test
    public void TC_07_Shadow_Popup_DOM(){
        driver.get("https://automationfc.github.io/shadow-dom");

        // tim Element la shadow host
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));

        // tu shadow host => truy cap den shadow
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // kiem tra phan tu trong shadow hien thi
        Assert.assertTrue(shadowRoot.findElement(By.cssSelector("span.info")).isDisplayed());
        Assert.assertFalse(shadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        // tim Element shadow host la con cua 1 shadow host khac
        WebElement nestedShadowHost = shadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));

        // tu shadow host => truy cap den shadow
        SearchContext nestedShadowRoot =  nestedShadowHost.getShadowRoot();

        // kiem tra phan tu trong shadow hien thi
        Assert.assertTrue(nestedShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).isDisplayed());
    }

    @Test
    public void TC_08_Shadow_Popup_DOM_Shopee(){
        driver.get("https://shopee.vn/");

        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowContext = shadowHostElement.getShadowRoot();

        // check neu popup xuat hien (popup not in DOM)
        if (shadowContext.findElements(By.cssSelector("div.home-popup__content")).size() > 0 &&
                shadowContext.findElements(By.cssSelector("div.home-popup__content")).getFirst().isDisplayed() ){
            actions.click(shadowContext.findElement(By.cssSelector("div.shopee-popup__close-btn"))).perform();
        }
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


    // override lai ham findElement khi popup xuat hien random
    public WebElement findElement(By locatorElement, By locatorPopup, By locatorClosePopup){
        if (driver.findElement(locatorPopup).isDisplayed()){
            driver.findElement(locatorClosePopup).click();
        }
        return driver.findElement(locatorElement);
    }

}
