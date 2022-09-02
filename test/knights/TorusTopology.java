package knights;

public class TorusTopology extends Topology {
    /**
     * Konstruktor
     *
     * @param rows    Zeilen
     * @param columns Spalten
     * @throws IllegalArgumentException Exception-Call im undefinierten Bereich (Negativ)
     */
    protected TorusTopology(int rows, int columns) throws IllegalArgumentException {
        super(rows, columns);
    }

    // Map muss noch geschrieben werden!
    @Override
    public Field map(Field f) {
        return null;
    }
}
