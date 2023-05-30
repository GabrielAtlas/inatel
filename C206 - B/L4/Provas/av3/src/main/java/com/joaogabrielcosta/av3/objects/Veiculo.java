package com.joaogabrielcosta.av3.objects;

import com.joaogabrielcosta.av3.exceptions.ValorInvalidoException;

public class Veiculo implements Comparable<Veiculo> {
    private final String marca, modelo;
    private final int ano;
    private final Motor motor;
    private final double kmRodados;

    public Veiculo(Motor motor, String marca, String modelo, int ano, double kmRodados) throws ValorInvalidoException {
        this.motor = motor;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.kmRodados = kmRodados;
        this.init();
    }

    private void init() throws ValorInvalidoException {
        if(this.ano < 1960 || this.ano > 2024) throw new ValorInvalidoException("O ano precisa ser entre 1960 e 2024.");
        if(this.kmRodados < 0) throw new ValorInvalidoException("A quantidade de km rodados deve ser um número maior que zero.");
        if(this.motor.getNumCilindros() <= 0) throw new ValorInvalidoException("A quantidade de número de cilindros deve ser maior que zero.");
        if(this.motor.getPotencia() <= 0) throw new ValorInvalidoException("A potencia deve ser um número positivo e não nulo.");
        if(!this.marca.equalsIgnoreCase("Rolls-Royce") && !this.marca.equalsIgnoreCase("Bentley"))
            throw new ValorInvalidoException("A marca do veiculo deve ser Bentley ou Rolls-Royce");
    }

    public void mostraInfo() {
        System.out.println("----- VEICULO -----");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ano: " + this.ano);
        System.out.println("Km Rodados: " + this.kmRodados);
        System.out.println(" Motor: ");
        System.out.println("  Potencia: " + this.getMotor().getPotencia());
        System.out.println("  Numero de cilindros: " + this.getMotor().getNumCilindros());
        System.out.println("----- VEICULO -----");
    }

    public Motor getMotor() {
        return motor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getKmRodados() {
        return kmRodados;
    }

    @Override
    public int compareTo(Veiculo o) {
        return Integer.compare(this.ano, o.getAno());
    }
}
