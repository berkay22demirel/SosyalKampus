package com.berkay22demirel.sosyalkamps;

import java.sql.Timestamp;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class Konum {
    String eposta;
    double enlem;
    double boylam;
    Timestamp guncellemeZamani;

    public Konum(String eposta, double boylam, double enlem, Timestamp guncellemeZamani) {
        this.eposta = eposta;
        this.boylam = boylam;
        this.enlem = enlem;
        this.guncellemeZamani = guncellemeZamani;
    }

    public Konum() {
    }

    public Konum(String eposta,double enlem, double boylam) {
        this.enlem = enlem;
        this.boylam = boylam;
        this.eposta = eposta;
    }

    public double getBoylam() {
        return boylam;
    }

    public void setBoylam(double boylam) {
        this.boylam = boylam;
    }

    public double getEnlem() {
        return enlem;
    }

    public void setEnlem(double enlem) {
        this.enlem = enlem;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public Timestamp getGuncellemeZamani() {
        return guncellemeZamani;
    }

    public void setGuncellemeZamani(Timestamp guncellemeZamani) {
        this.guncellemeZamani = guncellemeZamani;
    }
}
