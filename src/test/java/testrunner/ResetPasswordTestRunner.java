package testrunner;

import config.Setup;
import org.apache.commons.io.filefilter.PathEqualsFileFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ResetPassword;
import utils.EmailUtils;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class ResetPasswordTestRunner extends Setup
{
        @Test(priority = 1, description = "Reset with Wrong Password")
    public void resetWithWrongPassword(){
        driver.findElement(By.partialLinkText("Reset it here")).click();
        ResetPassword resetPassword = new ResetPassword(driver);
//        String email = ("shabitalahi123+03@gmail.com");
//        UserModel userModel =new UserModel();
//        userModel.setEmail(email);
//
//        resetPassword.resetPassword(userModel);
        resetPassword.resetPassword("shabitalahi123+222@gmail.com");
        String actualMessage =driver.findElement(By.tagName("p")).getText();
        System.out.println(actualMessage);
        String expectedMessgae = "Your email is not registered";
        Assert.assertTrue(actualMessage.equals(expectedMessgae));
    }
        @Test(priority = 2, description = "Reset with Empty Field")
    public void resetWithEmptyField(){
            driver.navigate().to("https://dailyfinance.roadtocareer.net/forgot-password");
//        driver.findElement(By.partialLinkText("Reset it here")).click();
        String isRequired =driver.findElement(By.tagName("input")).getAttribute("required");
        driver.findElement(By.tagName("button")).click();
        Assert.assertNotNull(isRequired, "Email field should have 'required' attribute.");
//        driver.close();
    }
    @Test(priority = 3, description = "User Login With Valid Email")
    public void resetPasswordWithValidEmail() throws IOException, ParseException, InterruptedException {
        driver.navigate().to("https://dailyfinance.roadtocareer.net/forgot-password");
//        driver.findElement(By.partialLinkText("Reset it here")).click();
        ResetPassword reset = new ResetPassword(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray =(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj =(JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = userObj.get("email").toString();

        reset.resetPassword(email);

        String actualMessage =driver.findElement(By.tagName("p")).getText();
        System.out.println(actualMessage);
        String expectedMessage = "Password reset link sent to your email";
        Assert.assertTrue(actualMessage.equals(expectedMessage));

        Thread.sleep(5000);

        String resetLink = EmailUtils.fetchResetPasswordLink(
                "imap.gmail.com", "imap", "shabitalahi123@gmail.com", "xmfx kglj sztm icaj", "Password Reset Request"
        );

        Assert.assertNotNull(resetLink, "Reset password link not found in email.");
        System.out.println("Reset Password Link: " + resetLink);

// Optionally, navigate with Selenium
        driver.get(resetLink);

        String password = "12345";
        List<WebElement> inputPassword = driver.findElements(By.tagName("input"));
        inputPassword.get(0).sendKeys(password);
        inputPassword.get(1).sendKeys(password);
        driver.findElement(By.tagName("button")).click();

        userObj.put("password",password);
        Utils.saveUserData("./src/test/resources/users.json",userObj);


    }
}
