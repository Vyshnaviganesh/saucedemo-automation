package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.Constants;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Valid login redirects to Products page")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);

        Assert.assertTrue(new InventoryPage(driver).isLoaded(),
                "Products page should load after valid login");
    }

    @Test(description = "Invalid login shows error message")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.INVALID_USERNAME, Constants.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should appear for invalid credentials");
        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LOGIN_ERROR);
    }
}