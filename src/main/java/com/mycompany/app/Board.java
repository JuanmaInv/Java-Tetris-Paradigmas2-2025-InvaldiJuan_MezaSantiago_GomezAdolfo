package com.mycompany.app;

import java.util.Random;

public class Board implements IBoardOperations, IMovement { // Implementa las interfaces IBoardOperations e IMovement
    // ATRIBUTOS
    private int[][] board; // Matriz que representa el tablero
    private int filas = 10; // 10 filas
    private int columnas = 20; // 20 columnas
    private Random random; // Generador de numeros aleatorios para seleccionar piezas

    private PieceBase piezaActual; // Pieza que se esta moviendo actualmente
    private int filaActual; // Fila actual de la pieza
    private int columnaActual; // Columna actual de la pieza

    private int lineasEliminadas = 0; // Para contar lineas eliminadas
    private int lineasParaGanar = 5; // Lineas necesarias para ganar

//CONSTRUCTOR que inicializa el tablero y el random de la pieza aleatoria actualmente
    public Board() {
        board = new int[filas][columnas];
        random = new Random();
        filaActual = 0;
        columnaActual = 0;
    }

// GETTERS
  // Getters para columnas para poder usarlos en Tetris.java
    public int getColumnas() {
        return columnas;
    }

  // Getters para filas para poder usarlos en Tetris.java
    public int getFilas() {
        return filas;
    }

  // Acceso al Random del tablero que se usa para seleccionar piezas aleatorias
    private Random getRandom() {
        return random;
    }

    //Interfaz IMovement
 // Verifica si el tablero esta vacio
    @Override
    public boolean tableroVacio() {
        for (int i = 0; i < board.length; i++) { // Recorre filas
            for (int j = 0; j < board[i].length; j++) { // Recorre columnas
                if (board[i][j] != 0) { // Si encuentra un valor diferente de cero, retorna false
                    return false; // No esta vacio
                }
            }
        }
        return true; // Si no encontro ningun valor diferente de cero, el tablero esta vacio
    }


    // VERIFICACIÓN DE COLISIONES Y LÍMITES DEL TABLERO
    // Verifica si una pieza puede colocarse en la posicion dada del tablero
    // sin salirse y sin colisionar con otras piezas
    // Devuelve true si es valido, false si hay colision o se sale
    @Override
    public boolean verificarColocacionValida(PieceBase piece, int fila, int columna) {
        for (int i = 0; i < piece.getForma().length; i++) { // Recorre filas de la pieza
            for (int j = 0; j < piece.getForma()[i].length; j++) { // Recorre columnas de la pieza
                if (piece.getForma()[i][j] != 0) { // Si hay un bloque en la pieza
// Calcular la posicion en el tablero
// lo que hace es sumar la cantidad de filas donde quiero colocar + la cantidad de filas del elemento de la pieza
//colocar square en fila8,columna18
// (0,0) --> nuevaFila = 8 + 0 = 8 ||| nuevaColumna = 18 + 0 = 18 --> tablero[8][18]
// (0,1) --> nuevaFila = 8 + 0 = 8 ||| nuevaColumna = 18 + 1 = 19 --> tablero[8][19]
// (1,0) --> nuevaFila = 8 + 1 = 9 ||| nuevaColumna = 18 + 0 = 18 --> tablero[9][18]
// (1,1) --> nuevaFila = 8 + 1 = 9 ||| nuevaColumna = 18 + 1 = 19 --> tablero[9][19]
                    int nuevaFila = fila + i;
                    int nuevaColumna = columna + j;
                    if (nuevaFila < 0 || nuevaFila >= board.length ||
                        nuevaColumna < 0 || nuevaColumna >= board[0].length) {
                        return false; //retorn falso cuando se sale del tablero
                    }
                    if (board[nuevaFila][nuevaColumna] != 0) {
                        return false; //retorna falso pq choca con otra pieza
                    }
                }
            }
        }
        return true;
    }

    // INGRESO Y COLOCACION DE PIEZAS EN EL TABLERO (asumiendo verificacion previa)
    // Coloca la pieza en el tablero en la posicion dada
    // Asume que la verificacion ya fue realizada y es valida
    @Override
    public void colocarPiezaEnTableroVerificada(PieceBase piece, int fila, int columna) { //
            // Actualiza la posición actual de la pieza
            this.filaActual = fila; // actualiza la fila actual de la pieza para que el movimiento sepa donde esta
            this.columnaActual = columna; // actualiza la columna actual de la pieza para que el movimiento sepa donde esta
        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {
                if (piece.getForma()[i][j] != 0) {
                    setBoard(fila + i, columna + j, piece.getForma()[i][j]); // settea cada uno de los bloques de la pieza en el tablero teniendo en cuenta su valor
                }
            }
        }
    }
    // el primer metodo valida y el segundo coloca la pieza en el tablero

    // Intenta colocar la pieza en la posición dada. Si es válida, actualiza piezaActual/fila/columna y la coloca en el tablero.
    // Devuelve true si se colocó, false si la posición no era válida.
    public boolean colocarSiValida(PieceBase piece, int fila, int columna) {
        if (!verificarColocacionValida(piece, fila, columna)) //si me da false
        return false;
        // Si es valida, actualiza piezaActual, filaActual, columnaActual y coloca la pieza
        setPiezaActual(piece);
        setFilaActual(fila);
        setColumnaActual(columna);
        colocarPiezaEnTableroVerificada(piece, fila, columna);
        return true;
    }

    
    // SET Y GET
    public PieceBase getPiezaActual() {
        return piezaActual;
    }
    public void setPiezaActual(PieceBase piezaActual) { // me permite cambiar la pieza actual 
        this.piezaActual = piezaActual;
    }


    @Override
    public int[][] getBoard() { // me permite obtener el tablero y retornarlo
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
    // Util para tests o para preparar una nueva situación sin alterar estadísticas.
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

    // Alias solicitado por el diagrama: número de líneas removidas 
    // Da las líneas eliminadas actualmente
    public int lineCount() {
        return getLineasEliminadas();
    }

    public int getLineasParaGanar() {
        return lineasParaGanar;
    }

    void setLineasParaGanar(int lineas) { //se ocupa private para que solo yo pueda cambiar la meta de lineas para ganar
        this.lineasParaGanar = lineas;
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
    public void moverPieza (PieceBase piezaActual, int deltaFila, int deltaColumna) { // Implementa el metodo de la interfaz IMovement
        // Solo permitir mover la pieza que está marcada como piezaActual en el tablero
        if (this.piezaActual == null || piezaActual == null) return;
        if (this.piezaActual != piezaActual) return;
        limpiarPiezaDelTablero(piezaActual, filaActual, columnaActual); // Limpia la pieza de su posicion actual

        // Reutilizar el validador sobrecargado: si el movimiento es válido, actualizar posiciones
        if (moverPieza(deltaFila, deltaColumna)) {
            setFilaActual(filaActual + deltaFila);
            setColumnaActual(columnaActual + deltaColumna);
        }

        // Colocar la pieza en la posición actual (sea la misma porque no se movio o la nueva pq se movio)
        colocarPiezaEnTableroVerificada(piezaActual, filaActual, columnaActual);
    }

    // Limpia la pieza del tablero en la posición actual, para mover la pieza o rotarla sin dejar rastro...osea borro la pieza vieja y la pongo en la nueva posicion....para moverla limpio su posicion antes de moverla
    public void limpiarPiezaDelTablero(PieceBase piece, int fila, int columna) {
        for (int i = 0; i < piece.getForma().length; i++) {
            for (int j = 0; j < piece.getForma()[i].length; j++) {
                if (piece.getForma()[i][j] != 0) {
                    board[fila + i][columna + j] = 0;
                }
            }
        }
    }

    // CONDICIONES DE JUEGO
    public boolean esFinDelJuego(Board board) {
        // 1) Victoria rápida: si ya alcanzó la meta de líneas, termina el juego (victoria)
        if (board.getLineasEliminadas() >= board.getLineasParaGanar()) {
            return true;
        }

        // 2) Si no alcanzó la meta, comprobar si existe alguna posición válida
        //    para la pieza actual. Si existe al menos una, no es fin de juego.
        PieceBase piece = board.getPiezaActual();
        if (piece == null) {
            // Sin pieza actual: no consideramos fin de juego por tablero lleno
            return false; // sale del metodo con false pq todavia se puede jugar
        }

        for (int fila = 0; fila < board.getFilas(); fila++) {
            for (int columna = 0; columna < board.getColumnas(); columna++) {
                if (board.verificarColocacionValida(piece, fila, columna)) {
                    // Encontro colocación válida, todavía se puede jugar
                    return false; // sale del metodo con false pq todavia se puede jugar
                }
            }
        }

        // No se encontró ninguna posición válida -> derrota
        return true; // sale del metodo con true y termina el juego
    }

    // Elimina la fila especificada y desplaza todas las filas superiores hacia abajo
    public void eliminarLinea(int fila) {
        for (int i = fila; i > 0; i--) { // desde la fila dada hacia arriba
            for (int j = 0; j < board[0].length; j++) { // recorre todas las columnas de fila i
                board[i][j] = board[i - 1][j]; // mueve la fila hacia abajo
            }
        }

    //despues de mover todas las filas hacia abajo, la primera fila queda duplicada
    //entonces la limpio y la dejo en 0
        for (int j = 0; j < board[0].length; j++) { 
    // limpia la fila que queda despues de mover todas las filas hacia abajo, la fila podria quedar con cualquier valor
            board[0][j] = 0;
        }
    }


    // ELIMINACION DE LINEAS
    // Este método busca filas completas (sin celdas 0) desde el fondo del tablero hacia arriba
    public void verificarYEliminarLineas() {
        // Iterar de abajo hacia arriba; si se elimina una linea, no decrementar
        // el indice para volver a comprobar la fila que acaba de descender.
        int i = getBoard().length - 1; // empieza desde la ultima fila hace -1 porque el indice empieza en 0
        while (i >= 0) { // mientras i sea mayor o igual a 0

            boolean lineaCompleta = true; // inicializo en true y corre el bucle buscando si hay algun 0
            // Si encuentra un 0 en alguna columna de la fila, se marca lineaCompleta=false y sale del for
            for (int j = 0; j < getBoard()[0].length; j++) { // recorre todas las columnas de cada fila (i)
                if (getBoard()[i][j] == 0) {
                    lineaCompleta = false; //retorna false porque la linea tiene 0s
                    break;// sale del for porque no se encontro una linea completa
                }
            }

            // Al terminar el bucle, si lineaCompleta sigue siendo true
            // significa que NO se encontró ningún 0 y por tanto la fila está completa
            if (lineaCompleta==true) { // si la linea esta completa
                eliminarLinea(i); //se elimina la linea
                // Incrementar contador de líneas eliminadas
                lineasEliminadas++;
            } else {
                i--; // lineaCompleta es false, moverse hacia arriba (fila anterior)
                    // para evaluar la siguiente fila
            }
        }
    }
    
// Implementación de caída libre
// baja la pieza pasada fila por fila hasta que ya no puede descender más, y entonces 
// la deja fijada en su última posición válida. Antes de cada comprobación borra la pieza
// del tablero para evitar que choque consigo misma al comprobar la fila siguiente.

    @Override
    public void caidaLibre(PieceBase piece) {
            // Mueve la pieza hacia abajo hasta que no pueda mas, sean 5 o 40 veces o ninguna
            // Osea maneja iteraciones hasta que no pueda bajar mas
            // Solo permitir caida libre sobre la pieza actual
                if (this.piezaActual == null || piece == null) // si no hay pieza actual o la pieza es null
                return; // Sale del metodo con return, Evita NullPointerException

                if (this.piezaActual != piece) // si la pieza actual no es la misma que la pieza pasada como parametro
                    return; // Sale del metodo con return, Evita NullPointerException

            // Para evitar que la pieza "choque" consigo misma en el tablero
                while (true) {

                    //inicialmente la pieza esta colocada en el tablero en una posicion dada
                    // se debe limpiar la pieza de la posicion que ocupaba en el tablero, antes de bajarla
                    limpiarPiezaDelTablero(piece, filaActual, columnaActual);

                    // Verificar si puede bajar una fila mas y si no puede, sale del bucle colocando la pieza en su posicion actual
                    if (verificarColocacionValida(piece, filaActual + 1, columnaActual)) { //verifica si puede bajar una fila mas teniendo en cuenta la columna actual de la pieza
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

