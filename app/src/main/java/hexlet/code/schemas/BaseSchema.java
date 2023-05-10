package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected List<Predicate> predicateList = new ArrayList<>();
    /**
     * Checks if the given value is valid according to its schema.
     * @param object the value to check
     * @return true if the value is valid, false otherwise
     */
    public boolean isValid(Object object) {
        return predicateList.stream()
                .allMatch(p -> p.test(object));
    }
}
