package com.joaogabrielcosta.thelastofus.objects;

public class Cidade {

    private String nome;
    private Personagem[] personagens;

    public Cidade(String nome, Personagem[] personagens) {
        this.nome = nome;
        this.personagens = personagens;
    }

    public void adicionarPersonagens(Personagem p) {
        this.personagens[p.getIdPersonagem()] = p;
        System.out.println("[Cidade] Adicionado personagem com id: " + p.getIdPersonagem());
    }

    public void listarPersonagens() {
        System.out.println("");
        int total = 0;
        for(Personagem personagem : this.personagens) {
            if(personagem != null) {
                System.out.println("[Cidade] Personagem ID #" + personagem.getIdPersonagem());
                total++;
            }
        }
        System.out.println("[Cidade] Total de personagens: " + total);
        System.out.println("");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Personagem[] getPersonagens() {
        return personagens;
    }

    public void setPersonagens(Personagem[] personagens) {
        this.personagens = personagens;
    }
}
