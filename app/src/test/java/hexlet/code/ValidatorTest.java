package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    @Test
    public void test() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("what does the fox say"));

        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("what does the fox say"));

        schema.minLength(5);
        assertFalse(schema.isValid("mem"));
        assertTrue(schema.isValid("memem"));
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("what");
        assertFalse(schema.isValid("that does the fox say"));
        assertTrue(schema.isValid("what does the fox say"));
    }

}
