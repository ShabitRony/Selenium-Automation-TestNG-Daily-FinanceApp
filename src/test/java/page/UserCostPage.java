package page;

import com.github.javafaker.Faker;
import config.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v133.webaudio.model.AudioListenerWillBeDestroyed;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class UserCostPage extends Setup {
    @FindBy(tagName = "input")
    List<WebElement> txtInput;
    @FindBy(id="month")
    WebElement txtMonth;
    @FindBy(id="remarks")
    WebElement txtRemarks;
    @FindBy(tagName = "button")
    List<WebElement> btnInput;

    public void UserCostPage(){
        PageFactory.initElements(driver,this);
    }
    public void addCost(){
         txtInput.get(0).sendKeys("Shirt");
         txtInput.get(1).click();
         txtInput.get(2).sendKeys("500");
         txtInput.get(3).sendKeys("05/01/2025");
         txtMonth.sendKeys("May");
         txtRemarks.sendKeys("Good");
         btnInput.get(3).click();
    }
}
