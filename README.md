# 🚀 Automation Exercise Selenium Framework

## 📌 Overview

This project is a **UI automation testing framework** built with **Selenium WebDriver (Java)** following the **Page Object Model (POM)** design pattern.

It automates end-to-end user scenarios on:
👉 https://automationexercise.com/

The goal of this project is to:

* Practice real-world automation testing
* Build a scalable and maintainable test framework
* Demonstrate SDET-level thinking in test design

---

## 🛠️ Tech Stack

| Category       | Tool / Framework   |
| -------------- | ------------------ |
| Language       | Java               |
| Automation     | Selenium WebDriver |
| Test Framework | TestNG             |
| Build Tool     | Maven              |
| IDE            | IntelliJ IDEA      |

---

## 🧠 Design Principles

### 🔹 Page Object Model (POM)

Each page is represented as a class containing:

* Web elements (locators)
* User actions (methods)

✔ Test classes DO NOT interact with WebDriver directly
✔ Page classes encapsulate UI behavior

---

### 🔹 Clean Test Flow

Example:

```java id="v2m91o"
HomePage home = new HomePage(driver);
LoginPage login = home.goToLoginPage();
```

👉 This ensures:

* Readable test scenarios
* Clear separation between test logic and UI logic

---

### 🔹 Navigation Handling

Each page method returns the next page:

```java id="93mb2q"
public LoginPage goToLoginPage() {
    click(loginBtn);
    return new LoginPage(driver);
}
```

👉 This models real user navigation and avoids invalid test states.

---

## 🧪 Test Coverage

### 🔐 Authentication

* Register user
* Login (valid / invalid)
* Logout

### 🛍️ Product

* View product list
* View product details
* Search products
* Add review

### 🛒 Cart

* Add to cart
* Update quantity
* Remove product

### 💳 Checkout

* Place order (multiple flows)
* Verify address
* Download invoice

### 🌐 Additional Features

* Contact form
* Subscription
* Category & brand navigation
* Scroll behavior

---

## ▶️ How to Run

### 1. Clone repository

```bash id="w5w4tn"
git clone https://github.com/quocanh-5/Automation-Exercise-Selenium-Project.git
```

### 2. Open project

* Open with IntelliJ IDEA or any Java IDE

### 3. Run tests

* Run TestNG test classes directly
  OR

```bash id="5r9mxf"
mvn clean test
```

---

## ⚙️ Current Limitations

This project is intentionally kept simple for learning purposes.
Current limitations include:

* Browser is hardcoded (Chrome)
* Uses implicit wait (not optimal for real-world scale)
* Test data is partially hardcoded
* No reporting integration

---

## 🚧 Future Improvements

Planned enhancements to reach production-level framework:

* Implement **DriverManager** (multi-browser support)
* Replace implicit wait with **explicit wait (WebDriverWait)**
* Add **ConfigReader** (environment-based configuration)
* Introduce **Test Data Object (DTO pattern)**
* Integrate reporting (Allure / Extent Report)
* Support parallel execution
* CI/CD integration (GitHub Actions)

---

## 👨‍💻 Author

**Tran Quoc Anh**

---

## 🎯 Purpose of This Project

This project is built to:

* Practice Selenium automation with real scenarios
* Understand framework design (not just scripting)
