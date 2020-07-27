import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        Map<String, String> parsedArgs = IntStream.range(0, args.length / 2).boxed()
                .collect(toMap(x -> args[x * 2], x -> args[x * 2 + 1]));
        Matrix matrix = read(parsedArgs.get("-in"));
        List<Complex[]> answer = Gauss.solve(matrix);
        write(answer, parsedArgs.get("-out"));
    }

    private static Matrix read(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] nm = br.readLine().split(" ");
            int variableCount = Integer.parseInt(nm[0]);
            int equationCount = Integer.parseInt(nm[1]);
            Matrix matrix = new Matrix(equationCount, variableCount + 1);
            for (int row = 0; row < equationCount; row++) {
                String[] parts = br.readLine().split(" ");
                for (int column = 0; column < variableCount + 1; column++) {
                    matrix.set(row, column, Complex.parse(parts[column]));
                }
            }
            return matrix;
        }
    }

    private static void write(List<Complex[]> answer, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filename))) {
            if (answer.size() != 1) {
                out.write(answer.isEmpty() ? "No solutions\n" : "Infinitely many solutions\n");
                return;
            }
            for (Complex x : answer.get(0)) out.println(x);
        }
    }
}
