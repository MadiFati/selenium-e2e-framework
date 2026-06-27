package com.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the SauceDemo inventory (products) page.
 */
public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> productCards;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    // ── Queries ───────────────────────────────────────────────────────────────

    @Step("Get number of products displayed")
    public int getProductCount() {
        return productCards.size();
    }

    @Step("Get product names")
    public List<String> getProductNames() {
        return productNames.stream()
            .map(WebElement::getText)
            .toList();
    }

    @Step("Get cart item count")
    public int getCartCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isOnInventoryPage() {
        return getCurrentUrl().contains("inventory");
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    @Step("Add first product to cart")
    public InventoryPage addFirstProductToCart() {
        WebElement addButton = productCards.get(0)
            .findElement(org.openqa.selenium.By.tagName("button"));
        click(addButton);
        log.info("Added first product to cart");
        return this;
    }

    @Step("Sort products by: {option}")
    public InventoryPage sortBy(String option) {
        new org.openqa.selenium.support.ui.Select(sortDropdown).selectByVisibleText(option);
        return this;
    }
}
