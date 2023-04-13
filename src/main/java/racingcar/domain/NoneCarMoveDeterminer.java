package racingcar.domain;

public class NoneCarMoveDeterminer implements CarMoveDeterminer {
    public NoneCarMoveDeterminer() {
    }

    @Override
    public boolean isMove() {
        return false;
    }
}

