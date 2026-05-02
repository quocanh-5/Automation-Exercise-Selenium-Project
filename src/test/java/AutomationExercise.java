import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AutomationExercise {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void registerUser(){
        driver.navigate().to("https://automationexercise.com/");
        driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@data-qa=\"signup-name\"]")).sendKeys("tquocanh400@gmail.com");
        driver.findElement(By.xpath("//input[@data-qa=\"signup-email\"]")).sendKeys("tquocanh400@gmail.com");
        driver.findElement(By.xpath("//button[@data-qa=\"signup-button\"]")).click();

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.xpath("//input[@data-qa=\"password\"]")).sendKeys("tquocanh400@gmail.com");


    }
}
