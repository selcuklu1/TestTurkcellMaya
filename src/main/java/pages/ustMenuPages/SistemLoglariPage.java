package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class SistemLoglariPage extends MainPage {

    SelenideElement txtBaslangicTarihi = $(By.id("sistemLogRaporForm:ilkTarihCalendar_input"));
    SelenideElement txtBitisTarihi = $(By.id("sistemLogRaporForm:sonTarihCalendar_input"));
    BelgenetElement txtKullanici = comboLov(By.id("sistemLogRaporForm:kullanciLovId:LovText"));
    BelgenetElement txtBirim = comboLov(By.id("sistemLogRaporForm:birimLovId:LovText"));
    BelgenetElement txtAksiyon = comboLov(By.id("sistemLogRaporForm:aksiyonLovId:LovText"));
    SelenideElement btnSorgula = $(By.id("sistemLogRaporForm:sorgulaButton"));
    ElementsCollection tableSistemLoglari = $$("tbody[id='sistemLogRaporForm:sistemLogRaporDataTable_data'] > tr[role='row']");
    ElementsCollection sistemRaporuTablePages = $$("td[id='sistemLogRaporForm:sistemLogRaporDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");
    @Step("Sistem logları sayfasını aç")
    public SistemLoglariPage openPage() {
        ustMenu(UstMenuData.Raporlar.SistemLoglari);
        return this;
    }


    @Step("Başlangıç Tarihi doldur: {baslangicTarihi}")
    public SistemLoglariPage baslangicTarihiDoldur(String baslangicTarihi) {
        txtBaslangicTarihi.setValue(baslangicTarihi);
        return this;
    }

    @Step("Bitiş Tarihi doldur: {bitisTarihi}")
    public SistemLoglariPage bitisTarihiDoldur(String bitisTarihi) {
        txtBitisTarihi.setValue(bitisTarihi);
        return this;
    }

    @Step("Kullanıcı Seç: {kullanici}")
    public SistemLoglariPage kullaniciSec(String kullanici) {
        txtKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Birim Seç: {birim}")
    public SistemLoglariPage birimSec(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }

    @Step("Aksiyon Seç: {aksiyon}")
    public SistemLoglariPage aksiyonSec(String aksiyon) {
        txtAksiyon.selectLov(aksiyon);
        return this;
    }

    @Step("Sorgula butonuna tıkla.")
    public SistemLoglariPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Sistem Raporu Tablosunda kontrol. Aksiyon: {aksiyon}, Kullanıcı: {kullanici}, Açıklama: {aciklama}, Log olmalı: {shouldBeExist}")
    public SistemLoglariPage sistemRaporuKontrol(String aksiyon, String tarih, String kullanici, String aciklama, boolean shouldBeExist) {
//        ElementsCollection sistemRaporuTablePages = $$("td[id='sistemLogRaporForm:sistemLogRaporDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        boolean elementFound = false;

        for (int i = 0; i < sistemRaporuTablePages.size(); i++) {
            sistemRaporuTablePages.get(i).click();

            SelenideElement sistemRaporu = tableSistemLoglari
                    .filterBy(text(aksiyon))
                    .filterBy(text(tarih))
                    .filterBy(text(kullanici))
                    .filterBy(text(aciklama))
                    .first();

            if (sistemRaporu.isDisplayed() && sistemRaporu.exists()) {
                elementFound = true;
                break;
            }

        }

        Assert.assertEquals(elementFound, shouldBeExist);

        return this;
    }

    @Step("Sistem Raporu Tablosunda kontrol. Aksiyon: {aksiyon}, Kullanıcı: {kullanici}")
    public SistemLoglariPage sistemRaporuGeldigiGorme(String aksiyon, String tarih, String kullanici) {
        boolean durum = $$("[id='sistemLogRaporForm:sistemLogRaporDataTable'] tbody tr")
                .filterBy(text(aksiyon))
                .filterBy(text(tarih))
                .filterBy(text(kullanici)).size() == 0;
        Assert.assertEquals(durum, false);
        takeScreenshot();
        return this;
    }


}

