package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryProductsPage;
import pages.HomePage;

public class CategoryTest extends BaseTest {

    @Test
    public void TC_19_viewCategoryProducts() {
        // Pre-condition: Browser is on the Home page
        HomePage home = new HomePage(driver);

        // Step 1: View 'Category' section on left sidebar
        Assert.assertTrue(home.isCategorySidebarVisible(), "Category sidebar is NOT visible on Home page!");

        // Step 2: Click 'Women' -> Click 'Dress'
        home.clickWomenCategory();
        CategoryProductsPage categoryPage = home.clickDressUnderWomen();

        // Expected Result 1: Page title updates to "WOMEN - DRESS PRODUCTS"
        Assert.assertEquals(categoryPage.getCategoryPageTitle(), "WOMEN - DRESS PRODUCTS",
                "Page title does not match for Women > Dress!");

        // Step 3: Click 'Men' -> Click 'Tshirts'
        categoryPage.clickMenCategory();

        // Gán lại chính categoryPage để cập nhật trạng thái trang sau khi web tải lại
        categoryPage = categoryPage.clickTshirtsUnderMen();

        // Expected Result 2: Page title updates to "MEN - TSHIRTS PRODUCTS"
        Assert.assertEquals(categoryPage.getCategoryPageTitle(), "MEN - TSHIRTS PRODUCTS",
                "Page title does not match for Men > Tshirts!");
    }
}