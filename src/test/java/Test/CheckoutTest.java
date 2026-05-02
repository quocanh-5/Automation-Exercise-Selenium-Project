package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    private String generateDynamicEmail() {
        return "e2e_14_" + System.currentTimeMillis() + "@test.com";
    }

    @Test
    public void TC_15_placeOrderRegisterWhileCheckout() {
        HomePage home = new HomePage(driver);

        // Step 1: Add product to cart -> View Cart
        ProductsPage productsPage = home.goToProductsPage();
        productsPage.hoverAndClickFirstProductAddToCart();
        CartPage cartPage = productsPage.clickViewCartOnModal();

        // Step 2: Click 'Proceed To Checkout'
        cartPage.clickProceedToCheckout();

        // Step 3: Prompted to Register/Login -> Click 'Register'
        LoginPage loginPage = cartPage.clickRegisterLoginOnCheckoutModal();

        // Step 4: Fill signup details
        String dynamicEmail = generateDynamicEmail();
        SignUpPage signUpPage = loginPage.signup("QA User", dynamicEmail);

        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "10", "October", "1995",
                "QA", "User", "123 Main St", "United States",
                "New York", "NYC", "10001", "1234567890"
        );

        // Xác nhận tạo tài khoản thành công và trở về trang chủ
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Không thấy thông báo tạo tài khoản thành công!");
        accountPage.clickContinue();

        // Step 5: Go to Cart -> Proceed Checkout
        cartPage = home.goToCartPage();
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckoutLoggedIn();

        // Step 6: Enter Payment -> Pay
        checkoutPage.enterComment("Please deliver between 9 AM and 5 PM.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        paymentPage.fillPaymentDetails(
                "QA Card",
                "123456789",
                "123",
                "12",
                "2026"
        );
        paymentPage.clickPayAndConfirmOrder();

        // Expected Result 1: Order completed successfully ("ORDER PLACED!").
        Assert.assertTrue(paymentPage.isOrderPlacedMsgVisible(), "'ORDER PLACED!' message is not visible!");

        // Step 7: Click 'Delete Account'
        accountPage = home.clickDeleteAccount();

        // Expected Result 2: Account is deleted afterwards.
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' message is not visible!");
        accountPage.clickContinue(); // Hoàn tất dọn dẹp data rác
    }
    @Test
    public void TC_16_placeOrderRegisterBeforeCheckout() {
        HomePage home = new HomePage(driver);

        // ==========================================
        // Step 1: Signup a new account
        // ==========================================
        LoginPage loginPage = home.goToLoginPage();

        // Dùng email động để test case luôn chạy pass ở nhiều lần chạy khác nhau
        String dynamicEmail = "e2e_15_" + System.currentTimeMillis() + "@test.com";
        SignUpPage signUpPage = loginPage.signup("QA User", dynamicEmail);

        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "10", "October", "1995",
                "QA", "User", "123 Main St", "United States",
                "New York", "NYC", "10001", "1234567890"
        );

        // Xác nhận tạo tài khoản thành công
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Không thấy thông báo tạo tài khoản thành công!");

        // Click Continue để về trang chủ (lúc này tài khoản đã được tự động Logged In)
        accountPage.clickContinue();

        // ==========================================
        // Step 2: Add product to cart -> View Cart
        // ==========================================
        ProductsPage productsPage = home.goToProductsPage();
        productsPage.hoverAndClickFirstProductAddToCart();
        CartPage cartPage = productsPage.clickViewCartOnModal();

        // ==========================================
        // Step 3: Click 'Proceed To Checkout'
        // ==========================================
        // Vì user ĐÃ LOGIN ở Step 1, click Checkout sẽ chuyển thẳng sang CheckoutPage
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckoutLoggedIn();

        // ==========================================
        // Step 4: Enter Payment -> Pay
        // ==========================================
        checkoutPage.enterComment("Order placed after registering.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        paymentPage.fillPaymentDetails(
                "QA Card",
                "123456789",
                "123",
                "12",
                "2026"
        );
        paymentPage.clickPayAndConfirmOrder();

        // Expected Result 1: Order completed successfully ("ORDER PLACED!").
        Assert.assertTrue(paymentPage.isOrderPlacedMsgVisible(), "'ORDER PLACED!' message is not visible!");

        // ==========================================
        // Step 5: Click 'Delete Account'
        // ==========================================
        accountPage = home.clickDeleteAccount();

        // Expected Result 2: Account is deleted afterwards.
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' message is not visible!");
        accountPage.clickContinue(); // Hoàn tất dọn dẹp data rác để đưa web về trạng thái sạch
    }
    @Test
    public void TC_24_verifyAddressDetailsInCheckoutPage() {
        HomePage home = new HomePage(driver);

        // ==========================================
        // Step 1: Signup account and fill specific Address details
        // ==========================================
        LoginPage loginPage = home.goToLoginPage();

        // Setup Data
        String dynamicEmail = "address_test_" + System.currentTimeMillis() + "@test.com";
        String firstName = "Sherlock";
        String lastName = "Holmes";
        String expectedAddress = "No 10, Baker Street";
        String expectedState = "NY";
        String expectedCity = "New York";

        SignUpPage signUpPage = loginPage.signup(firstName + " " + lastName, dynamicEmail);
        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "1", "January", "1990",
                firstName, lastName, expectedAddress, "United States",
                expectedState, expectedCity, "10001", "1234567890"
        );

        // Xác nhận tạo tài khoản thành công
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Account not created!");
        accountPage.clickContinue(); // Về trang chủ, trạng thái Logged In

        // ==========================================
        // Step 2: Add product to cart
        // ==========================================
        ProductsPage productsPage = home.goToProductsPage();
        productsPage.hoverAndClickFirstProductAddToCart();
        CartPage cartPage = productsPage.clickViewCartOnModal();

        // ==========================================
        // Step 3: Proceed to Checkout
        // ==========================================
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckoutLoggedIn();

        // ==========================================
        // Step 4: Compare addresses
        // ==========================================
        String deliveryAddressText = checkoutPage.getDeliveryAddressFullText();
        String billingAddressText = checkoutPage.getBillingAddressFullText();

        // Expected Result: Delivery Address and Billing Address displayed on the Checkout page exactly match
        // Kiểm tra khối Delivery Address
        Assert.assertTrue(deliveryAddressText.contains(expectedAddress),
                "Delivery address bị sai hoặc thiếu: " + expectedAddress);
        Assert.assertTrue(deliveryAddressText.contains(expectedState),
                "Delivery address bị sai hoặc thiếu State: " + expectedState);
        Assert.assertTrue(deliveryAddressText.contains(expectedCity),
                "Delivery address bị sai hoặc thiếu City: " + expectedCity);

        // Kiểm tra khối Billing Address
        Assert.assertTrue(billingAddressText.contains(expectedAddress),
                "Billing address bị sai hoặc thiếu: " + expectedAddress);
        Assert.assertTrue(billingAddressText.contains(expectedState),
                "Billing address bị sai hoặc thiếu State: " + expectedState);
        Assert.assertTrue(billingAddressText.contains(expectedCity),
                "Billing address bị sai hoặc thiếu City: " + expectedCity);

        // ==========================================
        // DỌN DẸP (CLEAN UP)
        // ==========================================
        // Xóa tài khoản để không để lại rác trên hệ thống test
        home.clickDeleteAccount().clickContinue();
    }
    @Test
    public void TC_25_downloadInvoiceAfterPurchaseOrder() throws InterruptedException {
        HomePage home = new HomePage(driver);

        // ==========================================
        // Step 1: Add product -> Proceed Checkout
        // ==========================================
        ProductsPage productsPage = home.goToProductsPage();
        productsPage.hoverAndClickFirstProductAddToCart();
        CartPage cartPage = productsPage.clickViewCartOnModal();
        cartPage.clickProceedToCheckout();

        // ==========================================
        // Step 2: Register new account -> Cart -> Checkout
        // ==========================================
        LoginPage loginPage = cartPage.clickRegisterLoginOnCheckoutModal();
        String dynamicEmail = "e2e_25_" + System.currentTimeMillis() + "@test.com";
        SignUpPage signUpPage = loginPage.signup("QA User", dynamicEmail);

        AccountPage accountPage = signUpPage.createAccount(
                "Aa@123456", "10", "October", "1995",
                "QA", "User", "123 Main St", "United States",
                "New York", "NYC", "10001", "1234567890"
        );
        accountPage.clickContinue(); // Về trang chủ sau khi tạo tài khoản

        cartPage = home.goToCartPage();
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckoutLoggedIn();

        // ==========================================
        // Step 3: Pay -> Click 'Download Invoice'
        // ==========================================
        checkoutPage.enterComment("Testing invoice download.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        paymentPage.fillPaymentDetails("QA Card", "123456789", "123", "12", "2026");
        paymentPage.clickPayAndConfirmOrder();

        Assert.assertTrue(paymentPage.isOrderPlacedMsgVisible(), "'ORDER PLACED!' message is not visible!");

        // --- XỬ LÝ KIỂM TRA FILE DOWNLOAD ---
        // Lấy đường dẫn thư mục Downloads mặc định của hệ điều hành
        String downloadPath = System.getProperty("user.home") + java.io.File.separator + "Downloads";
        java.io.File invoiceFile = new java.io.File(downloadPath, "invoice.txt");

        // Xóa file cũ (nếu có từ những lần chạy trước) để tránh việc Chrome tự đổi tên thành "invoice (1).txt"
        if (invoiceFile.exists()) {
            invoiceFile.delete();
        }

        // Click nút tải về
        paymentPage.clickDownloadInvoice();

        // Chờ tối đa 10 giây để file tải xong
        boolean isDownloaded = false;
        for (int i = 0; i < 10; i++) {
            if (invoiceFile.exists()) {
                isDownloaded = true;
                break;
            }
            Thread.sleep(1000); // Tạm dừng 1 giây mỗi vòng lặp
        }

        // Expected Result: Invoice file is downloaded successfully
        Assert.assertTrue(isDownloaded, "Lỗi: File invoice.txt không được tải xuống thành công!");

        // Dọn dẹp: Xóa file vừa tải để giữ sạch máy tính của bạn
        if (invoiceFile.exists()) {
            invoiceFile.delete();
        }

        // ==========================================
        // Step 4: Click 'Continue' -> Delete Account
        // ==========================================
        home = paymentPage.clickContinueButton();
        accountPage = home.clickDeleteAccount();

        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' message is not visible!");
        accountPage.clickContinue();
    }

}