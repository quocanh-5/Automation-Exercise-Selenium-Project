package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Tiêu đề của danh sách sản phẩm (dùng để xác nhận trang hiển thị đúng)
    By allProductsTitle = By.xpath("//h2[text()='All Products']");

    // Nút "View Product" của sản phẩm đầu tiên trong danh sách
    By firstProductViewBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");

    // Kiểm tra danh sách sản phẩm đã được hiển thị
    public boolean isProductsListVisible() {
        return isDisplayed(allProductsTitle);
    }

    // Click vào xem chi tiết sản phẩm đầu tiên
    public ProductDetailPage clickViewFirstProduct() {
        click(firstProductViewBtn);
        return new ProductDetailPage(driver);
    }

    // ==========================================
    // CÁC ELEMENT DÀNH CHO TÍNH NĂNG SEARCH
    // ==========================================
    By searchInput = By.id("search_product");
    By searchBtn = By.id("submit_search");

    // Tiêu đề sau khi search (Có thể viết hoa hoặc thường tùy DOM giao diện)
    By searchedProductsTitle = By.xpath("//h2[text()='Searched Products' or text()='SEARCHED PRODUCTS']");

    // Locator lấy ra danh sách tên của TẤT CẢ các sản phẩm đang hiển thị
    By productNames = By.xpath("//div[@class='productinfo text-center']/p");


    // ==========================================
    // CÁC ACTION DÀNH CHO TÍNH NĂNG SEARCH
    // ==========================================

    // Hàm thực hiện tìm kiếm
    public void searchForProduct(String productName) {
        sendKeys(searchInput, productName);
        click(searchBtn);
    }

    // Hàm kiểm tra tiêu đề "Searched Products" có hiển thị không
    public boolean isSearchedProductsTitleVisible() {
        return isDisplayed(searchedProductsTitle);
    }

    // Hàm kiểm tra xem toàn bộ sản phẩm trả về có chứa từ khóa không
    public boolean areAllProductsRelatedToSearch(String keyword) {
        // Lấy ra danh sách toàn bộ các thẻ chứa tên sản phẩm
        java.util.List<org.openqa.selenium.WebElement> products = driver.findElements(productNames);

        // Nếu không tìm thấy sản phẩm nào thì trả về false
        if (products.isEmpty()) {
            return false;
        }

        // Duyệt qua từng sản phẩm để kiểm tra tên
        for (org.openqa.selenium.WebElement product : products) {
            String name = product.getText().toLowerCase(); // Chuyển về chữ thường để so sánh cho chuẩn
            if (!name.contains(keyword.toLowerCase())) {
                System.out.println("Sản phẩm không khớp: " + name);
                return false; // Phát hiện 1 sản phẩm không chứa từ khóa là false luôn
            }
        }
        return true; // Tất cả đều chứa từ khóa
    }

    // ==========================================
    // CÁC ELEMENT DÀNH CHO TÍNH NĂNG ADD TO CART
    // ==========================================
    By firstProduct = By.xpath("(//div[@class='product-image-wrapper'])[1]");
    By firstProductAddToCartBtn = By.xpath("(//div[@class='product-image-wrapper'])[1]//a[contains(@class, 'add-to-cart')]");

    By secondProduct = By.xpath("(//div[@class='product-image-wrapper'])[2]");
    By secondProductAddToCartBtn = By.xpath("(//div[@class='product-image-wrapper'])[2]//a[contains(@class, 'add-to-cart')]");

    By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");
    By viewCartLinkModal = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");

    // ==========================================
    // CÁC ACTION DÀNH CHO TÍNH NĂNG ADD TO CART
    // ==========================================

    public void hoverAndClickFirstProductAddToCart() {
        hover(firstProduct);
        click(firstProductAddToCartBtn);
    }

    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

    public void hoverAndClickSecondProductAddToCart() {
        hover(secondProduct);
        click(secondProductAddToCartBtn);
    }

    public CartPage clickViewCartOnModal() {
        click(viewCartLinkModal);
        return new CartPage(driver);
    }
}