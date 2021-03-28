package Homework6Demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;

public class GuestOrder {

    WebDriver driver;

    public void GuestOrderTests() {
    }

    @BeforeMethod
    public void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\DEV\\selenium_drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("http://shop.pragmatic.bg/index.php?route=product/product&product_id=40");
    }

    @Test
    public void successfulPlacementOfGuestOrder() {
        WebElement addToCartButton = this.driver.findElement(By.id("button-cart"));
        addToCartButton.click();
        WebElement miniShoppingCart = this.driver.findElement(By.cssSelector("#cart button.btn-block"));
        miniShoppingCart.click();
        WebDriverWait wait = new WebDriverWait(this.driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.pull-right div a:nth-of-type(2)")));
        WebElement miniCartCheckoutLink = this.driver.findElement(By.cssSelector("ul.pull-right div a:nth-of-type(2)"));
        miniCartCheckoutLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.radio:nth-of-type(2) input")));
        WebElement guestRadioButton = this.driver.findElement(By.cssSelector("div.radio:nth-of-type(2) input"));
        guestRadioButton.click();
        WebElement continueButton = this.driver.findElement(By.id("button-account"));
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-guest")));
        this.driver.findElement(By.id("input-payment-firstname")).sendKeys(new CharSequence[]{"NqkvoFirstName"});
        this.driver.findElement(By.id("input-payment-lastname")).sendKeys(new CharSequence[]{"NqkyvLastName"});
        String randomEmail = RandomStringUtils.randomAlphanumeric(7) + "@blabla.com";
        this.driver.findElement(By.id("input-payment-email")).sendKeys(new CharSequence[]{randomEmail});
        this.driver.findElement(By.id("input-payment-telephone")).sendKeys(new CharSequence[]{"1234567890"});
        this.driver.findElement(By.id("input-payment-address-1")).sendKeys(new CharSequence[]{"nqkav adress 1"});
        this.driver.findElement(By.id("input-payment-city")).sendKeys(new CharSequence[]{"Mugla"});
        this.driver.findElement(By.id("input-payment-postcode")).sendKeys(new CharSequence[]{"1234"});
        Select countryDropDown = new Select(this.driver.findElement(By.id("input-payment-country")));
        countryDropDown.selectByValue("33");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("input-payment-zone"), "Yambol"));
        Select regionDropDown = new Select(this.driver.findElement(By.id("input-payment-zone")));
        regionDropDown.selectByValue("496");
        this.driver.findElement(By.id("button-guest")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method")));
        WebElement fillField = this.driver.findElement(By.xpath("//textarea[@name='comment']"));
        fillField.click();
        fillField.sendKeys("I'm at home after 17.30 pm.");
        this.driver.findElement(By.id("button-shipping-method")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-method")));
        this.driver.findElement(By.xpath("//input[@name='agree']")).click();
        this.driver.findElement(By.id("button-payment-method")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm")));
        this.driver.findElement(By.id("button-confirm")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > p:nth-of-type(1)")));

        Assert.assertEquals(this.driver.findElement(By.cssSelector("#content > h1")).getText(), "Your order has been placed!");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
