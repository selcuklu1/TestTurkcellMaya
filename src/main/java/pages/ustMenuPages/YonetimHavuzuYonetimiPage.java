package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class YonetimHavuzuYonetimiPage extends MainPage {

    private String EklenilenHavuzAdi = null;
    private String EklenilenBirimAdi = null;
    private String EklenilenKullaniciAdi = null;

    SelenideElement txtFiltreBirim = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement btnFiltreAra = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:searchEntitiesButton"));
    ElementsCollection treeFiltreBirimler = $$("div[id='yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:lovTree'] > ul > li");
    SelenideElement txtFiltreHavuzuAdi = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement cmbFiltreDurum = $(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnGuncelleOnay = $(By.id("baseConfirmationDialog:confirmButton"));

    SelenideElement btnYonetimHavuzuEkle = $(By.id("yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable:addNewYonetimHavuzuButton"));
    SelenideElement txtYonetimHavuzuAdi = $(By.id("yonetimHavuzuYonetimiEditorForm:adInput"));
    SelenideElement btnYonetimHavuzuKaydet = $(By.id("yonetimHavuzuYonetimiEditorForm:saveYonetimHavuzuButton"));
    SelenideElement tableYonetimHavuzuListesi = $(By.xpath("//tbody[@id='yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable_data']"));
    ElementsCollection trYonetimHavuzuListesi = $$("tbody[id='yonetimHavuzuYonetimiListingForm:yonetimHavuzuDataTable_data'] tr[role='row']");


    SelenideElement btnBirimEkle = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable:addNewBirimLinkButton"));
    SelenideElement txtKullananBirim = $(By.id("birimForm:birimList:LovText"));
    ElementsCollection treeKullananBirimler = $$("div[id='birimForm:birimList:D1birimListlovDialogId'] > div[id='birimForm:birimList:lovTree'] > ul > li");
    ElementsCollection treeAltKullananBirimler = $$("div[id='birimForm:birimList:D1birimListlovDialogId'] > div[id='birimForm:birimList:lovTree'] ul li ul li");
    SelenideElement btnKullananBirimKaydet = $(By.id("birimForm:addBirimListButton"));
    SelenideElement tableKullananBirimListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable_data']");
    ElementsCollection trKullananBirimListesi = $$("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuBirimDataTable_data'] tr");
    SelenideElement btnKullananBirimTree = $(By.id("birimForm:birimList:treeButton"));

    SelenideElement btnKullaniciTanimla = $(By.id("yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable:addNewKullaniciBirimLinkButton"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciBirimForm:kullaniciBirimList:LovText"));
    ElementsCollection treeKullanicilar = $$("div[id='kullaniciBirimForm:kullaniciBirimList:lovTree'] > ul > li");
    SelenideElement btnKullanicilarTreeKapat = $(By.id("kullaniciBirimForm:kullaniciBirimList:lovTreePanelKapat"));
    SelenideElement btnKullanicilarKaydet = $(By.id("kullaniciBirimForm:addKullaniciBirimListButton"));
    SelenideElement tableKullaniciListesi = $("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable_data']");
    ElementsCollection trKullaniciListesi = $$("tbody[id='yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable_data'] tr");


    public static class Durum {
        public static String TUMU = "TUMU";
        public static String AKTIF = "AKTIFLER";
        public static String PASIF = "PASIFLER";
    }

    public YonetimHavuzuYonetimiPage() {

        //waitUntil(visibilityOfElementLocated(pageTitle));
    }

    public YonetimHavuzuYonetimiPage openPage() {
        ustMenu("Yönetim Havuzu Yönetimi");
        return this;
    }


    // Arama işlemleri
    @Step("Yönetim Havuzu arama")
    public YonetimHavuzuYonetimiPage ara(String birimAdi, String yonetimHavuzuAdi, String durum, boolean checkIfExists) {
        if (birimAdi != null) {

            comboLov(By.id("yonetimHavuzuYonetimiListingForm:filterPanel:birimLov:LovText")).selectComboLov(birimAdi);
            /*

            txtFiltreBirim.setValue(birimAdi);
            treeFiltreBirimler
                    .filterBy(exactText(birimAdi))
                    .get(0)
                    .click();
             */
        }

        if (yonetimHavuzuAdi != null)
            txtFiltreHavuzuAdi.setValue(yonetimHavuzuAdi);

        if (durum != null)
            cmbFiltreDurum.selectOptionContainingText(durum);

        btnFiltreAra.click();

        if (checkIfExists == true && yonetimHavuzuAdi != null) {
            trYonetimHavuzuListesi
                    .filterBy(exactText(yonetimHavuzuAdi))
                    .get(0)
                    .shouldBe(exist);
        }

        return this;
    }

    // Havuz İşlemleri
    @Step("Yeni Yönetim Havuzu ekle")
    public YonetimHavuzuYonetimiPage yonetimHavuzuEkle(String yonetimHavuzuAdi) {
        btnYonetimHavuzuEkle.click();
        if (yonetimHavuzuAdi != null) {
            yonetimHavuzuAdiDoldur(yonetimHavuzuAdi);
        }
        return this;

    }

    @Step("Yönetim havuzu adı doldur")
    public YonetimHavuzuYonetimiPage yonetimHavuzuAdiDoldur(String yonetimHavuzuAdi) {
        EklenilenHavuzAdi = yonetimHavuzuAdi;
        txtYonetimHavuzuAdi.setValue(yonetimHavuzuAdi);
        return this;
    }

    @Step("Yeni Yönetim Havuzunu kaydet")
    public YonetimHavuzuYonetimiPage yonetimHavuzuKaydet() {
        btnYonetimHavuzuKaydet.click();
        /*
        trYonetimHavuzuListesi
                .filterBy(text(EklenilenHavuzAdi))
                .get(0)
                .shouldBe(exist);
        */
        //tableYonetimHavuzuListesi.$(By.xpath("./tr[contains(., '"+ EklenilenHavuzAdi +"')]")).shouldBe(exist);
        return this;
    }

    @Step("Yönetim Havuzunu Pasif yap")
    public YonetimHavuzuYonetimiPage yonetimHavuzuPasifYap(String yonetimHavuzuAdi) {
        trYonetimHavuzuListesi
                .filterBy(text(yonetimHavuzuAdi))
                .get(0)
                .$("button[id*='changeyonetimHavuzuStatusButton']")
                .click();

        if (btnGuncelleOnay.exists())
            btnGuncelleOnay.click();

        return this;
    }

    @Step("Yönetim Havuzu güncelle")
    public YonetimHavuzuYonetimiPage yonetimHavuzuGuncelle(String yonetimHavuzuAdi, Boolean checkFields) {

        trYonetimHavuzuListesi
                .filterBy(text(yonetimHavuzuAdi))
                .get(0)
                .$("button[id*='updateyonetimHavuzuButton']")
                .click();

        if (checkFields == true) {
            txtYonetimHavuzuAdi.shouldBe(exist);
            trKullananBirimListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));
            trKullaniciListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));

            if (yonetimHavuzuAdi != null)
                txtYonetimHavuzuAdi.shouldHave(value(yonetimHavuzuAdi));
        }


        return this;
    }

    @Step("Yönetim Havuzunu Kontrol et")
    public YonetimHavuzuYonetimiPage yonetimHavuzuKontrol(String yonetimHavuzuAdi, String[] yonetimHavuzuBirimler, String[] yonetimHavuzuKullanicilar) {

        txtYonetimHavuzuAdi.shouldBe(exist);
        trKullananBirimListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));
        trKullaniciListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));

        if (yonetimHavuzuAdi != null)
            txtYonetimHavuzuAdi.shouldHave(value(yonetimHavuzuAdi));

        if (yonetimHavuzuBirimler != null) {
            for (int i = 0; i < yonetimHavuzuBirimler.length; i++) {
                trKullananBirimListesi
                        .filterBy(exactText(yonetimHavuzuBirimler[i]))
                        .get(0)
                        .shouldBe(exist);
            }
        }

        if (yonetimHavuzuKullanicilar != null) {
            for (int i = 0; i < yonetimHavuzuKullanicilar.length; i++) {
                trKullaniciListesi
                        .filterBy(exactText(yonetimHavuzuKullanicilar[i]))
                        .get(0)
                        .shouldBe(exist);
            }
        }
        return this;
    }

    @Step("Yönetim Havuzunu Kullanan Birim Ekle")
    public YonetimHavuzuYonetimiPage kullananBirimEkle(String birimAdi) {
        btnBirimEkle.click();
        EklenilenBirimAdi = birimAdi;
//        btnKullananBirimTree.click();
//        treeKullananBirimler
//                .filterBy(text(birimAdi))
//                .get(0)
//                .click();

        btnKullananBirimKaydet.click();
        trKullananBirimListesi
                .filterBy(exactText(EklenilenBirimAdi))
                .get(0)
                .shouldBe(exist);
        return this;
    }

    // Kullanıcı işemleri
    @Step("Yönetim Havuzunda Tanımlı Kullanıcı ekle")
    public YonetimHavuzuYonetimiPage kullaniciEkle(String kullaniciAdi) {
        EklenilenKullaniciAdi = kullaniciAdi;
        btnKullaniciTanimla.click();
        txtKullaniciAdi.setValue(kullaniciAdi);
        treeKullanicilar
                .filterBy(exactText(kullaniciAdi))
                .get(0)
                .click();

        btnKullanicilarKaydet.click();
        trKullaniciListesi
                .filterBy(exactText(kullaniciAdi))
                .get(0)
                .shouldBe(exist);

        return this;
    }

    @Step("Yönetim Havuzunda Tanımlı Kullanıcı sil")
    public YonetimHavuzuYonetimiPage kullaniciSil(String kullaniciAdi) {
        trKullaniciListesi
                .filterBy(exactText(kullaniciAdi))
                .get(0)
                .$("button[id*='deleteYonetimHavuzuKullaniciBirimButton']")
                .click();

        trKullaniciListesi
                .filterBy(exactText(kullaniciAdi))
                .shouldHave(CollectionCondition.sizeLessThan(1));
        // yonetimHavuzuYonetimiEditorForm:yonetimHavuzuKullaniciBirimDataTable:0:deleteYonetimHavuzuKullaniciBirimButton
        return this;
    }


}
