package com.joaogabrielcosta.c206.av1;

import com.joaogabrielcosta.c206.av1.objects.Autor;
import com.joaogabrielcosta.c206.av1.objects.Estante;
import com.joaogabrielcosta.c206.av1.objects.Livro;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int idEstante;
        char c;

        log("Digite o ID da estante: ");
        idEstante = scanner.nextInt();
        scanner.nextLine();
        log("Digite a letra da estante: ");
        c = scanner.nextLine().charAt(0);

        Estante estante = new Estante(idEstante, c);

        int action = callHome(scanner, idEstante, true);

        while(action != 5) {
            switch(action) {
                case 1:
                    scanner.nextLine();
                    String nome, genLiterario, editora;
                    String autorNome, autorProfissao;
                    int qtdFolhas;
                    int autorNascimento;

                    log("Digite o nome do livro que deseja adicionar a estante: ");
                    nome = scanner.next();

                    do {
                        log("Digite o genero literario do livro: ");
                        genLiterario = scanner.next();
                        if(!isValidGenero(genLiterario)) log("Genero literario inválido, os tipos válidos são: DRAMA, SUSPENSE");
                    }while(!isValidGenero(genLiterario));

                    log("Digite a editora do livro: ");
                    editora = scanner.next();
                    log("Digite o nome do autor do livro: ");
                    autorNome = scanner.next();
                    log("Digite a profissao do autor do livro: ");
                    autorProfissao = scanner.next();
                    log("Digite a idade do autor do livro: ");
                    autorNascimento = scanner.nextInt();
                    log("Digite a quantidade de folhas do livro para finalizar: ");
                    qtdFolhas = scanner.nextInt();

                    Livro livro = new Livro(nome, genLiterario, editora, qtdFolhas, new Autor(autorNome, autorProfissao, autorNascimento));
                    estante.addLivro(livro);
                    log("Livro adicionado com sucesso na estante #" + idEstante + ".");
                    break;
                case 2:

                    log("Informações da estante #" + idEstante + ": ");
                    estante.mostrarInfos();
                    break;
                case 3:
                    log("Porcentagem de livros de DRAMA e SUSPENSE da estante #1: ");
                    estante.porcentagemGen();
                    break;
                case 4:
                    log("Livros com mais paginas e menos paginas: ");
                    estante.livroMaisEMenosPag();
                    break;
            }
            action = callHome(scanner, idEstante, false);
        }

        log("");
        log("Você saiu do sistema com sucesso.");

    }

    private static void log(String message) {
        System.out.println("[Sistema de Livros] " + message);
    }

    private static boolean isValidGenero(String genero) {
        return genero.equalsIgnoreCase("drama") || genero.equalsIgnoreCase("suspense");
    }

    private static int callHome(Scanner scanner, int estanteId, boolean first) {


        if(first) {
            log("");
            log("Seja bem vindo ao sistema, digite o que deseja fazer:");
            log("");
            log("Ações disponíveis:");
            log(" [1] Adicionar um novo livro a estante com id #" + estanteId);
            log(" [2] Mostrar informações da estante com id #" + estanteId);
            log(" [3] Mostrar % de livros de cada genero da estante com id #" + estanteId);
            log(" [4] Mostrar informações do livro e do autor do livro com mais e menos páginas.");
            log(" [5] Sair");
            log("");
        }

        if(!first) log("Digite sua próxima ação (1,2,3,4) ou 5 para sair: ");

        return scanner.nextInt();
    }


}