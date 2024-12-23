package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultsPage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest {
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void setUp() {
       
        
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com");

        // Initialize POM pages
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Test
    public void searchProductTest() {
        // Perform actions
        homePage.enterSearchQuery("Laptop");
        homePage.clickSearchButton();

        // Assert that the Add to Cart button is present, indicating results were shown
        boolean isButtonPresent = searchResultsPage.isAddToCartButtonPresent();
        Assert.assertTrue(isButtonPresent, "Search results not shown properly.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
