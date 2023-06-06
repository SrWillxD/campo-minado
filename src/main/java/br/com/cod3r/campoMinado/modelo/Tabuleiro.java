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
    public Campo getCampos(int linha, int coluna) {
        for (Campo campo : campos) {
            if (campo.getLinha() == linha && campo.getColuna() == coluna) {
                return campo;
            }
        }
        throw new IllegalArgumentException("Campo não encontrado para as coordenadas especificadas: " + linha + ", " + coluna);
    }

    public int getQuantidadeDeCampos(){
        return campos.size();
    }

    public void abrir(int linha, int coluna){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.abrir());
    }
    public void alterarMarcacao(int linha, int coluna){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alterarMarcacao());
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
        long minasArmadas;
        Predicate<Campo> minado = Campo::isMinado;
        do{
            minasArmadas = campos.stream().filter(minado).count();
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
        }while(minasArmadas<minas);
    }

    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c ->c.objetivoAlcancado());
    }

    public void reiniciar(){
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    public String  toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(int l = 0; l < linhas; l++){
            for(int c = 0; c < colunas; c++){
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
