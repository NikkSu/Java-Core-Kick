package com.nikita.composite.service.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.service.SentenceAnalyzerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SentenceAnalyzerServiceImpl implements SentenceAnalyzerService {

    private final Logger logger = LogManager.getLogger(SentenceAnalyzerServiceImpl.class);

    @Override
    public List<TextComponent> findSentencesWithIdenticalWords(TextComponent root) {
        List<TextComponent> result = new ArrayList<>();
        boolean isNotNull = root != null;

        if (isNotNull) {
            List<TextComponent> sentences = findSentences(root);
            
            for (TextComponent sentence : sentences) {
                boolean hasIdentical = hasIdenticalWords(sentence);
                if (hasIdentical) {
                    result.add(sentence);
                }
            }
            int size = result.size();
            logger.info("Found sentences with identical words: " + size);
        }
        return result;
    }

    private List<TextComponent> findSentences(TextComponent component) {
        List<TextComponent> sentences = new ArrayList<>();
        ComponentType type = component.getType();
        boolean isSentence = type == ComponentType.SENTENCE;

        if (isSentence) {
            sentences.add(component);
        } else {
            List<TextComponent> children = component.getChildren();
            for (TextComponent child : children) {
                List<TextComponent> childSentences = findSentences(child);
                sentences.addAll(childSentences);
            }
        }
        return sentences;
    }

    private boolean hasIdenticalWords(TextComponent sentence) {
        if (sentence instanceof TextComposite) {
            TextComposite composite = (TextComposite) sentence;
            List<TextComponent> words = composite.getWords();
            Set<String> uniqueWords = new HashSet<>();
            
            for (TextComponent word : words) {
                String wordStr = word.reconstruct();
                String lowerWord = wordStr.toLowerCase();
                boolean isAdded = uniqueWords.add(lowerWord);
                
                if (!isAdded) {
                    return true;
                }
            }
        }
        return false;
    }
}