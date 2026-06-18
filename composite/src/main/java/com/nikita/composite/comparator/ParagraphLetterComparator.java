package com.nikita.composite.comparator;

import com.nikita.composite.entity.TextComponent;

import java.util.Comparator;

public class ParagraphLetterComparator implements Comparator<TextComponent> {

    private final char targetLetter;

    public ParagraphLetterComparator(char targetLetter) {
        this.targetLetter = Character.toLowerCase(targetLetter);
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        int count1 = countLetter(o1);
        int count2 = countLetter(o2);

        int result = Integer.compare(count2, count1);

        boolean isEquals = result == 0;
        if (isEquals) {
            String str1 = o1.reconstruct();
            String str2 = o2.reconstruct();
            result = str1.compareToIgnoreCase(str2);
        }

        return result;
    }

    private int countLetter(TextComponent component) {
        String text = component.reconstruct();
        String lowerText = text.toLowerCase();
        char[] chars = lowerText.toCharArray();
        int count = 0;

        for (char c : chars) {
            boolean isMatch = c == targetLetter;
            if (isMatch) {
                count++;
            }
        }
        return count;
    }
}