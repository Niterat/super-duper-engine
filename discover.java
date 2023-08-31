import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssesmentAssigment {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path-to-chromedriver");

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Navigate to Discover website
        driver.get("https://www.discover.com/");

        try {
            // Click on "Credit Cards" icon - assuming icon located below "how can we help?" and not looking for the link from the menu although both end up at the same spot
            WebElement creditCardsIcon = driver.findElement(By.xpath("//*[@id=\"main-content-rwd\"]/div/div/div[2]/div[1]/div[1]/ul/li[1]/a/div/div"));
            creditCardsIcon.click();

            // Click "Apply Now" for Secured Credit Card
            WebElement applyBtn = driver.findElement(By.xpath("//a[contains(text(), 'Secured Credit Card')]/ancestor::div[@class='card-content-inner']//button[@data-testid='card-apply-now-button']"));
            applyBtn.click();

            // Skip pre-fill step
            WebElement skipBtn = driver.findElement(By.xpath("//button[contains(text(), 'Skip This Step')]"));
            skipBtn.click();

            // Wait for APR for Cash Advances to be visible
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement aprCashAdv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'APR for Cash Advances')]/following-sibling::div")));

            // Assert that APR for Cash Advances is greater than 20%
            String aprText = aprCashAdv.getText();
            double aprValue = Double.parseDouble(aprText.replaceAll("[^\\d.]", ""));
            if (aprValue > 20.0) {
                System.out.println("Test Passed! APR for Cash Advances is greater than 20%");
            } else {
                System.out.println("Test Failed! APR for Cash Advances is not greater than 20%");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}