package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KaydedilenGelenEvraklarPage extends MainPage {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kaydettiklerimCheckbox"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement tblKaydedilenGelenEvraklar =$(By.id("mainInboxForm:inboxDataTable"));

    public KaydedilenGelenEvraklarPage openPage() {
        ustMenu("Kaydedilen Gelen Evraklar");
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
    @Step("Tabloda evrak no kontrolü")
    public KaydedilenGelenEvraklarPage tabloKontrolu(String evrakNo)
    {
        int row = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role=row] div[class=searchText]").filterBy(Condition.text(evrakNo)).size();
        System.out.println(row);
        Assert.assertEquals(row,1);
        //log başarılı
        return  this;
    }

}
