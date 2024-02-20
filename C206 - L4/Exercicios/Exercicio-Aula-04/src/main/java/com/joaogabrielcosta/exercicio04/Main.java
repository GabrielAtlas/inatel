package com.joaogabrielcosta.exercicio04;

import com.joaogabrielcosta.exercicio04.objects.Carro;

public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro("Vermelho", "Fiat", "F-250", 20, 50);
        carro.ligar();
        carro.acelerar();
        carro.mostrarInfo();
        carro.getMotor().mostrarInfo();
    }
}