package com.joaogabrielcosta.av3;

import com.joaogabrielcosta.av3.objects.Arquivo;
import com.joaogabrielcosta.av3.objects.Motor;
import com.joaogabrielcosta.av3.objects.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arquivo arquivo = new Arquivo();
        ArrayList<Veiculo> veiculos = null;

        boolean flag = true;

        while(flag) {
            System.out.println("Selecione uma opção para continuar: \n");
            System.out.println("1 - Criar novo veiculo e salvar no arquivo TXT");
            System.out.println("2 - Ler veiculos salvos no arquivo TXT");
            System.out.println("3 - Ordenar os veiculos em ordem decrescente de ano.\n");
            System.out.println("4 - Mostra a quantidade de veiculos de cada marca disponível em estoque\n");
            System.out.println("5 - Sair do programa");

            try {
                int operation = scanner.nextInt();

                switch(operation) {
                    case 1: // Criar um novo veiculo no banco de dados
                        try {
                            System.out.println("Digite o ano: ");
                            int ano = scanner.nextInt();
                            System.out.println("Digite o numero de cilindros do motor: ");
                            int numCilindros = scanner.nextInt();
                            System.out.println("Digite a potencia do motor: ");
                            double potencia = scanner.nextDouble();
                            System.out.println("Digite a quantidade de KM rodados: ");
                            double kmRodados = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.println("Digite a marca do veiculo: ");
                            String marca = scanner.nextLine();
                            System.out.println("Digite o modelo do veiculo: ");
                            String modelo = scanner.nextLine();

                            Veiculo veiculo = new Veiculo(new Motor(potencia, numCilindros), marca, modelo, ano, kmRodados);
                            arquivo.escrever(veiculo);
                            System.out.println("Veiculo cadastrado com sucesso.");
                        }catch(Exception error) {
                            System.out.println("Ocorreu um erro durante o cadastro do veiculo: " + error.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Lendo informações dos livros cadastrados...");
                        veiculos = arquivo.ler();
                        System.out.println("Todos os veiculos cadastrados foram lidos com sucesso.");
                        System.out.println("Total de veiculos cadastrados: " + veiculos.size());
                        veiculos.forEach(Veiculo::mostraInfo);
                        break;
                    case 3:
                        if(veiculos == null) {
                            System.out.println("Por favor, leia todos os livros do arquivo primeiro para executar esta função.");
                            break;
                        }
                        Collections.sort(veiculos, Collections.reverseOrder());
                        System.out.println("Lista ordenada de forma descrescente: ");
                        veiculos.forEach(Veiculo::mostraInfo);
                        break;
                    case 4:
                        if(veiculos == null) {
                            System.out.println("Por favor, leia todos os livros do arquivo primeiro para executar esta função.");
                            break;
                        }
                        System.out.println("Quantidade de veiculos de cada marca em estoque: ");
                        int bentleyAmount = 0, rollsRoyceAmount = 0;

                        for(Veiculo veiculo : veiculos) {
                            if(veiculo.getMarca().equalsIgnoreCase("Rolls-Royce")) rollsRoyceAmount++;
                            else bentleyAmount++;
                        }

                        System.out.println(" - Rolls-Royce: " + rollsRoyceAmount);
                        System.out.println(" - Bentley: " + bentleyAmount);

                        break;
                    case 5:
                        flag = false;
                        break;
                }

            }catch(Exception error) {
                System.out.println("Por favor, digite uma opção válida.");
            }
        }
        System.out.println("Obrigado por utilizar :)");
    }
}