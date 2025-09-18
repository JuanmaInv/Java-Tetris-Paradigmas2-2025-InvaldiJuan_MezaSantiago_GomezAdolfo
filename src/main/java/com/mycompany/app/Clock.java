package com.mycompany.app;

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
        if (board != null && board.piezaActual != null && ticks % intervaloDescenso == 0) {
            board.moverPieza(board.piezaActual, 1, 0);
        }
    }
}
