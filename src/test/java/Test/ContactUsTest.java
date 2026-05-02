package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

import java.io.File;

public class ContactUsTest extends BaseTest {

    @Test
    public void TC_07_contactUsForm() {
        HomePage home = new HomePage(driver);

        // Step 1: Click 'Contact Us'
        ContactUsPage contactUsPage = home.goToContactUsPage();

        // [Xử lý File] Tạo tự động một file dummy để test chức năng Upload
        File tempFile = new File("test_image.png");
        try {
            tempFile.createNewFile(); // Tạo file ảo trong thư mục gốc của project
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 2 & 3: Fill Name, Email, Subject, Message và Upload file
        contactUsPage.fillContactForm(
                "QA Tester",
                "qa_tester@test.com",
                "Support Issue",
                "Need help with order",
                tempFile.getAbsolutePath() // Bắt buộc phải truyền đường dẫn TUYỆT ĐỐI
        );

        // Step 4: Click 'Submit'
        contactUsPage.clickSubmit();

        // Step 5: Click OK on alert popup
        contactUsPage.acceptAlert();

        // Expected Result 1: "Success! Your details have been submitted successfully." is visible.
        Assert.assertTrue(contactUsPage.isSuccessMsgVisible(), "Success message is not visible!");

        // Step 6: Click 'Home'
        home = contactUsPage.clickHomeButton();

        // Expected Result 2: Redirected back to Home
        // Xác nhận URL hiện tại đã quay trở về trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/", "Not redirected to Home page!");

        // Dọn dẹp: Xóa file tạm sau khi đã test xong
        if(tempFile.exists()) {
            tempFile.delete();
        }
    }
}