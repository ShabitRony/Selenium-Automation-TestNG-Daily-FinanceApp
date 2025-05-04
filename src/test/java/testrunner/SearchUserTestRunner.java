package testrunner;

import config.Setup;
import config.UserModel;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.AuthUtils;

import java.io.IOException;

import static config.Setup.driver;

public class SearchUserTestRunner extends Setup {
    @Test(priority = 1,description = "Admin Login")
    public void adminLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");
    }
    @Test(priority = 2,description = "Admin Search User By Email")
    public void searchUserByUpdatedEmail(){
//        UserModel userModel = new UserModel();
        driver.findElement(By.className("search-box")).sendKeys("shabitalahi123+25@gmail.com");
    }
    @Test(priority = 3,description = "Assert Table Data")
    public void assertTableRowData() {
        // XPath to the first row (excluding header)
        WebElement row = driver.findElement(By.xpath("//table/tbody/tr[1]"));

        // Get each cell
        String firstName = row.findElement(By.xpath("td[1]")).getText();
        String lastName = row.findElement(By.xpath("td[2]")).getText();
        String email = row.findElement(By.xpath("td[3]")).getText();
        String phone = row.findElement(By.xpath("td[4]")).getText();
        String address = row.findElement(By.xpath("td[5]")).getText();
        String gender = row.findElement(By.xpath("td[6]")).getText();
        String regDate = row.findElement(By.xpath("td[7]")).getText();
        String viewText = row.findElement(By.xpath("td[8]")).getText();

        // Assertions
        Assert.assertEquals(firstName, "Diego");
        Assert.assertEquals(lastName, "Shanahan");
        Assert.assertEquals(email, "shabitalahi123+25@gmail.com");
        Assert.assertEquals(phone, "01608257018");
        Assert.assertEquals(address, "Apt. 017 873 Hessel Squares, East Keiko, NC 27557");
        Assert.assertEquals(gender, "Male");
        Assert.assertEquals(regDate, "03/05/2025");
        Assert.assertEquals(viewText, "VIEW");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
