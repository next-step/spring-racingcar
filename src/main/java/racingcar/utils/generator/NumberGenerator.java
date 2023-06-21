package racingcar.utils.generator;

import java.util.List;

public interface NumberGenerator {
    List<Integer> calculatePositions(int size, int count);
    int calculatePosition(int n);
}
