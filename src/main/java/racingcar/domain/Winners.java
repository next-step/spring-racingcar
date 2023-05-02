package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Winners {

    private final List<String> names;

    public Winners(List<String> names) {
        this.names = names;
    }

    public String getNames() {
        return this.names.stream()
                .collect(Collectors.joining(","));
    }
}
