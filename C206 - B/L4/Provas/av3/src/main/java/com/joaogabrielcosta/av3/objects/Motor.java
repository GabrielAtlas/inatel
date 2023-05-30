package com.joaogabrielcosta.av3.objects;

public class Motor {

    private final double potencia;
    private final int numCilindros;

    public Motor(double potencia, int numCilindros) {
        this.potencia = potencia;
        this.numCilindros = numCilindros;
    }

    public double getPotencia() {
        return potencia;
    }

    public int getNumCilindros() {
        return numCilindros;
    }
}
