package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UserCostTestRunner extends Setup {
    @Test(priority = 1, description = "User Login")
    public static void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray =(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject =(JSONObject)jsonArray.get(jsonArray.size()-1);
        String email =jsonObject.get("email").toString();
        String password = jsonObject.get("password").toString();
        loginPage.doLogin(email,password);
    }

    @Test(priority = 2,description = "Add Cost Item")
    public void addCost() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> btnCost =  driver.findElements(By.tagName("button"));
        btnCost.get(1).click();
        Thread.sleep(2000);
        List<WebElement> txtInput = driver.findElements(By.tagName("input"));
            txtInput.get(0).sendKeys("Shirt");
            txtInput.get(1).click();
            txtInput.get(2).sendKeys("500");
            txtInput.get(3).sendKeys("05/01/2025");
            Select option =new Select(driver.findElement(By.id("month")));
            option.selectByVisibleText("May");
        driver.findElement(By.id("remarks")).sendKeys("Good");
        List<WebElement> btnSubmit = driver.findElements(By.tagName("button"));
        btnSubmit.get(3).click();

        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(2000); // wait for table update (replace with explicit wait in real case)

        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

// Assuming the newly added item is in the last row (excluding header row)
        WebElement lastRow = rows.get(rows.size() - 1);
        List<WebElement> cells = lastRow.findElements(By.tagName("td"));

// Assertions: You can use TestNG's Assert class


        Assert.assertEquals(cells.get(0).getText(), "Shirt", "Item name mismatch");
        Assert.assertEquals(cells.get(1).getText(), "1", "Quantity mismatch");
        Assert.assertEquals(cells.get(2).getText(), "500", "Amount mismatch");
        Assert.assertEquals(cells.get(3).getText(), "01/05/2025", "Purchase date  mismatch"); // Assuming default quantity = 1
        Assert.assertEquals(cells.get(4).getText(), "May", "Month mismatch");
        Assert.assertEquals(cells.get(5).getText(), "Good", "Remarks mismatch");
        }
    }

