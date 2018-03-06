package tests.HavaleYetkisi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.tabs.AltTabs;
import pages.ustMenuPages.RolYonetimiPage;

public class HavaleYetkisiTest extends BaseTest {
    RolYonetimiPage rolYonetimiPage;

    @BeforeMethod
    public void BeforeTest() {
        rolYonetimiPage = new RolYonetimiPage();

    }


    @Step("Havale işlemleri Tüm birimleri görebilme aksiyonlu rol oluşturma")
    public void preTS2253 (String yenirolad , String eklenecekAksiyon) throws InterruptedException {
        rolYonetimiPage.openPage();
        rolYonetimiPage.btnYeniRolekle()
                .txtYeniRolAd(yenirolad)
                .txtYeniRolKısaAd(yenirolad)
                .txtYeniRolEtiket("TS2253")
                .txtYeniRolEtiket("TS2253")
                .txtRolYetkiOnceligi(3)
                .btnYeniRolKaydetme();

        rolYonetimiPage.txtRolAdArama(yenirolad)
                .btnRolArama()
                .tblRolListeSecim(yenirolad)
                .tblRolListeSecimAksiyonButonu()
                .btnYeniAksiyonEkle()
                .txtDialogAksiyonad(eklenecekAksiyon)
                .btnDialogAksiyonAra()
                .btnDialogselectAction()
                .btnDialogAksiyonEkle();


    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true , description = "\"Havale İşlemleri Tüm Birimleri Görebilme\" aksiyonunu kaldırma")
    public void TS2253 () throws InterruptedException {
        String yeniRolad = "TS2253" + getSysDate();
        String yeniAksiyon = "Havale İşlemleri Tüm Birimleri Görebilme";
        login("hgner", "123");
        preTS2253 (yeniRolad,yeniAksiyon);
    }
}
