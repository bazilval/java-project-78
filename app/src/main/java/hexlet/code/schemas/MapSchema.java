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
            Predicate<Map> predicat = map -> {
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
            };
            predicateList.add(predicat);
        }
        return this;
    }
}
