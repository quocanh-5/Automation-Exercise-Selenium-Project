package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // 1. Account Information Elements
    By titleMrRadio = By.id("id_gender1");
    By titleMrsRadio = By.id("id_gender2");
    By password = By.id("password");
    By daysDropdown = By.id("days");
    By monthsDropdown = By.id("months");
    By yearsDropdown = By.id("years");
    By newsletterCheckbox = By.id("newsletter");
    By optinCheckbox = By.id("optin");

    // 2. Address Information Elements
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By company = By.id("company");
    By address1 = By.id("address1");
    By address2 = By.id("address2");
    By countryDropdown = By.id("country");
    By state = By.id("state");
    By city = By.id("city");
    By zipcode = By.id("zipcode");
    By mobileNumber = By.id("mobile_number");

    By createAccountBtn = By.xpath("//button[@data-qa='create-account']");


    public AccountPage createAccount(String pass, String day, String month, String year,
                                     String fName, String lName, String addr, String country,
                                     String stateName, String cityName, String zip, String mobile) {

        // Chọn danh xưng (Ví dụ mặc định chọn Mr)
        click(titleMrRadio);

        // Điền mật khẩu
        sendKeys(password, pass);

        // Xử lý Dropdown list (Ngày, tháng, năm)
        selectByVisibleText(daysDropdown, day);
        selectByVisibleText(monthsDropdown, month);
        selectByVisibleText(yearsDropdown, year);

        // Checkbox (Tùy chọn)
        click(newsletterCheckbox);
        click(optinCheckbox);

        // Điền thông tin địa chỉ bắt buộc
        sendKeys(firstName, fName);
        sendKeys(lastName, lName);
        sendKeys(address1, addr);

        // Xử lý Dropdown list (Quốc gia)
        selectByVisibleText(countryDropdown, country);

        sendKeys(state, stateName);
        sendKeys(city, cityName);
        sendKeys(zipcode, zip);
        sendKeys(mobileNumber, mobile);

        // Click tạo tài khoản
        click(createAccountBtn);

        return new AccountPage(driver);
    }
}