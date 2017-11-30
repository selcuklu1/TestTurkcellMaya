package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvrakPage extends MainPage {

    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim = comboLov(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt1805"));
    SelenideElement btnRaporAlPdf = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt1803"));
    SelenideElement tblKaydedilenGelenEvrak = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data"));
    SelenideElement tbldene = $(By.xpath("//tbody[@id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data']/tr/td[2]/div"));

    public KaydedilenGelenEvrakPage openPage() {
        ustMenu("Raporlar", "Kaydedilen Gelen Evrak");
        return this;
    }

    @Step("Birim alanı doldur")
    public KaydedilenGelenEvrakPage birimDoldur(String birim) {
        cmbBirim.selectLov(birim);
        return this;
    }

    @Step("Geldiği yer seç")
    public KaydedilenGelenEvrakPage geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOptionByValue(geldigiYer);
        return this;
    }

    @Step("Gelen Evrak no alanını doldur")
    public KaydedilenGelenEvrakPage gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrakPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Rapor al Excel")
    public KaydedilenGelenEvrakPage raporAlExcel() throws IOException, InterruptedException {

        deleteFile("C:\\Users\\Emre_Sencan\\Downloads\\","Rapor_");
        btnRaporAlExcel.click();
        Thread.sleep(4000);
        searchDownloadedFileWithName("C:\\Users\\Emre_Sencan\\Downloads\\","Rapor_.xls");
        return this;
    }


    //Dosyanın bilgisayara inip inmediğini kontrol eder.
    public boolean searchDownloadedFileWithName(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();
        Pattern y = Pattern.compile("[^0-9]");
        String s = null;
        SoftAssert sa = new SoftAssert();

        for (int i = 0; i < dir_contents.length; i++) {
            String file = dir_contents[i].getName().toString();
            s="";
            Matcher m = y.matcher(file);
            while (m.find()) {
                s =s+ m.group();
            }
//            sa.assertEquals(s,fileName,"Klasör "+ dir_contents[i].getName().toString() +"indirilmiştir.");
//            sa.assertNotEquals(s,fileName,"İstenilen dosya indirilmemiştir.");
//            assert s.equals(fileName) : "Klasör "+ dir_contents[i].getName().toString() + "indirilmiştir.";
//            assert s.equalsIgnoreCase(fileName) : "İstenilen dosya indirilmemiştir.";

            if (s.contains(fileName)){
                System.out.println("dosya indirilmiştir.");
                Allure.addAttachment(dir_contents[i].getName().toString(),"raporu indirilmiştir");
                flag = true;
                break;
            }
            else
                System.out.println("İstenilen dosya indirilmemiştir.");
        }
        return flag;
    }

    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage raporAlPdf() throws IOException, InterruptedException {
        deleteFile("C:\\Users\\Emre_Sencan\\Downloads\\","Rapor_");
        btnRaporAlPdf.click();
        Thread.sleep(4000);
        searchDownloadedFileWithName("C:\\Users\\Emre_Sencan\\Downloads\\","Rapor_.pdf");
        return this;
    }

    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage txtClear() {
        txtEvrakKayitNo.clear();
        return this;
    }

    public KaydedilenGelenEvrakPage tabloKontrolu(String evrakNo) {
//        WebElement columnId =  findElementOnTableByColumnInput(tblKaydedilenGelenEvrak,1,evrakNo);
        String text = tbldene.text();
        Assert.assertEquals(text, evrakNo);
        return this;
    }

}
