package com.nikita.composite.service;

import com.nikita.composite.entity.TextComponent;
import java.util.List;

public interface SentenceAnalyzerService {
    List<TextComponent> findSentencesWithIdenticalWords(TextComponent root);
}