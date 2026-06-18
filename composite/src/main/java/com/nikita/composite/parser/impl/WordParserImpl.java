package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParserImpl implements TextParser {

    private final Logger logger = LogManager.getLogger(WordParserImpl.class);
    private final TextParser nextParser;

    public WordParserImpl(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextComponent component, String text) {
        boolean isNotNull = text != null;

        if (isNotNull) {
            String[] parts = text.split(WORD_PUNCTUATION_REGEX);
            int length = parts.length;
            logger.debug("Parsed lexeme into words and punctuation. Count: " + length);

            for (String part : parts) {
                boolean isEmpty = part.isEmpty();

                if (!isEmpty) {
                    boolean isWord = part.matches(WORD_CHECK_REGEX);
                    ComponentType type = isWord ? ComponentType.WORD : ComponentType.PUNCTUATION;

                    TextComposite partComponent = new TextComposite(type);
                    component.add(partComponent);

                    boolean hasNextParser = nextParser != null;
                    if (hasNextParser) {
                        nextParser.parse(partComponent, part);
                    }
                }
            }
        }
    }
}