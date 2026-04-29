package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test of HTTP utilities.
 */
public class TestHttpUtil {

    @Test
    public void testBuildExpiresHeaderZero() {
        String header = HttpUtil.buildExpiresHeader(0);
        Assert.assertNotNull(header);
        Assert.assertTrue(header.length() > 0);
    }

    @Test
    public void testBuildExpiresHeaderFuture() {
        String header = HttpUtil.buildExpiresHeader(3600000);
        Assert.assertNotNull(header);
        Assert.assertTrue(header.length() > 0);
    }
}
