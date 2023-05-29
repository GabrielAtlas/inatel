 package com.joaogabrielcosta.inatel.c206b;

 import com.joaogabrielcosta.inatel.c206b.abstracts.RedeSocial;
 import com.joaogabrielcosta.inatel.c206b.objects.Usuario;
 import com.joaogabrielcosta.inatel.c206b.objects.redes.Facebook;
 import com.joaogabrielcosta.inatel.c206b.objects.redes.GooglePlus;
 import com.joaogabrielcosta.inatel.c206b.objects.redes.Instagram;
 import com.joaogabrielcosta.inatel.c206b.objects.redes.Twitter;

 public class Main {
    public static void main(String[] args) {
        RedeSocial[] redesSociais = new RedeSocial[]{ new Facebook(), new Instagram(), new GooglePlus(), new Twitter() };

        Usuario usuario = new Usuario(redesSociais);
        usuario.setEmail("joao@icloud.com");
        usuario.setNome("João Gabriel Betela da Costa");
        usuario.usarFacebook();
        usuario.usarInstagram();
    }
}