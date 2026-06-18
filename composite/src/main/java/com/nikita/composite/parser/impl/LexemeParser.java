package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements TextParser {

    private final Logger logger = LogManager.getLogger(LexemeParser.class);
    private final TextParser nextParser;

    public LexemeParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextComponent component, String text) {
        boolean isNotNull = text != null;

        if (isNotNull) {
            String[] lexemes = text.split(LEXEME_REGEX);
            int length = lexemes.length;
            logger.debug("Parsed sentence into lexemes. Count: " + length);

            for (String lexeme : lexemes) {
                TextComposite lexemeComponent = new TextComposite(ComponentType.LEXEME);
                component.add(lexemeComponent);

                boolean hasNextParser = nextParser != null;
                if (hasNextParser) {
                    nextParser.parse(lexemeComponent, lexeme);
                }
            }
        }
    }
}