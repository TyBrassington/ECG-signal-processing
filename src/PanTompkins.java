public class PanTompkins {
    public static double[] lowPassFilter(double[] signal){
        int n = signal.length;
        double[] output = new double[n];

        for (int i = 0; i < n; i++) {
            output[i] = signal[i];
            if (i >= 1) output[i] += 2 * output[i-1];
            if (i >= 2) output[i] -= output[i-2];
            if (i >= 6) output[i] -= 2 * signal[i-6];
            if (i >= 12) output[i] += signal[i-12];
        }
        return output;
    }

    public static double[] highPassFilter(double[] signal){
        int n = signal.length;
        double[] output = new double[n];

        for (int i = 0; i < n; i++) {
            output[i] = signal[i];
            if (i >= 1) output[i] += output[i-1];
            if(i >= 32) output[i] -= output[i-32];
        }
        return output;
    }

    public static double[] bandPassFilter(double[] signal){
        double[] temp = lowPassFilter(signal);
        return highPassFilter(temp);
    }

}
