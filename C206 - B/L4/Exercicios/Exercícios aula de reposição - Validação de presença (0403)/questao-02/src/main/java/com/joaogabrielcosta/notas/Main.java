package com.joaogabrielcosta.notas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a NP1: ");

        float np1, np2;

        do {

            while(!scanner.hasNextFloat()) {
                System.out.println("Por favor, digite apenas números para a NP1.");
                scanner.next();
            }

            np1 = scanner.nextFloat();
            if(np1 < 0) System.out.println("A NP1 não pode ser um numero menor que zero, digite novamente.");
        }while(np1 < 0);

        System.out.println("Digite a NP2: ");

        do {

            while(!scanner.hasNextFloat()) {
                System.out.println("Por favor, digite apenas números para a NP2.");
                scanner.next();
            }

            np2 = scanner.nextFloat();
            if(np2 < 0) System.out.println("A NP2 não pode ser um numero menor que zero, digite novamente.");
        }while(np2 < 0);

        final float result = (np1 + np2) / 2.0f;

        System.out.println("O resultado é: " + result);

        scanner.close();

    }
}