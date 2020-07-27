import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {
    private final Row[] rows;

    public Matrix(int rowCount, int columnCount) {
        rows = IntStream.range(0, rowCount)
                .mapToObj(i -> new Row(columnCount))
                .toArray(Row[]::new);
    }

    public void swapRows(int row1, int row2) {
        ArrayUtils.swap(rows, row1, row2);
    }

    public void swapColumns(int column1, int column2) {
        for (Row row : rows) row.swapColumns(column1, column2);
    }

    public Row getRow(int i) {
        return rows[i];
    }

    public void divideRow(int rowIndex, Complex k) {
        rows[rowIndex].divide(k);
    }

    public Complex get(int row, int column) {
        return rows[row].get(column);
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return rows[0].length();
    }

    public void set(int row, int column, Complex value) {
        rows[row].set(column, value);
    }

    @Override
    public String toString() {
        return Arrays.stream(rows)
                .map(Objects::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
