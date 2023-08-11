package lecture1;

public class lecture1 {
    public static void main(String[] args) {
        double[] timer1000 = forTimeCont(10000);
        double[] timer10000 = forTimeCont(100000);
        double[] timer100000 = forTimeCont(1000000);

        System.out.println("For 10000, lowest time: " + String.format("%5f", timer1000[0]) + "s / average time: "
                + String.format("%5f", timer1000[1]) + "s / highest time: " + String.format("%5f", timer1000[2]) + "s");
        System.out.println("For 100000, lowest time: " + String.format("%5f", timer10000[0]) + "s / average time: "
                + String.format("%5f", timer10000[1]) + "s / highest time: " + String.format("%5f", timer10000[2])
                + "s");
        System.out.println("For 1000000, lowest time: " + String.format("%5f", timer100000[0]) + "s / average time: "
                + String.format("%5f", timer100000[1]) + "s / highest time: " + String.format("%5f", timer100000[2])
                + "s");
    }

    public static double[] forTimeCont(int n) {

        int[] vector = new int[n];

        for (int i = 0; i < n; i++) {
            vector[i] = 0;
        }
        double lowestTime = 10000000000000000.0;
        double highestTime = 0.0;
        double averageTime = 0.0;

        for (int i = 0; i < 10; i++) {

            long initialTime = System.nanoTime();

            for (int j = 0; j < n; j++) {
            }

            long finalTime = System.nanoTime();

            double totalTime = (double) (finalTime - initialTime) / Math.pow(10, 9);

            averageTime += totalTime;

            if (totalTime < lowestTime) {
                lowestTime = totalTime;
            }

            if (totalTime > highestTime) {
                highestTime = totalTime;
            }

        }
        double finalAverageTime = averageTime / 10;

        return new double[] { lowestTime, finalAverageTime, highestTime };
    }
}