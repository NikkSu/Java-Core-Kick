package com.nikita.composite.parser;

import com.nikita.composite.entity.TextComponent;

public interface TextParser {
    static final String LEXEME_REGEX = "\\s+";
    static final String PARAGRAPH_REGEX = "\\n";
    static final String SENTENCE_REGEX = "(?<=[.!?…])\\s+";
    static final String WORD_PUNCTUATION_REGEX = "(?=[\\p{Punct}])|(?<=[\\p{Punct}])";
    static final String WORD_CHECK_REGEX = ".*\\w+.*";

    void parse(TextComponent component, String text);
}