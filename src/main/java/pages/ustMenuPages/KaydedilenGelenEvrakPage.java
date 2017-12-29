package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvrakPage extends MainPage {

    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim = comboLov(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable'] button:nth-child(4)");
    SelenideElement btnRaporAlPdf = $("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable'] button:nth-child(2)");
//    SelenideElement tblKaydedilenGelenEvrak = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data"));
    ElementsCollection tblKaydedilenGelenEvrak = $$("[id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data'] tr[role='row']");

    @Step("Kaydedilen Gelen Evrak sayfasını aç")
    public KaydedilenGelenEvrakPage openPage() {
        ustMenu("Raporlar", "Kaydedilen Gelen Evrak");
        return this;
    }

    @Step("Birim alanı \"{birim}\" doldurulur")
    public KaydedilenGelenEvrakPage birimDoldur(String birim) {
        cmbBirim.selectLov(birim);
        return this;
    }

    @Step("Geldiği yer \"{geldigiYer}\" seçilir")
    public KaydedilenGelenEvrakPage geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOptionByValue(geldigiYer);
        return this;
    }

    @Step("Gelen Evrak no alanını \"{evrakNo}\" girilir")
    public KaydedilenGelenEvrakPage gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrakPage sorgula() {
        btnSorgula.pressEnter();
        return this;
    }

    @Step("Rapor al Excel")
    public KaydedilenGelenEvrakPage raporAlExcel() throws IOException, InterruptedException {

        deleteFile("C:\\Users\\" + getPcUserName() + "\\Downloads\\","Rapor_");
        btnRaporAlExcel.click();
//        Thread.sleep(8000);
        btnSorgula.click();
        searchDownloadedFileWithName("C:\\Users\\" + getPcUserName() + "\\Downloads\\","Rapor_.xls");
        return this;
    }


    //Dosyanın bilgisayara inip inmediğini kontrol eder.


    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage raporAlPdf() throws IOException, InterruptedException {
        deleteFile("C:\\Users\\" + getPcUserName() + "\\Downloads\\","Rapor_");
        btnRaporAlPdf.click();
//        Thread.sleep(8000);
        btnSorgula.click();
        searchDownloadedFileWithName("C:\\Users\\" + getPcUserName() + "\\Downloads\\", "Rapor_.pdf");
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
