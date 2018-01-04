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

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class VekaletOnaylariPage extends MainPage {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    ElementsCollection tblOnayBekleyenler = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role=row]");
    SelenideElement lblVekaletVeren = $("form[id='mainPreviewForm'] tbody tr:nth-child(2) td:nth-child(3)");
    BelgenetElement txtVekaletAlan = comboLov(By.id("mainPreviewForm:previewVekaletAlanLov:LovSecilen"));
    SelenideElement lblAciklama = $("form[id='mainPreviewForm'] tbody tr:nth-child(7) td:nth-child(3)");
    SelenideElement dateTxtBaslangicTarihi = $("form[id='mainPreviewForm'] tbody tr:nth-child(5) td:nth-child(3) input");
    SelenideElement dateTxtBitisTarihi = $("form[id='mainPreviewForm'] tbody tr:nth-child(6) td:nth-child(3) input");
    SelenideElement txtEkleyeceginizNotlar = $("form[id='mainPreviewForm'] tbody tr:nth-child(8) td:nth-child(3) textarea");
    SelenideElement btnReddet = $(By.xpath("//span[normalize-space(text())= 'Reddet']"));
    SelenideElement btnOnay = $(By.xpath("//span[normalize-space(text())= 'Onayla']"));
    SelenideElement txtOnayEvraki = $(By.id("mainPreviewForm:onayEvrakBilgileri"));
    SelenideElement btnDetay = $(By.id("mainPreviewForm:detayButton"));

    SelenideElement tblEvrakNoPanel = $("[id^='windowReadOnlyForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] tbody td:nth-child(3) div");
    SelenideElement btnEkranKapat = $(By.xpath("//div[@id='windowReadOnlyEvrakDialog']//span[@class='ui-icon ui-icon-closethick']"));

    @Step("Kaydedilen gelen evraklar sayfası aç")
    public VekaletOnaylariPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.VekaletOnaylari);
        return this;
    }

    @Step("Filtrele alanını aç")
    public VekaletOnaylariPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Tarihi doldur")
    public VekaletOnaylariPage tarihiDoldur(String date) {
        dateTxtTarih.setValue(date);
        return this;
    }

    @Step("Onaylanacak Kayıt Seç")
    public VekaletOnaylariPage tablodanOnaylanacakKayıtSec(String text) {
        tblOnayBekleyenler
                .filterBy(Condition.text(text)).first()
                .click();
        return this;
    }

    @Step("")
    public VekaletOnaylariPage alanKontrolleri(String vekaletVeren, String vekaletAlan, String vekaletTarihi) {
        boolean vvSonuc = lblVekaletVeren.text().contains(vekaletVeren);
        boolean vaSonuc = txtVekaletAlan.text().contains(vekaletAlan);
        boolean dbaSonuc = dateTxtBaslangicTarihi.getValue().equals(vekaletTarihi);
        boolean dbiSonuc = dateTxtBitisTarihi.getValue().equals(vekaletTarihi);

        Assert.assertEquals(vvSonuc, true);
        Assert.assertEquals(vaSonuc, true);
        Assert.assertEquals(dbaSonuc, true);
        Assert.assertEquals(dbiSonuc, true);
        return this;
    }

    @Step("Ekleyeceiğiniz notlar doldur")
    public VekaletOnaylariPage ekleyeceginizNotlarDoldur(String not) {
        txtEkleyeceginizNotlar.sendKeys(not);
        return this;
    }

    @Step("Onay ret")
    public VekaletOnaylariPage reddet() {
        btnReddet.click();
        return this;
    }

    @Step("Onay evrakı alan kontrolü")
    public VekaletOnaylariPage onayEvrakiKontrol() {
        txtOnayEvraki.shouldBe(Condition.not(Condition.empty));
        return this;
    }

    @Step("Detay butonu tıkla")
    public VekaletOnaylariPage detay() {
        btnDetay.click();
        return this;
    }

    @Step("Evrak detay kontrolü")
    public VekaletOnaylariPage evrakKontol(String evrakNo) {
        tblEvrakNoPanel.text().equals(evrakNo);
        btnEkranKapat.click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    @Step("Onay onay")
    public VekaletOnaylariPage onay() {
        btnOnay.click();
        return this;
    }
}
