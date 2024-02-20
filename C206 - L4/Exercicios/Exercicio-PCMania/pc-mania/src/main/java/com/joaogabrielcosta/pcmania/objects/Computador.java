package com.joaogabrielcosta.pcmania.objects;

import java.util.List;

public class Computador {

    public final String marca;
    public final float preco;
    public final SistemaOperacional sistema;
    public final HardwareBasico[] hardware;
    public MemoriaUSB memoriaUSB;

    public Computador(String marca, float preco, SistemaOperacional sistema, HardwareBasico[] hardware) {
        this.marca = marca;
        this.preco = preco;
        this.sistema = sistema;
        this.hardware = hardware;
    }

    public void addMemoriaUSB(MemoriaUSB musb) {
        this.memoriaUSB = musb;
    }

    public void mostraPCConfigs() {
        System.out.println("");
        System.out.println(" Computador: " + this.marca);
        System.out.println(" Valor pago: " + this.preco);
        System.out.println(" Sistema Operacional: " + this.sistema.getNome() + " (" + this.sistema.getTipo() + "bits)");
        System.out.println(" Hardware:");
        for (HardwareBasico hardwareBasico : hardware) {
            System.out.println("  - " + hardwareBasico.getNome() + " - " + hardwareBasico.getCapacidade());
        }
        System.out.println(" Memória USB: " + (this.memoriaUSB == null ? "Não possui" : this.memoriaUSB.getNome() + " - " + this.memoriaUSB.getCapacidade() + "GB"));
        System.out.println("");
    }

}
