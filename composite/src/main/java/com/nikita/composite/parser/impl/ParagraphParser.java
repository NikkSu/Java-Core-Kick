package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements TextParser {

    private final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private final TextParser nextParser;

    public ParagraphParser(TextParser nextParser) {
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
        }
    }
}