package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        predicateList.add(x -> x instanceof String || x == null);
    }
    public StringSchema required() {
        predicateList.add(Objects::nonNull);
        Predicate<String> predicate = x -> !x.isEmpty();
        predicateList.add(predicate);
        return this;
    }
    public StringSchema minLength(int length) {
        Predicate<String> predicate = x -> x.length() >= length;
        predicateList.add(predicate);
        return this;
    }
    public StringSchema contains(String content) {
        Predicate<String> predicate = x -> x.contains(content);
        predicateList.add(predicate);
        return this;
    }
}
