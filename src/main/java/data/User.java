package data;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 19.12.2017
 * Açıklama:
 */
public class User {
    private String username = "";
    private String password = "";
    private String name = "";
    private String birimAdi = "";
    private String gorev = "";

    public User(String username, String password, String name, String birimAdi, String gorev) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birimAdi = birimAdi;
        this.gorev = gorev;
    }

    public User(String username, String password, String name, String birimAdi) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birimAdi = birimAdi;
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public String getGorev() {
        return gorev;
    }
}
