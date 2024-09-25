package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_11_Action_I {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover(){

        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.id("age"))).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Click(){
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        action.click(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']//ancestor::div[@class='fhs_column_stretch']//a[text()='Tiểu Thuyết']"))).perform();
        sleepInSeconds(2);
    }

    @Test
    public void TC_03_Click_Hold_Block(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ui-selectee"));
        Assert.assertEquals(elements.size(),20);

        action.clickAndHold(elements.get(0)).moveToElement(elements.get(3)).release().perform();
        sleepInSeconds(2);

        List<WebElement> elements1 = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(elements1.size(),4);
    }

    @Test
    public void TC_04_Click_Hold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ui-selectee"));
        Assert.assertEquals(elements.size(),20);

        action.keyDown(Keys.CONTROL);

        action.click(elements.get(0))
                .click(elements.get(3))
                .click(elements.get(5))
                .click(elements.get(6))
                .perform();
        List<WebElement> elements1 = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(elements1.size(),4);

    }

    @Test
    public void TC_05_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.id("demo")).isDisplayed());
    }

    @Test
    public void TC_06_Right_Click(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover")).isDisplayed());

        action.click(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover"))).perform();
        driver.switchTo().alert().dismiss();
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        sleepInSeconds(2);


    }

    @Test
    public void TC_07_Drag_Drop_Html4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
        sleepInSeconds(2);
        String color = Color.fromString(driver.findElement(By.id("droptarget")).getCssValue("background-color")).asHex().toLowerCase();
        Assert.assertEquals(color,"#03a9f4");


    }


    @AfterClass
    public void afterClass(){
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

}
