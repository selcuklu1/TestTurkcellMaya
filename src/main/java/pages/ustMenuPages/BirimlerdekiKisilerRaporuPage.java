package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class BirimlerdekiKisilerRaporuPage extends MainPage {

    BelgenetElement cmlBirim = comboLov(By.id("birimdekiKisilerRaporuForm:birimLov_id:LovText"));
    BelgenetElement txtBirim = comboLov(By.id("birimdekiKisilerRaporuForm:birimLov_id:LovText"));
    SelenideElement cmbDurum = $(By.id("birimdekiKisilerRaporuForm:evrakTipiId"));
    SelenideElement chkAltBirimiOlmayanlar = $(By.id("birimdekiKisilerRaporuForm:altBirimDahilId"));
    SelenideElement btnSorgula = $(By.id("birimdekiKisilerRaporuForm:sorgulaButton"));
    SelenideElement strKullaniciSayisi = $(By.xpath("//*[@id='birimdekiKisilerRaporuForm:birimdekiKisilerDataTable']/table/tfoot/tr[2]/td"));

    @Step("Birimlerdeki Kişiler Raporu sayfası aç")
    public BirimlerdekiKisilerRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.BirimlerdekiKisilerRaporu);
        return this;
    }

    @Step("Birimin set edilmiş olduğu kontrolu")
    public BirimlerdekiKisilerRaporuPage birimKontrol(String birim) {
        cmlBirim.getSelectedTitles().last().shouldHave(text(birim));

        return this;
    }

    @Step("Birim doldur")
    public BirimlerdekiKisilerRaporuPage birimDoldur(String birim) {
        txtBirim.clearAllSelectedItems();
        txtBirim.selectLov(birim);
        return this;
    }

    @Step("Durum seç")
    public BirimlerdekiKisilerRaporuPage durumSec(String secim) {
        cmbDurum.selectOption(secim);
        return this;
    }

    @Step("Alt Birimler Seç")
    public BirimlerdekiKisilerRaporuPage altBirimlerDahilSec(boolean value) {
        chkAltBirimiOlmayanlar.setSelected(value);
        return this;
    }

    @Step("Sorgula")
    public BirimlerdekiKisilerRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Birimin set edilmiş olduğu kontrolu : {kullaniciSayisi}")
    public String kullaniciSayisiniAl() {

        String kullaniciSayisi = strKullaniciSayisi.getText();

        String[] parts = kullaniciSayisi.split("Toplam ");// "004: 034556"
        String part1 = parts[1]; // 034556

        return part1;
    }

    @Step("Kullanıcıların TCKN, ad soyad ve birim bilgileri ile listelendiği görülür")
    public BirimlerdekiKisilerRaporuPage kullaniciListesiGeldigiGorme() {
        boolean durum = $$("[id='birimdekiKisilerRaporuForm:birimdekiKisilerDataTable_data'] tr").size() == 0;
        Assert.assertEquals(durum, false);
        takeScreenshot();
        return this;
    }

    @Step("Kullanıcı sayısının 3.adımla aynı olduğu görülür")
    public BirimlerdekiKisilerRaporuPage kullaniciSayilarininAyniOldugunuGorme(String kullaniciSayisi1, String kullaniciSayisi2) {

        Assert.assertEquals(kullaniciSayisi1.equals(kullaniciSayisi2), true);

        return this;
    }
}
