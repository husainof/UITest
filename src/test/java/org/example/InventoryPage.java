package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
    public WebDriver driver;
    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//*[contains(@id, 'add-to-cart-sauce-labs-backpack')]")
    private WebElement addToCardBtn;

    @FindBy(xpath = "//*[contains(@class, 'shopping_cart_link')]")
    private WebElement cart;

    public void addToCart() {
        addToCardBtn.click();
    }
    public void toCart() {
        cart.click();
    }
}
