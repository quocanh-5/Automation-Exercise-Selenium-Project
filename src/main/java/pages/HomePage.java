package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }
    By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");
    public LoginPage goToLoginPage() {
        click(signupLoginBtn);
        return new LoginPage(driver);
    }
    // Hàm nhận vào tên User để kiểm tra xem trên Header có dòng "Logged in as [Tên]" không
    public boolean isUserLoggedIn(String username) {
        By loggedInUser = By.xpath("//a[contains(text(), 'Logged in as')]/b[text()='" + username + "']");
        return isDisplayed(loggedInUser);
    }

    // Click Delete Account trên thanh header và trang sẽ chuyển hướng thông báo "Account Deleted"
    public AccountPage clickDeleteAccount() {
        click(deleteAccountBtn);
        return new AccountPage(driver);
    }
    By logoutBtn = By.xpath("//a[contains(text(),'Logout')]");

    // Thêm hàm này vào phần Actions
    public LoginPage logout() {
        click(logoutBtn);
        return new LoginPage(driver);
    }
    // Locator cho nút Contact Us trên header
    By contactUsBtn = By.xpath("//a[contains(text(),'Contact us')]");

    // Hàm chuyển sang trang Contact Us
    public ContactUsPage goToContactUsPage() {
        click(contactUsBtn);
        return new ContactUsPage(driver);
    }
    // Locator cho nút Test Cases trên thanh header
    By testCasesBtn = By.xpath("//a[contains(text(),'Test Cases')]");

    // Hàm chuyển sang trang Test Cases
    public TestCasesPage goToTestCasesPage() {
        click(testCasesBtn);
        return new TestCasesPage(driver);
    }
    // Locator cho nút Products trên thanh header
    By productsBtn = By.xpath("//a[@href='/products']");

    // Hàm chuyển sang trang Products
    public ProductsPage goToProductsPage() {
        click(productsBtn);
        return new ProductsPage(driver);
    }

    // ==========================================
    // SUBSCRIPTION ELEMENTS (Phần Footer)
    // ==========================================
    // Lưu ý: ID thực tế trên trang web này viết sai chính tả thành "susbscribe_email", ta cần bắt chính xác ID này
    By subscriptionEmailInput = By.id("susbscribe_email");
    By subscribeBtn = By.id("subscribe");
    By successSubscribeMsg = By.id("success-subscribe");

    // ==========================================
    // SUBSCRIPTION ACTIONS
    // ==========================================

    // Hàm cuộn trang xuống khu vực Subscription
    public void scrollToSubscription() {
        org.openqa.selenium.WebElement element = driver.findElement(subscriptionEmailInput);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Hàm điền email
    public void enterSubscriptionEmail(String email) {
        sendKeys(subscriptionEmailInput, email);
    }

    // Hàm click nút mũi tên
    public void clickSubscribeButton() {
        click(subscribeBtn);
    }

    // Hàm kiểm tra thông báo đăng ký thành công
    public boolean isSubscribeSuccessMsgVisible() {
        return isDisplayed(successSubscribeMsg);
    }

    // Locator cho nút Cart trên thanh header
    By cartBtn = By.xpath("//a[contains(text(),'Cart')]");

    // Hàm chuyển sang trang Cart
    public CartPage goToCartPage() {
        click(cartBtn);
        return new CartPage(driver);
    }
    // ==========================================
    // CATEGORY SIDEBAR ELEMENTS (Trang chủ)
    // ==========================================
    By categorySidebar = By.id("accordian");
    By womenCategory = By.xpath("//a[@href='#Women']");
    By womenDressSubCategory = By.xpath("//div[@id='Women']//a[contains(text(), 'Dress')]");

    // ==========================================
    // CATEGORY SIDEBAR ACTIONS
    // ==========================================

    public boolean isCategorySidebarVisible() {
        return isDisplayed(categorySidebar);
    }

    public void clickWomenCategory() {
        // Cuộn chuột xuống một chút để menu nằm trong tầm nhìn trước khi click
        org.openqa.selenium.WebElement element = driver.findElement(categorySidebar);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        click(womenCategory);
    }

    // Click xong chuyển hướng sang trang Category Products
    public CategoryProductsPage clickDressUnderWomen() {
        click(womenDressSubCategory);
        return new CategoryProductsPage(driver);
    }
    // ==========================================
    // BRANDS SIDEBAR ELEMENTS (Trang chủ)
    // ==========================================
    By brandsSidebar = By.xpath("//div[@class='brands_products']");
    By poloBrand = By.xpath("//a[@href='/brand_products/Polo']");

    // ==========================================
    // BRANDS SIDEBAR ACTIONS
    // ==========================================

    public boolean isBrandsSidebarVisible() {
        return isDisplayed(brandsSidebar);
    }

    public BrandProductsPage clickPoloBrand() {
        // Cuộn chuột xuống để menu Brands hiển thị rõ trên màn hình
        org.openqa.selenium.WebElement element = driver.findElement(brandsSidebar);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        click(poloBrand);
        return new BrandProductsPage(driver);
    }
    // ==========================================
    // RECOMMENDED ITEMS ELEMENTS
    // ==========================================
    // Locator tiêu đề "RECOMMENDED ITEMS"
    By recommendedItemsHeading = By.xpath("//h2[text()='recommended items' or text()='RECOMMENDED ITEMS']");

    // Locator nút "Add to cart" của sản phẩm đầu tiên trong danh sách đề xuất
    By firstRecommendedItemAddToCartBtn = By.xpath("(//div[@class='recommended_items']//a[contains(@class, 'add-to-cart')])[1]");

    // Locator nút "View Cart" trên Modal (Popup) báo thành công
    By viewCartLinkModal = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");

    // ==========================================
    // RECOMMENDED ITEMS ACTIONS
    // ==========================================

    // Hàm cuộn chuột xuống khu vực sản phẩm đề xuất
    public void scrollToRecommendedItems() {
        org.openqa.selenium.WebElement element = driver.findElement(recommendedItemsHeading);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Hàm click thêm sản phẩm đề xuất vào giỏ
    public void clickRecommendedItemAddToCart() {
        click(firstRecommendedItemAddToCartBtn);
    }

    // Hàm click View Cart trên Popup
    public CartPage clickViewCartOnModal() {
        click(viewCartLinkModal);
        return new CartPage(driver);
    }
    // ==========================================
    // ELEMENTS CHO TÍNH NĂNG SCROLL
    // ==========================================
    // Tiêu đề "SUBSCRIPTION" ở dưới Footer
    By subscriptionHeading = By.xpath("//h2[text()='Subscription']");

    // Nút mũi tên cuộn lên trên cùng (nằm ở góc dưới bên phải)
    By scrollUpArrowBtn = By.id("scrollUp");

    // Dòng chữ hiển thị ở Carousel trên cùng của trang web
    By topHeaderText = By.xpath("//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]");

    // ==========================================
    // ACTIONS CHO TÍNH NĂNG SCROLL
    // ==========================================

    // Kiểm tra tiêu đề SUBSCRIPTION có hiển thị không
    public boolean isSubscriptionHeadingVisible() {
        return isDisplayed(subscriptionHeading);
    }

    // Click vào nút mũi tên cuộn lên
    public void clickScrollUpArrow() {
        click(scrollUpArrowBtn);
    }

    // Kiểm tra dòng chữ trên cùng có hiển thị không
    public boolean isTopHeaderTextVisible() {
        return isDisplayed(topHeaderText);
    }
    // Hàm mô phỏng thao tác cuộn chuột thủ công lên trên cùng của trang
    public void scrollToTop() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}
