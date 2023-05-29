package com.joaogabrielcosta.inatel.c206b.objects.redes;

import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;
import com.joaogabrielcosta.inatel.c206b.interfaces.Compartilhamento;
import com.joaogabrielcosta.inatel.c206b.interfaces.VideoConferencia;

public class GooglePlus extends RedeSocial implements VideoConferencia, Compartilhamento {


    @Override
    public void fazStreaming() {
        System.out.println("[Google+] Fazendo streaming pelo Google+...");
    }

    @Override
    public void postarFoto() {
        System.out.println("[Google+] Postando foto pelo Google+...");
    }

    @Override
    public void postarVideo() {
        System.out.println("[Google+] Postando vídeo pelo Google+...");
    }

    @Override
    public void postarComentario() {
        System.out.println("[Google+] Postando comentário pelo Google+...");
    }

    @Override
    public void curtirPublicacao() {
        System.out.println("[Google+] Curtindo publicação pelo Google+...");
    }

    @Override
    public void compartilhar() {
        System.out.println("[Google+] Compartilhando pelo Google+...");
    }

}
