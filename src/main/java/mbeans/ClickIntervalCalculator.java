package mbeans;

public class ClickIntervalCalculator implements ClickIntervalCalculatorMBean {
    private Long prevClickTime = null;
    private double intervalSum = 0;
    private long intervalCount = 0;

    public void registerClick() {
        long currentTime = System.nanoTime();
        if (prevClickTime != null) {
            double interval = (currentTime - prevClickTime) / 1e9;
            intervalSum += interval;
            ++intervalCount;
        }
        prevClickTime = currentTime;
    }

    @Override
    public double getAverageInterval() {
        if (intervalCount == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return intervalSum / intervalCount;
    }
}
