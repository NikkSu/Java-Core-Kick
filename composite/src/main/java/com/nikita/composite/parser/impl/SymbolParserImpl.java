package com.nikita.composite.parser.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.SymbolLeaf;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParserImpl implements TextParser {

    private final Logger logger = LogManager.getLogger(SymbolParserImpl.class);

    public SymbolParserImpl() {}

    @Override
    public void parse(TextComponent component, String text) {
        boolean isNotNull = text != null;

        if (isNotNull) {
            char[] chars = text.toCharArray();
            int length = chars.length;
            logger.debug("Parsed string into symbols. Count: " + length);

            for (char symbol : chars) {
                SymbolLeaf leaf = new SymbolLeaf(symbol, ComponentType.SYMBOL);
                component.add(leaf);
            }
        } else {
            logger.warn("Received null text for symbol parsing");
        }
    }
}