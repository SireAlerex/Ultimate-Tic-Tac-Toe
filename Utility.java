public class Utility {
    public static int IndexOfMax(double[] array) {
        double maxValue = array[0];
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < array.length; i++) System.out.println(" "+array[i]);
        return maxIndex;
    }
}
