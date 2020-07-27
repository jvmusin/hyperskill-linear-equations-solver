import java.util.Arrays;

public class Row {
    private final Complex[] values;

    public Row(int n) {
        this.values = new Complex[n];
        Arrays.fill(values, Complex.ZERO);
    }

    public void divide(Complex k) {
        for (int i = 0; i < values.length; i++) values[i] = values[i].divide(k);
    }

    public void add(Row row, Complex k) {
        for (int i = 0; i < values.length; i++) values[i] = values[i].add(row.values[i].multiply(k));
    }

    public void subtract(Row row, Complex k) {
        add(row, k.negative());
    }

    public Complex get(int at) {
        return values[at];
    }

    public int length() {
        return values.length;
    }

    public void set(int i, Complex value) {
        values[i] = value;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    public void swapColumns(int column1, int column2) {
        ArrayUtils.swap(values, column1, column2);
    }
}
