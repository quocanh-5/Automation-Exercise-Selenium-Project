package Test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class SubscriptionTest extends BaseTest {

    @Test
    public void TC_11_verifySubscriptionInHomePage() {
        // Pre-condition: Browser is on the Home page (Đã mở sẵn nhờ BaseTest)
        HomePage home = new HomePage(driver);

        // Step 1: Scroll down to Footer
        home.scrollToSubscription();

        // Step 2: Enter email in Subscription input
        home.enterSubscriptionEmail("sub_home@test.com");

        // Step 3: Click the arrow button
        home.clickSubscribeButton();

        // Expected Result: Success message "You have been successfully subscribed!" is visible.
        Assert.assertTrue(home.isSubscribeSuccessMsgVisible(),
                "Subscription success message is NOT visible!");
    }
}