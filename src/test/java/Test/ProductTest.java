package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class ProductTest extends BaseTest {

    @Test
    public void TC_09_verifyAllProductsAndProductDetailPage() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: Click 'Products' menu
        ProductsPage productsPage = home.goToProductsPage();

        // Step 2: Verify Products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(),
                "Products list is NOT visible!");

        // Step 3: Click 'View Product' of the first item
        ProductDetailPage productDetailPage = productsPage.clickViewFirstProduct();

        // Expected Result: Product details (Name, Category, Price, Availability, Condition, Brand) are visible.
        Assert.assertTrue(productDetailPage.isProductDetailsVisible(),
                "Product detail information is missing or not fully visible!");
    }

    @Test
    public void TC_10_searchProduct() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: Go to 'Products'
        ProductsPage productsPage = home.goToProductsPage();

        // Step 2 & 3: Enter product name in search input -> Click Search icon
        String searchKeyword = "Sleeveless Dress";
        productsPage.searchForProduct(searchKeyword);

        // Expected Result 1: "SEARCHED PRODUCTS" heading is visible.
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(),
                "'SEARCHED PRODUCTS' heading is NOT visible!");

        // Expected Result 2: Only products matching the keyword are displayed.
        Assert.assertTrue(productsPage.areAllProductsRelatedToSearch(searchKeyword),
                "Có sản phẩm trong danh sách kết quả không khớp với từ khóa '" + searchKeyword + "'!");
    }
    @Test
    public void TC_21_searchProductsAndVerifyCartAfterLogin() {
        HomePage home = new HomePage(driver);

        // ==========================================
        // PRE-CONDITION: Not logged in. Valid account exists. Cart is empty.
        // ==========================================
        // Tạo nhanh 1 tài khoản mới để test case chạy độc lập và không phụ thuộc data cũ
        LoginPage loginPage = home.goToLoginPage();
        String dynamicEmail = "search_cart_" + System.currentTimeMillis() + "@test.com";
        String password = "Aa@123456";

        pages.SignUpPage signUpPage = loginPage.signup("QA User", dynamicEmail);
        pages.AccountPage accountPage = signUpPage.createAccount(
                password, "1", "January", "2000",
                "QA", "User", "Address", "United States",
                "State", "City", "00000", "000000000"
        );
        accountPage.clickContinue(); // Về trang chủ, lúc này tài khoản đang Logged In
        home.logout(); // Đăng xuất để giỏ hàng trống và đúng trạng thái "Not logged in"

        // ==========================================
        // TEST STEPS
        // ==========================================

        // Step 1: Go to Products -> Search product
        pages.ProductsPage productsPage = home.goToProductsPage();
        String searchKeyword = "Top";
        productsPage.searchForProduct(searchKeyword);

        // Xác nhận trang kết quả tìm kiếm đã hiển thị
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(), "Searched Products title is missing!");

        // Step 2: Add product to cart -> View Cart
        // (Hàm này sẽ tự động thêm sản phẩm đầu tiên trong danh sách kết quả tìm kiếm)
        productsPage.hoverAndClickFirstProductAddToCart();
        pages.CartPage cartPage = productsPage.clickViewCartOnModal();

        // Kiểm tra và lưu lại số lượng sản phẩm đang có trong giỏ khi CHƯA đăng nhập
        int productsInCartBeforeLogin = cartPage.getNumberOfProductsInCart();
        Assert.assertTrue(productsInCartBeforeLogin > 0, "Cart is empty before login!");

        // Step 3: Login to account
        // Dùng đối tượng HomePage mới để lấy thanh Header và sang trang Login
        home = new HomePage(driver);
        loginPage = home.goToLoginPage();
        home = loginPage.login(dynamicEmail, password);

        // Xác nhận đăng nhập thành công
        Assert.assertTrue(home.isUserLoggedIn("QA User"), "User is not logged in!");

        // Step 4: Navigate back to Cart
        cartPage = home.goToCartPage();

        // Expected Result: The added products remain in the cart even after the user logs in.
        int productsInCartAfterLogin = cartPage.getNumberOfProductsInCart();
        Assert.assertEquals(productsInCartAfterLogin, productsInCartBeforeLogin,
                "Lỗi: Số lượng sản phẩm trong giỏ hàng đã bị thay đổi (hoặc mất) sau khi đăng nhập!");

        // ==========================================
        // DỌN DẸP (CLEAN UP)
        // ==========================================
        // Xóa tài khoản để trả lại môi trường sạch sẽ
        home.clickDeleteAccount().clickContinue();
    }
    @Test
    public void TC_22_addReviewOnProduct() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: Go to Products -> Click 'View Product'
        ProductsPage productsPage = home.goToProductsPage();
        ProductDetailPage productDetailPage = productsPage.clickViewFirstProduct();

        // Step 2: Fill 'Write Your Review' form
        productDetailPage.fillReviewForm("Reviewer", "rev@test.com", "Good item!");

        // Step 3: Click 'Submit'
        productDetailPage.clickSubmitReview();

        // Expected Result: Success message "Thank you for your review." is visible.
        Assert.assertTrue(productDetailPage.isReviewSuccessMsgVisible(),
                "Thông báo gửi Review thành công không xuất hiện!");
    }
}
