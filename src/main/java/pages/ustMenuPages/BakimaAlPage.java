package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class BakimaAlPage extends MainPage {

    SelenideElement txtBilgilendirmeMetni = $("textarea[id^='bakimaAlFormId:']");
    SelenideElement btnBakimaAl = $(By.id("bakimaAlFormId:bakimModunaAl"));
    SelenideElement btnBakimdanCikar = $(By.id("bakimaAlFormId:bakimModundanCikar"));
    SelenideElement spanBilgilendirmeMetni = $("span[id^='bakimaAlFormId:'][id$='display']");
    SelenideElement btnKaydet = $(By.xpath("//button//span[contains(@class, 'ui-icon-check')]/.."));
    SelenideElement btnIptal = $(By.xpath("//button//span[contains(@class, 'ui-icon-close')]/.."));
    BelgenetElement txtKullanicilar = comboLov(By.id("bakimaAlFormId:bakimaAlKullanicilarId:LovText"));
    SelenideElement blinkUyari = $(By.xpath("//blink[starts-with(.,'Sistem') and contains(.,'tarihinde bakım moduna alınmıştır. Sisteme aşağıda tanımlanan kullanıcılar giriş yapabilecektir.')]"));
    ElementsCollection tableSecilenKullanicilar = $$("tbody[id='bakimaAlFormId:bakimaAlKullanicilarId:LovSecilenTable_data'] > tr[role='row']");

    @Step("Bilgilendirme metni 500 karakteri geçiyor mu?")
    public BakimaAlPage bilgilendirmeMetni500KarakterKontrolu() {
        if (spanBilgilendirmeMetni.isDisplayed())
            spanBilgilendirmeMetni.click();
        txtBilgilendirmeMetni.shouldBe(Condition.visible);
        boolean isSmallerThan500 = false;
        if (txtBilgilendirmeMetni.getValue().length() <= 500)
            isSmallerThan500 = true;
        Assert.assertTrue(isSmallerThan500);
        return this;
    }

    @Step("Bilgilendirme metni {uzunluk} karakterden küçük veya eşit")
    public BakimaAlPage bilgilendirmeMetniKarakterKontroluMax(int uzunluk) {
        spanBilgilendirmeMetni.click();
        txtBilgilendirmeMetni.shouldBe(Condition.visible);
        boolean isSmaller = false;
        if (txtBilgilendirmeMetni.getValue().length() <= uzunluk)
            isSmaller = true;
        Assert.assertTrue(isSmaller);
        return this;
    }


    @Step("Bakıma al sayfası aç")
    public BakimaAlPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.BakimaAl);
        return this;
    }

    @Step("{0}")
    public BakimaAlPage bilgilendirmeMetniTikla() {
        spanBilgilendirmeMetni.click();
        return this;
    }

    @Step("Bilgilendirme metnine \"{bilgilendirmeMetni}\" değerini gir.")
    public BakimaAlPage bilgilendirmeMetniGir(String bilgilendirmeMetni) {

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", btnBakimaAl);
        btnBakimaAl.waitUntil(Condition.visible, 5000);

        if (spanBilgilendirmeMetni.isDisplayed())
            spanBilgilendirmeMetni.click();

        txtBilgilendirmeMetni
                .shouldBe(Condition.visible)
                .setValue(bilgilendirmeMetni);

        return this;
    }

    @Step("Bilgilendirme metninde Kaydet butonuna tıkla")
    public BakimaAlPage bilgilendirmeMetniKaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Bilgilendirme metninde iptal butonuna tıkla")
    public BakimaAlPage bilgilendirmeMetniIptal() {
        btnIptal.click();
        return this;
    }

    @Step("Bilgilendirme Metni \"{bilgilendirmeMetni}\" değerine sahip olmalı")
    public BakimaAlPage bilgilendirmeMetniKontrol(String bilgilendirmeMetni) {
        spanBilgilendirmeMetni.click();
        txtBilgilendirmeMetni.shouldBe(Condition.visible).shouldHave(Condition.value(bilgilendirmeMetni));
        btnIptal.click();
        return this;
    }

    @Step("{kullaniciAdi} kullanicisini ekle")
    public BakimaAlPage kullaniciEkle(String kullaniciAdi) {
        txtKullanicilar.selectLov(kullaniciAdi);
        return this;
    }

    @Step("{kullaniciAdi} kullanicisini ekle")
    public BakimaAlPage kullaniciKontrol(String kullaniciAdi, boolean shouldBeSelectable) {

        boolean isUserSelectable = txtKullanicilar.isLovValueSelectable(kullaniciAdi);

        if (shouldBeSelectable == true)
            Assert.assertEquals(isUserSelectable, true);
        else
            Assert.assertEquals(isUserSelectable, false);

        return this;
    }

    @Step("Bakıma Al butonuna tıkla")
    public BakimaAlPage bakimaAl() {
        btnBakimaAl.click();
        return this;
    }

    @Step("Kullanıcıları temizle.")
    public BakimaAlPage kullanicilarTemizle() {

        txtKullanicilar.clearAllSelectedItems();
        return this;
    }

    @Step("Uyarı mesajı ve Bakımdan Çıkar butonu kontrolü.")
    public BakimaAlPage bakimdaOlmali(boolean bakimdaOlmalimi) {
        if (bakimdaOlmalimi == true) {
            blinkUyari.shouldBe(Condition.visible);
            btnBakimdanCikar.shouldBe(Condition.visible);
        } else {
            blinkUyari.shouldNotBe(Condition.visible);
            btnBakimaAl.shouldBe(Condition.visible);
        }
        return this;
    }

    @Step("Bakımdan çıkar.")
    public BakimaAlPage bakimdanCikar() {
        btnBakimdanCikar.click();
        return this;
    }

    @Step("Kullanıcı seçildi mi? kontrolü")
    public BakimaAlPage secilenKullaniciKontrol(String[] kullanicilar) {
        for (int i = 0; i < kullanicilar.length; i++) {
            tableSecilenKullanicilar
                    .filterBy(Condition.text(kullanicilar[i])).first().shouldBe(Condition.visible);
        }
        return this;
    }


    @Step("Sisteme girebilecek kullanıcıların Ad, Soyad ve Görev kontrolü")
    public BakimaAlPage sistemeGirebilecekKullanicilarBilgiKontrolEt(){

        for(int i = 0; i <= tableSecilenKullanicilar.size(); i ++){
            tableSecilenKullanicilar.get(0).$("span[class='lovItemTitle']").shouldBe(Condition.visible);
            tableSecilenKullanicilar.get(0).$("span[class='lovItemDetail']").shouldBe(Condition.visible);
        }

        return this;
    }

    @Step("Uyarı kaybolmalı ve Bakımdan Çıkar butonu değişmeli.")
    public BakimaAlPage bakimdanCikarKontrol() {
        btnBakimdanCikar.shouldNotBe(Condition.visible);
        btnBakimaAl.shouldBe(Condition.visible);
        blinkUyari.shouldNotBe(Condition.visible);
        return this;
    }
    // Sistem 06.12.2017 14:19:32 tarihinde bakım moduna alınmıştır. Sisteme aşağıda tanımlanan kullanıcılar giriş yapabilecektir.
    // bakimaAlFormId:bakimModundanCikar

}

