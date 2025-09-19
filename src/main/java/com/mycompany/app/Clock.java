package com.mycompany.app;

// Clock debe ser independiente de Tetris (no hereda de Tetris)
public class Clock implements ITick {
    private int ticks;
    private int intervaloDescenso; // Cada cuántos ticks la pieza debe bajar
    private Board board; // Referencia al tablero para controlar el descenso

    public Clock() {
        this.ticks = 0;
        this.intervaloDescenso = 2; // Por defecto cada 2 ticks (2 segundos)
    }
    
    public Clock(Board board, int intervaloDescenso) {
        this.ticks = 0;
        this.board = board;
        this.intervaloDescenso = intervaloDescenso;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void setIntervaloDescenso(int intervalo) {
        this.intervaloDescenso = intervalo;
    }

    public void tick() {
        setTicks(getTicks() + 1);
        
        // Si hay un tablero y una pieza actual, controlar el descenso automático
        if (board != null && board.getPiezaActual() != null && ticks % intervaloDescenso == 0) { // permite que la pieza baje cada X ticks
            board.moverPieza(board.getPiezaActual(), 1, 0); // Mueve la pieza actual hacia abajo
        }
    }
}
