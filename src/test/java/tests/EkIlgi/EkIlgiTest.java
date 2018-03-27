package tests.EkIlgi;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.switchTo;

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
    KoordineParafladiklarimPage koordineParafladiklarimPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    ImzaladiklarimPage imzaladiklarimPage;
    KoordineBekleyenlerPage koordineBekleyenlerPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;

    @BeforeMethod
    public void beforeTests(Method method) {

        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        taslakEvraklarPage = new TaslakEvraklarPage();
        evrakPageButtons = new EvrakPageButtons();
        parafladiklarimPage = new ParafladiklarimPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        koordineParafladiklarimPage = new KoordineParafladiklarimPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        koordineBekleyenlerPage = new KoordineBekleyenlerPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();

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
        String fizikselEkMetni = "Dosya eklendi " + getSysDate();

        String evrakNo1 = "5408";
        String evrakSayisi1 = "1402683517-1012";

        String evrakNo2 = "11357";
        String evrakSayisi2 = "234234234234234234-010.01-11357";

        String onayAkisi = "Ts2199 OnayAkisi";
        String kaldirilacakKlasorler = "ESK05";
        String evrakKonusu = "TS2199_" + getSysDate();
        String bilgi = "TS2199 Senaryosu";
        String geregi = "TS2199a Senaryosu";
        String basariMesaji = "İşlem başarılıdır!";

        login();

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()

                //TS2199.pdf
                .ekleriEkMetniDoldur(dosyaAdiPDF + " " + ekMetniAciklama)
                .dosyaEkle(pathPDF, "PDF")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPDF)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiPDF, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPDF)

                //TS2199.doc
                .ekleriEkMetniDoldur(dosyaAdiDOC + " " + ekMetniAciklama)
                .dosyaEkle(pathDOC, "DOC")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiDOC)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiDOC, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOC)

                //TS2199.docx
                .ekleriEkMetniDoldur(dosyaAdiDOCX + " " + ekMetniAciklama)
                .dosyaEkle(pathDOCX, "DOCX")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiDOCX)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiDOCX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiDOCX)

                //TS2199.xls
                .ekleriEkMetniDoldur(dosyaAdiXLS + " " + ekMetniAciklama)
                .dosyaEkle(pathXLS, "XLS")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiXLS)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiXLS, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.xlsx
                .ekleriEkMetniDoldur(dosyaAdiXLSX + " " + ekMetniAciklama)
                .dosyaEkle(pathXLSX, "XLSX")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiXLSX)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiXLSX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiXLS)

                //TS2199.ppt
                .ekleriEkMetniDoldur(dosyaAdiPPT + " " + ekMetniAciklama)
                .dosyaEkle(pathPPT, "PPT")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPPT)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiPPT, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPPT)

                //TS2199.pptx
                .ekleriEkMetniDoldur(dosyaAdiPPTX + " " + ekMetniAciklama)
                .dosyaEkle(pathPPTX, "PPTX")
                .dosyaYukleneneKadarLoadingBekle()
                .dosyaYukleneneKadarFileUploadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdiPPTX)
                .ekleriEkle()
                .dosyaYukleneneKadarLoadingBekle()
                .listelenenEklerdeKontrol(dosyaAdiPPTX, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdiPPTX)

                .fizikselEkEkleTabiniAc()
                .fizikselEkMetniDoldur(fizikselEkMetni)
                .fizikselEkMetniEkle()
                .listelenenEklerdeKontrol(fizikselEkMetni, "Fiziksel Ek Metni")

                .sistemdeKayitliEvrakEkleTabiniAc()
                .evrakAramaDoldur(evrakNo1)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakNo1)
                .evrakDetayiGoster()
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup2("Kapat")

                .evrakAramaDoldur(evrakNo2)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakNo2)
                .evrakDetayiGoster()
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup2("Kapat");

        evrakOlusturPage
                .ekleriTabAc()
                .evrakEkEkle()
                .listelenenEklerdeKontrol(evrakSayisi1, "Evrak Sayisi1")
                .eklenenEklerListesindeDetayGoster(evrakSayisi1)
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup2("Kapat")

                .listelenenEklerdeKontrol(evrakSayisi2, "Evrak Sayisi2")
                .eklenenEklerListesindeDetayGoster(evrakSayisi2)
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup2("Kapat");

        evrakOlusturPage
                .editorTabAc()
                .editordeEkKontrol(dosyaAdiPDF, "PDF")
                .editordeEkKontrol(dosyaAdiDOC, "DOC")
                .editordeEkKontrol(dosyaAdiDOCX, "DOCX")
                .editordeEkKontrol(dosyaAdiXLS, "XLS")
                .editordeEkKontrol(dosyaAdiXLSX, "XLSX")
                .editordeEkKontrol(dosyaAdiPPT, "PPT")
                .editordeEkKontrol(dosyaAdiPPTX, "PPTX")
                .editordeEkKontrol(fizikselEkMetni, "Fiziksel Ek Metin Açıklama")
                .editordeEkKontrol(evrakSayisi1, "Evrak Sayısı1")
                .editordeEkKontrol(evrakSayisi2, "Evrak Sayısı2");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(fizikselEkMetni)
                .ekSilmeOnayi("Evet");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(evrakSayisi1)
                .ekSilmeOnayi("Evet");

        evrakOlusturPage
                .ekleriTabAc()
                .ekIsmineGoreEkSilme(evrakSayisi2)
                .ekSilmeOnayi("Evet");

        evrakOlusturPage
                .editorTabAc()
                .editordeEkGelmedigiKontrolu(fizikselEkMetni)
                .editordeEkGelmedigiKontrolu(evrakSayisi1)
                .editordeEkGelmedigiKontrolu(evrakSayisi2);

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
        String evrakKonusu = "TS2346_EkIlgi_Senaryosu_" + getSysDate();


        String ilisikDosya1Aciklama = "İlisik_Dosya1_" + getSysDate();
        String ilisikDosya2Aciklama = "İlisik_Dosya2_" + getSysDate();
        String ilisikDosya3Aciklama = "İlisik_Dosya3_" + getSysDate();

        String dosyaAdi3 = "TS2346_dosya3.pdf";
        String pathDosya3 = getUploadPath() + "TS2346_dosya3.pdf";


        String ilisikDosya4Aciklama = "İlisik_Dosya4_" + getSysDate();
        String dosyaAdi4 = "TS2346_dosya4.pdf";
        String pathDosya4 = getUploadPath() + "TS2346_dosya4.pdf";

        String ilisikDosya5Aciklama = "İlisik_Dosya5_" + getSysDate();
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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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

        String evrakSayisi1 = "6345202-150-1065";
        String evrakSayisi2 = "6345202-010.01-11789";

        String evrakKonusu = "TS2348_EkIlgi_Senaryosu_" + getSysDate();

        String ekDosya1Aciklama = "Ek_Dosya1_" + getSysDate();
        String ekDosya2Aciklama = "Ek_Dosya2_" + getSysDate();
        String ekDosya3Aciklama = "Ek_Dosya3_" + getSysDate();
        String fizikselEkAciklama = "Fiziksel_Ek_" + getSysDate();

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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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
                .dosyaYukleneneKadarLoadingBekle()
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
                .evrakAramaDoldur(evrakSayisi1)
                .dokumanAra()
                .listelenenEvraklardaGelmemeKontrolu(evrakSayisi1)

                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi1)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi1)
                .evrakEkEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(evrakSayisi1, "Evrak Sayısı1")

                .ekListesindeDetayGoster(evrakSayisi1)
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        evrakOlusturPage
                .ekleriTabAc()

                .evrakAranacakYerSec("İşlem Yaptıklarımda Ara")
                .evrakAramaDoldur(evrakSayisi2)
                .dokumanAra()
                .listelenenEvraklardaGelmemeKontrolu(evrakSayisi2)

                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi2)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi2)
                .evrakEkEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(evrakSayisi2, "Evrak Sayısı2")

                .ekListesindeDetayGoster(evrakSayisi2)
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
                .editordeEkKontrol(ekDosya2Aciklama, "Açıklama")
                .editordeEkKontrol(ekDosya3Aciklama, "Açıklama")
                .editordeEkKontrol(fizikselEkAciklama, "Fiziksel Ek Açıklama")
                .editordeEkKontrol(evrakSayisi1, "Evrak Sayısı1")
                .editordeEkKontrol(evrakSayisi2, "Evrak Sayısı2");

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
        String evrakKonusu = "TS0956_EkIlgi_Senaryosu_" + getSysDate();

        String ilgiDosya1Aciklama = "İlgi_Dosya1_" + getSysDate();
        String ilgiDosya2Aciklama = "İlgi_Dosya2_" + getSysDate();
        String ilgiDosya3Aciklama = "İlgi_Dosya3_" + getSysDate();
        String ilgiDosya4Aciklama = "İlgi_Dosya4_" + getSysDate();
        String ilgiDosya5YeniAciklama = "İlgi_Dosya5_" + getSysDate();

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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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
                .evrakTarihBaslangicDoldur("01.01.2017")
                .evrakTarihBitisDoldur("01.12.2018")
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

        String evrakKonusu = "TS1493_EkIlgi_" + getSysDate();
        String konuKodu = "605.01";
        String kaldirilacakKlasorler = "300.01.61";
        String onayAkisi = "TS1493_EkIlgi_OnayAkısı";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Sezai ÇELİK";
        String kurum = "Başbakanlık";
        String kurumPostalanakYerler = "BAŞBAKANLIĞA";

        String evrakSayisi1 = "6345202-010.01-11601";

        String ekleriAciklamaDosya1 = "Ekleri_Dosya1_" + getSysDate();
        String pathDosya1 = getUploadPath() + "TS1493_dosya1.pdf";
        String dosyaAdi1 = "TS1493_dosya1.pdf";

        String fizikselEkAciklama = "FizikselEk_Dosya2" + getSysDate();

        String ilgileriAciklamaDosya2 = "İlgileri_Dosya2_" + getSysDate();
        String pathDosya2 = getUploadPath() + "TS1493_dosya2.pdf";
        String dosyaAdi2 = "TS1493_dosya2.pdf";

        String ilgileriAciklamaDosya3 = "İlgileri_Dosya3_" + getSysDate();

        String ilgileriEvrakSayisi1 = "6345202-010.01-13903";
        String ilgileriEvrakSayisi2 = "6345202-010.01-11845";

        String basariMesaji = "İşlem başarılıdır!";

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
                .geregiSecimTipiSecByText("Kullanıcı")
                .geregiDoldur(kullanici, "Kullanıcı")
                .onayAkisiDoldur(onayAkisi);

        //Ekleri tabı
        evrakOlusturPage

                //Dosya ekle
                .ekleriTabAc()
                .ekleriEkMetniDoldur(ekleriAciklamaDosya1)
                .dosyaEkle(pathDosya1, dosyaAdi1)
                .dosyaYukleneneKadarLoadingBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi1)
                .ekleriEkle()
                .ekEkleDusukDpiPopupOnayi("Evet")
                .listelenenEklereDosyanınGeldigiKontrolu(dosyaAdi1, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdi1)
                .dagitimYerleriAcEk1()
                .dagitimYerlerindeBirimVeKurumSecEk1()

                //fiziksel ek ekle
                .fizikselEkEkleTabiniAc()
                .fizikselEkMetniDoldur(fizikselEkAciklama)
                .fizikselEkMetniEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(fizikselEkAciklama, "Açıklama")
                .dagitimYerleriAcEk2()
                .dagitimYerlerindeBirimKullaniciKaldir()

                //Sistemde kayıtlı evrak ekle
                //evrak sayisi1
                .sistemdeKayitliEvrakEkleTabAc()
                .sistemdeKayitliEvrakEkleAlanKontrolleri()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(evrakSayisi1)
                .dokumanAra()
                .listelenenEvraklardaKontrol(evrakSayisi1)
                .evrakEkEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(evrakSayisi1, "Evrak Sayısı1")
                .dagitimYerleriAcEk3()
                .dagitimYerlerindeKullaniciSecEK3();

        evrakOlusturPage
                .editorTabAc();

        editor
                .type("TS1493 nolu senaryonun testi için bir editör metni girildi.");

        evrakOlusturPage
                .pdfOnIzleme();

        switchTo().window(1);

        evrakOlusturPage
                .pdfKontrol
                .PDFEk1Kontrolu(ekleriAciklamaDosya1)
                .PDFEk2Kontrolu(fizikselEkAciklama)
                .PDFEk3Kontrolu(evrakSayisi1)
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim1(birim, "Ek-2 konulmadı, Ek-3 konulmadı")
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim2(kurum, "Ek-3 konulmadı")
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim(kullanici, "Ek-1 konulmadı, Ek-2konulmadı");

        closeNewWindow();
        switchTo().window(0);

        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        //İlgileri tabı
        evrakOlusturPage
                .ilgileriTabAc()

                //dosya ekle
                .ilgileriIlgiMetniDoldur(ilgileriAciklamaDosya2)
                .dosyaEkle(pathDosya2, dosyaAdi2)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi2)
                .ilgileriEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(dosyaAdi2, "Dosya Adı")

                //metin ekle
                .ilgileriMetinEkleTabAc()
                .ilgileriMetinEkleIlgiMetniDoldur(ilgileriAciklamaDosya3)
                .ilgileriMetinEkleEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgileriAciklamaDosya3, "Ilgi Metni")

                //sistemde kayıtlı evrak ekle
                //Evrak sayi1
                .sistemdeKayitliEvrakEkleTabAc()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(ilgileriEvrakSayisi1)
                .dokumanAra()
                .listelenenEvraklardaKontrol(ilgileriEvrakSayisi1)
                .evrakEkEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgileriEvrakSayisi1, "Evrak Sayısı1")

                //Evrak sayi2
                .evrakAramaDoldur(ilgileriEvrakSayisi2)
                .dokumanAra()
                .listelenenEvraklardaKontrol(ilgileriEvrakSayisi2)
                .evrakEkEkle()
                .listelenenIlgilerdeDosyanınGeldigiKontrolu(ilgileriEvrakSayisi2, "Evrak Sayısı2")

                .ilgiEkListesindeDetayGoster(ilgileriEvrakSayisi2)
                .evrakDetayiKontrol()
                .evrakDetayiSayfasınıKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        evrakOlusturPage
                .editorTabAc()
                .editordeIlgiKontrol(ilgileriAciklamaDosya2, "Dosya Adı")
                .editordeIlgiKontrol(ilgileriAciklamaDosya3, "Ilgi metni")
                .editordeIlgiKontrol(ilgileriEvrakSayisi1, "Evrak Sayısı1")
                .editordeIlgiKontrol(ilgileriEvrakSayisi2, "Evrak Sayısı2");

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali(basariMesaji);

        Selenide.sleep(3000);
        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN); //ztekin

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakEkleriTabAc()
                .ekeGoreDagitimYerleriAc(ekleriAciklamaDosya1)
                .dagitimYerleriKontrol()
                .dagitimYerleriKapat(ekleriAciklamaDosya1)
                .ekeGoreDagitimYerleriAc(fizikselEkAciklama)
                .dagitimYerleriKontrol()
                .dagitimYerleriKapat(fizikselEkAciklama)
                .ekeGoreDagitimYerleriAc(evrakSayisi1)
                .dagitimYerleriKontrol()
                .dagitimYerleriKapat(evrakSayisi1);

        evrakOlusturPage
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        postalanacakEvraklarPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu) //evrakKonusu
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu) //evrakKonusu
                .evrakPostala()
                .evrakPostalanacakYerlereGoreYazdir()
                .evrakDetaylariEvrakinEkleriKontrol(ekleriAciklamaDosya1) //ekleriAciklamaDosya1
                .evrakDetaylariEkranKapat()
                .dagitimYerineGorePostaSec(kurumPostalanakYerler, "Adi Posta")
                .postala() //BU adım test stepinde eksikti
                .dialogpostalaEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameSEZAICELIK, TestData.passwordSEZAICELIK); //sezaicelik

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrol(evrakKonusu, true)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabEvrakEkleriAc()
                .gelenEvrakEkleriKontrol(evrakSayisi1, "Localden eklenen dosya");

        login(TestData.usernameYAKYOL, TestData.passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontroluAllPages(evrakKonusu) //evrakKonusu //TS1493_EkIlgi_20180319172927
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu) //evrakKonusu //TS1493_EkIlgi_20180319172927
                .tabEvrakEkleriAc()

                .teslimEvrakEkleriKontrol(ekleriAciklamaDosya1, "Sistemden eklenen evrak") //Ekleri_Dosya1_20180319172927 //ekleriAciklamaDosya1
                .evrakSecIcerikGoster(evrakKonusu, true); //TS1493_EkIlgi_20180319172927

        evrakDetayiPage
                .evrakGoster()
                .frameDegistirme()
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim1(birim, "Ek-2 konulmadı, Ek-3 konulmadı")
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim2(kurum, "Ek-3 konulmadı")
                .eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim3(kullanici, "Ek-1 konulmadı, Ek-2 konulmadı");


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2025: Evrak önizlemede Evrak Ek/İlgi/İlişikler tablarında sistemden eklenen evrakların da gösterilmesi (akordeon pdf önizleme)")
    public void TS2025() {

        String konuKodu = "605.01";
        String evrakKonusu = "TS2025_EkIlgi_Senaryosu_" + getSysDate();
        String kaldirilacakKlasorler = "300.01.61";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kurum = "Baş Dramaturgluk";
        String aktifKullaniciParaflama = "Mehmet BOZDEMİR";
        String kullaniciParaflama = "Gökçe ŞAHİN";
        String kullaniciKoordine = "Zübeyde TEKİN";
        String kullaniciImzalama = "Yasemin Çakıl AKYOL";
        //String kullaniciDetail = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
        String kullaniciDetail = "YGD BHUPGMY";

        String ekleriEvrakSayisi = "6345202-010.01-11088";

        String ekleriAciklamaDosya1 = "Ekleri_Dosya1_" + getSysDate();
        String pathDosya1 = getUploadPath() + "TS2025_dosya1.jpeg";
        String dosyaAdi1 = "TS2025_dosya1.jpeg";

        String ilgileriEvrakSayisi = "6345202-010.01-11057";
        String ilgileriAciklamaDosya2 = "İlgileri_Dosya2_" + getSysDate();
        String pathDosya2 = getUploadPath() + "TS2025_dosya2.pdf";
        String dosyaAdi2 = "TS2025_dosya2.pdf";

        String iliskiliEvrakSayisi = "6345202-010.01-11121";
        String iliskiliAciklamaDosya3 = "İlişkili_Dosya3_" + getSysDate();
        String pathDosya3 = getUploadPath() + "TS2025_dosya3.jpg";
        String dosyaAdi3 = "TS2025_dosya3.jpg";

        String basariMesaji = "İşlem başarılıdır!";

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

                .kullanicilarDoldurWithDetail(kullaniciParaflama, kullaniciDetail)
                .kullaniciyaKullaniciTipiSec(kullaniciParaflama, "PARAFLAMA")

                .koordineliSec(true)
                .kullanicilarDoldurWithDetail(kullaniciKoordine, kullaniciDetail)

                .koordineliSec(true)
                .kullanicilarDoldurWithDetail(kullaniciImzalama, kullaniciDetail)
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
                .dosyaYukleneneKadarLoadingBekle()
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
                .listelenenEvraklaraDosyanınGeldigiKontrolu(iliskiliAciklamaDosya3, "Dosya Adı")

                .sistemdeKayitliEvrakEkleTabiniAc()
                .evrakAranacakYerSec("Birim Evrakları Ara")
                .evrakAramaDoldur(iliskiliEvrakSayisi)
                .dokumanAra()
                .listelenenEvraklardaKontrol(iliskiliEvrakSayisi)
                .evrakIlisikEkle()
                .listelenenEvraklaraDosyanınGeldigiKontrolu(iliskiliEvrakSayisi, "Evrak Sayısı")
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakPageButtons
                .parafla()
                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b")
                .tabIlisikBilgileriAc()
                .ilisikBilgieriAccordionKontrol()
                .ilisikBilgilerindeDetayButonuKontrol("DOSYA", "EVRAK");

        login(TestData.usernameGSAHIN, TestData.passwordGSAHIN); //mbozdemir

        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu);

        evrakOlusturPage
                .parafla();

        parafladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b")
                .tabIlisikBilgileriAc()
                .ilisikBilgieriAccordionKontrol()
                .ilisikBilgilerindeDetayButonuKontrol("DOSYA", "EVRAK");

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);

        koordineBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu);

        evrakOlusturPage
                .koordineParafla();

        koordineParafladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b")
                .tabIlisikBilgileriAc()
                .ilisikBilgieriAccordionKontrol()
                .ilisikBilgilerindeDetayButonuKontrol("DOSYA", "EVRAK");

        login(TestData.usernameYAKYOL, TestData.passwordYAKYOL);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu);

        evrakOlusturPage
                .evrakImzala();

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b")
                .tabIlisikBilgileriAc()
                .ilisikBilgileriAccordionKontrol()
                .ilisikBilgilerindeDetayButonuKontrol("DOSYA", "EVRAK");

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        postalanacakEvraklarPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")
                .tabIlgiBilgileriAc()
                .ilgiBilgileriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b")

                .evrakPostala()
                .dagitimSecTbl1("Adi Posta")
                .evrakPostalaPostala(true);

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontroluAllPages(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .tabKontrolleri()
                .tabEvrakEkleriAc()
                .evrakEkleriAccordionKontrol()
                .evrakEklerindeDetayButonuKontrol("EK-1", "EK-2")

                .tabIlgiBilgileriAc()
                .ilgiBilgieriAccordionKontrol()
                .ilgiBilgilerindeDetayButonuKontrol("a", "b");

    }
}
