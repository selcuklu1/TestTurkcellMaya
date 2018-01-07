package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class OlurYazisiOlusturPage extends MainPage {

    SelenideElement tabBilgiler = $("button[id^='yeniOnayEvrakForm'] span[class$='kullaniciBilgileri']");
    //SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
    SelenideElement tabEditor = $("button .editor");
    //endregion
    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    private EditorTab editorTab = new EditorTab();

    @Step("Olur Yazısı Oluştur sayfasını aç")
    public OlurYazisiOlusturPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.OlurYazisiOlustur);
        return this;
    }

    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class BilgilerTab extends MainPage {

        SelenideElement divContainer = $("[id='yeniOnayEvrakForm' ] [id='evrakBilgileriContainerDiv']");

        // Onay Akışı Elementleri
        SelenideElement btnOnayAkisiEkle = $("#yeniOnayEvrakForm button[id*='onayAkisiEkle']");
        ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
        ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");
        SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:LovText']");
        SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisLov:lovTreePanelKapat']");
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        BelgenetElement cmbKullanici = comboLov(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisAdimLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement cmbSelectOneMenu = $(By.id("yeniOnayEvrakForm:evrakBilgileriList:14:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnOnayAkisGuncelle = $(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList:14:akisLov:j_idt'] [class$='update-icon']"));
        SelenideElement btnKullan = $("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='anlikAkisKullanButton']");

        //endregion

        private BilgilerTab open() {
            if (divContainer.is(not(visible)))
                tabBilgiler.click();

            //divContainer.shouldBe(visible);
            return this;
        }

        public boolean isOnTabPage() {
            return divContainer.is(visible);
        }

        @Step("Onay Akışı Ekle")
        public BilgilerTab onayAkisiEkle() {
            btnOnayAkisiEkle.click();
            return this;
        }

        @Step("Onay akışında güncel gelen kullanıcıyı kontrol et")
        public BilgilerTab onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .shouldHave(value(kullaniciTipi));
            return this;
        }

        @Step("Onay akışı kullanıcı adı ve koordine tipi kontrol et")
        public BilgilerTab onayAkisiKullaniciKoordineKontrol(String kullaniciAdi, String kullaniciTipi) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$(("[id^='yeniOnayEvrakForm:evrakBilgileriList'] [class='lovItemDetail']"))
                    .text().contains(kullaniciTipi);

            return this;
        }

        @Step("Onay akışı listesinde listelenen kullanıcıyı kontrol et")
        public BilgilerTab onayAkisiTreeKullaniciKontrol(String kullaniciAdi, Boolean exist) {
            txtOnayAkisiKullanicilarInput.setValue(kullaniciAdi);
            if (exist == true)
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(Condition.exist);
            else
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(not(Condition.exist));

            if (btnOnayAkisiPanelKapat.isDisplayed())
                btnOnayAkisiPanelKapat.click();
            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisDoldur(String kullanici) {
            cmbOnayAkisi.selectLov(kullanici);
            return this;
        }

        @Step("Onay akışı kullanıcı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiKullaniciDoldur(String kullanici) {
            cmbKullanici.shouldBe(visible);
            cmbKullanici.selectLov(kullanici);
            return this;
        }


        @Step("Seçilen onay akışı detail kontrolu: \"{onayAkisiDetail}\" ")
        public BilgilerTab onayAkisiDetailKontrol(String onayAkisiDetail) {
            /*System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(onayAkisiDetail), true);*/
            cmbOnayAkisi.getSelectedDetails().last().shouldHave(text(onayAkisiDetail));
            return this;
        }

        @Step("Seçilen onay akışı title kontrolu: \"{onayAkisiTitle}\" ")
        public BilgilerTab onayAkisiTitleKontrol(String onayAkisiTitle) {
            /*System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(onayAkisiTitle), true);*/
            cmbOnayAkisi.getSelectedTitles().last().shouldHave(text(onayAkisiTitle));
            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            if (cmbOnayAkisi.isLovSelected() == true) {
                cmbOnayAkisi.clearLastSelectedItem();
            }

            comboLov(cmbOnayAkisiBy).type(onayAkisi).getTitleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            comboLov(cmbOnayAkisiBy).closeTreePanel();
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

        @Step("Onay akışı güncelle")
        public BilgilerTab onayAkisiGuncelle() {
            btnOnayAkisGuncelle.shouldBe(visible);
            clickJs(btnOnayAkisGuncelle);
            return this;
        }

        @Step("Kullaniciya kullanici tipi sec")
        public BilgilerTab kullaniciyaKullaniciTipiSec(String kullanici, String secimTipi) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .selectOptionByValue(secimTipi);

            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan() {
            clickJs(btnKullan);
            return this;
        }


    }

    public class EditorTab extends MainPage {
        private TextEditor editor = new TextEditor();

        public TextEditor getEditor() {
            return editor;
        }

        private EditorTab open() {
            tabEditor.click();
            return this;

        }
    }

}
