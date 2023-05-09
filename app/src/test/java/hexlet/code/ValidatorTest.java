package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    static Validator validator;
    @BeforeAll
    public static void beforeAll() {
        validator = new Validator();
    }
    @Test
    public void stringTest() {
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
    @Test
    public void numberTest() {
        NumberSchema schema = validator.number();

        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.positive();
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(11));
    }
    @Test
    public void mapTest() {
        MapSchema schema = validator.map();
        HashMap<String, String> data = new HashMap<>();

        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(data));
        assertTrue(schema.isValid(null));

        schema.required();
        data.put("key1", "value1");
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(data));

        schema.sizeOf(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

}
