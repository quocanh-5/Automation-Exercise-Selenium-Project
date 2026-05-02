package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    By nameOnCard = By.name("name_on_card");
    By cardNumber = By.name("card_number");
    By cvc = By.name("cvc");
    By expiryMonth = By.name("expiry_month");
    By expiryYear = By.name("expiry_year");
    By payAndConfirmBtn = By.id("submit");
    By orderPlacedText = By.xpath("//b[text()='Order Placed!']");

    public void fillPaymentDetails(String name, String cardNo, String cvcNum, String expMonth, String expYear) {
        sendKeys(nameOnCard, name);
        sendKeys(cardNumber, cardNo);
        sendKeys(cvc, cvcNum);
        sendKeys(expiryMonth, expMonth);
        sendKeys(expiryYear, expYear);
    }

    public void clickPayAndConfirmOrder() {
        click(payAndConfirmBtn);
    }

    public boolean isOrderPlacedMsgVisible() {
        return isDisplayed(orderPlacedText);
    }
    // ==========================================
    // ELEMENTS (Bổ sung cho hóa đơn)
    // ==========================================
    By downloadInvoiceBtn = By.xpath("//a[contains(@href, 'download_invoice')]");
    By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    // ==========================================
    // ACTIONS (Bổ sung cho hóa đơn)
    // ==========================================
    public void clickDownloadInvoice() {
        click(downloadInvoiceBtn);
    }

    public HomePage clickContinueButton() {
        click(continueBtn);
        return new HomePage(driver);
    }
}