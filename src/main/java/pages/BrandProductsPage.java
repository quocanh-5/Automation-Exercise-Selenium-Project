package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrandProductsPage extends BasePage {

    public BrandProductsPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // ELEMENTS
    // ==========================================
    // Locator bắt thẻ Tiêu đề (VD: "BRAND - POLO PRODUCTS")
    By brandPageTitle = By.xpath("//h2[@class='title text-center']");

    // Locator cho thương hiệu thứ hai (H&M) ở thanh menu bên trái
    By hmBrand = By.xpath("//a[@href='/brand_products/H&M']");

    // ==========================================
    // ACTIONS
    // ==========================================

    public String getBrandPageTitle() {
        return getText(brandPageTitle);
    }

    // Click chuyển sang xem thương hiệu H&M
    public BrandProductsPage clickHMBrand() {
        click(hmBrand);
        return new BrandProductsPage(driver);
    }
}