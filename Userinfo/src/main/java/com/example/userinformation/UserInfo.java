package com.example.userinformation;

public class UserInfo {
    public  String username,password,ime,priimek,email;
    public  int telefon,teza,visina;
    public  String spol,ulica,hisna_st,ime_poste;
    public  int stevilka_poste;

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                ", email='" + email + '\'' +
                ", telefon=" + telefon +
                ", teza=" + teza +
                ", visina=" + visina +
                ", spol='" + spol + '\'' +
                ", ulica='" + ulica + '\'' +
                ", hisna_st='" + hisna_st + '\'' +
                ", ime_poste='" + ime_poste + '\'' +
                ", stevilka_poste=" + stevilka_poste +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefon() {
        return telefon;
    }

    public int getTeza() {
        return teza;
    }

    public int getVisina() {
        return visina;
    }

    public String getSpol() {
        return spol;
    }

    public String getUlica() {
        return ulica;
    }

    public String getHisna_st() {
        return hisna_st;
    }

    public String getIme_poste() {
        return ime_poste;
    }

    public int getStevilka_poste() {
        return stevilka_poste;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setTeza(int teza) {
        this.teza = teza;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setHisna_st(String hisna_st) {
        this.hisna_st = hisna_st;
    }

    public void setIme_poste(String ime_poste) {
        this.ime_poste = ime_poste;
    }

    public void setStevilka_poste(int stevilka_poste) {
        this.stevilka_poste = stevilka_poste;
    }

    public UserInfo(String username, String password, String ime, String priimek, String email, int telefon, int teza, int visina, String spol, String ulica, String hisna_st, String ime_poste, int stevilka_poste) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.telefon = telefon;
        this.teza = teza;
        this.visina = visina;
        this.spol = spol;
        this.ulica = ulica;
        this.hisna_st = hisna_st;
        this.ime_poste = ime_poste;
        this.stevilka_poste = stevilka_poste;
    }
}
