package com.joaogabrielcosta.pcmania.objects;

public class MemoriaUSB {

    public final String nome;
    public final int capacidade;

    public MemoriaUSB(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
