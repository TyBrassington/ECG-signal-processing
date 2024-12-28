public class Main {
    public static void main(String[] args) {
        //placeholder
        double[] signal = {};

        int windowSize = 5;

        double result[] = PanTompkins.panTompkinsFilter(signal, windowSize);

        System.out.println("Filtered Signal:");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}