package Homework5Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TakeAwayTest {
    WebDriver driver;

    @BeforeMethod
    public void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\DEV\\selenium_drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.takeaway.com/");
    }

    @Test
    public void takeAwayRestaurantTest() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        WebElement addressFillText = this.driver.findElement(By.id("imysearchstring"));
        addressFillText.sendKeys("Никола Ламбрев");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iautoCompleteDropDownContent")));
        this.driver.findElement(By.cssSelector("#iautoCompleteDropDownContent a")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@alt,'Forchetta')]")));
        WebElement myRestaurant = this.driver.findElement(By.xpath("//img[contains(@alt,'Forchetta')]"));

        String nameText = myRestaurant.getAttribute("alt");
        assertEquals(nameText, "La Forchetta Restaurant|Ресторант Ла Форкета - La Forchetta");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
