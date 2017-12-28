package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 26.12.2017
 * Açıklama:
 */
public enum Ivedilik {
    /*<option value="N" selected="selected">Normal</option>
	<option value="G">Günlü</option>
	<option value="I">İvedi</option>
	<option value="C">Çok İvedi</option>
	<option value="V">İvedi/Günlü</option>*/

    Normal("Normal", "N"),
    Gunlu("Günlü", "G"),
    KisiyeOzel("İvedi", "I"),
    Ozel("Çok İvedi", "C"),
    HizmeteOzel("İvedi/Günlü", "V");

    private String optionValue;
    private String optionText;

    Ivedilik(String optionText, String optionValue) {
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
