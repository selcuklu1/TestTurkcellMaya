package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KararYazisiOlusturPage extends MainPage {

    //Kaydet butonlar
    SelenideElement btnKaydetveOnaySun = $(By.id("yeniKararEvrakForm:kararEvrakRightTab:uiRepeat:2:cmdbutton"));

    //Bilgiler tab

    BelgenetElement txtKonuKodu = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:0:konuKoduLov:LovText"));
    SelenideElement txtKonu = $(By.id("yeniKararEvrakForm:evrakBilgileriList:2:konuTextArea"));
    SelenideElement cmbIvedilik = $(By.id("yeniKararEvrakForm:evrakBilgileriList:4:ivedilik"));
    SelenideElement dateMiat = $(By.id("yeniKararEvrakForm:evrakBilgileriList:5:miatCalendar_input"));
    BelgenetElement txtOnayAkisi = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisLov:LovText"));
    BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:7:eklenecekKlasorlerLov:LovText"));
    SelenideElement txtToplantiNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:8:toplantiNo"));
    SelenideElement dateToplantiTarihi = $(By.id("yeniKararEvrakForm:evrakBilgileriList:9:toplantiTarih_input"));
    SelenideElement txtKararNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:10:kararNo"));
    SelenideElement btnOnayAkisiEkle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:onayAkisiEkle"));
    SelenideElement btnKullanicilarKullan = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:anlikAkisKullanButton"));

    //Editor Tab


    @Step("Karar yazısı oluştur sayfası aç")
    public KararYazisiOlusturPage openPage(){
        ustMenu("Karar Yazısı Oluştur");
        return this;
    }

    @Step("Kaydet ve onay sun")
    public KararYazisiOlusturPage kaydetveOnaySun(){
        btnKaydetveOnaySun.click();
        return this;
    }

    @Step("Kullan")
    public KararYazisiOlusturPage kullan(){
        btnKullanicilarKullan.click();
        return this;
    }

    @Step("Karar no doldur")
    public KararYazisiOlusturPage kararNoDoldur(String kararNo){
        txtKararNo.setValue(kararNo);
        return this;
    }

    @Step("Toplantı tarih seç")
    public KararYazisiOlusturPage toplantiTarihDoldur(String tarih){
        dateToplantiTarihi.setValue(tarih);
        return this;
    }

    @Step("Toplanti no doldur")
    public KararYazisiOlusturPage toplantiNoDoldur(String toplantiNo){
        txtToplantiNo.setValue(toplantiNo);
        return this;
    }

    @Step("Kaldirilacak klasörler doldur")
    public KararYazisiOlusturPage kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler){
        txtKaldirilacakKlasorler.selectLov(kaldirilacakKlasorler);
        return this;
    }

    @Step("Onay akışı doldur")
    public KararYazisiOlusturPage onayAkisiDoldur(String onayAkisi){
        txtOnayAkisi.selectLov(onayAkisi);
        return this;
    }

    @Step("Miat Doldur")
    public KararYazisiOlusturPage miatDoldur(String miat){
        dateMiat.setValue(miat);
        return this;
    }

    @Step("İvedilik seç")
    public KararYazisiOlusturPage ivedilikSec(String secilen){
        cmbIvedilik.selectOption(secilen);
        return this;
    }
    @Step("Konu kodu doldur")
    public KararYazisiOlusturPage konuKoduDoldur(String konuKodu){
        txtKonuKodu.selectLov(konuKodu);
        return this;
    }
    @Step("Konu doldur")
    public KararYazisiOlusturPage konuDoldur(String konuKodu){
        txtKonu.setValue(konuKodu);
        return this;
    }

    @Step("Onay akışı ekle")
    public KararYazisiOlusturPage onayAkisiEkle(){
        btnOnayAkisiEkle.click();
        return this;
    }



}
