package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.*;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class DosyaEkleTab {

    private final static String tabName = "Dosya Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public DosyaEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    public DosyaEkleTab openTab() {
        parentElement.$("a[href$='dosyaEkleTab']").click();
        altTab = parentElement.$("div[id$='dosyaEkleTab']");
        return this;
    }

    @Step("Ek Metni ara")
    public SelenideElement getEkMetniTextarea(){
        return altTab.$("textarea[id$='dosyaAciklama']");
    }

    @Step("Ek Metni doldur")
    public DosyaEkleTab ekMetniDoldur(String text){
        getEkMetniTextarea().setValue(text);
        return this;
    }

    @Step("Tarama Havuzundan Ekle ara")
    public SelenideElement getTaramaHavuzundanEkleButton(){
        return altTab.$("button[id$='uploadFromTarananEvrakHavuzuEkA']");
    }

    @Step("Tarama Havuzundan Ekle butona tıkla")
    public DosyaEkleTab taramaHavuzundanEkleTikla(){
        getTaramaHavuzundanEkleButton().shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Tarayıcıdan Ekle ara")
    public SelenideElement getTarayicidanEkleButton(){
        return altTab.$("button[id$='dogrudanTaramaBaslatEkA']");
    }

    @Step("Tarayıcıdan Ekle butona tıkla")
    public DosyaEkleTab tarayicidanEkleTikla(){
        getTarayicidanEkleButton().shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Dosya Ekle alanı ara")
    public SelenideElement getDosyaEkleInput(){
        return altTab.$("input[id$='fileUploadButtonA_input']");
    }

    @Step("Dosya Ekle alanda dosyayı seç")
    public DosyaEkleTab dosyaEkleAlan(String fileName){
        getDosyaEkleInput().uploadFromClasspath(fileName);
        parentElement.shouldHave(text(fileName));
        return this;
    }

    @Step("Dosya Ekle alanda dosyayı seç")
    public DosyaEkleTab dosyaEkleAlanFromCustomPath(String filePath){
        File file = new File(filePath);
        getDosyaEkleInput().uploadFile(file);
        return this;
    }

    @Step("Dosya Ekle butonu ara")
    public SelenideElement getDosyaEkleButton(){
        return altTab.$("button[id$='dosyaEkleButton']");
    }

    @Step("Dosya Ekle butona tikla")
    public DosyaEkleTab getDosyaEkleButonaTikla(){
        getDosyaEkleButton().click();
        return this;
    }
}
