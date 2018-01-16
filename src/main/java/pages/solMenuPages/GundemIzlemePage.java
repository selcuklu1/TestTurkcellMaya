package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import java.io.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class GundemIzlemePage extends MainPage {

    BelgenetElement txtKlasor = comboLov(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gundemKlasorLov:LovText"));
    SelenideElement btnFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gundemFiltrele"));
    ElementsCollection islemButonlar = $$("[id='mainInboxForm:inboxDataTable:inboxIslemlerToolbar'] tbody button[id^='mainInboxForm:inboxDataTable:']");
    ElementsCollection tblGundemEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnGundemSirasiniKaydetUyariEvet = $(By.id("mainInboxForm:gundemSiraDegistirEvetButton"));
    SelenideElement btnGundemSirasiniKaydetUyariHayir = $(By.id("mainInboxForm:gundemSiraDegistirHayirButton"));
    SelenideElement btnYayimla = $(By.id("mainInboxForm:inboxDataTable:j_idt699"));
    SelenideElement btnYayimlaEvet = $(By.id("mainInboxForm:tekrarYayimlaEvetButton"));

    @Step("Kurul işlemleri sayfası aç")
    public GundemIzlemePage openPage() {
        solMenu(SolMenuData.KurulIslemleri.GundemIzleme);
        return this;
    }

    @Step("Klasor seç")
    public GundemIzlemePage kapatilanKlasorSec(String klasor) {
        txtKlasor.selectLov(klasor);
        btnFiltrele.click();
        return this;
    }

    @Step("Sıralamayı değiştir")
    public GundemIzlemePage siralamayiDegistir() {
        System.out.println(tblGundemEvraklar.size());
        if (tblGundemEvraklar.size() > 1) {
            String deger1 = $("[id='mainInboxForm:inboxDataTable:1:evrakTable'] input[id^='mainInboxForm:inboxDataTable:1']").getValue();
            String deger2 = $("[id='mainInboxForm:inboxDataTable:0:evrakTable'] input[id^='mainInboxForm:inboxDataTable:0']").getValue();
            $("[id='mainInboxForm:inboxDataTable:0:evrakTable'] input[id^='mainInboxForm:inboxDataTable:0']").setValue(deger1);
            $("[id='mainInboxForm:inboxDataTable:1:evrakTable'] input[id^='mainInboxForm:inboxDataTable:1']").setValue(deger2);
        }
        return this;
    }

    @Step("Gündem sırasını kaydet")
    public GundemIzlemePage gundemSirasiniKaydet(boolean secim) {
        islemButonlar.get(3).click();
        clickJs(btnGundemSirasiniKaydetUyariEvet);
        return this;
    }

    @Step("Aralikli gündem oluştur")
    public GundemIzlemePage aralikliGundemOlustur() {
        islemButonlar.get(0).click();
/*
        try {
            Robot robotobj = new Robot();
            robotobj.keyPress(KeyEvent.VK_TAB);
            robotobj.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
  */
        return this;
    }

    @Step("Yayımla")
    public GundemIzlemePage yayimla() {
        islemButonlar.get(2).click();
        clickJs(btnYayimlaEvet);
        return this;
    }

    public String indirilenDosyaAd() {
        int i = 0;
        while (i < 140) {
            sleep(i);
            i++;
        }

        //İndirilen file name çeker
        // File root = new File("C://users//" + System.getProperty("user.name") + "//Downloads//");
        //Windows makine için sabit url
        File root = new File("C://users//optiim//Downloads//");
        FilenameFilter beginswithm = new FilenameFilter() {
            public boolean accept(File directory, String filename) {
                return filename.matches("Rapor_.*\\.docx");
            }
        };

        File[] files = root.listFiles(beginswithm);
        for (File f : files) {
            System.out.println(f);
        }

        String filePath = files[0].getPath();


        return filePath;
    }

    @Step("Klasör doldur")
    public GundemIzlemePage klasorDoldur(String klasor) {
        txtKlasor.selectLov(klasor);
        return this;
    }

    @Step("Sıralama kontrol edilir")
    public GundemIzlemePage wordDosyaKontrolEt(String dosyaAdi) {

        File file = new File(dosyaAdi);
        FileInputStream fis = null;
        int counter = 0;

        try {
            fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            List<XWPFTable> tables = doc.getTables();
            int deger = 0;
            for (XWPFTable table : tables) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {

                        if (cell.getText().length() > 0) {

                            if (counter % 2 == 0)
                                System.out.print(cell.getText());
                            else {
                                //Kontrol alanı
                                //Sıra numarası
                                String siraNo = $("[id='mainInboxForm:inboxDataTable:" + deger + ":evrakTable'] input[id^='mainInboxForm:inboxDataTable:" + deger + "']").getValue();
                                //Başlık çek
                                String baslik = $("[id='mainInboxForm:inboxDataTable:" + deger + ":evrakTable'] td[class^='ui-inbox-satir1'] div").getText();
                                String[] konu = baslik.split("Konu: ");
                                //Geldiği yer çek
                                String yer = $("[id='mainInboxForm:inboxDataTable:" + deger + ":evrakTable'] td[class^='ui-inbox-satir2'] div").getText();
                                String[] geldigiYer = yer.split(" / ");
                                //Kayıt tarihi çek
                                String kayitTarihi = $("[id='mainInboxForm:inboxDataTable:" + deger + ":evrakTable'] td[class^='ui-inbox-satir3'] div").getText();
                                String[] tarihi = kayitTarihi.split("Kayıt Tarihi: ");
                                String[] tarih = tarihi[1].split("/");
                                boolean siraNoDurum = cell.getText().contains(siraNo);
                                if (siraNoDurum == true) System.out.println(siraNo + "siraNo İçerisinde bulunuyor");
                                boolean konuDurum = cell.getText().contains(konu[1]);
                                if (konuDurum == true) System.out.println(konu[1] + " İçerisinde bulunuyor");
                                boolean geldigiYerDurum = cell.getText().contains(geldigiYer[1]);
                                if (geldigiYerDurum == true)
                                    System.out.println(geldigiYer[1] + " İçerisinde bulunuyor");
                                boolean tarihDurum = cell.getText().contains(tarih[0]);
                                if (tarihDurum == true) System.out.println(tarih[0] + " İçerisinde bulunuyor");
                                deger++;
                                if (deger % 10 == 0) {
                                    if ($$("[id='mainInboxForm:inboxDataTable_paginator_top'] span[class='ui-paginator-next ui-state-default ui-corner-all']").size() == 1) {
                                        clickJs($$("[id='mainInboxForm:inboxDataTable_paginator_top'] span[class='ui-paginator-next ui-state-default ui-corner-all']").get(0));
                                    }
                                }
                            }
                        }
                        counter++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        File folder = new File("C://users//" + System.getProperty("user.name") + "//Downloads//");
        final File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir,
                                  final String name) {
                return name.matches("Rapor_.*\\.docx");
            }
        });
        for (File file1 : files) {
            if (!file1.delete()) {
                System.err.println("Can't remove " + file1.getAbsolutePath());
            }
        }


        return this;
    }

}
