package com.joaogabrielcosta.thelastofus.objects;

import com.joaogabrielcosta.thelastofus.interfaces.Aprimorar;
import com.joaogabrielcosta.thelastofus.interfaces.Lutar;

public class Humano extends Personagem implements Lutar, Aprimorar {

    private String nome;
    private int idade;
    private double energia, distanciaEscuta;
    private boolean vagalume;

    private Arma arma;

    public Humano(int vida, String nome, int idade, double energia, double distanciaEscuta, boolean vagalume, Arma arma) {
        super(vida);
        this.nome = nome;
        this.idade = idade;
        this.energia = energia;
        this.distanciaEscuta = distanciaEscuta;
        this.vagalume = vagalume;
        this.arma = arma;
    }

    public void mostrarInfos() {
        System.out.println();
        System.out.println("[Humano] Nome: " + nome);
        System.out.println("[Humano] Idade: " + idade);
        System.out.println("[Humano] Energia: " + energia);
        System.out.println("[Humano] Distancia Escuta: " + distanciaEscuta);
        System.out.println("[Humano] Vagalume: " + (vagalume ? "Sim" : "Não"));
        System.out.println("[Humano] Possui Arma: " + (arma == null ? "Não" : "Sim (" + arma.getTipoArma() + " - " + arma.getForca() + " de força)"));
        super.mostrarInfos();
        System.out.println();
    }

    public void addArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void atacar() {
        System.out.println("[Humano] " + this.nome + " atacou!");
        System.out.println("[Humano] Possui Arma: " + (arma == null ? "Não" : "Sim (" + arma.getTipoArma() + " - " + arma.getForca() + " de força)"));
    }

    @Override
    public void defender() {
        System.out.println("[Humano] " + this.nome + " defendeu!");
        System.out.println("[Humano] Possui Arma: " + (arma == null ? "Não" : "Sim (" + arma.getTipoArma() + " - " + arma.getForca() + " de força)"));
    }

    @Override
    public void modificarArma() {
        if(arma == null) {
            System.out.println("O parça vc n tem arma, adicione uma arma primeiro antes de executar este metodo.");
            return;
        }

        arma.setForca(arma.getForca() + 5);
        System.out.println("[Humano] Arma Aprimorada com sucesso. " + "(" + arma.getTipoArma() + " - " + arma.getForca() + " de força" + ")");
    }

    @Override
    public void modificarHabilidade(int qtdPilulas, String tipoHabilidade) {
        if(qtdPilulas <= 0) {
            System.out.println("Quantidade de pílulas inválidas, favor informar um valor maior que 0");
            return;
        }

        switch(tipoHabilidade.toUpperCase()) {
            case "ENERGIA":
                if(qtdPilulas <= 10) {
                    this.setEnergia(this.getEnergia() + (this.getEnergia() * 0.02));
                    System.out.println("[Humano] Sua energia foi aumentada em 2% pois você modificou a habilidade com " + qtdPilulas + (qtdPilulas > 1 ? " pilulas" : "pilula"));
                    return;
                }
                this.setEnergia(this.getEnergia() + (this.getEnergia() * 0.04));
                System.out.println("[Humano] Sua energia foi aumentada em 4% pois você modificou a habilidade com " + qtdPilulas + (qtdPilulas > 1 ? " pilulas" : "pilula"));
                break;
            case "DISTANCIA_DE_ESCUTA":
                if(qtdPilulas <= 10) {
                    this.setDistanciaEscuta(this.getDistanciaEscuta() + 0.2);
                    System.out.println("[Humano] Sua distância de escuta foi aumentada em 0.2 pois você modificou a habilidade com " + qtdPilulas + (qtdPilulas > 1 ? " pilulas" : "pilula"));
                    return;
                }
                this.setDistanciaEscuta(this.getDistanciaEscuta() + 0.5);
                System.out.println("[Humano] Sua distância de escuta foi aumentada em 0.5 pois você modificou a habilidade com " + qtdPilulas + (qtdPilulas > 1 ? " pilulas" : "pilula"));
                break;

            default: break;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getDistanciaEscuta() {
        return distanciaEscuta;
    }

    public void setDistanciaEscuta(double distanciaEscuta) {
        this.distanciaEscuta = distanciaEscuta;
    }

    public boolean isVagalume() {
        return vagalume;
    }

    public void setVagalume(boolean vagalume) {
        this.vagalume = vagalume;
    }

}
