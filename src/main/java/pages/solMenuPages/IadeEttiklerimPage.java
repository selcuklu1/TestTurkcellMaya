package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class IadeEttiklerimPage extends MainPage {
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat = $("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");
    SelenideElement btnIadeEdilmistir = $("button[id^='mainInboxForm:inboxDataTable:0:j_idt']");
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    ElementsCollection tabEvrakGecmisi = $$("[id$='evrakOnizlemeTab'] ul li");
    SelenideElement tabOnizlemeKontrol = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    ElementsCollection tblParafImzacilarListesi = $$("tbody[id='mainInboxForm:imzaListesiDataTable_data'] > tr[role='row']");

    @Step("İade ettiklerim sayfası aç")
    public IadeEttiklerimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.IadeEttiklerim);
        return this;
    }

    @Step("Önizleme Tab Kontrolü")
    public IadeEttiklerimPage onizlemeTabKontrol() {
        Assert.assertEquals(tabOnizlemeKontrol.isDisplayed(),true,"Önizleme Tab Kontrolü");
        Allure.addAttachment("Önizleme Tab Kontrolü","açılmaktadır");
        return this;
    }

    @Step("Evrakın listelendiği görülür: {konu}")
    public IadeEttiklerimPage evrakSec(String konu) {
        tblEvraklar.filterBy(Condition.text(konu)).get(0).click();
        return this;
    }

    @Step("Evrakın listelendiği görülür: {konu}, {yer}, {tarih}")
    public IadeEttiklerimPage evrakGeldigiGorme(String konu, String yer, String tarih) {
        boolean durum = tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Iade Edilmiştir Ikon Kontrolü")
    public IadeEttiklerimPage iadeEdilmistirIkonKontrolu(String konu) {
        boolean durum = tblEvraklar.filterBy(Condition.text(konu)).get(0).$("button[id^='mainInboxForm:inboxDataTable:0:j_idt']").isDisplayed();
        return this;
    }

    @Step("Imzacıları Kontrolü ve Tıklama - Kurdele Ikon Kontrolü : {konu}")
    public IadeEttiklerimPage imzacilari(String konu) {
        tblEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .$("[id$='btnImzasiz']").click();
        return this;
    }

    @Step("Paraf/Imzacılar Listesi Kontrol: {user}")
    public IadeEttiklerimPage parafImzacilarListesiKontrol(String user,boolean status) {
        boolean durum = tblParafImzacilarListesi
                .filterBy(Condition.text(user)).size() > 0;
        Assert.assertEquals(durum,status,"Paraf/Imzacılar Listesi Kontrol:");
        Allure.addAttachment("Paraf/Imzacılar Listesi Kontrol:" + status,user);
        takeScreenshot();
        return this;
    }

    @Step("Paraf/Imzacılar Listesinde tarih ve durum kontrolü")
    public IadeEttiklerimPage parafImzacilarListesiKontrolTarihDurum() {
        boolean tarih = tblParafImzacilarListesi.get(0).$$("td[role='gridcell']").get(2).getText().equals("");
        boolean durum = tblParafImzacilarListesi.get(0).$$("td[role='gridcell']").get(3).getText().equals("");
        Assert.assertEquals(tarih,true,"Tarih Boş Kontrolü");
        Assert.assertEquals(durum,true,"Durum Boş Kontrolü");
        Allure.addAttachment("Paraf/Imzacılar Listesinde tarih ve durum boş kontrolü :" , " sonuç:" + tarih +" " + durum);
        return this;
    }


    @Step("Evrak geçmişi alanına tıklanır")
    public IadeEttiklerimPage secilenEvrakEvrakGecmisi() {
        tabEvrakGecmisi.filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol: \"{teslimAlinan}\" \"{islemSureci}\" \"{tarih}\"")
    public IadeEttiklerimPage evrakGecmisi(String teslimAlinan, String islemSureci, String tarih) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan))
                .filterBy(Condition.text(tarih)).size() > 0;
        Assert.assertEquals(durum, true,"Evrak Geçmişi Kontrol");
        Allure.addAttachment("Teslim Alinan:" + teslimAlinan + " İşlem Süreci:" + islemSureci + " Tarih:" +  tarih , "");
        takeScreenshot();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol: \"{teslimAlinan}\" \"{birim}\" \"{islemSureci}\" \"{tarih}\"")
    public IadeEttiklerimPage evrakGecmisi(String teslimAlinan, String birim, String islemSureci, String tarih) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(birim)).size() > 0;
        Assert.assertEquals(durum, true,"Evrak Geçmişi Kontrol");
        Allure.addAttachment("Teslim Alinan:" + teslimAlinan + "Birim:" + birim +" İşlem Süreci:" + islemSureci + " Tarih:" +  tarih , "");
        takeScreenshot();
        return this;
    }

    @Step("Kullancılar doldur")
    public IadeEttiklerimPage kullanicilarDoldur(String kullanicilar) {
        txtKullanicilar.type(kullanicilar).getTitleItems().filterBy(Condition.text(kullanicilar)).first().click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public IadeEttiklerimPage gizlilikRaporSec(String konu, String yer, String gidecegiYer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.$$("button[id^='mainInboxForm:inboxDataTable:']").get(0).click();
        return this;
    }

    public IadeEttiklerimPage takipListeKapat() {
        btnTakipListesiKapat.click();
        return this;
    }

}
