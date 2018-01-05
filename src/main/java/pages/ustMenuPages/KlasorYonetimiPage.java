package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KlasorYonetimiPage extends MainPage {

    SelenideElement btnYeni = $(By.id("klasorYonetimiListingForm:klasorTreeTable:addNewKlasorButton"));
    BelgenetElement txtBirim = comboLov("[id^='klasorYonetimiEditorForm'][id$='LovText']");
    SelenideElement cmbKlasorTuru = $(By.id("klasorYonetimiEditorForm:klasorTuruListe"));
    SelenideElement txtKlasorKodu = $(By.id("klasorYonetimiEditorForm:klasorKoduInput"));
    SelenideElement txtKlasorAdi = $(By.id("klasorYonetimiEditorForm:klasorAdiInput"));
    SelenideElement dateKlasorAcilisTarihi = $(By.id("klasorYonetimiEditorForm:klasorAcilisTarihiCalendar_input"));
    SelenideElement dateKlasorKapanisTarihi = $(By.id("klasorYonetimiEditorForm:klasorKapanisTarihiCalendar_input"));
    SelenideElement btnKullaniciYetkiListesiYeni = $(By.id("klasorYonetimiEditorForm:klasorYonetimiYetkiliKullanicilarDataTable:addNewKYKlasorYetkiButton"));
    SelenideElement txtKullaniciYetkiEklemeAd = $(By.id("klasorYonetimiKullaniciYetkilendirmeForm:filterPanel:adFilterInput"));
    SelenideElement btnKullaniciYetkiEklemeAra = $(By.id("klasorYonetimiKullaniciYetkilendirmeForm:filterPanel:searchEntitiesButton"));
    SelenideElement btnKullaniciYetkiEklemeKaydet = $(By.id("klasorYonetimiKullaniciYetkilendirmeForm:saveKlasorYetkiButton"));
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='klasorYonetimiKullaniciYetkilendirmeForm:kullaniciDataTable_data'] > tr[role='row']");// span[class='ui-chkbox-icon']");
    SelenideElement btnKlasorEklemeKaydet = $(By.id("klasorYonetimiEditorForm:saveKlasorButton"));

    @Step("Klasör Yönetimi sayfası aç")
    public KlasorYonetimiPage openPage(){
        ustMenu(UstMenuData.KlasorIslemleri.KlasorYonetimi);
        return this;
    }


    @Step("Kaydet")
    public KlasorYonetimiPage klasorEklemeKaydet() {
        btnKlasorEklemeKaydet.click();
        return this;
    }

    @Step("Yetki tanımlanabilicek kaydet ")
    public KlasorYonetimiPage yetkiTanimlanabilicekSecKaydet() {
        btnKullaniciYetkiEklemeKaydet.click();
        return this;
    }

    @Step("Yetki tanımlanabilicek seç")
    public KlasorYonetimiPage yetkiTanimlanabilicekSec(String ad) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(ad)).first()
                .$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Kullanici Yetki Ekleme Ara")
    public KlasorYonetimiPage kullaniciYetkiEklemeAra() {
        btnKullaniciYetkiEklemeAra.click();
        return this;
    }

    @Step("Kullanıcı yetki ekleme ad doldur")
    public KlasorYonetimiPage ktxtKullaniciYetkiEklemeAdDoldur(String ad) {
        txtKullaniciYetkiEklemeAd.setValue(ad);
        return this;
    }

    @Step("Kullanıcı yetki listesi yetki ekle")
    public KlasorYonetimiPage kullaniciYetkiListesiYetkiEkle() {
        btnKullaniciYetkiListesiYeni.click();
        return this;
    }

    @Step("")
    public KlasorYonetimiPage stepmethod() {

        return this;
    }

    @Step("Klasör kapanış tarihi doldur")
    public KlasorYonetimiPage klasorKapanisTarihiDoldur(String tarih) {
        dateKlasorKapanisTarihi.setValue(tarih);
        return this;
    }

    @Step("Klasör açılış tarihi doldur")
    public KlasorYonetimiPage klasorAcilisTarihDoldur(String tarih) {
        dateKlasorAcilisTarihi.setValue(tarih);
        return this;
    }

    @Step("Klasör kodu doldur")
    public KlasorYonetimiPage klasorKoduDoldur(String klasorKodu) {
        txtKlasorKodu.setValue(klasorKodu);
        return this;
    }

    @Step("Klaör adı doldur")
    public KlasorYonetimiPage klasorAdiDoldur(String klasorAdi) {
        txtKlasorAdi.setValue(klasorAdi);
        return this;
    }

    @Step("Klasör türü seç")
    public KlasorYonetimiPage klasorTuruSec(String value) {
        cmbKlasorTuru.selectOption(value);
        return this;
    }

    @Step("Yeni")
    public KlasorYonetimiPage yeni() {
        btnYeni.click();
        return this;
    }

    @Step("Birim alanını doldur")
    public KlasorYonetimiPage birimDoldur(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }

}
