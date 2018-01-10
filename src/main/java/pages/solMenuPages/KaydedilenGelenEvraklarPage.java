package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KaydedilenGelenEvraklarPage extends MainPage {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kaydettiklerimCheckbox"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tblKaydedilenGelenEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    SelenideElement tblIlkRapor = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    ElementsCollection tblKaydedilenGelenEvraklar2 = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role='row']");


//    Evrak Detayları Sayfası

    SelenideElement txtEvrakBilgileriListKonuKodu = $("[id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");

    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    BelgenetElement cmbEvrakBilgileriListGeldigiKisi = comboLov("[id$='geldigiGercekKisiLov:LovText']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $("[id$=miatCalendar_input");
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("inboxItemInfoForm:evrakBilgileriList:14:j_idt12226"));


    @Step("Kaydedilen gelen evraklar sayfası aç")
    public KaydedilenGelenEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
        return this;
    }

    @Step("Filtrele alanını aç")
    public KaydedilenGelenEvraklarPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public KaydedilenGelenEvraklarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public KaydedilenGelenEvraklarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public KaydedilenGelenEvraklarPage tarihDoldur(String tarih) {
        dateTxtTarih.sendKeys(tarih);
        dateTxtTarih.pressEnter();
        return this;
    }

    @Step("Tablodan rapor seç")
    public KaydedilenGelenEvraklarPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tabloda evrak no kontrolu")
    public KaydedilenGelenEvraklarPage tabloKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar2.filterBy(text(evrakNo)).shouldHaveSize(1);
//        System.out.println(row);
//        Assert.assertEquals(row, 1);
        return this;
    }

    @Step("Tabloda ilk evrak içerik tıklama")
    public KaydedilenGelenEvraklarPage tabloRaporIcerik(String evrakNo) {
        tblIlkRapor.click();
        return this;
    }

    @Step("Guncelleme Kontrolleri")
    public KaydedilenGelenEvraklarPage guncellenenAlanKontrolleri(String evrakTarihi, String evrakTuru, String gizlilikDerecesi) {
        String txtEvrakTarihi = dateTxtEvrakBilgileriListEvrakTarihi.getValue();
        String txtEvrakTuru = cmbEvrakBilgileriListEvrakTuru.getSelectedText();
        String txtGizlilikDerecesi = cmbEvrakBilgileriListGizlilikDerecesi.getSelectedText();

        Assert.assertEquals(txtEvrakTarihi, evrakTarihi);
        Assert.assertEquals(txtEvrakTuru, evrakTuru);
        Assert.assertEquals(txtGizlilikDerecesi, gizlilikDerecesi);

        return this;
    }

    @Step("Tabloda evrak noya göre İçerik tıklama : \"{evrakNo}\" ")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileIcerikSec(String evrakNo) throws InterruptedException {

        Thread.sleep(2000);
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }

    @Step("Tabloda konuya göre evrak kontrolu : {evrakNo}")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileEvrakKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        return this;
    }


    @Step("Tabloda konuya göre evrak kontrolu : {konu}")
    public KaydedilenGelenEvraklarPage tabloKonuyaGoreEvrakKontrolu(String konu) {
        tblKaydedilenGelenEvraklar
                .filterBy(text(konu))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Tabloda konuya göre evrak İcerik tıklama : {konu}")
    public KaydedilenGelenEvraklarPage tabloKonuyaGoreIcerikSec(String konu) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }
}
