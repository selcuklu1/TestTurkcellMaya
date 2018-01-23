package tests.EkIlgi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.ParafBekleyenlerPage;
import pages.solMenuPages.ParafladiklarimPage;
import pages.solMenuPages.TaslakEvraklarPage;
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
    TaslakEvraklarPage taslakEvraklarPage;
    EvrakPageButtons evrakPageButtons;
    ParafladiklarimPage parafladiklarimPage;
    ParafBekleyenlerPage parafBekleyenlerPage;

    @BeforeMethod
    public void beforeTests(Method method) {

        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        taslakEvraklarPage = new TaslakEvraklarPage();
        evrakPageButtons = new EvrakPageButtons();
        parafladiklarimPage = new ParafladiklarimPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();

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

        String pathPDF = getUploadPath() + "TS2199.pdf";
        String pathDOC = getUploadPath() + "TS2199.doc";
        String pathDOCX = getUploadPath() + "TS2199.docx";
        String pathXLS = getUploadPath() + "TS2199.xls";
        String pathXLSX = getUploadPath() + "TS2199.xlsx";
        String pathPPT = getUploadPath() + "TS2199.ppt";
        String pathPPTX = getUploadPath() + "TS2199.pptx";

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

        login();

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()

                //TS2199.pdf
                .ekleriEkMetniDoldur(dosyaAdiPDF + " " +ekMetniAciklama)
                .dosyaEkle(pathPDF, "PDF")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPDF)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiPDF, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPDF)

                //TS2199.doc
                .ekleriEkMetniDoldur(dosyaAdiDOC + " " +ekMetniAciklama)
                .dosyaEkle(pathDOC, "DOC")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiDOC)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiDOC, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOC)

                //TS2199.docx
                .ekleriEkMetniDoldur(dosyaAdiDOCX + " " +ekMetniAciklama)
                .dosyaEkle(pathDOCX, "DOCX")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiDOCX)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiDOCX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOCX)

                //TS2199.xls
                .ekleriEkMetniDoldur(dosyaAdiXLS + " " +ekMetniAciklama)
                .dosyaEkle(pathXLS, "XLS")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiXLS)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiXLS, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.xlsx
                .ekleriEkMetniDoldur(dosyaAdiXLSX + " " +ekMetniAciklama)
                .dosyaEkle(pathXLSX, "XLSX")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiXLSX)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiXLSX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.ppt
                .ekleriEkMetniDoldur(dosyaAdiPPT + " " +ekMetniAciklama)
                .dosyaEkle(pathPPT, "PPT")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPPT)
                .ekleriEkle()
                .listelenenEklerdeKontrol(dosyaAdiPPT, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPPT)

                //TS2199.pptx
                .ekleriEkMetniDoldur(dosyaAdiPPTX + " " +ekMetniAciklama)
                .dosyaEkle(pathPPTX, "PPTX")
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPPTX)
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
                .editordeEkKontrol(dosyaAdiPDF, "PDF")
                .editordeEkKontrol(dosyaAdiDOC, "DOC")
                .editordeEkKontrol(dosyaAdiDOCX, "DOCX")
                .editordeEkKontrol(dosyaAdiXLS,"XLS")
                .editordeEkKontrol(dosyaAdiXLSX,"XLSX")
                .editordeEkKontrol(dosyaAdiPPT,"PPT")
                .editordeEkKontrol(dosyaAdiPPTX,"PPTX")
                .editordeEkKontrol(fizikselEkMetni,"Fiziksel Ek Metin Açıklama")
                .editordeEkKontrol(evrakSayisi, "Evrak Sayısı");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(fizikselEkMetni)
                .ekSilmeOnayi("Evet");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(evrakSayisi)
                .ekSilmeOnayi("Evet");

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

        //Burası güncellendi excele göre.
        evrakPageButtons
                .imzalaButonaTikla()
                .sImzalaRadioSec()
                .evrakImzaOnay()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2346: Evrak oluşturmada ilişkili evrak ekleme")
    public void TS2346() {

        String evrakSayisi = "6345202-044-10495";
        String evrakKonusu = "TS2346_EkIlgi_Senaryosu_"+getSysDate();


        String ilisikDosya1Aciklama = "İlisik_Dosya1_"+getSysDate();
        String ilisikDosya2Aciklama = "İlisik_Dosya2_"+getSysDate();
        String ilisikDosya3Aciklama = "İlisik_Dosya3_"+getSysDate();

        String dosyaAdi3 = "TS2346_dosya3.pdf";
        String pathDosya3 = getUploadPath() + "TS2346_dosya3.pdf";


        String ilisikDosya4Aciklama = "İlisik_Dosya4_"+getSysDate();
        String dosyaAdi4 = "TS2346_dosya4.pdf";
        String pathDosya4 = getUploadPath() + "TS2346_dosya4.pdf";

        String ilisikDosya5Aciklama = "İlisik_Dosya5_"+getSysDate();
        String dosyaAdi5 = "TS2346_dosya5.pdf";
        String pathDosya5 = getUploadPath() + "TS2346_dosya5.pdf";

        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR); //mbozdemir

        //en son taslaklar listesinde kontrol için uniquq konu giriliyor.
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuDoldur(evrakKonusu);

        evrakOlusturPage
                .iliskiliEvraklarTabAc()

                //Dosya ekle tabı
                //ilisik dosya1
                .ilisikMetniDoldur(ilisikDosya1Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("İlişik")
                .taramaHavuzuSorgula()
                .birinciEvrakSec(true)
                .dosya1AciklamaDoldur(ilisikDosya1Aciklama)
                //.taramaTuruSec("İlişik")
                .taramaHavuzuTamam()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(ilisikDosya1Aciklama, "Açıklama")
                .listelenenEvraklardaIndırButonuKontrol(ilisikDosya1Aciklama)

                //ilisik dosya2
                .ilisikMetniDoldur(ilisikDosya2Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("İlişik")
                .taramaHavuzuSorgula()
                .ikinciEvrakSec(true)
                .dosya2AciklamaDoldur(ilisikDosya2Aciklama)
                //.taramaTuruSec("İlişik")
                .taramaHavuzuTamam()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(ilisikDosya2Aciklama, "Açıklama")
                .listelenenEvraklardaIndırButonuKontrol(ilisikDosya2Aciklama)

                //ilisik dosya3
                .ilisikMetniDoldur(ilisikDosya3Aciklama)
                .dosyaEkle(pathDosya3, dosyaAdi3)
                .dosyaYukleneneKadarBekle()
                .iliskiliSitemdeEklenenDosyaAdiKontrol(dosyaAdi3)
                .iliskiliEkle()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(dosyaAdi3, "Dosya Adı")
                .listelenenEvraklardaIndırButonuKontrol(dosyaAdi3)

                //Sistemde kayıtlı evrak ekle tabı
                .sistemdeKayitliEvrakEkleTabiniAc()
                .sistemdeKayitliEvrakEkleAlanKontrolleri()

                .evrakAranacakYerSec("İşlem Yaptıklarımda Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaGelmemeKontrolu(evrakSayisi)

                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi)
                .evrakIlisikEkle()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(evrakSayisi, "Evrak Sayısı")

                .eklenenEvrakVeDosyaListesindeDetayGoster(evrakSayisi)
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        //Tercüme ekle tabı
        evrakOlusturPage
                .iliskiliEvraklarTabAc()
                .tercumeEkleTabiniAc()
                .tercumeIlisikMetniDoldur(ilisikDosya4Aciklama)
                .tercumeDosyaEkle(pathDosya4, dosyaAdi4)
                .dosyaYukleneneKadarBekle()
                .tercumeEklenenDosyaAdiKontrol(dosyaAdi4)
                .tercumeEkleEkle()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(dosyaAdi4, "Dosya Adı")
                .listelenenEvraklardaIndırButonuKontrol(dosyaAdi4)
                .eklenenEvrakVeDosyaListesindeDetayGoster(dosyaAdi4)
                .eklenTercumeDosyaKontrolu()
                .ismeGoreIlisikSilme(dosyaAdi4)
                .ilisikSilmeOnayi("Evet");

        evrakOlusturPage
                .iliskiliEvraklarTabAc()
                .tercumeIlisikMetniDoldur(ilisikDosya5Aciklama)
                .tercumeDosyaEkle(pathDosya5, dosyaAdi5)
                .dosyaYukleneneKadarBekle()
                .tercumeEklenenDosyaAdiKontrol(dosyaAdi5)
                .tercumeEkleEkle();

        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        taslakEvraklarPage
                .openPage()
                .evrakKontrolu(evrakKonusu);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2348: Evrak oluşturmada ek iliştirme")
    public void TS2348() {

        String evrakSayisi = "6345202-150-1065";
        String evrakKonusu = "TS2348_EkIlgi_Senaryosu_"+getSysDate();

        String ekDosya1Aciklama = "Ek_Dosya1_"+getSysDate();
        String ekDosya2Aciklama = "Ek_Dosya2_"+getSysDate();
        String ekDosya3Aciklama = "Ek_Dosya3_"+getSysDate();
        String fizikselEkAciklama = "Fiziksel_Ek_"+getSysDate();

        String dosyaAdi3 = "TS2348_dosya3.pdf";
        String pathDosya3 = getUploadPath() + "TS2348_dosya3.pdf";

        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR); //mbozdemir

        //Taslaklar listesinde kontrol için unique konu giriliyor.
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuDoldur(evrakKonusu);

        evrakOlusturPage
                .ekleriTabAc()

                //Dosya ekle tabı
                //ek1
                .ekleriEkMetniDoldur(ekDosya1Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("Ek")
                .taramaHavuzuSorgula()
                .birinciEvrakSec(true)
                .dosya1AciklamaDoldur(ekDosya1Aciklama)
                .taramaHavuzuTamam()
                .listelenenEklereDosyanınGeldigiKontrolu(ekDosya1Aciklama, "Açıklama")
                .listelenenEklerdeIndırButonuKontrol(ekDosya1Aciklama)

                //ek2
                .ekleriEkMetniDoldur(ekDosya2Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("Ek")
                .taramaHavuzuSorgula()
                .ikinciEvrakSec(true)
                .dosya2AciklamaDoldur(ekDosya2Aciklama)
                .taramaHavuzuTamam()
                .listelenenEklereDosyanınGeldigiKontrolu(ekDosya2Aciklama, "Açıklama")
                .listelenenEklerdeIndırButonuKontrol(ekDosya2Aciklama)

                //ek3
                .ekleriEkMetniDoldur(ekDosya3Aciklama)
                .dosyaEkle(pathDosya3, dosyaAdi3)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi3)
                .ekleriEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(dosyaAdi3, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdi3)

                //fiziksel ek
                .fizikselEkEkleTabiniAc()
                .fizikselEkMetniDoldur(fizikselEkAciklama)
                .fizikselEkMetniEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(dosyaAdi3, "Açıklama")

                .sistemdeKayitliEvrakEkleTabAc()
                .sistemdeKayitliEvrakEkleAlanKontrolleri()

                .evrakAranacakYerSec("İşlem Yaptıklarımda Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaGelmemeKontrolu(evrakSayisi)

                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi)
                .evrakEkEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(evrakSayisi, "Evrak Sayısı")

                .ekListesindeDetayGoster(evrakSayisi)
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        evrakOlusturPage
                .ekleriTabAc()

                //ek1 detay
                .ekListesindeDetayGoster(ekDosya1Aciklama)
                .ekleriDetayGeldigiKontrolu()

                //ek2 detay
                .ekListesindeDetayGoster(ekDosya2Aciklama)
                .ekleriDetayGeldigiKontrolu()

                //ek3 detay
                .ekListesindeDetayGoster(ekDosya3Aciklama)
                .ekleriDetayGeldigiKontrolu();

        evrakOlusturPage
                .editorTabAc()
                .editordeEkKontrol(ekDosya1Aciklama, "Açıklama")
                .editordeEkKontrol(ekDosya2Aciklama,"Açıklama")
                .editordeEkKontrol(ekDosya3Aciklama,"Açıklama")
                .editordeEkKontrol(fizikselEkAciklama, "Fiziksel Ek Açıklama")
                .editordeEkKontrol(evrakSayisi, "Evrak Sayısı");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(fizikselEkAciklama)
                .ekSilmeOnayi("Evet");

        evrakOlusturPage
                .editorTabAc()
                .editordeEkGelmedigiKontrolu(fizikselEkAciklama);

        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        taslakEvraklarPage
                .openPage()
                .evrakKontrolu(evrakKonusu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0956: Evrak oluşturmada ilgi ekleme")
    public void TS0956() {

        String evrakSayisi = "6345202-150-1065";
        String evrakKonusu = "TS0956_EkIlgi_Senaryosu_"+getSysDate();

        String ilgiDosya1Aciklama = "İlgi_Dosya1_"+getSysDate();
        String ilgiDosya2Aciklama = "İlgi_Dosya2_"+getSysDate();
        String ilgiDosya3Aciklama = "İlgi_Dosya3_"+getSysDate();
        String ilgiDosya4Aciklama = "İlgi_Dosya4_"+getSysDate();
        String ilgiDosya5YeniAciklama = "İlgi_Dosya5_"+getSysDate();

        String dosyaAdi3 = "TS0956_dosya3.pdf";
        String pathDosya3 = getUploadPath() + "TS0956_dosya3.pdf";

        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR); //mbozdemir

        //Taslaklar listesinde kontrol için unique konu giriliyor.
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuDoldur(evrakKonusu);

        evrakOlusturPage
                .ilgileriTabAc()

                //Dosya ekle tabı
                //ilgi1
                .ilgileriIlgiMetniDoldur(ilgiDosya1Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("İlgi")
                .taramaHavuzuSorgula()
                .birinciEvrakSec(true)
                .dosya1AciklamaDoldur(ilgiDosya1Aciklama)
                .taramaHavuzuTamam()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgiDosya1Aciklama, "Açıklama")
                .listelenenIlgilerdeIndırButonuKontrol(ilgiDosya1Aciklama)

                //ilgi2
                .ilgileriIlgiMetniDoldur(ilgiDosya2Aciklama)
                .taramaHavuzundanEkle()
                .evrakTuruSec("İlgi")
                .taramaHavuzuSorgula()
                .ikinciEvrakSec(true)
                .dosya2AciklamaDoldur(ilgiDosya2Aciklama)
                .taramaHavuzuTamam()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgiDosya2Aciklama, "Açıklama")
                .listelenenIlgilerdeIndırButonuKontrol(ilgiDosya2Aciklama)

                //ilgi3
                .ilgileriIlgiMetniDoldur(ilgiDosya3Aciklama)
                .dosyaEkle(pathDosya3, dosyaAdi3)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi3)
                .ilgileriEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(dosyaAdi3, "Dosya Adı")
                .listelenenIlgilerdeIndırButonuKontrol(dosyaAdi3)

                .ilgileriMetinEkleTabAc()
                .ilgileriMetinEkleIlgiMetniDoldur(ilgiDosya4Aciklama)
                .ilgileriMetinEkleEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgiDosya4Aciklama, "Dosya Adı")

                .sistemdeKayitliEvrakEkleTabAc()
                .sistemdeKayitliEvrakEkleAlanKontrolleri()

                .evrakAranacakYerSec("İşlem Yaptıklarımda Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaGelmemeKontrolu(evrakSayisi)

                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi)
                .evrakEkEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(evrakSayisi, "Evrak Sayısı")

                .ilgiEkListesindeDetayGoster(evrakSayisi)
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");


        evrakOlusturPage
                .ilgileriTabAc()

                //ilgi1 detay
                .ilgiEkListesindeDetayGoster(ilgiDosya1Aciklama)
                .ilgileriDetayGeldigiKontrolu()

                //ilgi2 detay
                .ilgiEkListesindeDetayGoster(ilgiDosya2Aciklama)
                .ilgileriDetayGeldigiKontrolu()

                //ilgi3 detay
                .ilgiEkListesindeDetayGoster(ilgiDosya3Aciklama)
                .ilgileriDetayGeldigiKontrolu();

        evrakOlusturPage
                .editorTabAc()
                .editordeIlgiKontrol(ilgiDosya1Aciklama, "Aciklama")
                .editordeIlgiKontrol(ilgiDosya2Aciklama, "Aciklama")
                .editordeIlgiKontrol(ilgiDosya3Aciklama, "Aciklama")
                .editordeIlgiKontrol(ilgiDosya4Aciklama, "Aciklama")
                .editordeIlgiKontrol(evrakSayisi, "Evrak Sayısı");

        evrakOlusturPage
                .ilgileriTabAc()
                .ilgiIsmineGoreAciklamaGuncelleme(ilgiDosya5YeniAciklama)
                .ilgiIsmineGoreIlgiSilme(ilgiDosya4Aciklama)
                .ilgiSilmeOnayi("Evet");

        evrakOlusturPage
                .editorTabAc()
                .editordeIlgiKontrol(ilgiDosya5YeniAciklama, "Aciklama")
                .editordeIlgiKontrol(ilgiDosya2Aciklama, "Aciklama")
                .editordeIlgiKontrol(ilgiDosya3Aciklama, "Aciklama")
                .editordeIlgiKontrol(evrakSayisi, "Evrak Sayısı");

        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        taslakEvraklarPage
                .openPage()
                .evrakKontrolu(evrakKonusu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1493: Farklı dağıtım yerlerine (Kişi-Birim) gönderilen eklerin kontrolü")
    public void TS1493() {

        String onayAkisi = "TS1493_EkIlgi_OnayAkışı";
        String birim = "Optiim Birim";
        String kullanici = "Sezai ÇELİK";
        String kurum = "Adalet Bakanlığı";
        String evrakSayisi = "234234234234234234-010.01-10910";
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2025: Evrak önizlemede Evrak Ek/İlgi/İlişikler tablarında sistemden eklenen evrakların da gösterilmesi (akordeon pdf önizleme)")
    public void TS2025() {

        String konuKodu = "605.01";
        String evrakKonusu = "TS2025_EkIlgi_Senaryosu_"+getSysDate();
        String kaldirilacakKlasorler = "300.01.61";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kurum = "Baş Dramaturgluk";
        String aktifKullaniciParaflama = "Mehmet BOZDEMİR";
        String kullaniciParaflama = "Gökçe ŞAHİN";
        String kullaniciKoordine = "Zübeyde TEKİN";
        String kullaniciImzalama = "Yasemin Çakıl AKYOL";

        String ekleriEvrakSayisi = "6345202-010.01-11088";
        String ekleriAciklamaDosya1 = "Ekleri_Dosya1_"+getSysDate();
        String pathDosya1 = getUploadPath() + "TS2025_dosya1.jpeg";
        String dosyaAdi1 = "TS2025_dosya1.jpeg";

        String ilgileriEvrakSayisi = "6345202-010.01-11057";
        String ilgileriAciklamaDosya2 = "İlgileri_Dosya2_"+getSysDate();
        String pathDosya2 = getUploadPath() + "TS2025_dosya2.pdf";
        String dosyaAdi2 = "TS2025_dosya2.pdf";

        String iliskiliEvrakSayisi = "6345202-010.01-11121";
        String iliskiliAciklamaDosya3 = "İlişkili_Dosya3_"+getSysDate();
        String pathDosya3 = getUploadPath() + "TS2025_dosya3.jpg";
        String dosyaAdi3 = "TS2025_dosya3.jpg";



        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR); //mbozdemir

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(evrakKonusu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)

                .geregiSecimTipiSecByText("Birim")
                .geregiDoldur(birim, "Birim")
                .geregiSecimTipiSecByText("Kurum")
                .geregiDoldur(kurum, "Kurum")
                .onayAkisiEkle()

                .kullanicilarDoldur(kullaniciParaflama)
                .kullaniciyaKullaniciTipiSec(kullaniciParaflama, "PARAFLAMA")

                .koordineliSec(true)
                .kullanicilarDoldur(kullaniciKoordine)

                .koordineliSec(true)
                .kullanicilarDoldur(kullaniciImzalama)
                .kullaniciyaKullaniciTipiSec(kullaniciImzalama, "IMZALAMA")

                .kullan();

        evrakOlusturPage
                .editorTabAc();

        editor
                .type("TS2025 nolu senaryonun testi için bir editör metni");

        //Ekleri tabı
        evrakOlusturPage
                .ekleriTabAc()
                .ekleriEkMetniDoldur(ekleriAciklamaDosya1)
                .dosyaEkle(pathDosya1, dosyaAdi1)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi1)
                .ekleriEkle()
                .ekEkleDusukDpiPopupOnayi("Evet")
                .listelenenEklereDosyanınGeldigiKontrolu(dosyaAdi1, "Dosya Adı")

                .sistemdeKayitliEvrakEkleTabAc()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(ekleriEvrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(ekleriEvrakSayisi)
                .evrakEkEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(ekleriEvrakSayisi, "Evrak Sayısı");

        //İlgileri tabı
        evrakOlusturPage
                .ilgileriTabAc()
                .ilgileriIlgiMetniDoldur(ilgileriAciklamaDosya2)
                .dosyaEkle(pathDosya2, dosyaAdi2)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi2)
                .ilgileriEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(dosyaAdi2, "Dosya Adı")

                .sistemdeKayitliEvrakEkleTabAc()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(ilgileriEvrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(ilgileriEvrakSayisi)
                .evrakEkEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgileriEvrakSayisi, "Evrak Sayısı");

        //İlişik evraklar tabı
        evrakOlusturPage
                .iliskiliEvraklarTabAc()
                .ilisikMetniDoldur(iliskiliAciklamaDosya3)
                .dosyaEkle(pathDosya3, dosyaAdi3)
                .dosyaYukleneneKadarBekle()
                .iliskiliSitemdeEklenenDosyaAdiKontrol(dosyaAdi3)
                .iliskiliEkle()
                .ekEkleDusukDpiPopupOnayi("Evet")
                .listelenenEvraklaraDosyanınGeldigiKontrolu(dosyaAdi3, "Dosya Adı")

                .sistemdeKayitliEvrakEkleTabiniAc()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(iliskiliEvrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(iliskiliEvrakSayisi)
                .evrakIlisikEkle()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(iliskiliEvrakSayisi, "Evrak Sayısı");

        evrakOlusturPage
                .kaydet(true)
                .parafla();

        parafladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol("TS2025_EkIlgi_Senaryosu_20180122145731")
                .konuyaGoreEvrakOnizlemedeAc("TS2025_EkIlgi_Senaryosu_20180122145731")
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .tabIlgiBilgileriAc()
                .ilgiBilgieriAccordionKontrol()
                .tabIlisikBilgileriAc()
                .ilisikBilgieriAccordionKontrol();

        login(TestData.usernameGSAHIN, TestData.passwordGSAHIN); //mbozdemir

        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol("TS2025_EkIlgi_Senaryosu_20180122145731")
                .konuyaGoreEvrakOnizlemedeAc("TS2025_EkIlgi_Senaryosu_20180122145731")
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .tabIlisikBilgileriAc()
                .ilisikBilgileriAccordionKontrol();
    }
}
