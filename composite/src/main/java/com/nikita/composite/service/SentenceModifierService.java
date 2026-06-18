package com.nikita.composite.service;

import com.nikita.composite.entity.TextComponent;

public interface SentenceModifierService {
    void swapFirstAndLastLexemes(TextComponent root);
}