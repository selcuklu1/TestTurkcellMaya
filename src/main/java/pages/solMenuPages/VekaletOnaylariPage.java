package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.xalan.templates.ElemApplyImport;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

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
    SelenideElement dateTxtBaslangicTarihi = $("form[id='mainPreviewForm'] tbody tr:nth-child(5) td:nth-child(3)");
    SelenideElement dateTxtBitisTarihi = $("form[id='mainPreviewForm'] tbody tr:nth-child(6) td:nth-child(3)");
    SelenideElement txtEkleyeceginizNotlar = $("form[id='mainPreviewForm'] tbody tr:nth-child(8) td:nth-child(3)");


}
