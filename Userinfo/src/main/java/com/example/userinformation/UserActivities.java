package com.example.userinformation;

import java.sql.Time;
import java.util.Date;

public class UserActivities {
    String datum;
    String cas;
    //Date datum;
    //int ocena_aktivnosti,koraki,prabljenje_kalorije,povp_srcni_utrip,povp_hitrost,razadlja;
    //Time cas;
    String username;
    String ime;
    String ocena_aktivnosti,koraki,prabljenje_kalorije,povp_srcni_utrip,povp_hitrost,razadlja;

    @Override
    public String toString() {
        return "UserActivities{" +
                "datum=" + datum +
                ", ocena_aktivnosti=" + ocena_aktivnosti +
                ", koraki=" + koraki +
                ", prabljenje_kalorije=" + prabljenje_kalorije +
                ", povp_srcni_utrip=" + povp_srcni_utrip +
                ", povp_hitrost=" + povp_hitrost +
                ", razadlja=" + razadlja +
                ", cas=" + cas +
                ", username='" + username + '\'' +
                ", ime='" + ime + '\'' +
                '}';
    }

    public UserActivities(String datum, String cas, String username, String ime, String ocena_aktivnosti, String koraki, String prabljenje_kalorije, String povp_srcni_utrip, String povp_hitrost, String razadlja) {
        this.datum = datum;
        this.cas = cas;
        this.username = username;
        this.ime = ime;
        this.ocena_aktivnosti = ocena_aktivnosti;
        this.koraki = koraki;
        this.prabljenje_kalorije = prabljenje_kalorije;
        this.povp_srcni_utrip = povp_srcni_utrip;
        this.povp_hitrost = povp_hitrost;
        this.razadlja = razadlja;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setOcena_aktivnosti(String ocena_aktivnosti) {
        this.ocena_aktivnosti = ocena_aktivnosti;
    }

    public void setKoraki(String koraki) {
        this.koraki = koraki;
    }

    public void setPrabljenje_kalorije(String prabljenje_kalorije) {
        this.prabljenje_kalorije = prabljenje_kalorije;
    }

    public void setPovp_srcni_utrip(String povp_srcni_utrip) {
        this.povp_srcni_utrip = povp_srcni_utrip;
    }

    public void setPovp_hitrost(String povp_hitrost) {
        this.povp_hitrost = povp_hitrost;
    }

    public void setRazadlja(String razadlja) {
        this.razadlja = razadlja;
    }

    public String getDatum() {
        return datum;
    }

    public String getCas() {
        return cas;
    }

    public String getUsername() {
        return username;
    }

    public String getIme() {
        return ime;
    }

    public String getOcena_aktivnosti() {
        return ocena_aktivnosti;
    }

    public String getKoraki() {
        return koraki;
    }

    public String getPrabljenje_kalorije() {
        return prabljenje_kalorije;
    }

    public String getPovp_srcni_utrip() {
        return povp_srcni_utrip;
    }

    public String getPovp_hitrost() {
        return povp_hitrost;
    }

    public String getRazadlja() {
        return razadlja;
    }
}