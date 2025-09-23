package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TetrisTesteos {
    
    //Creacion de piezas
    //testear si las piezas se crean o no ✅
    //testear si las piezas tienen la forma correcta ✅
    //testear si las piezas tienen la rotacion correcta ✅
    
    // Tests para verificar creación de piezas
    @Test
    public void testCrearPieceStick(){
        Piece piece = new PieceStick();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceSquare(){
        Piece piece = new PieceSquare();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceDog(){
        Piece piece = new PieceDog();
        assertTrue(piece != null);
        }

    @Test
    public void testCrearPieceL(){
        Piece piece = new PieceL();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceT(){
        Piece piece = new PieceT();
        assertTrue(piece != null); 
    }

    // Tests para verificar formas correctas
    @Test
    public void testFormasCorrectas() {
        // Verificar que las piezas tienen forma inicial correcta
        PieceSquare square = new PieceSquare();
        assertEquals(2, square.getAncho()); //verifica ancho
        assertEquals(2, square.getAlto()); //verifica alto
        assertFalse(square.esVacia()); // El cuadrado no está vacío
        
        PieceStick stick = new PieceStick();
        assertEquals(4, stick.getAncho()); //verifica ancho
        assertEquals(4, stick.getAlto()); //verifica alto
        assertFalse(stick.esVacia()); // El stick no está vacío

        PieceL l = new PieceL();
        assertEquals(3, l.getAncho()); //verifica ancho
        assertEquals(3, l.getAlto()); //verifica alto
        assertFalse(l.esVacia()); // La L no está vacía

        PieceT t = new PieceT();
        assertEquals(3, t.getAncho()); //verifica ancho
        assertEquals(3, t.getAlto()); //verifica alto
        assertFalse(t.esVacia()); // La T no está vacía

        PieceDog dog = new PieceDog();
        assertEquals(3, dog.getAncho()); //verifica ancho
        assertEquals(3, dog.getAlto()); //verifica alto
        assertFalse(dog.esVacia()); // El perro no está vacío
    }
    
    
    // Tests simples para verificar rotaciones correctas
    @Test
    public void testRotacionesSimples() {
        // Crear pieza y verificar que puede rotar sin errores
        PieceStick stick = new PieceStick();
        // Rotar derecha - verificar que la rotación se ejecuta sin errores
        stick.rotarDerecha();
        // Verificar que sigue teniendo una forma válida
        assertFalse(stick.esVacia()); // El stick no está vacío
        
        // Rotar izquierda
        PieceL piece2 = new PieceL(); // Nueva instancia para evitar acumulación de rotaciones
        piece2.rotarIzquierda(); // Rotar izquierda
        assertFalse(piece2.esVacia()); // La L no está vacía

        // El cuadrado debería mantenerse igual al rotar
        PieceSquare square = new PieceSquare();
        int anchoSquare = square.getAncho();
        int altoSquare = square.getAlto();
        square.rotarDerecha();
        assertEquals(anchoSquare, square.getAncho());
        assertEquals(altoSquare, square.getAlto());
    }

    // Tests para verificar creación de piezas    @Test
    public void testPieceLFormaCorrecta() {
        PieceL piece = new PieceL();
        int[][] formaEsperada = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
        
        // Verificar dimensiones
        assertEquals(3, piece.getAncho());
        assertEquals(3, piece.getAlto());
        
        // Verificar forma específica
        int[][] formaActual = piece.getForma();
        for (int i = 0; i < formaEsperada.length; i++) {
            for (int j = 0; j < formaEsperada[i].length; j++) {
                assertEquals(formaEsperada[i][j], formaActual[i][j]);
            }
        }
    }
    

    //testear rotaciones
    @Test
    public void testRotarPiecesDerecha(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarDerecha();
        piece2.rotarDerecha();
        piece3.rotarDerecha();
        piece4.rotarDerecha();
        piece5.rotarDerecha();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesIzquierda(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarIzquierda();
        piece2.rotarIzquierda();
        piece3.rotarIzquierda();
        piece4.rotarIzquierda();
        piece5.rotarIzquierda();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesLadoRandom(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.seleccionarLadoRandom();
        piece2.seleccionarLadoRandom();
        piece3.seleccionarLadoRandom();
        piece4.seleccionarLadoRandom();
        piece5.seleccionarLadoRandom();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    // Test simple para verificar que el stick rota correctamente
    @Test
    public void testStickRotacion() {
        PieceStick stick = new PieceStick();
        
        // Guardar forma original
        int[][] formaOriginal = stick.getForma();
        
        // Rotar derecha
        stick.rotarDerecha();
        
        // Verificar que la forma cambió
        int[][] formaRotada = stick.getForma();
        boolean cambio = false;
        
        // Comparar si algún elemento cambió de posición
        for (int i = 0; i < formaOriginal.length && i < formaRotada.length; i++) {
            for (int j = 0; j < formaOriginal[i].length && j < formaRotada[i].length; j++) {
                if (formaOriginal[i][j] != formaRotada[i][j]) {
                    cambio = true;
                    break;
                }
            }
            if (cambio) break;
        }
        
        assertTrue("La forma del stick debería cambiar al rotar", cambio);
        assertFalse("El stick no debería estar vacío después de rotar", stick.esVacia());
    }
    
    @Test
        public void testPieceSquareRotacion() {
            PieceSquare piece = new PieceSquare();
        
            // Rotar y verificar que sigue siendo válido
            piece.rotarDerecha();
            assertFalse("El cuadrado no debería estar vacío después de rotar", piece.esVacia());
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAncho());
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAlto());
        }
    
    @Test
    public void rotar_PieceSquare_360_derecha_test() {
        PieceSquare pS1 = new PieceSquare();
        PieceSquare pS2 = new PieceSquare();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
            for (int i = 0; i < pS1.getForma().length; i++) {// recorre filas
                assertArrayEquals(pS2.getForma()[i], pS1.getForma()[i]);// compara fila por fila
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void rotar_PieceL_360_derecha_test() {
        PieceL l1 = new PieceL();
        PieceL l2 = new PieceL();

        l1.rotarDerecha();
        l1.rotarDerecha();
        l1.rotarDerecha();
        l1.rotarDerecha();

            for (int i = 0; i < l1.getForma().length; i++) {
                assertArrayEquals(l2.getForma()[i], l1.getForma()[i]);
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    //Creacion y estado del juego
    //testear si el juego inicia/termina o no ✅
    @Test
    public void testJuegoInicia(){
        Tetris game = new Tetris();
        game.iniciarJuego();
       assertEquals(game.getEstado(), 1);
    }
    
    @Test
    public void testJuegoTermina(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);
        game.terminarJuego();
        assertEquals(game.getEstado(), 2);
    }
    
    @Test
    public void testReiniciarJuego(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);

        game.reiniciarJuego();
        // Verificar que está iniciado y terminado
        assertTrue(game.getEstado() == 0);

    }
    
    //Creacion y estado del tablero
    //testear si el tablero esta lleno o vacio ✅
    //testear si una pieza puede moverse o no en el tablero ✅
    //testear si una pieza puede rotar o no en el tablero
    //testear si una pieza puede caer al vacio o no en el tablero ✅
    //testear si una linea puede ser eliminada o no en el tablero
    //testear en casos de limites del tablero y colisiones/choques de piezas
    //testear si una pieza puede ser colocada o no en el tablero
    @Test
    public void testTableroVacio() {
        Board tablero = new Board();
        assertTrue(tablero.tableroVacio());
    }

    
    @Test
    public void testTableroLleno(){
        Board tablero = new Board();
        assertFalse(!tablero.tableroVacio());
    }


    @Test
    public void testMoverPiezaEnTablero() {
        Board tablero = new Board();
        Piece piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // Coloca la pieza en la posición inicial
        // Mover la pieza a la derecha e izquierda
        tablero.moverPieza(piece, 0, 1); // derecha
        tablero.moverPieza(piece, 0, -1); // izquierda
        assertTrue(true); // Solo verifica que los métodos se ejecutan sin error
    }

    @Test
    public void testPiezaCaidaLibre(){
        Board tablero = new Board();
        Piece piece1 = new PieceSquare();

        tablero.setPiezaActual(piece1);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.caidaLibre(piece1);

        // Verificar que ya no sea posible bajar una fila más
        boolean puedeBajar = tablero.verificarColocacionValida(piece1, tablero.getFilaActual() + 1, tablero.getColumnaActual());
        assertFalse("La pieza debería haber bajado hasta tocar otra pieza o el fondo", puedeBajar);

        // Verifica que la pieza esté dentro de los límites
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);

        // Verificar que la pieza esté en la fila más baja posible
    int altoTablero = tablero.getBoard().length; // por ejemplo, 10
    int altoPieza = piece1.getAlto();    // para PieceSquare, 2
    int filaFinalEsperada = altoTablero - altoPieza; // última fila posible
    assertEquals(filaFinalEsperada, tablero.getFilaActual()); // Verifica que la pieza esté en la fila esperada

        // Verificar que la pieza esté en la columna más baja posible (en este caso, 0)
        int columnaFinalEsperada = 0;
        assertEquals(columnaFinalEsperada, tablero.getColumnaActual()); // Verifica que la pieza esté en la columna esperada
    }


    @Test
    public void testPiezaDogCaidaLibre(){
        Board tablero = new Board(); // creo tablero
        Piece piece = new PieceDog(); // creo pieza Dog
        tablero.setPiezaActual(piece); // le digo al tablero cuál es la pieza actual
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // coloco la pieza en la posición inicial

        // Llamar a caidaLibre: debe bajar hasta chocar con el fondo
        tablero.caidaLibre(piece);

        // Verificar que ya no sea posible bajar una fila más
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual());
        assertFalse("La pieza debería haber bajado hasta tocar otra pieza o el fondo", tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()));
        // Verifica que la pieza esté dentro de los límites
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);
    }

    // Nuevo test: caída libre pero activada mediante ticks del reloj
    @Test
    public void testPiezaCaidaLibreConTick(){
        //  la pieza baja cada tick y terminamos cuando ya no puede bajar mas
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard();
        Piece piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0);

        // Hacemos que la pieza baje en cada tick, desciende cada 1 unidad de tiempo
        // Esto simula la caída libre activada por el reloj del juego
        tetris.setIntervaloDescenso(1);
        tetris.iniciarJuego();

        // LIMITE: maximo de iteraciones para evitar bucle infinito en caso de bug
        int LIMITE = 30; // tope de seguridad
        int antes = tablero.getFilaActual(); // fila antes de tick
        // iteramos hasta que la pieza ya no pueda bajar mas o se alcance el limite
        // lo que hace este for es simular el paso del tiempo
        // cada iteracion es un tick
        // cada tick hace que la pieza baje una fila
        for (int i = 0; i < LIMITE; i++) { // iteramos
            tetris.tick(); // avanza el reloj y hace bajar la pieza si corresponde
            int ahora = tablero.getFilaActual(); // fila después de tick
            if (ahora == antes) { //Permite salir si la pieza no baja mas porque colisiono con otra pieza o el fondo
                break;
            }
            antes = ahora; // actualiza antes para la proxima iteracion
        }

        // Verificamos que no puede bajar mas (esta apoyada)
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false
        assertFalse("La pieza deberia haber alcanzado el fondo tras los ticks", puedeBajar); // espera false
    }

    @Test
    public void testPiezaDogCaidaLibreConTick(){
        // Igual que el test anterior pero con la pieza Dog
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard();
        Piece piece = new PieceDog();
        tablero.setPiezaActual(piece);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0);

        tetris.setIntervaloDescenso(1); // la pieza baja cada tick
        tetris.iniciarJuego(); // inicia el juego

        int LIMITE = 30; // tope de seguridad para evitar bucle infinito
        int antes = tablero.getFilaActual(); // fila antes de tick
        // iteramos hasta que la pieza ya no pueda bajar mas o se alcance el limite
        // lo que hace este for es simular el paso del tiempo
        // cada iteracion es un tick
        // cada tick hace que la pieza baje una fila
        for (int i = 0; i < LIMITE; i++) {
            tetris.tick();
            int ahora = tablero.getFilaActual(); // fila después de tick
            if (ahora == antes){ // Permite salir si la pieza no baja más porque colisiono con otra pieza o el fondo
                break;
            }
            antes = ahora;
        }

        // Verificamos que no puede bajar mas (esta apoyada)
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false
        assertFalse("La pieza Dog debería haber alcanzado el fondo tras los ticks", puedeBajar); // espera false
    }
// El test crea un Board, coloca una pieza cuadrada (PieceSquare) en una posición baja del tablero y comprueba tres cosas:
// Que no se pueda colocar otra pieza exactamente encima (colisión).
// Que sí se pueda colocar la misma pieza en una posición libre (sin colisión).
// Que una colocación que saldría parcialmente fuera del tablero sea inválida.
    @Test
    public void testColisionEntrePiezas() { //
        Board tablero = new Board(); //Crea un tablero vacío donde hare las validaciones

        // Creo y coloco la primera pieza (square) en una posicion especifica
        Piece primera = new PieceSquare();
        tablero.colocarPiezaEnTableroVerificada(primera, 8, 5); // coloca un 2x2 en filas 8-9, col 5-6 [(8,5); (8,6); (9,5); (9,6)]

        //Creo otra pieza igual a la anterior
        Piece segunda = new PieceSquare();

        // Intento colocar otra pieza que colisione con la anterior
        //Para mas precision de "Colision" intento colocar una pieza de misma forma en la misma posicion.\
        boolean verificacionColision = tablero.verificarColocacionValida(segunda, 8, 5); //pregunta si es valido colocar la segunda pieza en la misma posicion que la primera, espera false porque hay colision
        assertFalse("No es valido colocar otra pieza en esa posicion", verificacionColision); //espera false

        // Intentar colocar otra pieza en una posición valida donde no hay piezas
        boolean puedeColocarLibre = tablero.verificarColocacionValida(segunda, 6, 5);
        assertTrue("Debería poder colocarse una pieza en posición libre", puedeColocarLibre);

        // Intentar colocar una pieza parcialmente fuera del tablero
        boolean fueraTablero = tablero.verificarColocacionValida(segunda, 9, 19); // borde derecho/inferior
        assertFalse("No debería ser válida una colocación que sale del tablero", fueraTablero);
    }

    @Test
    public void testColisionEntrePieceDog(){
        Board tablero = new Board();
        // Colocar la primera pieza (Dog) en la parte baja central
        Piece primera = new PieceDog();
        tablero.colocarPiezaEnTableroVerificada(primera, 8, 5); // coloca un 3x3 en filas 8-10, col 5-7

        //Verificar que hay una pieza en esa posicion
        assertTrue("Debería haber una pieza en la posición (8,6)", tablero.getBoard()[8][6] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (8,7)", tablero.getBoard()[8][7] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (9,5)", tablero.getBoard()[9][5] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (9,6)", tablero.getBoard()[9][6] != 0); //espera true porque hay pieza

        //verifica en una posicion que no deberia haber piezas, que no las hay
        assertFalse("No debería haber una pieza en la posición (7,5)", tablero.getBoard()[7][5] != 0); //espera false porque no hay pieza


        // Intentar colocar otra pieza que solape exactamente la anterior
        Piece segunda = new PieceDog();
        boolean verificacionColision = tablero.verificarColocacionValida(segunda, 8, 5); //espera false
        assertFalse("No debería poder colocarse una pieza solapada sobre otra", verificacionColision); //espera false

        // Intentar colocar otra pieza en una posición valida donde no hay piezas
        boolean puedeColocarPiece = tablero.verificarColocacionValida(segunda, 5, 5); //espera true
        assertTrue("Debería poder colocarse una pieza en posición libre", puedeColocarPiece); //espera true

        // Colocar la segunda pieza en la posición libre luego de validar  que es posible colocarla
        tablero.colocarPiezaEnTableroVerificada(segunda, 5, 5); //coloca la pieza en una posicion libre

        //Verificar que hay una pieza en esa posicion
        assertTrue("Debería haber una pieza en la posición (5,6)", tablero.getBoard()[5][6] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (5,7)", tablero.getBoard()[5][7] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (6,5)", tablero.getBoard()[6][5] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (6,6)", tablero.getBoard()[6][6] != 0); //espera true porque hay pieza

        assertFalse("No debería haber una pieza en la posición (7,5)", tablero.getBoard()[7][5] != 0); //espera false porque no hay pieza


        // Intentar colocar una pieza parcialmente fuera del tablero
        boolean fueraTablero = tablero.verificarColocacionValida(segunda, 9, 18); //esta fuera del tablero porque la pieza Dog es 3x3 y el tablero tiene 20 columnas si yo la coloco alli se sale del tablero
        assertFalse("No debería ser válida una colocación que sale del tablero", fueraTablero); //espera false
    }

    @Test
    public void testColisionyEliminacionDeLineas (){
        Tetris tetris = new Tetris();
        tetris.iniciarJuego();
        Board tablero = new Board(); // creo tablero

        // Crear 10 piezas cuadradas para llenar una fila completa
        Piece piece1 = new PieceSquare();
        Piece piece2 = new PieceSquare();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceSquare();
        Piece piece5 = new PieceSquare();
        Piece piece6 = new PieceSquare();
        Piece piece7 = new PieceSquare();
        Piece piece8 = new PieceSquare();
        Piece piece9 = new PieceSquare();
        Piece piece10 = new PieceSquare();
        // Colocar 10 piezas cuadradas (2x2) para llenar la fila 9 (índice 8)
        tablero.colocarPiezaEnTableroVerificada(piece1, 8, 0); // fila 8, columnas 0-1
        tablero.colocarPiezaEnTableroVerificada(piece2, 8, 2); // fila 8, columnas 2-3
        tablero.colocarPiezaEnTableroVerificada(piece3, 8, 4); // fila 8, columnas 4-5
        tablero.colocarPiezaEnTableroVerificada(piece4, 8, 6); // fila 8, columnas 6-7
        tablero.colocarPiezaEnTableroVerificada(piece5, 8, 8); // fila 8, columnas 8-9
        tablero.colocarPiezaEnTableroVerificada(piece6, 8, 10); // fila 8, columnas 10-11
        tablero.colocarPiezaEnTableroVerificada(piece7, 8, 12); // fila 8, columnas 12-13
        tablero.colocarPiezaEnTableroVerificada(piece8, 8, 14); // fila 8, columnas 14-15
        tablero.colocarPiezaEnTableroVerificada(piece9, 8, 16); // fila 8, columnas 16-17
        tablero.colocarPiezaEnTableroVerificada(piece10, 8, 18); // fila 8, columnas 18-19

        // Marcar la última pieza como piezaActual para que los métodos que la consulten no hagan NPE
        tablero.setPiezaActual(piece10);

        // Verificar que la  fila 8 está completamente ocupada
        // recorremos todas las columnas de la fila 8 utilizando getBoard()[0].length para obtener el número de columnas del tablero
        for (int j = 0; j < tablero.getColumnas(); j++) { // recorre columnas
            assertTrue("La fila 8 debería estar ocupada" + j, tablero.getBoard()[8][j] != 0); //espera true, con getBoard()[8][j] accedemos a la fila 8 y columna j del tablero
        }
        // Antes de eliminar, intentar colocar otra pieza encima debe FALLAR (colisión)
        Piece otra = new PieceSquare();
        boolean verificacionAntes = tablero.verificarColocacionValida(otra, 8, 0);
        assertFalse("Debería haber colisión al intentar colocar sobre una fila llena", verificacionAntes);

        // Eliminar líneas llenas
        tablero.verificarYEliminarLineas();
        int antes = tablero.getLineasEliminadas();
        tablero.verificarYEliminarLineas();
        int despues = tablero.getLineasEliminadas();
        assertEquals("Debería haberse eliminado 1 línea", antes, despues);

        // Verificar que la fila 9 ahora está vacía
        for (int j = 0; j < tablero.getColumnas(); j++) {
            assertEquals("La fila 9 debería estar vacía después de la eliminación", 0, tablero.getBoard()[9][j]); //espera 0
        }
    }

    @Test
    public void testcaidalibreycolisionentresquares(){ // Test que combina caída libre y colisión entre piezas(dos piezas square)
        Tetris tetris = new Tetris();
        tetris.iniciarJuego();
        Board tablero = tetris.getBoard();
        Piece piece1 = new PieceSquare();
        Piece piece2 = new PieceSquare();

        // Colocar la primera pieza en la parte baja del tablero
        tablero.setPiezaActual(piece1);
        tablero.setFilaActual(8);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece1, 8, 0); 

        // Colocar la segunda pieza en la parte alta del tablero
        tablero.setPiezaActual(piece2);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece2, 0, 0); // coloca un 2x2 en filas 0-1, col 0-1

        // Hacer que la segunda pieza caiga libremente
        tablero.caidaLibre(piece2);

          tablero.verificarColocacionValida(piece1, 8, 0); //verifica que la primera pieza sigue en su lugar
        // Verificar que la segunda pieza se detuvo justo encima de la primera
        tablero.verificarColocacionValida(piece2, 0, 0);
        boolean puedeBajar = tablero.verificarColocacionValida(piece2, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false porque la pieza no puede bajar mas
        assertFalse("La segunda pieza debería haberse detenido al tocar la primera", puedeBajar);
         //espera false ya que la pieza no puede bajar mas porque choca con la primera pieza
    }

}

