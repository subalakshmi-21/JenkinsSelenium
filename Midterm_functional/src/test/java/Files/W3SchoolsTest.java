package Files;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class W3SchoolsTest {
    
    WebDriver driver = new ChromeDriver();
    ExtentReports extent; // Declare the ExtentReports object
    ExtentTest test; // Declare the ExtentTest object

    @BeforeTest
    public void initializeExtentReport() {
        // Set up the ExtentReports object with an HTML reporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\subalakshmi.t\\eclipse-workspace\\Midterm\\extentReport4.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter); // Attach the reporter to ExtentReports
    }

    @Test
    public void testSearchFunctionality() {
        test = extent.createTest("Test Search Functionality", "Verifying search functionality with valid input");

        // Navigate to W3Schools
        driver.get("https://www.w3schools.com/");
        test.pass("Successfully navigated to W3Schools homepage"); // Explicitly marking the step as successful

        // Find the search bar element and input a valid keyword (HTML)
        WebElement searchBar = driver.findElement(By.id("tnb-google-search-input"));
        searchBar.sendKeys("HTML");
        test.pass("Search term HTML entered successfully"); // Explicitly marking the step as successful

        // Use Keys.ENTER or click the search button
        searchBar.sendKeys(Keys.ENTER); // Simulating pressing Enter
        test.pass("Search query submitted successfully"); // Explicitly marking the step as successful

        // Verify that "HTML Tutorial" appears in the results
        WebElement result = driver.findElement(By.partialLinkText("HTML Tutorial"));
        
        try {
            Assert.assertTrue(result.isDisplayed(), "HTML tutorial not found in search results");
            test.pass("Search result verified successfully: HTML Tutorial found");
        } catch (AssertionError e) {
            test.fail("Search result verification failed: " + e.getMessage());
        }
    }


    @AfterTest
    public void tearDown() {
        // Write the results to the report and close the driver
        if (extent != null) {
            extent.flush(); // This writes the test results to the HTML report
        }
        if (driver != null) {
            driver.quit(); // Close the WebDriver session
        }
    }
}
