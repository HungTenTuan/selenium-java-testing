package webdriver;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Command_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_IsDisplayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()){
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation testing");
            System.out.println("Email is displayed");
        }else {
            System.out.println("Email is not displayed");
        }

        if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()){
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation testing");
            System.out.println("Textarea is displayed");
        }else {
            System.out.println("Textarea is not displayed");
        }

        if ( driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()){
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            System.out.println("Input is displayed");
        }else {
            System.out.println("Input is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name: User5 is displayed");
        }else {
            System.out.println("Element is not displayed");
        }

        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
    }

    @Test
    public void TC_02_IsEnabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Check enable
        if (driver.findElement(By.id("mail")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("under_18")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("edu")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("job1")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("job2")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("development")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("slider-1")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        // Check disable
        if (driver.findElement(By.id("disable_password")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("radio-disabled")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("bio")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("job3")).isEnabled()) {
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("check-disbaled")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        if (driver.findElement(By.id("slider-2")).isEnabled()){
            System.out.println("Element is enable");
        }else {
            System.out.println("Element is disable");
        }

        Assert.assertTrue(driver.findElement(By.id("mail")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("under_18")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("job1")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("job2")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("development")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("slider-1")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("disable_password")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("radio-disabled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("bio")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("job3")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("check-disbaled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("slider-2")).isEnabled());

    }

    @Test
    public void TC_03_IsSelected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();

        if( driver.findElement(By.id("under_18")).isSelected()){
            System.out.println("Element is selected");
        }else {
            System.out.println("Element is de-selected");
        }

        if( driver.findElement(By.id("java")).isSelected()){
            System.out.println("Element is selected");
        }else {
            System.out.println("Element is de-selected");
        }

        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
    }

    @Test
    public void TC_04_Mailchimp(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("Hung1912200x@gmail.com");
        driver.findElement(By.xpath("//label[@title='Show Password']")).click();

        //One lowercase character
        driver.findElement(By.id("new_password")).sendKeys("a");
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //One uppercase character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("A");
        sleepInSeconds(4);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //One number
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("1");
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        //One special character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("@");
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //8 characters minimum
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("12345678");
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Must not contain username
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("aB1@5678");
        sleepInSeconds(2);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }


    //thuc hanh lai case nay bang so sanh text
    @Test
    public void TC_05_Invalid_Login(){
        // empty email and password
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//span[text()='Login']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");

        // invalid email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@123.13");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

        // password <6 characters
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

        //incorrect password or email
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456789123");
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg>ul>li>span")).getText(),"Invalid login or password.");

    }

    @Test
    public void TC_06_Login_Succses(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        String firstname = "Nguyen", middlename = "Dinh", lastname = "Hung",
                email_address = randomEmail(), password = "123456789", confirmation = "123456789";

        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("middlename")).sendKeys(middlename);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("email_address")).sendKeys(email_address);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(confirmation);
        driver.findElement(By.xpath("//span[text()='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(),"Hello,"+" " + firstname + " " + middlename + " " + lastname + "!" );

        String content = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div[@class='box-content']/p")).getText();
        Assert.assertTrue(content.contains(firstname + " " + middlename + " " + lastname));
        Assert.assertTrue(content.contains(email_address));


        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("product-collection-image-3")).click();
        sleepInSeconds(4);

        driver.findElement(By.xpath("//span[@class='separator']//following-sibling::a[text()='Add Your Review']")).click();
        sleepInSeconds(2);

        driver.findElement(By.id("Quality 1_5")).click();
        driver.findElement(By.id("review_field")).sendKeys("Good application");
        driver.findElement(By.id("summary_field")).sendKeys("Best phone");
        driver.findElement(By.id("nickname_field")).clear();
        driver.findElement(By.id("nickname_field")).sendKeys("Call me Fin");
        driver.findElement(By.xpath("//span[text()='Submit Review']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Your review has been accepted for moderation.");

        // Logout
        driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
    }

    @Test
    public void TC_07_Handle_TextBox_Textare(){

        /*driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        sleepInSeconds(2);

        driver.findElement(By.id("//span[text()='PIM']//parent::a")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[text()='Add Employee']//parent::li")).click();
        sleepInSeconds(2);

        // ???
        driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div[@class='']/input")).getAttribute("value");
        */

        Select a = new Select( driver.findElement(By.xpath("//input[@name='username']")));
        


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

    public String randomEmail(){
        return "Hung" + new Random().nextInt(9999) + "@gmail.com";
    }

}
