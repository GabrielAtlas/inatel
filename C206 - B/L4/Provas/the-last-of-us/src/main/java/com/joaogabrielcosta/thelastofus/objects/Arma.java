package com.joaogabrielcosta.thelastofus.objects;

public class Arma {

    private String tipoArma;
    private int forca;

    public Arma(String tipoArma, int forca) {
        this.tipoArma = tipoArma;
        this.forca = forca;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }
}
