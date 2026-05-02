package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrandProductsPage;
import pages.HomePage;

public class BrandTest extends BaseTest {

    @Test
    public void TC_20_viewBrandProducts() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: View 'Brands' section on left sidebar
        Assert.assertTrue(home.isBrandsSidebarVisible(), "Brands sidebar is NOT visible on Home page!");

        // Step 2: Click any brand (e.g., Polo)
        BrandProductsPage brandPage = home.clickPoloBrand();

        // Expected Result 1: Page title updates to "BRAND - POLO PRODUCTS"
        Assert.assertEquals(brandPage.getBrandPageTitle(), "BRAND - POLO PRODUCTS",
                "Page title does not match for Polo brand!");

        // Step 3: Click another brand (e.g., H&M)
        // Cập nhật lại đối tượng brandPage sau khi tải lại trang
        brandPage = brandPage.clickHMBrand();

        // Expected Result 2: Page title updates to "BRAND - H&M PRODUCTS"
        Assert.assertEquals(brandPage.getBrandPageTitle(), "BRAND - H&M PRODUCTS",
                "Page title does not match for H&M brand!");
    }
}