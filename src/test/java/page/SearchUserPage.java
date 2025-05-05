package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Setup.driver;

public class SearchUserPage {

        public SearchUserPage(){
            PageFactory.initElements(driver,this);

    }
    public void searchUserInfo(){
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
}

