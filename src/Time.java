import java.util.Arrays;
import java.util.List;

public class Time {

    public static Time ZERO = new Time(0);
    final int value;

    public Time(int value) {
        this.value = value;
    }

    // Converteste valorile tip ora:minut in minute.
    public Time(String value) {
        List<Integer> times = Arrays.stream(value.split(":")).map(Integer::parseInt).toList();
        this.value = times.get(0) * 60 + times.get(1);
    }

    // Converteste minutele in ora:minut.
    @Override
    public String toString() {
        return value / 60 + ":" + String.format("%02d", value % 60);
    }

    public int getValue() {
        return value;
    }

    public Time add(Time time) {
        return new Time(this.value + time.getValue());
    }

    public Time add(int time) {
        return new Time(this.value + time);
    }
}