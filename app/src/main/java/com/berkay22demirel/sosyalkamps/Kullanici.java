package com.berkay22demirel.sosyalkamps;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class Kullanici implements Serializable {
    String eposta;
    String sifre;
    String ad;
    String soyad;
    String ceptelefonu;
    String dogumtarihi;
    String cinsiyet;
    String fotografUrl;
    String bio;

    public Kullanici(String eposta, String sifre, String ad, String soyad, String ceptelefonu, String dogumtarihi, String cinsiyet) {
        this.eposta = eposta;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.ceptelefonu = ceptelefonu;
        this.dogumtarihi = dogumtarihi;
        this.cinsiyet = cinsiyet;
    }

    public Kullanici(String eposta, String sifre, String ad, String soyad, String ceptelefonu, String dogumtarihi, String cinsiyet,String bio) {
        this.eposta = eposta;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.ceptelefonu = ceptelefonu;
        this.dogumtarihi = dogumtarihi;
        this.cinsiyet = cinsiyet;
        this.bio = bio;
    }

    public Kullanici() {
    }

    public Kullanici(String eposta, String sifre) {
        this.eposta = eposta;
        this.sifre = sifre;
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

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getCeptelefonu() {
        return ceptelefonu;
    }

    public void setCeptelefonu(String ceptelefonu) {
        this.ceptelefonu = ceptelefonu;
    }

    public String getDogumtarihi() {
        return dogumtarihi;
    }

    public void setDogumtarihi(String dogumtarihi) {
        this.dogumtarihi = dogumtarihi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFotografUrl() {
        return fotografUrl;
    }

    public void setFotografUrl(String fotografUrl) {
        this.fotografUrl = fotografUrl;
    }

}
