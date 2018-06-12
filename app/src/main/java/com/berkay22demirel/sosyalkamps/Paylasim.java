package com.berkay22demirel.sosyalkamps;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class Paylasim {
    String eposta;
    String yazi;
    String fotografurl;
    String paylasimZamani;


    public Paylasim(String yazi, String eposta, String fotografurl, String paylasimZamani) {
        this.yazi = yazi;
        this.eposta = eposta;
        this.fotografurl = fotografurl;
        this.paylasimZamani = paylasimZamani;
    }

    public Paylasim() {
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public String getFotografurl() {
        return fotografurl;
    }

    public void setFotografurl(String fotografurl) {
        this.fotografurl = fotografurl;
    }

    public String getPaylasimZamani() {
        return paylasimZamani;
    }

    public void setPaylasimZamani(String paylasimZamani) {
        this.paylasimZamani = paylasimZamani;
    }
}

