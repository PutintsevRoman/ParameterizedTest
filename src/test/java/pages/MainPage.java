package pages;



import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public MainPage openPage() {
        open("books");

        // Some staff
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }
    public MainPage search(String value){
        $("#searchBox").setValue(value);
        return this;
    }
    public MainPage checkBook(String value){
        $(".rt-tr-group").$(byText(value)).shouldBe(visible);
        return this;
    }


}
