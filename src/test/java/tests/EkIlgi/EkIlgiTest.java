package tests.EkIlgi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;

import java.lang.reflect.Method;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Ek Ilgi" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class EkIlgiTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    @BeforeMethod
    public void beforeTests(Method method) {

        login();
        evrakOlusturPage = new EvrakOlusturPage();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2199: Evrak oluşturmada Ek ekleme (50 MB üzeri dosya ekleme)")
    public void TS2199() {

/*       pre. con.:
        “Yüklenen Dosyalar İçin Maksimum Boyut (byte)” sistem sabitindeki değer 400 MB olmalı.
        //sisteme yüklenebilecek 50 MB üzeri dosya bilgisayarda kayıtlı bulunmalı.
        //Office Converter sunucuda açık olmalıdır.(Türksat ile görüşülmeli)"*/

        String pathPDF = getDocPath() + "TS2199.pdf";
        String pathDOC = getDocPath() + "TS2199.doc";
        String pathDOCX = getDocPath() + "TS2199.docx";
        String pathXLS = getDocPath() + "TS2199.xls";
        String pathXLSX = getDocPath() + "TS2199.xlsx";
        String pathPPT = getDocPath() + "TS2199.ppt";
        String pathPPTX = getDocPath() + "TS2199.pptx";

        String ekMetniAciklama = "Metin eklendi";

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()
                .ekleriEkMetniDoldur(ekMetniAciklama)
                .dosyaEkle(pathPDF, "PDF")
                .dosyaYukleneneKadarBekleme(0, 60);
    }
}
