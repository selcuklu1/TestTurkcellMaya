package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class ErisimYonetimiPage extends MainPage {

    SelenideElement btnKullaniciErisimTab = $("a[href='#erisimYonetimiTabView:kullanicidanErisimTab']");
    SelenideElement getBtnKullaniciErisimAra = $(By.id("erisimYonetimiTabView:kullanicidanErisimYonetimiListingForm:searchKullaniciButton"));

    @Step("Erişim Yonetimi sayfası aç")
    public ErisimYonetimiPage openPage() {
        ustMenu("Erişim Yönetimi");
        return this;
    }

    @Step("Kullanıcı erişimi tab")
    public ErisimYonetimiPage kullaniciErisimTab() {
        btnKullaniciErisimTab.click();
        return this;
    }

    @Step("Kullanıcı erişim ara")
    public ErisimYonetimiPage kullanıcıErisimAra() {
        getBtnKullaniciErisimAra.click();
        return this;
    }
}
