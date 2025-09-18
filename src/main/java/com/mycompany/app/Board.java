package com.mycompany.app;

import java.util.Random;

public class Board implements IBoardOperations, IMovement { // Implementa las interfaces IBoardOperations e IMovement
    // ATRIBUTOS
    public int[][] board; // Matriz que representa el tablero
    public int fila = 10; // 10 filas
    public int columna = 20; // 20 columnas
    public Random random; // Generador de numeros aleatorios
    public Piece piezaActual; // Pieza que se esta moviendo actualmente

    private int filaActual; // Fila actual de la pieza
    private int columnaActual; // Columna actual de la pieza
    private int lineasEliminadas = 0; // Para contar lineas eliminadas
    private int lineasParaGanar = 5; // Lineas necesarias para ganar

    //CONSTRUCTOR
    public Board() {
        board = new int[fila][columna];
        random = new Random();
        filaActual = 0;
        columnaActual = 0;
    }

    // Metodo requerido por la interfaz. Verifica si el tablero esta vacio
    public boolean tableroVacio() {
        for (int i = 0; i < board.length; i++) { // Recorre filas
            for (int j = 0; j < board[i].length; j++) { // Recorre columnas
                if (board[i][j] != 0) { // Si encuentra un valor no cero
                    return false; // No está vacío
                }
            }
        }
        return true;
    }

    // VERIFICACIÓN DE COLISIONES Y LÍMITES DEL TABLERO
    //  Verifica si una pieza puede colocarse en la posicion dada del tablero
    // sin salirse y sin colisionar con otras piezas
    // Devuelve true si es valido, false si hay colision o se sale
    @Override
    public boolean verificarColocacionValida(Piece piece, int fila, int columna) {
        if (tableroVacio()) {
            return true; // Si el tablero está vacío, cualquier colocación es válida
        }
        else {
            for (int i = 0; i < piece.getForma().length; i++) {
                for (int j = 0; j < piece.getForma()[i].length; j++) {
                    if (piece.getForma()[i][j] != 0) {
                        int nuevaFila = fila + i;
                        int nuevaColumna = columna + j;
                        if (nuevaFila < 0 || nuevaFila >= board.length ||
                            nuevaColumna < 0 || nuevaColumna >= board[0].length) {
                            return false;
                        }
                        if (board[nuevaFila][nuevaColumna] != 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    // INGRESO Y COLOCACION DE PIEZAS EN EL TABLERO (asumiendo verificacion previa)
    //Coloca la pieza en el tablero en la posicion dada
    // Asume que la verificacion ya fue realizada y es valida
    public void colocarPiezaEnTableroVerificada(Piece piece, int fila, int columna) {
        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {
                if (piece.getForma()[i][j] != 0) {
                    setBoard(fila + i, columna + j, piece.getForma()[i][j]);
                }
            }
        }
    }


    // SET Y GET

    public Piece getPiezaActual() {
        return piezaActual;
    }
    public void setPiezaActual(Piece piezaActual) { // me permite cambiar la pieza actual 
        this.piezaActual = piezaActual;
    }

    public int[][] getBoard() {
        return board; 
    }

    public void setBoard(int fila, int columna, int valor) { // me permite cambiar el valor de una celda del tablero
        board[fila][columna] = valor;// setea el valor de la celda del tablero en la fila y columna especificadas
    }

    public int getColumnaActual() {
        return columnaActual; // devuelve la columna actual
    }

    public int getFilaActual() {
        return filaActual;
    }

    public void setColumnaActual(int columnaActual) { // me permite cambiar la columna actual
        this.columnaActual = columnaActual;
    }

    public void setFilaActual(int filaActual) {
        this.filaActual = filaActual;
    }

    //tengo get de lineas eliminadas y get/set de lineas para ganar
    //porque yo puedo consultar cuantas lineas elimine
    //y tambien puedo consultar cuantas lineas necesito para ganar y ademas puedo cambiar la meta de lineas para ganar
    
    public int getLineasEliminadas() {
        return lineasEliminadas; 
    }

    public void setLineasParaGanar(int lineas) {
        this.lineasParaGanar = lineas;
    }
    public int getLineasParaGanar() {
        return lineasParaGanar;
    }

    // MOVIMIENTO Y MANIPULACION DE PIEZAS
    // Metodo unico para mover la pieza en cualquier direccion
    // deltaFila: +1 abajo, -1 arriba, 0 sin cambio
    // deltaColumna: +1 derecha, -1 izquierda, 0 sin cambio
    public void moverPieza (Piece piezaActual, int deltaFila, int deltaColumna) {
        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual);
        int nuevaFila = filaActual + deltaFila;
        int nuevaColumna = columnaActual + deltaColumna;
        if (verificarColocacionValida(piezaActual, nuevaFila, nuevaColumna)) {
            setFilaActual(nuevaFila);
            setColumnaActual(nuevaColumna);
        }
        colocarPiezaEnTableroVerificada(piezaActual, filaActual, columnaActual);
    }

    // Limpia la pieza del tablero en la posición actual, para mover la pieza o rotarla sin dejar rastro...osea borro la pieza vieja y la pongo en la nueva posicion....paramoverla limpio su posicion antes de moverla
    public void limpiarPiezaDelTablero(Piece piece, int fila, int columna) {
        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {
                if (piece.getForma()[i][j] != 0) {
                    board[fila + i][columna + j] = 0;
                }
            }
        }
    }

    // CONDICIONES DE JUEGO
    // Devuelve true si el juego debe finalizar:
    // El jugador gana (lineasEliminadas >= lineasParaGanar)
    // No hay lugar para colocar la nueva pieza (no se puede colocar en la posición inicial)
    public boolean esFinDelJuego(Piece piece) {
    boolean lineasParaGanar = getLineasEliminadas() >= getLineasParaGanar();
    boolean tableroLleno = !verificarColocacionValida(piece, 0, 0);
    return lineasParaGanar || tableroLleno;
    }

    // ===== ELIMINACIÓN DE LÍNEAS =====
    public void verificarYEliminarLineas() {
        for (int i = 0; i < getBoard().length; i++) {
            boolean lineaCompleta = true;
            for (int j = 0; j < getBoard()[0].length; j++) {
                if (getBoard()[i][j] == 0) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                eliminarLinea(i);
                // Usar setter para actualizar lineasEliminadas
                int nuevasLineas = getLineasEliminadas() + 1;
                lineasEliminadas = nuevasLineas;
            }
        }
        // Usar piezaActual para verificar fin de juego
        if (esFinDelJuego(getPiezaActual())) {
            // Juego ganado
        }
    }

    public void eliminarLinea(int fila) {
        for (int i = fila; i > 0; i--) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i - 1][j];
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            board[0][j] = 0;
        }
    }
    
    // Implementación de caída libre
        @Override
        public void caidaLibre(Piece piece) {
            while (verificarColocacionValida(piece, filaActual + 1, columnaActual)) {
                moverPieza(piece, 1, 0); // Mueve hacia abajo
            }
     }
}

