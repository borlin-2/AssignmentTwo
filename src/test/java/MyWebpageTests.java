import com.codeborne.selenide.impl.WebElementSelector;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;

import java.io.File;
import java.io.IOException;

public class MyWebpageTests {

    @Test
    public void test1() throws IOException, ParseException {
        open("https://www.ltu.se/");
        sleep(2000);

        //Handle the pop-up window by clickling the button with the text "Tillåt alla cookies"
        $("button[class='CybotCookiebotDialogBodyButton']").click();
        sleep(2000);

        //find hidden element by the text "Student" with Xpath and click it
        $x("//a[text()='Student']").click();
        sleep(2000);

        //find hidden element by the text "MITT LTU" with Xpath and click it
        $x("//a[text()='Mitt LTU']").click();
        sleep(2000);

        ChromeDriver driver = new ChromeDriver();
        // Creating an instance of File with the pathname to the login credentials
        File jsonFile = new File("C:\\temp\\ltu.json");

        String email = null;
        String password = null;

        try {
            // Creating an instance of ObjectMapper to read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();

            // Using a FileInputStream to read the JSON file into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            // Retrieve the username and password from the JsonNode object
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException i) {
            // Print the stack trace of the exception if an error occurs
            i.printStackTrace();
        }

        try {
            // Here we are finding the fields for the email(username) and password, and then we enter the login credentials
            WebElement emailInput = driver.findElement(By.id("username"));
            emailInput.sendKeys(email);

            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys(password);

            // Here we click the login button to log in
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();

        } catch (Exception e) {

        }



        //Insert the word "Sven"
        //$("input[id='username']").sendKeys("");

       // sleep(2000);

        //find element by the name "password" and insert the password
       // $("input[name='password']").sendKeys("");

        //find element by the name "submit" and click it
       // $("input[name='submit']").click();
       // sleep(2000);
        }


    @Disabled
    @Test
    public void test2() {
    }

    @Disabled
    @Test
    public void test3() {
    }

    @Disabled
    @Test
    public void test4() {
    }

}
