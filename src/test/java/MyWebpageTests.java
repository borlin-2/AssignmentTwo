import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import java.io.File;
import java.io.IOException;
import com.codeborne.selenide.Screenshots;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MyWebpageTests {

    @Test //THE LOGIN and LOG OUT TEST
    public void test1() {
        //Opens the LTU webpage
        open("https://www.ltu.se/");

        //Handles the pop-up window by clicking the button with the text "Tillåt alla cookies"
        $("button[class='CybotCookiebotDialogBodyButton']").click();

        //Finds the Student lin in the top right corner and clicks it.
        $x("//a[text()='Student']").click();

        //Finds the login link named "MITT LTU" and clicks it.
        $x("//a[text()='Mitt LTU']").click();

        //We create an instance of File with the pathname to the login credentials.
        File jsonFile = new File("C:\\temp\\ltu.json");
        String email = null;
        String password = null;

        try {
            //We are creating an instance of ObjectMapper to read the JSON file with the login credentials.
            ObjectMapper objectMapper = new ObjectMapper();

            //We use a FileInputStream to read the JSON file into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            //We retrieve the username and password from the JsonNode object.
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException i) {
            //This prints the stack trace of the exception if an error occurs
            i.printStackTrace();
        }

        try {
            //Here we are finding the fields for the email(username) and password, and then we enter the login credentials.
            $(By.id("username")).sendKeys(email);
            $(By.id("password")).sendKeys(password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Finds and clicks the button "Logga in".
        var a1 = $("[name='submit']");
        a1.click();

        //Finds and clicks the menu in top right corner
        var a2 = $(".user-full-name");
        a2.click();

        //Finds and clicks the button "Logga ut".
        var a3 = $("[href='/c/portal/logout']");
        a3.click();
    }

    @Test //THE TRANSCRIPT DOWNLOAD TEST
    public void test2() {
        //Opens the Chrome web browser and goes to the webpage https://www.student.ladok.se/student/app/studentwebb/
        open("https://www.student.ladok.se/student/app/studentwebb/");

        //Handles the cookie banner by clicking the button that allows all cookies.
        var v1 = $x("//button[@class='btn btn-light']");
        v1.click();
        sleep(2000);

        //Clicks the login button in the middle of the page.
        $("[href*='/student/login?ret=/app/studentwebb']").click();

        //Finds the search field with the id "searchinput" and inserts the text "lulea" to search for the right university.
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
        var v4 = $("[name='submit']");
        v4.click();

        //Here we click the Menu button in the top right corner of the page.
        var v5 = $(".navbar-toggler");
        v5.click();

        //Here we click the option Transcripts and certificates in the drop-down menu.
        $("[href*='/student/app/studentwebb/intyg']").click();

        //The following code is commented out because it is creating a new transcript every time the test is run,
        //and it is not possible to delete the transcript from the webpage.
        //Instead, I am using the transcript that is already created on the webpage to download it.
        //Skipp to line 163 to see the code for downloading the transcript.

        //Clicks the button Create to create a new transcript.
        /* var v6 = $x("//button[@class='btn btn-ladok-brand']");
        v6.click();

        // Clicks the menu of different types of transcripts and/or certificates.
        var v7 = $("#intygstyp");
        v7.click();

        //Clicks the option Certificate of Registration in the drop-down menu.
        var v8 = $("[value='1: Object']");
        v8.click();

        //Finds the field for input for the From date and sends the date.
        var v9 = $("#start");
        v9.sendKeys("20220801");

        //Finds the field for input for the To date and sends the date.
        var v10 = $("#slut");
        v10.sendKeys("20230630");

        //We are clicking the button Create to creat the transcript.
        var v11 = $x("//button[@class='btn btn-ladok-brand text-nowrap me-lg-3']");
        v11.click(); */

        //Here the code for downloading the transcript starts.

        //Clicks on the last created Transcript and saves it to
        //C:\Users\Administrator\IdeaProjects\AssignmentTwo\build\downloads
        var v12 = $("[href*='https://www.student.ladok.se/student/proxy/extintegration/internal/intyg/68c23e47-eea8-11ed-b9d5-99ac793c4cff/pdf']");
        v12.click();
    }

    @Test //THE FINAL EXAMINATION INFO TEST
    public void test3() {
        //Opens the Chrome web browser and goes to the webpage https://www.student.ladok.se/student/app/studentwebb/
        open("https://portal.ltu.se/web/student/");

        //Clicks "Logga in" in the right top corner.
        var y1 = $("[class='nav-item-label']");
        y1.click();

        //Creating an instance of File with the pathname to the LTU login credentials
        File jsonFile = new File("C:\\temp\\ltu.json");
        String email = null;
        String password = null;

        try {
            //Creating an instance of ObjectMapper to read the JSON file with the credentials
            ObjectMapper objectMapper = new ObjectMapper();

            //Using a FileInputStream to read the JSON file into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            //Retrieve the username and password from the JsonNode object
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException i) {
            //Prints the stack trace of the exception if an error occurs
            i.printStackTrace();
        }

        try {
            //Finding the fields for the email(username) and password, and then enters the login credentials
            $(By.id("username")).sendKeys(email);
            $(By.id("password")).sendKeys(password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Clicks the button with the name "Logga in".
        var y2 = $("[name='submit']");
        y2.click();

        //Clicks Tentamen in the left menu.
        var y3 = $(".hasChildren", 1);
        y3.click();

        //Clicks the link named Tentamensschema.
        var y4 = $("[href='https://tenta.ltu.se/index.jsp']");
        y4.click();

        //Switches focus to the new tab
        switchTo().window(1);

        //Finds the search field for input.
        var y5 = $("#enkel_sokfalt");
        y5.click();

        //Enters the course code "I0015N-VT23-47000" in the search field.
        var y6 = $("#enkel_sokfalt");
        y6.sendKeys("I0015N-VT23-47000");

        //Clicks the button with the text "Sök schema".
        var y7 = $("#enkel_sokknapp");
        y7.click();

        //Clicks the document link in the search result.
        var y8 = $("[target='_blank']");
        y8.click();

        //Switches focus to the new tab
        switchTo().window(2);

        //Defines the target directory and file path for the coming screenshot.
        String targetFilePath = "C:\\Users\\Administrator\\IdeaProjects\\AssignmentTwo\\target\\screenshots\\final_examination.jpeg";

        //Take a screenshot of the webpage in tab 3.
        File screenshotFile = Screenshots.takeScreenShotAsFile();

        //Defines the target file
        File targetFile = new File(targetFilePath);

        //Checks if the target file already exists.
        if (targetFile.exists()) {
            //Deletes the existing file in it exists.
            targetFile.delete();
        }

        //Moves the screenshot file to the target directory with the desired file name.
        try {
            Files.move(screenshotFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test //THE DOWNLOAD SYLLABUS TEST
    public void test4() {
        //Opens the webpage https://www.ltu.se/
        open("https://www.ltu.se/");

        //Handles the pop-up window by clicking the button with the text "Tillåt alla cookies"
        $("button[class='CybotCookiebotDialogBodyButton']").click();
        sleep(2000);

        //Finds the search field and clicks it
        var t1 = $("[aria-label='Sök']");
        t1.click();

        //Places the text "I0015N" in the search field
        var t2 = $(".cludo-top-input");
        t2.sendKeys("I0015N");

        //Clicks the search button after the text "I0015N" is placed in the search field
        var t3 = $("[aria-label='Sök']", 1);
        t3.click();

        //Clicks the URL link to the course I0015N homepage
        var t4 = $("[href='https://www.ltu.se/edu/course/i00/i0015n/i0015n-test-av-it-system-1.81215']");
        t4.click();

        //Clicks the tab for the course in the spring 2023
        var t5 = $("[href='/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?termin=V23']");
        t5.click();

        //Clicks the link to the syllabus
        var t6 = $("[href='/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?termin=V23&kursView=kursplan']");
        t6.click();

        //Click on the link to the syllabus PDF and downloads it to C:\Users\Administrator\IdeaProjects\AssignmentTwo\build\downloads
        var t7 = $("[href='https://webapp.ltu.se/epok/dynpdf/public/kursplan/downloadPublicKursplan.pdf?kursKod=I0015N&lasPeriod=211&locale=sv']");
        t7.click();
    }
}
