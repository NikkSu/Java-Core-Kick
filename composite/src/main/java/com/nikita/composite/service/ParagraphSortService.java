package com.nikita.composite.service;

import com.nikita.composite.entity.TextComponent;

public interface ParagraphSortService {
    void sortParagraphsByLetter(TextComponent root, char letter);
}