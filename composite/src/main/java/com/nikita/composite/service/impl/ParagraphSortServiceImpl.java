package com.nikita.composite.service.impl;

import com.nikita.composite.comparator.ParagraphLetterComparator;
import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.service.ParagraphSortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ParagraphSortServiceImpl implements ParagraphSortService {

    private final Logger logger = LogManager.getLogger(ParagraphSortServiceImpl.class);

    @Override
    public void sortParagraphsByLetter(TextComponent root, char letter) {
        boolean isNotNull = root != null;

        if (isNotNull) {
            ComponentType type = root.getType();
            boolean isText = type == ComponentType.TEXT;

            if (isText) {
                List<TextComponent> paragraphs = root.getChildren();
                List<TextComponent> sortedList = new ArrayList<>(paragraphs);
                
                ParagraphLetterComparator comparator = new ParagraphLetterComparator(letter);
                sortedList.sort(comparator);

                for (TextComponent p : paragraphs) {
                    root.remove(p);
                }
                for (TextComponent p : sortedList) {
                    root.add(p);
                }
                logger.info("Sorted paragraphs by letter: " + letter);
            }
        }
    }
}