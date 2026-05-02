package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // ELEMENTS (Sử dụng XPath động để tìm đúng các thẻ chứa text mong muốn)
    // ==========================================
    By productName = By.xpath("//div[@class='product-information']/h2");
    By productCategory = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    By productPrice = By.xpath("//div[@class='product-information']/span/span");
    By productAvailability = By.xpath("//div[@class='product-information']//b[text()='Availability:']/..");
    By productCondition = By.xpath("//div[@class='product-information']//b[text()='Condition:']/..");
    By productBrand = By.xpath("//div[@class='product-information']//b[text()='Brand:']/..");

    // ==========================================
    // ACTIONS
    // ==========================================

    // Hàm gom nhóm kiểm tra tất cả các thông tin chi tiết có được hiển thị hay không
    public boolean isProductDetailsVisible() {
        return isDisplayed(productName) &&
                isDisplayed(productCategory) &&
                isDisplayed(productPrice) &&
                isDisplayed(productAvailability) &&
                isDisplayed(productCondition) &&
                isDisplayed(productBrand);
    }

    // ==========================================
    // ELEMENTS (BỔ SUNG CHO TÍNH NĂNG ADD TO CART)
    // ==========================================
    By quantityInput = By.id("quantity");
    By addToCartBtn = By.xpath("//button[contains(@class, 'cart')]");
    By viewCartLinkModal = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");

    // ==========================================
    // ACTIONS (BỔ SUNG CHO TÍNH NĂNG ADD TO CART)
    // ==========================================

    // Xóa số lượng mặc định (1) và điền số lượng mới
    public void setQuantity(String quantity) {
        driver.findElement(quantityInput).clear();
        sendKeys(quantityInput, quantity);
    }

    // Click nút Thêm vào giỏ hàng
    public void clickAddToCart() {
        click(addToCartBtn);
    }

    // Click nút View Cart trên Modal thông báo thành công
    public CartPage clickViewCartOnModal() {
        click(viewCartLinkModal);
        return new CartPage(driver);
    }
    // ==========================================
    // ELEMENTS CHO TÍNH NĂNG ADD REVIEW
    // ==========================================
    By reviewNameInput = By.id("name");
    By reviewEmailInput = By.id("email");
    By reviewTextInput = By.id("review");
    By submitReviewBtn = By.id("button-review");
    By reviewSuccessMsg = By.xpath("//span[contains(text(), 'Thank you for your review.')]");

    // ==========================================
    // ACTIONS CHO TÍNH NĂNG ADD REVIEW
    // ==========================================

    // Hàm điền thông tin đánh giá
    public void fillReviewForm(String name, String email, String review) {
        sendKeys(reviewNameInput, name);
        sendKeys(reviewEmailInput, email);
        sendKeys(reviewTextInput, review);
    }

    // Hàm click nút gửi đánh giá
    public void clickSubmitReview() {
        click(submitReviewBtn);
    }

    // Hàm kiểm tra thông báo đánh giá thành công
    public boolean isReviewSuccessMsgVisible() {
        return isDisplayed(reviewSuccessMsg);
    }

}