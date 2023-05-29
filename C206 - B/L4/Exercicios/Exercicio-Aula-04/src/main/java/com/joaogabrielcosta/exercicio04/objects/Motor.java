package com.joaogabrielcosta.exercicio04.objects;

public class Motor {

    public int potencia;
    public String tipo;

    public Motor(int potencia, String tipo) {
        this.potencia = potencia;
        this.tipo = tipo;
    }
    public void mostrarInfo() {
        System.out.println("[Motor] Potencia: " + potencia);
        System.out.println("[Motor] Tipo: " + tipo);
    }
}
