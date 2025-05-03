package utils;

import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import static config.Setup.driver;

public class AuthUtils {
    public static void getToken() throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')")!=null);

        //get the authToken from localStorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken')");
        String authTokenData = (String) js.executeScript("return window.localStorage.getItem('authTokenData')");
        System.out.println("AuthToken Retrieved : "+authToken);
        System.out.println("AuthTokenData Retrieved : "+authTokenData);

        //Save the authToken to a localStorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken",authToken);
        jsonObject.put("authTokenData",authTokenData);

        FileWriter fileWriter = new FileWriter("./src/test/resources/localstorage.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
    public static void setAuth(WebDriver driver) throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONObject authObj = (JSONObject) jsonParser.parse(new FileReader("./src/test/resources/localstorage.json"));
        String authToken = authObj.get("authToken").toString();
        String authTokenData = authObj.get("authTokenData").toString();
        System.out.println(authToken);

        JavascriptExecutor js = (JavascriptExecutor) Setup.driver;
        js.executeScript("window.localStorage.setItem('authToken',arguments[0]);",authToken);
        js.executeScript("window.localStorage.setItem('authTokenData',arguments[0]);",authTokenData);
        Thread.sleep(2000);
    }
}

