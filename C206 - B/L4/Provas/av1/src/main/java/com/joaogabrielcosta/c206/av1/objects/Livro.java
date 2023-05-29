package com.joaogabrielcosta.c206.av1.objects;

public class Livro {

    public final String titulo, genLiterario, editora;
    public final int qtdFolhas;

    public final Autor autor;

    public Livro (String titulo, String genLiterario, String editora, int qtdFolhas, Autor autor) {
        this.titulo = titulo;
        this.genLiterario = genLiterario;
        this.editora = editora;
        this.qtdFolhas = qtdFolhas;
        this.autor = autor;
    }

    public void mostrarInfos() {
        System.out.println("[Livro] Titulo: " + this.titulo);
        System.out.println("[Livro] Gen Literario: " + this.genLiterario);
        System.out.println("[Livro] Editora: " + this.editora);
        System.out.println("[Livro] Quantidade de Folhas: " + this.qtdFolhas);
    }
}
