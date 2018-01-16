package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class IadeEttiklerimPage extends MainPage {
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat = $("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");

    @Step("İade ettiklerim sayfası aç")
    public IadeEttiklerimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.IadeEttiklerim);
        return this;
    }

    @Step("Kullancılar doldur")
    public IadeEttiklerimPage kullanicilarDoldur(String kullanicilar) {
        txtKullanicilar.selectLov(kullanicilar);
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
