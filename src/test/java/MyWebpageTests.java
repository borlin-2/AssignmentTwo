import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
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

        //Insert the word "Sven"
        $("input[id='username']").sendKeys("");

        sleep(2000);

        //find element by the name "password" and insert the password
        $("input[name='password']").sendKeys("");

        //find element by the name "submit" and click it
        $("input[name='submit']").click();
        sleep(2000);
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
