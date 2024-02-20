package com.joaogabrielcosta.pcmania.objects;

public class HardwareBasico {

    public final String nome;
    public final float capacidade;

    public HardwareBasico(String nome, float capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public float getCapacidade() {
        return capacidade;
    }
}
