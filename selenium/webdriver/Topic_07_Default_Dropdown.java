package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {
    WebDriver driver;
    String FirstName ="Dinh", LastName ="Hung", DateOfBirthDay="10", DateOfBirthMonth="April", DateOfBirthYear="1914",
            Email= ranDomEmail(), Company="JV-IT", Password="123456789";


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        sleepInSeconds(2);

        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys(FirstName);
        driver.findElement(By.id("LastName")).sendKeys(LastName);

        WebElement day = driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        WebElement month = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        WebElement year = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        Select selectDay = new Select(day);
        selectDay.selectByVisibleText(DateOfBirthDay);
        Select selectMonth = new Select(month);
        selectMonth.selectByVisibleText(DateOfBirthMonth);
        Select selectYear = new Select(year);
        selectYear.selectByVisibleText(DateOfBirthYear);

        driver.findElement(By.id("Email")).sendKeys(Email);
        driver.findElement(By.id("Company")).sendKeys(Company);
        driver.findElement(By.id("Password")).sendKeys(Password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(Password);

        Assert.assertFalse(new Select(day).isMultiple());
        Assert.assertFalse(new Select(month).isMultiple());
        Assert.assertFalse(new Select(year).isMultiple());

        Assert.assertEquals(selectDay.getOptions().size(),32);
        Assert.assertEquals(selectMonth.getOptions().size(),13);
        Assert.assertEquals(selectYear.getOptions().size(),112);

        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSeconds(2);


        Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),FirstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),LastName);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),DateOfBirthDay);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),DateOfBirthMonth);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),DateOfBirthYear);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),Email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),Company);
    }

    @Test
    public void TC_02_Custom_Dropdown_Require_Jquery(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
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

    public String ranDomEmail(){
        return FirstName + new Random().nextInt(9999) + "@gmail.com";
    }
}
