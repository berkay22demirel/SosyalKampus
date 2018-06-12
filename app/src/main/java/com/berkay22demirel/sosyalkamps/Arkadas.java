package com.berkay22demirel.sosyalkamps;

import java.io.Serializable;

/**
 * Created by BerkayDemirel on 10.06.2018.
 */

public class Arkadas implements Serializable {
    String eposta;
    String arkadasEposta;

    public String getArkadasEposta() {
        return arkadasEposta;
    }

    public void setArkadasEposta(String arkadasEposta) {
        this.arkadasEposta = arkadasEposta;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
}
