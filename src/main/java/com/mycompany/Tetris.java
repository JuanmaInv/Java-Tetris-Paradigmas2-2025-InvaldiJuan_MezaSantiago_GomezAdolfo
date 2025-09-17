package com.mycompany;

public class Tetris {
    
    private boolean start;
    private boolean end;
    private boolean reseet;
    
    public Tetris() { // Constructor
        this.start = false;
        this.end = false;
        this.reseet = false;
    }
    
    //Inicia el juego
    public void startGame() {
        if (start == false && end==false) {
            this.start = true;
            this.end = false;
        }
    }
    
    // Finaliza el juego
    public void endGame() {
        if (start ==true && end == false) {
            this.start = false;
            this.end = true;
        }
    }

    public int stateGame() { // Estado del jogo
        if (start == false && end == false) {
            return 0; // Juego no iniciado

            } else if (start == true && end == false) {
            return 1; // Juego iniciado

                } else if (start == false && end == true) {
                return 2; // Juego terminado luego de inciar
            }
        return -1; // Estado desconocido
    }

}