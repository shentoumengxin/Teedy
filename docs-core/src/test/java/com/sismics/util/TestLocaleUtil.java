package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test of locale utilities.
 */
public class TestLocaleUtil {

    @Test
    public void testGetLocaleNull() {
        Locale locale = LocaleUtil.getLocale(null);
        Assert.assertEquals(Locale.ENGLISH, locale);
    }

    @Test
    public void testGetLocaleEmpty() {
        Locale locale = LocaleUtil.getLocale("");
        Assert.assertEquals(Locale.ENGLISH, locale);
    }

    @Test
    public void testGetLocaleLanguageOnly() {
        Locale locale = LocaleUtil.getLocale("en");
        Assert.assertEquals("en", locale.getLanguage());
        Assert.assertEquals("", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void testGetLocaleLanguageAndCountry() {
        Locale locale = LocaleUtil.getLocale("en_US");
        Assert.assertEquals("en", locale.getLanguage());
        Assert.assertEquals("US", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void testGetLocaleLanguageCountryAndVariant() {
        Locale locale = LocaleUtil.getLocale("zh_CN_variant");
        Assert.assertEquals("zh", locale.getLanguage());
        Assert.assertEquals("CN", locale.getCountry());
        Assert.assertEquals("variant", locale.getVariant());
    }
}
