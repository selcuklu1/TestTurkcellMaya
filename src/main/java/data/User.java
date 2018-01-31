package data;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 19.12.2017
 * Açıklama:
 */
public class User {
    private String username = "";
    private String password = "";
    private String fullname = "";
    private String birimAdi = "";
    private String gorev = "";
    private String name = "";
    private String surname = "";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        splitFullname(fullname);
    }

    public User(String username, String password, String fullname, String birimAdi) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birimAdi = birimAdi;
        splitFullname(fullname);
    }

    public User(String username, String password, String fullname, String birimAdi, String gorev) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birimAdi = birimAdi;
        this.gorev = gorev;

        splitFullname(fullname);

    }

    public User(String username, String password, String fullname, String birimAdi, String gorev, String name, String surname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birimAdi = birimAdi;
        this.gorev = gorev;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public String getGorev() {
        return gorev;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    private void splitFullname(String fullname) {
        if (!fullname.isEmpty()) {
            String[] n = fullname.split(" ", 2);
            this.name = n[0];
            this.surname = n[1];
        }
    }
}
