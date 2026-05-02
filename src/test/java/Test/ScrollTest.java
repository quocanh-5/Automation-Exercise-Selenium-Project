package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class ScrollTest extends BaseTest {

    @Test
    public void TC_26_verifyScrollUpUsingArrowButton() throws InterruptedException {
        HomePage home = new HomePage(driver);

        // ==========================================
        // TEST STEPS
        // ==========================================

        // Step 1: Scroll down to the bottom
        // Sử dụng hàm đã viết sẵn từ trước để cuộn xuống khu vực Footer
        home.scrollToSubscription();

        // Step 2: Verify 'SUBSCRIPTION' footer text
        Assert.assertTrue(home.isSubscriptionHeadingVisible(),
                "'SUBSCRIPTION' heading is NOT visible at the bottom!");

        // Step 3: Click the Arrow button (bottom right) to scroll up
        home.clickScrollUpArrow();

        // --- XỬ LÝ ANIMATION CUỘN TRANG ---
        // Nút mũi tên sử dụng JavaScript để tạo hiệu ứng "cuộn mượt" (smooth scroll).
        // Phải mất khoảng 1 giây để màn hình chạy từ dưới lên trên cùng.
        // Ta dùng Thread.sleep() một chút để chờ animation kết thúc trước khi verify.
        Thread.sleep(1500);

        // Expected Result: Page scrolls up smoothly. Header text is visible.
        Assert.assertTrue(home.isTopHeaderTextVisible(),
                "Top header text 'Full-Fledged practice website...' is NOT visible after scrolling up!");
    }
    @Test
    public void TC_27_verifyScrollUpWithoutArrowButton() throws InterruptedException {
        HomePage home = new HomePage(driver);

        // ==========================================
        // TEST STEPS
        // ==========================================

        // Step 1: Scroll down to the bottom
        // Cuộn xuống khu vực Footer
        home.scrollToSubscription();

        // Step 2: Verify 'SUBSCRIPTION' footer text
        Assert.assertTrue(home.isSubscriptionHeadingVisible(),
                "'SUBSCRIPTION' heading is NOT visible at the bottom!");

        // Step 3: Manually scroll up to top using mouse/trackpad
        // Gọi hàm Javascript để cuộn thẳng lên tọa độ (0,0) - tức là trên cùng của trang
        home.scrollToTop();

        // Tạm dừng một chút để giao diện trang web ổn định sau khi cuộn
        Thread.sleep(1000);

        // Expected Result: Header text "Full-Fledged practice website..." is visible at the top.
        Assert.assertTrue(home.isTopHeaderTextVisible(),
                "Top header text 'Full-Fledged practice website...' is NOT visible after scrolling up manually!");
    }
}