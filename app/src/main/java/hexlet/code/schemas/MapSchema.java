package hexlet.code.schemas;

import java.util.Map;

public class MapSchema implements BaseSchema {
    private boolean required;
    private Integer requiredSize;

    public MapSchema() {
        this.required = false;
        this.requiredSize = null;
    }
    public MapSchema required() {
        required = true;
        return this;
    }
    public MapSchema sizeOf(int size) {
        requiredSize = size;
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

        if (requiredSize != null) {
            return map.size() == requiredSize;
        }

        return true;
    }
}
