package page;

import config.Setup;
import config.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResetPassword extends Setup {
    @FindBy(tagName = "input")
    WebElement txtInput;
    @FindBy(tagName = "button")
    WebElement btnReset;
    @FindBy(tagName = "input")
    List<WebElement> inputPassword;

    public ResetPassword(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void resetPassword( String email){
    txtInput.sendKeys(email);
    btnReset.click();
    }
    public void newPassword(String newPassword){
        inputPassword.get(0).sendKeys(newPassword);
        inputPassword.get(1).sendKeys(newPassword);
        btnReset.click();
    }

}
