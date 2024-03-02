package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Demo text box")
public class TextBoxTests extends TestBase {

    @Test
    @DisplayName("Text box can be filled")
    @Story("Fill and submit text box form")
    @Tag("automated")
    void fillFormTest() {
        step("Open page /text-box", () -> {
            open("/text-box");

            if($(".fc-dialog-content").isDisplayed())
                $(byText("Consent")).click();
        });
        step("Fill form", () -> {
            $("#userName").setValue("Alex");
            $("#userEmail").setValue("alex@egorov.com");
            $("#currentAddress").setValue("Some street 1");
            $("#permanentAddress").setValue("Another street 1");
            $("#submit").click();
        });
        step("Check results", () -> {
            $("#output #name").shouldHave(text("Alex"));
            $("#output #email").shouldHave(text("alex@egorov.com"));
            $("#output #currentAddress").shouldHave(text("Some street 1"));
            $("#output #permanentAddress").shouldHave(text("Another street 1"));
        });
    }

    @Test
    @DisplayName("Text box can be cleared")
    @Story("Clear text box form")
    @Tag("automated")
    void clearFormTest() {
        step("Open page /text-box", () -> {
            open("/text-box");

            if($(".fc-dialog-content").isDisplayed())
                $(byText("Consent")).click();
        });
        step("Clear form", () -> {
            $("#userName").setValue("");
            $("#userEmail").setValue("");
            $("#currentAddress").setValue("");
            $("#permanentAddress").setValue("");
        });
        step("Check that form is empty", () -> {
            $("#userName").shouldHave(text(""));
            $("#userEmail").shouldHave(text(""));
            $("#currentAddress").shouldHave(text(""));
            $("#permanentAddress").shouldHave(text(""));
        });
    }

    @Test
    @DisplayName("Error message is displayed on incomplete form submission")
    @Story("Submit incomplete text box form and verify error message")
    @Tag("automated")
    void incompleteFormSubmissionTest() {
        step("Open page /text-box", () -> {
            open("/text-box");

            if($(".fc-dialog-content").isDisplayed())
                $(byText("Consent")).click();
        });
        step("Submit form with missing fields", () -> {
            $("#submit").click();
        });
        step("Check that error message is displayed", () -> {
            $(".text-danger").shouldHave(text("Error: Please fill out all fields"));
        });
    }

    @Test
    @DisplayName("Text box can be filled (Manual)")
    @Story("Perform manual fill and submit of text box form")
    @Tag("manual")
    void fillFormManualTest() {
        step("Open page /text-box");
        step("Fill form");
        step("Check results");
    }

    @Test
    @DisplayName("Form data can be edited (Manual)")
    @Story("Perform manual edit of text box form data")
    @Tag("manual")
    void editFormDataManualTest() {
        step("Open page /text-box");
        step("Edit form data");
        step("Check results");
    }

    @Test
    @DisplayName("Form submission with invalid data (Manual)")
    @Story("Perform manual submission of text box form with invalid data")
    @Tag("manual")
    void invalidFormSubmissionManualTest() {
        step("Open page /text-box");
        step("Submit form with invalid data");
        step("Check error message");
    }
}