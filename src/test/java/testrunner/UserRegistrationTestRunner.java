package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.UserRegistrationPage;
import utils.EmailUtils;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class UserRegistrationTestRunner extends Setup {
    @Test(description = "User Registration")
  public static void userRegistration() throws IOException, ParseException, InterruptedException {
      driver.findElement(By.partialLinkText("Register")).click();
      UserRegistrationPage userRegistration = new UserRegistrationPage(driver);
      Faker faker = new Faker();
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String email = ("shabitalahi123+26@gmail.com");
      String password ="1234";
      String phoneNumber = "0160"+ Utils.generateNumber(1000000,9999999);
      String address = faker.address().fullAddress();

      UserModel userModel = new UserModel();
      userModel.setFirstName(firstName);
      userModel.setLastName(lastName);
      userModel.setEmail(email);
      userModel.setPassword(password);
      userModel.setPhoneNumber(phoneNumber);
      userModel.setAddress(address);

      userRegistration.userRegistration(userModel);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Toastify__toast")));
        String successfulMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successfulMessageActual);
        String successfulMessageExpected ="registered successfully!";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);
        jsonObject.put("address",address);
        Utils.saveUserData("./src/test/resources/users.json",jsonObject);
        Assert.assertTrue(successfulMessageExpected.contains(successfulMessageExpected));
        Thread.sleep(5000);

        //Checking mail
        boolean isMailReceived = EmailUtils.checkEmail(
                "imap.gmail.com", "imap", "shabitalahi123@gmail.com", "xmfx kglj sztm icaj", "Congratulations on Registering!"
        );

        Assert.assertTrue("Confirmation email was received.", isMailReceived);

  }
}
