package com.joaogabrielcosta.inatel.c206b.objects;

import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;
import com.joaogabrielcosta.inatel.c206b.objects.redes.Facebook;
import com.joaogabrielcosta.inatel.c206b.objects.redes.GooglePlus;
import com.joaogabrielcosta.inatel.c206b.objects.redes.Instagram;
import com.joaogabrielcosta.inatel.c206b.objects.redes.Twitter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Usuario {

    private static final Random random = new Random();

    private @Setter String nome, email;
    private RedeSocial[] redesSociais;

    public Usuario(RedeSocial[] redesSociais) {
        this.redesSociais = redesSociais;
        // Gerando senhas aleatorias e numero de amigos aleatorios
        Arrays.asList(this.redesSociais).stream().forEach(redeSocial -> {
            redeSocial.setSenha(UUID.randomUUID().toString());
            redeSocial.setNumAmigos(random.nextInt(200));
        });
    }

    public void usarInstagram() {
        for(RedeSocial rede : redesSociais) {
            // Executando metodos na instancia do Instagram

            try {
                Instagram instagram = (Instagram) rede;
                instagram.curtirPublicacao();
                instagram.postarFoto();
                instagram.postarVideo();
            }catch(Exception err) {
                //Ignoring
            }

        }

    }

    public void usarFacebook() {
        for(RedeSocial rede : redesSociais) {
            // Executando metodos na instancia do Facebook

            try {
                Facebook facebook = (Facebook) rede;
                facebook.compartilhar();
                facebook.curtirPublicacao();
                facebook.fazStreaming();
            }catch(Exception err) {
                //Ignoring
            }

        }

    }

    public void usarTwitter() {
        for(RedeSocial rede : redesSociais) {
            // Executando metodos na instancia do Twitter

            try {
                Twitter twitter = (Twitter) rede;
                twitter.postarComentario();
                twitter.compartilhar();
                twitter.postarVideo();
            }catch(Exception err) {
                //Ignoring
            }

        }

    }

    public void usarGooglePlus() {
        for(RedeSocial rede : redesSociais) {
            // Executando metodos na instancia do Google+

            try {
                GooglePlus googlPlus = (GooglePlus) rede;
                googlPlus.fazStreaming();
                googlPlus.postarVideo();
                googlPlus.curtirPublicacao();
            }catch(Exception err) {
                //Ignoring
            }

        }

    }

}
