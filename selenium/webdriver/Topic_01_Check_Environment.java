package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
    WebDriver driver;

    @Test
    public void TC_01_Run_On_Fiefox() {
        driver = new FirefoxDriver();
        ///acb
        driver.get("https://v2d2c.staging-ureruad.com/admin/campaigns/edit_design/13305");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
       driver = new ChromeDriver();
       driver.get("https://v2d2c.staging-ureruad.com/admin/campaigns/edit_design/13305");
       driver.quit();
    }

    @Test
    public void TC_03_Run_On_Edge() {
       driver = new EdgeDriver();
       driver.get("https://v2d2c.staging-ureruad.com/admin/campaigns/edit_design/13305");
       driver.quit();
    }

}