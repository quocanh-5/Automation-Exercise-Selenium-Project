package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasesPage;

public class TestCasesTest extends BaseTest {

    @Test
    public void TC_08_verifyTestCasesPage() {
        // Pre-condition: Browser is on the Home page (Đã được xử lý ở BaseTest)
        HomePage home = new HomePage(driver);

        // Step 1: Click 'Test Cases' button on the header menu
        TestCasesPage testCasesPage = home.goToTestCasesPage();

        // Expected Result: Successfully navigated to the Test Cases list page.

        // Xác nhận 1: Dựa vào sự thay đổi của URL
        Assert.assertTrue(driver.getCurrentUrl().contains("/test_cases"),
                "Navigation failed: URL does not contain '/test_cases'");

        // Xác nhận 2: Dựa vào sự xuất hiện của tiêu đề trang trên giao diện
        Assert.assertTrue(testCasesPage.isPageTitleVisible(),
                "Navigation failed: Test Cases page title is not visible!");
    }
}