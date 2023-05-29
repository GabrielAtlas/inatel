package com.joaogabrielcosta.inatel.c206b.objects.redes;

import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;
import com.joaogabrielcosta.inatel.c206b.interfaces.Compartilhamento;

public class Twitter extends RedeSocial implements Compartilhamento {

    @Override
    public void postarFoto() {
        System.out.println("[Twitter] Postando foto pelo Twitter...");
    }

    @Override
    public void postarVideo() {
        System.out.println("[Twitter] Postando vídeo pelo Twitter...");
    }

    @Override
    public void postarComentario() {
        System.out.println("[Twitter] Postando comentario pelo Twitter...");
    }

    @Override
    public void compartilhar() {
        System.out.println("[Twitter] Compartilhando pelo Twitter...");
    }

    public void curtirPublicacao() {
        System.out.println("[Twitter] Curtiu uma publicacao no Twitter...");
    }

}
