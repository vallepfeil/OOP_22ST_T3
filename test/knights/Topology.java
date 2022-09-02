/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 3
 * Die abstrakte Klasse Topology soll es ermöglichen, verschiedene Topologien zu realisieren. Die
 * Klasse soll die folgenden Methoden bzw. Konstruktor aufweisen:
 * • protected Topology(int rows, int columns) ist der Konstruktor. Die beiden Parameter
 * geben die Größe des Schachbretts an, d.h. die Anzahl seiner Zeilen und Spalten. Bei
 * ungültigen Angaben für rows oder columns soll eine IllegalArgumentException geworfen
 * werden.
 * • Getter-Methoden für rows und columns.
 * • public int numberOfFields() gibt die Anzahl der Felder, also das Produkt von Zeilen und
 * Spaltenanzahl zurück.
 * • public abstract Field map(Field f) ist eine abstrakte Methode, die von konkreten
 * Unterklassen realisiert werden muss und die für die Realisierung der konkreten Topologie
 * verantwortlich ist. Die jeweilige Implementierung muss die folgenden Eigenschaften
 * aufweisen:
 * 1. Wird ein Feld f übergeben, das innerhalb des Schachbretts liegt (d.h. falls 0 ≤
 * f.getRow() < rows und 0 ≤ f.getColumn() < columns, wobei rows und columns die
 * Größe des Schachbretts angeben), soll f, also dasselbe Feld zurückgegeben werden.
 * 2. In allen anderen Fällen soll entweder null zurückgegeben werden oder ein Feld, das
 * innerhalb des Schachbretts liegt.
 * Diese map-Methode kann verwendet werden, um die Springerzüge unter der jeweiligen Topologie
 * zu berechnen: Befinde sich ein Springer auf einem Feld f1=new Field(0,1). Dann
 * wäre ein potentieller Springerzug der nach f2=new Field(-1,-1), wenn man nämlich versucht,
 * eine Zeile nach oben und zwei Spalten nach links zu ziehen. Natürlich liegt f2
 * außerhalb des Schachbretts. Ob der Zug bei der gegebenen Topologie (sei das hier t) möglich
 * ist, sieht man am Ergebnis f3=t.map(f2). Der Zug ist nicht erlaubt, wenn f3 gleich
 * null ist. Andernfalls ist f3 das Feld innerhalb des Schachbretts (siehe die zweite der oberen
 * beiden Bedingungen), wohin der Springer nun tatsächlich gezogen wird.
 */

package knights;

public abstract class Topology {
    int rows;
    int columns;

    /**
     * Konstruktor
     *
     * @param rows    Zeilen
     * @param columns Spalten
     * @throws IllegalArgumentException Exception-Call im undefinierten Bereich (Negativ)
     */
    protected Topology(int rows, int columns) throws IllegalArgumentException {
        if (rows <= 0 || columns <= 0) throw new IllegalArgumentException(); this.rows = rows; this.columns = columns;
    }

    /**
     * Getter-Methoden
     *
     * @return return(rows), return(columns)
     */
    public int getRows() {return (rows);}

    public int getColumns() {return (columns);}

    /**
     * Anzahl der Felder
     *
     * @return rows * columns
     */
    public int numberOfFields() {return rows * columns;}

    public abstract Field map(Field f);
}
