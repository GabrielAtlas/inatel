package com.joaogabrielcosta.calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tabela de ações: ");
        System.out.println(" [1] - Soma (+)");
        System.out.println(" [2] - Subtração (-)");
        System.out.println(" [3] - Multiplicação (*)");
        System.out.println(" [4] - Divisão (/)");
        System.out.println(" [5] - Expoencial (^)");
        System.out.println("Digite o tipo de operação que você deseja fazer:");

        int action;

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite apenas números de 1 a 5.");
                scanner.next();
            }
            action = scanner.nextInt();

            if(!(action >= 1 && action <= 5)) System.out.println("Por favor, digite apenas números de 1 a 5.");
        }while(!(action >= 1 && action <= 5));

        double number1, number2;

        System.out.println("Digite o primeiro valor para fazer a operação selecionada anteriormente: ");

        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, digite apenas números.");
            scanner.next();
        }

        number1 = scanner.nextDouble();

        System.out.println("Digite o segundo valor para fazer a operação selecionada anteriormente: ");

        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, digite apenas números.");
            scanner.next();
        }

        number2 = scanner.nextDouble();

        final double result = calculateResult(number1, number2, action);

        System.out.println("O resultado da operação é: " + result);
        
        scanner.close();

    }

    private static double calculateResult(double number1, double number2, int action) {
        switch(action){
            case 1:
                return number1 + number2;
            case 2:
                return number1 - number2;
            case 3:
                return number1 * number2;
            case 4:
                return number1 / number2;
            case 5:
                return Math.pow(number1, number2);
            default:
                return 0;
        }
    }

}