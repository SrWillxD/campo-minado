package br.com.cod3r.campoMinado.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTest{
    private Tabuleiro tabuleiro;
    @BeforeEach
    void iniciarTabuleiro(){
        tabuleiro = new Tabuleiro(10,10, 6);
    }

    @Test
    void testeAbrir(){
        tabuleiro = new Tabuleiro(6,6, 0);
        tabuleiro.abrir(0,0);
        assertTrue(tabuleiro.getCampos(0,0).isAberto());
    }

    @Test
    void testeAlterarMarcacao(){
        tabuleiro.alterarMarcacao(0,0);
        assertTrue(tabuleiro.getCampos(0,0).isMarcado());
    }
    @Test
    void testeAlterarMarcacao2x(){
        tabuleiro.alterarMarcacao(0,0);
        tabuleiro.alterarMarcacao(0,0);
        assertFalse(tabuleiro.getCampos(0,0).isMarcado());
    }

    @Test
    void testeGerarCampos() {
        int quantidadeEsperada = 10 * 10;
        int quantidadeDeCampos = tabuleiro.getQuantidadeDeCampos();
        assertEquals(quantidadeEsperada, quantidadeDeCampos);
    }



}
