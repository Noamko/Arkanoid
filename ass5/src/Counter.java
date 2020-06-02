public class Counter {
    private int value;
    void increase(int number) {
        value = value + number;
    }
    void decrease(int number) {
        System.out.println(value);
        value = value - number;
    }
    int getValue() {
        return value;
    }
}