package pages;

import common.BaseLibrary;
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

    public void logout(){
        $("button[id='topMenuForm:userMenuButton_button']").click();
    }
}
