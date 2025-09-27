package com.mycompany.app;

import java.util.Random;

public class Tetris implements IGameState{
    private boolean gameStart;
    private boolean gameEnd;
    private boolean gameWin;
    private Board board;
    private Clock clock;

    public Tetris(){ // Constructor, inicializa el juego en estado no iniciado y no terminado
        gameStart = false;
        gameEnd = false;
        gameWin = false;
        this.board = new Board();
        this.clock = new Clock(board, 2);
    }

    // Generador de números aleatorios para la columna inicial
    private Random random = new Random();

    // Genera una nueva pieza aleatoria
    private Piece generarPiezaAleatoria() {
        int randomPiece = random.nextInt(5);
        switch (randomPiece) {
            case 0:
                return new PieceSquare();
            case 1:
                return new PieceL();
            case 2:
                return new PieceT();
            case 3:
                return new PieceDog();
            default:
                return new PieceStick();
        }
    }
    
       // Crea y coloca una nueva pieza en una posición aleatoria válida del primer renglón
    // Si no se puede colocar, finaliza el juego
    public boolean nuevaPiezaAleatoria(Piece pieza) {
        int ancho = pieza.getAncho();
        int maxCol = board.getColumnas() - ancho;
        int colAleatoria;
        int intentos = 0;
        do {
            colAleatoria = (int)(Math.random() * (maxCol + 1));
            intentos++;
            if (board.verificarColocacionValida(pieza, 0, colAleatoria)) {
                board.setPiezaActual(pieza);
                board.setFilaActual(0);
                board.setColumnaActual(colAleatoria);
                board.colocarPiezaEnTableroVerificada(pieza, 0, colAleatoria);
                return true;
            }
        } while (intentos < 20);
        terminarJuego(); // No se pudo colocar la pieza, finaliza el juego
        return false;
    }

    // Intenta rotar la pieza actual a la derecha, solo si es posible
    public boolean rotarPiezaActualDerecha() {
        Piece pieza = board.getPiezaActual();
        if (pieza == null) return false;
        board.limpiarPiezaDelTablero(pieza, board.getFilaActual(), board.getColumnaActual());
        pieza.rotarDerecha();
        if (board.verificarColocacionValida(pieza, board.getFilaActual(), board.getColumnaActual())) {
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return true;
        } else {
            // Si no puede rotar, vuelve a la forma anterior (rotar izquierda 3 veces)
            pieza.rotarIzquierda();
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return false;
        }
    }

    // Intenta rotar la pieza actual a la izquierda, solo si es posible
    public boolean rotarPiezaActualIzquierda() {
        Piece pieza = board.getPiezaActual();
        if (pieza == null) return false;
        board.limpiarPiezaDelTablero(pieza, board.getFilaActual(), board.getColumnaActual());
        pieza.rotarIzquierda();
        if (board.verificarColocacionValida(pieza, board.getFilaActual(), board.getColumnaActual())) {
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return true;
        } else {
            // Si no puede rotar, vuelve a la forma anterior (rotar derecha 3 veces)
            pieza.rotarDerecha();
            pieza.rotarDerecha();
            pieza.rotarDerecha();
            board.colocarPiezaEnTableroVerificada(pieza, board.getFilaActual(), board.getColumnaActual());
            return false;
        }
    }

    // Verifica si la pieza actual está apoyada y no puede descender más
    public boolean piezaActualDetenida() {
        Piece pieza = board.getPiezaActual();
        if (pieza == null) return true;
        return !board.verificarColocacionValida(pieza, board.getFilaActual() + 1, board.getColumnaActual());
    }
    // Coloca una nueva pieza en la fila inicial (0) y en una columna aleatoria valida
    public void spawnNewPiece() {
        if (board == null) return;
        Piece pieza = generarPiezaAleatoria();
        int fila = 0;
        int maxColumnas = board.getColumnas() - pieza.getAncho();
        int columnaElegida = 0;

        if (maxColumnas < 0) {
            // Pieza mas ancha que el tablero — colocar en columna 0 y dejar que esFinDelJuego lo detecte
            columnaElegida = 0;
            board.setPiezaActual(pieza);
            board.setFilaActual(fila);
            board.setColumnaActual(columnaElegida);
            return;
        }

        // Elegir columna aleatoria dentro del rango permitido
        columnaElegida = random.nextInt(maxColumnas + 1);

        // Si la posición aleatoria no es válida, buscar una columna válida linealmente
        if (!board.verificarColocacionValida(pieza, fila, columnaElegida)) {
            boolean colocado = false;
            for (int c = 0; c <= maxColumnas; c++) {
                if (board.verificarColocacionValida(pieza, fila, c)) {
                    columnaElegida = c;
                    colocado = true;
                    break;
                }
            }
            // Si no se encontró columna válida en fila 0, buscar cualquier posición en el tablero
            if (!colocado) {
                boolean encontrado = false;
                for (int f = 0; f < board.getFilas(); f++) {
                    for (int c = 0; c < board.getColumnas(); c++) {
                        if (board.verificarColocacionValida(pieza, f, c)) {
                            fila = f;
                            columnaElegida = c;
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) break;
                }
                if (!encontrado) {
                    // No hay lugar para colocar la pieza: asignarla para que esFinDelJuego la detecte
                    board.setPiezaActual(pieza);
                    board.setFilaActual(0);
                    board.setColumnaActual(0);
                    return;
                }
            }
        }

        // Colocar la pieza en tablero
        board.setPiezaActual(pieza);
        board.setFilaActual(fila);
        board.setColumnaActual(columnaElegida);
        board.colocarPiezaEnTableroVerificada(pieza, fila, columnaElegida);
    }

    public void iniciarJuego(){ // Inicia el juego
        if (gameStart == false && gameEnd == false) { // Solo puede iniciar si no ha terminado o no ha comenzado
            this.gameStart = true;
            this.gameWin = false;
            // Colocar la primera pieza en la fila inicial 0 en una columna aleatoria válida
            spawnNewPiece();
        }
    }

    public void terminarJuego(){ // Termina el juego
        if (gameStart == true) { // Solo puede terminar si ya había iniciado
            this.gameStart = false;
            this.gameEnd = true;
            this.gameWin = false;
        }
    }

    public void reiniciarJuego(){ // Reinicia el juego a su estado inicial
        this.gameStart = false; // Modifica la variable del objeto actual
        this.gameEnd = false;   // No crea un nuevo objeto, sino que cambia el estado
        this.gameWin = false;
    }
    
    // Métodos simples para obtener estado
    public int getEstado(){
        // Priorizar estado de victoria
        if (gameWin) {
            return 3; // Game Win
        }
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
        if (board != null) { // Verifica que board no sea nulo
            // Primero comprobar si se alcanzó la condición de "win"
            if (board.getLineasEliminadas() >= board.getLineasParaGanar()) {
                // Marcar game win
                setEstado(3);
                return;
            }
            // Si no es win, comprobar si hay fin de juego por no poder colocar la pieza
            if (board.esFinDelJuego(board)) {
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