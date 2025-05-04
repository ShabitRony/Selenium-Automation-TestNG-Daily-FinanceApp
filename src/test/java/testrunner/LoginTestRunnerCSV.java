package testrunner;


import config.Setup;
import org.testng.annotations.Test;
import page.LoginDataSet;
import page.LoginPage;
//import org.junit.Test;

public class LoginTestRunnerCSV extends Setup {

    @Test(dataProvider = "LoginCSVData", dataProviderClass = LoginDataSet.class)
    public void doLogin(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email,password);
        loginPage.doLogout();
    }
}
