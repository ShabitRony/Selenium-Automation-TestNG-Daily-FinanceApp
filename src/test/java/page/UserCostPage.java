package page;

import config.ItemModel;
import config.Setup;
import org.openqa.selenium.WebElement;
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

    public void UserCostPage(ItemModel itemModel){
        PageFactory.initElements(driver,this);
    }
    @Test
    public void addCost(ItemModel itemModel){
         txtInput.get(0).sendKeys(itemModel.getItemName());
         txtInput.get(1).click();
         txtInput.get(2).sendKeys(itemModel.getAmount());
         txtInput.get(3).sendKeys(itemModel.getPurchaseDate());
         txtMonth.sendKeys(itemModel.getMonth());
         txtRemarks.sendKeys(itemModel.getRemarks()!=null?itemModel.getRemarks():"");
         btnInput.get(3).click();

    }

}
