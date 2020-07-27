public class DoubleUtils {
    private static final double EPS = 1e-8;

    public static boolean equals(double x, double y) {
        return Math.abs(x - y) < EPS;
    }

    public static boolean isZero(double x) {
        return equals(x, 0);
    }
}
