package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> shape;

    public MapSchema() {
        predicateList.add(x -> x instanceof Map<?, ?> || x == null);
        this.shape = null;
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
        shape = schemas;
        return this;
    }
    @Override
    public boolean isValid(Object object) {
        var shapeResult = true;
        if (shape != null) {
            var entrySet = shape.entrySet();
            var map = (Map) object;
            for (var entry : entrySet) {
                try {
                    String key = (String) entry.getKey();
                    BaseSchema schema = (BaseSchema) entry.getValue();

                    if (!schema.isValid(map.get(key))) {
                        shapeResult = false;
                    }
                } catch (Exception e) {
                    shapeResult = false;
                }
            }
        }

        return super.isValid(object) && shapeResult;
    }
}
