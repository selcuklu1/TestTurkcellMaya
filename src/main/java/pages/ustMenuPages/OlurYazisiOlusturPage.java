package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OlurYazisiOlusturPage extends MainPage {

    // Onay Akışı Elementleri
    SelenideElement btnOnayAkisiEkle = $("#yeniOnayEvrakForm button[id*='onayAkisiEkle']");
    ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
    ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");
    SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisLov:LovText']");
    SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisLov:lovTreePanelKapat']");

    @Step("Olur yazısı oluştur sayfasını aç")
    public OlurYazisiOlusturPage open() {
        ustMenu("Olur Yazısı Oluştur");
        return this;
    }

    @Step("Onay Akışı Ekle")
    public OlurYazisiOlusturPage onayAkisiEkle(){
        btnOnayAkisiEkle.click();
        return this;
    }

    @Step("Onay akışında güncel gelen kullanıcıyı kontrol et")
    public OlurYazisiOlusturPage onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi){
        trOnayAkisiEkleKullanicilar
                .filterBy(exactText(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .shouldHave(value(kullaniciTipi));
        return this;
    }

    @Step("Onay akışı listesinde listelenen kullanıcıyı kontrol et")
    public OlurYazisiOlusturPage onayAkisiTreeKullaniciKontrol(String kullaniciAdi, Boolean exist){
        txtOnayAkisiKullanicilarInput.setValue(kullaniciAdi);
        if (exist == true)
            listOnayAkisikullanicilar
                    .filterBy(exactText(kullaniciAdi))
                    .get(0)
                    .shouldBe(Condition.exist);
        else
            listOnayAkisikullanicilar
                    .filterBy(exactText(kullaniciAdi))
                    .get(0)
                    .shouldBe(not(Condition.exist));

        if(btnOnayAkisiPanelKapat.isDisplayed())
            btnOnayAkisiPanelKapat.click();
        return this;
    }






}
