package racingcar.domain;

public enum MovementAction {
    MOVE, STOP;

    public boolean isMove() {
        return this == MovementAction.MOVE;
    }
}
