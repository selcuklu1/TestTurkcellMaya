package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public enum EvrakDili {
    /*<option value="917" selected="selected">Türkçe</option>
	<option value="910">İngilizce</option>*/

    Turkce("Türkçe", "917"),
    Ingilizce("İngilizce", "910");

    private String optionValue;
    private String optionText;

    EvrakDili(String optionText, String optionValue) {
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
