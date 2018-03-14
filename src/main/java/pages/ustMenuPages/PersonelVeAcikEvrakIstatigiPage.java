package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class PersonelVeAcikEvrakIstatigiPage extends MainPage {

    BelgenetElement txtPersonel = comboLov(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:personelLovId:LovText"));
    SelenideElement btnSorgulaPersonelIstatigi = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:personelIstatistikleriSorgulaId"));
    SelenideElement txtBaslangicTarihiPeronelTab = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:basTarId_input"));
    SelenideElement txtBitisTarihiPeronelTab = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:bitTarId_input"));
    SelenideElement btnPersonelTabiTarihAraligiAra = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:tarAraId"));
    SelenideElement lblKlasoreKaldirdiklari = $x("//div[@id='personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:personelPasifInfoPanel_content']//td[contains(., 'Klasöre Kaldırdıkları')]/../td[3]/label");
    SelenideElement lblCevapsizKapatilanlar = $x("//div[@id='personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:personelPasifInfoPanel_content']//td[contains(., 'Cevapsız Kapatılanlar')]/../td[3]/label");
    SelenideElement btnKlasoreKaldirdiklariEvrakGoster = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:klasoreKaldirdiklariId"));
    SelenideElement btnCevapsizKapatilanlarEvrakGoster = $(By.id("personelBirimEvrakIstatistikYonetimiTabView:personelIstatistikleriForm:cevapsizKapatilanlarId"));

    ElementsCollection tblKlasoreKaldirilanlar = $$("tbody[id='evrakListDataTable_data'] > tr[role='row']");

    @Step("Personel ve Açık evrak İstatiği sayfasını aç")
    public PersonelVeAcikEvrakIstatigiPage openPage() {
        ustMenu(UstMenuData.Raporlar.PersonelveAcikEvrakIstatistigi);
        return this;
    }

    @Step("Personel alanını {personel} olarak doldur")
    public PersonelVeAcikEvrakIstatigiPage personelDoldur(String personel){
        txtPersonel.selectLov(personel);
        return this;
    }

    @Step("Personel İstatiği tabında Sorgula butonuna tıkla.")
    public PersonelVeAcikEvrakIstatigiPage personelIstatigiSorgula(){
        btnSorgulaPersonelIstatigi.click();
        return this;
    }

    @Step("Tarih aralığı doldur: {baslangic} - {bitis}. Ara butonuna tıkla.")
    public PersonelVeAcikEvrakIstatigiPage personelTabTarihAraligiDoldurVeAra(String baslangic, String bitis){

        txtBaslangicTarihiPeronelTab.setValue(baslangic);
        txtBitisTarihiPeronelTab.setValue(bitis);
        btnPersonelTabiTarihAraligiAra.click();

        return this;
    }
    
    @Step("Cevapsız Kapatılan alanında 0 dan büyük değer olduğu görülür.")
    public PersonelVeAcikEvrakIstatigiPage cevapsizKapatilanlarKontrol(){
        String cevapsizKapatilanlarText = lblCevapsizKapatilanlar.getText().trim();
        int cevapsizKapatilanlar = Integer.parseInt(cevapsizKapatilanlarText);
        boolean status = false;

        if(cevapsizKapatilanlar > 0)
            status = true;

        Assert.assertEquals(status, true, "Cevapsız Kapatılanlar alanında değer kontrolü");
        Allure.addAttachment("Cevapsız Kapatılanlar", cevapsizKapatilanlarText + " adet");
        return this;
    }

    @Step("Klasöre Kaldırdıkları alanında 0 dan büyük değer olduğu görülür.")
    public PersonelVeAcikEvrakIstatigiPage klasoreKaldirdiklariKontrol(){
        String klasoreKaldirdiklariText = lblKlasoreKaldirdiklari.getText().trim();
        int klasoreKaldirdiklari = Integer.parseInt(klasoreKaldirdiklariText);
        boolean status = false;

        if(klasoreKaldirdiklari > 0)
            status = true;

        Assert.assertEquals(status, true, "Kalsöre Kaldırdıkları alanında değer kontrolü");
        Allure.addAttachment("Klasöre Kaldırdıkları", klasoreKaldirdiklariText + " adet");
        return this;
    }
    
    @Step("Klasöre Kaldiirilanlar alanında evrak göster butonuna basılır.")
    public PersonelVeAcikEvrakIstatigiPage klasoreKaldirilanlarEvrakGoster(){
        btnKlasoreKaldirdiklariEvrakGoster.click();
        return this;
    }

    @Step("Cevapsız Kapatılanlar alanında evrak göster butonuna basılır.")
    public PersonelVeAcikEvrakIstatigiPage cevapsizKapatilanlarEvrakGoster(){
        btnCevapsizKapatilanlarEvrakGoster.click();
        return this;
    }

    @Step("Klasöre Kaldırılanlar listesinde {konu} konulu evrak olmalı mı? : {evrakOlmali}")
    public PersonelVeAcikEvrakIstatigiPage klasoreKaldirilanlarEvrakKontrol(String konu, boolean evrakOlmali){

        if(evrakOlmali == true){

            tblKlasoreKaldirilanlar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldBe(Condition.visible);

        } else {

            tblKlasoreKaldirilanlar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldNotBe(Condition.visible);

        }

        $x("//span[@id='evrakListDialogOutputPanel']//span[contains(@class, 'closethick')]/..").click();

        return this;
    }

    @Step("Cevapsız Kapatılanlar listesinde {konu} konulu evrak olmalı mı? : {evrakOlmali}")
    public PersonelVeAcikEvrakIstatigiPage cevapsizKapatilanlarEvrakKontrol(String konu, boolean evrakOlmali){

        if(evrakOlmali == true){

            tblKlasoreKaldirilanlar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldBe(Condition.visible);

        } else {

            tblKlasoreKaldirilanlar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldNotBe(Condition.visible);

        }

        $x("//span[@id='evrakListDialogOutputPanel']//span[contains(@class, 'closethick')]/..").click();

        return this;
    }







}

