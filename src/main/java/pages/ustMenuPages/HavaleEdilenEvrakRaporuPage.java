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
    //SelenideElement paginator_next = $("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] [class='ui-icon ui-icon-seek-next']");
    SelenideElement paginator_next = $("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] [class^='ui-paginator-next ui-state-default ui-corner-all']");
    SelenideElement paginator_next_disabled = $("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] [class='ui-paginator-next ui-state-default ui-corner-all ui-state-disabled']");


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

    @Step("Havale Eden birim doldur: {birim}")
    public HavaleEdilenEvrakRaporuPage havaleEdenBirimDoldur(String birim) {
        txtHavaleEdenBirim.selectLov(birim);
        return this;
    }

    @Step("Havale Edilen kullanici doldur: {kullanici}")
    public HavaleEdilenEvrakRaporuPage havaleEdilenKullaniciDoldur(String kullanici) {
        txtHavaleEdilenKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Havale Eden kullanici doldur: {kullanici}")
    public HavaleEdilenEvrakRaporuPage havaleEdenKullaniciDoldur(String kullanici) {
        txtHavaleEdenKullanici.selectLov(kullanici);
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

        Assert.assertEquals(txtHavaleEdenBirim.isDisplayed(), true, "Havale Eden Birim Alanı Görüntülendi");
        Allure.addAttachment("Havale Eden Birim Alanı Görüntülendi : ", "");

        Assert.assertEquals(txtHavaleEdenKullanici.isDisplayed(), true, "Havale Eden Kullanıcı Alanı Görüntülendi");
        Allure.addAttachment("Havale Eden Kullanıcı Alanı Görüntülendi : ", "");

        Assert.assertEquals(txtHavaleEdilenBirim.isDisplayed(), true, "Havale Edilen Birim Görüntülendi");
        Allure.addAttachment("Havale Edilen Birim Alanı Görüntülendi : ", "");

        Assert.assertEquals(txtHavaleEdilenKullanici.isDisplayed(), true, "Havale Edilen Kullanici Alanı Görüntülendi");
        Allure.addAttachment("Havale Edilen Kullanici Alanı Görüntülendi : ", "");

        Assert.assertEquals(txtHevaleTarihAraligiBaslangic.isDisplayed(), true, "Havale Tarih Araligi Baslangic Alanı Görüntülendi");
        Allure.addAttachment("Havale Tarih Araligi Baslangic Alanı Görüntülendi : ", "");

        Assert.assertEquals(txtHevaleTarihAraligiBitis.isDisplayed(), true, "Havale Tarih Araligi Bitis Alanı Görüntülendi");
        Allure.addAttachment("Havale Tarih Araligi Bitis Alanı Görüntülendi : ", "");

        Assert.assertEquals(btnTemizle.isDisplayed(), true, "Temizle Alani Görüntülendi");
        Allure.addAttachment("Temizle Alanı Görüntülendi : ", "");

        Assert.assertEquals(btnSorgula.isDisplayed(), true, "Sorgula Alanı Görüntülendi");
        Allure.addAttachment("Sorgula Alanı Görüntülendi : ", "");

        takeScreenshot();
        return this;
    }

    @Step("Havale Edilen Evrak Raporu Tablosunda kontrol. Evrak: {konu}")
    public HavaleEdilenEvrakRaporuPage rapordaEvraklarıListele(String konu) {
        boolean durum = false;
        System.out.println("konu:" + konu);
        boolean paginator_next_durum = paginator_next_disabled.isDisplayed();
        if(tblHavaleEdilenEvrakRaporuListele.size() > 0)
            paginator_next_durum = false;
        while (!paginator_next_durum && !durum) {
            durum = tblHavaleEdilenEvrakRaporuListele.filterBy(text(konu)).size() > 0;
            System.out.println("durum1:" + durum + " paginator_next status1:" + paginator_next_durum);

            paginator_next_durum = paginator_next_disabled.isDisplayed();
            System.out.println("durum2:" + durum + " paginator_next status2:" + paginator_next_durum);

            if (durum == false && paginator_next_durum == false)
                paginator_next.click();

        }

        Assert.assertEquals(durum, true, "Evrak listelenmedi");
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
        takeScreenshot();
        return this;
    }

}

