package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_16_JavaScripExcutor {
    WebDriver driver;
    Actions actions;
    WebDriverWait webDriverWait;
    JavascriptExecutor jsExecutor;




    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
        // open page by url
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        sleepInSecond(3);
        String urlPage = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(urlPage,"http://live.techpanda.org/");
        sleepInSecond(2);

        // get domanin
        String domainPage = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(domainPage,"live.techpanda.org");
        sleepInSecond(1);

        //Open Mobie page
        jsExecutor.executeScript("arguments[0].click()",getElement("//a[text()='Mobile']"));
        sleepInSecond(1);

        // Add product SSung galaxy to cart
        jsExecutor.executeScript("arguments[0].click()",getElement("//img[@id='product-collection-image-3']//parent::a//following-sibling::div//button"));
        sleepInSecond(3);

        //verify msg
        String textExpected = "Samsung Galaxy was added to your shopping cart.";
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('Samsung Galaxy was added to your shopping cart.')[0];");
        Boolean result = textExpected.equals(textActual);
        Assert.assertTrue(result);

        // Click to open page Custome Service
        jsExecutor.executeScript("arguments[0].click()",getElement("//a[text()='Customer Service']"));
        sleepInSecond(1);
        // verify page by title
        String titleCustomeServicePage = (String) jsExecutor.executeScript("return document.title");
        Assert.assertEquals(titleCustomeServicePage,"Customer Service");

        // scroll to elememt Newletter textBox
        jsExecutor.executeScript("arguments[0].scrollIntoView(false)",getElement("//input[@id='newsletter']"));
        sleepInSecond(1);

        // highlight newsletter checkbox
        WebElement newsletterCheckbox = getElement("//input[@id='newsletter']");
        String originalStyle = newsletterCheckbox.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1])",newsletterCheckbox, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style','" + originalStyle + "')",newsletterCheckbox);
        sleepInSecond(1);

        // type value valid into checkbox
        jsExecutor.executeScript("arguments[0].setAttribute('value','"+ randomEmail() + "')",newsletterCheckbox);
        sleepInSecond(1);

        // click subscribe btn
        jsExecutor.executeScript("arguments[0].click()",getElement("//span[text()='Subscribe']"));
        sleepInSecond(3);

        // verify msg
        String textExpected2 = "Thank you for your subscription.";
        String textActual2 = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('Thank you for your subscription.')[0];");
        Boolean result2 = textExpected2.equals(textActual2);
        Assert.assertTrue(result2);

        // navigate to another page
        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");
        sleepInSecond(1);
        String domainFb = (String) jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(domainFb,"facebook.com");


    }

    @Test
    public void TC_02_(){
        // open browser
        jsExecutor.executeScript("window.location = 'https://automationfc.github.io/html5/index.html'");
        sleepInSecond(4);

        // verify url
        String urlHomePage = (String) jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(urlHomePage,"https://automationfc.github.io/html5/index.html");

        // verify domain
        String domainHomePage = (String) jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(domainHomePage,"automationfc.github.io");

        // click btn submit
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@class='btn']"));
        sleepInSecond(1);

        // verify msg Name field
        String validationMsgName = (String) jsExecutor.executeScript("return arguments[0].validationMessage",getElement("//input[@id='fname']"));
        Assert.assertEquals(validationMsgName,"Please fill out this field.");

        // sendkey into name textbox
        jsExecutor.executeScript("arguments[0].setAttribute('value','Hung')",getElement("//input[@id='fname']"));
        sleepInSecond(1);

        // click btn submit
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@class='btn']"));
        sleepInSecond(1);

        // verify msg Password field
        String validationMsgPass = (String) jsExecutor.executeScript("return arguments[0].validationMessage;",getElement("//input[@id='pass']"));
        Assert.assertEquals(validationMsgPass,"Please fill out this field.");

        // sendkey into pass textbox
        jsExecutor.executeScript("arguments[0].setAttribute('value','123456@123')",getElement("//input[@id='pass']"));
        sleepInSecond(1);

        // click btn submit
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@class='btn']"));
        sleepInSecond(1);

        // verify msg Email field
        String validationMsgEmail = (String) jsExecutor.executeScript("return arguments[0].validationMessage",getElement("//input[@id='em']"));
        Assert.assertEquals(validationMsgEmail,"Please fill out this field.");

        // sendkey into Email textbox
        jsExecutor.executeScript("arguments[0].setAttribute('value','" + randomEmail() + "')",getElement("//input[@id='em']"));
        sleepInSecond(1);

        // click btn submit
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@class='btn']"));
        sleepInSecond(1);

        // verify msg Address field
        String validationMsgAddress = (String) jsExecutor.executeScript("return arguments[0].validationMessage",getElement("//select"));
        Assert.assertEquals(validationMsgAddress,"Please select an item in the list.");

        new Select(getElement("//select")).selectByVisibleText("HA NOI");
        /*// choose option from dropdown
        jsExecutor.executeScript("arguments[0].value = 'HA NOI'; arguments[0].dispatchEvent(new Event('change'));",getElement("//select"));
        sleepInSecond(1);*/

        // click btn submit
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@class='btn']"));
        sleepInSecond(1);

        // verify new direction
        String textExpect = "405 Not Allowed";
        String textActual = (String)jsExecutor.executeScript("return document.documentElement.innerText.match('405 Not Allowed')[0];");
        Boolean result = textExpect.equals(textActual);
        Assert.assertTrue(result);

    }

    @Test
    public void TC_03_(){
        // access URL
        jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4'");
        sleepInSecond(4);

        // verify URL
        String urlHomePage = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(urlHomePage,"https://demo.guru99.com/v4/");
        sleepInSecond(1);

        // verify domain
        String domainHomePahe = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(domainHomePahe,"demo.guru99.com");
        sleepInSecond(1);

        // Sendkey into checkbox to login
        jsExecutor.executeScript("arguments[0].setAttribute('value','mngr589257')",getElement("//input[@name='uid']"));
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('value','EdUmygA')",getElement("//input[@name='password']"));
        sleepInSecond(2);

        // click btn login
        jsExecutor.executeScript("arguments[0].click()",getElement("//input[@name='btnLogin']"));
        sleepInSecond(2);

        // click btn new Customer
        jsExecutor.executeScript("arguments[0].click()",getElement("//a[text()='New Customer']"));
        sleepInSecond(2);

        // remove type date from datePicker
        jsExecutor.executeScript("arguments[0].setAttribute('value','09/11/2024')",getElement("//input[@id='dob']"));
        sleepInSecond(1);

    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }


    public String randomEmail(){
        return "Hung" + new Random().nextInt(999) + "@gmail.com";
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

}
