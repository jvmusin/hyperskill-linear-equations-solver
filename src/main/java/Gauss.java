import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.singletonList;

public class Gauss {
    public static List<Complex[]> solve(Matrix m) {
        return new Solver(m).solve();
    }

    private static boolean isNotZero(Complex x) {
        return x.isNotZero();
    }

    private static class Solver {
        final Matrix m;
        final int variableCount;
        final int[] resultIndices;

        Solver(Matrix m) {
            this.m = m;
            this.variableCount = m.getColumnCount() - 1;
            this.resultIndices = IntStream.range(0, variableCount).toArray();
        }

        List<Complex[]> solve() {
            int realVariableCount = phase1();
            for (int i = realVariableCount; i < m.getRowCount(); i++) {
                for (int j = 0; j < m.getColumnCount(); j++) {
                    if (isNotZero(m.get(i, j))) {
                        return Collections.emptyList();
                    }
                }
            }
            if (realVariableCount != variableCount) return Arrays.asList(null, null);

            phase2(realVariableCount);

            Complex[] answer = new Complex[variableCount];
            for (int i = 0; i < variableCount; i++) answer[resultIndices[i]] = m.get(i, variableCount);
            return singletonList(answer);
        }

        boolean fixPhase1Column(int row) {
            for (int row1 = row; row1 < m.getRowCount(); row1++) {
                if (isNotZero(m.get(row1, row))) {
                    m.swapRows(row, row1);
                    return true;
                }
            }
            for (int row1 = row; row1 < m.getRowCount(); row1++) {
                for (int column1 = row; column1 < variableCount; column1++) {
                    if (isNotZero(m.get(row1, column1))) {
                        m.swapColumns(column1, row);
                        ArrayUtils.swap(resultIndices, column1, row);
                        m.swapRows(row, row1);
                        return true;
                    }
                }
            }
            return false;
        }

        int phase1() {
            for (int variable = 0; variable < variableCount; variable++) {
                if (!fixPhase1Column(variable)) return variable;
                m.divideRow(variable, m.get(variable, variable));
                for (int row = variable + 1; row < m.getRowCount(); row++) {
                    fixRow(variable, row);
                }
            }
            return variableCount;
        }

        void phase2(int realVariableCount) {
            for (int variable = realVariableCount - 1; variable >= 0; variable--) {
                for (int row = variable - 1; row >= 0; row--) {
                    fixRow(variable, row);
                }
            }
        }

        void fixRow(int variable, int row) {
            if (isNotZero(m.get(row, variable))) {
                Complex k = m.get(row, variable).divide(m.get(variable, variable));
                m.getRow(row).subtract(m.getRow(variable), k);
            }
        }
    }
}
