package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    // Locator bắt thẻ tiêu đề "Test Cases" để xác nhận trang đã tải thành công
    By pageTitle = By.xpath("//b[contains(text(), 'Test Cases')]");

    // Hàm kiểm tra tiêu đề trang có hiển thị hay không
    public boolean isPageTitleVisible() {
        return isDisplayed(pageTitle);
    }

}