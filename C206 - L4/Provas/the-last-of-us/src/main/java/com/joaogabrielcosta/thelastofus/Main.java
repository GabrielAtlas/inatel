package com.joaogabrielcosta.thelastofus;

import com.joaogabrielcosta.thelastofus.objects.*;

public class Main {
    public static void main(String[] args) {
        Cidade cidade = new Cidade("Santa Rita do Sapucaí", new Personagem[50]);

        Zumbi zumbi = new Zumbi(20, 0);
        zumbi.transformacao();
        zumbi.mostrarInfos();
        zumbi.atacar();
        zumbi.defender();
        zumbi.infectou();

        cidade.adicionarPersonagens(zumbi);

        Humano humano = new Humano(20, "John Doe", 20, 50, 2, false, null);
        humano.mostrarInfos();
        humano.atacar();
        humano.defender();
        humano.addArma(new Arma("Espada de Madeira", 20));
        humano.modificarHabilidade(20, "ENERGIA");
        humano.modificarHabilidade(5, "DISTANCIA_DE_ESCUTA");
        humano.modificarArma();
        humano.mostrarInfos();

        cidade.adicionarPersonagens(humano);
        cidade.listarPersonagens();

    }
}