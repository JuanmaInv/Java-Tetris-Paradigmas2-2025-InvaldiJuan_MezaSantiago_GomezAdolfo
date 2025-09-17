package com.mycompany.app;

import java.util.Random;
import com.mycompany.Tetris;

public class Board extends Tetris  {
    public int[][] board;
    public int fila = 10;
    public int columna = 20;
    public Random random;
    public Piece piezaActual;

    private int filaActual;
    private int columnaActual;

    private int lineasEliminadas = 0;
    private int porcentaje = 50;

    public Board() {
        board = new int[fila][columna];
        random = new Random();
        filaActual = 0;
        columnaActual = 0;
    }
    
    public void setPiezaActual(Piece piezaActual) {
        this.piezaActual = piezaActual;
    } 

    public void setBoard(int fila, int columna, int valor) {
        board[fila][columna] = valor;
    }

    public boolean tableroVacio() { //Metodo para verificar si el tablero esta vacio

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    return false; // Si encuentra un valor distinto de 0, el tablero no está vacío
                }
            }
        }
        return true; // Si recorre todo el tablero y encuentra solo 0, está vacío
    }

    public void ingresarNuevaPieza(Piece piece) {   //Permite el ingreso de nueva pieza al board

        if (esFinDelJuego(piece)) {
            return;  // No ingresamos la pieza porque ya no hay espacio
        }
        setPiezaActual(piece);
        colocarPiezaEnTablero(piezaActual, 0, 0);   //Se ingresa la pieza en el tablero
        filaActual = 0;
        columnaActual = 0;
    }
    
    
    public void colocarPiezaEnTablero(Piece piece, int fila, int columna) {     //Este metodo es invocado en el metodo IngresarNuevaPieza

        for (int i = 0; i < piece.getForma().length; i++) {     //Recorre el tablero dentro de sus limites y coloca la pieza
            for (int j = 0; j < piece.getForma()[i].length; j++) {

                if (piece.getForma()[i][j] != 0) {
                    if (fila + i < board.length && columna + j < board[0].length) { // Verifico que no salga del tablero
                        setBoard(fila + i, columna + j, piece.getForma()[i][j]);
                    }
                    else{
                        //No pasa nada, la pieza no puede moverse fuera del tablero
                    }
                }
            }
        }
    }

    public int getColumnaActual() { //Devuelve el valor de la variable columnaActual, es utilizada para detectar la columna aleatoria que se genera

        return columnaActual;
    }

    public void setColumnaActual(int columnaActual){    //Establece el valor de la variable columnaActual

        this.columnaActual=columnaActual;
        
    }
    
    public void setFilaActual(int filaActual) { //Establece el valor de la variable filaActual, es utilizada para detectar la fila aleatoria que se genera

        this.filaActual = filaActual;
    }

    public int getFilaActual() { // Devuelve el valor de filaActual, es utilizada para detectar la fila aleatoria que se genera


        return filaActual;
    }

    public int[][] getBoard() { //Devuelve el board

        return board;
    }

    //NUEVO

    public boolean puedeColocarse(Piece piece, int fila, int columna) { //Este metodo devuelve un booleano, si la pieza puede ser colocada o no en el board en X posicion

        for (int i = 0; i < piece.getForma().length; i++) { //Recorre el tablero dentro de sus limites
            for (int j = 0; j < piece.getForma()[i].length; j++) {

                if (piece.getForma()[i][j] != 0) {  // Verifica que la forma de la pieza, y toma los valores que se encuentren los 1.
                    int nuevaFila = fila + i;
                    int nuevaColumna = columna + j;
    
                    // Verifica que no se salga del tablero
                    if (nuevaFila >= board.length || nuevaColumna >= board[0].length || nuevaFila < 0 || nuevaColumna < 0) {
                        return false; // No se puede colocar
                    }
    
                    // Verifica que no haya otra pieza en esa posición
                    if (board[nuevaFila][nuevaColumna] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void descenderPieza(Piece piezaActual) { //Metodo para descender X pieza en el board
        
        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual); //Llama al metodo que limpia la pieza del tablero
    
        // Verifica si la pieza puede descender
        if (puedeColocarse(piezaActual, filaActual + 1, columnaActual)) {
            setFilaActual(getFilaActual() + 1); //Si es asi, la pieza se mueve una fila hacia abajo
        }
    
        colocarPiezaEnTablero(piezaActual, filaActual, columnaActual);  //Se coloca la pieza en una fila hacia abajo, generando el descenso 
    }

    public void limpiarPiezaDelTablero(Piece piece, int fila, int columna) {    //Limpia la pieza del tablero

        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {

                if (piece.getForma()[i][j] != 0) {  //En los lugares donde habian 1, osea donde estaba la pieza, ahora lo llena de 0
                    board[fila + i][columna + j] = 0;    //Debido a que la pieza descendio
                }
            }
        }
    }

    public void moverPiezaDerecha(Piece piezaActual) {  //Metodo para mover la pieza a la derecha

        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual); //Limpia el tablero, osea pone 0 donde estaba la pieza antes
    
        if (puedeColocarse(piezaActual, filaActual, columnaActual + 1)) {   //Verifica si la pieza puede moverse a la derecha
            setColumnaActual(getColumnaActual() + 1);
        }
    
        colocarPiezaEnTablero(piezaActual, filaActual, columnaActual); //Coloca la pieza en la nueva posicion
    }
    
    public void moverPiezaIzquierda(Piece piezaActual) {    //Metodo para mover la pieza a la izquierda
        
        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual); //Limpia el tablero, osea pone 0 donde estaba antes la pieza
    
        if (puedeColocarse(piezaActual, filaActual, columnaActual - 1)) {   // Verifica si la pieza puede moverse a la izquierda
            setColumnaActual(getColumnaActual() - 1);
        }
    
        colocarPiezaEnTablero(piezaActual, filaActual, columnaActual); //Coloca la pieza en la nueva posicion
    }


    //NUEVO
    public boolean esFinDelJuego(Piece piece) { //Metodo para determinar el Fin del juego

        // Intenta colocar la pieza en la fila superior del tablero
        return !puedeColocarse(piece, 0, 0); // Retorna falso si no hay espacio en la fila superior
    }

    
    public boolean esFinDelJuegoPorPorcentaje() {   //Metodo para determinar que gano

        int totalLineas = board.length; //Guardo las lineas total
        int porcentajeLlenado = (lineasEliminadas * 100) / totalLineas; //Se multiplica las elim por 100 y se divide por el total
        return porcentajeLlenado >= porcentaje; // Si llega a 50 gano
    }

    //NUEVO
    public void verificarYEliminarLineas() {    //Permite verificar si una linea esta llena, y la elimina.

        for (int i = 0; i < board.length; i++) { //Recorre toda la fila
            boolean lineaCompleta = true; //se inicia que ya esta leno Pero luego en el otro for se verifica si de vedad esta lleno

            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 0) {// Verifica que la celda esta vacia
                    lineaCompleta = false;//Esta vacia ntonces sale del if
                    break;
                }
            }

            if (lineaCompleta) {//Si esta lleno entonces llama a la funcio eliminarLinea
                eliminarLinea(i);
                lineasEliminadas++;
            }
        
        } if (esFinDelJuegoPorPorcentaje()) {
            //fin

        }
    }
    
    public void eliminarLinea(int fila) { //Metodo para eliminar una linea

        // Mueve todas las filas superiores una posición hacia abajo
        for (int i = fila; i > 0; i--) {
            for (int j = 0; j < board[0].length; j++) {

                board[i][j] = board[i - 1][j]; //Copia el contenido de la fila superior a la actual
            
            }
        }

    // Limpia la fila llena
        for (int j = 0; j < board[0].length; j++) {
            board[fila][j] = 0; //Establece toda la fila en 0
        }
    }

}
