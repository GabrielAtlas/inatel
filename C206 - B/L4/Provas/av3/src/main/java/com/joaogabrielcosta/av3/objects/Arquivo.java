package com.joaogabrielcosta.av3.objects;

import com.joaogabrielcosta.av3.exceptions.ValorInvalidoException;

import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    public void escrever(Veiculo veiculo) {
        BufferedWriter bw = null;
        try{
            OutputStream os = new FileOutputStream("veiculos.txt",true);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write("+VEICULO+");
            bw.newLine();
            bw.write(veiculo.getMarca() + "\n");
            bw.write(veiculo.getModelo()  + "\n");
            bw.write(veiculo.getAno() + "\n");
            bw.write(veiculo.getKmRodados() + "\n");

            // Salvando motor
            bw.write(veiculo.getMotor().getPotencia() + "\n");
            bw.write(veiculo.getMotor().getNumCilindros() + "\n");

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

    public ArrayList<Veiculo> ler() {

        BufferedReader br = null;

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        try{
            InputStream is = new FileInputStream("veiculos.txt");
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line = br.readLine();

            while(line != null){
                if(line.contains("+VEICULO+")){
                    String marca = br.readLine(), modelo = br.readLine();

                    int ano = -1, numCilindros = -1;
                    double kmRodados = -1, potencia = -1;

                    try {
                        ano = Integer.parseInt(br.readLine());
                        kmRodados = Double.parseDouble(br.readLine());
                        potencia = Double.parseDouble(br.readLine());
                        numCilindros = Integer.parseInt(br.readLine());
                    }catch(Exception error) {}

                    System.out.println("Lendo: " + marca + " - " + modelo + " - " + ano + " - " + numCilindros + " - " + kmRodados + " - " + potencia);

                    try {
                        Veiculo veiculo = new Veiculo(new Motor(potencia, numCilindros), marca, modelo, ano, kmRodados);
                        veiculos.add(veiculo);
                    }catch(ValorInvalidoException exception) {
                        System.out.println("Não foi possível carregar o veiculo: " + marca + ":");
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

        return veiculos;
    }

}