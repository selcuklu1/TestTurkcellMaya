package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BaseLibrary {
    SelenideElement mainPageLeftContainer = $("#mainInboxForm");
    SelenideElement mainPageLeftContainerDataTable = $("#mainInboxForm\\:inboxDataTable");

    public SelenideElement getMainPageLeftContainer() {
        return mainPageLeftContainer;
    }

    public SelenideElement getMainPageLeftContainerDataTable() {
        return mainPageLeftContainerDataTable;
    }

    public SearchTable searchTable() {
        return new SearchTable(mainPageLeftContainerDataTable);
    }

    public Filtreler filter() {
        return new Filtreler();
    }

    public void ustMenu(Enum ustMenuData, boolean... useJS) {
        new UstMenu().openMenu(ustMenuData, useJS);
    }

    public void solMenu(Enum solMenuData, boolean... useJS) {
        new SolMenu().openMenu(solMenuData, useJS);
    }

    public MainPage solMenu(Enum solMenuData) {
        return new SolMenu().openMenu(solMenuData);
    }

    public MainPage solMenu2(Enum solMenuData) {
        return new SolMenu().openMenu2(solMenuData);
    }

    public IslemMesajlari islemMesaji() {
        return new IslemMesajlari();
    }

    @Step("Kep bağlantısı alanı aç")
    public MainPage kepBaglantisi() {
        $(By.id("topMenuForm:userMenuButton_button")).click();
        $(By.id("topMenuForm:kepLoginButton")).click();
        return this;
    }

    @Step("Bağlan")
    public MainPage kepAdresBaglantisiBaglan1() {
        $("[id^='kepForm:kayitliKepDataTable:0:j_idt235']").click();
        return this;
    }

    @Step("Kullanıcı Adı ve TC Kimlik No alanlarında kullanıcının verileri görüntülenir")
    public MainPage kullaniciAdiVeTCKimlikNoLoginOlunanKullaniciGeldigiGorme() {
        Boolean durum1 = $("[id$='kullaniciAdi']").shouldBe(visible).exists() == true;
        Boolean durum2 = $("[id$='tcKimlikNo']").shouldBe(visible).exists() == true;
        Assert.assertEquals(durum1, durum2);
        takeScreenshot();
        return this;
    }

    public MainPage kepAdresBaglantisiBaglan2() {
        $("[id='kepForm:kayitliKepDataTable:1:j_idt235']").click();
        return this;
    }

    @Step("{kep} kep olan bağlan tıklanır")
    public MainPage kepAdresleriBaglan(String kep) {
        $$("[id='kepForm:kayitliKepDataTable_data'] tr").filterBy(Condition.text(kep))
                .get(0).$("button").click();
        return this;
    }

    @Step("Kullanıcı adı ve Tc Kimlik no alanlarındaki bilgileri değiştirilir - Değiştirilemez olduğu görülür")
    public MainPage kullaniciAdiTcKimlikNoKontol() {
        $(By.id("kepLogin2FormId:kullaniciAdi")).shouldBe(Condition.disabled);
        $(By.id("kepLogin2FormId:tcKimlikNo")).shouldBe(Condition.disabled);
        return this;
    }

    @Step("Parola doldur")
    public MainPage parolaDoldur(String parola) {
        $(By.id("kepLogin2FormId:parola")).setValue(parola);
        return this;
    }

    @Step("Şifre Doldur")
    public MainPage sifreDoldur(String sifre) {
        $(By.id("kepLogin2FormId:sifre")).setValue(sifre);
        return this;
    }

    @Step("Bağlan")
    public MainPage kepBaglantisiBaglan() {
        $(By.id("kepLogin2FormId:j_idt255")).click();
        return this;
    }

    @Step("Çıkış yap")
    public void logout() {
        $("button[id='topMenuForm:userMenuButton_button']").click();
        $("#topMenuForm\\:logOutButton").click();

        for (int i = 0; i < 5; i++) {
            if (getIslemOnayDialog().is(visible))
                getIslemOnayDialog().$x("descendant::button[.='Evet']").click();
            sleep(1000);
        }
    }

    public SelenideElement getIslemOnayDialog() {
        return $x("//div[@id='baseConfirmationDialog:dialog']");
    }


    public MainPage ustMenuEvrakIslemleriAc() {
        $(By.id("topMenuForm2:ust:0:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuKullaniciIslemleri() {
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:3:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuRaporlar() {
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:6:ustMenuEleman")).click();
        return this;
    }

    @Step("Vekalet var uyarı popup")
    public MainPage vekaletVarUyariPopUp() {
        SelenideElement popUpAktifVekaletUyarı = $(By.id("aktifVekaletinizVarUyariMesajiDialog"));
        SelenideElement btnTamam = $(By.id("aktifVekaletinizVarUyariMesajiDialogEvetBtn"));
        popUpAktifVekaletUyarı.exists();
        btnTamam.click();
        return this;
    }

    @Step("Birim Seç")
    public MainPage birimSec(Condition condition, boolean... selectAnyway) {
        SelenideElement currentBirim = $("#kullaniciBirimAd").shouldBe(visible)
                .shouldHave(matchText(".*"));
        //String currentBirim = $("#kullaniciBirimAd").shouldBe(visible).shouldHave(matchText(".*")).text();

        if (currentBirim.has(condition)
                && (!$("#birimlerimMenusuContainer a.ui-menuitem-selected").exists() || $("#birimlerimMenusuContainer a.ui-menuitem-selected").has(condition))
                && !(selectAnyway.length > 0 ? selectAnyway[0] : false))
            return this;

        $$("#leftMenuForm #birimlerimMenusuContainer a")
                .filterBy(condition).shouldHave(sizeGreaterThan(0))
                .first().click();

        Allure.addAttachment("Birim Adı : ", $$("#leftMenuForm #birimlerimMenusuContainer a")
                .filterBy(condition).first().getText());
        //$("#leftMenuForm #birimlerimMenusuContainer").$(byLinkText(birim)).click();

        //$("#kullaniciBirimAd").shouldHave(condition);
        return this;
    }

    @Step("Şuanki Birim kontrolü")
    public MainPage currentBirimKontrol(Condition condition) {
        $("#kullaniciBirimAd").shouldHave(condition);
        return this;
    }

    public ConfirmDialog confirmDialog() {
        return new ConfirmDialog();
    }

    public ElementsCollection getPageCloseButtons() {
        return $$("div[id^='window'][id$='Dialog'] > div[class~='ui-dialog-titlebar'] > a[class~='ui-dialog-titlebar-close']");
    }

    public ElementsCollection getPageTitles() {
        return $$("div[id^='window'][id$='Dialog'] > div[class~='ui-dialog-titlebar'] > span[class='ui-dialog-title']");
    }

    public EvrakPageButtons evrakPageButtons() {
        return new EvrakPageButtons();
        //return new EvrakPageButtons($("#mainPreviewForm"));
    }

    public MainPage evrakOlusturSayfayiKapat() {
        $$("[id='window1Dialog'] span[class='ui-icon ui-icon-closethick']").first().click();
        islemPenceresiKaydetPopup("Evet");
        return this;
    }

    public MainPage evrakOlusturSayfaKapat() {
        $$("[id='window1Dialog'] span[class='ui-icon ui-icon-closethick']").first().click();
        $(By.id("kapatKaydetHayirButton")).pressEnter();
        return this;

    }

    @Step("Cevap yaz sayfasında Ekranı kapat.")
    public MainPage cevapYazSayfaKapat(String cevap) {
        $("[id='windowCevapEvrakDialog'] [class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();
        if (cevap == "Evet")
            $("[id='kapatKaydetEvetButton']").click();

        else if (cevap == "Hayır") {
            $("[id='kapatKaydetHayirButton']").click();
        } else if (cevap == "İptal") {

            $(By.id("kapatKaydetIptalButton")).click();
        }
        return this;
    }

    @Step("Footer'da açılan sayfa butonu bul")
    public SelenideElement getFooterPageButton(String pageTitle) {
        return $x("//div[@id='mainTaskBar']//div[@type='button']/span[contains(.,'" + pageTitle + "')]");
    }

    @Step("Parafla")
    public MainPage parafla() {
        new EvrakPageButtons().evrakParafla();
        /*SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();
        sImzalaRadioSec();
        evrakImzaOnay();*/
        return this;
    }

    @Step("Parafla butona tıkla")
    public MainPage paraflaButonaTikla() {
        new EvrakPageButtons().paraflaButonaTikla();
        return this;
    }

    @Step("Koordine Parafla")
    public MainPage koordineParafla() {
        new EvrakPageButtons().evrakKoordineParafla();
        /*SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();
        sImzalaRadioSec();
        evrakImzaOnay();*/
        return this;
    }

    @Step("İmzala butona tıkla")
    public MainPage imzalaButonaTikla() {
        new EvrakPageButtons().imzalaButonaTikla();
        //imzalaButton().click();
        return this;
    }

    @Step("Evrak Guncellendi Imzalanamaz Uyarı Kontrolü ve Tamam")
    public MainPage evrakGuncellendiImzalanamazUyariKontrol(String uyari) {
        Assert.assertEquals(switchTo().alert().getText().equals(uyari), true, "Evrak Guncellendi ve Imzalanamaz Uyarı Kontrolü");
        Allure.addAttachment("Evrak Guncellendi ve Imzalanamaz Uyarı Kontrolü", "");
        switchTo().alert().accept();
        return this;
    }

    @Step("Parafla")
    public MainPage evrakParafla() {
        new EvrakPageButtons().evrakParafla();
        /*paraflaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        evrakImzaOnay();*/
        return this;
    }

    @Step("İmzala")
    public MainPage evrakImzala() {
        new EvrakPageButtons().evrakImzala();
        /*imzalaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        evrakImzaOnay();*/
        return this;
    }

    @Step("Icerik Degisti Iptal Tıklanır")
    public MainPage icerikDegistiIptal() {
        new EvrakPageButtons().icerikDegistiIptal();
        return this;
    }

    @Step("Icerik Degisti Uyarı Kontrol")
    public MainPage icerikDegistiUyarıKontrol(String uyari,String secenek1,String secenek2) {
        new EvrakPageButtons().icerikDegistiUyarıKontrol(uyari,secenek1,secenek2);
        return this;
    }

    @Step("Evrak Icerik Degisti Imzala ve Devam Et (Önceki kullanıcıları akıştan çıkartarak)")
    public MainPage evrakIcerikDegistiImzalaveDevamEt() {
        new EvrakPageButtons().evrakIcerikDegistiImzalaveDevamEt();
        return this;
    }

    @Step("Evrak Icerik Degisti Iade Et")
    public MainPage evrakIcerikDegistiIadeEt() {
        new EvrakPageButtons().evrakIcerikDegistiIadeEt();
        return this;
    }

    @Step("Evrak Icerik Degisti Kaydet")
    public MainPage evrakSecmeliDegistiKaydet() {
        new EvrakPageButtons().evrakSecmeliDegistiKaydet();
        return this;
    }

    @Step("Kullanıcıya Iade Et")
    public MainPage kullaniciyaIadeEt() {
        new EvrakPageButtons().kullaniciyaIadeEt();
        return this;
    }

    public MainPage evrakIcerikDegistiKaydetEvet() {
        new EvrakPageButtons().evrakIcerikDegistiKaydetEvet();
        return this;
    }


    @Step("Evrak içeriğini değiştirdiğiniz için evrak üzerindeki değişiklikler kaydedilecektir ve bu aşamadan sonra evrakınızı yalnızca iade edebilir veya güncellemeye devam edebilirsiniz. İşleminize devam etmek istiyor musunuz?")
    public MainPage evrakSecmeliDegistiEvet() {
        new EvrakPageButtons().evrakSecmeliDegistiEvet();
        return this;
    }


    @Step("İmzalama butonun üzerine Üçgen içerisinde Ünlem(!) ")
    public MainPage imzalanamazButtonKontrol() {
        new EvrakPageButtons().imzalanamazButtonKontrol();
        return this;
    }

    @Step("Paraflama butonun üzerine Üçgen içerisinde Ünlem(!) ")
    public MainPage paraflanamazButtonKontrol() {
        new EvrakPageButtons().paraflanamazButtonKontrol();
        return this;
    }

    @Step("Evrak İmzala Uyarı Kontrol")
    public MainPage evrakImzalaUyariKontrol(String uyari) {
        new EvrakPageButtons().evrakImzalaUyariKontrol(uyari);
        return this;
    }

    @Step("Iade Et")
    public MainPage iadeEt() {
        new EvrakPageButtons().iadeEt();
        return this;
    }

    @Step("Parafci Kontrol")
    public MainPage parafciKontrol(String user) {
        new EvrakPageButtons().parafciKontrol(user);
        return this;
    }

    @Step("Parafci Kontrol")
    public MainPage kontrolEdenKontrol(String user) {
        new EvrakPageButtons().kontrolEdenKontrol(user);
        return this;
    }

    @Step("Dosya Ekle")
    public MainPage dosyaEkle(String path,String file) throws InterruptedException{
        new EvrakPageButtons().dosyaEkle(path,file);
        return this;
    }

    @Step("Not Alanı Doldur")
    public MainPage notAlaniDoldur(String konu) {
        new EvrakPageButtons().notAlaniDoldur(konu);
        return this;
    }


    @Step("Menülerin geldiği görülür")
    public MainPage evrakIslemleriIslemYaptiklarimMenuKontrol() {

        Assert.assertEquals($(By.id("topMenuForm2:ust:0:ustMenuEleman")).isDisplayed(), true);
        Assert.assertEquals($(By.id("leftMenuForm:leftMenuIslemBekleyenEvraklar")).isDisplayed(), true);

        takeScreenshot();

        return this;
    }


    @Step("Ekranın sağ üstünde bulunan isim soyisim alanına tıklanır.")
    public MainPage userMenuAc() {
        $(By.id("topMenuForm:userMenuButton_button")).click();
        return this;
    }

    @Step("User Menu listesinde \"{menuName}\" menusu bulunur.")
    public MainPage userMenuKontrol(String menuName) {

        ElementsCollection elementList = $$(By.id("topMenuForm:userMenuButton_menu")).last().$$("li");

        boolean status = elementList
                .filterBy(Condition.text(menuName))
                .first().isDisplayed();

        Assert.assertEquals(status,true,"Listede menu ismi mevcut.");

        return this;
    }

    @Step("User Menu listesinde \"{menuName}\" tıklanır.")
    public MainPage userMenuMenuSec(String menuName) {

        ElementsCollection elementList = $$(By.id("topMenuForm:userMenuButton_menu")).last().$$("li");

        elementList
                .filterBy(Condition.text(menuName))
                .first().click();

        return this;
    }

    @Step("Profil ekranında guncel birimin \"{guncelBirim}\" olanların Rol adları alınır.")
    public String[] profildenRolAdiAlma(String guncelBirim) {
        int j= 0;
        ElementsCollection tblRolListesi = $$("div[class='ui-datatable-scrollable-body'] tbody[id$='data'] tr[data-ri]");

        String [] rolAdi = new String[tblRolListesi.filterBy(text(guncelBirim)).size()];

        for (int i = 0; i < tblRolListesi.size(); i++) {
            String birim = tblRolListesi.get(i).$("td:nth-child(2)").text();
            if (birim.equals(guncelBirim)) {
                rolAdi[j] = tblRolListesi.get(i).$("td:nth-child(1)").text();
                j++;
            }
//            Allure.addAttachment("Rol Adı : ", rolAdi[i]);
        }

//        $x("//span[text()='Profil']//..//..//div//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();

        return rolAdi;
    }

    @Step("Profil ekranı kapatılır.")
    public void profilEkraniKapat() {
        $x("//span[text()='Profil']//..//..//div//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();
    }
}
