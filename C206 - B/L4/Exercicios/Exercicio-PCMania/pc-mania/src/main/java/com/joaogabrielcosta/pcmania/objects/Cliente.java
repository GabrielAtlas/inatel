package com.joaogabrielcosta.pcmania.objects;

public class Cliente {

    public final String nome;
    public final long cpf;

    public Computador[] compras;

    public Cliente(String nome, long cpf, Computador[] compras) {
        this.nome = nome;
        this.cpf = cpf;
        this.compras = compras;
    }

    public float calculaTotalCompra() {
        float totalCompra = 0.00f;

        for (Computador computer : compras) {
            totalCompra += computer.preco;
        }

        return totalCompra;
    }

}
