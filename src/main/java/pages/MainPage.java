package pages;

import com.codeborne.selenide.Condition;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.SolMenu;
import pages.pageComponents.UserMenu;
import pages.pageComponents.UstMenu;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BaseLibrary {
    private SolMenu solMenu = new SolMenu();
    private UstMenu ustMenu = new UstMenu();
    private IslemMesajlari islemMesajlari = new IslemMesajlari();
    private UserMenu userMenu = new UserMenu();

    //region Ust NavigationMenu
    public void ustMenu(String ustMenuIsmi, String altMenuIsmi) {
        ustMenu.ustMenu(ustMenuIsmi, altMenuIsmi);
    }

    public void ustMenu(String menuIsmi) {
        ustMenu.ustMenu(menuIsmi);
    }

    //endregion

    //region Sol NavigationMenu
    public void solMenu(SolMenuData.IslemBekleyenEvraklar menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.BirimEvraklari menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.KapatmaIslemleri menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.Bildirimler menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.ArsivIslemleri menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.YoneticiIslemleri menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.KurulIslemleri menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }

    public void solMenu(SolMenuData.IslemYaptiklarim menu, boolean... useJS) {
        solMenu.solMenu(menu, ((useJS.length <= 0) || useJS[0]));
    }
    //endregion

    public IslemMesajlari islemMesaji() {
        return islemMesajlari;
    }

    //region Sayfalar

    /*public BaseLibrary baseLibrary() {
        return new BaseLibrary();
    }

    public Filtreler mainPage() {
        return new Filtreler();
    }

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public PulYonetimiPage pulYonetimiPage() {
        return new PulYonetimiPage();
    }

    public EvrakOlusturPage evrakOlusturPage() {
        return new EvrakOlusturPage();
    }

    public PaylastiklarimPage paylastiklarimPage() {
        return new PaylastiklarimPage();
    }

    public TuzelKisiYonetimiPage TuzelKisiYonetimiPage() {
        return new TuzelKisiYonetimiPage();
    }

//    public BirimIcerikSablonlarPage BirimIcerikSablonlarPage() {
  //      return new BirimIcerikSablonlarPage();
//    }

    public YonetimHavuzuYonetimiPage yonetimHavuzuYonetimiPage() {
        return new YonetimHavuzuYonetimiPage();
    }

    public OnayAkisYonetimiPage onayAkisYonetimiPage() {
        return new OnayAkisYonetimiPage();
    }

    public GercekKisiYonetimPage gercekKisiYonetimPage() {
        return new GercekKisiYonetimPage();
    }

    public GelenEvrakKayitPage gelenEvrakKayitPage(){return new GelenEvrakKayitPage();}

    public KaydedilenGelenEvraklarPage kaydedilenGelenEvraklar() {return new KaydedilenGelenEvraklarPage();}

    public TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage() {return new TeslimAlinmayiBekleyenlerPage();}

    public BirimYonetimiPage BirimYonetimiPage() { return  new BirimYonetimiPage();
    }

    public KurumYonetimiPage KurumYonetimiPage() { return new KurumYonetimiPage();
    }*/
    //endregion

    @Step("Kep bağlantısı alanı aç")
    public MainPage kepBaglantisi(){
    $(By.id("topMenuForm:userMenuButton_button")).click();
    $(By.id("topMenuForm:kepLoginButton")).click();
        return this;
    }
    @Step("Bağlan")
    public MainPage kepAdresBaglantisiBaglan1(){
        $("[id='kepForm:kayitliKepDataTable:0:j_idt235']").click();
        return this;
    }

    public MainPage kepAdresBaglantisiBaglan2(){
        $("[id='kepForm:kayitliKepDataTable:1:j_idt235']").click();
        return this;
    }
    @Step("Kullanıcı adı ve Tc Kimlik no kontrol et")
    public MainPage kullaniciAdiTcKimlikNoKontol(){
        $(By.id("kepLogin2FormId:kullaniciAdi")).shouldBe(Condition.disabled);
        $(By.id("kepLogin2FormId:tcKimlikNo")).shouldBe(Condition.disabled);
        return this;
    }
    @Step("Parola doldur")
    public MainPage parolaDoldur(String parola){
        $(By.id("kepLogin2FormId:parola")).setValue(parola);
        return this;
    }
    @Step("Şifre Doldur")
    public MainPage sifreDoldur(String sifre){
        $(By.id("kepLogin2FormId:sifre")).setValue(sifre);
        return this;
    }
    @Step("Bağlan")
    public MainPage kepBaglantisiBaglan(){
        $(By.id("kepLogin2FormId:j_idt255")).click();
        return this;
    }

    @Step("Çıkış yap")
    public void logout() {
        $("button[id='topMenuForm:userMenuButton_button']").click();
    }

    public MainPage ustMenuEvrakIslemleriAc() {
        $(By.id("topMenuForm2:ust:0:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuKullaniciIslemleri() throws InterruptedException{
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:3:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuRaporlar() throws InterruptedException{
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:6:ustMenuEleman")).click();
        return this;
    }

    public MainPage altMenuTooltipKontrol(String altMenuAd) {

        String tooltip="";
        switch (altMenuAd){
            case "Evrak Oluştur":
                $(By.id("topMenuForm2:ust:0:ust:0:ust:0:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip, "(Shift + E)");
                break;
            case "Giden Evrak Kayıt":
                $(By.id("topMenuForm2:ust:0:ust:0:ust:2:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + I)");
                break;
            case "Gelen Evrak Kayıt":
                $(By.id("topMenuForm2:ust:0:ust:0:ust:3:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + G)");
                break;
            case "Evrak Arama":
                $(By.id("topMenuForm2:ust:0:ust:1:ust:1:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + A)");
                break;
            case "Karar Yazısı Oluştur":
                $(By.id("topMenuForm2:ust:0:ust:0:ust:4:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + K)");
                break;
            case "Kullanıcı Yönetimi":
                $(By.id("topMenuForm2:ust:3:ust:0:ust:1:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + U)");
                break;
            case "Gelen Evrak Raporu":
                $(By.id("topMenuForm2:ust:6:ust:0:ust:9:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + N)");
                break;
            case "Personel ve Açık Evrak İstatistiği":
                $(By.id("topMenuForm2:ust:6:ust:0:ust:10:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + P)");
                break;
            case "Olur Yazısı Oluştur":
                $(By.id("topMenuForm2:ust:0:ust:0:ust:1:ust")).hover();
                tooltip = $(By.id("tiptip_content")).innerText();
                System.out.println(tooltip);
                Assert.assertEquals(tooltip,"(Shift + O)");
                break;
        }

        return this;
    }
}
