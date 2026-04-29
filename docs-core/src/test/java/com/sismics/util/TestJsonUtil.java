package com.sismics.util;

import jakarta.json.JsonValue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of JSON utilities.
 */
public class TestJsonUtil {

    @Test
    public void testNullableStringNull() {
        JsonValue result = JsonUtil.nullable((String) null);
        Assert.assertEquals(JsonValue.ValueType.NULL, result.getValueType());
    }

    @Test
    public void testNullableStringNonNull() {
        JsonValue result = JsonUtil.nullable("hello");
        Assert.assertEquals(JsonValue.ValueType.STRING, result.getValueType());
        Assert.assertEquals("hello", result.toString().replace("\"", ""));
    }

    @Test
    public void testNullableIntegerNull() {
        JsonValue result = JsonUtil.nullable((Integer) null);
        Assert.assertEquals(JsonValue.ValueType.NULL, result.getValueType());
    }

    @Test
    public void testNullableIntegerNonNull() {
        JsonValue result = JsonUtil.nullable(42);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, result.getValueType());
    }

    @Test
    public void testNullableLongNull() {
        JsonValue result = JsonUtil.nullable((Long) null);
        Assert.assertEquals(JsonValue.ValueType.NULL, result.getValueType());
    }

    @Test
    public void testNullableLongNonNull() {
        JsonValue result = JsonUtil.nullable(123456789L);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, result.getValueType());
    }
}
