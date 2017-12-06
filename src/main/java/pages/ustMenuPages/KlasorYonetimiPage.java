package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class KlasorYonetimiPage extends MainPage {

    SelenideElement btnYeni = $(By.id("klasorYonetimiListingForm:klasorTreeTable:addNewKlasorButton"));

    @Step("Klasör Yönetimi sayfası aç")
    public KlasorYonetimiPage openPage(){
        ustMenu("Klasör Yönetimi");
        return this;
    }
    
    @Step("Yeni")
    public KlasorYonetimiPage yeni(){
        btnYeni.click();
        return this;
    }

}
