package com.joaogabrielcosta.inatel.c206b.objects.redes;

import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;
import com.joaogabrielcosta.inatel.c206b.interfaces.Compartilhamento;
import com.joaogabrielcosta.inatel.c206b.interfaces.VideoConferencia;

public class Facebook extends RedeSocial implements VideoConferencia, Compartilhamento {

    @Override
    public void fazStreaming() {
        System.out.println("[Facebook] Fazendo streaming pelo Facebook...");
    }

    @Override
    public void postarFoto() {
        System.out.println("[Facebook] Postando foto pelo Facebook...");
    }

    @Override
    public void postarVideo() {
        System.out.println("[Facebook] Postando vídeo pelo Facebook...");
    }

    @Override
    public void postarComentario() {
        System.out.println("[Facebook] Postando comentário pelo Facebook...");
    }

    @Override
    public void curtirPublicacao() {
        System.out.println("[Facebook] Curtindo publicação pelo Facebook...");
    }

    @Override
    public void compartilhar() {
        System.out.println("[Facebook] Compartilhando pelo Facebook...");
    }
}
