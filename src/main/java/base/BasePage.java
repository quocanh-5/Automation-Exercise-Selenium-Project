package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }
    //common actions
    public void click(By locator) {
        WebElement element = driver.findElement(locator);
        try {
            // Cố gắng click bình thường trước
            element.click();
        } catch (Exception e) {
            // Nếu bị chặn (như lỗi quảng cáo), dùng JS để ép click
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }
    public void sendKeys(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }
    public String getText(By locator){
        return driver.findElement(locator).getText();
    }
    public boolean isDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
    }
    public void selectByVisibleText(By locator, String text){
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }
    // Hàm di chuột (Hover) vào một phần tử
    public void hover(By locator) {
        WebElement element = driver.findElement(locator);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).perform();
    }
}
