package com.nikita.composite.service;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.impl.LexemeParserImpl;
import com.nikita.composite.parser.impl.SentenceParserImpl;
import com.nikita.composite.parser.impl.SymbolParserImpl;
import com.nikita.composite.parser.impl.WordParserImpl;
import com.nikita.composite.service.impl.SentenceModifierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceModifierServiceImplTest {

    private final SentenceModifierServiceImpl modifierService = new SentenceModifierServiceImpl();
    private SentenceParserImpl sentenceParser;

    @BeforeEach
    void setUp() {
        SymbolParserImpl symbolParser = new SymbolParserImpl();
        WordParserImpl wordParser = new WordParserImpl(symbolParser);
        LexemeParserImpl lexemeParser = new LexemeParserImpl(wordParser);
        sentenceParser = new SentenceParserImpl(lexemeParser);
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