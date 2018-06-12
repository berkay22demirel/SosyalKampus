package com.berkay22demirel.sosyalkamps;

import java.io.Serializable;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class Mesaj implements Serializable {
    String kimeEposta;
    String kimdenEposta;
    String icerik;
    String gönderimZamani;

    public Mesaj() {
    }

    public Mesaj(String kimeEposta, String kimdenEposta, String icerik, String gönderimZamani) {
        this.kimeEposta = kimeEposta;
        this.kimdenEposta = kimdenEposta;
        this.icerik = icerik;
        this.gönderimZamani = gönderimZamani;
    }

    public String getKimdenEposta() {
        return kimdenEposta;
    }

    public void setKimdenEposta(String kimdenEposta) {
        this.kimdenEposta = kimdenEposta;
    }

    public String getKimeEposta() {
        return kimeEposta;
    }

    public void setKimeEposta(String kimeEposta) {
        this.kimeEposta = kimeEposta;
    }

    public String getGönderimZamani() {
        return gönderimZamani;
    }

    public void setGönderimZamani(String gönderimZamani) {
        this.gönderimZamani = gönderimZamani;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
}
