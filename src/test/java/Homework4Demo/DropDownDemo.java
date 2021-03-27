package Homework4Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class DropDownDemo {

    WebDriver driver;

    @BeforeMethod
            public void setUp() {

    System.setProperty("webdriver.chrome.driver","C:\\DEV\\selenium_drivers\\chromedriver.exe");
    driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg/admin/");
    }

    @Test

    public void testDropdown() {


        WebElement userInput = driver.findElement(By.id("input-username"));
        userInput.sendKeys("admin");

        WebElement passInput = driver.findElement(By.id("input-password"));
        passInput.sendKeys(("parola123!"));

        WebElement pressLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        pressLogin.click();

        WebElement salesMenu = driver.findElement(By.cssSelector("#menu-sale > a"));
        salesMenu.click();

        WebElement menuOrder = driver.findElement(By.cssSelector("#collapse4 > li:nth-of-type(1)"));
        menuOrder.click();

        WebElement dropDown = driver.findElement(By.id("input-order-status"));
        dropDown.click();

        Select orderStatus = new Select(dropDown);

        assertFalse(orderStatus.isMultiple());

        assertEquals(orderStatus.getOptions().size(), 16);

        List<String> expectedOrders = Arrays.asList(new String[]{
                "",
                "Missing Orders",
                "Canceled",
                "Canceled Reversal",
                "Chargeback",
                "Complete",
                "Denied",
                "Expired",
                "Failed",
                "Pending",
                "Processed",
                "Processing",
                "Refunded",
                "Reversed",
                "Shipped",
                "Voided"});
        List<String> actualOrders = new ArrayList<>();

        List<WebElement> allOrders = orderStatus.getOptions();

        for(WebElement option : allOrders) {
            actualOrders.add(option.getText());
        }
        assertEquals(actualOrders, expectedOrders);
    }
}
