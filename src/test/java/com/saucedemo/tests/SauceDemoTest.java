package com.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class SauceDemoTest {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ Opens browser before every test
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ Closes browser after every test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // ==========================================
    // 🔐 TEST 1 — Valid Login
    // ==========================================
    @Test
    public void testValidLogin() {
        // Open website
        driver.get("https://www.saucedemo.com");

        // Type username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // Type password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Click login
        driver.findElement(By.id("login-button")).click();

        // Verify Products page loaded
        wait.until(ExpectedConditions.urlContains("inventory"));
        String title = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(title, "Products", "Should land on Products page");
    }

    // ==========================================
    // 🔐 TEST 2 — Invalid Login
    // ==========================================
    @Test
    public void testInvalidLogin() {
        // Open website
        driver.get("https://www.saucedemo.com");

        // Type wrong credentials
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();

        // Verify error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']")));
        Assert.assertTrue(
                driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed(),
                "Error message should appear"
        );
    }

    // ==========================================
    // 🛒 TEST 3 — Add to Cart
    // ==========================================
    @Test
    public void testAddToCart() {
        // Login first
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add first item to cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".inventory_item:first-child button"))).click();

        // Verify cart badge shows 1
        String cartCount = driver.findElement(
                By.cssSelector(".shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "1", "Cart should have 1 item");
    }

    // ==========================================
    // 🛒 TEST 4 — Cart Page Item Count
    // ==========================================
    @Test
    public void testCartPageItemCount() {
        // Login
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add item and go to cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".inventory_item:first-child button"))).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        // Verify 1 item in cart
        int itemCount = driver.findElements(By.cssSelector(".cart_item")).size();
        Assert.assertEquals(itemCount, 1, "Cart page should show 1 item");
    }

    // ==========================================
    // 💳 TEST 5 — Full Checkout Flow
    // ==========================================
    @Test
    public void testFullCheckoutFlow() {
        // Step 1 — Login
        driver.get("https://www.saucedemo.com");

        //Step 2 — Read credentials FROM the page itself
        // Get all usernames listed on the page
        String credentialsText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='login-credentials']"))).getText();

        // Get password from page
        String passwordText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='login-password']"))).getText();

        // Parse first username from the list
        // credentialsText looks like: "standard_user\nlocked_out_user\nproblem_user..."
        String firstUsername = credentialsText.split("\n")[0].trim();

        // Parse password — last line is "secret_sauce"
        String password = passwordText.split("\n")[passwordText.split("\n").length - 1].trim();

        System.out.println("Using Username: " + firstUsername);
        System.out.println("Using Password: " + password);

        // Step 3 — Login with credentials read from page
        driver.findElement(By.id("user-name")).sendKeys(firstUsername);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        //step 2 - By hardcoding username and password
        //driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //driver.findElement(By.id("login-button")).click();

        // Step 3 — Add item to cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".inventory_item:first-child button"))).click();

        // Step 4 — Go to cart
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        // Step 5 — Click checkout
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("checkout"))).click();

        // Step 6 — Fill shipping info
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first-name"))).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Step 7 — Finish order
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("finish"))).click();

        // Step 8 — Verify confirmation
        String confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".complete-header"))).getText();
        Assert.assertEquals(confirmation, "Thank you for your order!",
                "Order confirmation should appear");
    }
}