package com.berkay22demirel.sosyalkamps;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class Yorum {
    String mekanEposta;
    String yapilanYorum;
    String yorumYapanEposta;
    String paylasimZamani;

    public Yorum(String mekanEposta, String yapilanYorum, String yorumYapanEposta, String paylasimZamani) {
        this.mekanEposta = mekanEposta;
        this.yapilanYorum = yapilanYorum;
        this.yorumYapanEposta = yorumYapanEposta;
        this.paylasimZamani = paylasimZamani;
    }

    public Yorum() {
    }

    public String getMekanEposta() {
        return mekanEposta;
    }

    public void setMekanEposta(String mekanEposta) {
        this.mekanEposta = mekanEposta;
    }

    public String getYorumYapanEposta() {
        return yorumYapanEposta;
    }

    public void setYorumYapanEposta(String yorumYapanEposta) {
        this.yorumYapanEposta = yorumYapanEposta;
    }

    public String getYapilanYorum() {
        return yapilanYorum;
    }

    public void setYapilanYorum(String yapilanYorum) {
        this.yapilanYorum = yapilanYorum;
    }

    public String getPaylasimZamani() {
        return paylasimZamani;
    }

    public void setPaylasimZamani(String paylasimZamani) {
        this.paylasimZamani = paylasimZamani;
    }
}
