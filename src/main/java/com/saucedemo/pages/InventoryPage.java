package com.saucedemo.pages;

import com.saucedemo.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle    = By.cssSelector(".title");
    private By addToCartBtn = By.cssSelector(".inventory_item:first-child button");
    private By cartIcon     = By.cssSelector(".shopping_cart_link");
    private By cartBadge    = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoaded() {
        wait.until(ExpectedConditions.urlContains("inventory"));
        return driver.findElement(pageTitle).getText().equals(Constants.PRODUCTS_TITLE);
    }

    public void addFirstItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public int getCartCount() {
        var badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }
}