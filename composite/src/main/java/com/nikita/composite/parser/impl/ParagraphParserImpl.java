package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParserImpl implements TextParser {

    private final Logger logger = LogManager.getLogger(ParagraphParserImpl.class);
    private static final String PARAGRAPH_REGEX = "\\n";
    private final TextParser nextParser;

    public ParagraphParserImpl(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextComponent component, String text) {
        boolean isNotNull = text != null;

        if (isNotNull) {
            String[] paragraphs = text.split(PARAGRAPH_REGEX);
            int length = paragraphs.length;
            logger.info("Parsed text into paragraphs. Count: " + length);

            for (String paragraph : paragraphs) {
                TextComposite paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);
                component.add(paragraphComponent);

                boolean hasNextParser = nextParser != null;
                if (hasNextParser) {
                    nextParser.parse(paragraphComponent, paragraph);
                }
            }
        } else {
            logger.warn("Received null text for paragraph parsing");
        }
    }
}