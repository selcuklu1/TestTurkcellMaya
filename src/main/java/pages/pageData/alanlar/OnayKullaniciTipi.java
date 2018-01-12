package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 26.12.2017
 * Açıklama:
 */
public enum OnayKullaniciTipi {
    /*<option value="KONTROL">Kontrol</option>
	<option value="PARAFLAMA" selected="selected">Paraflama</option>
	<option value="IMZALAMA">İmzalama</option>*/

    KONTROL("Kontrol", "KONTROL"),
    PARAFLAMA("Paraflama", "PARAFLAMA"),
    IMZALAMA("İmzalama", "IMZALAMA");

    private String optionValue;
    private String optionText;

    OnayKullaniciTipi(String optionText, String optionValue) {
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getOptionText() {
        return optionText;
    }
}
