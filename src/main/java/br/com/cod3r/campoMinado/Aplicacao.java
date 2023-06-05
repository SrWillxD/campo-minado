package br.com.cod3r.campoMinado;

import br.com.cod3r.campoMinado.modelo.Tabuleiro;

public class Aplicacao{
    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

        System.out.println(tabuleiro);
    }
}
