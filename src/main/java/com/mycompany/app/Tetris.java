package com.mycompany.app;


public class Tetris implements IGameState{
    private boolean gameStart;
    private boolean gameEnd;
    private Board board;
    private Clock clock;

    public Tetris(){ // Constructor, inicializa el juego en estado no iniciado y no terminado
        gameStart = false;
        gameEnd = false;
        this.board = new Board();
        this.clock = new Clock(board, 2);
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

    // Consulta a Board si el juego debe finalizar y actualiza el estado global.
    // Si Board indica fin de juego, llama a terminarJuego().
    public void actualizarEstadoJuego() {
        if (board != null && board.getPiezaActual() != null) {
            if (board.esFinDelJuego(board.getPiezaActual())) {
                terminarJuego();
            }
        }
    }
    
    // IMPLEMENTACION IGAMESTATE
    @Override
    public void setEstado(int estado) {
        switch (estado) {
            case 0:
                gameStart = false;
                gameEnd = false;
                break;
            case 1:
                gameStart = true;
                gameEnd = false;
                break;
            case 2:
                gameStart = false;
                gameEnd = true;
                break;
        }
    }
    
    @Override
    public boolean isJuegoActivo() {
        return gameStart && !gameEnd;
    }
    
    // Métodos adicionales
    public Board getBoard() {
        return board;
    }
    
    public Clock getClock() {
        return clock;
    }

    // Avanza el reloj una unidad (equivalente a 1 "tick").
    // Llamar desde afuera cada segundo o cada X segundos
    public void tick() {
        if (clock == null) {
            return; // Si no hay reloj, no hacer nada
        }

        // Solo avanzar el reloj si el juego está activo
        if (isJuegoActivo()) {
            clock.tick();
            // Después de avanzar el reloj, comprobar si el juego terminó
            actualizarEstadoJuego();
        }
    }

    // Permite configurar cuántos ticks deben pasar para que la pieza baje (ejemplo: 2)
    public void setIntervaloDescenso(int intervalo) { // Cada cuántos ticks la pieza debe bajar
        if (clock != null) { // Si hay un reloj, actualizar su intervalo
            clock.setIntervaloDescenso(intervalo); // Cada cuántos ticks la pieza debe bajar
        }
    }

}