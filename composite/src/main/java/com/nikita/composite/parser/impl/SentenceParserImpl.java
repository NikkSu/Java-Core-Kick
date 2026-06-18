package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParserImpl implements TextParser {

    private final Logger logger = LogManager.getLogger(SentenceParserImpl.class);
    private final TextParser nextParser;

    public SentenceParserImpl(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextComponent component, String text) {
        boolean isNotNull = text != null;

        if (isNotNull) {
            String[] sentences = text.split(SENTENCE_REGEX);
            int length = sentences.length;
            logger.debug("Parsed paragraph into sentences. Count: " + length);

            for (String sentence : sentences) {
                TextComposite sentenceComponent = new TextComposite(ComponentType.SENTENCE);
                component.add(sentenceComponent);

                boolean hasNextParser = nextParser != null;
                if (hasNextParser) {
                    nextParser.parse(sentenceComponent, sentence);
                }
            }
        }
    }
}