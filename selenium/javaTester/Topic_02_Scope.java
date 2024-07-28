package javaTester;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Scope {
    //-	Các biến được khai báo bên trong class được gọi là biến Global ( bên ngoài hàm ) -> biến toàn cục
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
    }

    @Test
    public void TC_01_(){
    //-	Các biến được khai báo bên trong một hàm được gọi là biến local ( hoặc trong một block code) -> biến cục bộ

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
}
