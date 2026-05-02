package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryProductsPage extends BasePage {

    public CategoryProductsPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // ELEMENTS
    // ==========================================
    // Thẻ Tiêu đề nằm ở giữa trang (VD: "WOMEN - DRESS PRODUCTS")
    By categoryPageTitle = By.xpath("//h2[@class='title text-center']");

    // Các nút trên thanh Category Sidebar dành cho Men
    By menCategory = By.xpath("//a[@href='#Men']");
    By menTshirtsSubCategory = By.xpath("//div[@id='Men']//a[contains(text(), 'Tshirts')]");

    // ==========================================
    // ACTIONS
    // ==========================================
    public String getCategoryPageTitle() {
        return getText(categoryPageTitle);
    }

    public void clickMenCategory() {
        click(menCategory);
    }

    // Click xong trang sẽ tải lại để hiển thị sản phẩm Tshirts
    public CategoryProductsPage clickTshirtsUnderMen() {
        click(menTshirtsSubCategory);
        return new CategoryProductsPage(driver);
    }
}