package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        predicateList.add(x -> x instanceof Map<?, ?> || x == null);
    }

    public MapSchema required() {
        predicateList.add(Objects::nonNull);
        return this;
    }
    public MapSchema sizeof(int size) {
        Predicate<Map> predicate = x -> x.size() == size;
        predicateList.add(predicate);
        return this;
    }
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        if (schemas != null) {
            var predicat = new ShapePredicate(schemas);
            predicateList.add(predicat);
        }
        return this;
    }
    private final class ShapePredicate implements Predicate<Map> {
        private final Map<String, BaseSchema> schemas;

        ShapePredicate(Map<String, BaseSchema> shape) {
            this.schemas = shape;
        }
        @Override
        public boolean test(Map map) {
            var entrySet = schemas.entrySet();
            for (var entry : entrySet) {
                try {
                    String key = (String) entry.getKey();
                    Object value = map.get(key);
                    BaseSchema schema = (BaseSchema) entry.getValue();

                    if (!schema.isValid(value)) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }
    }
}
