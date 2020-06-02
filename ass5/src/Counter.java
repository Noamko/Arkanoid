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
    void increase(int number) {
        value = value + number;
    }

    /**
     * reduce the counter by given number.
     * @param number int
     */
    void decrease(int number) {
        value = value - number;
    }

    /**
     * get the current counter values.
     * @return int value
     */
    int getValue() {
        return value;
    }
}