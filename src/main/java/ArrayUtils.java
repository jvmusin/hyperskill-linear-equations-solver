public class ArrayUtils {
    public static void swap(int[] array, int column1, int column2) {
        int x = array[column1];
        array[column1] = array[column2];
        array[column2] = x;
    }

    public static <T> void swap(T[] array, int column1, int column2) {
        T x = array[column1];
        array[column1] = array[column2];
        array[column2] = x;
    }
}
