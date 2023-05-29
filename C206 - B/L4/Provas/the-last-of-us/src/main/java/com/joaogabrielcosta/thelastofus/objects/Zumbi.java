package com.joaogabrielcosta.thelastofus.objects;

import com.joaogabrielcosta.thelastofus.interfaces.Infectar;
import com.joaogabrielcosta.thelastofus.interfaces.Lutar;

public class Zumbi extends Personagem implements Infectar, Lutar {

    private int diasInfeccao;
    private boolean cego;

    public Zumbi(int vida, int diasInfeccao) {
        super(vida);
        this.diasInfeccao = diasInfeccao;
    }

    public void mostrarInfos() {
        System.out.println("[Zumbi] Dias Infeccao: " + diasInfeccao);
        System.out.println("[Zumbi] Cego: " + (cego ? "Sim" : "Não"));
        super.mostrarInfos();
    }

    //TODO: CHAMAR ESTE METODO NA MAIN
    public void transformacao() {
        if(this.diasInfeccao >= 2 && this.diasInfeccao <= 14) {
            this.cego = false;
        }else if(this.diasInfeccao >= 14 && this.diasInfeccao <= 365) {
            this.cego = false;
        }else if(this.diasInfeccao > 365) {
            this.cego = true;
        }
    }

    @Override
    public void infectou() {
        System.out.println("O zumbi mordou e infectou um Humano.");
    }

    @Override
    public void atacar() {
        System.out.println("[Zumbi] O Zumbi com id " + super.getIdPersonagem() + " atacou!");
    }

    @Override
    public void defender() {
        System.out.println("[Zumbi] O Zumbi com id " + super.getIdPersonagem() + " defendeu!");
    }

    public int getDiasInfeccao() {
        return diasInfeccao;
    }

    public void setDiasInfeccao(int diasInfeccao) {
        this.diasInfeccao = diasInfeccao;
    }

    public boolean isCego() {
        return cego;
    }

    public void setCego(boolean cego) {
        this.cego = cego;
    }
}
