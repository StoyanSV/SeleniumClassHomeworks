package Homework3Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTestDemo {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\DEV\\selenium_drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg/admin");
    }

    @Test
    public void testAdminLogin() {
        WebElement userInput = driver.findElement(By.id("input-username"));
        userInput.sendKeys("admin");

        WebElement passInput = driver.findElement(By.id("input-password"));
        passInput.sendKeys(("parola123!"));

        WebElement pressLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        pressLogin.click();

        WebElement admin = driver.findElement(By.className("img-circle"));
        String titleText = admin.getAttribute("title");
        String nameText = admin.getAttribute("alt");

        Assert.assertEquals(titleText,"admin");

        Assert.assertEquals(nameText, "Milen Strahinski");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
