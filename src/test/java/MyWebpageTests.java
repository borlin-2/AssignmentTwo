import com.codeborne.selenide.impl.WebElementSelector;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import java.util.logging.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.*;

import java.io.File;
import java.io.IOException;

public class MyWebpageTests {

    @Disabled
    @Test //THE LOG IN TEST
    public void test1() {
        open("https://www.ltu.se/");
        sleep(2000);

        //Handle the pop-up window by clicking the button with the text "Tillåt alla cookies"
        $("button[class='CybotCookiebotDialogBodyButton']").click();
        sleep(2000);

        //find hidden element by the text "Student" with Xpath and click it
        $x("//a[text()='Student']").click();
        sleep(2000);

        //find hidden element by the text "MITT LTU" with Xpath and click it
        $x("//a[text()='Mitt LTU']").click();
        sleep(2000);

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
            $(By.id("username")).sendKeys(email);
            $(By.id("password")).sendKeys(password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //find hidden element by the text "Logga in" with CSS and click it
        // Wait for the element to become visible and not transparent
        $("[name='submit']").waitUntil(not(cssValue("opacity", "0")), 10000);
        // Perform actions on the element once it is visible and not transparent
        $("[name='submit']").click();

        sleep(20000);
    }


    @Test //THE TRANSCRIPT DOWNLOAD TEST
    public void test2() {
        //Opens the Chrome webbrowser and goes to the webpage https://www.student.ladok.se/student/app/studentwebb/
        open("https://www.student.ladok.se/student/app/studentwebb/");

        //Handles the cookie banner by clicking the button that allows all cookies.
        var v1 = $x("//button[@class='btn btn-light']");
        v1.click();
        sleep(2000);

        //Clicks the login button in the middle of the page.
        $("[href*='/student/login?ret=/app/studentwebb']").click();

        //Finds the search field with the id "searchinput" and inserts the text "lulea" to seach for the right university.
        var v2 = $("#searchinput");
        v2.sendKeys("lulea");

        //Finds the search result that corresponds with the search and clicks it.
        var v3 = $("[aria-label='Select Lulea University of Technology']");
        v3.click();

        //Here we are creating an instance of the class File with the pathname to the login credentials for LTU.
        File jsonFile = new File("C:\\temp\\ltu.json");
        String email = null;
        String password = null;

        try {
            //We are creating an instance of ObjectMapper to read the JSON file where the credentials are stored.
            ObjectMapper objectMapper = new ObjectMapper();

            //We are using a FileInputStream to read the JSON file into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            //We retrieve the username and password from the JsonNode object, aka the login credentials in the JSON file.
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException i) {
            //This prints the stack trace of the exception if an error occurs
            i.printStackTrace();
        }
        try {
            //Finds the fields for the email(username) and password, and then we enter the login credentials in each field.
            $(By.id("username")).sendKeys(email);
            $(By.id("password")).sendKeys(password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Here we find the button with the name "submit" and click it.
        $("[name='submit']").waitUntil(not(cssValue("opacity", "0")), 10000);
        $("[name='submit']").click();

        sleep(2000);

        //Here we click the Menu button in the top right corner of the page.
        var v4 = $(".navbar-toggler");
        v4.click();

        //Here we click the option Transcripts and certificates in the drop-down menu.
        $("[href*='/student/app/studentwebb/intyg']").click();

        //The following code is commented out because it is creating a new transcript every time the test is run,
        //and it is not possible to delete the transcript from the webpage.
        //Instead, I am using the transcript that is already created on the webpage to download it.
        //Skipp to line 178 to see the code for downloading the transcript.

        //Clicks the button Create to create a new transcript.
        /* var v5 = $x("//button[@class='btn btn-ladok-brand']");
        v5.click();

        // Clicks the menu of different types of transcripts and/or certificates.
        var v6 = $("#intygstyp");
        v6.click();

        //Clicks the option Certificate of Registration in the drop-down menu.
        var v7 = $("[value='1: Object']");
        v7.click();

        //Finds the field for input for the From date and sends the date.
        var v8 = $("#start");
        v8.sendKeys("20220801");

        //Finds the field for input for the To date and sends the date.
        var v9 = $("#slut");
        v9.sendKeys("20230630");

        //We are clicking the button Create to creat the transcript.
        var v10 = $x("//button[@class='btn btn-ladok-brand text-nowrap me-lg-3']");
        v10.click(); */

        //Here the code for downloading the transcript starts.

        //Clicks on the last created Transcript and saves it to
        //C:\Users\Administrator\IdeaProjects\AssignmentTwo\build\downloads
        var v11 = $("[href*='https://www.student.ladok.se/student/proxy/extintegration/internal/intyg/68c23e47-eea8-11ed-b9d5-99ac793c4cff/pdf']");
        v11.click();

    }


    @Disabled
    @Test //THE FINAL EXAMINATION INFO TEST
    public void test3() {
        open("https://portal.ltu.se/web/student/");
        sleep(2000);

        $("[class='nav-item-label']").waitUntil(not(cssValue("opacity", "0")), 10000);
        // Perform actions on the element once it is visible and not transparent
        $("[class='nav-item-label']").click();

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
            $(By.id("username")).sendKeys(email);
            $(By.id("password")).sendKeys(password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        $("[name='submit']").waitUntil(not(cssValue("opacity", "0")), 10000);
        // Perform actions on the element once it is visible and not transparent
        $("[name='submit']").click();

        sleep(2000);

        //find the element with the href attribute containing the text "https://www.student.ladok.se/student/#/intyg" and click it
        $("[href*='https://www.student.ladok.se/student/#/intyg']").click();

        sleep(2000);

        // Switch to the new tab
        switchTo().window(1);


    }

    @Disabled
    @Test //THE DOWNLOAD SYLLABUS TEST
    public void test4() {
        open("https://www.ltu.se/");
        sleep(2000);

        //Handle the pop-up window by clicking the button with the text "Tillåt alla cookies"
        $("button[class='CybotCookiebotDialogBodyButton']").click();
        sleep(2000);
    }

}
