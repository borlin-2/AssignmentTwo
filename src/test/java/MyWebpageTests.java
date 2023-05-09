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


        open("https://www.student.ladok.se/student/app/studentwebb/");

        var v1 = $x("//button[@class='btn btn-light']");
        v1.click();

        sleep(2000);

        $("[href*='/student/login?ret=/app/studentwebb']").click();

        // find search field with the id "searchinput" and click it
        var v2 = $("#searchinput");
        v2.sendKeys("lulea");

        // click the element with the aria-label "Select Lulea University of Technology"
        var v3 = $("[aria-label='Select Lulea University of Technology']");
        v3.click();

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








        sleep(5000);

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
