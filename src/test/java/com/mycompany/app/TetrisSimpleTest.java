package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

// Importar las clases necesarias
import com.mycompany.Tetris;

public class TetrisSimpleTest {

    @Test
    public void testEstadoInicial(){
        Tetris tetris = new Tetris();
        assertEquals(0, tetris.stateGame()); // Verificar que el estado inicial sea "no iniciado"
    }

    @Test
    public void testIniciarJuego(){
        Tetris tetris = new Tetris();
        tetris.startGame();
        assertEquals(1, tetris.stateGame()); // Verificar que el estado sea "juego iniciado"
    }
    
    @Test
    public void testFinalizarJuego(){
        Tetris tetris = new Tetris();
        tetris.startGame();
        tetris.endGame();
        assertEquals(2, tetris.stateGame()); // Verificar que el estado sea "juego terminado"
    }
    
    @Test
    public void testCicloCompletoJuego(){
        Tetris tetris = new Tetris();
        
        // Estado inicial
        assertEquals(0, tetris.stateGame());
        
        // Iniciar juego
        tetris.startGame();
        assertEquals(1, tetris.stateGame());
        
        // Finalizar juego
        tetris.endGame();
        assertEquals(2, tetris.stateGame());
        
        assertTrue("El ciclo del juego funcionó correctamente", true);
    }
}