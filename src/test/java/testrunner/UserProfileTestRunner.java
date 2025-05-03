package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.AuthUtils;
import utils.Utils;

import javax.print.attribute.ResolutionSyntax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class UserProfileTestRunner extends Setup {
    @BeforeTest
    public void setAuth() throws IOException, ParseException, InterruptedException {
         AuthUtils.setAuth(driver);
    }
    @Test(priority = 1,description = "User edit by admin")
    public void editUserInfo() throws InterruptedException, IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(5000);

        driver.navigate().to("https://dailyfinance.roadtocareer.net/user/e198f186-bb5d-4dbe-ba27-3e3f878444cc");

        loginPage.button.get(1).click();
        WebElement txtEmail = driver.findElement(By.name("email"));
        txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        txtEmail.sendKeys("shabitalahi123+25@gmail.com");
        loginPage.button.get(2).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        JSONParser parser = new JSONParser();
        JSONArray jsonArray =(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj =(JSONObject) jsonArray.get(jsonArray.size()-1);



        userObj.put("email", txtEmail.getAttribute("value"));
        Utils.saveUserData("./src/test/resources/users.json",userObj);

    }
    @Test(priority = 3, description = "Admin Logout")
    public void logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.doLogout();
//        driver.quit();
    }
}

