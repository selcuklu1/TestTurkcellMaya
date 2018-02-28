package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GelenEvrakZimmetRaporuPage extends MainPage {
    SelenideElement sorgula = $(By.id("gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:sorgulaButtonTab1"));
    SelenideElement zimmetRaporTablo = $(By.id("gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1_data"));
    SelenideElement popupKapatma = $("[class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all ui-state-hover']");
    SelenideElement evrakEtiket = $("[id$='etiketMetinID']");
    SelenideElement islemKapat = $(By.id("kapatButton"));
    ElementsCollection zimmetEvrakListele = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr");
    ElementsCollection hareketGecmisi = $$("[id='zimmetRaporHareketGecmisiForm:hareketGecmisiDataTableId_data']");
    ElementsCollection pencereListesi1 = $$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']");
    ElementsCollection pencereListesi2 = $$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']");
    SelenideElement lblSayfa = $("div[id='window1Dialog'] span[class='ui-dialog-title']");

    @Step("GelenEvrakZimmetRaporu sayfasını aç")
    public GelenEvrakZimmetRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.GelenEvrakZimmetRaporu);
        return this;
    }

    @Step("Orta alanda \"{sayfa}\" ekranı açılır\n")
    public GelenEvrakZimmetRaporuPage sayfaKontrol(String sayfa) {
        Assert.assertEquals(lblSayfa.getText().equals(sayfa),true,sayfa);
        Allure.addAttachment(sayfa,"açılmaktadır");
        takeScreenshot();
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Sorgula")
    public GelenEvrakZimmetRaporuPage sorgula() {
        sorgula.click();
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda kontrol. Evrak1: {konu}")
    public GelenEvrakZimmetRaporuPage rapordaEvraklarıListele(String konu) {
        boolean durum = zimmetEvrakListele.filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, true,"Gelen Evrak Zimmet Raporu Kontrolü");
        Allure.addAttachment("Gelen Evrak Zimmet Raporu :" + konu,"");
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda kontrol. Evrak1: {konu1}, Evrak2: {konu2}")
    public GelenEvrakZimmetRaporuPage rapordaEvraklarıListele(String konu1, String konu2) {
//        boolean durum = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr")
        boolean durum = zimmetEvrakListele.filterBy(text(konu1)).size() == 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Evrak Adedi Kontrolu: \"{evrakNo}\" ")
    public GelenEvrakZimmetRaporuPage evrakAdediKontrolu(String evrakNo) {
        int evrakSayisi = zimmetEvrakListele.filterBy(Condition.text(evrakNo)).size();
        boolean durum = zimmetEvrakListele.filterBy(Condition.text(evrakNo)).size() == 1;
        Assert.assertEquals(durum,true, "Evrak Adedi Kontrolü");
        Allure.addAttachment(evrakNo + " nolu evrak adedi:" + evrakSayisi,"");
        return this;
    }

    @Step("Raporda Kontroller : \"{konu}\" \"{kullanici}\" \"{tarih}\" ")
    public GelenEvrakZimmetRaporuPage rapordaKontrol(String konu,String kullanici,String tarih) {
        boolean durumKonu1 = zimmetEvrakListele
                .filterBy(text(konu))
                .filterBy(text(kullanici))
                .filterBy(text(tarih)).size() > 0;
        Assert.assertEquals(durumKonu1, true,"Raporda Kontroller" );
        Allure.addAttachment("Kullanıcı: " + kullanici + " Tarih: " + tarih,"");
        takeScreenshot();
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakGecmisiButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id$='evrakGecmisiId']").get(0).click();
        }
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Detay Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakDetayButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id$='evrakGosterButtonTab1']").get(0).click();
        }
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Etiket Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakEtiketButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id*='j_idt']").get(0).click();
        }
        return this;
    }


    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Kontrolü: Evrak: {konu}, Kullanıcı: {kullanıcı}, IslemSureci: {islemSureci}")
    public GelenEvrakZimmetRaporuPage evrakGecmisiKontrolu(String konu, String kullanıcı, String islemSureci) {
//        boolean durumKonu1 = $$("[id='zimmetRaporHareketGecmisiForm:hareketGecmisiDataTableId_data']")
        boolean durumKonu1 = hareketGecmisi
                .filterBy(text(kullanıcı))
                .filterBy(text(islemSureci)).size() == 0;
        Assert.assertEquals(durumKonu1, false);
        takeScreenshot();
        return this;
    }

    SelenideElement evrakNoKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:0:evrakNoPanelGrid"));
    SelenideElement konuKoduKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:1:konuKoduLov:LovSecilen"));
    SelenideElement konuKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:3:konuTextArea"));
    SelenideElement evrakTuruKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:4:evrakTuruCombo"));
    SelenideElement evrakTarihiKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:7:evrakTarihi_input"));
    SelenideElement evrakDiliKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:6:evrakDili"));
    SelenideElement evrakGizlilikDerecesiKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:8:guvenlikKodu"));
    SelenideElement evrakKurumKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:9:kisiKurum"));
    SelenideElement evrakGeldigiKurumKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:9:geldigiKurumLov:LovSecilen"));
    SelenideElement evrakIvedilikKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:12:ivedilik"));


    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Kontrolü: Evrak: {konu}")
    public GelenEvrakZimmetRaporuPage evrakDetayKontrolu(String evrakNo,String konuKodu, String konu,String evrakTuru,String evrakTarihi,String evrakDili,String gizlilikDerecesi,String kisiKurum,String geldigiKurum,String ivedilik) {
        Assert.assertEquals(evrakNoKontrolu.getText().contains(evrakNo),true,"Evrak No Kontrolü");
        Allure.addAttachment("Evrak No Kontrolü" , evrakNo);

        Assert.assertEquals(konuKoduKontrolu.getText().contains(konuKodu),true,"Evrak Konu Kodu Kontrolü");
        Allure.addAttachment("Evrak Konu Kodu Kontrolü" , konuKodu);

        Assert.assertEquals(konuKontrolu.getText().contains(konu),true,"Evrak Konu Kontrolü");
        Allure.addAttachment("Evrak Konu Kontrolü" , konu);

        Assert.assertEquals(evrakTuruKontrolu.getText().contains(evrakTuru),true,"Evrak Türü Kontrolü");
        Allure.addAttachment("Evrak Türü Kontrolü" , evrakTuru);

        Assert.assertEquals(evrakTarihiKontrolu.getValue().contains(evrakTarihi),true,"Evrak Tarihi Kontrolü");
        Allure.addAttachment("Evrak Tarihi Kontrolü" , evrakTarihi);

        Assert.assertEquals(evrakDiliKontrolu.getText().contains(evrakDili),true,"Evrak Dili Kontrolü");
        Allure.addAttachment("Evrak Dili Kontrolü" , evrakDili);

        Assert.assertEquals(evrakGizlilikDerecesiKontrolu.getText().contains(gizlilikDerecesi),true,"Evrak Gizlilik Derecesi Kontrolü");
        Allure.addAttachment("Evrak Gizlilik Derecesi Kontrolü" , gizlilikDerecesi);

        Assert.assertEquals(evrakKurumKontrolu.getText().contains(kisiKurum),true,"Evrak Kurum Kontrolü");
        Allure.addAttachment("Evrak Kurum Kontrolü" , kisiKurum);

        Assert.assertEquals(evrakGeldigiKurumKontrolu.getText().contains(geldigiKurum),true,"Evrak Geldigi Kurum Kontrolü");
        Allure.addAttachment("Evrak Geldigi Kurum Kontrolü" , geldigiKurum);

        Assert.assertEquals(evrakIvedilikKontrolu.getText().contains(ivedilik),true,"Evrak Ivedilik Kontrolü");
        Allure.addAttachment("Evrak Ivedilik Kontrolü" , ivedilik);


        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Etiket Kontrolü")
    public GelenEvrakZimmetRaporuPage evrakEtiketKontrolu() {
        boolean durum = evrakEtiket.isDisplayed();
        Assert.assertEquals(durum, true);
        Allure.addAttachment("Evrak Etiket", "Bulunmaktadır");
        takeScreenshot();
        return this;
    }


    @Step("Popup Kapatma")
    public GelenEvrakZimmetRaporuPage popupKapatma() {
        ElementsCollection tr = pencereListesi1.filterBy(text("Evrak Geçmişi"));
        tr.get(0).$("[href]").click();
        takeScreenshot();
        return this;
    }

    @Step("Evrak Kapatma")
    public GelenEvrakZimmetRaporuPage evrakKapatma() {
        ElementsCollection tr = pencereListesi2.filterBy(text("Evrak Detayı"));
        tr.get(0).$("[href]").click();
        islemKapat.click();
        takeScreenshot();
        return this;
    }

}
