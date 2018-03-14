package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public enum GizlilikDerecesi {
    /*<option value="G">Gizli</option>
	<option value="K">Kişiye Özel</option>
	<option value="O">Özel</option>
	<option value="H">Hizmete Özel</option>
	<option value="T">Tasnif Dışı</option>
	<option value="N" selected="selected">Normal</option>*/
    /*Nato gizli
    Çok gizli
    Gizli
    Özel
    Kişiye Özel
    Hizmete özel
    Tasnif dışı
    Normal*/

    NATO_GIZLI("Nato Gizli", ""),
    COK_GIZLI("Çok Gizli", "C"),
    GIZLI("Gizli", "G"),
    KISIYE_OZEL("Kişiye Özel", "K"),
    OZEL("Özel", "O"),
    HIZMETE_OZEL("Hizmete Özel", "H"),
    TASNIF_DISI("Tasnif Dışı", "T"),
    NORMAL("Normal", "N");

    /*private String loactor = "select[id$='evrakDili']";
    private By by = By.cssSelector("select[id$='evrakDili']");*/
    private String optionValue;
    private String optionText;

    GizlilikDerecesi(String optionText, String optionValue) {
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getOptionText() {
        return optionText;
    }

    /*public By by() {
        return by;
    }
    public String getLocator() {
        return loactor;
    }*/
}
