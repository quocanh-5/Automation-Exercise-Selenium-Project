package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // 1. SIGNUP ELEMENTS
    // ==========================================
    By nameInput = By.name("name");
    By emailInput = By.xpath("//input[@data-qa='signup-email']");
    By signupBtn = By.xpath("//button[@data-qa='signup-button']");

    // ==========================================
    // 2. LOGIN ELEMENTS (THÊM MỚI Ở ĐÂY)
    // ==========================================
    By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    By loginBtn = By.xpath("//button[@data-qa='login-button']");
    By loginErrorMsg = By.xpath("//p[text()='Your email or password is incorrect!']");

    // ==========================================
    // CÁC HÀM XỬ LÝ (ACTIONS)
    // ==========================================

    public SignUpPage signup(String name, String email) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        click(signupBtn);
        return new SignUpPage(driver);
    }

    // Hàm thực hiện đăng nhập
    public HomePage login(String email, String password) {
        sendKeys(loginEmailInput, email);
        sendKeys(loginPasswordInput, password);
        click(loginBtn);
        return new HomePage(driver);
    }

    // Hàm kiểm tra thông báo lỗi khi đăng nhập sai (Dành cho TC_03)
    public boolean isLoginErrorVisible() {
        return isDisplayed(loginErrorMsg);
    }
    public boolean isLoginBtnVisible() {
        return isDisplayed(loginBtn);
    }

    // Thêm locator này vào phần 1. SIGNUP ELEMENTS
    By signupErrorMsg = By.xpath("//p[text()='Email Address already exist!']");

    // Thêm hàm này vào phần ACTIONS (dưới hàm signup)
    public boolean isSignupErrorVisible() {
        return isDisplayed(signupErrorMsg);
    }
}