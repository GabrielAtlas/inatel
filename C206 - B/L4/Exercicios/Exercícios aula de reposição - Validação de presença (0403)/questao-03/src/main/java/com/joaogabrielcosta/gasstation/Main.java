package com.joaogabrielcosta.gasstation;

import com.joaogabrielcosta.gasstation.enums.FuelType;
import com.joaogabrielcosta.gasstation.objects.FuelBomb;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<FuelType> fuelTypesList = Arrays.asList(FuelType.values());

        System.out.println("Tipos de combustiveis cadastrados: ");

        fuelTypesList.forEach(fuelType->System.out.println(" [" + fuelType.getIdentifier() + "] " + fuelType.name()));
        System.out.println("Digite o tipo de combustível que você deseja utilizar para abastecer seu carro: ");

        char type = scanner.next().charAt(0);

        Optional<FuelType> selectedType = fuelTypesList.stream().filter(fuelType->fuelType.getIdentifier() == type).findFirst();

        if(!selectedType.isPresent()){
            System.out.println("O tipo de combustivel nao existe, tente novamente.");
            return;
        }

        FuelBomb bomb = selectedType.get().getFuelBomb();

        int liters;

        do {
            System.out.print("Digite a quantidade de litros combustivel: ");

            while(!scanner.hasNextInt()) {
                System.out.println("Por favor, digite apenas números para a NP1.");
                scanner.next();
            }

            liters = scanner.nextInt();
            if(liters < 0) System.out.println("A quantidade de litros não pode ser um numero menor que zero, digite novamente.");
        }while(liters < 0);

        final double price = bomb.calculatePrice(liters);

        System.out.println("O preço que o cliente deve pagar é de: R$" + price);

        scanner.close();

    }

}