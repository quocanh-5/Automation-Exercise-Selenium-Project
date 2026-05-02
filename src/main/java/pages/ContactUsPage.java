package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // ELEMENTS
    // ==========================================
    By nameInput = By.name("name");
    By emailInput = By.name("email");
    By subjectInput = By.name("subject");
    By messageInput = By.id("message");
    By uploadFileInput = By.name("upload_file"); // Nút chọn file
    By submitBtn = By.name("submit");
    By successMsg = By.xpath("//div[contains(@class, 'status alert alert-success')]");
    By homeBtn = By.xpath("//a[contains(text(), 'Home') and contains(@class, 'btn-success')]");

    // ==========================================
    // ACTIONS
    // ==========================================

    // Hàm điền thông tin và đính kèm file
    public void fillContactForm(String name, String email, String subject, String message, String filePath) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        sendKeys(subjectInput, subject);
        sendKeys(messageInput, message);

        // Trong Selenium, upload file chỉ đơn giản là sendKeys đường dẫn tuyệt đối của file vào thẻ input type="file"
        sendKeys(uploadFileInput, filePath);
    }

    public void clickSubmit() {
        click(submitBtn);
    }

    // Xử lý Popup Alert mặc định của trình duyệt
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessMsgVisible() {
        return isDisplayed(successMsg);
    }

    public HomePage clickHomeButton() {
        click(homeBtn);
        return new HomePage(driver);
    }
}