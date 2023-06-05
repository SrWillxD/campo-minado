package br.com.cod3r.campoMinado.modelo;

import br.com.cod3r.campoMinado.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList();
    Campo(int linha, int coluna){
        this.linha=linha;
        this.coluna=coluna;
    }

    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha+deltaColuna;

        if (deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else{
            return false;
        }
    }

    void aterarMarcacao(){
        if(!aberto){
            marcado = !marcado;
        }
    }

    boolean abrir(){
        if(!aberto && !marcado){
            aberto = true;

            if(minado){
                throw new ExplosaoException();
            }

            if(vizinhancaSegura()){
                vizinhos.forEach(vizinhos->vizinhos.abrir());
            }
            return true;
        }else{
            return false;
        }
    }

    boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v->v.minado);
    }

    void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isMarcado(){
        return marcado;
    }
    public boolean isAberto(){
        return aberto;
    }
    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasNaVisinhanca(){
        return vizinhos.stream().filter(vizinho -> vizinho.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "X";
        }else if(aberto && minado){
            return "*";
        }else if(aberto && minasNaVisinhanca() > 0 ){
            return Long.toString(minasNaVisinhanca());
        }else if(aberto ){
            return " ";
        }else{
            return "?";
        }
    }
}
