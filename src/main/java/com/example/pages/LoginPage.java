package com.example.pages;

import com.example.utils.ConfigManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the SauceDemo login page.
 * Each action is annotated with @Step for Allure reporting.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // ── Actions ───────────────────────────────────────────────────────────────

    @Step("Open login page")
    public LoginPage open() {
        navigateTo(ConfigManager.get().baseUrl());
        log.info("Login page opened");
        return this;
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("Click login button")
    public InventoryPage clickLogin() {
        click(loginButton);
        waitForUrlContains("inventory");
        return new InventoryPage();
    }

    @Step("Submit invalid credentials")
    public LoginPage clickLoginExpectingFailure() {
        click(loginButton);
        return this;
    }

    // ── Composite actions (most used pattern) ─────────────────────────────────

    @Step("Login with username: {username}")
    public InventoryPage loginAs(String username, String password) {
        return open()
            .enterUsername(username)
            .enterPassword(password)
            .clickLogin();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(org.openqa.selenium.By.cssSelector("[data-test='error']"));
    }
}
