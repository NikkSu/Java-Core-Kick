package com.nikita.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final List<TextComponent> components = new ArrayList<>();
    private final ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        boolean isNotNull = component != null;
        if (isNotNull) {
            components.add(component);
        }
    }

    @Override
    public void remove(TextComponent component) {
        boolean isNotNull = component != null;
        if (isNotNull) {
            components.remove(component);
        }
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String reconstruct() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = components.size();

        for (int i = 0; i < size; i++) {
            TextComponent child = components.get(i);
            String childText = child.reconstruct();
            stringBuilder.append(childText);

            int lastIndex = size - 1;
            boolean isNotLast = i < lastIndex;

            if (isNotLast) {
                boolean isText = type == ComponentType.TEXT;
                boolean isParagraph = type == ComponentType.PARAGRAPH;
                boolean isSentence = type == ComponentType.SENTENCE;

                if (isText) {
                    stringBuilder.append("\n");
                } else if (isParagraph) {
                    stringBuilder.append("\t");
                } else if (isSentence) {
                    stringBuilder.append(" ");
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        Class<?> thisClass = getClass();
        Class<?> objClass = obj.getClass();
        if (thisClass != objClass) {
            return false;
        }

        TextComposite that = (TextComposite) obj;
        if (this.type != that.type) {
            return false;
        }

        int thisSize = this.components.size();
        int thatSize = that.components.size();
        if (thisSize != thatSize) {
            return false;
        }

        for (int i = 0; i < thisSize; i++) {
            TextComponent thisChild = this.components.get(i);
            TextComponent thatChild = that.components.get(i);
            boolean isEquals = thisChild.equals(thatChild);
            if (!isEquals) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int typeHash = type != null ? type.hashCode() : 0;
        result = 31 * result + typeHash;

        for (TextComponent component : components) {
            int childHash = component != null ? component.hashCode() : 0;
            result = 31 * result + childHash;
        }

        return result;
    }

    public List<TextComponent> getWords() {
        List<TextComponent> words = new ArrayList<>();
        boolean isWord = type == ComponentType.WORD;

        if (isWord) {
            words.add(this);
        } else {
            for (TextComponent child : components) {
                if (child instanceof TextComposite) {
                    TextComposite compositeChild = (TextComposite) child;
                    List<TextComponent> childWords = compositeChild.getWords();
                    words.addAll(childWords);
                }
            }
        }
        return words;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("TextComposite{type=");
        stringBuilder.append(type);
        stringBuilder.append(", childrenCount=");

        int size = components.size();
        stringBuilder.append(size);

        stringBuilder.append(", text='");

        String reconstructedText = this.reconstruct();
        stringBuilder.append(reconstructedText);

        stringBuilder.append("'}");

        return stringBuilder.toString();
    }
}