package com.joaogabrielcosta.thelastofus.objects;

public abstract class Personagem {

    private static int numPersonagens = -1;

    private int vida, idPersonagem;

    public Personagem(int vida) {
        numPersonagens = numPersonagens + 1;
        this.vida = vida;
        this.idPersonagem = numPersonagens;
    }

    public void mostrarInfos() {
        System.out.println("[Personagem] NumPersonagens: " + numPersonagens);
        System.out.println("[Personagem] Vida: " + vida);
        System.out.println("[Personagem] idPersonagem: " + idPersonagem);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }
}
