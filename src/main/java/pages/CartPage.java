package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Locator bắt lấy tất cả các dòng sản phẩm (dòng <tr>) trong giỏ hàng
    By cartProductRows = By.xpath("//tbody/tr");

    // Lấy số lượng sản phẩm có trong giỏ hàng
    public int getNumberOfProductsInCart() {
        return driver.findElements(cartProductRows).size();
    }

    // Kiểm tra giá, số lượng và tổng tiền của từng sản phẩm có chính xác không
    public boolean verifyPricesQuantitiesAndTotal() {
        List<WebElement> rows = driver.findElements(cartProductRows);

        for (int i = 1; i <= rows.size(); i++) {
            // Lấy Text của Giá, Số lượng và Tổng tiền từng dòng dựa theo index i
            String priceText = getText(By.xpath("//tbody/tr[" + i + "]//td[@class='cart_price']/p"));
            String quantityText = getText(By.xpath("//tbody/tr[" + i + "]//td[@class='cart_quantity']/button"));
            String totalText = getText(By.xpath("//tbody/tr[" + i + "]//td[@class='cart_total']/p"));

            // Dùng Regex xóa tất cả chữ cái và ký tự đặc biệt (VD: "Rs. 500" -> 500)
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
            int quantity = Integer.parseInt(quantityText.trim());
            int total = Integer.parseInt(totalText.replaceAll("[^0-9]", ""));

            // Kiểm tra logic toán học
            if (price * quantity != total) {
                System.out.println("Lỗi tính toán ở dòng " + i + ": Giá " + price + " * SL " + quantity + " != Tổng " + total);
                return false; // Nếu 1 dòng sai thì lập tức đánh Fail test case
            }
        }
        return true; // Tất cả đều tính đúng
    }

    // Lấy số lượng (Quantity) của sản phẩm đầu tiên trong giỏ hàng
    public String getFirstProductQuantity() {
        // Chỉ đích danh ô số lượng của dòng đầu tiên (tr[1])
        return getText(By.xpath("//tbody/tr[1]//td[@class='cart_quantity']/button"));
    }

    // ==========================================
    // ELEMENTS CHO LUỒNG CHECKOUT
    // ==========================================
    By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    By registerLoginModalLink = By.xpath("//div[@class='modal-content']//a[@href='/login']");

    // ==========================================
    // ACTIONS CHO LUỒNG CHECKOUT
    // ==========================================

    // Click nút Tiến hành thanh toán
    public void clickProceedToCheckout() {
        click(proceedToCheckoutBtn);
    }

    // Click nút Đăng ký/Đăng nhập trên Popup khi chưa có tài khoản
    public LoginPage clickRegisterLoginOnCheckoutModal() {
        click(registerLoginModalLink);
        return new LoginPage(driver);
    }

    // Khi đã đăng nhập, click Checkout sẽ chuyển thẳng sang trang Checkout
    public CheckoutPage clickProceedToCheckoutLoggedIn() {
        click(proceedToCheckoutBtn);
        return new CheckoutPage(driver);
    }
    // ==========================================
    // ELEMENTS CHO LUỒNG XÓA SẢN PHẨM (REMOVE PRODUCT)
    // ==========================================
    By deleteProductBtn = By.className("cart_quantity_delete");
    By emptyCartMsg = By.xpath("//b[text()='Cart is empty!']");

    // ==========================================
    // ACTIONS CHO LUỒNG XÓA SẢN PHẨM
    // ==========================================

    // Click vào nút 'X' để xóa sản phẩm đầu tiên
    public void clickDeleteProduct() {
        click(deleteProductBtn);
    }

    // Kiểm tra dòng thông báo giỏ hàng trống có xuất hiện không
    public boolean isCartEmptyMsgVisible() {
        // Nhờ implicit wait 10s cài đặt sẵn ở BaseTest,
        // Selenium sẽ tự động chờ một chút cho đến khi text này xuất hiện
        return isDisplayed(emptyCartMsg);
    }
}