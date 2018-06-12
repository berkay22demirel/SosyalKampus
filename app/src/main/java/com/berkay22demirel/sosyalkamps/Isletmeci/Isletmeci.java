package com.berkay22demirel.sosyalkamps.Isletmeci;

import java.io.Serializable;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class Isletmeci implements Serializable {
    String eposta;
    String sifre;
    String mekanAdi;
    String mekanTelefon;
    String mekanAdres;
    String mekanFotografUrl;

    public Isletmeci() {
    }

    public Isletmeci(String eposta, String sifre, String mekanAdi, String mekanAdres, String mekanTelefon, String mekanFotografUrl) {
        this.eposta = eposta;
        this.sifre = sifre;
        this.mekanAdi = mekanAdi;
        this.mekanAdres = mekanAdres;
        this.mekanTelefon = mekanTelefon;
        this.mekanFotografUrl = mekanFotografUrl;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getMekanAdi() {
        return mekanAdi;
    }

    public void setMekanAdi(String mekanAdi) {
        this.mekanAdi = mekanAdi;
    }

    public String getMekanTelefon() {
        return mekanTelefon;
    }

    public void setMekanTelefon(String mekanTelefon) {
        this.mekanTelefon = mekanTelefon;
    }

    public String getMekanAdres() {
        return mekanAdres;
    }

    public void setMekanAdres(String mekanAdres) {
        this.mekanAdres = mekanAdres;
    }

    public String getMekanFotografUrl() {
        return mekanFotografUrl;
    }

    public void setMekanFotografUrl(String mekanFotografUrl) {
        this.mekanFotografUrl = mekanFotografUrl;
    }
}
