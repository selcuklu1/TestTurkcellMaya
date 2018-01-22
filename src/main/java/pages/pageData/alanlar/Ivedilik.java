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

    NORMAL("Normal", "N"),
    GUNLU("Günlü", "G"),
    IVEDI("İvedi", "I"),
    COKI_VEDI("Çok İvedi", "C"),
    IVEDI_GUNLU("İvedi/Günlü", "V");

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
