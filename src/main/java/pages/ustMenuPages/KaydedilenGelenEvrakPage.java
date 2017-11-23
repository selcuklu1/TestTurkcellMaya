package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvrakPage extends MainPage {

    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim =  comboLov (By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt22375"));
    SelenideElement btnRaporAlPdf = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt22373"));

    public KaydedilenGelenEvrakPage openPage() {
        ustMenu("Raporlar","Kaydedilen Gelen Evrak");
        return this;
    }

    @Step("Birim alanı doldur")
    public KaydedilenGelenEvrakPage birimDoldur(String birim) {
        cmbBirim.selectComboLov(birim);
        return this;
    }

    @Step("Geldiği yer seç")
    public KaydedilenGelenEvrakPage geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOptionByValue(geldigiYer);
        return this;
    }

    @Step("Gelen Evrak no alanını doldur")
    public KaydedilenGelenEvrakPage gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrakPage sorgula() {
        btnSorgula.click();
        return this;
    }
    @Step("Rapor al Excel")
    public KaydedilenGelenEvrakPage raporAlExcel() {
        btnRaporAlExcel.click();
        confirm();
        return this;
    }
    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage raporAlPdf() {
        btnRaporAlPdf.click();
        confirm();
        return this;
    }
    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage txtClear() {
        txtEvrakKayitNo.clear();
        return this;
    }

}
