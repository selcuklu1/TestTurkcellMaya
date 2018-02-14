package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class HavaleEdilenEvrakRaporuPage extends MainPage {

    BelgenetElement txtHavaleEdilenBirim = comboLov(By.id("havaleEvrakRaporuForm:havaleEdilenbirimLovId:LovText"));
    BelgenetElement txtHavaleEdilenKullanici = comboLov(By.id("havaleEvrakRaporuForm:havaleEdilenKullaniciLovId:LovText"));
    SelenideElement txtHevaleTarihAraligiBaslangic = $(By.id("havaleEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement txtHevaleTarihAraligiBitis = $(By.id("havaleEvrakRaporuForm:sonTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("havaleEvrakRaporuForm:sorgulaButton"));

    @Step("Havale Edilen Evrak Raporu sayfasını aç")
    public HavaleEdilenEvrakRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.HavaleEdilenEvrakRaporu);
        return this;
    }

    @Step("Havale Edilen birim doldur: {birim}")
    public HavaleEdilenEvrakRaporuPage havaleEdilenBirimDoldur(String birim){
        txtHavaleEdilenBirim.selectLov(birim);
        return this;
    }

    @Step("Havale Edilen birim doldur: {birim}")
    public HavaleEdilenEvrakRaporuPage havaleEdilenKullaniciDoldur(String kullanici){
        txtHavaleEdilenKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Havale Tarih Aralığı başlanıgıcı doldur: {baslangicTarih}")
    public HavaleEdilenEvrakRaporuPage havaleTarihAraligiBaslangicDoldur(String baslangicTarih){
        txtHevaleTarihAraligiBaslangic.setValue(baslangicTarih);
        return this;
    }

    @Step("Havale Tarih Aralığı bitişi doldur: {bitisTarih}")
    public HavaleEdilenEvrakRaporuPage havaleTarihAraligiBitisDoldur(String bitisTarih){
        txtHevaleTarihAraligiBitis.setValue(bitisTarih);
        return this;
    }

    @Step("Sorgula tıklanır")
    public HavaleEdilenEvrakRaporuPage sorgula(){
        btnSorgula.click();
        return this;
    }

    @Step("Evrak detay seçilir: {evrakNo}")
    public HavaleEdilenEvrakRaporuPage evrakDetaySec(String evrakNo){
        $$("[id='havaleEvrakRaporuForm:havaleEvrakRaporOutputTab_data'] > tr").filterBy(Condition.text(evrakNo))
                .get(0).$("button").click();
        return this;
    }

    @Step("Evrak detayı geldiği görünür")
    public HavaleEdilenEvrakRaporuPage evrakDetayGeldigGorme(){
        boolean durum = $$(By.id("windowReadOnlyEvrakDialog")).size()>0;
        Assert.assertEquals(durum,true);
        return this;
    }

}

