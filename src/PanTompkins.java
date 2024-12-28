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


    public static double[] differentiation(double[] signal){
        int n = signal.length;
        double[] output = new double[n];
        for (int i = 4; i < n; i++) { //dont allow negative index
            output[i] = (2 * signal[i] + signal[i-1] - signal[i-3]- 2 * signal[i-4]) / 8;
        }
        return output;
    }

    public static double[] square(double[] signal){
        int n = signal.length;
        double[] output = new double[n];
        for (int i = 0; i < n; i++) {
            output[i] = signal[i] * signal[i];
        }
        return output;
    }

    public static double[] movingWindow(double[] signal, int windowSize){
        int n = signal.length;
        double[] output = new double[n];
        double sum = 0;

        //init moving window
        for (int i = 0; i < windowSize && i < n; i++) {
            sum += signal[i];
        }
        //slide window
        for (int i = 0; i < n; i++) {
            if (i > windowSize) sum -= signal[i - windowSize]; //subtract oldest sample
            sum += signal[i]; //add newest sample
            output[i] = sum / windowSize;
        }
        return output;
    }

    public static double[] panTompkinsFilter(double[] signal, int windowSize){
        //apply Pan-Tompkins
        double[] bandSignal = bandPassFilter(signal);
        double[] diffSignal = differentiation(bandSignal);
        double[] squaredSignal = square(diffSignal);

        return movingWindow(squaredSignal, windowSize);
    }
}
