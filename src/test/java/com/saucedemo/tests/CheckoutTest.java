package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.Constants;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Complete end-to-end checkout flow")
    public void testFullCheckoutFlow() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);

        // Add to cart & go to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();

        // Checkout
        new CartPage(driver).proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillShippingInfo("John", "Doe", "12345");
        checkoutPage.finishOrder();

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                Constants.ORDER_CONFIRMATION,
                "Order confirmation should appear after checkout"
        );
    }
}