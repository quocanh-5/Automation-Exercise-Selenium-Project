package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    @Test
    public void TC_13_addProductsInCart() {
        HomePage home = new HomePage(driver);

        // Step 1: Go to 'Products'
        ProductsPage productsPage = home.goToProductsPage();

        // Step 2: Hover over 1st product, click 'Add to cart'
        productsPage.hoverAndClickFirstProductAddToCart();

        // Step 3: Click 'Continue Shopping'
        productsPage.clickContinueShopping();

        // Step 4: Hover over 2nd product, click 'Add to cart'
        productsPage.hoverAndClickSecondProductAddToCart();

        // Step 5: Click 'View Cart'
        CartPage cartPage = productsPage.clickViewCartOnModal();

        // Expected Result 1: Both products are added to the cart
        Assert.assertEquals(cartPage.getNumberOfProductsInCart(), 2,
                "Số lượng sản phẩm trong giỏ hàng không đúng!");

        // Expected Result 2: Prices, quantities, and total prices are calculated correctly
        Assert.assertTrue(cartPage.verifyPricesQuantitiesAndTotal(),
                "Giá tiền, số lượng hoặc tổng tiền tính toán bị sai!");
    }

    @Test
    public void TC_14_verifyProductQuantityInCart() {
        HomePage home = new HomePage(driver);

        // Pre-condition: Browser is on the Home page
        // Đi tới trang Products để có thể chọn 1 sản phẩm bất kỳ
        ProductsPage productsPage = home.goToProductsPage();

        // Step 1: Open 'View Product' for any item (Ở đây chọn sản phẩm đầu tiên)
        ProductDetailPage productDetailPage = productsPage.clickViewFirstProduct();

        // Step 2: Change Quantity to 4
        String expectedQuantity = "4";
        productDetailPage.setQuantity(expectedQuantity);

        // Step 3: Click 'Add to cart'
        productDetailPage.clickAddToCart();

        // Step 4: Click 'View Cart'
        CartPage cartPage = productDetailPage.clickViewCartOnModal();

        // Expected Result: The product is displayed in the cart with the exact Quantity = 4.
        Assert.assertEquals(cartPage.getFirstProductQuantity(), expectedQuantity,
                "Số lượng sản phẩm trong giỏ hàng không khớp với số lượng đã nhập!");
    }
    @Test
    public void TC_18_removeProductsFromCart() {
        HomePage home = new HomePage(driver);

        // ==========================================
        // PRE-CONDITION: Cart contains at least 1 product
        // ==========================================
        ProductsPage productsPage = home.goToProductsPage();
        productsPage.hoverAndClickFirstProductAddToCart();

        // Mở trang giỏ hàng (Step 1 của kịch bản)
        CartPage cartPage = productsPage.clickViewCartOnModal();

        // Xác nhận chắc chắn đã có sản phẩm trong giỏ trước khi xóa
        Assert.assertTrue(cartPage.getNumberOfProductsInCart() > 0,
                "Pre-condition failed: Giỏ hàng đang trống, không có gì để xóa!");

        // ==========================================
        // TEST STEPS & EXPECTED RESULT
        // ==========================================

        // Step 2: Click the 'X' (Delete) button next to the product
        cartPage.clickDeleteProduct();

        // Expected Result: The product is successfully removed from the cart. "Cart is empty" is visible.
        Assert.assertTrue(cartPage.isCartEmptyMsgVisible(),
                "'Cart is empty!' message is NOT visible after deleting product!");
    }
    @Test
    public void TC_23_addToCartFromRecommendedItems() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: Scroll down to 'RECOMMENDED ITEMS' section
        home.scrollToRecommendedItems();

        // Step 2: Click 'Add to cart' on any item (ở đây chọn item đầu tiên)
        // Nhờ hàm click bọc JavascriptExecutor ở BasePage, click sẽ luôn thành công
        // kể cả khi có hiệu ứng trượt của thanh carousel sản phẩm
        home.clickRecommendedItemAddToCart();

        // Step 3: Click 'View Cart'
        CartPage cartPage = home.clickViewCartOnModal();

        // Expected Result: The recommended product is successfully added to the cart
        Assert.assertTrue(cartPage.getNumberOfProductsInCart() > 0,
                "Lỗi: Không tìm thấy sản phẩm nào trong giỏ hàng sau khi thêm từ Recommended Items!");
    }
}