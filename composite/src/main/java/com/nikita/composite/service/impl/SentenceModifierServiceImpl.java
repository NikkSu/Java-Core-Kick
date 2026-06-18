package com.nikita.composite.service.impl;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.service.SentenceModifierService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SentenceModifierServiceImpl implements SentenceModifierService {

    private final Logger logger = LogManager.getLogger(SentenceModifierServiceImpl.class);

    @Override
    public void swapFirstAndLastLexemes(TextComponent root) {
        boolean isNotNull = root != null;

        if (isNotNull) {
            ComponentType type = root.getType();
            boolean isSentence = type == ComponentType.SENTENCE;

            if (isSentence) {
                List<TextComponent> children = root.getChildren();
                int size = children.size();
                boolean canSwap = size > 1;

                if (canSwap) {
                    int lastIndex = size - 1;
                    TextComponent first = children.getFirst();
                    TextComponent last = children.get(lastIndex);

                    root.remove(first);
                    root.remove(last);

                    List<TextComponent> allChildren = root.getChildren();
                    for (TextComponent c : allChildren) {
                        root.remove(c);
                    }
                    
                    root.add(last);
                    for (int i = 1; i < lastIndex; i++) {
                        TextComponent middle = children.get(i);
                        root.add(middle);
                    }
                    root.add(first);
                }
            } else {
                List<TextComponent> children = root.getChildren();
                for (TextComponent child : children) {
                    swapFirstAndLastLexemes(child);
                }
            }
            logger.info("Successfully swapped lexemes");
        }
    }
}