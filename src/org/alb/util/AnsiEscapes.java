package org.alb.util;

import java.io.IOException;

/*
 * The MIT License
 *
 * Copyright 2018 andreaalbertini.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * <p>
 * Utility per l'uso di codici ANSI ESCAPES. Implementa i colori a 3/4 bit (i
 * colori a 16 o 256 bit non sono supportati) e alcuni comandi (solo quelli
 * supportati dalla maggior parte dei terminali).
 *
 * <p>
 * Esempio di utilizzo:
 * <pre>
 * {@code
 *      AnsiEscapes.clearScreen(); // pulisce lo schermo
 *      AnsiEscapes.setForegroundColor(AnsiEscapes.COLOR_RED); // setta il colore rosso
 *      System.out.print("Hello world"); // stampa "Hello world" in rosso
 *      AnsiEscapes.setForegroundColor(AnsiEscapes.COLOR_BLUE); // setta il colore rosso
 *      System.out.print("Hello world"); // stampa "Hello world" in blu
 *      AnsiEscapes.reset();//resettatutti gli attributi del terminale
 * }
 * </pre> Risultato:<br>
 * <img src="HelloWorld.png" alt="Esempio 1"><br>
 *
 * <p>
 * Per un utilizzo pi&uacute; comodo della classe &egrave; possibile importare
 * tutte le costanti e i metodi statici di AnsiEscapes. In questo modo &egrave;
 * possibile utilizzare le costanti e i metodi senza dover fare esplicitamente
 * riferimento alla classe AnsiEscapes (Ad esempio "clearScreen()" invece di
 * "AnsiEscapes.clearScreen()").
 *
 * <p>
 * Esempio:
 * <pre>
 * {@code
 *  import static org.alb.util.AnsiEscapes.*;
 *
 *  public class MyColoredTest{
 *      public static void main(String[] args){
 *          clearScreen(); // pulisce lo schermo
 *          moveTo(10, 10);//sposta il cursore alla riga 10, colonna 10
 *          setForegroundColor(COLOR_RED); // setta il colore rosso
 *          setBold();//attiva grassetto
 *          System.out.println("Hello world"); // stampa "Hello world" in rosso e grassetto
 *          reset();//reset bold e colori
 *          setForegroundColor(COLOR_BLUE); // setta il colore rosso
 *          moveTo(11, 10);//sposta il cursore alla coordinata 11,10
 *          System.out.println("Hello world"); // stampa "Hello world" in blu
 *          reset();//resetta tutti gli attributi del terminale
 *      }
 *  }
 * }
 * </pre>
 *
 *
 * @author Andrea Albertini
 */
public class AnsiEscapes {

    /**
     * Il codice del colore nero.
     */
    public static final int COLOR_BLACK = 30;

    /**
     * Il codice del colore rosso.
     */
    public static final int COLOR_RED = 31;

    /**
     * Il codice del colore verde.
     */
    public static final int COLOR_GREEN = 32;

    /**
     * Il codice del colore giallo.
     */
    public static final int COLOR_YELLOW = 33;

    /**
     * Il codice del colore blue.
     */
    public static final int COLOR_BLUE = 34;

    /**
     * Il codice del colore magenta.
     */
    public static final int COLOR_MAGENTA = 35;

    /**
     * Il codice del colore ciano.
     */
    public static final int COLOR_CYAN = 36;

    /**
     * Il codice del colore bianco.
     */
    public static final int COLOR_WHITE = 37;

    /**
     * Il codice del colore nero brillante.
     */
    public static final int COLOR_BRIGHT_BLACK = 90;

    /**
     * Il codice del colore rosso brillante.
     */
    public static final int COLOR_BRIGHT_RED = 91;

    /**
     * Il codice del colore verde brillante.
     */
    public static final int COLOR_BRIGHT_GREEN = 92;

    /**
     * Il codice del colore giallo brillante.
     */
    public static final int COLOR_BRIGHT_YELLOW = 93;

    /**
     * Il codice del colore blue brillante.
     */
    public static final int COLOR_BRIGHT_BLUE = 94;

    /**
     * Il codice del colore magenta brillante.
     */
    public static final int COLOR_BRIGHT_MAGENTA = 95;

    /**
     * Il codice del colore ciano brillante.
     */
    public static final int COLOR_BRIGHT_CYAN = 96;

    /**
     * Il codice del colore bianco brillante.
     */
    public static final int COLOR_BRIGHT_WHITE = 97;

    /**
     * Il comando di reset di tutti gli attributi.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Il comando clear screen. Usato per pulire lo schermo.
     */
    private static final String ANSI_CLS = "\u001b[2J";

    /**
     * Il comando per mandare il cursore alla cordinata HOME (1,1).
     */
    private static final String ANSI_HOME = "\u001b[H";

    /**
     * Il comando per attivare la modalit&aacute; bold (grassetto).
     */
    private static final String ANSI_BOLD = "\u001b[1m";

    /**
     * Il comando per attivare la modalit&aacute; bold (grassetto).
     */
    private static final String ANSI_NORMAL = "\u001b[21m";

    /**
     * Comando di swap, inverte il colore di background con quello di
     * foreground.
     */
    private static final String ANSI_REVERSEON = "\u001b[7m";

    /**
     * Resetta la stato di default del terminale.
     */
    public static void reset() {
        System.out.print(ANSI_RESET);
    }

    /**
     * Pulisce lo schermo.
     */
    public static void clearScreen() {
        System.out.print(ANSI_CLS);
    }

    /**
     * Attiva la modalit&aacute; bold (grassetto).
     */
    public static void setBold() {
        System.out.print(ANSI_BOLD);
    }

    /**
     * Sposta il cursore alla coordinata specificata da row e col. Le coordinate
     * sono specificate a partire da 1 (Dove [1,1] &egrave; l'angolo superiore
     * sinistro dello schermo). Se i valori dei parametri forniti per row e col
     * sono inferiori a 1, il metodo non fa niente.
     *
     * @param row la coordinata verticale (riga) in cui spostare il cursore
     * @param col la coordinata orizzontale (colonna) in cui spostare il cursore
     */
    public static void moveTo(int row, int col) {
        if (row > 0 && col > 0) {
            System.out.print("\u001b[" + row + ";" + col + "H");
        }
    }

    /**
     * Definisce il colore dello sfondo da applicare nelle stampe successive. Il
     * parametro foregroundColor deve essere uno dei codici colore validi (es.
     * COLOR_BLACK). Se i l codice colore fornito non &egrave; valido, il metodo
     * non fa niente.
     *
     * @param foregroundColor il codice del colore dello sfondo
     */
    public static void setForegroundColor(int foregroundColor) {
        if (isColorCodeValid(foregroundColor)) {
            System.out.print("\u001b[" + foregroundColor + "m");
        }
    }

    /**
     * Definisce il colore da applicare nelle stampe successive. Il parametro
     * backgroundColor deve essere uno dei codici colore validi (es.
     * COLOR_BLACK). Se il codice colore fornito non &egrave; valido, il metodo
     * non fa niente.
     *
     * @param backgroundColor il codice del colore della stampa
     */
    public static void setBackgroundColor(int backgroundColor) {
        if (isColorCodeValid(backgroundColor)) {
            System.out.print("\u001b[" + (backgroundColor + 10) + "m");
        }
    }

    /**
     * Definisce il colore di background e il colore di background. I parametri
     * backgroundColor e foregroundColor devono essere dei codici colore validi
     * (es. COLOR_BLACK). Se il codice colore fornito non &egrave; valido, il
     * metodo non fa niente.
     *
     * @param backgroundColor il codice del colore di background
     * @param foregroundColor il codice del colore di foreground
     */
    public static void setColor(int backgroundColor, int foregroundColor) {
        if (isColorCodeValid(backgroundColor) && isColorCodeValid(foregroundColor)) {
            System.out.print("\u001b[" + foregroundColor + ";" + (backgroundColor + 10) + "m");
        }
    }

    /**
     * Verifica se il codice colore (3/4 bit) &egrave; supportato.
     *
     * @param color il codice colore da verificare
     * @return true se il codice &egrave; valido, false altrimenti.
     */
    private static boolean isColorCodeValid(int color) {
        return color >= COLOR_BLACK && color <= COLOR_WHITE
                || color >= COLOR_BRIGHT_BLACK && color <= COLOR_BRIGHT_WHITE;
    }

    /**
     * Ritorna il codice del prossimo carattere nel buffer della tastiera se
     * disponibile, altrimenti ritorna -1.
     *
     * @return il codice del prossimo carattere se disponibile, -1 altrimenti
     * @throws IOException se non è possibile leggere la tastiera
     */
    public static int getKey() throws IOException {
        if (System.in.available() > 0) {
            return System.in.read();
        } else {
            return -1;
        }
    }
}
