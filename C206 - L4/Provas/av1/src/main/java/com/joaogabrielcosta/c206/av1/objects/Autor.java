package com.joaogabrielcosta.c206.av1.objects;

public class Autor {

    private final String nome, profissao;
    private final int anoNascimento;

    public Autor(String nome, String profissao, int anoNascimento) {
        this.nome = nome;
        this.profissao = profissao;
        this.anoNascimento = anoNascimento;
    }

    public void mostrarInfos() {
        System.out.println("[Autor] Nome: " + this.nome);
        System.out.println("[Autor] Profissao: " + this.profissao);
        System.out.println("[Autor] Ano nascimento: " + this.anoNascimento);
    }

}
