package br.com.cod3r.campoMinado.visao;

import br.com.cod3r.campoMinado.excecao.ExplosaoException;
import br.com.cod3r.campoMinado.excecao.SairException;
import br.com.cod3r.campoMinado.modelo.Tabuleiro;

import java.util.Scanner;

public class TabuleiroConsole{
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo(){
        try{
            boolean continuar = true;

            while(continuar){
                cicloDoJogo();
                System.out.println("Outra partida? (S/n)");
                String resposta = entrada.nextLine();

                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                }else{
                    tabuleiro.reiniciar();
                }
            }
        }catch(SairException e){
            System.out.println("Tchau!!!");
        }finally{
            entrada.close();
        }
    }

    private void cicloDoJogo(){
        try{
            while(!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y): ");
            }

            System.out.println("Você ganhou!!");
        }catch(ExplosaoException e){
            System.out.println("Você perdeu!");
        }
    }

    private String capturarValorDigitado(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
