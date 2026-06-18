package com.nikita.composite.parser;

import com.nikita.composite.entity.TextComponent;

public interface TextParser {
    void parse(TextComponent component, String text);
}