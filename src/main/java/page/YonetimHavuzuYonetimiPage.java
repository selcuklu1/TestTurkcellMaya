package page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Exists;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YonetimHavuzuYonetimiPage extends BaseLibrary {

    private String EklenilenHavuzAdi = null;
    private String EklenilenBirimAdi = null;
    private String EklenilenKullaniciAdi = null;

    SelenideElement txtFiltreBirim = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement btnFiltreAra = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:searchEntitiesButton"));
    ElementsCollection treeFiltreBirimler = $$("div[id='yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:lovTree'] > ul > li");
    SelenideElement txtFiltreHavuzuAdi = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement cmbFiltreDurum = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:durumSelectBox"));

    SelenideElement btnYonetimHavuzuEkle = $(By.id("yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable:addNewYonetimHavuzuButton"));
    SelenideElement txtYonetimHavuzuAdi = $(By.id("yonetimHavuzuYonetimiEditorForm:adInput"));
    SelenideElement btnYonetimHavuzuKaydet = $(By.id("yonetimHavuzuYonetimiEditorForm:saveYonetimHavuzuButton"));
    SelenideElement tableYonetimHavuzuListesi = $(By.xpath("//tbody[@id='yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable_data']"));
    ElementsCollection trYonetimHavuzuListesi = $$("tbody[id='yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable_data'] tr[role='row']");


    SelenideElement btnBirimEkle = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable:addNewBirimLinkButton"));
    SelenideElement txtKullananBirim = $(By.id("birimForm:birimList:LovText"));
    ElementsCollection treeKullananBirimler = $$("div[id='birimForm:birimList:D1birimListlovDialogId'] > div[id='birimForm:birimList:lovTree'] > ul > li");
    SelenideElement btnKullananBirimKaydet = $(By.id("birimForm:addBirimListButton"));
    SelenideElement tableKullananBirimListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable_data']");
    ElementsCollection trKullananBirimListesi = $$("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable_data'] tr");


    SelenideElement btnKullaniciTanimla = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable:addNewKullaniciBirimLinkButton"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciBirimForm:kullaniciBirimList:LovText"));
    ElementsCollection treeKullanicilar = $$("div[id='kullaniciBirimForm:kullaniciBirimList:lovTree'] > ul > li");
    SelenideElement btnKullanicilarTreeKapat = $(By.id("kullaniciBirimForm:kullaniciBirimList:lovTreePanelKapat"));
    SelenideElement btnKullanicilarKaydet = $(By.id("kullaniciBirimForm:addKullaniciBirimListButton"));
    SelenideElement tableKullaniciListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable_data']");

    public static class Durum{
        public static String TUMU = "TUMU";
        public static String AKTIF = "AKTIFLER";
        public static String PASIF = "PASIFLER";
    }

    public YonetimHavuzuYonetimiPage() {

        //waitUntil(visibilityOfElementLocated(pageTitle));
    }

    @Step("Yönetim havuzu arama")
    public YonetimHavuzuYonetimiPage ara(String birimAdi, String yonetimHavuzuAdi, String durum){
        if(birimAdi != ""){
            txtFiltreBirim.setValue(birimAdi);
            treeFiltreBirimler
                    .filterBy(text(birimAdi))
                    .get(0)
                    .click();
        }

        if(yonetimHavuzuAdi != "")
            txtFiltreHavuzuAdi.setValue(yonetimHavuzuAdi);

        if(durum != "")
            cmbFiltreDurum.selectOptionContainingText(durum);

        btnFiltreAra.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuEkle(){
        btnYonetimHavuzuEkle.click();
        return this;

    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuAdiDoldur(String yonetimHavuzuAdi){
        EklenilenHavuzAdi = yonetimHavuzuAdi;
        txtYonetimHavuzuAdi.setValue(yonetimHavuzuAdi);
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuKaydet(){
        btnYonetimHavuzuKaydet.click();
        trYonetimHavuzuListesi
                .filterBy(text(EklenilenHavuzAdi))
                .get(0)
                .shouldBe(exist);
        //tableYonetimHavuzuListesi.$(By.xpath("./tr[contains(., '"+ EklenilenHavuzAdi +"')]")).shouldBe(exist);
        return this;
    }

    public YonetimHavuzuYonetimiPage kullananBirimiEkle(){
        btnBirimEkle.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullananBirimSec(String birimAdi){
        EklenilenBirimAdi = birimAdi;
        txtKullananBirim.setValue(birimAdi);
        treeKullananBirimler.get(0).click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullananBirimKaydet(){
        btnKullananBirimKaydet.click();
        trKullananBirimListesi
                .filterBy(text(EklenilenBirimAdi))
                .get(0)
                .shouldBe(exist);
        //tableKullananBirimListesi.$(By.xpath("./tr[contains(., '"+ EklenilenBirimAdi +"')]")).shouldBe(exist);
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciEkle() {
        btnKullaniciTanimla.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciSec(String kullaniciAdi){
        EklenilenKullaniciAdi = kullaniciAdi;
        txtKullaniciAdi.setValue(kullaniciAdi);
        treeKullanicilar.get(0).click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciKaydet(){
        btnKullanicilarKaydet.click();
        tableKullaniciListesi.$(By.xpath("./tr[contains(., '"+ EklenilenKullaniciAdi +"')]")).shouldBe(exist);
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuPasifYap(String yonetimHavuzuAdi){
        trYonetimHavuzuListesi
                .filterBy(text(yonetimHavuzuAdi))
                .get(0)
                .$("button[id*='changeyonetimHavuzuStatusButton']")
                .click();
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuGuncelle(String yonetimHavuzuAdi){
        trYonetimHavuzuListesi
                .filterBy(text(yonetimHavuzuAdi))
                .get(0)
                .$("button[id*='updateyonetimHavuzuButton']")
                .click();
        txtYonetimHavuzuAdi.shouldHave(value(yonetimHavuzuAdi));
        return this;
    }

}
