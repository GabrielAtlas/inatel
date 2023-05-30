package com.joaogabrielcosta.av3;

import com.joaogabrielcosta.av3.objects.Arquivo;
import com.joaogabrielcosta.av3.objects.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arquivo arquivo = new Arquivo();
        ArrayList<Livro> livros = null;

        boolean flag = true;

        while(flag) {
            System.out.println("Selecione uma opção para continuar: \n");
            System.out.println("1 - Criar novo livro e salvar no arquivo TXT");
            System.out.println("2 - Ler livros salvos no arquivo TXT");
            System.out.println("3 - Ordenar os livros em ordem crescente de páginas");
            System.out.println("4 - Ordenar os livros em ordem decrescente de páginas.\n");

            try {
                int operation = scanner.nextInt();

                switch(operation) {
                    case 1: // Salvar informações do livro no arquivo
                        try {
                            scanner.nextLine();
                            System.out.println("Digite o nome do livro: ");
                            String nomeLivro = scanner.nextLine();
                            System.out.println("Digite o autor: ");
                            String autor = scanner.nextLine();
                            System.out.println("Digite a editora: ");
                            String editora = scanner.nextLine();
                            System.out.println("Digite a quantidade de páginas: ");
                            int qtdPaginas = scanner.nextInt();

                            Livro livro = new Livro(nomeLivro, autor, editora, qtdPaginas);
                            arquivo.escrever(livro);
                            System.out.println("Livro cadastrado com sucesso.");
                        }catch(Exception error) {
                            System.out.println("Ocorreu um erro durante o cadastro de livros: " + error.getMessage());
                        }
                        break;
                    case 2: // Mostrar informações dos livros salvos no arquivo
                        System.out.println("Lendo informações dos livros cadastrados...");
                        livros = arquivo.ler();
                        System.out.println("Todos os livros cadastrados foram lidos com sucesso.");
                        System.out.println("Total de livros cadastrados: " + livros.size());
                        break;
                    case 3: // Ordenar os livros em ordem crescente de páginas
                        if(livros == null) {
                            System.out.println("Por favor, leia todos os livros do arquivo primeiro para executar esta função.");
                            break;
                        }
                        Collections.sort(livros);
                        System.out.println("Lista ordenada de forma crescente: ");
                        livros.forEach(Livro::mostraInfo);
                        break;
                    case 4: // Ordenar os livros em ordem decrescente de páginas
                        if(livros == null) {
                            System.out.println("Por favor, leia todos os livros do arquivo primeiro para executar esta função.");
                            break;
                        }
                        Collections.sort(livros, Collections.reverseOrder());
                        System.out.println("Lista ordenada de forma descrescente: ");
                        livros.forEach(Livro::mostraInfo);
                        break;
                }

            }catch(Exception error) {
                System.out.println("Por favor, digite uma opção válida.");
            }
        }
    }
}
