package com.mycompany.app;

import java.util.Random;

public class Board implements IBoardOperations, IMovement { // Implementa las interfaces IBoardOperations e IMovement
    // ATRIBUTOS
    private int[][] board; // Matriz que representa el tablero
    private int filas = 10; // 10 filas
    private int columnas = 20; // 20 columnas
    private Random random; // Generador de numeros aleatorios
    private Piece piezaActual; // Pieza que se esta moviendo actualmente

    private int filaActual; // Fila actual de la pieza
    private int columnaActual; // Columna actual de la pieza
    private int lineasEliminadas = 0; // Para contar lineas eliminadas
    private int lineasParaGanar = 5; // Lineas necesarias para ganar

    //CONSTRUCTOR
    public Board() {
        board = new int[filas][columnas];
        random = new Random();
        filaActual = 0;
        columnaActual = 0;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }


    // Acceso al Random del tablero
    private Random getRandom() {
        return random;
    }

    private void setRandom(Random random) {
        this.random = random;
    }

    // Metodo requerido por la interfaz. Verifica si el tablero esta vacio
    @Override
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
        for (int i = 0; i < piece.getForma().length; i++) { // Recorre filas de la pieza
            for (int j = 0; j < piece.getForma()[i].length; j++) { // Recorre columnas de la pieza
                if (piece.getForma()[i][j] != 0) { // Si hay un bloque en la pieza
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

    // INGRESO Y COLOCACION DE PIEZAS EN EL TABLERO (asumiendo verificacion previa)
    //Coloca la pieza en el tablero en la posicion dada
    // Asume que la verificacion ya fue realizada y es valida
    @Override
    public void colocarPiezaEnTableroVerificada(Piece piece, int fila, int columna) {
            // Actualiza la posición actual de la pieza
            this.filaActual = fila;
            this.columnaActual = columna;
        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {
                if (piece.getForma()[i][j] != 0) {
                    setBoard(fila + i, columna + j, piece.getForma()[i][j]); // settea cada uno de los bloques de la pieza en el tablero teniendo en cuenta su valor
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

    @Override
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

    // Limpia todo el tablero (todas las celdas a 0) y resetea la pieza actual y posiciones.
    // No toca el contador de líneas eliminadas para que las pruebas que dependan
    // de ese contador no se vean afectadas a menos que se desee explícitamente.
    public void limpiarTablero() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        this.piezaActual = null;
        this.filaActual = 0;
        this.columnaActual = 0;
    }
    
    //tengo get de lineas eliminadas y get/set de lineas para ganar
    //porque yo puedo consultar cuantas lineas elimine
    //y tambien puedo consultar cuantas lineas necesito para ganar y ademas puedo cambiar la meta de lineas para ganar
    
    public int getLineasEliminadas() {
        return lineasEliminadas; 
    }

    public void setLineasEliminadas(int lineasEliminadas) {
        if (lineasEliminadas < 0)
        return;
        this.lineasEliminadas = lineasEliminadas;
    }

    public void setLineasParaGanar(int lineas) {
        this.lineasParaGanar = lineas;
    }
    public int getLineasParaGanar() {
        return lineasParaGanar;
    }

    // sobrecargar el metodo moverPieza
    // Variante, solo valida si la pieza actual puede moverse
    // Devuelve true si es posible mover la pieza actual en (deltaFila, deltaColumna), false en caso contrario
    public boolean moverPieza(int deltaFila, int deltaColumna) {
        if (this.piezaActual == null) {
            return false; // no hay pieza que mover
        }
        int nuevaFila = this.filaActual + deltaFila;
        int nuevaColumna = this.columnaActual + deltaColumna;
        return verificarColocacionValida(this.piezaActual, nuevaFila, nuevaColumna);
    }


    // MOVIMIENTO Y MANIPULACION DE PIEZAS
    // Metodo unico para mover la pieza en cualquier direccion
    // deltaFila: +1 abajo, -1 arriba, 0 sin cambio
    // deltaColumna: +1 derecha, -1 izquierda, 0 sin cambio
    @Override
    public void moverPieza (Piece piezaActual, int deltaFila, int deltaColumna) { // Implementa el metodo de la interfaz IMovement
        // Solo permitir mover la pieza que está marcada como piezaActual en el tablero
        if (this.piezaActual == null || piezaActual == null) return;
        if (this.piezaActual != piezaActual) return;

        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual);// Limpia la pieza de su posicion actual

        int nuevaFila = filaActual + deltaFila; // Calculo la nueva fila 
        int nuevaColumna = columnaActual + deltaColumna; // Calculo la nueva columna
        if (verificarColocacionValida(piezaActual, nuevaFila, nuevaColumna)) { // Verifico si la nueva posicion es valida
            setFilaActual(nuevaFila); // Si es valida, actualizo la fila y columna actuales
            setColumnaActual(nuevaColumna);
        }
        colocarPiezaEnTableroVerificada(piezaActual, filaActual, columnaActual); // Coloco la pieza en la nueva posicion
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
    public boolean esFinDelJuego(Board board) {
        boolean lineasParaGanar = board.getLineasEliminadas() >= board.getLineasParaGanar();

        // Determinar si no existe ninguna posición válida donde colocar la pieza actual
        boolean tableroLleno = false;
        Piece p = board.getPiezaActual();
        if (p == null) {
            // Si no hay pieza actual, no consideramos que el tablero esté lleno (evitar NPE)
            tableroLleno = false;
        } else {
            boolean existePosicionValida = false;
            for (int f = 0; f < board.getFilas(); f++) {
                for (int c = 0; c < board.getColumnas(); c++) {
                    if (board.verificarColocacionValida(p, f, c)) {
                        existePosicionValida = true;
                        break;
                    }
                }
                if (existePosicionValida)
                break;
            }
            tableroLleno = !existePosicionValida;
        }

        return lineasParaGanar || tableroLleno;
    }

    // ===== ELIMINACIÓN DE LÍNEAS =====
    public void verificarYEliminarLineas() {
        // Iterar de abajo hacia arriba; si se elimina una línea, no decrementar
        // el índice para volver a comprobar la fila que acaba de descender.
        int i = getBoard().length - 1;
        while (i >= 0) {
            boolean lineaCompleta = true;
            for (int j = 0; j < getBoard()[0].length; j++) {
                if (getBoard()[i][j] == 0) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                eliminarLinea(i);
                // Incrementar contador de líneas eliminadas
                lineasEliminadas++;
                // NO decrementar i: la fila nueva en la posición i debe ser re-evaluada
            } else {
                i--; // solo moverse hacia arriba si no se eliminó la fila
            }
        }
        
        // Usar piezaActual para verificar fin de juego (solo si existe)
        Piece piece = getPiezaActual();
        if (piece != null && esFinDelJuego(this)) {
            // Juego terminado
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
            //mueve la pieza hacia abajo hasta que no pueda mas, sean 5 o 40 veces o ninguna
            //osea maneja iteraciones hasta que no pueda bajar mas
            // Solo permitir caída libre sobre la pieza actual
                if (this.piezaActual == null || piece == null) // si no hay pieza actual o la pieza es null
                return; // sale del metodo con return, Evita NullPointerException

                if (this.piezaActual != piece) // si la pieza actual no es la misma que la pieza pasada como parametro
                return; // sale del metodo con return, Evita NullPointerException

                // Para evitar que la pieza "choque" consigo misma en el tablero
                while (true) {
                    //inicialmente la pieza esta colocada en el tablero en su posicion inicial
                    // Limpiar la pieza de su posicion actual antes de verificar la siguiente fila
                    limpiarPiezaDelTablero(piece, filaActual, columnaActual);

                    // Verificar si puede bajar una fila mas y si no puede, sale del bucle colocando la pieza en su posicion actual
                    if (verificarColocacionValida(piece, filaActual + 1, columnaActual)) {
                        // Reutilizar moverPieza para actualizar fila/columna y recolocar
                        // moverPieza internamente limpiará y colocará; la doble limpieza es inofensiva
                        moverPieza(piece, 1, 0);
                        // continuar el bucle para intentar bajar otra fila
                    } else {
                        // No puede bajar mas: recolocar la pieza en su posicion actual y salir
                        colocarPiezaEnTableroVerificada(piece, filaActual, columnaActual);
                        break;
                    }
                }
     }
}

