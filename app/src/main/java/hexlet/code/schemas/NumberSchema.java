package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        predicateList.add(x -> x instanceof Integer || x == null);
    }
    public NumberSchema required() {
        predicateList.add(Objects::nonNull);
        return this;
    }
    public NumberSchema positive() {
        Predicate<Integer> predicate = x -> x == null || x > 0;
        predicateList.add(predicate);
        return this;
    }
    public NumberSchema range(int minRange, int maxRange) {
        Predicate<Integer> predicate = x -> x >= minRange && x <= maxRange;
        predicateList.add(predicate);
        return this;
    }
}
