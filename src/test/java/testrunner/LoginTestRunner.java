package testrunner;

import config.Setup;
import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v133.layertree.model.StickyPositionConstraint;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.ResetPassword;
import utils.AuthUtils;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static config.Setup.driver;

public class LoginTestRunner extends Setup {


  @Test(priority = 1, description = "Admin Login")
    public void adminLogin() throws IOException {
    LoginPage loginPage = new LoginPage(driver);
    if(System.getProperty("email")!=null && System.getProperty("password")!=null){
        loginPage.doLogin(System.getProperty("email"),System.getProperty("password"));
    }else{
        loginPage.doLogin("admin@test.com","admin123");
    }
    AuthUtils.getToken();
}
    @Test(priority = 2, description = "Admin Logout")
    public void logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.doLogout();
    }

    @Test(priority =3, description = "User Login with NewPassword")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray =(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject =(JSONObject)jsonArray.get(jsonArray.size()-1);
        String email =jsonObject.get("email").toString();
        String password = jsonObject.get("password").toString();
        Thread.sleep(2000);
        loginPage.doLogin(email,password);

    }
    @Test(priority = 4, description = "User Logout")
    public void userLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(3000);
        loginPage.doLogout();
        driver.quit();
    }

 }



