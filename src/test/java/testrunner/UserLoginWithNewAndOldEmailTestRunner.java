package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class UserLoginWithNewAndOldEmailTestRunner extends Setup {
    @Test(priority =1, description = "User Login with Email")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray =(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject =(JSONObject)jsonArray.get(jsonArray.size()-1);
        String email =jsonObject.get("email").toString();

        String password = jsonObject.get("password").toString();
        loginPage.doLogin(email,password);

    }
    @Test(priority = 2,description = "User Logout")
    public void logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.doLogout();
//        driver.quit();
    }
    @Test(priority = 3, description = "Login with Old Email")
    public void userLoginWithOldEmail(){
        LoginPage login = new LoginPage(driver);
        login.userLogin("shabitalahi123+55@gmail.com","12345");

        String actualMessage= driver.findElement(By.tagName("p")).getText();
        System.out.println(actualMessage);
        String expectedMessage = "Invalid email or password";
        Assert.assertTrue(actualMessage.equals(expectedMessage));

    }
}
