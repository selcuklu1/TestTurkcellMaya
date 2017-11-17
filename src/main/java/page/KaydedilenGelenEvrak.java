package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import drivers.Firefox;
import io.qameta.allure.Step;
import javafx.scene.control.ButtonType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.Credentials;
import pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.*;
import static pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvrak extends BaseLibrary {

    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim =  comboLov (By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt22375"));
    SelenideElement btnRaporAlPdf = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt22373"));

    @Step("Birim alanı doldur")
    public KaydedilenGelenEvrak birimDoldur(String birim) {
        cmbBirim.selectComboLov(birim);
        return this;
    }

    @Step("Geldiği yer seç")
    public KaydedilenGelenEvrak geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOptionByValue(geldigiYer);
        return this;
    }

    @Step("Gelen Evrak no alanını doldur")
    public KaydedilenGelenEvrak gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrak sorgula() {
        btnSorgula.click();
        return this;
    }
    @Step("Rapor al Excel")
    public KaydedilenGelenEvrak raporAlExcel() {
        btnRaporAlExcel.click();
        confirm();
        return this;
    }
    @Step("Rapor al PDF")
    public KaydedilenGelenEvrak raporAlPdf() {
        btnRaporAlPdf.click();
        confirm();
        return this;
    }

}
