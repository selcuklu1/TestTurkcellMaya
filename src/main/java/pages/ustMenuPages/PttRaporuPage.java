package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class PttRaporuPage extends MainPage {

    SelenideElement divAramaDetaylar = $x("//h3[. = 'Arama Detayları']");
    SelenideElement txtPostaTarihi = $("span[id$='postaTarihi'] > input");
    BelgenetElement txtUlke = comboLov("input[id$=':lovUlkePttRapor:LovText']");
    BelgenetElement txtIl = comboLov("input[id$=':lovIlPttRapor:LovText']");
    public ElementsCollection tableRaporlar = $$("tbody[id='pttRaporuForm:havaleEvrakRaporOutputTab_data'] > tr[role='row']");
    SelenideElement btnSorgula = $("button[id$=':sorgulaButton']");
    SelenideElement cmbPostaTipi = $("select[id$=':postaTipiMenuPttRapor']");
    SelenideElement txtDagitici = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Dağıtıcı']/ancestor::tr//input");
    SelenideElement txtDuzenleyen = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Düzenleyen']/ancestor::tr//input");
    SelenideElement txtAvansSorumlusu = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Avans Sorumlusu']/ancestor::tr//input");
    SelenideElement txtKontrolEden = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Kontrol Eden']/ancestor::tr//input");
    SelenideElement txtPttMerkez = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Ptt Merkez']/ancestor::tr//input");
    ElementsCollection tableColumns = $$("div[id='pttRaporuForm:havaleEvrakRaporOutputTab'] > table > thead > tr[role='row'] > th");
    SelenideElement btnTumSonuclariRaporla = $x("//div[@id='pttRaporuForm:havaleEvrakRaporOutputTab']//button[1]");

    public static class PttRaporExcellTest{

        public String dagitici;
        public String duzenleyen;
        public String avansSorumlusu;
        public String kontrolEden;
        public String pttMerkez;

        public String[] gidenKurumlar;
        public String[] ulkeler;
        public String[] sehirler;
        public String[] postaAdlari;
        public String[] agirliklar;
        public String[] pulNolar;
        public String[] ucretTLler;

        public PttRaporExcellTest(String excelFilepath) throws IOException {

            //String excelFileName = "/Users/huseyintumer/Downloads/Rapor_1516259983559.xls";

            FileInputStream fis = new FileInputStream(excelFilepath);
            Workbook wb = new HSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            int temp = -1;
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell.getRichStringCellValue().getString().trim().contains("Yukarda dökümü yapılan")) {
                    temp = row.getRowNum();
                }
            }

            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            gidenKurumlar = new String[temp - 2];
            ulkeler = new String[temp - 2];
            sehirler = new String[temp - 2];
            postaAdlari = new String[temp - 2];
            agirliklar = new String[temp - 2];
            pulNolar = new String[temp - 2];
            ucretTLler = new String[temp - 2];

            String gidenKurum, ulke, sehir, postaAdi, agirlik, pulNo, ucretTL;

            for(int i = 2; i < temp; i++){

                CellReference cellB = new CellReference("B" + i);
                CellReference cellC = new CellReference("C" + i);
                CellReference cellD = new CellReference("D" + i);
                CellReference cellE = new CellReference("E" + i);
                CellReference cellF = new CellReference("F" + i);
                CellReference cellG = new CellReference("G" + i);
                CellReference cellH = new CellReference("H" + i);

                gidenKurum = evaluator.evaluate(sheet.getRow(cellB.getRow()).getCell(cellB.getCol())).getStringValue();
                ulke = evaluator.evaluate(sheet.getRow(cellC.getRow()).getCell(cellC.getCol())).getStringValue();
                sehir = evaluator.evaluate(sheet.getRow(cellD.getRow()).getCell(cellD.getCol())).getStringValue();
                postaAdi = evaluator.evaluate(sheet.getRow(cellE.getRow()).getCell(cellE.getCol())).getStringValue();
                agirlik = evaluator.evaluate(sheet.getRow(cellF.getRow()).getCell(cellF.getCol())).getStringValue();
                pulNo = evaluator.evaluate(sheet.getRow(cellG.getRow()).getCell(cellG.getCol())).getStringValue();
                ucretTL = evaluator.evaluate(sheet.getRow(cellH.getRow()).getCell(cellH.getCol())).getStringValue();

                gidenKurumlar[i - 2] = gidenKurum;
                ulkeler[i - 2] = ulke;
                sehirler[i - 2] = sehir;
                postaAdlari[i - 2] = postaAdi;
                agirliklar[i - 2] = agirlik;
                pulNolar[i - 2] = pulNo;
                ucretTLler[i - 2] = ucretTL;

                System.out.println("----------------------------\n");
                System.out.println("Giden Kurum : " + gidenKurum);
                System.out.println("Ülke : " + ulke);
                System.out.println("Şehir : " + sehir);
                System.out.println("Posta Adı: " + postaAdi);
                System.out.println("Ağırlık : " + agirlik);
                System.out.println("Pul No : " + pulNo);
                System.out.println("Ücret TL : " + ucretTL);
                System.out.println("----------------------------\n");

            }

            temp = -1;
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell.getRichStringCellValue().getString().trim().contains("Dağıtıcı")) {
                    temp = row.getRowNum();
                }
            }
            int rowNumber = temp + 2;

            CellReference cellA = new CellReference("A" + rowNumber);
            CellReference cellB = new CellReference("B" + rowNumber);
            CellReference cellC = new CellReference("C" + rowNumber);
            CellReference cellD = new CellReference("D" + rowNumber);
            CellReference cellE = new CellReference("E" + rowNumber);

            dagitici = evaluator.evaluate(sheet.getRow(cellA.getRow()).getCell(cellA.getCol())).getStringValue();
            duzenleyen = evaluator.evaluate(sheet.getRow(cellB.getRow()).getCell(cellB.getCol())).getStringValue();
            avansSorumlusu = evaluator.evaluate(sheet.getRow(cellC.getRow()).getCell(cellC.getCol())).getStringValue();
            kontrolEden = evaluator.evaluate(sheet.getRow(cellD.getRow()).getCell(cellD.getCol())).getStringValue();
            pttMerkez = evaluator.evaluate(sheet.getRow(cellE.getRow()).getCell(cellE.getCol())).getStringValue();

            System.out.println("----------------------------\n");
            System.out.println("Dağıtıcı: " + dagitici);
            System.out.println("Düzenleyen: " + duzenleyen);
            System.out.println("Avans Sorumlusu: " + avansSorumlusu);
            System.out.println("Kontrol Eden: " + kontrolEden);
            System.out.println("PTT Merkez: " + pttMerkez);
            System.out.println("----------------------------\n");

        }

        @Step("Excelde Kişi Bilgilerinde Dağıtıcı: {_dagitici}, Düzenleyen: {_duzenleyen}, Avans Sorumlusu: {_avansSorumlusu}, Kontrol Eden: {_kontrolEden}, PTT Merkez: {_pttMerkez} bilgileri kontrol edildi.")
        public void kisiBilgileriKontrol(String _dagitici, String _duzenleyen, String _avansSorumlusu, String _kontrolEden, String _pttMerkez){
            Assert.assertEquals(dagitici, _dagitici);
            Assert.assertEquals(duzenleyen, _duzenleyen);
            Assert.assertEquals(avansSorumlusu, _avansSorumlusu);
            Assert.assertEquals(kontrolEden, _kontrolEden);
            Assert.assertEquals(pttMerkez, _pttMerkez);
        }

        @Step("Excelde Giden Kurum: {gidenKurum}, Ülke: {ulke}, Şehir: {sehir}, Posta Adı: {postaAdi}, Ağırlık: {agirlik}, Pul No: {pulNo}, Ücret: {ucretTL} bilgileri kontrol edildi.")
        public void tabloKontrol(String gidenKurum, String ulke, String sehir, String postaAdi, String agirlik, String pulNo, String ucretTL) {
            boolean rowFound = false;
            for(int i = 0; i < gidenKurumlar.length; i++){
                if(gidenKurumlar[i].equals(gidenKurum) && ulkeler[i].equals(ulke) && sehirler[i].equals(sehir) && postaAdlari[i].equals(postaAdi)&& agirliklar[i].equals(agirlik) && pulNolar[i].equals(pulNo) && ucretTLler[i].equals(ucretTL))
                {
                    rowFound = true;
                    break;
                }
            }
            Assert.assertEquals(rowFound, true);
        }


    }

    @Step("Ptt Raporu sayfasını aç")
    public PttRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.PttRaporu);
        return this;
    }

    @Step("Posta tarihi gir: {postTarihi}")
    public PttRaporuPage postaTarihiDoldur(String postaTarihi) {
        txtPostaTarihi.setValue(postaTarihi);
        return this;
    }

    @Step("Posta tipi sec: {postaTipi}")
    public PttRaporuPage postaTipiSec(String postaTipi) {
        cmbPostaTipi.selectOption(postaTipi);
        return this;
    }

    @Step("Arama Detayları Panelini Aç")
    public PttRaporuPage aramaDetaylariPanelAc() {
        divAramaDetaylar.click();
        return this;
    }

    @Step("Sorgula butonuna tıkla")
    public PttRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Tablo da Gittiği Yer: {gittigiYer}, Evrak Sayı: {evrakSayi}, Gidiş Şekli: {gidisSekli} verileri olmalı mı?: {shouldBeExist}")
    public PttRaporuPage tabloKontrolEt(String gittigiYer, String evrakSayi, String gidisSekli, boolean shouldBeExist) {
        if (shouldBeExist == true) {

            ElementsCollection tablePages = $$("td[id$='pttRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

            boolean elementFound = false;

            for (int i = 0; i < tablePages.size(); i++) {
                tablePages.get(i).click();

                SelenideElement currentLine = tableRaporlar
                        .filterBy(Condition.text(gittigiYer))
                        .filterBy(Condition.text(evrakSayi))
                        .filterBy(Condition.text(gidisSekli))
                        .first();

                if (currentLine.isDisplayed() && currentLine.exists()) {
                    elementFound = true;
                    break;
                }

            }

            Assert.assertEquals(elementFound, shouldBeExist);

            return this;

        } else {

            ElementsCollection tablePages = $$("td[id$='pttRaporuForm:havaleEvrakRaporOutputTab_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

            boolean elementFound = false;

            for (int i = 0; i < tablePages.size(); i++) {
                tablePages.get(i).click();

                SelenideElement currentLine = tableRaporlar
                        .filterBy(Condition.text(gittigiYer))
                        .filterBy(Condition.text(evrakSayi))
                        .filterBy(Condition.text(gidisSekli))
                        .first();

                if (currentLine.isDisplayed() && currentLine.exists()) {
                    elementFound = true;
                }

                Assert.assertEquals(elementFound, shouldBeExist);

            }

        }
        return this;
    }

    @Step("Tablodan {kolonAdi} değeri alındı.")
    public String tablodanDegerAl(String kolonAdi) {

        String returnValue = "";
        int columnIndex = -1;
        String columnName = "";
        for (int i = 0; i < tableColumns.size(); i++) {
            if (tableColumns.get(i).getText().equals(kolonAdi)) {
                columnIndex = i + 1;
                break;
            }
        }

        returnValue = $x("//tbody[@id='pttRaporuForm:havaleEvrakRaporOutputTab_data']/tr[@role='row']/td[" + columnIndex + "]").getText();

        return returnValue;
    }

    @Step("Tablodan {satirNumarasi}. satırdan '{kolonAdi}' değeri alındı.")
    public String tablodanDegerAl(String kolonAdi, int satirNumarasi) {

        String returnValue = "";
        int columnIndex = -1;
        String columnName = "";
        for (int i = 0; i < tableColumns.size(); i++) {
            if (tableColumns.get(i).getText().equals(kolonAdi)) {
                columnIndex = i + 1;
                break;
            }
        }

        returnValue = $x("//tbody[@id='pttRaporuForm:havaleEvrakRaporOutputTab_data']/tr[@role='row']["+satirNumarasi+"]/td[" + columnIndex + "]").getText();

        return returnValue;
    }

    @Step("Dağıtıcı doldur: {dagitici}")
    public PttRaporuPage dagiticiDoldur(String dagitici) {
        txtDagitici.setValue(dagitici);
        return this;
    }

    @Step("Duzenleyen doldur: {duzenleyen}")
    public PttRaporuPage duzenleyenDoldur(String duzenleyen) {
        txtDuzenleyen.setValue(duzenleyen);
        return this;
    }

    @Step("Avans Sorumlusu doldur: {avansSorumlusu}")
    public PttRaporuPage avansSorumlusuDoldur(String avansSorumlusu) {
        txtAvansSorumlusu.setValue(avansSorumlusu);
        return this;
    }

    @Step("Kontrol Eden doldur: {kontrolEden}")
    public PttRaporuPage kontrolEdenDoldur(String kontrolEden) {
        txtKontrolEden.setValue(kontrolEden);
        return this;
    }

    @Step("Ptt Merkez doldur: {pttMerkez}")
    public PttRaporuPage pttMerkezDoldur(String pttMerkez) {
        txtPttMerkez.setValue(pttMerkez);
        return this;
    }

    @Step("Ülke doldur: {ulke}")
    public PttRaporuPage ulkeDoldur(String ulke) {
        //txtUlke.selectLov(ulke);
        txtUlke.type(ulke)
                .getTitleItems()
                .filterBy(Condition.textCaseSensitive(ulke))
                .first().click();
        txtUlke.closeTreePanel();
        return this;
    }

    @Step("İl doldur: {il}")
    public PttRaporuPage ilDoldur(String il) {
        txtIl.selectLov(il);
        return this;
    }

    @Step("Tüm Sonuçları Raporla butonuna tıkla")
    public PttRaporuPage tumSonuclariRaporla() {
        btnTumSonuclariRaporla.click();
        return this;
    }

    @Step("Rapor al butonuna tıkla. ")
    public PttRaporuPage raporAl(){
        $$x("//button[contains(@id, 'pttRaporuForm:havaleEvrakRaporOutputTab')]").first().click();
        return this;
    }

    @Step("Dağıtıcı Değerini döndür.")
    public String dagiticiGetir(){
        return txtDagitici.getValue();
    }

    @Step("Düzenleyen Değerini döndür.")
    public String duzenleyenGetir(){
        return txtDuzenleyen.getValue();
    }

    @Step("Avans Sorumlusu Değerini döndür.")
    public String avansSorumlusuGetir(){
        return txtAvansSorumlusu.getValue();
    }

    @Step("Kontrol Eden Değerini döndür.")
    public String kontrolEdenGetir(){
        return txtKontrolEden.getValue();
    }

    @Step("PTT Merkez Değerini döndür.")
    public String pttMerkezGetir(){
        return txtPttMerkez.getValue();
    }

    public String indirilenDosyaAd() {
        int i = 0;
        while (i < 140) {
            sleep(i);
            i++;
        }

        File root = new File(getDownloadPath());
        FilenameFilter beginswithm = new FilenameFilter() {
            public boolean accept(File directory, String filename) {
                return filename.matches("Rapor_.*\\.xls");
            }
        };

        File[] files = root.listFiles(beginswithm);
        for (File f : files) {
            System.out.println(f);
        }

        String filePath = files[0].getPath();

        return filePath;
    }


}

