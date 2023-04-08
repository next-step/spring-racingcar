package racingcar.domain;

public class ReadOnlyCarMoveEntropy implements CarMoveEntropy {
    public ReadOnlyCarMoveEntropy() {
    }

    public int getInt() {
        throw new UnsupportedOperationException("ReadOnly, you cannot move cars");
    }
}

