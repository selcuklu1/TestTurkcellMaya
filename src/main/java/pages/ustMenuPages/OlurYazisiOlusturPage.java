package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class OlurYazisiOlusturPage extends MainPage {

    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    private EditorTab editorTab = new EditorTab();
    //endregion

    SelenideElement tabBilgiler = $("button[id^='yeniOnayEvrakForm:onayEvrakLeftTab:uiRepeat'] span[class$='kullaniciBilgileri']");
    SelenideElement tabEditor = $("button .editor");

    @Step("Olur yazısı oluştur sayfasını aç")
    public OlurYazisiOlusturPage openPage() {
        ustMenu("Olur Yazısı Oluştur");
        return this;
    }

    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    public class BilgilerTab extends MainPage {

        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");

        // Onay Akışı Elementleri
        SelenideElement btnOnayAkisiEkle = $("#yeniOnayEvrakForm button[id*='onayAkisiEkle']");
        ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
        ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");
        SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:LovText']");
        SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisLov:lovTreePanelKapat']");
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement cmbSelectOneMenu = $(By.id("yeniOnayEvrakForm:evrakBilgileriList:14:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnOnayAkisGuncelle = $(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList:14:akisLov:j_idt'] [class$='update-icon']"));


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
            cmbOnayAkisi.shouldBe(visible);
            cmbOnayAkisi.selectLov(kullanici);
            return this;
        }

        @Step("Seçilen onay akışı detail kontrolu: \"{secim}\" ")
        public BilgilerTab onayAkisiDetailKontrol(String secim) {
            System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(secim), true);
            return this;
        }

        @Step("Seçilen onay akışı title kontrolu: \"{secim}\" ")
        public BilgilerTab onayAkisiTitleKontrol(String secim) {
            System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(secim), true);
            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            comboLov(cmbOnayAkisiBy).type(onayAkisi).titleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

        @Step("Onay akışı güncelle")
        public BilgilerTab onayAkisiGuncelle() {
            btnOnayAkisGuncelle.click();
            return this;
        }


    }
    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class EditorTab extends MainPage {
        public TextEditor getEditor() {
            return editor;
        }

        private TextEditor editor = new TextEditor();

        private EditorTab open() {
            tabEditor.click();
            return this;

        }
    }

}
