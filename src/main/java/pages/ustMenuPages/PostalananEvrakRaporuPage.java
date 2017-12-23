package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class PostalananEvrakRaporuPage extends MainPage {

    SelenideElement txtEvrakSayisi = $( By.id("postalananEvrakRaporuForm:evrakSayiTextId"));
    SelenideElement btnPostaBaslangicTarihi = $(By.id("postalananEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("postalananEvrakRaporuForm:sorgulaButton"));
    ElementsCollection tblSorgulamaSonuc = $$("tbody[id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']");

public PostalananEvrakRaporuPage openPage() {

        ustMenu("Postalanan Evrak Raporu");
        return this;
}

public PostalananEvrakRaporuPage evrakSayisi (String txt) {
    txtEvrakSayisi.setValue(txt);
    return this;
}

public PostalananEvrakRaporuPage postaAramaBaslangicTarihi (String txt) {

    btnPostaBaslangicTarihi.setValue(txt);
    return this;
}

public PostalananEvrakRaporuPage postaSorgulama() {

    btnSorgula.click();
    return this;

}

public PostalananEvrakRaporuPage sonucKarsilastirma () {

    tblSorgulamaSonuc.get(0);

    return this;
}
}
