package testrunner;

import config.Setup;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static config.Setup.driver;

public class UserDataScrapTestRunner extends Setup {

    @BeforeMethod
    public void adminLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");
    }

    @Test
    public void scrapeUserTableTest() throws IOException {
        String filePath = "F:\\JUnit\\Assignment-Selenium-TestNG\\src\\test\\java\\utils\\user_data.txt";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        List<WebElement> tableContainer = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("table")));
        WebElement table = tableContainer.get(0);

        try (FileWriter writer = new FileWriter(filePath)) {
            scrapData(writer, table);
        }
    }

    public void scrapData(FileWriter writer, WebElement table) throws IOException {
        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
        Assert.assertTrue(rows.size() > 0, "No rows found in user table.");

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String cellText = cell.getText().trim();
                writer.write(cellText + "\t");
                System.out.print(cellText + "\t");
            }
            writer.write("\n");
            System.out.println();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

