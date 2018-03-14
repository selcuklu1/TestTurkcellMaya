package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
public class EvrakHavaleKurallariYonetimiPage extends MainPage {
    ElementsCollection tblEvraklar = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] tbody tr");
    ElementsCollection tblKuralinTanimliOlduguBirimler = $$("[id='havaleKuralYonetimiEditorForm:kullaniciBirimDataTable'] tbody tr");
    SelenideElement window = $("#havaleKuralYonetimiListingForm");
    SelenideElement btnIlkGuncelle = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:0:updateHavaleKuralButton"));
    SelenideElement btnIlkPasifYap = $(By.id("havaleKuralYonetimiEditorForm:kullaniciBirimDataTable:0:kullaniciBirimPasifButton"));
    SelenideElement btnIlkAktifYap = $("[id$='kullaniciBirimAktifButton']");
    SelenideElement btnAra = $("[id='havaleKuralYonetimiListingForm:filterPanel:searchEntitiesButton']");
    SelenideElement btnSil = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:0:deleteHavaleKuralButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
    BelgenetElement txtBirim = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement cmbDurum = $(By.id("havaleKuralYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement cmbGeldigiYerTipi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerTipiSelect"));
    SelenideElement chkAltBirimDahil = $(By.id("havaleKuralYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox_input"));
    BelgenetElement txtKullanici = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerKullaniciLov:LovText"));
    SelenideElement btnYeni = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:addNewHavaleKuralButton"));
    ElementsCollection cmbEvrakTuru = $$("[class='ui-selectcheckboxmenu-label-container']");
    ElementsCollection cmbEvrakTuruGenelge = $$("[class='ui-selectcheckboxmenu-items ui-selectcheckboxmenu-list ui-widget-content ui-widget ui-corner-all ui-helper-reset'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    BelgenetElement txtKonuKodu = comboLov(By.id("havaleKuralYonetimiEditorForm:konuKoduKriterLov:LovText"));
    SelenideElement txtKuralAdi = $(By.id("havaleKuralYonetimiEditorForm:kuralAdi"));
    SelenideElement btnKuralinTanimliOlduguBirimlerYeni = $(By.id("havaleKuralYonetimiEditorForm:kullaniciBirimDataTable:addNewKullaniciBirimLinkButton"));
    BelgenetElement txtBirimEkleBirim = comboLov(By.id("hkKullaniciBirimEditorForm:havaleKuralKullaniciBirimIliskiBirimLov:LovText"));
    SelenideElement chkBirimEkleAltBirimIlk = $("[id='hkKullaniciBirimEditorForm:birimDataTable'] tbody tr div[id^='hkKullaniciBirimEditorForm:birimDataTable:0'] div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    ElementsCollection chkBirimEkleAltBirimIlkAd = $$("[id='hkKullaniciBirimEditorForm:birimDataTable_data'] tr[data-ri='0'] td");
    SelenideElement chkBirimAltBirimlerDahil = $(By.id("hkKullaniciBirimEditorForm:altBirimlerDahilCheckbox_input"));
    SelenideElement btnBirimEkleEkle = $(By.id("hkKullaniciBirimEditorForm:saveKullaniciBirimButton"));
    SelenideElement btnKuralEklemeKaydet = $(By.id("havaleKuralYonetimiEditorForm:saveHavaleKuralButton"));
    SelenideElement sonTable = $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_paginator_top'] [class='ui-paginator-last ui-state-default ui-corner-all']");
    SelenideElement txtFiltreleKuralAdi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:adFilterInput"));
    BelgenetElement txtFiltreleGeldigiYerBirim = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerBirimLov:LovText"));
    BelgenetElement txtFiltreleGeldigiYerGercekKisi = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerGercekKisiLov:LovText"));
    BelgenetElement txtFiltreleGeldigiYerTuzelKisi = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerTuzelKisiLov:LovText"));
    BelgenetElement txtFiltreleGeldigiYerKurum = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerKurumLov:LovText"));

    BelgenetElement txtGeldigiYerBirim = comboLov(By.id("havaleKuralYonetimiEditorForm:geldigiYerBirimLov:LovText"));
    BelgenetElement txtGeldigiYerKullanici = comboLov(By.id("havaleKuralYonetimiEditorForm:geldigiYerKullaniciLov:LovText"));
    BelgenetElement txtGeldigiYerGercekKisi = comboLov(By.id("havaleKuralYonetimiEditorForm:geldigiYerGercekKisiLov:LovText"));
    BelgenetElement txtGeldigiYerTuzelKisi = comboLov(By.id("havaleKuralYonetimiEditorForm:geldigiYerTuzelKisiLov:LovText"));
    BelgenetElement txtGeldigiYerKurum = comboLov(By.id("havaleKuralYonetimiEditorForm:geldigiYerKurumLov:LovText"));

    //TODO Kime havale edilicek
    BelgenetElement txtKimeHavaleEdilecekBirim = comboLov(By.id("havaleKuralYonetimiEditorForm:havaleBirimLov:LovText"));
    BelgenetElement txtKimeHavaleEdilecekKisi = comboLov(By.id("havaleKuralYonetimiEditorForm:havaleKullaniciLov:LovText"));
    BelgenetElement txtKimeHavaleEdilecekKullaniciListesi = comboLov(By.id("havaleKuralYonetimiEditorForm:havaleKullaniciListesiLov:LovText"));
    SelenideElement txtKimeHavaleEdilecekAciklama = $(By.id("havaleKuralYonetimiEditorForm:havaleAciklama"));
    //TODO

    //Kuralın tanımlı olduğu birimler
    SelenideElement cmbKuralınTanimliOlduguBirimler = $(By.id("havaleKuralYonetimiEditorForm:kullaniciBirimDataTable:durumSelectBox"));


    @Step("Kural ekleme kaydet")
    public EvrakHavaleKurallariYonetimiPage kuralEklemeKaydet() {
        clickJs(btnKuralEklemeKaydet);
        return this;
    }


    @Step("Kuralın tanımlı olduğu birimler alanından {durum} seçilir")
    public EvrakHavaleKurallariYonetimiPage kuralinTanimliOlduguBirimlerSec(String durum) {
        cmbKuralınTanimliOlduguBirimler.selectOption(durum);
        return this;
    }

    @Step("Güncelle")
    public EvrakHavaleKurallariYonetimiPage ilkGuncelle() {
        btnIlkGuncelle.click();
        return this;
    }

    @Step("Kuralın tanımlı olduğu birimler alanında listelenen aktif birimlerden sağında pasif yap butonu tıklanır.")
    public EvrakHavaleKurallariYonetimiPage ilkPasifYap() {
        clickJs(btnIlkPasifYap);
        return this;
    }

    @Step("Aktif Yap")
    public EvrakHavaleKurallariYonetimiPage ilkAktifYap() {
        clickJs(btnIlkAktifYap);
        return this;
    }

    @Step("Birim ekle")
    public EvrakHavaleKurallariYonetimiPage birimEkleEkle() {
        clickJs(btnBirimEkleEkle);
        return this;
    }

    @Step("Seçilen birim ve alt birimlerin kurala birim olarak atandığı görülür.")
    public EvrakHavaleKurallariYonetimiPage secilenBirimVeAltBirimlerinAtandigiGorulur(String kullanici) {
        boolean durum = $$("[id='havaleKuralYonetimiEditorForm:kullaniciBirimDataTable']").filterBy(Condition.text(kullanici)).size() == 1;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Birim sil")
    public EvrakHavaleKurallariYonetimiPage yeniBirimSil(String birimAdi) {
        tblKuralinTanimliOlduguBirimler.filterBy(Condition.text(birimAdi)).get(0).$("[id$='deleteKullaniciBirimButton']").click();
        return this;
    }

    @Step("Kullanıcı / Birim ilişkisini silmek istediğinizden emin misiniz?  Uyarı verdiği görülür.")
    public EvrakHavaleKurallariYonetimiPage birimIliskisiniSilmekIstemisinizUyariGeldigiGorme() {
        boolean durum = $$("[id='baseConfirmationDialog:dialog']").size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Birim doldur")
    public EvrakHavaleKurallariYonetimiPage birimEkleBirimDoldur(String birim) {
        txtBirimEkleBirim.selectLov(birim);
        return this;
    }

    @Step("Alt birim eklenir")
    public EvrakHavaleKurallariYonetimiPage birimEkleAltBirimEkle(boolean durum) {
        chkBirimEkleAltBirimIlk.click();
        takeScreenshot();
        return this;
    }

    public String birimEkleAltBirimIlkAdCek() {
        String altBirim = chkBirimEkleAltBirimIlkAd.get(1).$("div[class='ui-dt-c']").getText();
        return altBirim;
    }

    @Step("Birim alanında alt birim seçeneği seçilir")
    public EvrakHavaleKurallariYonetimiPage birimEkleAltBirimlerDahil(boolean durum) {
        //chkBirimAltBirimlerDahil.setSelected(durum);
        $("[id='hkKullaniciBirimDialogPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Filtrele alanındaki kural adı alanını doldur {kuralAdi} | {alan}")
    public EvrakHavaleKurallariYonetimiPage filtreleKuralAdiDoldur(String kuralAdi, String alan) {
        txtFiltreleKuralAdi.setValue(kuralAdi);
        return this;
    }

    @Step("Kuralın tanımlı olduğu birimler")
    public EvrakHavaleKurallariYonetimiPage kuralinTanimliOlduguBirimlerYeni() {
        btnKuralinTanimliOlduguBirimlerYeni.click();
        return this;
    }

    @Step("Evrak türü Genelge seçilir")
    public EvrakHavaleKurallariYonetimiPage evrakTuruSecGenelge() {
        cmbEvrakTuru.get(0).click();
        cmbEvrakTuruGenelge.get(2).click();
        cmbEvrakTuru.get(0).click();
        return this;
    }

    @Step("Evrak türü Beyanname seçilir")
    public EvrakHavaleKurallariYonetimiPage evrakTuruSecBeyanname() {
        cmbEvrakTuru.get(0).click();
        cmbEvrakTuruGenelge.get(3).click();
        cmbEvrakTuru.get(0).click();
        return this;
    }

    @Step("Evrak türü Dilekçe seçilir")
    public EvrakHavaleKurallariYonetimiPage evrakTuruSecDilekce() {
        cmbEvrakTuru.get(0).click();
        cmbEvrakTuruGenelge.get(1).click();
        cmbEvrakTuru.get(0).click();
        return this;
    }

    @Step("Evrak türü {turu} seçilir")
    public EvrakHavaleKurallariYonetimiPage evrakTuruSec(int i, String turu) {
        cmbEvrakTuru.get(0).click();
        cmbEvrakTuruGenelge.get(i).click();
        cmbEvrakTuru.get(0).click();
        return this;
    }

    @Step("Konu kodu doldur")
    public EvrakHavaleKurallariYonetimiPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Evrak dili Türkçe seçilir")
    public EvrakHavaleKurallariYonetimiPage evrakDiliTurkceSec() {
        boolean durum = $$(By.id("havaleKuralYonetimiEditorForm:selectedEvrakDiliListId"))
                .filterBy(Condition.text("Türkçe")).size() == 1;
        if (durum == true) {
        }
        return this;
    }

    @Step("Yeni kural oluştur")
    public EvrakHavaleKurallariYonetimiPage yeniKural() {
        btnYeni.click();
        return this;
    }

    @Step("Tablo değer kontrolu")
    public EvrakHavaleKurallariYonetimiPage tabloKontrol(String deger) {
        $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_data']").shouldHave(text(deger));
        return this;
    }

    @Step("Alt birim dahil seçilir")
    public EvrakHavaleKurallariYonetimiPage altBirimDahilSec(boolean secim) {
        chkAltBirimDahil.setSelected(secim);
        return this;
    }

    @Step("Geldiği yer tipi {geldigiYer} seçilir")
    public EvrakHavaleKurallariYonetimiPage geldigiYerTipiSec(String geldigiYer) {
        cmbGeldigiYerTipi.selectOption(geldigiYer);
        return this;
    }

    @Step("Birim doldur")
    public EvrakHavaleKurallariYonetimiPage filtreleGeldigiYerBirimDoldur(String birim) {
        txtFiltreleGeldigiYerBirim.selectLov(birim);
        return this;
    }

    @Step("Gerçek Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage filtreleGeldigiYerGercekKisiDoldur(String gercekKisi) {
        txtFiltreleGeldigiYerGercekKisi.selectLov(gercekKisi);
        return this;
    }

    @Step("Kullanıcı doldur")
    public EvrakHavaleKurallariYonetimiPage filtreleGeldigiYerKullaniciDoldur(String kullanici) {

        //İlk selectLov seçemiyor, sebeb araştırdık tek çözüm aşağıdaki gibi
        txtGeldigiYerKullanici.type(kullanici).getSelectableItems().first().click();
        txtGeldigiYerKullanici.closeTreePanel();
        if (!txtGeldigiYerKullanici.isLovSelected())
            txtGeldigiYerKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Tüzel Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage filtreleGeldigiYerTuzelKisiDoldur(String tuzelKisi) {
        txtFiltreleGeldigiYerTuzelKisi.selectLov(tuzelKisi);
        return this;
    }

    @Step("Kurum doldur")
    public EvrakHavaleKurallariYonetimiPage filtreleGeldigiYerKurumDoldur(String kurum) {
        txtFiltreleGeldigiYerKurum.selectLov(kurum);
        return this;
    }

    @Step("Birim doldur")
    public EvrakHavaleKurallariYonetimiPage geldigiYerBirimDoldur(String birim) {
        txtGeldigiYerBirim.selectLov(birim);
        return this;
    }

    @Step("Gerçek Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage geldigiYerGercekKisiDoldur(String gercekKisi) {
        txtGeldigiYerGercekKisi.selectLov(gercekKisi);
        return this;
    }

    @Step("Kullanıcı doldur")
    public EvrakHavaleKurallariYonetimiPage geldigiYerKullaniciDoldur(String kullanici) {

        //İlk selectLov seçemiyor, sebeb araştırdık tek çözüm aşağıdaki gibi
        txtGeldigiYerKullanici.type(kullanici).getSelectableItems().first().click();
        txtGeldigiYerKullanici.closeTreePanel();
        if (!txtGeldigiYerKullanici.isLovSelected())
            txtGeldigiYerKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Tüzel Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage geldigiYerTuzelKisiDoldur(String tuzelKisi) {
        txtGeldigiYerTuzelKisi.selectLov(tuzelKisi);
        return this;
    }

    @Step("Kurum doldur")
    public EvrakHavaleKurallariYonetimiPage geldigiYerKurumDoldur(String kurum) {
        txtGeldigiYerKurum.selectLov(kurum);
        return this;
    }

    @Step("Durum Seçiniz")
    public EvrakHavaleKurallariYonetimiPage durumSec(String durum) {
        cmbDurum.selectOption(durum);
        return this;
    }

    @Step("Kural adı doldur")
    public EvrakHavaleKurallariYonetimiPage kuralAdiDoldur(String kuralAdi) {
        txtKuralAdi.setValue(kuralAdi);
        return this;
    }
    @Step("Kural adı doldur")
    public EvrakHavaleKurallariYonetimiPage filtrelemeKuralAdiDoldur(String kuralAdi) {
        txtFiltreleKuralAdi.setValue(kuralAdi);
        return this;
    }

    public String kuralAdiCek() {
        String kuralAdi = txtKuralAdi.getValue();
        System.out.println(kuralAdi);
        return kuralAdi;
    }

    @Step("Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage kimeHavaleEdilecekKisiDoldur(String kisi, String birim) {
        txtKimeHavaleEdilecekKisi.type(kisi).getDetailItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Birim doldur")
    public EvrakHavaleKurallariYonetimiPage kimeHavaleEdilecekBirimDoldur(String birim) {
        txtKimeHavaleEdilecekBirim.type(birim).getTitleItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Kullanıcı Listesi doldur")
    public EvrakHavaleKurallariYonetimiPage kimeHavaleEdilecekKullaniciListesiDoldur(String kullaniciListesi) {
        txtKimeHavaleEdilecekKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı Listesi doldur")
    public EvrakHavaleKurallariYonetimiPage kimeHavaleEdilecekAciklamaDoldur(String aciklama) {
        txtKimeHavaleEdilecekAciklama.setValue(aciklama);
        return this;
    }


    @Step("Birim alanı doldurulur")
    public EvrakHavaleKurallariYonetimiPage birimDoldur(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }

    @Step("Sil")
    public EvrakHavaleKurallariYonetimiPage sil() {
        btnSil.click();
        return this;
    }

    @Step("Sil \"{konu}\" | \"{konuAdi}\" ")
    public EvrakHavaleKurallariYonetimiPage sil(String konu, String konuAdi) {
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='deleteHavaleKuralButton']").click();
        return this;
    }

    @Step("Kopyala \"{konu}\" | \"{konuAdi}\" ")
    public EvrakHavaleKurallariYonetimiPage kopyala(String konu, String konuAdi) {
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='copyHavaleKuralButton']").click();
        return this;
    }

    @Step("PasifYap \"{konu}\" | \"{konuAdi}\" ")
    public EvrakHavaleKurallariYonetimiPage pasifYap(String konu, String konuAdi) {
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='havaleKuralPasifButton']").click();
        return this;
    }

    @Step("Güncelle \"{konu}\" | \"{konuAdi}\" ")
    public EvrakHavaleKurallariYonetimiPage havaleKurallariListesiGuncelle(String konu, String konuAdi) {
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='updateHavaleKuralButton']").click();
        return this;
    }

    @Step("Seçilen kuralın bilgilerinin sağda güncellemek için açıldığı görülür.")
    public EvrakHavaleKurallariYonetimiPage kuralGuncellemeEkraniGeldigiGorme() {
        boolean durum = $$(By.id("havaleKuralYonetimiEditorForm:havaleKuralYonetimiEditorPanel_header")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Aktif yap \"{konu}\" | \"{konuAdi}\" ")
    public EvrakHavaleKurallariYonetimiPage aktifYap(String konu, String konuAdi) {
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='kuralAktifButton']").click();
        return this;
    }


    @Step("Havale kuralını silmek istediğinize emin misiniz? - uyarı verdiği görülür")
    public EvrakHavaleKurallariYonetimiPage havaleKuraliniSilmekIstediginizeEminMisinizGeldigiGorme() {
        boolean durum = $$("[id='baseConfirmationDialog:dialog']").size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("İslem onayı evet")
    public EvrakHavaleKurallariYonetimiPage islemOnayiEvet() {
        btnIslemOnayiEvet.pressEnter();
        return this;
    }

    @Step("Ara")
    public EvrakHavaleKurallariYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Kayıtlı bütün kuralların listelendiği görülür.")
    public EvrakHavaleKurallariYonetimiPage havaleKurallariListesiGorme() {
        boolean durum = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_data'] >tr").size() > 0;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("")
    public EvrakHavaleKurallariYonetimiPage pasifYapilanKullaniciGuncelle(String kuralAdi) {

        if ($("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_paginator_top'] [class='ui-paginator-last ui-state-default ui-corner-all']").exists() == true)
            $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_paginator_top'] [class='ui-paginator-last ui-state-default ui-corner-all']").pressEnter();

        tblEvraklar.filterBy(Condition.text(kuralAdi)).get(0).$("[id$='updateHavaleKuralButton']").click();
        return this;
    }

    @Step("Kullanıcı doldur")
    public EvrakHavaleKurallariYonetimiPage kullaniciDoldur(String kullanici) {
        txtKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Evrak havale kuralları yonetimi sayfası açılır")
    public EvrakHavaleKurallariYonetimiPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.EvrakHavaleKurallariYonetimi);
        return this;
    }


}
