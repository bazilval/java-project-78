package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    @Test
    public void stringTest() {
        var validator = new Validator();
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
        var validator = new Validator();
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
        var validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, Object> data = new HashMap<>();

        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(data));
        assertTrue(schema.isValid(null));

        schema.required();
        data.put("name", "Kolya");
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("age", 100);
        assertTrue(schema.isValid(data));

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        assertTrue(schema.isValid(data));

        data.put("age", null);
        assertTrue(schema.isValid(data));

        data.put("name", "");
        assertFalse(schema.isValid(data));

        data.put("name", "Valya");
        data.put("age", -5);
        assertFalse(schema.isValid(data));
    }

}
