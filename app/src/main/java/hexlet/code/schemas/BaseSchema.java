package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected List<Predicate> predicateList = new ArrayList<>();
    public boolean isValid(Object object) {
        return predicateList.stream()
                .allMatch(p -> p.test(object));
    }
}
