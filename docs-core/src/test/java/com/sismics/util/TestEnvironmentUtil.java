package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test of environment utilities.
 */
public class TestEnvironmentUtil {

    @Test
    public void testIsUnitTestDefault() {
        // By default webappContext is false, so isUnitTest should return true
        Assert.assertTrue(EnvironmentUtil.isUnitTest());
    }

    @Test
    public void testIsWebappContextDefault() {
        Assert.assertFalse(EnvironmentUtil.isWebappContext());
    }

    @Test
    public void testSetWebappContext() {
        EnvironmentUtil.setWebappContext(true);
        Assert.assertTrue(EnvironmentUtil.isWebappContext());
        Assert.assertFalse(EnvironmentUtil.isUnitTest());

        // Reset to default
        EnvironmentUtil.setWebappContext(false);
        Assert.assertFalse(EnvironmentUtil.isWebappContext());
        Assert.assertTrue(EnvironmentUtil.isUnitTest());
    }

    @Test
    public void testIsDevMode() {
        // Should not throw and returns a boolean
        boolean result = EnvironmentUtil.isDevMode();
        // We don't assert the exact value because it depends on JVM properties
    }

    @Test
    public void testIsWindows() {
        boolean result = EnvironmentUtil.isWindows();
        // Running on Linux, so should be false
        Assert.assertFalse(result);
    }

    @Test
    public void testIsMacOs() {
        boolean result = EnvironmentUtil.isMacOs();
        Assert.assertFalse(result);
    }

    @Test
    public void testIsUnix() {
        boolean result = EnvironmentUtil.isUnix();
        Assert.assertTrue(result);
    }

    @Test
    public void testGetWindowsAppData() {
        // May be null on Linux
        String appData = EnvironmentUtil.getWindowsAppData();
        // Just verify it does not throw
    }

    @Test
    public void testGetMacOsUserHome() {
        String home = EnvironmentUtil.getMacOsUserHome();
        Assert.assertNotNull(home);
    }

    @Test
    public void testGetTeedyHome() {
        // May be null if system property is not set
        String teedyHome = EnvironmentUtil.getTeedyHome();
        // Just verify it does not throw
    }
}
