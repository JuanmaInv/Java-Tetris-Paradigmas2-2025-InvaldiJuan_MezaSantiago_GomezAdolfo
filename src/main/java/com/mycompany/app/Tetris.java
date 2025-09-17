package com.mycompany.app;

public class Tetris {
    private boolean gameStart;
    private boolean gameEnd;

    public Tetris(){ // Constructor, inicializa el juego en estado no iniciado y no terminado
        gameStart = false;
        gameEnd = false;
    }

    public void iniciarJuego(){ // Inicia el juego
        if (gameStart == false && gameEnd == false) { // Solo puede iniciar si no ha terminado o no ha comenzado
            this.gameStart = true;
        }
    }

    public void terminarJuego(){ // Termina el juego
        if (gameStart == true) { // Solo puede terminar si ya había iniciado
            this.gameStart = false;
            this.gameEnd = true;
        }
    }

    public void reiniciarJuego(){ // Reinicia el juego a su estado inicial
        this.gameStart = false; // Modifica la variable del objeto actual
        this.gameEnd = false;   // No crea un nuevo objeto, sino que cambia el estado
    }

    // Métodos simples para obtener estado
    public int getEstado(){
        if (gameStart==false && gameEnd==false) { // El juego no ha iniciado ni ha teminado (CASO BASE) o se ha reiniciado
            return 0;
        } else if (gameStart==true && gameEnd==false) { // El juego ha iniciado
            return 1;
        } else if (gameStart == false && gameEnd==true) { // El juego ha terminado
            return 2; 
        }
        return 3; // Estado invalido (no deberia ocurrir)
    }

}