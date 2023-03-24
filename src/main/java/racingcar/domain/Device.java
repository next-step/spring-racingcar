package racingcar.domain;

public class Device {

    private String names;

    private int count;

    public Device(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
