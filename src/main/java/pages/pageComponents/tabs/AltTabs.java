package pages.pageComponents.tabs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.pageComponents.SearchTable;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class AltTabs {

    private SelenideElement tab;

    public AltTabs(SelenideElement tab) {
        this.tab = tab;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Alt Tabs
    ///////////////////////////////////////////////////////////////////////////
    //region Open Tabs
    @Step("Dosya Ekle tabı aç")
    public DosyaEkleTab dosyaEkleTabiAc() {
        return new DosyaEkleTab().openTab();
    }

    @Step("Fiziksel Ek Ekle tabı aç")
    public FizikselEkEkleTab fizikselEkEkleTabiAc() {
        return new FizikselEkEkleTab().openTab();
    }

    @Step("Sistemde Kayıtlı Evrak Ekle")
    public SistemdeKayitliEvrakEkleTab sistemdeKayitliEvrakEkleTabiAc() {
        return new SistemdeKayitliEvrakEkleTab().openTab();
    }

    @Step("Web Adresini Ekle tabı aç")
    public WebAdresiniEkleTab webAdresiniEkleTabiAc() {
        return new WebAdresiniEkleTab().openTab();
    }

    @Step("Arşivde Kayıtlı Evrak Ekle tabı aç")
    public ArsivdeKayitliEvrakEkleTab arsivdeKayitliEvrakEkleTabiAc() {
        return new ArsivdeKayitliEvrakEkleTab().openTab();
    }

    @Step("Metin Ekle tabı aç")
    public MetinEkleTab metinEkleTabiAc() {
        return new MetinEkleTab().openTab();
    }

    @Step("Tercüme Ekle tabı aç")
    public TercumeEkleTab tercumeEkleTabiAc() {
        return new TercumeEkleTab().openTab();
    }
    //endregion

    public class DosyaEkleTab {

        private final static String tabName = "Dosya Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private DosyaEkleTab openTab() {
            tab.$("a[href$='dosyaEkleTab']").click();
            altTab = tab.$("div[id$='dosyaEkleTab']");
            return this;
        }

        @Step("Ek Metni bul")
        public SelenideElement getEkMetniTextarea() {
            return altTab.$("textarea[id$='dosyaAciklama']");
        }

        @Step("Ek Metni doldur")
        public DosyaEkleTab ekMetniDoldur(String text) {
            getEkMetniTextarea().setValue(text);
            return this;
        }

        @Step("Tarama Havuzundan Ekle bul")
        public SelenideElement getTaramaHavuzundanEkleButton() {
            return altTab.$("button[id$='uploadFromTarananEvrakHavuzuEkA']");
        }

        @Step("Tarama Havuzundan Ekle butona tıkla")
        public DosyaEkleTab taramaHavuzundanEkleTikla() {
            getTaramaHavuzundanEkleButton().shouldBe(visible, enabled).click();
            return this;
        }

        @Step("Tarayıcıdan Ekle bul")
        public SelenideElement getTarayicidanEkleButton() {
            return altTab.$("button[id$='dogrudanTaramaBaslatEkA']");
        }

        @Step("Tarayıcıdan Ekle butona tıkla")
        public DosyaEkleTab tarayicidanEkleTikla() {
            getTarayicidanEkleButton().shouldBe(visible, enabled).click();
            return this;
        }

        @Step("Dosya Ekle alanı bul")
        public SelenideElement getDosyaEkleInput() {
            return altTab.$("input[id$='fileUploadButtonA_input']");
        }

        @Step("Dosya Ekle alanda dosyayı seç")
        public DosyaEkleTab dosyaEkleAlan(String fileName) {
            getDosyaEkleInput().uploadFromClasspath(fileName);
            tab.shouldHave(text(fileName));
            return this;
        }

        @Step("Dosya Ekle alanda dosyayı seç")
        public DosyaEkleTab dosyaEkleAlanFromCustomPath(String filePath) {
            File file = new File(filePath);
            getDosyaEkleInput().uploadFile(file);
            return this;
        }

        @Step("Dosya Ekle butonu bul")
        public SelenideElement getDosyaEkleButton() {
            return altTab.$("button[id$='dosyaEkleButton']");
        }

        @Step("Dosya Ekle butona tıkla")
        public DosyaEkleTab getDosyaEkleButonaTikla() {
            getDosyaEkleButton().click();
            return this;
        }
    }

    public class FizikselEkEkleTab {
        private final static String tabName = "Fiziksel Ek Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private FizikselEkEkleTab openTab() {
            tab.$("a[href$='aciklamaEkleTab']").click();
            altTab = tab.$("div[id$='aciklamaEkleTab']");
            return this;
        }

        @Step("Fiziksel Ek Metni bul")
        public SelenideElement getFizikselEkMetniTextarea() {
            return altTab.$("textarea[id$='aciklamaTextArea']");
        }

        @Step("Ek Metni doldur")
        public FizikselEkEkleTab fizikselEkMetniDoldur(String text) {
            getFizikselEkMetniTextarea().setValue(text);
            return this;
        }

        @Step("Dosya Ekle butonu bul")
        public SelenideElement getEkleButton() {
            return altTab.$("button[id$='aciklamaEkleButton']");
        }

        @Step("Dosya Ekle butona tikla")
        public FizikselEkEkleTab getDosyaEkleButonaTikla() {
            getEkleButton().click();
            return this;
        }
    }

    public class SistemdeKayitliEvrakEkleTab {
        private final static String tabName = "Sistemde Kayıtlı Evrak Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private SistemdeKayitliEvrakEkleTab openTab() {
            tab.$("a[href$='sistemdeKayitliEvragiEkleTab']").click();
            altTab = tab.$("div[id$='sistemdeKayitliEvragiEkleTab']");
            return this;
        }

        @Step("Dosya Ekle alanı bul")
        public SelenideElement getEvrakTarihiBasInput() {
            return altTab.$("input[id$='ekIslemleriEvrakTarihBasId_input']");
        }

        @Step("Evrak Tarihi başlangiç gir")
        public SistemdeKayitliEvrakEkleTab evrakTarihiBasGir(String tarih) {
            getEvrakTarihiBasInput().setValue(tarih);
            return this;
        }

        @Step("Dosya Ekle alanı bul")
        public SelenideElement getEvrakTarihiSonInput() {
            return altTab.$("input[id$='ekIslemleriEvrakTarihSonId_input']");
        }

        @Step("Evrak Tarihi başlangiç tarihi gir")
        public SistemdeKayitliEvrakEkleTab evrakTarihiSonGir(String tarih) {
            getEvrakTarihiSonInput().setValue(tarih);
            return this;
        }

        @Step("Evrakın Aranacağı Yer alanı bul")
        public SelenideElement getEvrakinAranacagiYerSelect() {
            return altTab.$("input[id$='ekIslemleriEvrakAramaAranacakYerId']");
        }

        @Step("Evrakın Aranacağı Yeri seç")
        public SistemdeKayitliEvrakEkleTab evrakinAranacagiYeriSec(String text) {
            getEvrakinAranacagiYerSelect().selectOption(text);
            return this;
        }

        @Step("Evrak Arama alanı bul")
        public SelenideElement getEvrakAramaInput() {
            return altTab.$("input[id$='evrakAramaText']");
        }

        @Step("Evrak Arama alanı doldur")
        public SistemdeKayitliEvrakEkleTab evrakAraDoldur(String text) {
            getEvrakAramaInput().setValue(text);
            return this;
        }

        @Step("Doküman Ara butonu bul")
        public SelenideElement getDokumanAraButton() {
            return altTab.$("button[id$='dokumanAraButton']");
        }

        @Step("Doküman Ara butona tıkla")
        public SistemdeKayitliEvrakEkleTab dokumanAraTikla() {
            getDokumanAraButton().click();
            return this;
        }

        @Step("Aranan Evrak tablosunda işlem yapılacak")
        public SearchTable getArananEvrakTablosu() {
            return new SearchTable($("div[id$='sistemdeKayitliEvrakListesiDataTable']"));
            //sistemdeKayitliEvrakListesiDataTable_data
        }

    }

    public class WebAdresiniEkleTab {
        private final static String tabName = "Web Adresini Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private WebAdresiniEkleTab openTab() {
            tab.$("a[href$='webAdresindenEkEkle']").click();
            altTab = tab.$("div[id$='webAdresindenEkEkle']");
            return this;
        }

        @Step("Ek Metni bul")
        public SelenideElement getEkMetniTextarea() {
            return altTab.$("textarea[id$='webAdresiAciklamaTextArea']");
        }

        @Step("Ek Metni doldur")
        public WebAdresiniEkleTab ekMetniDoldur(String text) {
            getEkMetniTextarea().setValue(text);
            return this;
        }

        @Step("Web Adresi alanı bul")
        public SelenideElement getWebAdresiInput() {
            return altTab.$("descendant::tr[descendant::label[normalize-space(.)='Web Adresi']]//input");
        }

        @Step("Web Adresi alanı doldur")
        public WebAdresiniEkleTab webAdresiAlan(String text) {
            getWebAdresiInput().setValue(text);
            return this;
        }

        @Step("Web Adresi Ekle butonu bul")
        public SelenideElement getWebAdresiEkleButton() {
            return altTab.$("button[id$='webAdresindenEkleButton']");
        }

        @Step("Web Adresi Ekle butona tıkla")
        public WebAdresiniEkleTab webAdresiEkleTikla() {
            getWebAdresiEkleButton().click();
            return this;
        }

        @Step("Dosya Ekle butonu bul")
        public SelenideElement getEkleButton() {
            return altTab.$("button[id$='webAdresindenEkEkleButton']");
        }

        @Step("Dosya Ekle butona tikla")
        public WebAdresiniEkleTab getDosyaEkleButonaTikla() {
            getEkleButton().click();
            return this;
        }

    }

    public class ArsivdeKayitliEvrakEkleTab {
        private final static String tabName = "Arşivde Kayıtlı Evrak Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private ArsivdeKayitliEvrakEkleTab openTab() {
            tab.$("a[href$='arsivdenEkEkleTabId']").click();
            altTab = tab.$("div[id$='arsivdenEkEkleTabId']");
            return this;
        }

        @Step("Konu alanı bul")
        public SelenideElement getKonuInput() {
            return altTab.$("input[id$='arsivdenEvrakAraKonuInputTextId']");
        }

        @Step("Konu alanı doldur")
        public ArsivdeKayitliEvrakEkleTab konuDoldur(String text) {
            getKonuInput().setValue(text);
            return this;
        }

        @Step("Kullanıcı alanı bul")
        public BelgenetElement getKullaniciCombolov() {
            return comboLov(altTab, "input[id$='kisiyeLov_id:LovText']");
        }

        @Step("Konu alanı doldur")
        public ArsivdeKayitliEvrakEkleTab kullaniciSec(String value, Condition... filterByTitle) {
            if (filterByTitle.length > 0) {
                ElementsCollection filtered = getKullaniciCombolov().type(value).getTitleItems();
                for (Condition condition : filterByTitle) {
                    filtered = filtered.filterBy(condition);
                }
                filtered.shouldHave(CollectionCondition.sizeGreaterThan(0)).first().click();
            } else
                getKullaniciCombolov().selectLov(value);

            return this;
        }

        @Step("Evrak Sayı alanı bul")
        public SelenideElement getEvrakSayiInput() {
            return altTab.$("input[id$='arsivdenEvrakAraSayiInputTextId']");
        }

        @Step("Evrak Sayı alanı doldur")
        public ArsivdeKayitliEvrakEkleTab evrakSayiDoldur(String text) {
            getEvrakSayiInput().setValue(text);
            return this;
        }

        @Step("Doküman Ara butonu bul")
        public SelenideElement getDokumanAraButton() {
            return altTab.$("button[id$='arsivdenEvrakAraButtonId']");
        }

        @Step("Doküman Ara butona tıkla")
        public ArsivdeKayitliEvrakEkleTab dokumanAraTikla() {
            getDokumanAraButton().click();
            return this;
        }

        @Step("Aranan Evrak tablosunda işlem yapılacak")
        public SearchTable getArananEvrakTablosu() {
            return new SearchTable($("div[id$='arsivdenEvrakAraListesiDataTable']"));
        }
    }

    public class MetinEkleTab {
        private final static String tabName = "Metin Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private MetinEkleTab openTab() {
            tab.$("a[href$='aciklamaEkleTab']").click();
            altTab = tab.$("div[id$='aciklamaEkleTab']");
            return this;
        }

        @Step("İlgi Metni bul")
        public SelenideElement getIlgiMetniTextarea() {
            return altTab.$("textarea[id$='aciklamaTextArea']");
        }

        @Step("İlgi Metni doldur")
        public MetinEkleTab ilgiMetniDoldur(String text) {
            getIlgiMetniTextarea().setValue(text);
            return this;
        }

        @Step("Ekle butonu bul")
        public SelenideElement getEkleButton() {
            return altTab.$("button[id$='aciklamaEkleButton']");
        }

        @Step("Ekle butona tikla")
        public MetinEkleTab getDosyaEkleButonaTikla() {
            getEkleButton().click();
            return this;
        }
    }

    public class TercumeEkleTab {
        private final static String tabName = "Tercüme Ekle";
        private SelenideElement altTab;

        @Step(tabName + " tabı aç")
        private TercumeEkleTab openTab() {
            tab.$("a[href$='tercumeEvragiEkleTab']").click();
            altTab = tab.$("div[id$='tercumeEvragiEkleTab']");
            return this;
        }

        @Step("İlişik Metni bul")
        public SelenideElement getIlisikMetniTextarea() {
            return altTab.$("textarea[id$='tercumeAciklama']");
        }

        @Step("İlişik Metni doldur")
        public TercumeEkleTab ilisikMetniDoldur(String text) {
            getIlisikMetniTextarea().setValue(text);
            return this;
        }

        @Step("Dosya Ekle alanı bul")
        public SelenideElement getDosyaEkleInput() {
            return altTab.$("input[id$='fileUploadButtonB_input']");
        }

        @Step("Dosya Ekle alanda dosyayı seç")
        public TercumeEkleTab dosyaEkleAlan(String fileName) {
            getDosyaEkleInput().uploadFromClasspath(fileName);
            tab.shouldHave(text(fileName));
            return this;
        }

        @Step("Dosya Ekle alanda dosyayı seç")
        public TercumeEkleTab dosyaEkleAlanFromCustomPath(String filePath) {
            File file = new File(filePath);
            getDosyaEkleInput().uploadFile(file);
            return this;
        }

        @Step("Ekle butonu bul")
        public SelenideElement getEkleButton() {
            return altTab.$("button[id$='tercumeEkleButton']");
        }

        @Step("Ekle butona tıkla")
        public TercumeEkleTab getDosyaEkleButonaTikla() {
            getEkleButton().click();
            return this;
        }
    }

}
