package com.sismics.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of HTML to plain text utility.
 */
public class TestHtmlToPlainText {

    @Test
    public void testSimpleParagraph() {
        HtmlToPlainText converter = new HtmlToPlainText();
        Document doc = Jsoup.parse("<p>Hello World</p>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("Hello World"));
    }

    @Test
    public void testAnchorLink() {
        HtmlToPlainText converter = new HtmlToPlainText();
        Document doc = Jsoup.parse("<a href=\"http://example.com\">link</a>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("link"));
        Assert.assertTrue(plain.contains("http://example.com"));
    }

    @Test
    public void testListItem() {
        HtmlToPlainText converter = new HtmlToPlainText();
        Document doc = Jsoup.parse("<ul><li>item one</li><li>item two</li></ul>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("item one"));
        Assert.assertTrue(plain.contains("item two"));
    }

    @Test
    public void testLongTextWrap() {
        HtmlToPlainText converter = new HtmlToPlainText();
        String longText = "This is a very long text that should definitely exceed the eighty character limit and trigger the word wrapping logic in the formatter.";
        Document doc = Jsoup.parse("<p>" + longText + "</p>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("This is a very long text"));
    }

    @Test
    public void testMultipleParagraphs() {
        HtmlToPlainText converter = new HtmlToPlainText();
        Document doc = Jsoup.parse("<p>First paragraph.</p><p>Second paragraph.</p>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("First paragraph."));
        Assert.assertTrue(plain.contains("Second paragraph."));
    }

    @Test
    public void testHeading() {
        HtmlToPlainText converter = new HtmlToPlainText();
        Document doc = Jsoup.parse("<h1>Title</h1>");
        String plain = converter.getPlainText(doc.body());
        Assert.assertTrue(plain.contains("Title"));
    }
}
