Here's the complete README.md — just copy everything between the lines:

---

```markdown
# SauceDemo Automation Framework

Automated test suite for [SauceDemo](https://www.saucedemo.com) covering Login, Cart, and Checkout flows using Selenium WebDriver with Java and TestNG.

---

## 🛠️ Tech Stack

| Tool | Version |
|------|---------|
| Java | JDK 18 |
| Selenium WebDriver | 4.21.0 |
| TestNG | 7.10.2 |
| WebDriverManager | 5.8.0 |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

---

## 📁 Project Structure

```
saucedemo-automation/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── base/
│   │   │   └── BaseTest.java
│   │   ├── constants/
│   │   │   └── Constants.java
│   │   ├── pages/
│   │   │   ├── LoginPage.java
│   │   │   ├── InventoryPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/
│   │       └── DriverManager.java
│   └── test/java/com/saucedemo/
│       └── tests/
│           ├── LoginTest.java
│           ├── CartTest.java
│           └── CheckoutTest.java
├── testngXmls/
│   └── testng.xml
├── pom.xml
└── README.md
```

---

## ✅ Test Coverage

### 🔐 Login Tests
| Test | Description |
|------|-------------|
| `testValidLogin` | Valid credentials redirect to Products page |
| `testInvalidLogin` | Invalid credentials show error message |

### 🛒 Cart Tests
| Test | Description |
|------|-------------|
| `testAddToCart` | Add item to cart updates badge count to 1 |
| `testCartPageItemCount` | Cart page displays correct number of items |

### 💳 Checkout Tests
| Test | Description |
|------|-------------|
| `testFullCheckoutFlow` | End-to-end: login → add item → cart → checkout → order confirmation |

---

## 🚀 How to Run

### Prerequisites
- Java JDK 18+
- Maven 3.8+
- Google Chrome (latest)
- IntelliJ IDEA (recommended)

### Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/saucedemo-automation.git
cd saucedemo-automation
```

### Run all tests via Maven
```bash
mvn test
```

### Run a specific test class
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=CartTest
mvn test -Dtest=CheckoutTest
```

### Run via IntelliJ
Right-click `testngXmls/testng.xml` → **Run**

---

## 🏗️ Design Patterns

### Page Object Model (POM)
Each page of the application has a dedicated class containing:
- Element locators (By)
- Methods to interact with the page

```
LoginPage     → open(), login(), isErrorDisplayed(), getErrorMessage()
InventoryPage → isLoaded(), addFirstItemToCart(), goToCart(), getCartCount()
CartPage      → getCartItemCount(), proceedToCheckout()
CheckoutPage  → fillShippingInfo(), finishOrder(), getConfirmationMessage()
```

### BaseTest
Centralized setup and teardown using TestNG `@BeforeMethod` and `@AfterMethod`.
Each test gets a fresh browser instance — no state shared between tests.

### Constants
All test data in one place:
- Base URL
- Valid/Invalid credentials
- Expected page titles and messages

### ThreadLocal WebDriver
Supports safe parallel test execution — each thread gets its own driver instance.

---

## 🧪 Test Results

```
Tests run : 5
Passed    : 5
Failed    : 0
Skipped   : 0
Duration  : ~1 min 20 sec
```

---

## 🌐 Application Under Test

**SauceDemo** — https://www.saucedemo.com

A demo e-commerce site designed for automation practice.

| User Type | Username | Password |
|-----------|----------|----------|
| Standard User | standard_user | secret_sauce |
| Locked Out User | locked_out_user | secret_sauce |
| Problem User | problem_user | secret_sauce |

---

## 📋 Future Enhancements

- [ ] Add ExtentReports for HTML test reports
- [ ] Add data-driven testing with Excel/CSV
- [ ] Add screenshot on test failure
- [ ] Integrate with GitHub Actions CI/CD
- [ ] Add Docker + Selenium Grid support
- [ ] Cross-browser testing (Firefox, Edge)

---

## 👤 Author

Vyshnavi H G
QA Automation Engineer 1
[GitHub](https://github.com/Vyshnaviganesh) | [LinkedIn](https://www.linkedin.com/in/vyshnavi-h-g-6747991a0/)

---

## 📄 License

This project is for personal learning and portfolio purposes.
```
