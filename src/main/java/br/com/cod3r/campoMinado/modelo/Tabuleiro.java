package br.com.cod3r.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        for(Campo c1 : campos){
            for(Campo c2 : campos){
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas(){
        long minasArmadas = 0;
        Predicate<Campo> minado = Campo::isMinado;
        do{
            minasArmadas = campos.stream().filter(minado).count();
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
        }while(minasArmadas<minas);
    }



}
