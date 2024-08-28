package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
       selectDropdown("https://jqueryui.com/resources/demos/selectmenu/default.html","//span[@id='speed-button']","//li[@class='ui-menu-item']","Medium");
       Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(),"Medium");
       sleepInSeconds(2);

       selectDropdown("https://jqueryui.com/resources/demos/selectmenu/default.html","//span[@id='files-button']","//li[@class='ui-menu-item']","Some unknown file");
       Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(),"Some unknown file");
       sleepInSeconds(2);

       selectDropdown("https://jqueryui.com/resources/demos/selectmenu/default.html","//span[@id='number-button']","//ul[@id='number-menu']//div","19");
       Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),"19");
       sleepInSeconds(2);

       selectDropdown("https://jqueryui.com/resources/demos/selectmenu/default.html","//span[@id='salutation-button']","//li[@class='ui-menu-item']","Dr.");
       Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(),"Dr.");
       sleepInSeconds(2);



    }

    @Test
    public void TC_02_(){
        selectDropdown("https://react.semantic-ui.com/maximize/dropdown-example-selection/","//div[@id='root']","//div[@class='item']//span","Matt");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='root']//div")).getText(),"Matt");
    }

    @Test
    public void TC_03_(){

        selectDropdown("https://mikerodham.github.io/vue-dropdowns/","//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//li","First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
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

    public void selectDropdown(String url,String xPathParentDropdown, String xPathchildsDropdown, String xPathexpectedResult){
        driver.get(url);
       /*	Viết hàm mô tả được hành vi của dropdown
        1.	Click vào thẻ để nó sổ ra hết được các item bên trong ra*/
        driver.findElement(By.xpath(xPathParentDropdown)).click();
        sleepInSeconds(2);

  /*     2.	Nó sẽ sổ ra và chứa hết tất cả item
        2.2  Nó sẽ sổ ra nhưng chỉ chứa 1 phần và đang load thêm(so luong record rat lon)*/
        List<WebElement> dataDropdown = explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPathchildsDropdown)));

        for (WebElement item : dataDropdown){
            String tmp = item.getText();
            if(tmp.equals(xPathexpectedResult)){
                item.click();
                System.out.println("result =" + tmp );
                break;
            }
        }
        /*  3. Nếu item mình cần chọn thì click vào
        3.1 Nếu item nằm bên dưới thì cần scroll xuống để hiển thị lên rồi mới chọn*/

        /*4. Trước khi click cần kiểm tra nếu như text của item bằng với item cần chọn thì click vào
         */
    }
}
