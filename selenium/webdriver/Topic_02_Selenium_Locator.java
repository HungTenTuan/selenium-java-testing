package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        driver.manage().window().maximize();
    }
    // <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
    /**/
    @Test
    public void TC_01_Id(){
        driver.findElement(By.id("FirstName"));
    }

    @Test
    public void TC_02_Class(){
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_TagName(){
        driver.findElements(By.tagName("input"));
        // Dung de tim nhieu the HTML giong nhau
        //it duoc su dung
    }

    @Test
    public void TC_05_LinkText(){
        driver.findElement(By.linkText("Sitemap"));
        // Bat buoc lay tat ca cua text de tim kiem -> do chinh xac cao vi tuyet doi
        //it duoc su dung
        //chi ho tro lay text cua link
    }

    @Test
    public void TC_06_Partial_LinkText() {
        driver.findElement(By.partialLinkText("vendor account"));
        //Co the tim bang 1 phan cua text de tim kiem -> do chinh xac se khong cao vi tuong doi
        //it duoc su dung
        //chi ho tro lay text cua link
    }
    @Test
    public void TC_07_Css(){
        //Co the cover duoc 6 truong hop tren

        // Css voi ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        // Css voi Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        // Css voi name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        // Css voi tagname
        driver.findElement(By.cssSelector("input"));

        // Css voi link -> phai ket hop voi href, khong ket hop duoc voi text
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        // Css voi partial link
        driver.findElement(By.cssSelector("a[href*='/customer']")); //lay giua
        /*
        driver.findElement(By.cssSelector("a[href^='/customer']")); // lay dau
        driver.findElement(By.cssSelector("a[href$='/customer']")); //lay cuoi
         */
    }

    @Test
    public void TC_08_Xpath(){
        // Co the cover duoc tat ca 7 cai tren
        // Khong cho viet tat
        // Tu Css -> Xpath them dau // ow dau va them dau @ truoc attribute
        // Xpath voi ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        // Xpath voi Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        // Xpath voi name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // Xpath voi tagname
        driver.findElement(By.xpath("//input"));

        // Xpath voi link -
        driver.findElement(By.xpath("//a[@href='/customer/addresses']")); // lay bang link
        driver.findElement(By.xpath("//a[text()='Addresses']")); // lay bang text

        // Xpath voi partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]")); //lay bang link
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]")); //lay bang texrt

    }


    @AfterClass
    public void afterClass(){
      //  driver.quit();
    }


}
