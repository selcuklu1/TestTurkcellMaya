package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class OlurYazisiOlusturPage extends MainPage {

    SelenideElement tabBilgiler = $("button .kullaniciBilgileri");

    //region Tabs local variables
    private OlurYazisiOlusturPage.BilgilerTab bilgilerTab = new OlurYazisiOlusturPage.BilgilerTab();

    //endregion

    @Step("Olur yazısı oluştur sayfasını aç")
    public OlurYazisiOlusturPage openPage() {
        new UstMenu().ustMenu("Olur Yazısı Oluştur");
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


        //endregion

        private OlurYazisiOlusturPage.BilgilerTab open() {
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

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            boolean selectable = comboLov(cmbOnayAkisiBy).isLovValueSelectable(onayAkisi);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }


    }








}
