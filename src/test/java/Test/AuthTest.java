package Test;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

public class AuthTest extends BaseTest {

    // Hàm tạo email ngẫu nhiên để không bao giờ bị trùng khi test Đăng ký
    private String generateRandomEmail() {
        return "qatest_" + System.currentTimeMillis() + "@test.com";
    }

    @Test
    public void TC_01_registerUser() {
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.goToLoginPage();

        String randomEmail = generateRandomEmail(); // Dùng email ngẫu nhiên
        SignUpPage signUpPage = loginPage.signup("QA Tester", randomEmail);

        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "15", "May", "1990",
                "QA", "Tester", "123 Main St", "Canada",
                "Ontario", "Toronto", "12345", "0123456789"
        );

        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Account Created message is not visible!");

        // TEST DỪNG TẠI ĐÂY. Trình duyệt tự đóng qua hàm teardown() của BaseTest.
    }

    @Test
    public void TC_02_deleteAccount() {
        // PRE-CONDITION: Để có tài khoản mà xóa, ta phải tạo nó trước (hoặc dùng tài khoản cố định)
        // Để giữ tính độc lập, mình tạo 1 tài khoản mới tinh rồi lập tức xóa nó.
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.goToLoginPage();

        String tempEmail = generateRandomEmail();
        SignUpPage signUpPage = loginPage.signup("User For Delete", tempEmail);
        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "1", "January", "2000",
                "First", "Last", "Address", "India",
                "State", "City", "00000", "000000000"
        );

        // TEST BẮT ĐẦU:
        accountPage.clickContinue(); // Về trang chủ

        // Thực hiện hành động xóa
        accountPage.deleteAccount();

        // Kiểm tra kết quả
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account Deleted message is not visible!");

    }

    @Test
    public void TC_03_loginWithCorrectEmailAndPassword() {
        // LƯU Ý (Pre-condition): Để TC_02 pass, email "valid_user@test.com" BẮT BUỘC phải tồn tại.
        // Bạn có thể đăng ký bằng tay một tài khoản có email/pass tương tự trước khi chạy test,
        // và lưu ý nếu ở Step 5 bạn xóa tài khoản thì lần sau chạy lại sẽ bị báo lỗi.

        HomePage home = new HomePage(driver);

        // Step 1 & 2: Click 'Signup / Login'
        LoginPage loginPage = home.goToLoginPage();

        // Step 3 & 4: Enter correct Email & Password -> Click 'Login'
        // Truyền vào Email và Password đúng
        home = loginPage.login("valid_user@test.com", "Aa@123456");

        // Expected Result 1: "Logged in as username" is visible.
        // Truyền vào tên (Username) tương ứng với tài khoản trên
        Assert.assertTrue(home.isUserLoggedIn("Valid User"), "User is not logged in!");

        // Step 5: Click 'Delete Account'
        AccountPage accountPage = home.clickDeleteAccount();

        // Expected Result 2: Account is deleted
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account Deleted message is not visible!");
    }

    @Test
    public void TC_04_loginWithIncorrectEmailAndPassword() {
        HomePage home = new HomePage(driver);

        // Step 1 & 2: Click 'Signup / Login'
        LoginPage loginPage = home.goToLoginPage();

        // Step 3 & 4: Enter INCORRECT Email & Password -> Click 'Login'
        // Bạn có thể truyền bất kỳ email/pass sai nào vào đây
        loginPage.login("incorrect_email@test.com", "WrongPassword123");

        // Expected Result: Error 'Your email or password is incorrect!' is visible.
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Login error message is not visible!");
    }

    @Test
    public void TC_05_logoutUser() {
        // ==========================================
        // PRE-CONDITION: User is currently logged in
        // ==========================================
        // Để test case chạy độc lập và ổn định, ta tạo nhanh một user mới để nó tự động Login
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.goToLoginPage();

        String tempEmail = generateRandomEmail();
        SignUpPage signUpPage = loginPage.signup("Logout User", tempEmail);
        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "1", "January", "2000",
                "First", "Last", "Address", "India",
                "State", "City", "00000", "000000000"
        );
        accountPage.clickContinue(); // Sau bước này, tài khoản mới đã được Logged In trên HomePage

        // Xác nhận đã đăng nhập thành công trước khi test Logout
        Assert.assertTrue(home.isUserLoggedIn("Logout User"), "Pre-condition failed: User is not logged in!");

        // ==========================================
        // TEST STEPS & EXPECTED RESULT
        // ==========================================
        // Step 1: Click 'Logout' button on the header.
        loginPage = home.logout();

        // Expected Result: User is redirected to the Login page. Session is cleared.
        Assert.assertTrue(loginPage.isLoginBtnVisible(), "Logout failed: User is not redirected to Login page!");

        // Bạn cũng có thể kiểm tra thêm URL để chắc chắn 100%
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "URL does not match login page!");
    }

    @Test
    public void TC_06_registerUserWithExistingEmail() {
        // ==========================================
        // PRE-CONDITION: An account with the target email already exists.
        // ==========================================
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.goToLoginPage();

        // Tạo 1 tài khoản ngẫu nhiên để chắc chắn email này đã tồn tại trong database
        String existingEmail = generateRandomEmail();
        SignUpPage signUpPage = loginPage.signup("First User", existingEmail);
        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "1", "January", "2000",
                "First", "Last", "Address", "India",
                "State", "City", "00000", "000000000"
        );

        accountPage.clickContinue(); // Về lại trang chủ sau khi tạo tài khoản thành công
        home.logout(); // Đăng xuất để quay về trạng thái chưa đăng nhập

        // ==========================================
        // TEST STEPS & EXPECTED RESULT
        // ==========================================
        // Step 1 & 2: Go to Home page -> Click 'Signup / Login'
        // (Sau khi logout ở trên, hệ thống tự động đưa ta về thẳng trang Login)

        // Step 3 & 4: Enter Name and an already registered Email -> Click 'Signup'.
        // Ta sử dụng lại chính existingEmail vừa tạo thành công ở trên
        loginPage.signup("Tester", existingEmail);

        // Expected Result: Error message "Email Address already exist!" is visible.
        Assert.assertTrue(loginPage.isSignupErrorVisible(), "Error message 'Email Address already exist!' is not visible!");
    }


}