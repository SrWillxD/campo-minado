package br.com.cod3r.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;
    private final List<Campo> campos = new ArrayList();

    public Tabuleiro(int linhas, int colunas, int minas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();

    }

    private void gerarCampos(){
        for(int linha = 0; linha < linhas; linha++){
            for(int colunas = 0; colunas < this.colunas; colunas++){
                campos.add(new Campo(linha, colunas));
            }
        }
    }

    private void associarVizinhos(){

    }

    private void sortearMinas(){
        
    }



}
