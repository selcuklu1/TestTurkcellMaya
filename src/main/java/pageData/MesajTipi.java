package pageData;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public enum MesajTipi {

    BASARILI("İşlem Başarılıdır"),
    UYARI("Uyarı"),
    DIKKAT("Dikkat");


    private String value;

    MesajTipi(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
