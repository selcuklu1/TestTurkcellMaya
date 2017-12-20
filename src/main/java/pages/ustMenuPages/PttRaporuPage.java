package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class PttRaporuPage extends MainPage {

    SelenideElement divAramaDetaylar = $x("//h3[. = 'Arama Detayları']");
    SelenideElement txtPostaTarihi = $("span[id$='postaTarihi'] > input");
    BelgenetElement txtUlke = comboLov("input[id$=':lovUlkePttRapor:LovText']");
    BelgenetElement txtIl = comboLov("input[id$=':lovIlPttRapor:LovText']");
    ElementsCollection tableRaporlar = $$("tbody[id='pttRaporuForm:havaleEvrakRaporOutputTab_data'] > tr[role='row']");
    SelenideElement btnSorgula = $("button[id$=':sorgulaButton']");
    SelenideElement cmbPostaTipi = $("select[id$=':postaTipiMenuPttRapor']");

    SelenideElement txtDagitici = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Dağıtıcı']/ancestor::tr//input");
    SelenideElement txtDuzenleyen = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Düzenleyen']/ancestor::tr//input");
    SelenideElement txtAvansSorumlusu = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Avans Sorumlusu']/ancestor::tr//input");
    SelenideElement txtKontrolEden = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Kontrol Eden']/ancestor::tr//input");
    SelenideElement txtPttMerkez = $x("//form[@id='pttRaporuForm']//label[normalize-space(text())='Ptt Merkez']/ancestor::tr//input");

    ElementsCollection tableColumns = $$("div[id='pttRaporuForm:havaleEvrakRaporOutputTab'] > table > thead > tr[role='row'] > th");

    SelenideElement btnTumSonuclariRaporla = $x("//div[@id='pttRaporuForm:havaleEvrakRaporOutputTab']//button[1]");

    @Step("Ptt Raporu sayfasını aç")
    public PttRaporuPage openPage() {
        ustMenu("Ptt Raporu");
        return this;
    }

    @Step("Posta tarihi gir: {postTarihi}")
    public PttRaporuPage postaTarihiDoldur(String postaTarihi){
        txtPostaTarihi.setValue(postaTarihi);
        return this;
    }

    @Step("Posta tipi sec: {postaTipi}")
    public PttRaporuPage postaTipiSec(String postaTipi){
        cmbPostaTipi.selectOption(postaTipi);
        return this;
    }

    @Step("Arama Detayları Panelini Aç")
    public PttRaporuPage aramaDetaylariPanelAc(){
        divAramaDetaylar.click();
        return this;
    }

    @Step("Sorgula butonuna tıkla")
    public PttRaporuPage sorgula(){
        btnSorgula.click();
        return this;
    }

    @Step("Tablo kontrolü")
    public PttRaporuPage tabloKontrolEt(String gittigiYer, String evrakSayi, String gidisSekli, boolean shouldBeExist){
        if(shouldBeExist == true){

            tableRaporlar
                    .filterBy(Condition.text(gittigiYer))
                    .filterBy(Condition.text(evrakSayi))
                    .filterBy(Condition.text(gidisSekli))
                    .first()
                    .shouldBe(Condition.exist)
                    .shouldBe(Condition.visible);

        } else {

            tableRaporlar
                    .filterBy(Condition.text(gittigiYer))
                    .filterBy(Condition.text(evrakSayi))
                    .filterBy(Condition.text(gidisSekli))
                    .first()
                    .shouldNotBe(Condition.exist)
                    .shouldNotBe(Condition.visible);

        }
        return this;
    }

    @Step("Tablo kontrolü")
    public String  tablodanDegerAl(String kolonAdi){

        String returnValue = "";
        int columnIndex = -1;

        for(int i = 0; i < tableColumns.size(); i++){
            if(tableColumns.get(i).getText() == kolonAdi) {
                columnIndex = i + 1;
                break;
            }
        }


        returnValue = $x("//tbody[@id='pttRaporuForm:havaleEvrakRaporOutputTab_data']/tr[@role='row']/td["+columnIndex+"]").getText();

        return returnValue;
    }
    
    @Step("Dağıtıcı doldur: {dagitici}")
    public PttRaporuPage dagiticiDoldur(String dagitici){
        txtDagitici.setValue(dagitici);
        return this;
    }

    @Step("Duzenleyen doldur: {duzenleyen}")
    public PttRaporuPage duzenleyenDoldur(String duzenleyen){
        txtDuzenleyen.setValue(duzenleyen);
        return this;
    }

    @Step("Avans Sorumlusu doldur: {avansSorumlusu}")
    public PttRaporuPage avansSorumlusuDoldur(String avansSorumlusu){
        txtAvansSorumlusu.setValue(avansSorumlusu);
        return this;
    }

    @Step("Kontrol Eden doldur: {kontrolEden}")
    public PttRaporuPage kontrolEdenDoldur(String kontrolEden){
        txtKontrolEden.setValue(kontrolEden);
        return this;
    }

    @Step("Ptt Merkez doldur: {pttMerkez}")
    public PttRaporuPage pttMerkezDoldur(String pttMerkez){
        txtPttMerkez.setValue(pttMerkez);
        return this;
    }

    @Step("Ülke doldur: {ulke}")
    public PttRaporuPage ulkeDoldur(String ulke){
        txtUlke.setValue(ulke);
        return this;
    }

    @Step("İl doldur: {il}")
    public PttRaporuPage ilDoldur(String il){
        txtIl.setValue(il);
        return this;
    }

    @Step("Tüm Sonuçları Raporla butonuna tıkla")
    public PttRaporuPage tumSonuclariRaporla(){
        btnTumSonuclariRaporla.click();
        return this;
    }

   @Step("Excell kontrol et")
   public PttRaporuPage excellKontrolEt(){
       String excelFilePath = "/Users/huseyintumer/Downloads/Rapor_1513693363904.xls";
       try (Workbook workbook = WorkbookFactory.create(new File(excelFilePath))) {
           Sheet firstSheet = workbook.getSheetAt(0);
           Iterator<Row> iterator = firstSheet.iterator();

           while (iterator.hasNext()) {
               Row nextRow = iterator.next();
               Iterator<Cell> cellIterator = nextRow.cellIterator();

               while (cellIterator.hasNext()) {
                   Cell cell = cellIterator.next();

                   switch (cell.getCellType()) {
                       case Cell.CELL_TYPE_STRING:
                           System.out.print(cell.getStringCellValue());
                           break;
                       case Cell.CELL_TYPE_BOOLEAN:
                           System.out.print(cell.getBooleanCellValue());
                           break;
                       case Cell.CELL_TYPE_NUMERIC:
                           System.out.print(cell.getNumericCellValue());
                           break;
                   }
                   System.out.print(" - ");
               }
               System.out.println();
           }

           workbook.close();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (InvalidFormatException e) {
           e.printStackTrace();
       }
       return this;
   }





}

