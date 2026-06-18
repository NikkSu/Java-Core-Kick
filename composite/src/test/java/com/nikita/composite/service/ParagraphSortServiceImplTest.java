package com.nikita.composite.service;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.impl.LexemeParserImpl;
import com.nikita.composite.parser.impl.ParagraphParserImpl;
import com.nikita.composite.parser.impl.SentenceParserImpl;
import com.nikita.composite.parser.impl.SymbolParserImpl;
import com.nikita.composite.parser.impl.WordParserImpl;
import com.nikita.composite.service.impl.ParagraphSortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParagraphSortServiceImplTest {

    private final ParagraphSortServiceImpl sortService = new ParagraphSortServiceImpl();
    private ParagraphParserImpl paragraphParser;

    @BeforeEach
    void setUp() {
        SymbolParserImpl symbolParser = new SymbolParserImpl();
        WordParserImpl wordParser = new WordParserImpl(symbolParser);
        LexemeParserImpl lexemeParser = new LexemeParserImpl(wordParser);
        SentenceParserImpl sentenceParser = new SentenceParserImpl(lexemeParser);
        paragraphParser = new ParagraphParserImpl(sentenceParser);
    }

    @Test
    void testSortParagraphsByLetter_ValidRoot_SortsCorrectly() {
        TextComponent rootText = new TextComposite(ComponentType.TEXT);

        String rawText = "cat.\nbanana.\nbat.";

        paragraphParser.parse(rootText, rawText);

        char targetLetter = 'a';

        String expectedFirst = "banana.";
        String expectedSecond = "bat.";
        String expectedThird = "cat.";

        sortService.sortParagraphsByLetter(rootText, targetLetter);

        List<TextComponent> sortedParagraphs = rootText.getChildren();

        String actualFirst = sortedParagraphs.get(0).reconstruct().trim();
        String actualSecond = sortedParagraphs.get(1).reconstruct().trim();
        String actualThird = sortedParagraphs.get(2).reconstruct().trim();

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
        assertEquals(expectedThird, actualThird);
    }
}