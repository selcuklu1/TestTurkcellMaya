package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-02-06
 * Proje: Türksat Functional Test Automation
 * Class: "İptal edilen evraklar raporu" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class IptalEdilenEvraklarRaporuPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'İptal Edilen Evraklar Raporu' and @class = 'ui-dialog-title']"));
    SelenideElement dateIlkTarih = $(By.id("iptalEdilenEvraklarRaporuForm:ilkTarihCalendar_input"));
    SelenideElement dateSonTarih = $(By.id("iptalEdilenEvraklarRaporuForm:sonTarihCalendar_input"));
    SelenideElement cmbBelgeDurumu = $(By.id("iptalEdilenEvraklarRaporuForm:belgeTipiId_input"));
    SelenideElement btnSorgula = $(By.id("iptalEdilenEvraklarRaporuForm:sorgulaButton"));
    ElementsCollection tblIptalEdilenEvrakRaporu = $$("[id='iptalEdilenEvraklarRaporuForm:iptalEdilenEvraklarDataTable_data'] > tr[role='row']");

    @Step("İptal Edilen Evraklar Raporu sayfası aç")
    public IptalEdilenEvraklarRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.IptalEdilenEvraklarRaporu);
        return this;
    }

    @Step("Sayfa açıldı mı kontrolü")
    public IptalEdilenEvraklarRaporuPage sayfaAcilmali() {
        pageTitle.shouldBe(visible);
        return this;
    }

    @Step("Silme tarihini doldur: Başlangıç")
    public IptalEdilenEvraklarRaporuPage ilkTarihDoldur(String ilkTarih) {
        dateIlkTarih.setValue(ilkTarih);
        return this;
    }

    @Step("Silme tarihini doldur: Bitiş")
    public IptalEdilenEvraklarRaporuPage sonTarihDoldur(String sonTarih) {
        dateSonTarih.setValue(sonTarih);
        return this;
    }

    @Step("Belge durumu seç: {secim}")
    public IptalEdilenEvraklarRaporuPage belgeDurumuSec(String secim) {
        cmbBelgeDurumu.selectOption(secim);
        return this;
    }

    @Step("Sorgula")
    public IptalEdilenEvraklarRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }


    @Step("İptal Edilen Evrak Raporu listesinde evrak kontrolu")
    public IptalEdilenEvraklarRaporuPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblIptalEdilenEvrakRaporu
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }


    @Step("İptal Edilen Evrak Raporu listesinde evrak detayı göster butonuna tıkla")
    public IptalEdilenEvraklarRaporuPage konuyaGoreEvrakDetayiTikla(String konu) {

        tblIptalEdilenEvrakRaporu
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakGosterButton']").click();

        return this;
    }


}
