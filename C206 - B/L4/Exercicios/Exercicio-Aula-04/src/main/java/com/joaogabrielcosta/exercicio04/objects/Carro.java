package com.joaogabrielcosta.exercicio04.objects;

public class Carro {

    public String cor, marca, modelo;
    public Motor motor;
    public double velocidadeMax, velocidadeAtual;

    public Carro (String cor, String marca, String modelo, double velocidadeAtual, double velocidadeMax) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = new Motor(20, "Diesel");
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
    }

    public void ligar() {
        message("O carro foi ligado.");
    }

    public void acelerar() {
        this.velocidadeAtual += 2;
    }

    public Motor getMotor() {
        return motor;
    }

    public void mostrarInfo() {
        message("Cor: " + cor);
        message("Velocidade atual: " + velocidadeAtual + " Km/h");
        message("Velocidade máxima: " + velocidadeMax + " Km/h");
    }

    private void message(String message) {
        System.out.println("[Carro] " + message);
    }
}
