package page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Exists;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageComponents.IslemMesajlari;
import pageData.MesajTipi;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YonetimHavuzuYonetimiPage extends BaseLibrary {

    private String EklenilenHavuzAdi = null;
    private String EklenilenBirimAdi = null;
    private String EklenilenKullaniciAdi = null;

    SelenideElement txtBirim = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement btnAra = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:searchEntitiesButton"));
    ElementsCollection treeBirimler = $$("div[id='yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:lovTree'] > ul > li");
    SelenideElement btnYonetimHavuzuEkle = $(By.id("yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable:addNewYonetimHavuzuButton"));
    SelenideElement txtYonetimHavuzuAdi = $(By.id("yonetimHavuzuYonetimiEditorForm:adInput"));
    SelenideElement btnYonetimHavuzuKaydet = $(By.id("yonetimHavuzuYonetimiEditorForm:saveYonetimHavuzuButton"));
    SelenideElement tableYonetimHavuzuListesi = $(By.xpath("//tbody[@id='yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable_data']"));

    SelenideElement btnBirimEkle = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable:addNewBirimLinkButton"));
    SelenideElement txtKullananBirim = $(By.id("birimForm:birimList:LovText"));
    ElementsCollection treeKullananBirimler = $$("div[id='birimForm:birimList:D1birimListlovDialogId'] > div[id='birimForm:birimList:lovTree'] > ul > li");
    SelenideElement btnKullananBirimKaydet = $(By.id("birimForm:addBirimListButton"));
    SelenideElement tableKullananBirimListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable_data']");

    SelenideElement btnKullaniciEkle = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable:addNewYonetimHavuzuKullaniciBirimLinkButton"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciBirimForm:kullaniciBirimList:LovText"));
    ElementsCollection treeKullanicilar = $$("div[id='kullaniciBirimForm:kullaniciBirimList:lovTree'] > ul > li");
    SelenideElement btnKullanicilarTreeKapat = $(By.id("kullaniciBirimForm:kullaniciBirimList:lovTreePanelKapat"));
    SelenideElement btnKullanicilarKaydet = $(By.id("kullaniciBirimForm:addKullaniciBirimListButton"));
    SelenideElement tableKullaniciListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable_data']");

    public YonetimHavuzuYonetimiPage() {

        //waitUntil(visibilityOfElementLocated(pageTitle));
    }

    public YonetimHavuzuYonetimiPage birimSec(String _birimAdi){
        txtBirim.setValue(_birimAdi);
        treeBirimler.get(0).click();
        return this;
    }

    public YonetimHavuzuYonetimiPage ara(){
        btnAra.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuEkle(){
        btnYonetimHavuzuEkle.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuAdiDoldur(String _YonetimHavuzuAdi){
        EklenilenHavuzAdi = _YonetimHavuzuAdi;
        txtYonetimHavuzuAdi.setValue(_YonetimHavuzuAdi);
        return this;
    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuKaydet(){
        btnYonetimHavuzuKaydet.click();
        tableYonetimHavuzuListesi.$(By.xpath("./tr[contains(., '"+ EklenilenHavuzAdi +"')]"))
                .shouldBe(Condition.exist);
        return this;
    }

    public YonetimHavuzuYonetimiPage kullananBirimiEkle(){
        btnBirimEkle.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullananBirimSec(String _birimAdi){
        EklenilenBirimAdi = _birimAdi;
        txtKullananBirim.setValue(_birimAdi);
        treeKullananBirimler.get(0).click();
        return this;
    }

    @Step("Birim kaydet")
    public YonetimHavuzuYonetimiPage kullananBirimKaydet(){
        btnKullananBirimKaydet.click();
        //new IslemMesajlari().beklenenMesajTipi(MesajTipi.BASARILI);
        tableKullananBirimListesi.$(By.xpath("./tr[contains(., '"+ EklenilenBirimAdi +"')]")).shouldBe(Condition.exist);
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciEkle() {
        btnKullaniciEkle.click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciSec(String _kullaniciAdi){
        EklenilenKullaniciAdi = _kullaniciAdi;
        txtKullaniciAdi.setValue(_kullaniciAdi);
        treeKullanicilar.get(0).click();
        return this;
    }

    public YonetimHavuzuYonetimiPage kullaniciKaydet(){
        btnKullanicilarKaydet.click();
        tableKullaniciListesi.$(By.xpath("./tr[contains(., '"+ EklenilenKullaniciAdi +"')]")).shouldBe(Condition.exist);
        return this;
    }

}
