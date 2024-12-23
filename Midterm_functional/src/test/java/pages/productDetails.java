package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class productDetails {
    WebDriver driver;

    // Locators
    private By addToCartButton = By.id("add-to-cart-button");
    private By buyNowButton = By.id("buy-now-button");

    // Constructor
    public productDetails(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }

    public void clickBuyNowButton() {
        driver.findElement(buyNowButton).click();
    }
}
