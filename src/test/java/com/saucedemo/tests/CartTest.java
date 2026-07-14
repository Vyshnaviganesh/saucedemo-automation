package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.Constants;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private void loginAndGoToInventory() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);
    }

    @Test(description = "Add item to cart updates cart badge count")
    public void testAddToCart() {
        loginAndGoToInventory();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), 1,
                "Cart badge should show 1 item");
    }

    @Test(description = "Cart page displays correct item count")
    public void testCartPageItemCount() {
        loginAndGoToInventory();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();
        Assert.assertEquals(new CartPage(driver).getCartItemCount(), 1,
                "Cart page should list 1 item");
    }
}