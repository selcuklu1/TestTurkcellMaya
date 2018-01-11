package tests.EkIlgi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
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
    TextEditor editor;

    @BeforeMethod
    public void beforeTests(Method method) {

        login();
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2199: Evrak oluşturmada Ek ekleme (50 MB üzeri dosya ekleme)")
    public void TS2199() {

/*       pre. con.:
        “Yüklenen Dosyalar İçin Maksimum Boyut (byte)” sistem sabitindeki değer 400 MB olmalı.
        //sisteme yüklenebilecek 50 MB üzeri dosya bilgisayarda kayıtlı bulunmalı.
        //Office Converter sunucuda açık olmalıdır.(Türksat ile görüşülmeli)"*/

        String dosyaAdiPDF = "TS2199.pdf";
        String dosyaAdiDOC = "TS2199.doc";
        String dosyaAdiDOCX = "TS2199.docx";
        String dosyaAdiXLS = "TS2199.xls";
        String dosyaAdiXLSX = "TS2199.xlsx";
        String dosyaAdiPPT = "TS2199.ppt";
        String dosyaAdiPPTX = "TS2199.pptx";
        String dosyaAdiDOC2 = "TS2199test.doc";  //silinecek sonra

        String pathPDF = getDocPath() + "TS2199.pdf";
        String pathDOC = getDocPath() + "TS2199.doc";
        String pathDOCX = getDocPath() + "TS2199.docx";
        String pathXLS = getDocPath() + "TS2199.xls";
        String pathXLSX = getDocPath() + "TS2199.xlsx";
        String pathPPT = getDocPath() + "TS2199.ppt";
        String pathPPTX = getDocPath() + "TS2199.pptx";

        //Denemk için yazıldı. Silinecek sonra
        String pathDOC2 = getDocPath() + "TS2199test.doc";

        String ekMetniAciklama = " isimli dosya eklendi";
        String fizikselEkMetni = "Dosya eklendi " +getSysDate();

        String evrakNo = "5408";
        String evrakSayisi = "1402683517-1012";

        String onayAkisi = "Ts2199 OnayAkisi";
        String kaldirilacakKlasorler = "ESK05";
        String evrakKonusu = "TS2199_" +  getSysDate();
        String bilgi = "TS2199 Senaryosu";
        String geregi = "TS2199a Senaryosu";
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()

                //TS2199.pdf
                .ekleriEkMetniDoldur(dosyaAdiPDF + " " +ekMetniAciklama)
                .dosyaEkle(pathPDF, "PDF")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiPDF)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiPDF, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPDF)

                //TS2199.doc
                .ekleriEkMetniDoldur(dosyaAdiDOC + " " +ekMetniAciklama)
                .dosyaEkle(pathDOC, "DOC")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiDOC)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiDOC, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOC)

                //TS2199.docx
                .ekleriEkMetniDoldur(dosyaAdiDOCX + " " +ekMetniAciklama)
                .dosyaEkle(pathDOCX, "DOCX")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiDOCX)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiDOCX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOCX)

                //TS2199.xls
                .ekleriEkMetniDoldur(dosyaAdiXLS + " " +ekMetniAciklama)
                .dosyaEkle(pathXLS, "XLS")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiXLS)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiXLS, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.xlsx
                .ekleriEkMetniDoldur(dosyaAdiXLSX + " " +ekMetniAciklama)
                .dosyaEkle(pathXLSX, "XLSX")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiXLSX)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiXLSX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.ppt
                .ekleriEkMetniDoldur(dosyaAdiPPT + " " +ekMetniAciklama)
                .dosyaEkle(pathPPT, "PPT")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiPPT)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiPPT, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPPT)

                //TS2199.pptx
                .ekleriEkMetniDoldur(dosyaAdiPPTX + " " +ekMetniAciklama)
                .dosyaEkle(pathPPTX, "PPTX")
                .dosyaYukleneneKadarBekle()
                .eklenenDosyaAdiKontrol(dosyaAdiPPTX)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiPPTX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPPTX)

                .fizikselEkEkleTabiniAc()
                .fizikselEkMetniDoldur(fizikselEkMetni)
                .fizikselEkMetniEkle()
                .listelenenEklerdeKontrol(fizikselEkMetni, "Fiziksel Ek Metni")

                .sistemdeKayitliEvrakEkleTabiniAc()
                .evrakAramaDoldur(evrakNo)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakNo)
                .evrakDetayiGoster()
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        evrakOlusturPage
                .ekleriTabAc()
                .evrakEkEkle()
                .listelenenEklerdeKontrol(evrakSayisi, "Evrak Sayisi")
                .eklenenEklerListesindeDetayGoster(evrakSayisi)
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        evrakOlusturPage
                .editorTabAc()
                .editordeEkKontrol(dosyaAdiPDF)
                .editordeEkKontrol(dosyaAdiDOC)
                .editordeEkKontrol(dosyaAdiDOCX)
                .editordeEkKontrol(dosyaAdiXLS)
                .editordeEkKontrol(dosyaAdiXLSX)
                .editordeEkKontrol(dosyaAdiPPT)
                .editordeEkKontrol(dosyaAdiPPTX)
                .editordeEkKontrol(fizikselEkMetni)
                .editordeEkKontrol(evrakSayisi);

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(fizikselEkMetni)
                .silmeOnayi("Evet");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(evrakSayisi)
                .silmeOnayi("Evet");

        evrakOlusturPage
                .editorTabAc()
                .editordeEkGelmedigiKontrolu(fizikselEkMetni)
                .editordeEkGelmedigiKontrolu(evrakSayisi);

        editor
                .type("TS2199 nolu senaryonun testi");

        //Evrak alanları doldurma
        evrakOlusturPage
                .bilgilerTabiAc()
                .konuDoldur(evrakKonusu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiDoldur(bilgi)
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiDoldur(geregi, "Gerçek Kişi Adı")
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .imzalaButonaTikla()
                .sImzalaRadioSec()
                .evrakImzaOnay()
                .islemMesaji().basariliOlmali(basariMesaji);
    }
}
