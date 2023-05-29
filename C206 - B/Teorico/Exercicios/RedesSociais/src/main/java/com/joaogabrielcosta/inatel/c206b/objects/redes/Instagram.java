package com.joaogabrielcosta.inatel.c206b.objects.redes;

import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;

public class Instagram extends RedeSocial {

    @Override
    public void postarFoto() {
        System.out.println("[Instagram] Postando foto pelo Instagram...");
    }

    @Override
    public void postarVideo() {
        System.out.println("[Instagram] Postando vídeo pelo Instagram...");
    }

    @Override
    public void postarComentario() {
        System.out.println("[Instagram] Postando comentário pelo Instagram...");
    }

    @Override
    public void curtirPublicacao() {
        System.out.println("[Instagram] Curtiu uma publicacao pelo Instagram...");
    }

}
