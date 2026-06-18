package com.nikita.composite.service;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.impl.LexemeParser;
import com.nikita.composite.parser.impl.SentenceParser;
import com.nikita.composite.parser.impl.SymbolParser;
import com.nikita.composite.parser.impl.WordParser;
import com.nikita.composite.service.impl.SentenceModifierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceModifierServiceTest {

    private final SentenceModifierServiceImpl modifierService = new SentenceModifierServiceImpl();
    private SentenceParser sentenceParser;

    @BeforeEach
    void setUp() {
        SymbolParser symbolParser = new SymbolParser();
        WordParser wordParser = new WordParser(symbolParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser);
        sentenceParser = new SentenceParser(lexemeParser);
    }

    @ParameterizedTest
    @CsvSource({
            "'First second third last', 'last second third First '",
            "'One two', 'two One '",
            "'OnlyOne', 'OnlyOne'"
    })
    void testSwapFirstAndLastLexemes_ValidSentences_SwapsCorrectly(String input, String expected) {
        TextComposite rootText = new TextComposite(ComponentType.TEXT);
        sentenceParser.parse(rootText, input);

        modifierService.swapFirstAndLastLexemes(rootText);

        String reconstructed = rootText.reconstruct();
        String trimmedExpected = expected.trim();
        String trimmedActual = reconstructed.trim();
        assertEquals(trimmedExpected, trimmedActual);
    }
}