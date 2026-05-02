package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    By commentTextarea = By.name("message");
    By placeOrderBtn = By.xpath("//a[contains(@href, '/payment')]");

    public void enterComment(String comment) {
        sendKeys(commentTextarea, comment);
    }

    public PaymentPage clickPlaceOrder() {
        click(placeOrderBtn);
        return new PaymentPage(driver);
    }
    // ==========================================
    // ADDRESS ELEMENTS
    // ==========================================
    By deliveryAddressBlock = By.id("address_delivery");
    By billingAddressBlock = By.id("address_invoice");

    // ==========================================
    // ADDRESS ACTIONS
    // ==========================================

    // Lấy toàn bộ Text của khối Delivery Address
    public String getDeliveryAddressFullText() {
        return getText(deliveryAddressBlock);
    }

    // Lấy toàn bộ Text của khối Billing Address
    public String getBillingAddressFullText() {
        return getText(billingAddressBlock);
    }
}