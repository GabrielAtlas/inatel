package com.joaogabrielcosta.pcmania;

import com.joaogabrielcosta.pcmania.objects.*;

import java.util.Scanner;

public class PCMania {

    public static void main(String[] args) {

        Computador promocao1, promocao2, promocao3;

        promocao1 = new Computador("Positivo", 2300, new SistemaOperacional("Linux Ubuntu", 32),
                new HardwareBasico[] {
                        new HardwareBasico("Pentium Core i3", 2200),
                        new HardwareBasico("Memória RAM", 8),
                        new HardwareBasico("HD", 500)
                });
        promocao1.addMemoriaUSB(new MemoriaUSB("Pen-drive", 16));

        promocao2 =
                new Computador("Acer", 5800, new SistemaOperacional("Windows 8", 64),
                        new HardwareBasico[] {
                                new HardwareBasico("Pentium Core i5", 3370),
                                new HardwareBasico("Memória RAM", 16),
                                new HardwareBasico("HD", 1000)
                        });
        promocao2.addMemoriaUSB(new MemoriaUSB("Pen-drive", 32));

        promocao3 =
                new Computador("Vaio", 1800, new SistemaOperacional("Windows 10", 64),
                        new HardwareBasico[] {
                                new HardwareBasico("Pentium Core i7", 4500),
                                new HardwareBasico("Memória RAM", 32),
                                new HardwareBasico("HD", 2000)
                        });
        promocao3.addMemoriaUSB(new MemoriaUSB("HD Externo", 1000));

        printHome();

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        Cliente cliente = null;

        while(input != 0) {

            if(cliente == null) {
                scanner.reset();
                System.out.println("Para concluir sua compra, você precisa finalizar seu cadastro, para isso digite seu nome: ");
                String nome = scanner.next();
                System.out.println("Agora digite seu CPF: ");
                long cpf = scanner.nextLong();
                cliente = new Cliente(nome, cpf, new Computador[4]);
            }

            comprarPC(cliente, (input == 1 ? promocao1 : (input == 2 ? promocao2 : promocao3)));

            System.out.println("Digite uma nova ação para continuar (1,2,3 ou 0 para sair): ");
            input = scanner.nextInt();

        }

        System.out.println("Você saiu do sistema com sucesso.");
    }

    private static void comprarPC(Cliente cliente, Computador computador) {
        int totalCompras = 0;

        for(Computador computer : cliente.compras) {
            if(computer != null) totalCompras++;
        }

        if(totalCompras >= 3) {
            System.out.println("Você já comprou todas as promoções possiveis, não da mais parça :/");
            return;
        }

        cliente.compras[totalCompras + 1] = computador;

        System.out.println("Você comprou o PC " + computador.marca + ".");
        System.out.println("");
        System.out.println("Detalhes da compra:");
        computador.mostraPCConfigs();
    }

    private static void printHome() {
        System.out.println("Olá, seja bem vindo a loja PC Mania.");
        System.out.println("");
        System.out.println("Selecione uma ação do menu abaixo para continuar:");
        System.out.println(" [1] Comprar promoção 1 (PC Positivo)");
        System.out.println(" [2] Comprar promoção 2 (PC Acer)");
        System.out.println(" [3] Comprar promoção 3 (PC Vaio)");
        System.out.println(" [0] Sair do sistema ");
        System.out.println("");
    }

}