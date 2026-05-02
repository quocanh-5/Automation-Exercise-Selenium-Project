package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    By continueBtn = By.xpath("//a[@data-qa='continue-button']");
    By deleteBtn = By.xpath("//a[@href='/delete_account']");
    By accountDeletedText = By.xpath("//b[text()='Account Deleted!']");

    // ==========================================
    // 2 HÀM BẠN LỠ XÓA ĐÃ ĐƯỢC KHÔI PHỤC Ở ĐÂY
    // ==========================================

    public boolean isAccountCreatedVisible() {
        return isDisplayed(accountCreatedText);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    // ==========================================

    public void deleteAccount() {
        click(deleteBtn);
    }

    public boolean isAccountDeletedVisible() {
        return isDisplayed(accountDeletedText);
    }

    public boolean isAccountDeletedMsgVisible() {
        return isDisplayed(accountDeletedText);
    }
}