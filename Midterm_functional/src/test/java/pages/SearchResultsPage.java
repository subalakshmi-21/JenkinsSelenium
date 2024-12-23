package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    WebDriver driver;

    // Locator for Add to Cart button
    private By addToCartButton = By.id("a-autoid-1-announce");

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isAddToCartButtonPresent() {
        try {
            WebElement button = driver.findElement(addToCartButton);
            return button.isDisplayed();  // Return true if the button is displayed
        } catch (Exception e) {
            return false;  // Return false if the button is not found
        }
    }
}
