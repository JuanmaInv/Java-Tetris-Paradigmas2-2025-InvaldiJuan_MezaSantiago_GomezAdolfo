# Java-Tetris-Paradigmas2-2025-JuanmaInv_Arke-off

# Trabajo Práctico 2: Tetris con POO vía TDD
# Objetivo:
  - Crear las clases necesarias en lenguaje Java con sus correspondientes test unitarios que satisfagan los requerimientos planteados. Y que los tests tengan más de 90% de cobertura.
  - Contenido que debe estar implementado:
  - Igualdad
  - Constructor
  - Sobrescribir métodos
  - Componentes de un objeto
  - Doble encapsulamiento (get set) atributo privado y método privado
  - Interfaz y método con interfaz
  - Herencia
  - Polimorfismo
  - Sobrecarga
  - Clases e Instancias de clases
  - Clase abstracta y método abstracto
  - Sin print, todos testeos unitarios JUnit que sean simples. Assert false true equals

# Criterios a tener en cuenta:
  - El siguiente trabajo práctico tiene nota grupal y personal (el código es grupal y el coloquio personal).
  - Los grupos deben ser de 3 personas y deben crear un repositorio grupal en Github
  - La rúbrica tiene una suma 100 puntos (nota 10 diez)\
  - Se debe realizar con el paradigma orientado a objetos en lenguaje Java y orientados con test unitarios.
  - Se deberá presentar con los test unitarios que pasen para cada funcionalidad (se podrán generar los test unitarios que sean necesarios)
  - Para aprobar se deberá presentar en coloquio del grupo explicando el 

# Se valorará: 
  - Aplicación de elementos, artefactos y principios del paradigma orientado a objetos
  - Realización de flujo de trabajo en GitHub. Repositorio grupal
  - Programación basada en test (TDD)
  - Cobertura de código más del 90%

# Entregables:
  - Coloquio grupal para presentar defender el trabajo
  - Enlace a repositorio grupal en Github
  - Cobertura del test del código

# Escenario: Tetris
  La empresa UCP Video Games Inc, desea realizar un videojuego para un evento internacional, el mismo se basa en el famoso juego Tetris.
  Se desea que el equipo de desarrollo pueda escribir el código base en Java basado en programación orientado a objetos para luego se pueda
  desarrollar la interfaz de usuario. Para eso como el Ingeniero en Sistemas conforma un equipo de trabajo de dos personas para este cometido. 


# Requerimientos técnicos


# Preparar entorno:
  - Probando el entorno + IDE
  - Pasos para validar
    - Crear una clase Tetris
    - Crear un test en TetrisTest

# Requerimiento 1:
  - Se deben poder crear piezas (una de cada tipo) (Piece.java)
  - Cada pieza se arma con 4 elementos.
  - Las piezas son:
      - La T (PieceT.java)
      - El cuadrado (PieceSquare.java)
      - El palo (PieceStick.java)
      - La ele (PieceL.java) left y right
      - El perro (PieceDog.java )left y right
  - ¿Cuántos Test? ¿Cómo se prueba?

# Requerimiento 2:
  - Las piezas se pueden rotar a izquierda o derecha.
  - En cada movimiento debe rotar sobre “su eje”.
  - ¿Cuántos movimientos hay?
  - Como testear?
  - ¿Cuántos test por pieza por movimiento?

# Requerimiento 3:
  - El tablero/ “board” tiene 10x20 elementos (Board.java)
  - Las piezas van ingresando por la parte de arriba
      - Aleatoriamente en cualquier lugar del primer renglón (siempre la pieza completa).
      - Y en una posición de la pieza aleatoria.
      - No se puede colocar una pieza por fuera del tablero.
  - Las piezas van bajando a medida el reloj indica x segundos
      - Ejemplo 2 segundos.
  - La pieza actual solamente es la que desciende a medida que el reloj avanza.
  - La pieza puede moverse (orientación) y descender solamente si puede caber en dicho lugar.

# Requerimiento 4:
  - Detener movimiento pieza actual.
      - Cuando no se puede descender o girar.
  - No se puede ingresar más piezas al tablero.
      - Cuando no hay “líneas” disponibles en el tablero (primera línea).
      - Finalizar juego (no se puede ingresar más piezas al tablero).
  
# Requerimiento 5:
  - Linea completa, desaparece (baja toda una línea)
      - A medida que una línea del tablero se completa.
      - Esa línea desaparece (se debe bajar todas las celdas).
  - El juego finaliza cuando se completan X líneas.
      - Ejemplo cuando se completan 5 líneas (configurable por el juego Tetris).
  - El tablero se mantiene con las mismas casillas.

# Mínimos Test a Entregar
  - Test de creación de piezas (PieceTest). Todas las piezas deben tener un test de creación y verificación de que exista con “la forma” de dicha pieza.
  - Test de rotación (a izquierda y derecha) de dicha pieza.
  - Test del tablero (Board).
      - Que tenga el formato.
      - Que se pueda agregar pieza (completa).
      - Que la pieza que se agrega no se pueda “sobrepasar” los límites del tablero.
  - Test del reloj (que cada vez que el reloj avanza).
      - Método tick del reloj(avanza). Contar de 1 el avance (no hay hora).
  - Que el tablero al agregar una pieza se pueda aleatoriamente “rotar” y luego agregar completa.
  - Que el tablero en cada “movimiento” haga que la pieza actual se mueva un fila para abajo (si se puede).
  - Que el tablero si se coloca varias piezas en una línea completa debe sumar la línea y remover dicha línea.
  - Que se pueda crear un juego Tetris.
      - Poder crear y comenzar.
      - Poder indicar desde afuera que se cree un “tick” del reloj.
      - Que en cada tick se juegue en el tablero el movimiento.

[DOCUMENTACION] (https://docs.google.com/document/d/1HcwQHvetELJaVBRKapoV5MmaNs7SmCDS7Mm4Bziafbc/edit?usp=sharing)

<img width="1003" height="157" alt="image" src="https://github.com/user-attachments/assets/10e665ec-d29d-4238-8715-ecc8001eda68" />


<img width="1012" height="486" alt="image" src="https://github.com/user-attachments/assets/549178b7-02f1-43bd-9f67-4d9846d2f725" />

