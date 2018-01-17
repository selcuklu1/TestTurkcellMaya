package tests.EkIlgi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
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

    @BeforeMethod
    public void beforeTests(Method method) {

        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        taslakEvraklarPage = new TaslakEvraklarPage();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TS2199: Evrak oluşturmada Ek ekleme (50 MB üzeri dosya ekleme)")
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

        String pathPDF = getDocPath() + "TS2199.pdf";
        String pathDOC = getDocPath() + "TS2199.doc";
        String pathDOCX = getDocPath() + "TS2199.docx";
        String pathXLS = getDocPath() + "TS2199.xls";
        String pathXLSX = getDocPath() + "TS2199.xlsx";
        String pathPPT = getDocPath() + "TS2199.ppt";
        String pathPPTX = getDocPath() + "TS2199.pptx";

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

        //TODO: Müşteriden yeni güncelleme bekleniyor.
/*        evrakOlusturPage
                .imzalaButonaTikla()
                .sImzalaRadioSec()
                .evrakImzaOnay()
                .islemMesaji().basariliOlmali(basariMesaji);*/
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
        String pathDosya3 = getDocPath() + "TS2346_dosya3.pdf";


        String ilisikDosya4Aciklama = "İlisik_Dosya4_"+getSysDate();
        String dosyaAdi4 = "TS2346_dosya4.pdf";
        String pathDosya4 = getDocPath() + "TS2346_dosya4.pdf";

        String ilisikDosya5Aciklama = "İlisik_Dosya5_"+getSysDate();
        String dosyaAdi5 = "TS2346_dosya5.pdf";
        String pathDosya5 = getDocPath() + "TS2346_dosya5.pdf";

        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.username4, TestData.password4); //mbozdemir

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
        String pathDosya3 = getDocPath() + "TS2348_dosya3.pdf";

        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.username4, TestData.password4); //mbozdemir

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
                .editordeEkKontrol(ekDosya1Aciklama)
                .editordeEkKontrol(ekDosya2Aciklama)
                .editordeEkKontrol(ekDosya3Aciklama)
                .editordeEkKontrol(fizikselEkAciklama)
                .editordeEkKontrol(evrakSayisi);

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
    @Test(enabled = false, description = "TS0956: Evrak oluşturmada ilgi ekleme")
    public void TS0956() {

        String evrakKonusu = "TS2348_EkIlgi_Senaryosu_"+getSysDate();

        String ilgiDosya1Aciklama = "İlgi_Dosya1_"+getSysDate();
        String ilgiDosya2Aciklama = "İlgi_Dosya2_"+getSysDate();
        String ilgiDosya3Aciklama = "İlgi_Dosya3_"+getSysDate();

        String dosyaAdi3 = "TS0956_dosya3.pdf";
        String pathDosya3 = getDocPath() + "TS0956_dosya3.pdf";

        String basariMesaji = "İşlem başarılıdır!";
        login(TestData.username4, TestData.password4); //mbozdemir

        //Taslaklar listesinde kontrol için unique konu giriliyor.
        evrakOlusturPage
                .openPage();
                //.bilgilerTabiAc()
                //.konuDoldur(evrakKonusu);

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
                .listelenenIlgilerdeIndırButonuKontrol(ilgiDosya2Aciklama);

/*                //ilgi3
                .ilgileriIlgiMetniDoldur(ilgiDosya3Aciklama)
                .dosyaEkle(pathDosya3, dosyaAdi3)
                .dosyaYukleneneKadarBekle()
                .ekleriEklenenDosyaAdiKontrol(dosyaAdi3)
                .ekleriEkle()
                .listelenenEklereDosyanınGeldigiKontrolu(dosyaAdi3, "Dosya Adı")
                .listelenenEklerdeIndırButonuKontrol(dosyaAdi3)*/
    }
}
