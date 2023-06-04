package br.com.cod3r.campoMinado.modelo;
import static org.junit.jupiter.api.Assertions.*;

import br.com.cod3r.campoMinado.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {

    private Campo campo;
    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3,3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3,2);
        boolean result = campo.adicionarVizinho(vizinho);
        assertTrue(result);
    }
    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3,4);
        boolean result = campo.adicionarVizinho(vizinho);
        assertTrue(result);
    }
    @Test
    void testeVizinhoDistancia1EmCima() {
        Campo vizinho = new Campo(2,3);
        boolean result = campo.adicionarVizinho(vizinho);
        assertTrue(result);
    }
    @Test
    void testeVizinhoDistancia1EmBaixo() {
        Campo vizinho = new Campo(4,3);
        boolean result = campo.adicionarVizinho(vizinho);
        assertTrue(result);
    }

    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2,2);
        boolean result = campo.adicionarVizinho(vizinho);
        assertTrue(result);
    }
    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1,1);
        boolean result = campo.adicionarVizinho(vizinho);
        assertFalse(result);
    }

    @Test
    void testeValorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao(){
        campo.aterarMarcacao();
        assertTrue(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacaoDuasChamadas(){
        campo.aterarMarcacao();
        campo.aterarMarcacao();
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }
    @Test
    void testeAbrirNaoMinadoMarcado(){
        campo.aterarMarcacao();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoMarcado(){
        campo.aterarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class,()->{
        campo.abrir();
        });
    }



}
