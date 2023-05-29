package com.joaogabrielcosta.inatel.c206b.abstracts;

import lombok.Setter;

public abstract class RedeSocial {

    protected @Setter String senha;
    protected @Setter int numAmigos;

    public abstract void postarFoto();
    public abstract void postarVideo();
    public abstract void postarComentario();
    public void curtirPublicacao() {
        System.out.println("Curtiu uma publicacao.");
    }

}
