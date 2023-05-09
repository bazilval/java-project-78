package hexlet.code.schemas;

import java.util.Map;

public class MapSchema implements BaseSchema {
    private boolean required;
    private Integer requiredSize;
    private Map<String, BaseSchema> shape;

    public MapSchema() {
        this.required = false;
        this.requiredSize = null;
        this.shape = null;
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        requiredSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        shape = schemas;
        return this;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null) {
            return !required;
        }

        if (!(object instanceof Map<?, ?>)) {
            return false;
        }

        var map = (Map<?, ?>) object;

        if (requiredSize != null && map.size() != requiredSize) {
            return false;
        }

        if (shape != null) {
            var entrySet = shape.entrySet();
            for (var entry : entrySet) {
                try {
                    String key = (String) entry.getKey();
                    BaseSchema schema = (BaseSchema) entry.getValue();

                    if (!schema.isValid(map.get(key))) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }

        return true;
    }
}
