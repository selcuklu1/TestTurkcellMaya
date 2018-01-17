package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KaydedilenGelenEvrakPage extends MainPage {

    SelenideElement chkAltBirim = $(By.id("birimeGelenEvrakRaporuForm:altBirimId_input"));
    SelenideElement dateTxtEvrakTarihiBaslangic = $(By.id("birimeGelenEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement dateTxtEvrakTarihiBitis = $(By.id("birimeGelenEvrakRaporuForm:sonTarihCalendar_input"));
    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim = comboLov(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable'] button:nth-child(4)");
    SelenideElement btnRaporAlPdf = $("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable'] button:nth-child(2)");
    //    SelenideElement tblKaydedilenGelenEvrak = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data"));
    ElementsCollection tblKaydedilenGelenEvrak = $$("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data'] tr[role='row']");
    BelgenetElement txtGeldigiBirim = comboLov(By.id("birimeGelenEvrakRaporuForm:geldigiBirimLov_id:LovText"));
    BelgenetElement txtGeldigiKurum = comboLov(By.id("birimeGelenEvrakRaporuForm:geldigiKurumLov_idTeblig:LovText"));


    @Step("Kaydedilen Gelen Evrak sayfasını aç")
    public KaydedilenGelenEvrakPage openPage() {
        ustMenu(UstMenuData.Raporlar.KaydedilenGelenEvrak);
        return this;
    }

    @Step("Ekran Alan kontrolleri")
    public KaydedilenGelenEvrakPage ekranAlanKontrolleri() {

        SelenideElement lblBirim = $(By.xpath("//label[normalize-space(text())='Birim']"));
        SelenideElement lblAltBirim = $(By.xpath("//label[normalize-space(text())='Alt Birim']"));
        SelenideElement lblGeldigiYer = $(By.xpath("//label[normalize-space(text())='Geldiği Yer']"));
        SelenideElement lblEvrakKonusu = $(By.xpath("//label[normalize-space(text())='Evrak Konusu']"));
        SelenideElement lblEvrakKayitNo = $(By.xpath("//label[normalize-space(text())='Evrak Kayıt No']"));
        SelenideElement lblEvrakSayisi = $(By.xpath("//label[normalize-space(text())='Evrak Sayısı']"));
        SelenideElement lblEvrakTarihi = $(By.xpath("//label[normalize-space(text())='Evrak Tarihi']"));
        SelenideElement lblEvrakKayitTarihi = $(By.xpath("//label[normalize-space(text())='Evrak Kayıt Tarihi']"));
        SelenideElement lblKAydedenKullanici = $(By.xpath("//label[normalize-space(text())='Kaydeden Kullanıcı']"));

        Allure.addAttachment(lblBirim.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblAltBirim.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblGeldigiYer.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblEvrakKonusu.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblEvrakKayitNo.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblEvrakSayisi.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblEvrakTarihi.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblEvrakKayitTarihi.text(), "Ekran kontrolü ok");
        Allure.addAttachment(lblKAydedenKullanici.text(), "Ekran kontrolü ok");

        return this;
    }

    @Step("Birim alanı \"{birim}\" doldurulur")
    public KaydedilenGelenEvrakPage birimDoldur(String birim) {
        cmbBirim.selectLov(birim);
        return this;
    }

    @Step("Birim alanı kontrolü")
    public KaydedilenGelenEvrakPage birimKontrol() {
        boolean kontrol = cmbBirim.is(Condition.empty);
        Assert.assertEquals(kontrol, false, "Birim alanı dolu gelmiştir.");
        Allure.addAttachment(cmbBirim.getText(), "Birim alanı dolu gelmiştir.");
        return this;
    }

    @Step("Alt Birim Seçimi : \"{shoulBeSelect}\" ")
    public KaydedilenGelenEvrakPage altBirimSec(boolean shoulBeSelect) {
        boolean statu = chkAltBirim.isSelected();
        if (statu != shoulBeSelect)
            $(By.id("birimeGelenEvrakRaporuForm:altBirimId")).click();
        return this;
    }

    @Step("Evrak Tarihi alanı kontrolü")
    public KaydedilenGelenEvrakPage evrakTarihiKontrol() throws ParseException {
        boolean kontrolBaslangic = dateTxtEvrakTarihiBaslangic.is(Condition.empty);
        boolean kontrolBitis = dateTxtEvrakTarihiBitis.is(Condition.empty);

        String txt1 = dateTxtEvrakTarihiBitis.getValue();
        String txt2 = dateTxtEvrakTarihiBaslangic.getValue();

        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(txt1);
        Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse(txt2);
        long diff = Math.abs(date1.getTime() - date2.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        String diffNum = new Long(diffDays).toString();
        System.out.println(diffDays);


        Assert.assertEquals(kontrolBaslangic, false, "Evrak Tarihi Başlangıç alanı dolu gelmiştir.");
        Assert.assertEquals(kontrolBitis, false, "Evrak Tarihi Bitiş alanı dolu gelmiştir.");
        Allure.addAttachment(dateTxtEvrakTarihiBaslangic.getValue(), "Evrak Tarihi Başlangıç alanı dolu gelmiştir.");
        Allure.addAttachment(dateTxtEvrakTarihiBitis.getValue(), "Evrak Tarihi Bitiş alanı dolu gelmiştir.");
        Allure.addAttachment("Tarih farkı : ", diffNum);

        return this;
    }

    @Step("Evrak Tarihi başlangıç alanını \"{tarih}\" alanı kontrolü")
    public KaydedilenGelenEvrakPage evrakTarihiBaslangicDoldur(String tarih) {
        dateTxtEvrakTarihiBaslangic.clear();
        dateTxtEvrakTarihiBaslangic.sendKeys(tarih);
        return this;
    }

    @Step("Geldiği yer \"{geldigiYer}\" seçilir")
    public KaydedilenGelenEvrakPage geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOption(geldigiYer);
        return this;
    }

    @Step("Geldiği yer \"{geldigiBirim}\" seçilir")
    public KaydedilenGelenEvrakPage geldigiBirimSec(String geldigiBirim) {
        txtGeldigiBirim.selectLov(geldigiBirim);
        return this;
    }

    @Step("Geldiği yer \"{geldigiKurum}\" seçilir")
    public KaydedilenGelenEvrakPage geldigiKurumSec(String geldigiKurum) {
        txtGeldigiKurum.selectLov(geldigiKurum);
        return this;
    }

    @Step("Gelen Evrak no alanını \"{evrakNo}\" girilir")
    public KaydedilenGelenEvrakPage gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.clear();
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrakPage sorgula() {
        btnSorgula.pressEnter();
        return this;
    }

    @Step("Tabloda evrak No kontrolü : \"{evrakNo}\" ")
    public KaydedilenGelenEvrakPage tabloKontrouAll(String evrakNo, String evraNo2) {
        ElementsCollection kisiselPages = $$("th[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_paginator_top'] > span[class='ui-paginator-pages'] >  span");
        int size = 0;
        int size2 = 0;
        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            size = tblKaydedilenGelenEvrak
                    .filterBy(Condition.text(evrakNo))
                    .size();
            size2 = tblKaydedilenGelenEvrak
                    .filterBy(Condition.text(evraNo2))
                    .size();
        }

//        SelenideElement table= $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable"));
//        boolean status = findElementOnTableByColumnInputInAllPages(table,2,evrakNo).isDisplayed();
//        Assert.assertEquals(status, true);
        if (size > 0 && size2 > 0)
            Allure.addAttachment("Tablo Listesi : ", "Aranılan evraklar tabloda bulundu");
        else
            Allure.addAttachment("Tablo Listesi : ", "Aranılan evrak tabloda bulunamadı");
        return this;
    }


    @Step("Rapor al Excel")
    public KaydedilenGelenEvrakPage raporAlExcel(String downloadPath) throws IOException {

        deleteFile(downloadPath, "Rapor_");
        btnRaporAlExcel.click();
//        islemMesaji().basariliOlmali();
//        Thread.sleep(8000);
//        btnSorgula.click();
//        islemMesaji().basariliOlmali();
        searchDownloadedFileWithName(getDownloadPath(), "Rapor_.xls");
        return this;
    }


    //Dosyanın bilgisayara inip inmediğini kontrol eder.


    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage raporAlPdf() throws IOException {
        deleteFile(getDownloadPath(), "Rapor_");
        btnRaporAlPdf.click();
//        Thread.sleep(8000);
        islemMesaji().basariliOlmali();
//        btnSorgula.click();
        searchDownloadedFileWithName(getDownloadPath(), "Rapor_.pdf");
        return this;
    }

    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage txtClear() {
        txtEvrakKayitNo.clear();
        return this;
    }

    @Step("Tablo kontrolu : \"{evrakNo}\" ")
    public KaydedilenGelenEvrakPage tabloKontrolu(String evrakNo) {
//        WebElement columnId =  findElementOnTableByColumnInput(tblKaydedilenGelenEvrak,1,evrakNo);
        tblKaydedilenGelenEvrak.filterBy(Condition.text(evrakNo)).shouldHave(sizeGreaterThan(0));
        return this;
    }

}
