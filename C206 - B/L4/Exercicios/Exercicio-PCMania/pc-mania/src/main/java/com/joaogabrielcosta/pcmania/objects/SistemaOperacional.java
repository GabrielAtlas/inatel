package com.joaogabrielcosta.pcmania.objects;

public class SistemaOperacional {

    public final String nome;
    public final int tipo;

    public SistemaOperacional(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }
}
