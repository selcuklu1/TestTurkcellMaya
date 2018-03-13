package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserMenu extends MainPage {
    SelenideElement userMenuButton = $(By.id("topMenuForm:userMenuButton_button"));
    SelenideElement userMenu = $(By.id("topMenuForm:userMenuButton_menu"));
    SelenideElement helpButton = $(By.id("topMenuForm:helpButton"));
    SelenideElement aboutButton = $(By.id("topMenuForm:aboutButton"));
    SelenideElement profilButton = $(By.id("topMenuForm:profilButton"));
    SelenideElement ePostaBilgileriButton = $(By.id("topMenuForm:ePostaBilgileriButton"));
    SelenideElement kepLoginButton = $(By.id("topMenuForm:kepLoginButton"));
    SelenideElement konfigurasyonTestiButton = $x("//div[@id='topMenuForm:userMenuButton_menu']//a[.='Konfigurasyon Testi']");
    SelenideElement siteHaritasiButton = $x("//div[@id='topMenuForm:userMenuButton_menu']//a[.='Site Haritası']");
    SelenideElement logOutButton = $(By.id("topMenuForm:logOutButton"));


    @Step("User menü açılır")
    public UserMenu userMenuAc(){
        userMenuButton.click();
        userMenu.shouldBe(visible);
        return this;
    }

    @Step("Profil menü seçilir")
    public Profil profilMenuSec(){
        profilButton.click();
        return new Profil();
    }

    public class Profil {
        SelenideElement container = $x("//span[@class='ui-dialog-title' and .='Profil']/ancestor::div[contains(@class,'ui-widget-content')]");
        SelenideElement rolListesiTable = container.$(".ui-datatable-scrollable-body");
        ElementsCollection rolListesi = rolListesiTable.$$("tr[data-ri][role=row]");
                //$$("div[class='ui-datatable ui-widget ui-datatable-scrollable'] tr[data-ri][role=row]");

        @Step("Profil penceresi kapa")
        public Profil closeDialog(){
            container.$("div[class~='ui-dialog-titlebar'] a[class~='ui-dialog-titlebar-close']").click();
            container.should(disappear);
            return this;
        }

        @Step("Rol Listesi {condition} içermeli")
        public Profil rolListesiTesksIcermeli(Condition... condition){
            rolListesiTable.shouldHave(condition);
            return this;
        }

        @Step("Rol Listesi {condition} içerMEmeli")
        public Profil rolListesiTesksIcermemeli(Condition... condition){
            rolListesiTable.shouldNotHave(condition);
            return this;
        }

        @Step("Tüm rolleri alınır")
        public ArrayList<String> getAllRoles(){
            ArrayList<String> roles = new ArrayList<>();
            rolListesi.forEach((role) -> {
                roles.add(role.$("td[role='gridcell']").text());
            });

            Allure.addAttachment("Alınan rolleri", roles.toString());
            return roles;
        }
    }
}
