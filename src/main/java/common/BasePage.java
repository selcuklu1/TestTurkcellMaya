package common;

import io.qameta.allure.Step;
import page.*;
import pageComponents.MainPage;
import pageComponents.UstMenu;
import pageComponents.IslemMesajlari;
import pageComponents.SolMenu;

import static pageData.SolMenuData.*;

public class BasePage {

    //region Ust Menu
    @Step("\"{ustMenuIsmi}\"->\"{altMenuIsmi}\" ust menu aç")
    public UstMenu ustMenuAc(String ustMenuIsmi, String altMenuIsmi) {
        return new UstMenu(ustMenuIsmi, altMenuIsmi);
    }
    @Step("\"{menuIsmi}\" ust menu aç")
    public UstMenu ustMenuAc(String menuIsmi) {
        return new UstMenu(menuIsmi);
    }
    //endregion

    //region Sol Menu
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(IslemBekleyenEvraklar menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(BirimEvraklari menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(KapatmaIslemleri menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(Bildirimler menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(ArsivIslemleri menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(YoneticiIslemleri menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(KurulIslemleri menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    @Step("\"{menu.groupText}\"->\"{menu.value}\" sol menu aç")
    public SolMenu solMenu(IslemYaptiklarim menu, boolean... useJS) {
        return new SolMenu(menu, ((useJS.length > 0) ? useJS[0]: true));
    }
    //endregion



    //region Sayfalr
    public IslemMesajlari islemMesaji() {
        return new IslemMesajlari();
    }

    public MainPage mainPage() {
        return new MainPage();
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
    //endregion
}
