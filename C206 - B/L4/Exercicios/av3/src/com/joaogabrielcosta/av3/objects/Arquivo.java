package com.joaogabrielcosta.av3.objects;

import com.joaogabrielcosta.av3.exceptions.InfoInvalidaException;

import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    public void escrever(Livro livro) {
        BufferedWriter bw = null;
        try{
            OutputStream os = new FileOutputStream("livros.txt",true);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write("+LIVRO+");
            bw.newLine();
            bw.write(livro.getNome() + "\n");
            bw.write(livro.getAutor()  + "\n");
            bw.write(livro.getEditora() + "\n");
            bw.write(livro.getQtdPaginas() + "\n");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bw != null) bw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Livro> ler() {

        BufferedReader br = null;

        ArrayList<Livro> livros = new ArrayList<>();

        try{
            InputStream is = new FileInputStream("livros.txt");
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line = br.readLine();

            while(line != null){
                if(line.contains("+LIVRO+")){
                    String livroNome = br.readLine(), livroAutor = br.readLine(), livroEditora = br.readLine();

                    int livroPaginas = 0;

                    try {
                        livroPaginas = Integer.parseInt(br.readLine());
                    }catch(Exception error) {}

                    System.out.println("Lendo: " + livroNome + " - " + livroAutor + " - " + livroEditora + " - " + livroPaginas);

                    try {
                        Livro livro = new Livro(livroNome, livroAutor, livroEditora, livroPaginas);
                        livros.add(livro);
                    }catch(InfoInvalidaException exception) {
                        System.out.println("Não foi possível carregar o livro: " + livroNome + ":");
                        System.out.println(exception.getMessage());
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }

        return livros;
    }

}
