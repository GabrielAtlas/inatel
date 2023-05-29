package com.joaogabrielcosta.c206.av1.objects;

public class Estante {

    private final int idEstante;
    private final char letra;

    private Livro[] livros;

    public Estante(int idEstante, char letra) {
        this.idEstante = idEstante;
        this.letra = letra;
        this.livros = new Livro[120];
    }

    public void addLivro(Livro livro) {
        for(int i = 0; i <= this.livros.length; i++) {
            if(this.livros[i] == null){
                this.livros[i] = livro;
                break;
            }
        }
    }

    public void porcentagemGen() {
        int drama = 0;
        int suspense = 0;

        int totalLivros = 0;

        for(int i = 0; i <= this.livros.length - 1; i++) {
            Livro livro = this.livros[i];
            if(livro != null) {
                if(livro.genLiterario.equalsIgnoreCase("DRAMA")) drama++;
                else suspense++;
                totalLivros++;
            }
        }

        if(totalLivros != 0)
        {
            System.out.println("Drama: " + drama);
            System.out.println("Suspense: " + suspense);
            System.out.println("Total: " + totalLivros);

            double dramaPercentage = ((drama / totalLivros) * 100.00), suspensePercentage = ((suspense / totalLivros) * 100.00);

            System.out.println("% de livros do genero DRAMA: " + dramaPercentage + "%");
            System.out.println("% de livros do genero SUSPENSE: " + suspensePercentage + "%");
        }else System.out.println("Não existem livros cadastrados no sistema.");
    }

    public void livroMaisEMenosPag() {
        Livro maisPaginas = null, menosPaginas = null;
        for(int i = 0; i <= this.livros.length -1; i++) {
            if(maisPaginas == null && this.livros[i] != null) maisPaginas = this.livros[i];
            if(menosPaginas == null && this.livros[i] != null) menosPaginas = this.livros[i];
            if(maisPaginas != null && this.livros[i] != null && maisPaginas.qtdFolhas < this.livros[i].qtdFolhas)
                maisPaginas = this.livros[i];
            if(menosPaginas != null && this.livros[i] != null && menosPaginas.qtdFolhas > this.livros[i].qtdFolhas)
                menosPaginas = this.livros[i];
        }

        if(maisPaginas != null) {
            System.out.println("Livro com mais paginas:");
            maisPaginas.mostrarInfos();
        }

        if(menosPaginas != null) {
            System.out.println("Livro com menos paginas:");
            menosPaginas.mostrarInfos();
        }

        if(maisPaginas == null && menosPaginas == null) System.out.println("Não existe nenhum livro com mais paginas e menos paginas, pois não existe nenhum livro cadastrado.");
    }

    public void mostrarInfos() {
        int qtdLivros = 0;
        for(int i = 0; i <= this.livros.length - 1; i++) {
            if(this.livros[i] != null) qtdLivros++;
        }
        System.out.println("[Estante] ID: " + this.idEstante);
        System.out.println("[Estante] Letra: " + this.letra);
        System.out.println("[Estante] Quantidade de livros: " + qtdLivros);
    }
}
