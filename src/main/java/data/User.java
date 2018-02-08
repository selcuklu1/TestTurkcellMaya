package data;

import java.util.ArrayList;

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
    private String birimKisaAdi = "";
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
        //this.birimAdi = birimAdi;
        splitBirimAdi(birimAdi);
        splitFullname(fullname);
    }

    public User(String username, String password, String fullname, String birimAdi, String gorev) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        //this.birimAdi = birimAdi;
        this.gorev = gorev;
        splitBirimAdi(birimAdi);
        splitFullname(fullname);

    }

    public User(String username, String password, String fullname, String birimAdi, String gorev, String name, String surname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        //this.birimAdi = birimAdi;
        splitBirimAdi(birimAdi);
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

    public String getBirimKisaAdi() {
        return birimKisaAdi;
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

    private void splitBirimAdi(String birimAdi) {
        if (birimAdi.isEmpty()) return;
        int lastIndex = birimAdi.lastIndexOf("/");
        if (lastIndex > 0){
            this.birimAdi = birimAdi.substring(0, lastIndex).trim();
            this.birimKisaAdi = birimAdi.substring(lastIndex + 1).trim();
        } else {
            this.birimAdi = birimAdi;
        }
    }

    private ArrayList<String> getAll(){
        ArrayList<String> list = new ArrayList<>();
        if (!name.isEmpty()) list.add(name);
        if (!surname.isEmpty()) list.add(surname);
        if (!gorev.isEmpty()) list.add(gorev);
        if (!birimAdi.isEmpty()) list.add(birimAdi);
        if (!birimKisaAdi.isEmpty()) list.add(birimKisaAdi);
        return list;
    }

}
