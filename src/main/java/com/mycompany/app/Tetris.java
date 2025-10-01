package com.mycompany.app;

import java.util.Random;

public class Tetris implements IGameState{
    private boolean gameStart; // Indica si el juego ha comenzado
    private boolean gameEnd; // Indica si el juego ha terminado
    private boolean gameWin; // Indica si el jugador ha ganado
    private Board board; // Tablero del juego
    private Clock clock; // Reloj del juego

    public Tetris(){ // Constructor, inicializa el juego en estado no iniciado y no terminado
        gameStart = false; // No ha comenzado
        gameEnd = false; // No ha terminado
        gameWin = false; // No ha ganado
        this.board = new Board(); // Siempre crea un nuevo tablero al iniciar Tetris
        this.clock = new Clock(board, 2); // Reloj con intervalo de descenso de 2 ticks que afecta al tablero porque baja la pieza cada X ticks
    }

    // Generador de números aleatorios para la columna inicial
    private Random random = new Random();

    // Genera una nueva pieza aleatoria
    // llama a los constructores de las piezas
    PieceBase generarPiezaAleatoria() { // 5 tipos de piezas
        int randomPiece = random.nextInt(5); // 0 a 4
        switch (randomPiece) {
            case 0:
                return new PieceSquare(); // case 0
            case 1:
                return new PieceL(); // case 1
            case 2:
                return new PieceT(); // case 2
            case 3:
                return new PieceDog(); // case 3
            default:
                return new PieceStick(); // case 4
        }
    }
    
    // Crea y coloca una nueva pieza en una posición aleatoria válida de la primer fila
    // Si no se puede colocar, finaliza el juego
    // boolean para indicar si se pudo colocar la pieza
    public boolean nuevaPiezaAleatoria(PieceBase pieza) { // Recibe la pieza a colocar
        int maxCol = board.getColumnas() - pieza.getAncho(); // Columna máxima para colocar la pieza sin salirse del tablero

        // Elegir una columna de inicio aleatoria y probar secuencialmente todas las columnas (sin repetir)
        int inicio = random.nextInt(maxCol + 1); // Columna inicial aleatoria
            for (int i = 0; i <= maxCol; i++) { // Probar todas las columnas
                int col = (inicio + i) % (maxCol + 1); // Va probando de la forma circular, es decir, si empieza en 3 y hay 5 columnas, prueba 3,4,0,1,2
                if (board.verificarColocacionValida(pieza, 0, col)) {
                    board.setPiezaActual(pieza);
                    board.setFilaActual(0);
                    board.setColumnaActual(col);
                    board.colocarPiezaEnTableroVerificada(pieza, 0, col);
                    return true;
                }
            }
        // Si no se encontró ninguna columna válida, terminar el juego y devuelve false
        end();
        return false;
        }

    // Intenta rotar la pieza actual a la derecha, solo si es posible
    public boolean rotateRight() {
        PieceBase pieza = board.getPiezaActual();
        if (pieza == null)
        return false;
        board.limpiarPiezaDelTablero(pieza, board.getFilaActual(), board.getColumnaActual());
        pieza.rotateRight();

        if (board.verificarColocacionValida(pieza, board.getFilaActual(), board.getColumnaActual())) {
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return true;
            
        } else {
            // Si no puede rotar, vuelve a la forma anterior (rotar izquierda 3 veces)
            pieza.rotateLeft();
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return false;
        }
    }

    // Intenta rotar la pieza actual a la izquierda, solo si es posible
    public boolean rotateLeft() {
        PieceBase pieza = board.getPiezaActual();
        if (pieza == null) return false;
        board.limpiarPiezaDelTablero(pieza, board.getFilaActual(), board.getColumnaActual());
        pieza.rotateLeft();
        if (board.verificarColocacionValida(pieza, board.getFilaActual(), board.getColumnaActual())) {
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return true;
        } else {
            // Si no puede rotar, vuelve a la forma anterior (rotar derecha 3 veces)
            pieza.rotateRight();
            pieza.rotateRight();
            pieza.rotateRight();
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return false;
        }
    }

    // Verifica si la pieza actual está apoyada y no puede descender más
    public boolean piezaActualDetenida() {
        PieceBase pieza = board.getPiezaActual();
        if (pieza == null)
        return true;
        return !board.verificarColocacionValida(pieza, board.getFilaActual() + 1, board.getColumnaActual());
    }

    @Override
    public void start(){ // Inicia el juego
        if (gameStart == false && gameEnd == false && gameWin == false) { // Solo puede iniciar si no ha terminado o no ha comenzado
            this.gameStart = true;
        }
    }

    @Override
    public void end(){ // Termina el juego
        this.gameStart = false;
        this.gameEnd = true;
    }

    @Override
    public void restart(){ // Reinicia el juego a su estado inicial
        this.gameStart = false;
        this.gameEnd = false;
        this.gameWin = false;
    }

    @Override
    public int getState(){
        // Priorizar la condición de "win"
        if (gameWin) {
            return 3; // estado de "win"
        }

        if (gameStart==false && gameEnd==false) {
            return 0; // Estado inicial, no ha comenzado ni terminado
        } else if (gameStart==true && gameEnd==false) {
            return 1; // estado de "en juego"
        } else if (gameStart == false && gameEnd==true) {
            return 2; // estado de "fin del juego"
        }
        // Por defecto, devolver estado inicial si no coincide ninguna condición
        return 0;
    }

    // Alias simple para UML: state() devuelve lo mismo que getState()
    public int state() {
        return getState();
    }

    // Consulta a Board si el juego debe finalizar y actualiza el estado global.
    // Si Board indica fin de juego, llama a terminarJuego().
    public void actualizarEstadoJuego() {
        if (board != null) { // Verifica que board no sea nulo
            // Primero comprobar si se alcanzó la condición de "win"
            if (board.getLineasEliminadas() >= board.getLineasParaGanar()) {
                // Marcar game win
                setState(3);
                return;
            }
            // Si no es win, comprobar si hay fin de juego por no poder colocar la pieza
            if (board.esFinDelJuego(board)) {
                end();
            }
        }
    }
    
    // IMPLEMENTACION IGAMESTATE
    @Override
    public void setState(int state) {
        switch (state) {
            case 0:
                gameStart = false;
                gameEnd = false;
                gameWin = false;
                break;
            case 1:
                gameStart = true;
                gameEnd = false;
                gameWin = false;
                break;
            case 2:
                gameStart = false;
                gameEnd = true;
                gameWin = false;
                break;
            case 3:
                // Game win
                gameStart = false;
                gameEnd = false;
                gameWin = true;
                break;
        }
    }
    
    @Override
    public boolean isJuegoActivo() {
        return gameStart && !gameEnd;
    }
    
    // Métodos adicionales
    public Board getBoard() {
        // Asegurar que nunca se devuelva null 
        if (this.board == null) {
            this.board = new Board();
        }
        return this.board;
    }
    
    public Clock getClock() {
        return clock;
    }

    // Getter/Setter para el generador aleatorio interno (cumple encapsulamiento)
    private Random getRandom() {
        if (this.random == null) {
            this.random = new Random();
        }
        return this.random;
    }

    private void setRandom(Random random) {
        this.random = random;
    }

    // Getters/Setters básicos para cumplir encapsulamiento
    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public boolean isGameWin() {
        return gameWin;
    }

    public void setGameWin(boolean gameWin) {
        this.gameWin = gameWin;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
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