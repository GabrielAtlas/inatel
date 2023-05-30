package com.joaogabrielcosta.av3.objects;

import com.joaogabrielcosta.av3.exceptions.InfoInvalidaException;

public class Livro implements Comparable<Livro> {
    private final String nome, autor, editora;
    private final int qtdPaginas;

    public Livro(String nome, String autor, String editora, int qtdPaginas) throws InfoInvalidaException {
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
        this.qtdPaginas = qtdPaginas;
        this.init(editora, qtdPaginas);
    }

    private void init(String editora, int qtdPaginas) throws InfoInvalidaException {
        if(!editora.equalsIgnoreCase("Leya") && !editora.equalsIgnoreCase("Arqueiro") && !editora.equalsIgnoreCase("Rocco"))
            throw new InfoInvalidaException("A editora precisa ser uma das editoras disponíveis: Rocco, Leya ou Arqueiro.");
        if(qtdPaginas < 1) throw new InfoInvalidaException("O livro precisa ter pelo menos uma página.");
    }

    public void mostraInfo() {
        System.out.println("----- LIVRO -----");
        System.out.println("Nome: " + this.nome);
        System.out.println("Autor: " + this.autor);
        System.out.println("Editora: " + this.editora);
        System.out.println("Qtd de páginas: " + this.qtdPaginas);
        System.out.println("----- LIVRO -----");
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    @Override
    public int compareTo(Livro o) {
        return Integer.compare(this.qtdPaginas, o.qtdPaginas);
    }
}
