package Files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GitHubLoginTest {

    WebDriver driver;

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String testType) {
        
        driver = new ChromeDriver();
        driver.get("https://github.com/login");  // GitHub login page

        try {
            // Input email and password
            WebElement emailField = driver.findElement(By.id("login_field"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.name("commit"));

            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            loginButton.click();

            // Wait for the dashboard page to load after login (adjust selector as needed)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));

            // If login was successful, proceed with logging out after first test
            if (testType.equals("Positive")) {
                System.out.println("Login Successful with " + email);
            } else {
                System.out.println("Login Failed with " + email);
            }

        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
        } finally {
            // Log out if it’s the first login test, or after every test
            try {
                WebElement profileMenu = driver.findElement(By.xpath("//summary[@aria-label='View profile and more']"));
                profileMenu.click();
                WebElement logoutButton = driver.findElement(By.xpath("//button[text()='Sign out']"));
                logoutButton.click();
                System.out.println("Logged out after " + testType + " test.");
            } catch (Exception e) {
                System.out.println("Logout failed or not applicable for " + testType + " test.");
            }

            // Close the browser window after each test
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        List<String[]> data = readCSV("C:\\Users\\subalakshmi.t\\eclipse-workspace\\Midterm_functional\\github_login_data.csv"); // Update the CSV file path

        // Convert the list into a 2D array for DataProvider
        Object[][] loginData = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            loginData[i] = data.get(i);
        }

        return loginData;
    }

    private List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // Skip the header row
        reader.readLine(); // Skip the first line which is the header

        while ((line = reader.readLine()) != null) {
            // Assuming CSV structure: email, password, test type
            String[] credentials = line.split(",");
            data.add(credentials);
        }

        reader.close();
        return data;
    }
}
