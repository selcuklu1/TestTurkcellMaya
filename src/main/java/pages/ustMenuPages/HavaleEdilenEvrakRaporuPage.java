package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class HavaleEdilenEvrakRaporuPage extends MainPage {

    BelgenetElement txtHavaleEdenBirim = comboLov(By.id("havaleEvrakRaporuForm:havaleEdenebirimLovId:LovText"));
    BelgenetElement txtHavaleEdenKullanici = comboLov(By.id("havaleEvrakRaporuForm:havaleEdenKullaniciLovId:LovText"));
    BelgenetElement txtHavaleEdilenBirim = comboLov(By.id("havaleEvrakRaporuForm:havaleEdilenbirimLovId:LovText"));
    BelgenetElement txtHavaleEdilenKullanici = comboLov(By.id("havaleEvrakRaporuForm:havaleEdilenKullaniciLovId:LovText"));
    SelenideElement txtHevaleTarihAraligiBaslangic = $(By.id("havaleEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement txtHevaleTarihAraligiBitis = $(By.id("havaleEvrakRaporuForm:sonTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("havaleEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnTemizle = $(By.id("havaleEvrakRaporuForm:temizleButton"));

    ElementsCollection tblHavaleEdilenEvrakRaporuListele = $$("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab'] tbody tr");
    SelenideElement paginator_next = $("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] [class='ui-icon ui-icon-seek-next']");

    SelenideElement frmInboxItemInfoForm = $("form[id='windowReadOnlyForm']");

    @Step("Havale Edilen Evrak Raporu sayfasını aç")
    public HavaleEdilenEvrakRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.HavaleEdilenEvrakRaporu);
        return this;
    }

    @Step("Havale Edilen birim doldur: {birim}")
    public HavaleEdilenEvrakRaporuPage havaleEdilenBirimDoldur(String birim) {
        txtHavaleEdilenBirim.selectLov(birim);
        return this;
    }

    @Step("Havale Edilen birim doldur: {birim}")
    public HavaleEdilenEvrakRaporuPage havaleEdilenKullaniciDoldur(String kullanici) {
        txtHavaleEdilenKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Havale Tarih Aralığı başlanıgıcı doldur: {baslangicTarih}")
    public HavaleEdilenEvrakRaporuPage havaleTarihAraligiBaslangicDoldur(String baslangicTarih) {
        txtHevaleTarihAraligiBaslangic.setValue(baslangicTarih);
        return this;
    }

    @Step("Havale Tarih Aralığı bitişi doldur: {bitisTarih}")
    public HavaleEdilenEvrakRaporuPage havaleTarihAraligiBitisDoldur(String bitisTarih) {
        txtHevaleTarihAraligiBitis.setValue(bitisTarih);
        return this;
    }

    @Step("Sorgula tıklanır")
    public HavaleEdilenEvrakRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Evrak detay seçilir: {evrakNo}")
    public HavaleEdilenEvrakRaporuPage evrakDetaySec(String evrakNo) {
        $$("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_data'] > tr").filterBy(text(evrakNo))
                .get(0).$("button").click();
        return this;
    }

    @Step("Evrak detayı geldiği görünür")
    public HavaleEdilenEvrakRaporuPage evrakDetayGeldigGorme() {
        boolean durum = $$(By.id("windowReadOnlyEvrakDialog")).size() > 0;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Havale Edilen Evrak Raporundaki Kontroller")
    public HavaleEdilenEvrakRaporuPage havaleEdilenEvrakRaporAlanKontrolu() {
        String text = "";
        if (txtHavaleEdenBirim.isDisplayed()) {
            text += "Havale Eden Birim,";
            Assert.assertEquals(txtHavaleEdenBirim.isDisplayed(), true, "Havale Eden Birim Alanı Görüntülendi");
            Allure.addAttachment("Havale Eden Birim Alanı Görüntülendi : ", "");
        }
        if (txtHavaleEdenKullanici.isDisplayed()) {
            text += "Havale Eden Kullanıcı,";
            Assert.assertEquals(txtHavaleEdenKullanici.isDisplayed(), true, "Havale Eden Kullanıcı Alanı Görüntülendi");
            Allure.addAttachment("Havale Eden Kullanıcı Alanı Görüntülendi : ", "");
        }
        if (txtHavaleEdilenBirim.isDisplayed()) {
            text += "Havale Edilen Birim, ";
            Assert.assertEquals(txtHavaleEdilenBirim.isDisplayed(), true, "Havale Edilen Birim Görüntülendi");
            Allure.addAttachment("Havale Edilen Birim Alanı Görüntülendi : ", "");
        }
        if (txtHavaleEdilenKullanici.isDisplayed()) {
            text += "Havale Edilen Kullanici,";
            Assert.assertEquals(txtHavaleEdilenKullanici.isDisplayed(), true, "Havale Edilen Kullanici Alanı Görüntülendi");
            Allure.addAttachment("Havale Edilen Kullanici Alanı Görüntülendi : ", "");
        }
        if (txtHevaleTarihAraligiBaslangic.isDisplayed()) {
            text += "Havale Tarih Araligi Baslangic,";
            Assert.assertEquals(txtHevaleTarihAraligiBaslangic.isDisplayed(), true, "Havale Tarih Araligi Baslangic Alanı Görüntülendi");
            Allure.addAttachment("Havale Tarih Araligi Baslangic Alanı Görüntülendi : ", "");
        }
        if (txtHevaleTarihAraligiBitis.isDisplayed()) {
            text += "Havale Tarih Araligi Bitis,";
            Assert.assertEquals(txtHevaleTarihAraligiBitis.isDisplayed(), true, "Havale Tarih Araligi Bitis Alanı Görüntülendi");
            Allure.addAttachment("Havale Tarih Araligi Bitis Alanı Görüntülendi : ", "");
        }
        if (btnTemizle.isDisplayed()) {
            text += "Temizle,";
            Assert.assertEquals(btnTemizle.isDisplayed(), true, "Temizle Alani Görüntülendi");
            Allure.addAttachment("Temizle Alanı Görüntülendi : ", "");
        }
        if (btnSorgula.isDisplayed()) {
            text += "Sorgulama Butonu alanları gösterilmektedir.";
            Assert.assertEquals(btnSorgula.isDisplayed(), true, "Sorgula Alanı Görüntülendi");
            Allure.addAttachment("Sorgula Alanı Görüntülendi : ", "");
        }
        Allure.addAttachment("Alan Kontrolleri : ", text);
        takeScreenshot();
        return this;
    }

    @Step("Havale Edilen Evrak Raporu Tablosunda kontrol. Evrak: {konu}")
    public HavaleEdilenEvrakRaporuPage rapordaEvraklarıListele(String konu) {
        boolean durum = false;
        while (paginator_next.isDisplayed() && !durum) {
            durum = tblHavaleEdilenEvrakRaporuListele.filterBy(text(konu)).size() > 0;
            if (durum == false)
                paginator_next.click();
        }

        Assert.assertEquals(durum, true, "Evrak listelendi");
        Allure.addAttachment("Evrak listelendi", konu);
        return this;
    }

    @Step("Havale Edilen Evrak Raporu Tablosunda Detay Tikla. Evrak: {konu}")
    public HavaleEdilenEvrakRaporuPage rapordaEvraklarıListeleDetayTikla(String konu) {
        boolean durum = false;
        while (paginator_next.isDisplayed() && !durum) {
            durum = tblHavaleEdilenEvrakRaporuListele.filterBy(text(konu)).size() > 0;
            if (durum)
                tblHavaleEdilenEvrakRaporuListele.filterBy(text(konu)).first().$$("td [id$='evrakGosterButton']").get(0).click();
            if (durum == false)
                paginator_next.click();
        }

        Assert.assertEquals(durum, true, "Evrak listelendi");
        Allure.addAttachment("Evrak listelendi", konu);
        return this;
    }

    @Step("Ekran Detay ekranı açılır\n")
    public HavaleEdilenEvrakRaporuPage ekranKontrolEvrakDetayi() {
        Assert.assertEquals(frmInboxItemInfoForm.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

}

