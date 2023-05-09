package hexlet.code.schemas;

public class NumberSchema implements BaseSchema {
    private boolean required;
    private boolean positive;
    private int min;
    private int max;

    public NumberSchema() {
        this.required = false;
        this.positive = false;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }
    public NumberSchema required() {
        required = true;
        return this;
    }
    public NumberSchema positive() {
        positive = true;
        return this;
    }
    public NumberSchema range(int minRange, int maxRange) {
        this.min = minRange;
        this.max = maxRange;
        return this;
    }
    @Override
    public boolean isValid(Object object) {
        if (object == null) {
            return !required;
        }

        if (object.getClass() != Integer.class) {
            return false;
        }

        var num = (int) object;

        if (positive && num <= 0) {
            return false;
        }

        return num >= min && num <= max;
    }
}
