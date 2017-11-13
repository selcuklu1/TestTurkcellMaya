package pageComponents;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.testng.Assert;
import pageData.MesajTipi;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static pageData.MesajTipi.*;

public class IslemMesajlari extends BaseLibrary {
//
    
    //yeni env objeleri
    //div class="lobibox-notify lobibox-notify-success animated-fast fadeInDown notify-mini"
    //span lobibox-close
    //div class=lobibox-notify-title
    //div class=lobibox-notify-msg

    private SelenideElement islemMesajiTitle = $(".ui-growl-message  > .ui-growl-title");
    private SelenideElement islemMesaji = $(".ui-growl-message p");

    @Step("Beklenen mesaj tipi \"{mesajTipi.BASARILI.value()}\"")
    public void beklenenMesajTipi(MesajTipi mesajTipi) {
        Assert.assertEquals(getMesajTipi(), mesajTipi.value());
    }

    @Step("Başarılı mesajı gelmeli")
    private void basariliOlmali() {
        Assert.assertEquals(getMesajTipi(), BASARILI.value());
    }
    @Step("Uyarı mesajı gelmeli")
    private void uyariOlmali() {
        Assert.assertEquals(getMesajTipi(), UYARI.value());
    }
    @Step("Dikkat mesajı gelmeli")
    private void dikkatOlmali() {
        Assert.assertEquals(getMesajTipi(), DIKKAT.value());
    }

    public boolean isBasarili() {
        return getMesajTipi().equalsIgnoreCase(BASARILI.value());
    }

    public boolean isUyari() {
        return getMesajTipi().equalsIgnoreCase(UYARI.value());
    }

    public boolean isDikkat() {
        return getMesajTipi().equalsIgnoreCase(DIKKAT.value());
    }

    public String getMesajTipi() {
        return islemMesajiTitle.text();
    }

    public String getMesaj() {
        return islemMesaji.text();
    }


}


