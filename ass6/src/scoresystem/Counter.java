package scoresystem;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class Counter {
    private int value;

    /**
     * increase the counter by a given number.
     * @param number int
     */
    public void increase(int number) {
        value = value + number;
    }

    /**
     * reduce the counter by given number.
     * @param number int
     */
    public void decrease(int number) {
        value = value - number;
    }

    /**
     * get the current counter values.
     * @return int value
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}