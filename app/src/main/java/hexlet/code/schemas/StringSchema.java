package hexlet.code.schemas;

public class StringSchema implements Schema {
    private boolean required;
    private int minLength;
    private String requiredContent;

    public StringSchema() {
        this.required = false;
        this.minLength = 0;
        this.requiredContent = "";
    }
    public StringSchema required() {
        required = true;
        return this;
    }
    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }
    public StringSchema contains(String content) {
        requiredContent = content;
        return this;
    }
    public boolean isValid(Object object) {
        if (object == null) {
            return !required;
        }

        if (object.getClass() != String.class) {
            return false;
        }

        var string = (String) object;

        if (required && string.isEmpty()) {
            return false;
        }

        if (string.length() < minLength) {
            return false;
        }

        return string.contains(requiredContent);
    }
}
